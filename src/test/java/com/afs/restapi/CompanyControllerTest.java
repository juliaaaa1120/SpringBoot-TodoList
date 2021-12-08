package com.afs.restapi;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
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

    // GET "/employees"
    // prepare data
    // send request
    // assertion
    @BeforeEach
    void cleanRepository() {
        companyRepository.clearAll();
    }

    @Test
    void should_get_all_companies_when_perform_get_given_companies() throws Exception {
        //given
        Company company1 = new Company(1, "OOCL", Arrays.asList(
                new Employee(1, "Julia", 18, "Female", 100000),
                new Employee(2, "Jason", 18, "Male", 100000),
                new Employee(3, "Klaus", 18, "Male", 100000)
        ));
        companyRepository.create(company1);
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
                .andExpect(jsonPath("$[0].employees[0].id").value(1))
                .andExpect(jsonPath("$[0].employees[0].name").value("Julia"))
                .andExpect(jsonPath("$[0].employees[0].age").value(18))
                .andExpect(jsonPath("$[0].employees[0].gender").value("Female"))
                .andExpect(jsonPath("$[0].employees[0].salary").value(100000))
                .andExpect(jsonPath("$[0].employees[1].id").value(2))
                .andExpect(jsonPath("$[0].employees[1].name").value("Jason"))
                .andExpect(jsonPath("$[0].employees[1].age").value(18))
                .andExpect(jsonPath("$[0].employees[1].gender").value("Male"))
                .andExpect(jsonPath("$[0].employees[1].salary").value(100000));
        //then
    }

    @Test
    void should_get_company_by_id_when_perform_get_given_company_id() throws Exception {
        //given
        Company company1 = new Company(1, "OOCL", Arrays.asList(
                new Employee(1, "Julia", 18, "Female", 100000),
                new Employee(2, "Jason", 18, "Male", 100000),
                new Employee(3, "Klaus", 18, "Male", 100000)
        ));
        companyRepository.create(company1);
        Company company2 = new Company(2, "SF Express", Arrays.asList(
                new Employee(8, "Gloria", 18, "Female", 100000)
        ));
        companyRepository.create(company2);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}", company2.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.companyName").value("SF Express"))
                .andExpect(jsonPath("$.employees", hasSize(1)))
                .andExpect(jsonPath("$.employees[0].id").value(8))
                .andExpect(jsonPath("$.employees[0].name").value("Gloria"))
                .andExpect(jsonPath("$.employees[0].age").value(18))
                .andExpect(jsonPath("$.employees[0].gender").value("Female"))
                .andExpect(jsonPath("$.employees[0].salary").value(100000));
        //then
    }

    @Test
    void should_get_all_employees_in_company_when_perform_get_given_company_id_employees() throws Exception {
        //given
        Company company = new Company(1, "OOCL", Arrays.asList(
                new Employee(1, "Julia", 18, "Female", 100000),
                new Employee(2, "Jason", 18, "Male", 100000),
                new Employee(3, "Klaus", 18, "Male", 100000)
        ));
        companyRepository.create(company);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/employees", company.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Julia"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("Female"))
                .andExpect(jsonPath("$[0].salary").value(100000))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jason"))
                .andExpect(jsonPath("$[1].age").value(18))
                .andExpect(jsonPath("$[1].gender").value("Male"))
                .andExpect(jsonPath("$[1].salary").value(100000));
        //then
    }

    @Test
    void should_get_companies_by_page_when_perform_get_given_page_and_page_size() throws Exception {
        //given
        Company company1 = new Company(1, "OOCL", Arrays.asList(
                new Employee(1, "Julia", 18, "Female", 100000),
                new Employee(2, "Jason", 18, "Male", 100000),
                new Employee(3, "Klaus", 18, "Male", 100000)
        ));
        companyRepository.create(company1);
        Company company2 = new Company(2, "DHL", Arrays.asList(
                new Employee(4, "Joanne", 18, "Female", 100000),
                new Employee(5, "John", 18, "Male", 100000),
                new Employee(6, "Johnson", 18, "Male", 100000),
                new Employee(7, "Nicole", 18, "Female", 100000)
        ));
        companyRepository.create(company2);
        Company company3 = new Company(3, "SF Express", Arrays.asList(
                new Employee(8, "Gloria", 18, "Female", 100000)
        ));
        companyRepository.create(company3);
        Company company4 = new Company(4, "Disney", Arrays.asList(
                new Employee(9, "Koby", 18, "Male", 100000)
        ));
        companyRepository.create(company4);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/companies")
                .param("page", "2")
                .param("pageSize", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].companyName").value("SF Express"))
                .andExpect(jsonPath("$[0].employees[0].id").value(8))
                .andExpect(jsonPath("$[0].employees[0].name").value("Gloria"))
                .andExpect(jsonPath("$[0].employees[0].age").value(18))
                .andExpect(jsonPath("$[0].employees[0].gender").value("Female"))
                .andExpect(jsonPath("$[0].employees[0].salary").value(100000))
                .andExpect(jsonPath("$[1].id").value(4))
                .andExpect(jsonPath("$[1].companyName").value("Disney"))
                .andExpect(jsonPath("$[1].employees[0].id").value(9))
                .andExpect(jsonPath("$[1].employees[0].name").value("Koby"))
                .andExpect(jsonPath("$[1].employees[0].age").value(18))
                .andExpect(jsonPath("$[1].employees[0].gender").value("Male"))
                .andExpect(jsonPath("$[1].employees[0].salary").value(100000));

        //then
    }

    @Test
    void should_return_company_when_perform_post_given_company() throws Exception {
        //given
        String employee = "{\n" +
                "        \"id\": 1,\n" +
                "        \"companyName\": \"OOCL\",\n" +
                "        \"employees\": [\n" +
                "            {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"Julia\",\n" +
                "                \"age\": 18,\n" +
                "                \"gender\": \"Female\",\n" +
                "                \"salary\": 100000\n" +
                "            }\n" +
                "        ]\n" +
                "    }";
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.companyName").value("OOCL"))
                .andExpect(jsonPath("$.employees[0].id").value(1))
                .andExpect(jsonPath("$.employees[0].name").value("Julia"))
                .andExpect(jsonPath("$.employees[0].age").value(18))
                .andExpect(jsonPath("$.employees[0].gender").value("Female"))
                .andExpect(jsonPath("$.employees[0].salary").value(100000));
    }

    @Test
    void should_return_updated_employee_when_perform_put_given_employee_id() throws Exception {
        //given
        Company company = new Company(1, "OOCL", Arrays.asList(
                new Employee(1, "Julia", 18, "Female", 100000),
                new Employee(2, "Jason", 18, "Male", 100000),
                new Employee(3, "Klaus", 18, "Male", 100000)
        ));
        companyRepository.create(company);
        String updatedCompany = "{\n" +
                "    \"companyName\": \"Disney\",\n" +
                "    \"employees\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"Gloria\",\n" +
                "            \"age\": 18,\n" +
                "            \"gender\": \"Female\",\n" +
                "            \"salary\": 100000\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/companies/{id}", company.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(updatedCompany))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.companyName").value("Disney"))
                .andExpect(jsonPath("$.employees[0].id").value(1))
                .andExpect(jsonPath("$.employees[0].name").value("Gloria"))
                .andExpect(jsonPath("$.employees[0].age").value(18))
                .andExpect(jsonPath("$.employees[0].gender").value("Female"))
                .andExpect(jsonPath("$.employees[0].salary").value(100000));
    }

//    @Test
//    void should_return_null_when_perform_delete_given_employee_id() throws Exception {
//        //given
//        Employee employee1 = new Employee(1, "Julia", 18, "Female", 100000);
//        companyRepository.create(employee1);
//        Employee employee2 = new Employee(2, "Jason", 18, "Male", 100000);
//        companyRepository.create(employee2);
//        Employee employee3 = new Employee(3, "Gloria", 18, "Female", 100000);
//        companyRepository.create(employee3);
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", employee2.getId()))
//                .andExpect(status().isNoContent());
//        assertEquals(2, companyRepository.findAll().size());
//    }
}

