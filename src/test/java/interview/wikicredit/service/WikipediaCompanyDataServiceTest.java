package interview.wikicredit.service;

import interview.wikicredit.data.Company;
import interview.wikicredit.data.WikipediaData;
import interview.wikicredit.repository.WikipediaDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class WikipediaCompanyDataServiceTest {

    private WikipediaCompanyDataService service;

    @Mock
    private DefaultCompanyService companyService;

    @Mock
    WikipediaRequestService requestService;

    @Mock
    WikipediaDataRepository repository;

    @BeforeEach
    private void setUp(){
        service = new WikipediaCompanyDataService(requestService, companyService, repository);
    }

    @Test
    void getCompanyData_notFound_throwsEntityNotFoundException(){
        Integer companyId = 1;

        Mockito.when(repository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getCompanyData(companyId));
    }

    @Test
    void getCompanyData_found_ReturnsWikipediaData(){
        Integer companyId = 1;
        Company company = new Company();
        company.setId(companyId);
        company.setName("Zoolander Enterprises");
        WikipediaData data = new WikipediaData();
        data.setCompany(company);
        data.setSummary("But why male models?");
        data.setPageId(213);
        data.setUpdatedAt(Instant.now());
        data.setArticleExists(true);

        Mockito.when(repository.findById(companyId)).thenReturn(Optional.of(data));

        WikipediaData actualData = service.getCompanyData(companyId);

        assertEquals(data, actualData);
    }
}
