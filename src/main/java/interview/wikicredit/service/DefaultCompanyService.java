package interview.wikicredit.service;

import interview.wikicredit.data.Company;
import interview.wikicredit.dto.CompanyRequest;
import interview.wikicredit.mapper.CompanyMapper;
import interview.wikicredit.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class DefaultCompanyService implements CompanyService {

    private final CompanyRepository repository;

    public DefaultCompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Company getCompanyById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(String.format("Company with id %d not found", id)));
    }

    @Override
    public Company createCompany(CompanyRequest request) {
        return repository.save(CompanyMapper.INSTANCE.companyRequestToCompany(request));
    }


}
