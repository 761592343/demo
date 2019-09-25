package com.yq.proxy;

import com.yq.proxy.dynamic.cglib.CglibProxy;
import com.yq.proxy.dynamic.jdk.JdkProxy;

public class Main {
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
    }
}
