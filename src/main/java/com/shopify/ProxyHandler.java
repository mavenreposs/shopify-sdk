package com.shopify;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * ClassName ProxyHandler
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/27 17:38
 */
public class ProxyHandler implements InvocationHandler {

    private final Object object;

    public ProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke " + method.getName());
        Object result = method.invoke(object, args);
        System.out.println("After invoke " + method.getName());
        return result;
    }

}
