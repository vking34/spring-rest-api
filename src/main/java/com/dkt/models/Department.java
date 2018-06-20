package com.dkt.models;

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

public class Department extends ResourceSupport {

    private int departmentID;
    private String title;
    private String subTitle;
    private List<Employee> employees;

    public Department(){
    }

    public Department(int id, String title, String subTitle){
        this.departmentID = id;
        this.title = title;
        this.subTitle = subTitle;
        employees = new ArrayList<Employee>();
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setId(int id) {
        this.departmentID = id;
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

    public List<Employee> getEmployees()
    {
        return employees;
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