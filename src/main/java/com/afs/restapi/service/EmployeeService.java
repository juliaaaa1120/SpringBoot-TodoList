package com.afs.restapi.service;

import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee edit(Integer id, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id);
        if (updatedEmployee.getAge() != null) {
            employee.setAge(updatedEmployee.getAge());
        }
        if (updatedEmployee.getSalary() != null) {
            employee.setSalary(updatedEmployee.getSalary());
        }
        return employeeRepository.save(id, employee);
    }

    public Employee findById(Integer id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public List<Employee> findByPage(Integer page, Integer pageSize) {
        return employeeRepository.findByPage(page, pageSize);
    }

    public Employee create(Employee employee) {
        return employeeRepository.create(employee);
    }

    public Employee remove(Integer id) {
        return employeeRepository.remove(id);
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        return employeeRepository.getEmployeesByCompanyId(companyId);
    }
}
