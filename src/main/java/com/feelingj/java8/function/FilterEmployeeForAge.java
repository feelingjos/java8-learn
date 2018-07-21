package com.feelingj.java8.function;

import com.feelingj.java8.model.Employee;

public class FilterEmployeeForAge implements MyPredicate<Employee>{

	@Override
	public boolean test(Employee t) {
		return t.getAge() <= 35;
	}

}
