package com.feelingj.java8.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.feelingj.java8.model.Employee;
import com.feelingj.java8.model.Employee.Status;

public class StreamGrammar {
	
	List<Employee> ems = Arrays.asList(
			new Employee(102, "李四", 59, 6666.66),
			new Employee(101, "张三", 18, 9999.99),
			new Employee(103, "王五", 28, 3333.33),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(105, "田七", 38, 5555.55)
	);
	
	List<Employee> empss = Arrays.asList(
			new Employee(102, "李四", 59, 6666.66, Status.BUSY),
			new Employee(101, "张三", 18, 9999.99, Status.FREE),
			new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
			new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(105, "田七", 38, 5555.55, Status.BUSY)
	);
	
	//1. 创建 Stream
	@Test
	public void test1(){
		//1. Collection 提供了两个方法  stream() 与 parallelStream()
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream(); //获取一个顺序流
		Stream<String> parallelStream = list.parallelStream(); //获取一个并行流
		
		//2. 通过 Arrays 中的 stream() 获取一个数组流
		Integer[] nums = new Integer[10];
		Stream<Integer> stream1 = Arrays.stream(nums);
		
		//3. 通过 Stream 类中静态方法 of()
		Stream<Integer> stream2 = Stream.of(1,2,3,4,5,6);
		
		//4. 创建无限流
		//迭代
		Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
		stream3.forEach(System.out::println);
		
		//生成
		Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
		stream4.forEach(System.out::println);
	}
	
	/*
	  筛选与切片
		filter——接收 Lambda ， 从流中排除某些元素。
		limit——截断流，使其元素不超过给定数量。
		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
		//内部迭代：迭代操作 Stream API 内部完成
	 */
	@Test
	public void test2(){
		//所有的中间操作不会做任何的处理
		Stream<Employee> stream = ems.stream()
			.filter((e) -> {
				System.out.println("中间操作");
				return e.getAge() <= 35;
			});
		
		ems.stream()
				.filter((e) -> e.getSalary() >5000)
				.limit(2)
				.forEach(System.out::println);
		
		ems.stream()
			.filter((e) -> e.getSalary() >5000)
			.skip(2)
			.distinct()
			.forEach(System.out::println);
		//只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
		stream.forEach(System.out::println);
	}
	
	//外部迭代
	@Test
	public void test3(){
		Iterator<Employee> it = ems.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	/**
	 * 映射
	 *  map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
	 *	flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	 */
	@Test
	public void test4() {
		List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
		list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
		ems.stream().map(Employee::getName).forEach(System.out::println);

        System.out.println("-------------------------------");
        
        Stream<Stream<Character>> stream = list.stream().map(StreamGrammar::filterCharacter);
        stream.forEach(sm->{
        	sm.forEach(System.out::println);
        });
        
        System.out.println("---------------------------------");
        
        Stream<Character> stream3 = list.stream()
 			   .flatMap(StreamGrammar::filterCharacter);
 		
 		stream3.forEach(System.out::println);
		
	}
	
	public static Stream<Character> filterCharacter(String str){
		List<Character> list = new ArrayList<>();
		
		for (Character ch : str.toCharArray()) {
			list.add(ch);
		}
		
		return list.stream();
	}
	
	/*
		sorted()——自然排序
		sorted(Comparator com)——定制排序
	 */
	@Test
	public void test5(){
		List<String> list=Arrays.asList("ccc","aaa","bbb","ddd","eee");
		list.stream().forEach(System.out::println);
		
		System.out.println("------------------------");
		
		ems.stream()
		      .sorted((x,y) -> {
		    	if(x.getAge() == y.getAge()){
					return x.getName().compareTo(y.getName());
				}else{
					return Integer.compare(x.getAge(), y.getAge());
				}
		      })
		      .forEach(System.out::println);
		
	}
	
	//3. 终止操作
	/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
	@Test
	public void test6(){
		boolean allMatch = empss.stream()
		    .allMatch((e) -> e.getStatus().equals(Status.BUSY));
		
		boolean anyMatch = empss.stream()
	    .anyMatch((e)->e.getStatus().equals(Status.BUSY));
		
		boolean noneMatch = empss.stream()
			    .noneMatch((e)->e.getStatus().equals(Status.BUSY));
		
		Optional<Employee> op = empss.stream()
				.sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
				.findFirst();
			
		System.out.println(op.get());
		
		Optional<Employee> findAny = empss.stream()
		       .filter((e) -> e.getStatus().equals(Status.FREE))
		       .findAny();
		
		System.out.println(findAny.get());
		
		long count = empss.stream().count();
		
		Optional<Employee> max = empss.stream()
		       .max(((x,y) -> Double.compare(x.getSalary(),y.getSalary())));
		System.out.println(max.get());
		
		Optional<Double> min = empss.stream()
		      .map(Employee::getSalary)
		      .min(Double::compare);
		
		System.out.println(min);
	}
	
	@Test
	public void test7() {
		
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		Integer sum = list.stream()
			.reduce(0, (x, y) -> x + y);
		
		System.out.println(sum);
		
        System.out.println("----------------------------------------");
		
		Optional<Double> op = empss.stream()
			.map(Employee::getSalary)
			.reduce(Double::sum);
		
		System.out.println(op.get());
		
	}
	
	/**
	 * 收集
	 * collect- 将流转换为其他形式。接收一个Collter接口的实现，用于给stream中的元素做汇总的方法
	 */
	@Test
	public void test8() {
		List<String> list = empss.stream()
		      .map(Employee::getName)
		      .collect(Collectors.toList());
		
		list.forEach(System.out::println);
		
		System.out.println("-----------------------");
		
		Set<String> set = empss.stream()
		      .map(Employee::getName)
		      .collect(Collectors.toSet());
		
		set.forEach(System.out::println);
		
		System.out.println("------------------------------");
		
		HashSet<String> hashSet = empss.stream()
		       .map(Employee::getName)
		       .collect(Collectors.toCollection(HashSet::new));
		
		hashSet.forEach(System.out::println);
		
		System.out.println("-------------------------------");
		
		//收集总数
		Long collect = empss.stream()
		       .collect(Collectors.counting());
		
		System.out.println("----------------------------------");
		
		//平均值
		Double collect2 = empss.stream()
		       .collect(Collectors.averagingDouble(Employee::getSalary));
		
		System.out.println("----------------------------------");
		
		//总和
		Double collect3 = empss.stream()
	       .collect(Collectors.summingDouble(Employee::getSalary));
		
		System.out.println("----------------------------------");
		
		//最大值
		Optional<Employee> collect4 = empss.stream()
	       .collect(Collectors.maxBy((x,y)->Double.compare(x.getSalary(), y.getSalary())));
		
		Optional<Double> collect5 = empss.stream()
		     .map(Employee::getSalary)
		     .collect(Collectors.minBy(Double::compare));
		
		
	}
	
    public void test9() {
    	Map<Status, List<Employee>> group = empss.stream()
    	      .collect(Collectors.groupingBy(Employee::getStatus));
    	
    	empss.stream()
	      .collect(Collectors.groupingBy(Employee::getStatus,Collectors.groupingBy(Employee::getSalary)));
        
    	//分区
    	Map<Boolean, List<Employee>> collect = empss.stream()
	      .collect(Collectors.partitioningBy((e)->e.getSalary()>8000));
    	
    	
    	DoubleSummaryStatistics collect2 = empss.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
    	System.out.println(collect2.getAverage());
    	System.out.println(collect2.getCount());
    	System.out.println(collect2.getMin());
    	System.out.println(collect2.getSum());
    	
    	System.out.println("------------------");
    	
    	String collect3 = empss.stream().map(Employee::getName).collect(Collectors.joining());
    	
	
    }
	
	
	
	

}
