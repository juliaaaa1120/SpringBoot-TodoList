package com.afs.restapi.mapper;

import com.afs.restapi.dto.EmployeeRequest;
import com.afs.restapi.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();

        // manual set all attribute to employee from employeeRequest
//        employee.setId(employeeRequest.getId());
//        employee.setName(employeeRequest.getName());
//        employee.setAge(employeeRequest.getAge());
//        employee.setGender(employeeRequest.getGender());
//        employee.setCompanyId(employeeRequest.getCompanyId());
//        employee.setSalary(employeeRequest.getSalary());

        // BeanUtils
        BeanUtils.copyProperties(employeeRequest, employee);
    }
}
