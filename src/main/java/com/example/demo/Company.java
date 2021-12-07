package com.example.demo;

import java.util.List;

public class Company {
    private final Integer id;
    private final String companyName;
    private final List<Employee> employees;


    public Company(int id, String companyName, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employees = employees;
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
}

