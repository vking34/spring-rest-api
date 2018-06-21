package com.dkt;

import com.dkt.models.Employee;
import com.dkt.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class mongoDBSeeder implements CommandLineRunner {

    private EmployeeRepository employeeRepository;

    public mongoDBSeeder(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Employee employee1 = new Employee(2234, "dung", 21, 2400);
        Employee employee2 = new Employee(2346, "thoa", 21, 3000);
        Employee employee3 = new Employee(5323, "hung", 21, 6000);

        this.employeeRepository.deleteAll();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);


    }
}
