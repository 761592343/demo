package com.yq.proxy.dynamic.cglib;

/**
 * 需要被代理的对象，cglib动态代理不需要实现接口
 */
public class Action {
    /**
     * 对象转字符串
     * @param obj
     * @return
     */
    public String object2String(Object obj) {
        System.out.println("进入了被代理的方法：object2String");

        if (obj == null) {
            return null;
        }

        return obj.toString();
    }

    /**
     * 打印对象
     * @param obj
     */
    public void printStr(Object obj) {
        System.out.println("进入了被代理的方法：printStr");
        System.out.println(obj);
    }
}
