package interview.wikicredit.controller;

import interview.wikicredit.dto.WikipediaDataResponse;
import interview.wikicredit.mapper.WikipediaDataMapper;
import interview.wikicredit.service.CompanyDataService;
import interview.wikicredit.service.WikipediaCompanyDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wiki")
public class WikiLoadingController {

    private final CompanyDataService companyDataService;

    public WikiLoadingController(
        WikipediaCompanyDataService companyDataService) {
        this.companyDataService = companyDataService;
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<WikipediaDataResponse> getWikipediaData(@PathVariable Integer companyId) {
        return ResponseEntity.ok(WikipediaDataMapper.INSTANCE
            .wikipediaDataToResponse(companyDataService.getCompanyData(companyId)));
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<WikipediaDataResponse> persistDataFromWikipedia(
        @PathVariable Integer companyId) {
        return ResponseEntity.ok(WikipediaDataMapper.INSTANCE
            .wikipediaDataToResponse(companyDataService.fetchCompanyDataFromWiki(companyId)));
    }


}