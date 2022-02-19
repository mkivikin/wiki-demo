package interview.wikicredit.mapper;

import interview.wikicredit.data.WikipediaData;
import interview.wikicredit.dto.WikiSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WikipediaResponseMapper {
    WikipediaResponseMapper INSTANCE = Mappers.getMapper(WikipediaResponseMapper.class);

    WikipediaData mergeResponseToWikipediaData(@MappingTarget WikipediaData data, WikiSummaryResponse response);
}
