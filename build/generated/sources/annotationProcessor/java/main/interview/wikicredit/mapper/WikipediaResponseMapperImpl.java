package interview.wikicredit.mapper;

import interview.wikicredit.data.WikipediaData;
import interview.wikicredit.dto.WikiSummaryResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-19T13:48:40+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
public class WikipediaResponseMapperImpl implements WikipediaResponseMapper {

    @Override
    public WikipediaData mergeResponseToWikipediaData(WikipediaData data, WikiSummaryResponse response) {
        if ( response == null ) {
            return null;
        }

        data.setPageId( response.getPageId() );

        return data;
    }
}
