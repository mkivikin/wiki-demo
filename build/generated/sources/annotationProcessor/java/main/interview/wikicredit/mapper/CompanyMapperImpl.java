package interview.wikicredit.mapper;

import interview.wikicredit.data.Company;
import interview.wikicredit.dto.CompanyRequest;
import interview.wikicredit.dto.CompanyResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-19T13:27:07+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyResponse companyToResponse(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setName( company.getName() );

        return companyResponse;
    }

    @Override
    public Company companyRequestToCompany(CompanyRequest company) {
        if ( company == null ) {
            return null;
        }

        Company company1 = new Company();

        company1.setName( company.getName() );

        return company1;
    }
}
