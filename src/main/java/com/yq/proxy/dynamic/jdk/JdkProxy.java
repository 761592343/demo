package com.yq.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理实现
 */
public class JdkProxy<T> implements InvocationHandler {
    // 需要代理的对象
    private T obj;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Jdk Proxy: 进入" + method.getName() + "方法前执行：" + "beforeProxy()");

        beforeProxy();
        Object res = method.invoke(this.obj, args);
        afterProxy();

        System.out.println("Jdk Proxy: 退出" + method.getName() + "方法前执行：" + "afterProxy()");
        return res;
    }

    /**
     * 在执行被代理方法前执行
     */
    private void beforeProxy() {
        // do something
    }

    /**
     * 在执行被代理方法后执行
     */
    private void afterProxy() {
        // do something
    }

    /**
     * 根据需要被代理的对象获取代理对象
     * @param obj 被代理的对象
     * @return 代理对象
     */
    public T getProxy(T obj) {
        this.obj = obj;
        return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }
}
