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
                .map(company -> findById(company.getId()))
                .collect(Collectors.toList());
        return companies;
    }

    public Company edit(Integer id, Company updatedCompany) {
        Company company = findById(id);
        if (updatedCompany.getCompanyName() != null) {
            company.setCompanyName(updatedCompany.getCompanyName());
        }
        return companyRepository.save(id, company);
    }

    public Company findById(Integer id) {
        updateEmployees(id);
        return companyRepository.findById(id);
    }

    public List<Employee> findAllEmployeesByCompanyId(Integer id) {
        updateEmployees(id);
        return companyRepository.findAllEmployeesByCompanyId(id);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        findAll();
        return companyRepository.findByPage(page, pageSize);
    }

    public Company create(Company company) {
        Company updatedCompany = companyRepository.create(company);
        updateEmployees(updatedCompany.getId());
        return updatedCompany;
    }

    public Company remove(Integer id) {
        updateEmployees(id);
        return companyRepository.remove(id);
    }

    public void updateEmployees(Integer companyId) {
        Company company = companyRepository.findById(companyId);
        List<Employee> employees = employeeService.getEmployeesByCompanyId(companyId);
        companyRepository.updateEmployees(companyId, employees);
    }
}
