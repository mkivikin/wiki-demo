package interview.wikicredit.mapper;

import interview.wikicredit.data.WikipediaData;
import interview.wikicredit.dto.CompanyDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyDataMapper {
    CompanyDataMapper INSTANCE = Mappers.getMapper(CompanyDataMapper.class);

    @Mapping(source = "company.name", target = "companyName")
    @Mapping(source = "company.id", target = "companyId")
    CompanyDataResponse wikipediaDataToResponse(WikipediaData data);
}
