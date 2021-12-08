package com.afs.restapi;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.CompanyRepository;
import com.afs.restapi.repository.EmployeeRepository;
import com.afs.restapi.service.CompanyService;
import com.afs.restapi.service.EmployeeService;
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

//    @Test
//    void should_return_employee_by_id_when_find_by_id_given_employee_id() {
//        //given
//        Employee employee1 = new Employee(1, "Julia", 22, "Female", 100000);
//        Employee employee2 = new Employee(2, "Jason", 22, "Male", 100000);
//        Employee employee3 = new Employee(3, "Joanne", 22, "Female", 100000);
//
//        given(mockEmployeeRepository.findById(employee3.getId()))
//                .willReturn(employee3);
//
//        //when
//        Employee actual = employeeService.findById(employee3.getId());
//
//        //then
//        assertEquals(employee3, actual);
//    }
//
//    @Test
//    void should_return_employees_by_gender_when_find_by_gender_given_employee_gender() {
//        //given
//        List<Employee> femaleEmployees = new ArrayList<>();
//        Employee employee1 = new Employee(1, "Julia", 22, "Female", 100000);
//        Employee employee2 = new Employee(2, "Jason", 22, "Male", 100000);
//        Employee employee3 = new Employee(3, "Joanne", 22, "Female", 100000);
//        femaleEmployees.add(employee1);
//        femaleEmployees.add(employee3);
//
//        given(mockEmployeeRepository.findByGender("Female"))
//                .willReturn(femaleEmployees);
//
//        //when
//        List<Employee> actual = employeeService.findByGender("Female");
//
//        //then
//        assertEquals(femaleEmployees, actual);
//    }
//
//    @Test
//    void should_return_employees_by_page_when_find_by_page_given_page_and_page_size() {
//        //given
//        List<Employee> displayedEmployees = new ArrayList<>();
//        Employee employee1 = new Employee(1, "Julia", 22, "Female", 100000);
//        Employee employee2 = new Employee(2, "Jason", 22, "Male", 100000);
//        Employee employee3 = new Employee(3, "Joanne", 22, "Female", 100000);
//        Employee employee4 = new Employee(3, "John", 22, "Male", 100000);
//        displayedEmployees.add(employee3);
//        displayedEmployees.add(employee4);
//
//        given(mockEmployeeRepository.findByPage(2,2))
//                .willReturn(displayedEmployees);
//
//        //when
//        List<Employee> actual = employeeService.findByPage(2,2);
//
//        //then
//        assertEquals(displayedEmployees, actual);
//    }
//
//    @Test
//    void should_return_employee_when_create_employee_given_employee() {
//        //given
//        Employee employee = new Employee(1, "Julia", 22, "Female", 100000);
//
//        given(mockEmployeeRepository.create(employee))
//                .willReturn(employee);
//
//        //when
//        Employee actual = employeeService.create(employee);
//
//        //then
//        assertEquals(employee, actual);
//    }
//
//    @Test
//    void should_return_updated_employee_when_edit_employee_given_updated_employee() {
//        //given
//        Employee employee = new Employee(1, "Julia", 22, "Female", 100000);
//        Employee updatedEmployee = new Employee(1, "Julia", 25, "Female", 500000);
//        given(mockEmployeeRepository.findById(any()))
//                .willReturn(employee);
//        employee.setAge(updatedEmployee.getAge());
//        employee.setSalary(updatedEmployee.getSalary());
//        given(mockEmployeeRepository.save(any(), any(Employee.class)))
//                .willReturn(employee);
//
//        //when
//        Employee actual = employeeService.edit(employee.getId(), updatedEmployee);
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
//        given(mockEmployeeRepository.remove(employee1.getId()))
//                .willReturn(employee1);
//
//        //when
//        Employee actual = employeeService.remove(employee1.getId());
//
//        //then
//        assertEquals(employee1, actual);
//    }
}
