package com.afs.restapi.dto;

import com.afs.restapi.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class CompanyRequest {
    private String name;

    public CompanyRequest() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
