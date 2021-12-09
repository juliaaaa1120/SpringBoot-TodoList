package com.afs.restapi.service;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.CompanyRepository;
import com.afs.restapi.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    @Mock
    CompanyRepository mockCompanyRepository;
    @Mock
    EmployeeRepository mockEmployeeRepository;
    @InjectMocks
    CompanyService companyService;

    @Test
    void should_return_all_companies_when_find_all_given_companies() {
        //given
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("1", "OOCL"));

        given(mockCompanyRepository.findAll())
                .willReturn(companies);

        //when
        List<Company> actual = companyService.findAll();

        //then
        assertEquals(companies, actual);
    }

    @Test
    void should_return_company_by_id_when_find_by_id_given_company_id() {
        //given
        Company company = new Company("1", "OOCL");

        given(mockCompanyRepository.findById(company.getId()))
                .willReturn(company);

        //when
        Company actual = companyService.findById(company.getId());

        //then
        assertEquals(company, actual);
    }

    @Test
    void should_return_all_employees_in_company_when_find_all_employees_by_company_id_given_company_id_employees() {
        //given
        List<Employee> employees = Arrays.asList(new Employee("1", "Julia", 18, "Female","1", 100000),
                new Employee("2", "Jason", 18, "Male","1", 100000),
                new Employee("3", "Klaus", 18, "Male","1", 100000));
        Company company = new Company("1", "OOCL");

        given(mockCompanyRepository.findAllEmployeesByCompanyId(company.getId()))
                .willReturn(employees);

        //when
        List<Employee> actual = companyService.findAllEmployeesByCompanyId(company.getId());

        //then
        assertEquals(employees, actual);
    }

    @Test
    void should_return_companies_by_page_when_find_by_page_given_page_and_page_size() {
        //given
        List<Company> displayedCompanies = new ArrayList<>();
        Company company1 = new Company("1", "OOCL");
        Company company2 = new Company("2", "DHL");
        Company company3 = new Company("3", "SF Express");
        Company company4 = new Company("4", "Disney");
        displayedCompanies.add(company3);
        displayedCompanies.add(company4);

        given(mockCompanyRepository.findByPage(2,2))
                .willReturn(displayedCompanies);

        //when
        List<Company> actual = companyService.findByPage(2,2);

        //then
        assertEquals(displayedCompanies, actual);
    }

    @Test
    void should_return_company_when_create_company_given_company() {
        //given
        Company company = new Company("1", "OOCL");

        given(mockCompanyRepository.create(company))
                .willReturn(company);

        //when
        Company actual = companyService.create(company);

        //then
        assertEquals(company, actual);
    }

    @Test
    void should_return_updated_company_when_edit_company_given_updated_company() {
        //given
        Company company = new Company("1", "OOCL");
        Company updatedCompany = new Company("1", "Disney");
        given(mockCompanyRepository.findById(any()))
                .willReturn(company);
        company.setCompanyName(updatedCompany.getCompanyName());
        company.setEmployees(updatedCompany.getEmployees());
        given(mockCompanyRepository.save(any(), any(Company.class)))
                .willReturn(company);

        //when
        Company actual = companyService.edit(company.getId(), updatedCompany);

        //then
        assertEquals(company, actual);
    }

    @Test
    void should_return_company_when_delete_company_given_company_id() {
        //given
        Company company1 = new Company("1", "OOCL");
        Company company2 = new Company("2", "DHL");

        given(mockCompanyRepository.remove(company2.getId()))
                .willReturn(company2);

        //when
        Company actual = companyService.remove(company2.getId());

        //then
        assertEquals(company2, actual);
    }
}
