package com.dkt.models;

import org.springframework.hateoas.ResourceSupport;

public class Employee extends ResourceSupport {

    private int employeeID;
    private String name;
    private int age;
    private int salary;

    public Employee(){}

    public Employee(int id, String name, int age, int salary) {
        this.employeeID = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getEmployeeID() {
        return employeeID;

    }

    public void setId(int id) {
        this.employeeID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
