package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public Employee getEmployeeById(@PathVariable Integer id) {
//        return employeeRepository.findById(id);
//    }
//
//    @GetMapping(params = {"gender"})
//    public List<Employee> getEmployeesByGender(@RequestParam String gender) {
//        return employeeRepository.findByGender(gender);
//    }
//
//    @GetMapping(params = {"page", "pageSize"})
//    public List<Employee> getEmployeesByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
//        return employeeRepository.findByPage(page, pageSize);
//    }
//
//    @PostMapping
//    public Employee createEmployee(@RequestBody Employee employee) {
//        return employeeRepository.create(employee);
//    }
//
//    @PutMapping("/{id}")
//    public Employee editEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
//        Employee employee = employeeRepository.findById(id);
//        if (employee.getAge() != null) {
//            employee.setAge(updatedEmployee.getAge());
//        }
//        if (employee.getSalary() != null) {
//            employee.setSalary(employee.getSalary());
//        }
//        return employeeRepository.save(id, updatedEmployee);
//    }
}
