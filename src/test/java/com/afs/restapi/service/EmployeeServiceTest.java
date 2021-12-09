package com.afs.restapi.service;

import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.EmployeeRepository;
import com.afs.restapi.repository.EmployeeRepositoryInMongo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository mockEmployeeRepository;
    @Mock
    EmployeeRepositoryInMongo mockEmployeeRepositoryInMongo;
    @InjectMocks
    EmployeeService employeeService;

    @Test
    void should_return_all_employees_when_find_all_given_employees() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1", "Julia", 22, "Female","1", 100000));
        given(mockEmployeeRepositoryInMongo.findAll())
                .willReturn(employees);

        //when
        List<Employee> actual = employeeService.findAll();

        //then
        assertEquals(employees, actual);
    }

    @Test
    void should_return_employee_by_id_when_find_by_id_given_employee_id() {
        //given
        Employee employee1 = new Employee("1", "Julia", 22, "Female", "1", 100000);
        Employee employee2 = new Employee("2", "Jason", 22, "Male", "1", 100000);
        Employee employee3 = new Employee("3", "Joanne", 22, "Female", "1",100000);

        given(mockEmployeeRepositoryInMongo.findById(employee3.getId()))
                .willReturn(java.util.Optional.of(employee3));

        //when
        Employee actual = employeeService.findById(employee3.getId());

        //then
        assertEquals(employee3, actual);
    }

    @Test
    void should_return_employees_by_gender_when_find_by_gender_given_employee_gender() {
        //given
        List<Employee> femaleEmployees = new ArrayList<>();
        Employee employee1 = new Employee("1", "Julia", 22, "Female", "1", 100000);
        Employee employee2 = new Employee("2", "Jason", 22, "Male", "1", 100000);
        Employee employee3 = new Employee("3", "Joanne", 22, "Female", "1", 100000);
        femaleEmployees.add(employee1);
        femaleEmployees.add(employee3);

        given(mockEmployeeRepositoryInMongo.findByGender("Female"))
                .willReturn(femaleEmployees);

        //when
        List<Employee> actual = employeeService.findByGender("Female");

        //then
        assertEquals(femaleEmployees, actual);
    }

    @Test
    void should_return_employees_by_page_when_find_by_page_given_page_and_page_size() {
        //given
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee("1", "Julia", 22, "Female", "1", 100000);
        Employee employee2 = new Employee("2", "Jason", 22, "Male", "1", 100000);
        Employee employee3 = new Employee("3", "Joanne", 22, "Female", "1", 100000);
        Employee employee4 = new Employee("3", "John", 22, "Male", "1", 100000);
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);

        PageImpl<Employee> returnedPage = new PageImpl<>(employees, PageRequest.of(2, 2), 1);

        given(mockEmployeeRepositoryInMongo.findAll(any(PageRequest.class)))
                .willReturn(returnedPage);

        //when
        List<Employee> actual = employeeService.findByPage(2,2);

        //then
        assertEquals(returnedPage.getContent(), actual);
    }

    @Test
    void should_return_employee_when_create_employee_given_employee() {
        //given
        Employee employee = new Employee("1", "Julia", 22, "Female", "1", 100000);

        given(mockEmployeeRepositoryInMongo.insert(employee))
                .willReturn(employee);

        //when
        Employee actual = employeeService.create(employee);

        //then
        assertEquals(employee, actual);
        assertEquals(employee.getId(), actual.getId());
        assertEquals(employee.getName(), actual.getName());
        assertEquals(employee.getAge(), actual.getAge());
        assertEquals(employee.getGender(), actual.getGender());
        assertEquals(employee.getCompanyId(), actual.getCompanyId());
        assertEquals(employee.getSalary(), actual.getSalary());
    }

    @Test
    void should_return_updated_employee_when_edit_employee_given_updated_employee() {
        //given
        Employee employee = new Employee("1", "Julia", 22, "Female", "1", 100000);
        Employee updatedEmployee = new Employee("1", "Julia", 25, "Female", "1", 500000);
        given(mockEmployeeRepositoryInMongo.findById(any()))
                .willReturn(java.util.Optional.of(employee));
        employee.setAge(updatedEmployee.getAge());
        employee.setSalary(updatedEmployee.getSalary());
        given(mockEmployeeRepositoryInMongo.save(any(Employee.class)))
                .willReturn(employee);

        //when
        Employee actual = employeeService.edit(employee.getId(), updatedEmployee);

        //then
        assertEquals(employee, actual);
        assertEquals(employee.getId(), actual.getId());
        assertEquals(employee.getName(), actual.getName());
        assertEquals(employee.getAge(), actual.getAge());
        assertEquals(employee.getGender(), actual.getGender());
        assertEquals(employee.getCompanyId(), actual.getCompanyId());
        assertEquals(employee.getSalary(), actual.getSalary());
    }

    @Test
    void should_return_employee_when_delete_employee_given_employee_id() {
        //given
        Employee employee1 = new Employee("1", "Julia", 22, "Female", "1", 100000);
        Employee employee2 = new Employee("2", "Jason", 22, "Male", "1", 100000);

        willDoNothing().given(mockEmployeeRepositoryInMongo).deleteById(employee1.getId());

        //when
        employeeService.remove(employee1.getId());

        //then
        verify(mockEmployeeRepositoryInMongo).deleteById(employee1.getId());
    }
}
