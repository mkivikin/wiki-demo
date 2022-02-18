package interview.wikicredit.mapper;

import interview.wikicredit.data.Company;
import interview.wikicredit.data.WikipediaData;
import interview.wikicredit.dto.WikipediaDataResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-17T21:51:27+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
public class WikipediaDataMapperImpl implements WikipediaDataMapper {

    @Override
    public WikipediaDataResponse wikipediaDataToResponse(WikipediaData data) {
        if ( data == null ) {
            return null;
        }

        WikipediaDataResponse wikipediaDataResponse = new WikipediaDataResponse();

        wikipediaDataResponse.setCompanyName( dataCompanyName( data ) );
        Integer id = dataCompanyId( data );
        if ( id != null ) {
            wikipediaDataResponse.setCompanyId( id.longValue() );
        }
        wikipediaDataResponse.setUpdatedAt( data.getUpdatedAt() );
        wikipediaDataResponse.setArticleExists( data.getArticleExists() );
        wikipediaDataResponse.setPageId( data.getPageId() );
        wikipediaDataResponse.setSummary( data.getSummary() );

        return wikipediaDataResponse;
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
