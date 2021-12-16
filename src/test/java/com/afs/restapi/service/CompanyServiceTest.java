//package com.afs.restapi.service;
//
//import com.afs.restapi.entity.Todo;
////import com.afs.restapi.repository.CompanyRepositoryInMongo;
//import com.afs.restapi.repository.TodoRepositoryInMongo;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.willDoNothing;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(SpringExtension.class)
//public class CompanyServiceTest {
//    @Mock
//    CompanyRepository mockCompanyRepository;
//    @Mock
//    EmployeeRepository mockEmployeeRepository;
//    @Mock
//    CompanyRepositoryInMongo mockCompanyRepositoryInMongo;
//    @Mock
//    TodoRepositoryInMongo mockTodoRepositoryInMongo;
//    @InjectMocks
//    CompanyService companyService;
//
//    @Test
//    void should_return_all_companies_when_find_all_given_companies() {
//        //given
//        List<Company> companies = new ArrayList<>();
//        companies.add(new Company("1", "OOCL"));
//
//        given(mockCompanyRepositoryInMongo.findAll())
//                .willReturn(companies);
//
//        //when
//        List<Company> actual = companyService.findAll();
//
//        //then
//        assertEquals(companies, actual);
//        assertEquals(1, actual.size());
//    }
//
//    @Test
//    void should_return_company_by_id_when_find_by_id_given_company_id() {
//        //given
//        Company company = new Company("1", "OOCL");
//
//        given(mockCompanyRepositoryInMongo.findById(company.getId()))
//                .willReturn(java.util.Optional.of(company));
//
//        //when
//        Company actual = companyService.findById(company.getId());
//
//        //then
//        assertEquals(company, actual);
//        assertEquals(company.getName(), actual.getName());
//    }
//
//    @Test
//    void should_return_all_employees_in_company_when_find_all_employees_by_company_id_given_company_id_employees() {
//        //given
//        List<Todo> todos = Arrays.asList(new Todo("1", "Julia", 18, "Female","1", 100000),
//                new Todo("2", "Jason", 18, "Male","1", 100000),
//                new Todo("3", "Klaus", 18, "Male","1", 100000));
//        Company company = new Company("1", "OOCL");
//
//        given(mockTodoRepositoryInMongo.getEmployeesByCompanyId(company.getId()))
//                .willReturn(todos);
//
//        //when
//        List<Todo> actual = companyService.findAllEmployeesByCompanyId(company.getId());
//
//        //then
//        assertEquals(todos, actual);
//        assertEquals(3, actual.size());
//    }
//
//    @Test
//    void should_return_companies_by_page_when_find_by_page_given_page_and_page_size() {
//        //given
//        List<Company> companies = new ArrayList<>();
//        Company company1 = new Company("1", "OOCL");
//        Company company2 = new Company("2", "DHL");
//        Company company3 = new Company("3", "SF Express");
//        Company company4 = new Company("4", "Disney");
//        companies.add(company1);
//        companies.add(company2);
//        companies.add(company3);
//        companies.add(company4);
//
//        PageImpl<Company> returnedPage = new PageImpl<>(companies, PageRequest.of(2, 2), 1);
//
//        given(mockCompanyRepositoryInMongo.findAll(any(PageRequest.class)))
//                .willReturn(returnedPage);
//
//        //when
//        List<Company> actual = companyService.findByPage(2,2);
//
//        //then
//        assertEquals(returnedPage.getContent(), actual);
//    }
//
//    @Test
//    void should_return_company_when_create_company_given_company() {
//        //given
//        Company company = new Company("1", "OOCL");
//
//        given(mockCompanyRepositoryInMongo.insert(company))
//                .willReturn(company);
//
//        //when
//        Company actual = companyService.create(company);
//
//        //then
//        assertEquals(company, actual);
//    }
//
//    @Test
//    void should_return_updated_company_when_edit_company_given_updated_company() {
//        //given
//        Company company = new Company("1", "OOCL");
//        Company updatedCompany = new Company("1", "Disney");
//        given(mockCompanyRepositoryInMongo.findById(any()))
//                .willReturn(java.util.Optional.of(company));
//        company.setName(updatedCompany.getName());
//        company.setEmployees(updatedCompany.getEmployees());
//        given(mockCompanyRepositoryInMongo.save(any(Company.class)))
//                .willReturn(company);
//
//        //when
//        Company actual = companyService.edit(company.getId(), updatedCompany);
//
//        //then
//        assertEquals(company, actual);
//        assertEquals(company.getName(), actual.getName());
//    }
//
//    @Test
//    void should_return_company_when_delete_company_given_company_id() {
//        //given
//        Company company1 = new Company("1", "OOCL");
//
//        willDoNothing().given(mockCompanyRepositoryInMongo).deleteById(company1.getId());
//
//        //when
//        companyService.remove(company1.getId());
//
//        //then
//        verify(mockCompanyRepositoryInMongo).deleteById(company1.getId());
//        assertEquals(0, companyService.findAll().size());
//    }
//}
