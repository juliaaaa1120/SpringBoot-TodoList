package com.afs.restapi.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Document
public class Company {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String companyName;
    private List<Employee> employees = new ArrayList<>();

    public Company(String id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }
    public Company() {
    }

    public String getId() {
        return this.id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

