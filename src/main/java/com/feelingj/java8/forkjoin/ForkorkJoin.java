package com.feelingj.java8.forkjoin;

import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.Test;

public class ForkorkJoin {
	
	@Test
	public void test1() {
		long start = System.currentTimeMillis();
		
		ForkJoinPool pool=new ForkJoinPool();
		ForkJoinTask<Long> task=new ForkJoinCalculate(0,100000000L);
		Long sum=pool.invoke(task);
		long end = System.currentTimeMillis();
		
		
		System.out.println(sum);
		System.out.println("耗费的时间为: " + (end - start));
	}
	
	@Test
	public void test2() {
        long start = System.currentTimeMillis();
		
		long sum = 0L;
		
		for (long i = 0L; i <= 50000000000L; i++) {
			sum += i;
		}
		
		System.out.println(sum);
		
		long end = System.currentTimeMillis();
		
		System.out.println("耗费的时间为: " + (end - start));
	}
	
	@Test
	public void test4() {
		long start = System.currentTimeMillis();
		
		Long sum = LongStream.rangeClosed(0L, 10000000000L)
							 .parallel()
							 .sum();
		
		System.out.println(sum);
		
		long end = System.currentTimeMillis();
		
		System.out.println("耗费的时间为: " + (end - start)); //2061-2053-2086-18926
	}

}
