package interview.wikicredit.mapper;

import interview.wikicredit.data.Company;
import interview.wikicredit.data.WikipediaData;
import interview.wikicredit.dto.CompanyDataResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-19T13:27:07+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
public class CompanyDataMapperImpl implements CompanyDataMapper {

    @Override
    public CompanyDataResponse wikipediaDataToResponse(WikipediaData data) {
        if ( data == null ) {
            return null;
        }

        CompanyDataResponse companyDataResponse = new CompanyDataResponse();

        companyDataResponse.setCompanyName( dataCompanyName( data ) );
        Integer id = dataCompanyId( data );
        if ( id != null ) {
            companyDataResponse.setCompanyId( id.longValue() );
        }
        companyDataResponse.setUpdatedAt( data.getUpdatedAt() );
        companyDataResponse.setArticleExists( data.getArticleExists() );
        companyDataResponse.setPageId( data.getPageId() );
        companyDataResponse.setSummary( data.getSummary() );

        return companyDataResponse;
    }

    private String dataCompanyName(WikipediaData wikipediaData) {
        if ( wikipediaData == null ) {
            return null;
        }
        Company company = wikipediaData.getCompany();
        if ( company == null ) {
            return null;
        }
        String name = company.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer dataCompanyId(WikipediaData wikipediaData) {
        if ( wikipediaData == null ) {
            return null;
        }
        Company company = wikipediaData.getCompany();
        if ( company == null ) {
            return null;
        }
        Integer id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
