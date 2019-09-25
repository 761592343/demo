package com.yq.proxy;

import com.yq.proxy.dynamic.cglib.CglibProxy;
import com.yq.proxy.dynamic.jdk.JdkProxy;
import com.yq.proxy.statics.Proxy;

/**
 * 代理模式demo实现
 * 静态代理需要代理类和被代理类都实现相同的接口；
 * Jdk实现的动态代理需要实现接口；
 * Cglib实现的动态代理不需要实现接口。
 */
public class Main {
    /**
     * 测试静态代理
     */
    private static void staticProxy() {
        com.yq.proxy.statics.Action action = new Proxy(new com.yq.proxy.statics.ActionImpl());
        String res = action.object2String(123);
        action.printStr(res);
    }

    /**
     * 测试Jdk动态代理的可用性
     */
    private static void jdkProxy() {
        com.yq.proxy.dynamic.jdk.Action action = new JdkProxy<com.yq.proxy.dynamic.jdk.Action>()
                .getProxy(new com.yq.proxy.dynamic.jdk.ActionImpl());
        String res = action.object2String(123);
        action.printStr(res);
    }

    /**
     * 测试Cglib动态代理的可用性
     */
    private static void cglibProxy() {
        com.yq.proxy.dynamic.cglib.Action action = new CglibProxy<com.yq.proxy.dynamic.cglib.Action>()
                .getProxy(new com.yq.proxy.dynamic.cglib.Action());
        String res = action.object2String(123);
        action.printStr(res);
    }

    public static void main(String[] args) {
        staticProxy();
        jdkProxy();
        cglibProxy();
    }
}
