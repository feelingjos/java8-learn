package com.feelingj.java8.annotion;

import java.lang.reflect.Method;

import org.junit.Test;

public class Myanno {
	
	@MyAnnotion("hello")
	@MyAnnotion("world")
	public void show() {
		
	}
	
	@Test
	public void test1() throws NoSuchMethodException, SecurityException {
		Method method = Myanno.class.getMethod("show");
		MyAnnotion[] annotationsByType = method.getAnnotationsByType(MyAnnotion.class);
		
		for (MyAnnotion myAnnotion : annotationsByType) {
			System.out.println(myAnnotion.value());
		}
		
	}

}
