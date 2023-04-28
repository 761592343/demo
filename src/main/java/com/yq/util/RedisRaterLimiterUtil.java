package com.yq.util;

import com.sun.istack.internal.logging.Logger;
import com.yq.redis.RedisConnPool;
import org.apache.commons.lang3.BooleanUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 依赖redis实现的简单限流工具，基于令牌桶算法
 * @author 姚琪
 * @createtime 2023/4/26
 */
public class RedisRaterLimiterUtil {
    // 限流控制器缓存，按照业务场景进行缓存，如果阈值有变化，则覆盖旧缓存
    private static final Map<String, RedisRaterLimiter> raterLimiterMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        while (true) {
            if (acquire("test", 1, 30000)) {
                System.out.println(System.currentTimeMillis());
            }

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ignored) {}
        }
    }

    /**
     * 尝试获取限流控制的访问授权<br/>
     * <strong> 示例：对于限流1分钟最多20次并发的情况，使用以下两种传参方式解释参数功能：<br/>
     * 1: threshold=20, windowSize=60*1000, 这种情况允许1s内出现20个请求，但是接下来59s无任何请求<br/>
     * 2: threshold=1, windowSize=3*1000, 这种情况会每3s只允许一次请求，匀速</strong>
     * @param key 业务场景的唯一标识
     * @param threshold 限流参数，单位时间的最大阈值
     * @param windowSize 限流参数，单位时间窗口大小
     * @return 授权结果
     * @author 姚琪
     * @createtime 2023/4/26
     */
    public static boolean acquire(String key, int threshold, int windowSize) {
        RedisRaterLimiter redisRaterLimiter = raterLimiterMap.get(key);

        if (redisRaterLimiter == null || redisRaterLimiter.threshold != threshold || redisRaterLimiter.windowSize != windowSize) {
            redisRaterLimiter = new RedisRaterLimiter(key, threshold, windowSize);
            raterLimiterMap.put(key, redisRaterLimiter);
        }

        return redisRaterLimiter.acquire();
    }

    /**
     * 令牌桶实现类型，
     * @author 姚琪
     * @createtime 2023/4/26
     */
    private static class RedisRaterLimiter {
        /** 单位时间的限流数量 */
        private final int threshold;
        /** 单位时间窗口大小，单位毫秒（窗口最大请求量的过期时间） */
        private final int windowSize;
        /** 窗口的当前请求量计数器的过期时间，单位毫秒（时间为窗口时间大小的毫秒数 + 10 * 1000ms）<br/>（windowSize + 10 * 1000） */
        private final int currentCounterExpire;
        /** 单位时间最大阈值限制的缓存Key（桶最大容量缓存） */
        private final String maxCountKey;
        /** 当前时间窗口的授权计数缓存 */
        private final String currCountKey;

        /**
         * 限流构造器，创建一个可复用的限流控制器<br/>
         * <strong> 示例：对于限流1分钟最多20次并发的情况，使用以下两种传参方式解释参数功能：<br/>
         * 1: threshold=20, windowSize=60*1000, 这种情况允许1s内出现20个请求，但是接下来59s无任何请求<br/>
         * 2: threshold=1, windowSize=3*1000, 这种情况会每3s只允许一次请求，匀速</strong>
         * @param raterLimiterKey 限流控制器的RedisKey，用于区分不同的业务
         * @param threshold 单位时间的限流数量
         * @param windowSize 单位时间窗口大小，单位毫秒
         * @author 姚琪
         * @createtime 2023/4/26
         */
        public RedisRaterLimiter(String raterLimiterKey, int threshold, int windowSize) {
            this.threshold = threshold;
            this.windowSize = windowSize;
            this.currentCounterExpire = windowSize + 10 * 1000;
            this.maxCountKey = raterLimiterKey + "_MAX_COUNT";
            this.currCountKey = raterLimiterKey + "_CURRENT_COUNT";
        }

        /**
         * 尝试获取限流控制令牌
         */
        private boolean acquire() {
            // 无效的限流值 直接返回有效令牌
            if (threshold <= 0 || windowSize <= 0) {
                return true;
            }

            try (Jedis jedisClient = RedisConnPool.getResource()) {
                SetParams params = new SetParams();
                params.nx();
                params.px(windowSize);
                // 尝试记录单位时间的最大总数
                Boolean initMaxCountFlag = "OK".equals(jedisClient.set(maxCountKey, String.valueOf(threshold), params));

                // 初始化成功则直接将当前时间窗口的请求数量置为1，并返回成功
                if (BooleanUtils.isTrue(initMaxCountFlag)) {
                    // 初始化当前请求计数器，时间单位：毫秒
                    jedisClient.psetex(currCountKey, currentCounterExpire, "1");
                    return true;
                }

                // 初始化失败有两种情况，1、当前时间窗口已经初始化；2、当前窗口未初始化但初始化失败了，忽略第2种情况
                long incr = jedisClient.incr(currCountKey);

                // 极端异常情况直接忽略
                if (incr < 0) {
                    return false;
                }

                return incr <= threshold;
            }
            catch (Exception e) {
                Logger.getLogger(RedisRaterLimiterUtil.class)
                        .warning("acquire----尝试获取限流控制令牌失败，未知异常 this: " + this, e);
            }

            return false;
        }

        @Override
        public String toString() {
            return "RedisRaterLimiter{" +
                    "threshold=" + threshold +
                    ", windowSize=" + windowSize +
                    ", currentCounterExpire=" + currentCounterExpire +
                    ", maxCountKey='" + maxCountKey + '\'' +
                    ", currCountKey='" + currCountKey + '\'' +
                    '}';
        }
    }
}
