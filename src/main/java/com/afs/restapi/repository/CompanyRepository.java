package com.afs.restapi.repository;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.exception.NoCompanyFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    @Autowired
    private EmployeeRepository employeeRepository;

    public CompanyRepository() {
        companies.add(new Company("1", "OOCL"));
        companies.add(new Company("2", "DHL"));
        companies.add(new Company("3", "SF Express"));
    }


    public List<Company> findAll() {
        return companies;
    }

    public Company findById(String id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(NoCompanyFoundException::new);
    }

    public List<Employee> findAllEmployeesByCompanyId(String id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .map(Company::getEmployees)
                .orElseThrow(NoCompanyFoundException::new);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companies.stream()
                .skip((long)(page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Company create(Company company) {
        Integer nextId = companies.stream()
                .mapToInt(existingCompany -> Integer.parseInt(existingCompany.getId()))
                .max()
                .orElse(0) + 1;
        company.setId(nextId.toString());
        companies.add(company);
        return company;
    }

    public Company save(String id, Company updatedCompany) {
        Company company = findById(id);
        companies.remove(company);
        companies.add(updatedCompany);
        return updatedCompany;
    }

    public void remove(String id) {
        Company company = findById(id);
        companies.remove(company);
    }

    public void clearAll() {
        companies.clear();
    }

}
