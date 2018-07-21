package com.feelingj.java8.function;

@FunctionalInterface
public interface MyPredicate<T> {
	
	public boolean test(T t);

}
