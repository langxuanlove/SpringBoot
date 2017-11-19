package com.test;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflect {
	public String show(String str) {
		System.out.println(str + "代理测试");
		return "success";
	}
	public void show1() {
		System.out.println("无参数代理测试");
	}
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TestReflect test=new TestReflect();
		Class _Clazz = test.getClass();
		Method _method;
		try {
			// 不带参数的反射使用
			_method = _Clazz.getMethod("show1");
			_method.invoke(test);
			System.out.println("以上是          不带参数的.============");
			
			// 带参数的反射使用
			_method = _Clazz.getMethod("show",new Class[] { String.class });
			System.out.println(_method.invoke(test, "带参数的"));
			System.out.println("以上是            带参数的.============");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
	}
}

	