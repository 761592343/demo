package com.yq.proxy.dynamic.jdk;

/**
 * 动态代理：使用jdk实现动态代理必须实现接口
 */
public interface Action {
    /**
     * 对象转字符串
     * @param obj
     * @return
     */
    String object2String(Object obj);

    /**
     * 打印对象
     * @param obj
     */
    void printStr(Object obj);
}
