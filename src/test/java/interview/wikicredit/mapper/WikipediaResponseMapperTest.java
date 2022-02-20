package interview.wikicredit.mapper;

import interview.wikicredit.data.Company;
import interview.wikicredit.data.WikipediaData;
import interview.wikicredit.dto.WikiSummaryResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WikipediaResponseMapperTest {

    @Test
    void mergeResponseToWikipediaData_emptyData_returnsUpdatedData() {
        WikiSummaryResponse response = new WikiSummaryResponse();
        response.setPageId(123);
        response.setExtract("Random text from wikipedia");
        Company company = new Company();
        company.setId(1);
        company.setName("Random Company");
        WikipediaData data = new WikipediaData();
        data.setCompany(company);
        data.setArticleExists(false);

        data = WikipediaResponseMapper.INSTANCE.mergeResponseToWikipediaData(data, response);

        assertNotNull(data);
        assertEquals(response.getPageId(), data.getPageId());
        assertEquals(response.getExtract(), data.getSummary());
    }
}
