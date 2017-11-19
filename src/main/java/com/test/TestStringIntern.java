package com.test;

import org.junit.Test;

public class TestStringIntern {
	
	@Test
	public void demo(){
		// 此地方执行了两个操作，一个是生产字符串常量1放入常量池中，一个是创建了字符串对象.
		String s = new String("1");  
		// 此句无效,因为常量池中已经生成了1字符串
	    s.intern();
	    // 引用的是常量池中的字符串1
	    String s2 = "1";  
	    // s是堆中的字符串对象 !=常量池中的1
	    // false
	    System.out.println(s == s2);  
	   
	    // 常量池中生成2字符串,同时生成22字符串对象
	    String s3 = new String("2") + new String("2");
	    // 执行有效将22放入常量池中,常量池记录22字符串对象的地址的引用
	    s3.intern();  
	    // 引用的是常量池中的地址引用，也就是s3指的堆中的对象
	    String s4 = "22";  
	    // s3指的和s4指的堆中的对象是同一个,所以相等
	    // true
	    System.out.println(s3 == s4);  
	    
	    // 生成33字符串常量放入常量池中.
	    String s5="33";//也可以写成s5="3"+"3";(这种写法3字符串是不入池的).
	    // 创建3字符串常量放入常量池中，同时创建33字符串对象在堆上
	    String s31 = new String("3") + new String("3");  
	    // 因为上面已经是有11字符串了,所以下面这句返回的是实际是上面s5一样的地址。可以说是废弃的
	    s3.intern();  
	    // 使用的是串常量池中的字符串33，也就是和s5指向的字符串对象是同一个，因此s41和s31是不一样的.
	    String s41 = "33";  
	    // false
	    System.out.println(s31 == s41); 
	    
	    // 常量池中生成bbb字符串,同时生成bbbbbb字符串对象
	    String a=new String("bbb")+"bbb";
	    // 将bbbbbb字符串对象放入常量池中,执行有效
	    a.intern();
	    // 实际引用的是常量池中存放的a字符串对象实例的引用地址，和a指向的堆上的字符串对象是同一个。
	    String b="bbbbbb";
	    // true
	    System.out.println(a==b);
	    
	}
	
//	@Test
	public  void demo1() {
		
		// jdk1.7中常量池放在了堆中,
//		String a="计算机软件";
		/**
		 * 分析：因为计算机软件五个字直接使用了双引号生命，故JVM会在运行时
		 * 常量池中首先查找有没有改字符串，有则直接返回该字符串在常量池中的
		 * 引用，没有则直接在常量池中创建该字符串，然后返回引用，此时，该句
		 * 代码已经执行完毕，不会再java heap （堆）中创建内容相同的字符串
		 * 该字符串只在常连池中创建了一个String对象。
		 * 
		 * When the intern method is invoked, if the pool already contains a
	     * string equal to this <code>String</code> object as determined by
	     * the {@link #equals(Object)} method, then the string from the pool is
	     * returned. Otherwise, this <code>String</code> object is added to the
	     * pool and a reference to this <code>String</code> object is returned.
	     * <p>
		 * 
		 * 此方法被调用时,如果池中包含此String对象,（通过equals方法比较包含）,则返回池中的对象，
		 * 否则将此String对象添加到池中并且返回一个此String对象的引用。（就是说如果池中有字符串对象，
		 * 不管池中存放的是引用还是字符串实例，都返回池中的，如果没有就把当前的字符串实例加入池中，同时返回
		 * 此字符串的引用。）
		 * */
		 String b1=new StringBuffer().append("计算机").append("软件").toString();
		 System.out.println(b1.intern()==b1);
		 String b2=new StringBuffer().append("ja").append("va").toString();
		 System.out.println(b2.intern()==b2);
		 
//		 String b="计算机软件";
//		 System.out.println(a+"/"+b);
	}
}
