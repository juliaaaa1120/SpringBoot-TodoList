package com.afs.restapi.dto;

import com.afs.restapi.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class CompanyRequest {
    private String companyName;

    public CompanyRequest() {
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
