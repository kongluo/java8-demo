package com.kongluo.lambda;

import com.kongluo.object.Employee;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @program: java8-demo
 * @description:
 * @author: Mr.Wang
 * @create: 2018-09-27 14:30
 **/
public class TestLambda
{
    /* 原来的匿名内部类 */
    @Test
    public void test1(){
        Comparator<Integer> com = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer>  ts = new TreeSet<>(com);
    }

    //Lambda 表达式
    public void test2(){
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,9999.99),
            new Employee("李四",38,9999.99),
            new Employee("王五",50,9999.99),
            new Employee("赵六",16,9999.99),
            new Employee("田七",8,9999.99)
    );
    //需求:获取当前公司中员工年龄大于35的员工信息
    public List<Employee> filterEmployee(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for (Employee emp:list){
            if (emp.getAge()>=35){
                emps.add(emp);
            }
        }
        return emps;
    }
    //需求:获取当前公司中员工工资大于5000的员工信息
    public List<Employee> filterEmployee2(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for (Employee emp:list){
            if (emp.getSalary()>=5000){
                emps.add(emp);
            }
        }
        return emps;
    }

    //优化方式一: 策略设计模式

    @Test
    public void test4(){
        List<Employee> list = filterEmployee(employees, new FilterEmployeeByAge());
        for (Employee employee:list){
            System.out.println(employee);
        }
        System.out.println("----------------------------");

        List<Employee> list1 = filterEmployee(employees, new FilterEmployeeBySalary());
        for (Employee employee:list1){
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployee(List<Employee> list,MyPredicate<Employee> mp){
        List<Employee> emps = new ArrayList<>();
        for (Employee employee:list){
            if (mp.test(employee)){
                emps.add(employee);
            }
        }
        return emps;
    }

    //优化方式二:匿名内部类
    @Test
    public void test5(){
        List<Employee> list = filterEmployee(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 5000;
            }
        });
        for (Employee employee:list){
            System.out.println(employee);
        }
    }

    //优化方式三:Lambda表达式
    @Test
    public void test6(){
        List<Employee> list = filterEmployee(this.employees, (e) -> e.getSalary() >= 5000);
        list.forEach(System.out::println);
    }

    //优化方式四：Stream API
    @Test
    public void test7(){
        employees.stream().filter((e)->e.getSalary()>=5000)
                .forEach(System.out::println);
        System.out.println("-----------------------");
        employees.stream().map(Employee::getName)
                .forEach(System.out::println);
    }
}
