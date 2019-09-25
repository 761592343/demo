package com.yq.proxy.statics;

/**
 * 静态代理共同接口，代理对象和被代理对象都要实现该接口
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
