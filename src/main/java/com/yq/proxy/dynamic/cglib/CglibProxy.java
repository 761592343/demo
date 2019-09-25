package com.yq.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * Cglib代理实现
 * @param <T>
 */
public class CglibProxy<T> implements MethodInterceptor {
    // 需要代理的对象
    private T obj;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {
        System.out.println("Cglib Proxy: 进入" + method.getName() + "方法前执行：" + "beforeProxy()");

        beforeProxy();
        Object res = method.invoke(this.obj, objects);
        afterProxy();

        System.out.println("Cglib Proxy: 退出" + method.getName() + "方法前执行：" + "afterProxy()");
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
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }
}
