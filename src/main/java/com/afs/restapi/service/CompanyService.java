package com.afs.restapi.service;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.CompanyRepository;
import com.afs.restapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    private EmployeeService employeeService;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        List<Company> companies = companyRepository.findAll().stream()
                .map(company -> updateEmployees(company.getId()))
                .collect(Collectors.toList());
        return companies;
    }

    public Company edit(Integer id, Company updatedCompany) {
        Company company = companyRepository.findById(id);
        if (updatedCompany.getCompanyName() != null) {
            company.setCompanyName(updatedCompany.getCompanyName());
        }
        if (updatedCompany.getEmployees() != null) {
            company.setEmployees(updatedCompany.getEmployees());
        }
        return companyRepository.save(id, company);
    }

    public Company findById(Integer id) {
        return updateEmployees(id);
    }

    public List<Employee> findAllEmployeesByCompanyId(Integer id) {
        return companyRepository.findAllEmployeesByCompanyId(id);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companyRepository.findByPage(page, pageSize);
    }

    public Company create(Company company) {
        return companyRepository.create(company);
    }

    public Company remove(Integer id) {
        return companyRepository.remove(id);
    }

    public Company updateEmployees(Integer companyId) {
        Company company = companyRepository.findById(companyId);
        List<Employee> employees = employeeService.getEmployeesByCompanyId(companyId);
        companyRepository.updateEmployees(companyId, employees);
        return company;
    }
}
