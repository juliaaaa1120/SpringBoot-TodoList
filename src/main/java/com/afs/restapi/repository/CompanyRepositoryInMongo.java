package com.afs.restapi.repository;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepositoryInMongo extends MongoRepository<Company, String> {
    Optional<Company> findById(String id);

    List<Company> findByGender(String gender);

    void clearAll();
}
