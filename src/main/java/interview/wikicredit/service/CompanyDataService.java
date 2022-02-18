package interview.wikicredit.service;

import interview.wikicredit.data.WikipediaData;

public interface CompanyDataService {

    WikipediaData getCompanyData(Integer companyId);

    WikipediaData fetchCompanyDataFromWiki(Integer companyId);
}
