package interview.wikicredit.service;

import interview.wikicredit.data.Company;
import interview.wikicredit.data.WikipediaData;
import interview.wikicredit.dto.WikiSummaryResponse;
import interview.wikicredit.repository.WikipediaDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class WikipediaCompanyDataServiceTest {

    @Mock
    WikipediaRequestService requestService;
    @Mock
    WikipediaDataRepository repository;
    @Captor
    ArgumentCaptor<WikipediaData> wikiDataCaptor;
    private WikipediaCompanyDataService service;
    @Mock
    private DefaultCompanyService companyService;

    @BeforeEach
    private void setUp() {
        service = new WikipediaCompanyDataService(requestService, companyService, repository);
    }

    @Test
    void getCompanyData_notFound_throwsEntityNotFoundException() {
        Integer companyId = 1;

        Mockito.when(repository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getCompanyData(companyId));
    }

    @Test
    void getCompanyData_found_ReturnsWikipediaData() {
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

    @Test
    void getExistingCompanyWikipediaData_noDataPresent_newDataCreated() {
        Company company = new Company();
        company.setId(123);
        company.setName("East Empire Company");

        WikipediaData data = service.getExistingCompanyWikiData(company);

        assertEquals(company, data.getCompany());
        assertEquals(false, data.getArticleExists());
    }

    @Test
    void getExistingCompanyWikipediaData_dataPresent_dataReturned() {
        Company company = new Company();
        company.setId(123);
        company.setName("East Empire Company");
        WikipediaData data = new WikipediaData();
        data.setArticleExists(true);
        data.setSummary("Random trading company summary");
        data.setUpdatedAt(Instant.now());
        data.setPageId(56565);
        data.setCompany(company);
        company.setWikipediaData(data);

        WikipediaData actualData = service.getExistingCompanyWikiData(company);

        assertEquals(data, actualData);
    }

    @Test
    void validateWikipediaResponse_noResponse_throwsValidationException() {
        assertThrows(ValidationException.class, () -> service.validateWikipediaResponse(null));
    }

    @Test
    void validateWikipediaResponse_noExtract_throwsValidationException() {
        WikiSummaryResponse response = new WikiSummaryResponse();
        response.setPageId(123);

        assertThrows(ValidationException.class, () -> service.validateWikipediaResponse(response));
    }

    @Test
    void validateWikipediaResponse_noPageId_throwsValidationException() {
        WikiSummaryResponse response = new WikiSummaryResponse();
        response.setExtract("Fake test extract");

        assertThrows(ValidationException.class, () -> service.validateWikipediaResponse(response));
    }

    @Test
    void fetchCompanyDataFromWiki_responsePresent_dataReturned() {
        Integer companyId = 1;
        Company company = new Company();
        company.setId(companyId);
        company.setName("Empty Co.");
        WikiSummaryResponse response = new WikiSummaryResponse();
        response.setExtract("Empty Co. is empty inside.");
        response.setPageId(1337);

        Mockito.when(companyService.getCompanyById(companyId)).thenReturn(company);
        Mockito.when(requestService.fetchSummaryFromWiki(company.getName())).thenReturn(response);
        Mockito.when(repository.save(any(WikipediaData.class))).thenAnswer(i -> i.getArgument(0));
        WikipediaData data = service.fetchCompanyDataFromWiki(companyId);

        assertEquals(response.getExtract(), data.getSummary());
        assertEquals(response.getPageId(), data.getPageId());
        assertEquals(true, data.getArticleExists());
        assertNotNull(data.getUpdatedAt());
    }

}
