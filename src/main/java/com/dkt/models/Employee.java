package com.dkt.models;

import com.dkt.passingObjects.resp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Employee")

public class Employee extends ResourceSupport {

    @Id
    private String _id;

    @Field("id")
//    @Indexed(unique = true)
    private int employeeID;

    @Field("name")
    private String name;

    @Field("age")
    private int age;

    @Field("salary")
    private int salary;

    @Field("departments")
    List<String> departments;

    public Employee(){}

    public Employee(String _id, int employeeID, String name, int age, int salary) {
        this._id = _id;
        this.employeeID = employeeID;
        this.name = name;
        this.age = age;
        this.salary = salary;
        departments = new ArrayList<String>();
    }

    public Employee(int employeeID, String name, int age, int salary) {
        this.employeeID = employeeID;
        this.name = name;
        this.age = age;
        this.salary = salary;
        departments = new ArrayList<String>();
    }

    public Employee(String _id, int employeeID, String name, int age, int salary, List<String> departments) {
        this._id = _id;
        this.employeeID = employeeID;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.departments = departments;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    public boolean addDepartment(String newID){
        for(String id : departments){
            if(id.equals(newID))
            {
                return false;
            }
        }
        departments.add(newID);
        return true;
    }

    public boolean removeDepartmentById(String id){
        for(int i = 0; i < departments.size(); i++){
            if(departments.get(i).equals(id)){
                departments.remove(i);
                return true;
            }
        }
        return false;
    }

}
