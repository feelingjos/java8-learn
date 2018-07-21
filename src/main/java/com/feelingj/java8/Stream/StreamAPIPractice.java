package com.feelingj.java8.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.feelingj.java8.model.Employee;
import com.feelingj.java8.model.Employee.Status;

public class StreamAPIPractice {
	List<Employee> empss = Arrays.asList(
			new Employee(102, "李四", 59, 6666.66, Status.BUSY),
			new Employee(101, "张三", 18, 9999.99, Status.FREE),
			new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
			new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(105, "田七", 38, 5555.55, Status.BUSY)
	);
	
	/*
	  	1.	给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
		，给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】。
	 */
	@Test
	public void test1() {
		Integer[] nums=new Integer[]{1,2,3,4,5};
		Arrays.stream(nums).map((x)->x*x).forEach(System.out::println);
	}
	
	public void test2() {
		Optional<Integer> reduce = empss.stream().map(e->1).reduce(Integer::sum);
        System.out.println(reduce.get());
        
	}
	
	

}
