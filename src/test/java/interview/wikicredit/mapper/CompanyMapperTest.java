package interview.wikicredit.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import interview.wikicredit.data.Company;
import interview.wikicredit.dto.CompanyRequest;
import org.junit.jupiter.api.Test;

class CompanyMapperTest {

    @Test
    void companyRequestToCompany_validRequest_returnsCompany(){
        String companyName = "Swedbank";
        CompanyRequest request = new CompanyRequest();
        request.setName(companyName);

        Company company = CompanyMapper.INSTANCE.companyRequestToCompany(request);

        assertNotEquals(null, company);
        assertEquals(companyName, company.getName());
    }
}
