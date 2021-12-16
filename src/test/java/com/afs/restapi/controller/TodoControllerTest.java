//package com.afs.restapi.controller;
//
//import com.afs.restapi.entity.Todo;
//import com.afs.restapi.repository.TodoRepositoryInMongo;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TodoControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    EmployeeRepository employeeRepository;
//    @Autowired
//    TodoRepositoryInMongo todoRepositoryInMongo;
//
//    // GET "/employees"
//    // prepare data
//    // send request
//    // assertion
//    @BeforeEach
//    void cleanRepository() {
//        employeeRepository.clearAll();
//        todoRepositoryInMongo.deleteAll();
//    }
//
//    @Test
//    void should_get_all_employees_when_perform_get_given_employees() throws Exception {
//        //given
//        Todo todo = new Todo(null, "Julia", 18, "Female", "1", 100000);
//        todoRepositoryInMongo.insert(todo);
//        // when
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
////        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", employee.getId()))
////        mockMvc.perform(MockMvcRequestBuilders.get("/employees/" + employee.getId()))
////        mockMvc.perform(MockMvcRequestBuilders.get("/employees?gender=" + "Male"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].id").value(todo.getId()))
//                .andExpect(jsonPath("$[0].name").value("Julia"))
//                .andExpect(jsonPath("$[0].age").value(18))
//                .andExpect(jsonPath("$[0].gender").value("Female"));
//        // then
//    }
//
//    @Test
//    void should_get_employee_by_id_when_perform_get_given_employee_id() throws Exception {
//        //given
//        Todo todo1 = new Todo(null, "Julia", 18, "Female","1", 100000);
//        todoRepositoryInMongo.insert(todo1);
//        Todo todo2 = new Todo(null, "Jason", 18, "Male", "2",100000);
//        todoRepositoryInMongo.insert(todo2);
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", todo2.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(todo2.getId()))
//                .andExpect(jsonPath("$.name").value("Jason"))
//                .andExpect(jsonPath("$.age").value(18))
//                .andExpect(jsonPath("$.gender").value("Male"));
//        //then
//    }
//
//    @Test
//    void should_get_employees_by_gender_when_perform_get_given_employee_gender() throws Exception {
//        //given
//        Todo todo1 = new Todo(null, "Julia", 18, "Female", "1",100000);
//        todoRepositoryInMongo.insert(todo1);
//        Todo todo2 = new Todo(null, "Jason", 18, "Male","2", 100000);
//        todoRepositoryInMongo.insert(todo2);
//        Todo todo3 = new Todo(null, "Gloria", 18, "Female", "3",100000);
//        todoRepositoryInMongo.insert(todo3);
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
//                    .param("gender", "Female"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(todo1.getId(), todo3.getId())))
//                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder("Julia", "Gloria")))
//                .andExpect(jsonPath("$[*].age").value(containsInAnyOrder(18, 18)))
//                .andExpect(jsonPath("$[*].gender").value(containsInAnyOrder("Female", "Female")));
//        //then
//    }
//
//    @Test
//    void should_get_employees_by_page_when_perform_get_given_page_and_page_size() throws Exception {
//        //given
//        Todo todo1 = new Todo(null, "Julia", 18, "Female", "1",100000);
//        todoRepositoryInMongo.insert(todo1);
//        Todo todo2 = new Todo(null, "Jason", 18, "Male", "2",100000);
//        todoRepositoryInMongo.insert(todo2);
//        Todo todo3 = new Todo(null, "Gloria", 18, "Female", "3",100000);
//        todoRepositoryInMongo.insert(todo3);
//        Todo todo4 = new Todo(null, "Johnson", 18, "Male", "2",100000);
//        todoRepositoryInMongo.insert(todo4);
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
//                .param("page", "2")
//                .param("pageSize", "2"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(todo3.getId(), todo4.getId())))
//                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder("Gloria", "Johnson")))
//                .andExpect(jsonPath("$[*].age").value(containsInAnyOrder(18, 18)))
//                .andExpect(jsonPath("$[*].gender").value(containsInAnyOrder("Female", "Male")));
//        //then
//    }
//
//    @Test
//    void should_return_employee_when_perform_post_given_employee() throws Exception {
//        //given
//        String employee = "{\n" +
//                "        \"name\": \"Koby\",\n" +
//                "        \"age\": 18,\n" +
//                "        \"gender\": \"Male\",\n" +
//                "        \"companyId\": \"3\",\n" +
//                "        \"salary\": 100000\n" +
//                "}";
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(employee))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value("Koby"))
//                .andExpect(jsonPath("$.age").value(18))
//                .andExpect(jsonPath("$.gender").value("Male"))
//                .andExpect(jsonPath("$.companyId").value("3"))
//                .andExpect(jsonPath("$.salary").value(100000));
//    }
//
//    @Test
//    void should_return_updated_employee_when_perform_put_given_employee_id() throws Exception {
//        //given
//        Todo todo1 = new Todo(null, "Julia", 18, "Female", "1",100000);
//        todoRepositoryInMongo.insert(todo1);
//        Todo todo2 = new Todo(null, "Jason", 18, "Male","2", 100000);
//        todoRepositoryInMongo.insert(todo2);
//        Todo todo3 = new Todo(null, "Gloria", 18, "Female", "3",100000);
//        todoRepositoryInMongo.insert(todo3);
//        String updatedEmployee = "{\n" +
//                "        \"age\": 30,\n" +
//                "        \"salary\": 500000\n" +
//                "}";
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", todo2.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(updatedEmployee))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(todo2.getId()))
//                .andExpect(jsonPath("$.name").value("Jason"))
//                .andExpect(jsonPath("$.age").value(30))
//                .andExpect(jsonPath("$.gender").value("Male"))
//                .andExpect(jsonPath("$.companyId").value("2"))
//                .andExpect(jsonPath("$.salary").value(500000));
//    }
//
//    @Test
//    void should_return_employee_when_perform_delete_given_employee_id() throws Exception {
//        //given
//        Todo todo1 = new Todo(null, "Julia", 18, "Female", "1",100000);
//        todoRepositoryInMongo.insert(todo1);
//        Todo todo2 = new Todo(null, "Jason", 18, "Male", "2",100000);
//        todoRepositoryInMongo.insert(todo2);
//        Todo todo3 = new Todo(null, "Gloria", 18, "Female","3", 100000);
//        todoRepositoryInMongo.insert(todo3);
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", todo2.getId()))
//                .andExpect(status().isNoContent());
//        assertEquals(2, todoRepositoryInMongo.findAll().size());
//    }
//}
//
