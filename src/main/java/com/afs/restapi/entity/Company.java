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
    private String name;
    private List<Employee> employees = new ArrayList<>();

    public Company(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Company() {
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

