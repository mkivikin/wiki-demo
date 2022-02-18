package interview.wikicredit.mapper;

import interview.wikicredit.dto.CompanyRequest;
import interview.wikicredit.dto.CompanyResponse;
import interview.wikicredit.data.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyResponse companyToResponse(Company company);

    Company companyRequestToCompany(CompanyRequest company);
}
