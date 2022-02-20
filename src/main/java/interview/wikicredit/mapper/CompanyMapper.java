package interview.wikicredit.mapper;

import interview.wikicredit.data.Company;
import interview.wikicredit.dto.CompanyRequest;
import interview.wikicredit.dto.CompanyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(target = "summary", source = "wikipediaData.summary")
    CompanyResponse companyToResponse(Company company);

    Company companyRequestToCompany(CompanyRequest company);
}
