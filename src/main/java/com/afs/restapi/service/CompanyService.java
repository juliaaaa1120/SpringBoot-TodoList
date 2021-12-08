package com.afs.restapi.service;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.CompanyRepository;
import com.afs.restapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

//    public Employee edit(Integer id, Employee updatedEmployee) {
//        Employee employee = employeeRepository.findById(id);
//        if (updatedEmployee.getAge() != null) {
//            employee.setAge(updatedEmployee.getAge());
//        }
//        if (updatedEmployee.getSalary() != null) {
//            employee.setSalary(updatedEmployee.getSalary());
//        }
//        return employeeRepository.save(id, employee);
//    }
//
    public Company findById(Integer id) {
        return companyRepository.findById(id);
    }
//
//    public List<Employee> findByGender(String gender) {
//        return employeeRepository.findByGender(gender);
//    }
//
//    public List<Employee> findByPage(Integer page, Integer pageSize) {
//        return employeeRepository.findByPage(page, pageSize);
//    }
//
//    public Employee create(Employee employee) {
//        return employeeRepository.create(employee);
//    }
//
//    public Employee remove(Integer id) {
//        return employeeRepository.remove(id);
//    }
}
