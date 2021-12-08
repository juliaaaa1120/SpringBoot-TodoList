package com.afs.restapi.entity;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private Integer id;
    private String companyName;
    private List<Employee> employees = new ArrayList<>();

    public Company(int id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public Integer getId() {
        return this.id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

