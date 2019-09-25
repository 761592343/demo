package com.yq.proxy.statics;

/**
 * 静态代理
 */
public class Proxy implements Action {
    // 被代理对象
    private Action obj;

    public Proxy(Action obj) {
        this.obj = obj;
    }

    @Override
    public String object2String(Object obj) {
        System.out.println("Jdk Proxy: 进入方法前执行：" + "beforeProxy()");

        beforeProxy();
        String res = this.obj.object2String(obj);
        afterProxy();

        System.out.println("Jdk Proxy: 退出方法前执行：" + "afterProxy()");
        return res;
    }

    @Override
    public void printStr(Object obj) {
        System.out.println("Jdk Proxy: 进入方法前执行：" + "beforeProxy()");

        beforeProxy();
        this.obj.printStr(obj);
        afterProxy();

        System.out.println("Jdk Proxy: 退出方法前执行：" + "afterProxy()");
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
}
