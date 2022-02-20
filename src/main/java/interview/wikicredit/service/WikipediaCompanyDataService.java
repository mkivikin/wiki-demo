package interview.wikicredit.service;

import interview.wikicredit.data.Company;
import interview.wikicredit.data.WikipediaData;
import interview.wikicredit.dto.WikiSummaryResponse;
import interview.wikicredit.mapper.WikipediaResponseMapper;
import interview.wikicredit.repository.WikipediaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.Instant;

@Service
@Transactional
@Slf4j
public class WikipediaCompanyDataService implements CompanyDataService {

    private final WikipediaRequestService requestService;
    private final CompanyService companyService;
    private final WikipediaDataRepository repository;

    public WikipediaCompanyDataService(
        WikipediaRequestService requestService,
        DefaultCompanyService companyService,
        WikipediaDataRepository repository) {
        this.requestService = requestService;
        this.companyService = companyService;
        this.repository = repository;
    }

    @Override
    public WikipediaData getCompanyData(Integer companyId) {
        return repository.findById(companyId).orElseThrow(() -> new EntityNotFoundException(
            String.format("Company id %d wikipedia data not found", companyId)));
    }

    @Override
    public WikipediaData fetchCompanyDataFromWiki(Integer companyId) {
        Company company = companyService.getCompanyById(companyId);
        WikipediaData data = getExistingCompanyWikiData(company);

        try {
            WikiSummaryResponse response = requestService.fetchSummaryFromWiki(company.getName());
            validateWikipediaResponse(response);
            data = WikipediaResponseMapper.INSTANCE.mergeResponseToWikipediaData(data, response);
            data.setArticleExists(true);
        } catch (WebClientException e) {
            data.setArticleExists(false);
            log.warn("Failed to fetch company data from wikipedia", e);
        }
        data.setUpdatedAt(Instant.now());

        return repository.save(data);
    }

    public void validateWikipediaResponse(WikiSummaryResponse response) {
        if (response == null) {
            throw new ValidationException("Response is missing.");
        }
        if (response.getExtract() == null) {
            throw new ValidationException("Extract is missing from response.");
        }
        if (response.getPageId() == null) {
            throw new ValidationException("PageId is missing from response.");
        }

    }

    public WikipediaData getExistingCompanyWikiData(Company company) {
        WikipediaData data = company.getWikipediaData();
        if (data == null) {
            data = new WikipediaData();
            data.setCompany(company);
            data.setArticleExists(false);
        }

        return data;
    }


}
