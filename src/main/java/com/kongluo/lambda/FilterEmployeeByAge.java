package com.kongluo.lambda;

import com.kongluo.object.Employee;

/**
 * @program: java8-demo
 * @description:
 * @author: Mr.Wang
 * @create: 2018-09-27 15:15
 **/
public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge()>=35;
    }
}
