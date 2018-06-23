package com.dkt.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

//public class Department {
//
//    private int id;
//    private String title;
//    private String subTitle;
//    private List<Employee> employees;
//
//    public Department(){
//    }
//
//    public Department(int id, String title, String subTitle){
//        this.id = id;
//        this.title = title;
//        this.subTitle = subTitle;
//        employees = new ArrayList<Employee>();
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getSubTitle() {
//        return subTitle;
//    }
//
//    public void setSubTitle(String subTitle) {
//        this.subTitle = subTitle;
//    }
//
//    public List<Employee> getEmployees()
//    {
//        return employees;
//    }
//
//    public boolean addEmployee(Employee newEmployee){
//        for(Employee e: employees){
//            if(e.getId() == newEmployee.getId()){
//                return false;
//            }
//        }
//        employees.add(newEmployee);
//        return true;
//    }
//
//    public boolean removeEmployee(int id){
//        for(int i = 0; i < employees.size(); i++){
//            if(employees.get(i).getId() == id){
//                employees.remove(i);
//                return true;
//            }
//        }
//        return false;
//    }
//}

@Document(collection = "department")

public class Department extends ResourceSupport {

    @Id
    private String _id;

    @Field("id")
    private int departmentID;

    @Field("title")
    private String title;

    @Field("subTitle")
    private String subTitle;

    @Field("employees")
    private List<Employee> employees;

    public Department(){
    }

    public Department(int departmentID, String title, String subTitle) {
        this.departmentID = departmentID;
        this.title = title;
        this.subTitle = subTitle;
        employees = new ArrayList<Employee>();
    }

    public Department(String _id, int departmentID, String title, String subTitle, List<Employee> employees) {
        this._id = _id;
        this.departmentID = departmentID;
        this.title = title;
        this.subTitle = subTitle;
        this.employees = employees;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public boolean addEmployee(Employee newEmployee){
        for(Employee e: employees){
            if(e.getId() == newEmployee.getId()){
                return false;
            }
        }
        employees.add(newEmployee);
        return true;
    }

    public boolean removeEmployee(int id){
        for(int i = 0; i < employees.size(); i++){
            if(employees.get(i).getEmployeeID() == id){
                employees.remove(i);
                return true;
            }
        }
        return false;
    }
}