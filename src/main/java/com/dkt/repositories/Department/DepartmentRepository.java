package com.dkt.repositories.Department;

import com.dkt.models.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {
    public void deleteByDepartmentID(int id);
    public Department findDepartmentByDepartmentID(int id);
}
