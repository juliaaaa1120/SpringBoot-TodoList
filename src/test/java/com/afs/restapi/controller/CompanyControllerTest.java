package com.afs.restapi.controller;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.CompanyRepository;
import com.afs.restapi.repository.CompanyRepositoryInMongo;
import com.afs.restapi.repository.EmployeeRepository;
import com.afs.restapi.repository.EmployeeRepositoryInMongo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeRepositoryInMongo employeeRepositoryInMongo;
    @Autowired
    CompanyRepositoryInMongo companyRepositoryInMongo;

    // GET "/employees"
    // prepare data
    // send request
    // assertion
    @BeforeEach
    void cleanRepository() {
        companyRepository.clearAll();
        employeeRepository.clearAll();
        employeeRepositoryInMongo.deleteAll();
        companyRepositoryInMongo.deleteAll();
    }

    @Test
    void should_get_all_companies_when_perform_get_given_companies() throws Exception {
        //given
        Company company = new Company(null, "OOCL");
        companyRepositoryInMongo.insert(company);
        Employee employee1 = new Employee(null, "Julia", 18, "Female", company.getId().toString(), 100000);
        employeeRepositoryInMongo.insert(employee1);
        Employee employee2 = new Employee(null, "Jason", 18, "Male", company.getId().toString(), 100000);
        employeeRepositoryInMongo.insert(employee2);
        Employee employee3 = new Employee(null, "Klaus", 18, "Male", company.getId().toString(),100000);
        employeeRepositoryInMongo.insert(employee3);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", employee.getId()))
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees/" + employee.getId()))
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees?gender=" + "Male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(company.getId()))
                .andExpect(jsonPath("$[0].name").value("OOCL"))
                .andExpect(jsonPath("$[0].employees", hasSize(3)))
                .andExpect(jsonPath("$[0].employees[*].id").value(containsInAnyOrder(employee1.getId(), employee2.getId(), employee3.getId())))
                .andExpect(jsonPath("$[0].employees[*].name").value(containsInAnyOrder("Julia", "Jason", "Klaus")))
                .andExpect(jsonPath("$[0].employees[*].age").value(containsInAnyOrder(18, 18, 18)))
                .andExpect(jsonPath("$[0].employees[*].gender").value(containsInAnyOrder("Female", "Male", "Male")))
                .andExpect(jsonPath("$[0].employees[*].companyId").value(containsInAnyOrder(company.getId(), company.getId(), company.getId())))
                .andExpect(jsonPath("$[0].employees[*].salary").value(containsInAnyOrder(100000, 100000, 100000)));
        //then
    }

    @Test
    void should_get_company_by_id_when_perform_get_given_company_id() throws Exception {
        //given
        Company company1 = new Company(null, "OOCL");
        companyRepositoryInMongo.insert(company1);
        Company company2 = new Company(null, "SF Express");
        companyRepositoryInMongo.insert(company2);
        Employee employee1 = new Employee(null, "Julia", 18, "Female", company1.getId().toString(), 100000);
        employeeRepositoryInMongo.insert(employee1);
        Employee employee2 = new Employee(null, "Jason", 18, "Male",company1.getId().toString(), 100000);
        employeeRepositoryInMongo.insert(employee2);
        Employee employee3 = new Employee(null, "Klaus", 18, "Male", company2.getId().toString(),100000);
        employeeRepositoryInMongo.insert(employee3);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}", company2.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(company2.getId()))
                .andExpect(jsonPath("$.name").value("SF Express"))
                .andExpect(jsonPath("$.employeeResponses", hasSize(1)))
                .andExpect(jsonPath("$.employeeResponses[0].id").value(employee3.getId()))
                .andExpect(jsonPath("$.employeeResponses[0].name").value("Klaus"))
                .andExpect(jsonPath("$.employeeResponses[0].age").value(18))
                .andExpect(jsonPath("$.employeeResponses[0].gender").value("Male"));
        //then
    }

    @Test
    void should_get_all_employees_in_company_when_perform_get_given_company_id_employees() throws Exception {
        //given
        Company company1 = new Company(null, "OOCL");
        companyRepositoryInMongo.insert(company1);
        Company company2 = new Company(null, "SF Express");
        companyRepositoryInMongo.insert(company2);
        Employee employee1 = new Employee(null, "Julia", 18, "Female", company1.getId().toString(), 100000);
        employeeRepositoryInMongo.insert(employee1);
        Employee employee2 = new Employee(null, "Jason", 18, "Male", company1.getId().toString(), 100000);
        employeeRepositoryInMongo.insert(employee2);
        Employee employee3 = new Employee(null, "Klaus", 18, "Male", company2.getId().toString(),100000);
        employeeRepositoryInMongo.insert(employee3);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/employees", company1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(employee1.getId(), employee2.getId())))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder("Julia", "Jason")))
                .andExpect(jsonPath("$[*].age").value(containsInAnyOrder(18, 18)))
                .andExpect(jsonPath("$[*].gender").value(containsInAnyOrder("Female", "Male")));
        //then
    }

    @Test
    void should_get_companies_by_page_when_perform_get_given_page_and_page_size() throws Exception {
        //given
        Company company1 = new Company(null, "OOCL");
        companyRepositoryInMongo.insert(company1);
        Company company2 = new Company(null, "DHL");
        companyRepositoryInMongo.insert(company2);
        Company company3 = new Company(null, "SF Express");
        companyRepositoryInMongo.insert(company3);
        Company company4 = new Company(null, "Disney");
        companyRepositoryInMongo.insert(company4);
        Employee employee1 = new Employee(null, "Julia", 18, "Female",company1.getId().toString(), 100000);
        employeeRepositoryInMongo.insert(employee1);
        Employee employee2 = new Employee(null, "Jason", 18, "Male",company2.getId().toString(), 100000);
        employeeRepositoryInMongo.insert(employee2);
        Employee employee3 = new Employee(null, "Klaus", 18, "Male", company3.getId().toString(),100000);
        employeeRepositoryInMongo.insert(employee3);
        Employee employee4 = new Employee(null, "Gloria", 18, "Female", company4.getId().toString(),100000);
        employeeRepositoryInMongo.insert(employee4);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/companies")
                .param("page", "2")
                .param("pageSize", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(company3.getId(), company4.getId())))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder("SF Express", "Disney")))
                .andExpect(jsonPath("$[*].employees[*].id").value(containsInAnyOrder(employee3.getId(), employee4.getId())))
                .andExpect(jsonPath("$[*].employees[*].name").value(containsInAnyOrder("Klaus", "Gloria")))
                .andExpect(jsonPath("$[*].employees[*].age").value(containsInAnyOrder(18, 18)))
                .andExpect(jsonPath("$[*].employees[*].gender").value(containsInAnyOrder("Male", "Female")))
                .andExpect(jsonPath("$[*].employees[*].companyId").value(containsInAnyOrder(company3.getId(), company4.getId())))
                .andExpect(jsonPath("$[*].employees[*].salary").value(containsInAnyOrder(100000, 100000)));

        //then
    }

    @Test
    void should_return_company_when_perform_post_given_company() throws Exception {
        //given
        String company = "{\n" +
                "        \"name\": \"OOCL\"\n" +
                "    }";
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(company))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("OOCL"));
    }

    @Test
    void should_return_updated_employee_when_perform_put_given_employee_id() throws Exception {
        //given
        Company company = new Company(null, "OOCL");
        companyRepositoryInMongo.insert(company);
        Employee employee1 = new Employee(null, "Julia", 18, "Female", company.getId().toString(), 100000);
        employeeRepositoryInMongo.insert(employee1);
        Employee employee2 = new Employee(null, "Jason", 18, "Male", company.getId().toString(), 100000);
        employeeRepositoryInMongo.insert(employee2);
        Employee employee3 = new Employee(null, "Klaus", 18, "Male", company.getId().toString(),100000);
        employeeRepositoryInMongo.insert(employee3);
        String updatedCompany = "{\n" +
                "    \"name\": \"Disney\"\n" +
                "}";
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/companies/{id}", company.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(updatedCompany))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(company.getId()))
                .andExpect(jsonPath("$.name").value("Disney"))
                .andExpect(jsonPath("$.employees", hasSize(3)))
                .andExpect(jsonPath("$.employees[*].id").value(containsInAnyOrder(employee1.getId(), employee2.getId(), employee3.getId())))
                .andExpect(jsonPath("$.employees[*].name").value(containsInAnyOrder("Julia", "Jason", "Klaus")))
                .andExpect(jsonPath("$.employees[*].age").value(containsInAnyOrder(18, 18, 18)))
                .andExpect(jsonPath("$.employees[*].gender").value(containsInAnyOrder("Female", "Male", "Male")))
                .andExpect(jsonPath("$.employees[*].companyId").value(containsInAnyOrder(company.getId(), company.getId(), company.getId())))
                .andExpect(jsonPath("$.employees[*].salary").value(containsInAnyOrder(100000, 100000, 100000)));
    }

    @Test
    void should_return_company_when_perform_delete_given_company_id() throws Exception {
        //given
        Company company = new Company(null, "OOCL");
        companyRepositoryInMongo.insert(company);
        Employee employee1 = new Employee(null, "Julia", 18, "Female", company.getId().toString(), 100000);
        employeeRepositoryInMongo.insert(employee1);
        Employee employee2 = new Employee(null, "Jason", 18, "Male", company.getId().toString(), 100000);
        employeeRepositoryInMongo.insert(employee2);
        Employee employee3 = new Employee(null, "Klaus", 18, "Male", company.getId().toString(),100000);
        employeeRepositoryInMongo.insert(employee3);
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/{id}", company.getId()))
                .andExpect(status().isNoContent());
        assertEquals(0, companyRepositoryInMongo.findAll().size());
    }
}

