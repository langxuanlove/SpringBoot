package com.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;


public class TestEquals {

//	@Test
	public void testEqualsAndHashcode(){
		String a=new String ("abc");
		String b="abc";
		String c="abc";
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(c.hashCode());
		System.out.println(a==b);
		System.out.println(b==c);
		System.out.println(a.equals(b));
		System.out.println(c.equals(b));
		System.out.println(new Object().equals(new Object()));
		String a1=new String("abc");
		System.out.println(a.equals(a1));
		System.out.println(a==a1);
//		String s = new String("xyz");
//		创建两个对象
//		第一个  new的时候 会在堆生成一个对象
//		 第二个 当成参数传入的"xyz"会在常量池生成一个对象 以后用 String x="xyz" 
//		之类的新的变量都会引用常量池的xyz
//		所以是生成两个对象
	}
	
//	@Test
//	public void testA(){
//		Map<A, Object> map=new HashMap<A ,Object>();
//		map.put(new A(), new Object());
//		map.put(new A(), new Object());
//		System.out.println(map.size());
//	}
//	@Test//数组拷贝
	public void testCopy(){
//		int[] a={1,2,3,4};
//		int[] b=new int[10];
//		System.arraycopy(a, 0, b, 0, 4);
//		for (int  i = 0;  i < b.length;  i++) {
//			System.out.println(b[i]);
//		}
		String [] a1={"1","2","3","4"};
		String b1="2";
		int size=a1.length;
		int s=size;
		for (int i = 0; i < a1.length; i++) {            
	           if (b1.equals(a1[i])) {                
	               System.arraycopy(a1, i + 1, a1, i, size - i - 1);                
	               size = s;   
	               a1[--size] = null;
	           }        
	    } 
		for (int i = 0; i < a1.length; i++) {
			System.out.println(a1[i]);
		}
		String cc="123"+true+"sdf";
		System.out.println(cc);
//		List<String> ls=new ArrayList<String>();
//		ls.remove(0);
	}
	
	
}
