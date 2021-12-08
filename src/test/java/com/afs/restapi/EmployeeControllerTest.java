package com.afs.restapi;

import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    EmployeeRepository employeeRepository;

    // GET "/employees"
    // prepare data
    // send request
    // assertion
    @BeforeEach
    void cleanRepository() {
        employeeRepository.clearAll();
    }

    @Test
    void should_get_all_employees_when_perform_get_given_employees() throws Exception {
        //given
        Employee employee = new Employee(1, "Julia", 18, "Female", 100000);
        employeeRepository.create(employee);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", employee.getId()))
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees/" + employee.getId()))
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees?gender=" + "Male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Julia"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("Female"))
                .andExpect(jsonPath("$[0].salary").value(100000));
        //then
    }

    @Test
    void should_return_employee_when_perform_post_given_employee() throws Exception {
        //given
        String employee = "{\n" +
                "        \"name\": \"Koby\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"salary\": 100000\n" +
                "}";

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Koby"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.salary").value(100000));
    }
}

