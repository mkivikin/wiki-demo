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

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DefaultCompanyServiceTest {

    private DefaultCompanyService service;

    @Mock
    private CompanyRepository repository;

    @BeforeEach
    private void setUp() {
        service = new DefaultCompanyService(repository);
    }

    @Test
    void createCompany_validRequest_returnsCompany() {
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

    @Test
    void getCompanyById_notFound_throwsEntityNotFoundException() {
        Integer companyId = 1;

        Mockito.when(repository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getCompanyById(companyId));
    }

    @Test
    void getCompanyById_found_returnsCompany() {
        Integer companyId = 1;
        Company company = new Company();
        company.setId(companyId);
        company.setName("Swedbank");

        Mockito.when(repository.findById(companyId)).thenReturn(Optional.of(company));
        Company actualCompany = service.getCompanyById(companyId);

        assertEquals(company, actualCompany);
    }
}
