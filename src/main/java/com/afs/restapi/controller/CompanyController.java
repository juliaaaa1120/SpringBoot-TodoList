package com.afs.restapi.controller;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.service.CompanyService;
import com.afs.restapi.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable String id) {
        return companyService.findById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getAllEmployeesByCompanyId(@PathVariable String id) {
        return companyService.findAllEmployeesByCompanyId(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompaniesByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return companyService.findByPage(page, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company createCompany(@RequestBody Company company) {
        return companyService.create(company);
    }

    @PutMapping("/{id}")
    public Company editCompany(@PathVariable String id, @RequestBody Company updatedCompany) {
        return companyService.edit(id, updatedCompany);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Company deleteCompany(@PathVariable String id) {
        return companyService.remove(id);
    }
}
