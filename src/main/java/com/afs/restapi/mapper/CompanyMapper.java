package com.afs.restapi.mapper;

import com.afs.restapi.dto.CompanyRequest;
import com.afs.restapi.dto.CompanyResponse;
import com.afs.restapi.dto.EmployeeRequest;
import com.afs.restapi.dto.EmployeeResponse;
import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    @Autowired
    private final EmployeeMapper employeeMapper;

    public CompanyMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();

        // manual set all attribute to employee from employeeRequest
//        employee.setId(employeeRequest.getId());
//        employee.setName(employeeRequest.getName());
//        employee.setAge(employeeRequest.getAge());
//        employee.setGender(employeeRequest.getGender());
//        employee.setCompanyId(employeeRequest.getCompanyId());
//        employee.setSalary(employeeRequest.getSalary());

        // BeanUtils
        BeanUtils.copyProperties(companyRequest, company);
        return company;
    }

    public CompanyResponse toResponse(Company company, List<Employee> employees) {
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);
        companyResponse.setEmployeeResponses(
            employees.stream()
                    .map(employee -> employeeMapper.toResponse(employee))
                    .collect(Collectors.toList())
        );
        return companyResponse;
    }
}
