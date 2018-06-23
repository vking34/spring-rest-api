package com.dkt.controllers;


import com.dkt.models.Department;
import com.dkt.models.Departments;
import com.dkt.models.Employee;
import com.dkt.models.Employees;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.Department.DepartmentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.dkt.repositories.Employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rest-api/departments")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class DepartmentsController {

//    @Autowired
//    private DepartmentsController departmentsController;

//    Departments departments = Departments.getInstance();
//    Employees employees = Employees.getInstance();

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // get departments list
    @RequestMapping(method = RequestMethod.GET, produces = {"application/hal+json"})
    public Resources<Department> getDepartmentsList(){
//        List<Department> departmentsList = departments.getList();
        System.out.println("GET all departments...");
        List<Department> departmentsList = departmentRepository.findAll();

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
//        return departments.createDepartment(id, title, subTitle);
        if(departmentRepository.findDepartmentByDepartmentID(id) == null)
        {
            try{
                departmentRepository.insert(new Department(id, title, subTitle));
                return true;
            }catch (Exception e)
            {
                return false;
            }
        }else
            return false;

    }

    // edit an existing department
    @PutMapping("/{id}")
    public boolean editDepartment(@PathVariable int id ,@RequestBody Map<String, String> newInfo){
//        int id = Integer.parseInt(department.get("id"));
        String title = newInfo.get("title");
        String subTitle = newInfo.get("subTitle");
        Department department = departmentRepository.findDepartmentByDepartmentID(id);
        if(department != null)
        {
            department.setTitle(title);
            department.setSubTitle(subTitle);
            departmentRepository.save(department);
            return true;
        }
        else
            return false;

    }

    // delete department by id
    @DeleteMapping("/{id}")
    public boolean deleteDepartment(@PathVariable int id){
//        return departments.deleteDepartmentById(id);
        departmentRepository.deleteByDepartmentID(id);
        return true;
    }

    // get department by id
    @GetMapping(value = "/{id}", produces = {"application/hal+json"})
    public Resource<Department> getDepartment(@PathVariable int id){
//        Department department = departments.getDepartmentById(id);
        Department department = departmentRepository.findDepartmentByDepartmentID(id);
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

    // add Employee to department
    @PostMapping("/{departmentID}/addEmployee/{employeeID}")
    public resp addEmployeeToDepartment(@PathVariable("departmentID") int departmentID, @PathVariable("employeeID") int employeeID){
        Employee employee = employeeRepository.findByEmployeeID(employeeID);
        Department department = departmentRepository.findDepartmentByDepartmentID(departmentID);
        if(employee != null && department != null)
        {
            department.addEmployee(employee);
            System.out.println(department.getTitle() + " : " + employee.getName());
            employee.addDepartment(department.get_id());
            employeeRepository.save(employee);
            departmentRepository.save(department);
            return new resp(true);
        }
        else
            return new resp(false);
    }

    // remove Employee to department
    @DeleteMapping("/{departmentID}/removeEmployee/{employeeID}")
    public resp removeEmployee(@PathVariable int departmentID, @PathVariable("employeeID") int employeeID){
        Employee employee = employeeRepository.findByEmployeeID(employeeID);
        Department department = departmentRepository.findDepartmentByDepartmentID(departmentID);
        if(employee != null && department != null)
        {
            department.removeEmployee(employeeID);
            employee.removeDepartmentById(department.get_id());
            employeeRepository.save(employee);
            departmentRepository.save(department);
            return new resp(true);
        }
        else
            return new resp(false);
    }
}
