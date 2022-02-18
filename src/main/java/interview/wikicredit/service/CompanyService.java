package interview.wikicredit.service;

import interview.wikicredit.data.Company;
import interview.wikicredit.dto.CompanyRequest;

public interface CompanyService {

    Company getCompanyById(Integer id);

    Company createCompany(CompanyRequest request);

}
