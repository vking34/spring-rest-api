package com.dkt;

import com.dkt.models.Department;
import com.dkt.models.Employee;
import com.dkt.repositories.Department.DepartmentRepository;
import com.dkt.repositories.Employee.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class mongoDBSeeder implements CommandLineRunner {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public mongoDBSeeder(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        employeeRepository.deleteAll();
        departmentRepository.deleteAll();

        Employee employee1 = new Employee(2234, "dung", 21, 2400);
        Employee employee2 = new Employee(2346, "thoa", 21, 3000);
        Employee employee3 = new Employee(5323, "hung", 21, 6000);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        employee1 = employeeRepository.findByEmployeeID(2234);

        Department department1 = new Department(23, "Sale", "marketing");
        Department department2 = new Department(34, "IT", "computer");
        department2.addEmployee(employee1);

        departmentRepository.insert(department1);
        departmentRepository.insert(department2);

        department2 = departmentRepository.findDepartmentByDepartmentID(34);
        employee1.addDepartment(department2.get_id());
        employeeRepository.save(employee1);


        System.out.println("ok");

    }
}
