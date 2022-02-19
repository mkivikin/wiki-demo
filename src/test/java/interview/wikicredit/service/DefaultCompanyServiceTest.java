package interview.wikicredit.service;

import interview.wikicredit.data.Company;
import interview.wikicredit.dto.CompanyRequest;
import interview.wikicredit.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class DefaultCompanyServiceTest {

    DefaultCompanyService service;

    @Mock
    CompanyRepository repository;

    @BeforeEach
    private void setUp(){
        service = new DefaultCompanyService(repository);
    }

    @Test
    void createCompany_validRequest_returnsCompany(){
        String companyName = "RandomCompany";
        CompanyRequest request = new CompanyRequest();
        request.setName(companyName);
        Company company = new Company();
        company.setId(123);
        company.setName(companyName);

        Mockito.when(repository.save(any(Company.class))).thenReturn(company);
        Company actualCompany = service.createCompany(request);

        assertEquals(company, actualCompany);
    }
}
