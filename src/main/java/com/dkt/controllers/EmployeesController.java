package com.dkt.controllers;


import com.dkt.models.Employee;
import com.dkt.models.Employees;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rest-api/employees")

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)

public class EmployeesController {

    Employees list = Employees.getInstance();

//    @GetMapping
//    public List<Employee> getEmployeesList(){
//        return list.getList();
//    }

    @GetMapping(produces = {"application/hal+json"})
    public Resources<Employee> getEmployeeList(){
        List<Employee> employeeList = list.getList();
        for(Employee e : employeeList){
            if(!e.hasLinks())
            {
                Link selfLink = linkTo(EmployeesController.class).slash(e.getEmployeeID()).withSelfRel();
                e.add(selfLink);
            }
        }
        Link employeesLink = linkTo(EmployeesController.class).withSelfRel();
        Resources<Employee> result = new Resources<Employee>(employeeList, employeesLink);
        return result;
    }

    @PostMapping
    public boolean addEmployee(@RequestBody Map<String, String> employee){
        int id = Integer.parseInt(employee.get("id"));
        String name = employee.get("name");
        int age = Integer.parseInt(employee.get("age"));
        int salary = Integer.parseInt(employee.get("salary"));
        return list.addEmployee(id, name, age, salary);
    }

    @PutMapping
    public boolean updateEmployee(@RequestBody Map<String, String> employee){
        int id = Integer.parseInt(employee.get("id"));
        String name = employee.get("name");
        int age = Integer.parseInt(employee.get("age"));
        int salary = Integer.parseInt(employee.get("salary"));
        return list.updateEmployee(id, name, age, salary);
    }

    @DeleteMapping("/{ID}")
    public boolean deleteEmployee(@PathVariable int ID){
        return list.deleteEmployeeById(ID);
    }

//    @GetMapping("/{id}")
//    public Employee getEmployee(@PathVariable int id){
//        return list.getEmployeeById(id);
//    }

    @GetMapping(value = "/{id}", produces = {"application/hal+json"})
    public Resource<Employee> getEmployee(@PathVariable int id){
        Employee employee = list.getEmployeeById(id);

        Link selfLink = employee.getLink("self");
        if(selfLink == null){
            selfLink = linkTo(EmployeesController.class).slash(id).withSelfRel();
            employee.add(selfLink);
        }

        Link all = employee.getLink("allEmployees");
        if(all == null)
        {
            all = linkTo(EmployeesController.class).withRel("allEmployees");
            employee.add(all);
        }

        return new Resource<Employee>(employee, selfLink, all);
    }
}
