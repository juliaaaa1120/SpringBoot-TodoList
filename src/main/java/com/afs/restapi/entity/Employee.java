package com.afs.restapi.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Employee {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private Integer age;
    private String gender;
    private String companyID;
    private Integer salary;


    public Employee(String id, String name, Integer age, String gender, String companyID, Integer salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.companyID = companyID;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getGender() {
        return this.gender;
    }

    public String getCompanyId() {
        return this.companyID;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCompanyId(String companyID) {
        this.companyID = companyID;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}

