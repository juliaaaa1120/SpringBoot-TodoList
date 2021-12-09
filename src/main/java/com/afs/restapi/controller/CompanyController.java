//package com.afs.restapi.controller;
//
//import com.afs.restapi.dto.CompanyRequest;
//import com.afs.restapi.dto.CompanyResponse;
//import com.afs.restapi.dto.EmployeeResponse;
//import com.afs.restapi.entity.Company;
//import com.afs.restapi.entity.Employee;
//import com.afs.restapi.mapper.CompanyMapper;
//import com.afs.restapi.mapper.EmployeeMapper;
//import com.afs.restapi.service.CompanyService;
//import com.afs.restapi.service.EmployeeService;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("companies")
//public class CompanyController {
//    private final CompanyService companyService;
//    private final CompanyMapper companyMapper;
//    private final EmployeeMapper employeeMapper;
//
//    public CompanyController(CompanyService companyService, CompanyMapper companyMapper, EmployeeMapper employeeMapper) {
//        this.companyService = companyService;
//        this.companyMapper = companyMapper;
//        this.employeeMapper = employeeMapper;
//    }
//
//    @GetMapping
//    public List<Company> getAllCompanies() {
//        return companyService.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public CompanyResponse getCompanyById(@PathVariable String id) {
//        List<Employee> employees = companyService.findAllEmployeesByCompanyId(id);
//        return companyMapper.toResponse(companyService.findById(id), employees);
//    }
//
//    @GetMapping("/{id}/employees")
//    public List<EmployeeResponse> getAllEmployeesByCompanyId(@PathVariable String id) {
//        return companyService.findAllEmployeesByCompanyId(id).stream()
//                .map(employeeMapper::toResponse)
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping(params = {"page", "pageSize"})
//    public List<Company> getCompaniesByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
//        return companyService.findByPage(page, pageSize);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Company createCompany(@RequestBody CompanyRequest companyRequest) {
//        return companyService.create(companyMapper.toEntity(companyRequest));
//    }
//
//    @PutMapping("/{id}")
//    public Company editCompany(@PathVariable String id, @RequestBody CompanyRequest companyRequest) {
//        return companyService.edit(id, companyMapper.toEntity(companyRequest));
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteCompany(@PathVariable String id) {
//        companyService.remove(id);
//    }
//}
