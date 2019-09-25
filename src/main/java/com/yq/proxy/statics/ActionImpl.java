package com.yq.proxy.statics;

/**
 * 需要被代理的对象
 */
public class ActionImpl implements Action {
    public String object2String(Object obj) {
        System.out.println("进入了被代理的方法：object2String");

        if (obj == null) {
            return null;
        }

        return obj.toString();
    }

    public void printStr(Object obj) {
        System.out.println("进入了被代理的方法：printStr");
        System.out.println(obj);
    }
}