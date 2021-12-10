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
        Company company = new Company("1", "OOCL");
        companyRepositoryInMongo.insert(company);
        Employee employee1 = new Employee("1", "Julia", 18, "Female","1", 100000);
        employeeRepositoryInMongo.insert(employee1);
        Employee employee2 = new Employee("2", "Jason", 18, "Male","1", 100000);
        employeeRepositoryInMongo.insert(employee2);
        Employee employee3 = new Employee("3", "Klaus", 18, "Male", "1",100000);
        employeeRepositoryInMongo.insert(employee3);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", employee.getId()))
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees/" + employee.getId()))
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees?gender=" + "Male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].companyName").value("OOCL"))
                .andExpect(jsonPath("$[0].employees", hasSize(3)))
                .andExpect(jsonPath("$[0].employees[*].id").value(containsInAnyOrder("1", "2", "3")))
                .andExpect(jsonPath("$[0].employees[*].name").value(containsInAnyOrder("Julia", "Jason", "Klaus")))
                .andExpect(jsonPath("$[0].employees[*].age").value(containsInAnyOrder(18, 18, 18)))
                .andExpect(jsonPath("$[0].employees[*].companyId").value(containsInAnyOrder("1", "1", "1")))
                .andExpect(jsonPath("$[0].employees[*].gender").value(containsInAnyOrder("Female", "Male", "Male")));
        //then
    }

//    @Test
//    void should_get_company_by_id_when_perform_get_given_company_id() throws Exception {
//        //given
//        Company company1 = new Company("1", "OOCL");
//        companyRepository.create(company1);
//        Company company2 = new Company("2", "SF Express");
//        companyRepository.create(company2);
//        Employee employee1 = new Employee("1", "Julia", 18, "Female","1", 100000);
//        employeeRepository.create(employee1);
//        Employee employee2 = new Employee("2", "Jason", 18, "Male","1", 100000);
//        employeeRepository.create(employee2);
//        Employee employee3 = new Employee("3", "Klaus", 18, "Male", "2",100000);
//        employeeRepository.create(employee3);
//
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}", company2.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(2))
//                .andExpect(jsonPath("$.companyName").value("SF Express"))
//                .andExpect(jsonPath("$.employees", hasSize(1)))
//                .andExpect(jsonPath("$.employees[0].id").value(3))
//                .andExpect(jsonPath("$.employees[0].name").value("Klaus"))
//                .andExpect(jsonPath("$.employees[0].age").value(18))
//                .andExpect(jsonPath("$.employees[0].gender").value("Male"))
//                .andExpect(jsonPath("$.employees[0].companyId").value(2))
//                .andExpect(jsonPath("$.employees[0].salary").value(100000));
//        //then
//    }
//
//    @Test
//    void should_get_all_employees_in_company_when_perform_get_given_company_id_employees() throws Exception {
//        //given
//        Company company1 = new Company("1", "OOCL");
//        companyRepository.create(company1);
//        Company company2 = new Company("2", "SF Express");
//        companyRepository.create(company2);
//        Employee employee1 = new Employee("1", "Julia", 18, "Female", "1", 100000);
//        employeeRepository.create(employee1);
//        Employee employee2 = new Employee("2", "Jason", 18, "Male", "1", 100000);
//        employeeRepository.create(employee2);
//        Employee employee3 = new Employee("3", "Klaus", 18, "Male", "2",100000);
//        employeeRepository.create(employee3);
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/employees", company1.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(1, 2)))
//                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder("Julia", "Jason")))
//                .andExpect(jsonPath("$[*].age").value(containsInAnyOrder(18, 18)))
//                .andExpect(jsonPath("$[*].gender").value(containsInAnyOrder("Female", "Male")))
//                .andExpect(jsonPath("$[*].companyId").value(containsInAnyOrder(1, 1)))
//                .andExpect(jsonPath("$[*].salary").value(containsInAnyOrder(100000, 100000)));
//        //then
//    }
//
//    @Test
//    void should_get_companies_by_page_when_perform_get_given_page_and_page_size() throws Exception {
//        //given
//        Company company1 = new Company("1", "OOCL");
//        companyRepository.create(company1);
//        Company company2 = new Company("2", "DHL");
//        companyRepository.create(company2);
//        Company company3 = new Company("3", "SF Express");
//        companyRepository.create(company3);
//        Company company4 = new Company("4", "Disney");
//        companyRepository.create(company4);
//        Employee employee1 = new Employee("1", "Julia", 18, "Female","1", 100000);
//        employeeRepository.create(employee1);
//        Employee employee2 = new Employee("2", "Jason", 18, "Male","2", 100000);
//        employeeRepository.create(employee2);
//        Employee employee3 = new Employee("3", "Klaus", 18, "Male", "3",100000);
//        employeeRepository.create(employee3);
//        Employee employee4 = new Employee("4", "Gloria", 18, "Female", "4",100000);
//        employeeRepository.create(employee4);
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.get("/companies")
//                .param("page", "2")
//                .param("pageSize", "2"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(3, 4)))
//                .andExpect(jsonPath("$[*].companyName").value(containsInAnyOrder("SF Express", "Disney")))
//                .andExpect(jsonPath("$[*].employees[*].id").value(containsInAnyOrder(3, 4)))
//                .andExpect(jsonPath("$[*].employees[*].name").value(containsInAnyOrder("Klaus", "Gloria")))
//                .andExpect(jsonPath("$[*].employees[*].age").value(containsInAnyOrder(18, 18)))
//                .andExpect(jsonPath("$[*].employees[*].gender").value(containsInAnyOrder("Male", "Female")))
//                .andExpect(jsonPath("$[*].employees[*].companyId").value(containsInAnyOrder(3, 4)))
//                .andExpect(jsonPath("$[*].employees[*].salary").value(containsInAnyOrder(100000, 100000)));
//
//        //then
//    }
//
//    @Test
//    void should_return_company_when_perform_post_given_company() throws Exception {
//        //given
//        String employee = "{\n" +
//                "        \"companyName\": \"OOCL\"\n" +
//                "    }";
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.post("/companies")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(employee))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").isNumber())
//                .andExpect(jsonPath("$.companyName").value("OOCL"));
//    }
//
//    @Test
//    void should_return_updated_employee_when_perform_put_given_employee_id() throws Exception {
//        //given
//        Company company = new Company("1", "OOCL");
//        companyRepository.create(company);
//        Employee employee1 = new Employee("1", "Julia", 18, "Female","1", 100000);
//        employeeRepository.create(employee1);
//        Employee employee2 = new Employee("2", "Jason", 18, "Male","1", 100000);
//        employeeRepository.create(employee2);
//        Employee employee3 = new Employee("3", "Klaus", 18, "Male", "1",100000);
//        employeeRepository.create(employee3);
//        String updatedCompany = "{\n" +
//                "    \"companyName\": \"Disney\"\n" +
//                "}";
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.put("/companies/{id}", company.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(updatedCompany))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.companyName").value("Disney"))
//                .andExpect(jsonPath("$.employees", hasSize(3)))
//                .andExpect(jsonPath("$.employees[*].id").value(containsInAnyOrder(1, 2, 3)))
//                .andExpect(jsonPath("$.employees[*].name").value(containsInAnyOrder("Julia", "Jason", "Klaus")))
//                .andExpect(jsonPath("$.employees[*].age").value(containsInAnyOrder(18, 18, 18)))
//                .andExpect(jsonPath("$.employees[*].gender").value(containsInAnyOrder("Female", "Male", "Male")))
//                .andExpect(jsonPath("$.employees[*].companyId").value(containsInAnyOrder(1, 1, 1)))
//                .andExpect(jsonPath("$.employees[*].salary").value(containsInAnyOrder(100000, 100000, 100000)));
//    }
//
//    @Test
//    void should_return_company_when_perform_delete_given_company_id() throws Exception {
//        //given
//        Company company = new Company("1", "OOCL");
//        companyRepository.create(company);
//        Employee employee1 = new Employee("1", "Julia", 18, "Female","1", 100000);
//        employeeRepository.create(employee1);
//        Employee employee2 = new Employee("2", "Jason", 18, "Male","1", 100000);
//        employeeRepository.create(employee2);
//        Employee employee3 = new Employee("3", "Klaus", 18, "Male", "1",100000);
//        employeeRepository.create(employee3);
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/{id}", company.getId()))
//                .andExpect(status().isNoContent())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.companyName").value("OOCL"))
//                .andExpect(jsonPath("$.employees", hasSize(3)))
//                .andExpect(jsonPath("$.employees[*].id").value(containsInAnyOrder(1, 2, 3)))
//                .andExpect(jsonPath("$.employees[*].name").value(containsInAnyOrder("Julia", "Jason", "Klaus")))
//                .andExpect(jsonPath("$.employees[*].age").value(containsInAnyOrder(18, 18, 18)))
//                .andExpect(jsonPath("$.employees[*].gender").value(containsInAnyOrder("Female", "Male", "Male")))
//                .andExpect(jsonPath("$.employees[*].companyId").value(containsInAnyOrder(1, 1, 1)))
//                .andExpect(jsonPath("$.employees[*].salary").value(containsInAnyOrder(100000, 100000, 100000)));
//        assertEquals(0, companyRepository.findAll().size());
//    }
}

