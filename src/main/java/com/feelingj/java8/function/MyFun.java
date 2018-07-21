package com.feelingj.java8.function;

@FunctionalInterface
public interface MyFun {

	public Integer getValue(Integer num);
	
	//Object方法，不影响函数式接口使用
	boolean equals(Object obj);
	
	//Object方法，不影响函数式接口使用
	int hashCode();
	
}
