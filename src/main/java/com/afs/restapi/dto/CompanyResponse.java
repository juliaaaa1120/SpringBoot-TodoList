package com.afs.restapi.dto;

import com.afs.restapi.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class CompanyResponse {
    private String id;
    private String companyName;
    private List<EmployeeResponse> employeeResponses;

    public CompanyResponse() {
    }

    public String getId() {
        return this.id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public List<EmployeeResponse> getEmployeeResponses() {
        return this.employeeResponses;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmployeeResponses(List<EmployeeResponse> employeeResponses) {
        this.employeeResponses = employeeResponses;
    }
}
