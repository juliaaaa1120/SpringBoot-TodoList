package com.afs.restapi.service;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.CompanyRepository;
import com.afs.restapi.repository.CompanyRepositoryInMongo;
import com.afs.restapi.repository.EmployeeRepository;
import com.afs.restapi.repository.EmployeeRepositoryInMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyRepositoryInMongo companyRepositoryInMongo;
    private final EmployeeRepositoryInMongo employeeRepositoryInMongo;

    @Autowired
    private final EmployeeService employeeService;

    public CompanyService(CompanyRepository companyRepository, EmployeeService employeeService, CompanyRepositoryInMongo companyRepositoryInMongo, EmployeeRepositoryInMongo employeeRepositoryInMongo) {
        this.companyRepository = companyRepository;
        this.employeeService = employeeService;
        this.companyRepositoryInMongo = companyRepositoryInMongo;
        this.employeeRepositoryInMongo = employeeRepositoryInMongo;
    }

    public List<Company> findAll() {
        List<Company> companies = companyRepositoryInMongo.findAll();
//        companies.forEach(company -> company.setEmployees(findAllEmployeesByCompanyId(company.getId())));
        return companies;
    }

    public Company edit(String id, Company updatedCompany) {
        Company company = companyRepository.findById(id);
        if (updatedCompany.getCompanyName() != null) {
            company.setCompanyName(updatedCompany.getCompanyName());
        }
//        company.setEmployees(findAllEmployeesByCompanyId(company.getId()));
        return companyRepository.save(id, company);
    }

    public Company findById(String id) {
        Company company = companyRepository.findById(id);
//        company.setEmployees(findAllEmployeesByCompanyId(company.getId()));
        return company;
    }

    public List<Employee> findAllEmployeesByCompanyId(String id) {
        return employeeService.getEmployeesByCompanyId(id);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        List<Company> companies = companyRepository.findByPage(page, pageSize);
//        companies.forEach(company -> company.setEmployees(findAllEmployeesByCompanyId(company.getId())));
        return companies;
    }

    public Company create(Company company) {
        return companyRepository.create(company);
    }

    public void remove(String id) {
        companyRepository.remove(id);
    }
}
