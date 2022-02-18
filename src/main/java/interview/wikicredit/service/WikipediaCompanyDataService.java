package interview.wikicredit.service;

import interview.wikicredit.data.Company;
import interview.wikicredit.data.WikipediaData;
import interview.wikicredit.dto.WikiSummaryResponse;
import interview.wikicredit.repository.WikipediaDataRepository;
import java.time.Instant;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
@Transactional
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
        WikipediaData data = company.getWikipediaData();
        if(data == null ) {
            data = new WikipediaData();
            data.setCompany(company);
        }
        data.setUpdatedAt(Instant.now());

        try {
            WikiSummaryResponse response = requestService.fetchSummaryFromWiki(company.getName());
            data.setPageId(response.getPageId());
            data.setSummary(response.getExtract());
            data.setArticleExists(true);
        } catch(WebClientException e) {
            data.setArticleExists(false);
        }

        return repository.save(data);
    }


}
