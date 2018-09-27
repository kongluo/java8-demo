package com.kongluo.lambda;

import com.kongluo.object.Employee;

/**
 * @program: java8-demo
 * @description:
 * @author: Mr.Wang
 * @create: 2018-09-27 15:21
 **/
public class FilterEmployeeBySalary implements MyPredicate<Employee>{

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary()>=5000;
    }
}
