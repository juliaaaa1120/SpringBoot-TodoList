package com.afs.restapi.dto;

public class EmployeeRequest {
    private String name;
    private Integer age;
    private String gender;
    private String companyId;
    private Integer salary;

    public EmployeeRequest() {
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
        return this.companyId;
    }

    public Integer getSalary() {
        return this.salary;
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

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
