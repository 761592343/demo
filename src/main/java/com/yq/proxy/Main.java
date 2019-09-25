package com.yq.proxy;

import com.yq.proxy.dynamic.jdk.Action;
import com.yq.proxy.dynamic.jdk.ActionImpl;
import com.yq.proxy.dynamic.jdk.JdkProxy;

public class Main {
    /**
     * 测试Jdk动态代理的可用性
     */
    private static void jdkProxy() {
        Action action = new JdkProxy<Action>().getProxy(new ActionImpl());
        String res = action.object2String(123);
        action.printStr(res);
    }

    public static void main(String[] args) {
    }
}
