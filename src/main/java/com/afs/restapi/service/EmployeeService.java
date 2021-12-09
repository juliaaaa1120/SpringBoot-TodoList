package com.afs.restapi.service;

import com.afs.restapi.entity.Employee;
import com.afs.restapi.exception.NoEmployeeFoundException;
import com.afs.restapi.repository.EmployeeRepository;
import com.afs.restapi.repository.EmployeeRepositoryInMongo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeRepositoryInMongo employeeRepositoryInMongo;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeRepositoryInMongo employeeRepositoryInMongo) {
        this.employeeRepository = employeeRepository;
        this.employeeRepositoryInMongo = employeeRepositoryInMongo;
    }

    public List<Employee> findAll() {
        return employeeRepositoryInMongo.findAll();
    }

    public Employee edit(String id, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id);
        if (updatedEmployee.getAge() != null) {
            employee.setAge(updatedEmployee.getAge());
        }
        if (updatedEmployee.getSalary() != null) {
            employee.setSalary(updatedEmployee.getSalary());
        }
        return employeeRepository.save(id, employee);
    }

    public Employee findById(String id) {
        return employeeRepositoryInMongo.findById(id)
                .orElseThrow(NoEmployeeFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employeeRepositoryInMongo.findByGender(gender);
    }

    public List<Employee> findByPage(Integer page, Integer pageSize) {
        return employeeRepository.findByPage(page, pageSize);
    }

    public Employee create(Employee employee) {
        return employeeRepository.create(employee);
    }

    public Employee remove(String id) {
        return employeeRepository.remove(id);
    }

    public List<Employee> getEmployeesByCompanyId(String companyId) {
        return employeeRepository.getEmployeesByCompanyId(companyId);
    }
}
