package com.afs.restapi.service;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.exception.NoCompanyFoundException;
import com.afs.restapi.repository.CompanyRepository;
import com.afs.restapi.repository.CompanyRepositoryInMongo;
import com.afs.restapi.repository.EmployeeRepositoryInMongo;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyRepositoryInMongo companyRepositoryInMongo;
    private final EmployeeRepositoryInMongo employeeRepositoryInMongo;

    public CompanyService(CompanyRepository companyRepository, CompanyRepositoryInMongo companyRepositoryInMongo, EmployeeRepositoryInMongo employeeRepositoryInMongo) {
        this.companyRepository = companyRepository;
        this.companyRepositoryInMongo = companyRepositoryInMongo;
        this.employeeRepositoryInMongo = employeeRepositoryInMongo;
    }
    public List<Company> findAll() {
        List<Company> companies = companyRepositoryInMongo.findAll();
        for (Company company : companies) {
            if (findAllEmployeesByCompanyId(company.getId()).size() != 0) {
                company.setEmployees(findAllEmployeesByCompanyId(company.getId()));
            }
        }
        return companies;
    }

    public Company edit(String id, Company updatedCompany) {
        Company company = companyRepositoryInMongo.findById(id)
                .orElseThrow(NoCompanyFoundException::new);
        if (updatedCompany.getName() != null) {
            company.setName(updatedCompany.getName());
        }
        if (findAllEmployeesByCompanyId(company.getId()).size() != 0) {
            company.setEmployees(findAllEmployeesByCompanyId(company.getId()));
        }
        return companyRepositoryInMongo.save(company);
    }

    public Company findById(String id) {
        Company company = companyRepositoryInMongo.findById(id)
                .orElseThrow(NoCompanyFoundException::new);
        if (findAllEmployeesByCompanyId(company.getId()).size() != 0) {
            company.setEmployees(findAllEmployeesByCompanyId(company.getId()));
        }
        return company;
    }

    public List<Employee> findAllEmployeesByCompanyId(String id) {
        return employeeRepositoryInMongo.getEmployeesByCompanyId(id);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        List<Company> companies = companyRepositoryInMongo.findAll(PageRequest.of(page - 1, pageSize))
                .stream()
                .collect(Collectors.toList());
        for (Company company : companies) {
            if (findAllEmployeesByCompanyId(company.getId()).size() != 0) {
                company.setEmployees(findAllEmployeesByCompanyId(company.getId()));
            }
        }
        return companies;
    }

    public Company create(Company company) {
        return companyRepositoryInMongo.insert(company);
    }

    public void remove(String id) {
        companyRepositoryInMongo.deleteById(id);
    }

}
