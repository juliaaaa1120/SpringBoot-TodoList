package com.afs.restapi;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.CompanyRepository;
import com.afs.restapi.repository.EmployeeRepository;
import com.afs.restapi.service.CompanyService;
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
    @InjectMocks
    CompanyService companyService;

    @Test
    void should_return_all_companies_when_find_all_given_companies() {
        //given
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "OOCL", Arrays.asList(
                new Employee(1, "Julia", 18, "Female", 100000),
                new Employee(2, "Jason", 18, "Male", 100000),
                new Employee(3, "Klaus", 18, "Male", 100000)
        )));
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
        Company company = new Company(1, "OOCL", Arrays.asList(
                new Employee(1, "Julia", 18, "Female", 100000),
                new Employee(2, "Jason", 18, "Male", 100000),
                new Employee(3, "Klaus", 18, "Male", 100000)
        ));

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
        List<Employee> employees = Arrays.asList(new Employee(1, "Julia", 18, "Female", 100000),
                new Employee(2, "Jason", 18, "Male", 100000),
                new Employee(3, "Klaus", 18, "Male", 100000));
        Company company = new Company(1, "OOCL", employees);

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
        Company company1 = new Company(1, "OOCL", Arrays.asList(
                new Employee(1, "Julia", 18, "Female", 100000),
                new Employee(2, "Jason", 18, "Male", 100000),
                new Employee(3, "Klaus", 18, "Male", 100000)
        ));
        Company company2 = new Company(2, "DHL", Arrays.asList(
                new Employee(4, "Joanne", 18, "Female", 100000),
                new Employee(5, "John", 18, "Male", 100000),
                new Employee(6, "Johnson", 18, "Male", 100000),
                new Employee(7, "Nicole", 18, "Female", 100000)
        ));
        Company company3 = new Company(3, "SF Express", Arrays.asList(
                new Employee(8, "Gloria", 18, "Female", 100000)
        ));
        Company company4 = new Company(4, "Disney", Arrays.asList(
                new Employee(9, "Koby", 18, "Male", 100000)
        ));
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
        Company company = new Company(1, "OOCL", Arrays.asList(
                new Employee(1, "Julia", 18, "Female", 100000),
                new Employee(2, "Jason", 18, "Male", 100000),
                new Employee(3, "Klaus", 18, "Male", 100000)
        ));

        given(mockCompanyRepository.create(company))
                .willReturn(company);

        //when
        Company actual = companyService.create(company);

        //then
        assertEquals(company, actual);
    }

//    @Test
//    void should_return_updated_employee_when_edit_employee_given_updated_employee() {
//        //given
//        Employee employee = new Employee(1, "Julia", 22, "Female", 100000);
//        Employee updatedEmployee = new Employee(1, "Julia", 25, "Female", 500000);
//        given(mockCompanyRepository.findById(any()))
//                .willReturn(employee);
//        employee.setAge(updatedEmployee.getAge());
//        employee.setSalary(updatedEmployee.getSalary());
//        given(mockCompanyRepository.save(any(), any(Employee.class)))
//                .willReturn(employee);
//
//        //when
//        Employee actual = companyService.edit(employee.getId(), updatedEmployee);
//
//        //then
//        assertEquals(employee, actual);
//    }
//
//    @Test
//    void should_return_employee_when_delete_employee_given_employee_id() {
//        //given
//        Employee employee1 = new Employee(1, "Julia", 22, "Female", 100000);
//        Employee employee2 = new Employee(2, "Jason", 22, "Male", 100000);
//
//        given(mockCompanyRepository.remove(employee1.getId()))
//                .willReturn(employee1);
//
//        //when
//        Employee actual = companyService.remove(employee1.getId());
//
//        //then
//        assertEquals(employee1, actual);
//    }
}
