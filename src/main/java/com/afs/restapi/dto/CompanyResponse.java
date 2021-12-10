package com.afs.restapi.dto;

import com.afs.restapi.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class CompanyResponse {
    private String id;
    private String name;
    private List<EmployeeResponse> employeeResponses;

    public CompanyResponse() {
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<EmployeeResponse> getEmployeeResponses() {
        return this.employeeResponses;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeResponses(List<EmployeeResponse> employeeResponses) {
        this.employeeResponses = employeeResponses;
    }
}
