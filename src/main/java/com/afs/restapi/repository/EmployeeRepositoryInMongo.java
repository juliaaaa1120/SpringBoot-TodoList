package com.afs.restapi.repository;

import com.afs.restapi.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepositoryInMongo extends MongoRepository<Employee, String> {
    Optional<Employee> findById(String id);

    List<Employee> findByGender(String gender);
}
