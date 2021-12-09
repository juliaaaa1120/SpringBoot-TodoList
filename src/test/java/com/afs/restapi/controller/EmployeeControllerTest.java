package com.afs.restapi.controller;

import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Employee employee = new Employee("1", "Julia", 18, "Female", "1", 100000);
        employeeRepository.create(employee);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", employee.getId()))
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees/" + employee.getId()))
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees?gender=" + "Male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Julia"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("Female"))
                .andExpect(jsonPath("$[0].companyId").value(1))
                .andExpect(jsonPath("$[0].salary").value(100000));
        //then
    }

    @Test
    void should_get_employee_by_id_when_perform_get_given_employee_id() throws Exception {
        //given
        Employee employee1 = new Employee("1", "Julia", 18, "Female","1", 100000);
        employeeRepository.create(employee1);
        Employee employee2 = new Employee("2", "Jason", 18, "Male", "2",100000);
        employeeRepository.create(employee2);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", employee2.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Jason"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.companyId").value(2))
                .andExpect(jsonPath("$.salary").value(100000));
        //then
    }

    @Test
    void should_get_employees_by_gender_when_perform_get_given_employee_gender() throws Exception {
        //given
        Employee employee1 = new Employee("1", "Julia", 18, "Female", "1",100000);
        employeeRepository.create(employee1);
        Employee employee2 = new Employee("2", "Jason", 18, "Male","2", 100000);
        employeeRepository.create(employee2);
        Employee employee3 = new Employee("3", "Gloria", 18, "Female", "3",100000);
        employeeRepository.create(employee3);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                    .param("gender", "Female"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(1, 3)))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder("Julia", "Gloria")))
                .andExpect(jsonPath("$[*].age").value(containsInAnyOrder(18, 18)))
                .andExpect(jsonPath("$[*].gender").value(containsInAnyOrder("Female", "Female")))
                .andExpect(jsonPath("$[*].companyId").value(containsInAnyOrder(1, 3)))
                .andExpect(jsonPath("$[*].salary").value(containsInAnyOrder(100000, 100000)));
        //then
    }

    @Test
    void should_get_employees_by_page_when_perform_get_given_page_and_page_size() throws Exception {
        //given
        Employee employee1 = new Employee("1", "Julia", 18, "Female", "1",100000);
        employeeRepository.create(employee1);
        Employee employee2 = new Employee("2", "Jason", 18, "Male", "2",100000);
        employeeRepository.create(employee2);
        Employee employee3 = new Employee("3", "Gloria", 18, "Female", "3",100000);
        employeeRepository.create(employee3);
        Employee employee4 = new Employee("4", "Johnson", 18, "Male", "2",100000);
        employeeRepository.create(employee4);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .param("page", "2")
                .param("pageSize", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(3, 4)))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder("Gloria", "Johnson")))
                .andExpect(jsonPath("$[*].age").value(containsInAnyOrder(18, 18)))
                .andExpect(jsonPath("$[*].gender").value(containsInAnyOrder("Female", "Male")))
                .andExpect(jsonPath("$[*].companyId").value(containsInAnyOrder(2, 3)))
                .andExpect(jsonPath("$[*].salary").value(containsInAnyOrder(100000, 100000)));
        //then
    }

    @Test
    void should_return_employee_when_perform_post_given_employee() throws Exception {
        //given
        String employee = "{\n" +
                "        \"name\": \"Koby\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"companyId\": \"3\",\n" +
                "        \"salary\": 100000\n" +
                "}";
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Koby"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.companyId").value(3))
                .andExpect(jsonPath("$.salary").value(100000));
    }

    @Test
    void should_return_updated_employee_when_perform_put_given_employee_id() throws Exception {
        //given
        Employee employee1 = new Employee("1", "Julia", 18, "Female", "1",100000);
        employeeRepository.create(employee1);
        Employee employee2 = new Employee("2", "Jason", 18, "Male","2", 100000);
        employeeRepository.create(employee2);
        Employee employee3 = new Employee("3", "Gloria", 18, "Female", "3",100000);
        employeeRepository.create(employee3);
        String updatedEmployee = "{\n" +
                "        \"age\": 30,\n" +
                "        \"salary\": 500000\n" +
                "}";
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", employee2.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(updatedEmployee))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Jason"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.companyId").value(2))
                .andExpect(jsonPath("$.salary").value(500000));
    }

    @Test
    void should_return_employee_when_perform_delete_given_employee_id() throws Exception {
        //given
        Employee employee1 = new Employee("1", "Julia", 18, "Female", "1",100000);
        employeeRepository.create(employee1);
        Employee employee2 = new Employee("2", "Jason", 18, "Male", "2",100000);
        employeeRepository.create(employee2);
        Employee employee3 = new Employee("3", "Gloria", 18, "Female","3", 100000);
        employeeRepository.create(employee3);
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", employee2.getId()))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Jason"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.companyId").value(2))
                .andExpect(jsonPath("$.salary").value(100000));
        assertEquals(2, employeeRepository.findAll().size());
    }
}

