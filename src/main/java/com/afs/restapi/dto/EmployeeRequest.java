package com.afs.restapi.dto;

public class EmployeeRequest {
    private String id;
    private final String name;
    private Integer age;
    private final String gender;
    private String companyID;
    private Integer salary;


    public EmployeeRequest(String id, String name, Integer age, String gender, String companyID, Integer salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.companyID = companyID;
        this.salary = salary;
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

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCompanyId(String companyID) {
        this.companyID = companyID;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
