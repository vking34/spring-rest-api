package com.dkt.controllers;


import com.dkt.models.Department;
import com.dkt.models.Departments;
import com.dkt.models.Employee;
import com.dkt.models.Employees;
import org.apache.commons.logging.Log;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rest-api/departments")

//public class DepartmentsController {
//
//    Departments departments = Departments.getInstance();
//    Employees employees = Employees.getInstance();
//
//    // get departments list
//    @GetMapping
//    public List<Department> getDepartmentsList(){
//        return departments.getList();
//    }
//
//    // create a new department
//    @PostMapping
//    public boolean createDepartment(@RequestBody Map<String, String> department){
//        int id = Integer.parseInt(department.get("id"));
//        String title = department.get("title");
//        String subTitle = department.get("subTitle");
//        return departments.createDepartment(id, title, subTitle);
//    }
//
//    // edit an existing department
//    @PutMapping
//    public boolean editDepartment(@RequestBody Map<String, String> department){
//        int id = Integer.parseInt(department.get("id"));
//        String title = department.get("title");
//        String subTitle = department.get("subTitle");
//        return departments.updateDepartment(id, title, subTitle);
//    }
//
//    // delete department by id
//    @DeleteMapping("/{id}")
//    public boolean deleteDepartment(@PathVariable int id){
//        return departments.deleteDepartmentById(id);
//    }
//
//    // get department by id
//    @GetMapping("/{id}")
//    public Department getDepartment(@PathVariable int id){
//        return departments.getDepartmentById(id);
//    }
//
//    // add employee to department
//    @PostMapping("/{departmentID}/addEmployee")
//    public boolean addEmployee(@PathVariable("departmentID") int departmentID, @RequestParam(value = "id") int employeeID){
//        return departments.getDepartmentById(departmentID).addEmployee(employees.getEmployeeById(employeeID));
//    }
//
//    // remove employee to department
//    @PutMapping("/{departmentID}/removeEmployee")
//    public boolean removeEmployee(@PathVariable int departmentID, @RequestParam(value = "id") int employeeID){
//        return departments.getDepartmentById(departmentID).removeEmployee(employeeID);
//    }
//
//}

@EnableHypermediaSupport(type = HypermediaType.HAL)
public class DepartmentsController {

//    @Autowired
//    private DepartmentsController departmentsController;

    Departments departments = Departments.getInstance();
    Employees employees = Employees.getInstance();

    // get departments list
    @RequestMapping(method = RequestMethod.GET, produces = {"application/hal+json"})
    public Resources<Department> getDepartmentsList(){
        List<Department> departmentsList = departments.getList();
        for(Department department : departmentsList){
            department.removeLinks();

            Link selfLink = linkTo(DepartmentsController.class).slash(department.getDepartmentID()).withSelfRel(); ;
            department.add(selfLink);
        }

        Link link = linkTo(DepartmentsController.class).withSelfRel();
        Resources<Department> result = new Resources<>(departmentsList, link);
        return result;
    }

    // create a new department
    @PostMapping
    public boolean createDepartment(@RequestBody Map<String, String> department){
        int id = Integer.parseInt(department.get("id"));
        String title = department.get("title");
        String subTitle = department.get("subTitle");
        return departments.createDepartment(id, title, subTitle);
    }

    // edit an existing department
    @PutMapping("/{id}")
    public boolean editDepartment(@PathVariable int id ,@RequestBody Map<String, String> department){
//        int id = Integer.parseInt(department.get("id"));
        String title = department.get("title");
        String subTitle = department.get("subTitle");
        return departments.updateDepartment(id, title, subTitle);
    }

    // delete department by id
    @DeleteMapping("/{id}")
    public boolean deleteDepartment(@PathVariable int id){
        return departments.deleteDepartmentById(id);
    }

    // get department by id
    @GetMapping(value = "/{id}", produces = {"application/hal+json"})
    public Resource<Department> getDepartment(@PathVariable int id){
        Department department = departments.getDepartmentById(id);

        for(Employee e : department.getEmployees()){
            e.removeLinks();
            Link eLink = linkTo(EmployeesController.class).slash(e.getEmployeeID()).withSelfRel();
            e.add(eLink);
        }

        Link selfLink = department.getLink("self");
        if(selfLink == null){
            selfLink = linkTo(DepartmentsController.class).slash(id).withSelfRel();
            department.add(selfLink);
        }

        Link all = department.getLink("allDepartments");
        if(all == null){
            System.out.println("add all departments link");
             all = linkTo(DepartmentsController.class).withRel("allDepartments");
            department.add(all);
        }

        return new Resource<Department>(department, selfLink, all);
    }

    // add employee to department
    @PostMapping("/{departmentID}/addEmployee/{employeeID}")
    public boolean addEmployee(@PathVariable("departmentID") int departmentID, @PathVariable("employeeID") int employeeID){
        return departments.getDepartmentById(departmentID).addEmployee(employees.getEmployeeById(employeeID));
    }

    // remove employee to department
    @PutMapping("/{departmentID}/removeEmployee/{employeeID}")
    public boolean removeEmployee(@PathVariable int departmentID, @PathVariable("employeeID") int employeeID){
        return departments.getDepartmentById(departmentID).removeEmployee(employeeID);
    }

    // remove employee to department
//    @PutMapping("/{departmentID}/removeEmployee")
//    public boolean removeEmployee(@PathVariable int departmentID, @RequestParam(value = "id") int employeeID){
//        return departments.getDepartmentById(departmentID).removeEmployee(employeeID);
//    }
}
