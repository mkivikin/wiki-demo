package interview.wikicredit.mapper;

import interview.wikicredit.data.Company;
import interview.wikicredit.data.WikipediaData;
import interview.wikicredit.dto.CompanyRequest;
import interview.wikicredit.dto.CompanyResponse;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class CompanyMapperTest {

    @Test
    void companyRequestToCompany_validRequest_returnsCompany(){
        String companyName = "Swedbank";
        CompanyRequest request = new CompanyRequest();
        request.setName(companyName);

        Company company = CompanyMapper.INSTANCE.companyRequestToCompany(request);

        assertNotNull(company);
        assertEquals(companyName, company.getName());
    }

    @Test
    void companyToResponse_companyWithoutWikiData_returnsCompanyResponse(){
        String companyName = "Swedbank";
        Company company = new Company();
        company.setName(companyName);

        CompanyResponse response = CompanyMapper.INSTANCE.companyToResponse(company);

        assertNotNull(response);
        assertEquals(companyName, response.getName());
        assertNull(response.getSummary());
    }

    @Test
    void companyToResponse_companyWithWikiData_returnsCompanyResponse(){
        String companyName = "Swedbank";
        Company company = new Company();
        company.setName(companyName);
        WikipediaData data = new WikipediaData();
        data.setCompany(company);
        data.setSummary("Fake summary");
        company.setWikipediaData(data);

        CompanyResponse response = CompanyMapper.INSTANCE.companyToResponse(company);

        assertNotNull(response);
        assertEquals(companyName, response.getName());
        assertEquals(data.getSummary(), response.getSummary());
    }
}
