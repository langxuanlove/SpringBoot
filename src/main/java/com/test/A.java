package com.test;

/**
 * 
 * @author Jikey
 * @version 
 * @className: A <br/>
 * @date: 2016-11-4 上午9:13:10 <br/>
 * @since JDK 1.7
 * 
 * ​
 * 1、如果两个对象equals，Java运行时环境会认为他们的hashcode一定相等。 
 * 2、如果两个对象不equals，他们的hashcode有可能相等。 
 * 3、如果两个对象hashcode相等，他们不一定equals。 
 * 4、如果两个对象hashcode不相等，他们一定不equals
 * 
 * http://blog.csdn.net/chy800/article/details/7239838
 */

public class A {  
  
    @Override  
    public boolean equals(Object obj) {  
        System.out.println("判断equals");  
        return false;  
    }  
  
    @Override  
    public int hashCode() {  
        System.out.println("判断hashcode");  
        return 1;  
    }  
    public static void main(String[] args) {
		System.out.println(A.class.getResource("/").getPath()+"/");
	}
}  
