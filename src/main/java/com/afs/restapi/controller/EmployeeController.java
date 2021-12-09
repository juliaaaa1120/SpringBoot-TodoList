package com.afs.restapi.controller;

import com.afs.restapi.dto.EmployeeRequest;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.mapper.EmployeeMapper;
import com.afs.restapi.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        return employeeService.findById(id);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeesByGender(@RequestParam String gender) {
        return employeeService.findByGender(gender);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getEmployeesByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return employeeService.findByPage(page, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.create(employeeMapper.toEntity(employeeRequest));
    }

    @PutMapping("/{id}")
    public Employee editEmployee(@PathVariable String id, @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.edit(id, employeeMapper.toEntity(employeeRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Employee deleteEmployee(@PathVariable String id) {
        return employeeService.remove(id);
    }
}
