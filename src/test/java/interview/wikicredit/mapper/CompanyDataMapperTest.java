package interview.wikicredit.mapper;

import interview.wikicredit.data.Company;
import interview.wikicredit.data.WikipediaData;
import interview.wikicredit.dto.CompanyDataResponse;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class CompanyDataMapperTest {

    @Test
    void wikipediaDataToResponse_validData_returnsCompanyDataResponse(){
        Instant updatedAt = Instant.now();
        String companyName = "Swedbank";
        Company company = new Company();
        company.setId(1);
        company.setName(companyName);
        WikipediaData data = new WikipediaData();
        data.setCompany(company);
        data.setArticleExists(true);
        data.setPageId(123);
        data.setSummary("Fake summary");
        data.setUpdatedAt(updatedAt);

        CompanyDataResponse response = WikipediaDataMapper.INSTANCE.wikipediaDataToResponse(data);

        assertNotNull(response);
        assertEquals(1, response.getCompanyId());
        assertEquals(companyName, response.getCompanyName());
        assertEquals(true, response.getArticleExists());
        assertEquals(123, response.getPageId());
        assertEquals("Fake summary", response.getSummary());
        assertEquals(updatedAt, response.getUpdatedAt());
    }

}
