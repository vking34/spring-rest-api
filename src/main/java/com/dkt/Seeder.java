package com.dkt;

import com.dkt.models.Department;
import com.dkt.repositories.Department.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
//public class Seeder implements CommandLineRunner {
//
//    private DepartmentRepository departmentRepository;
//
//    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
//        this.departmentRepository = departmentRepository;
//    }
//
//    @Override
//    public void run(String... strings) throws Exception {
//
////        Employee employee1 = new Employee(3453, "liy", 21, 2400);
////        Employee employee2 = new Employee(8795, "quy", 21, 3000);
////        Employee employee3 = new Employee(3423, "tran", 21, 6000);
//
//        Department department1 = new Department(23, "Sale", "marketing");
//        Department department2 = new Department(34, "IT", "computer");
//
////        department1.addEmployee(employee1);
////        department2.addEmployee(employee2);
////
////        departmentRepository.save(department1);
////        departmentRepository.save(department2);
////
//
//    }
//}