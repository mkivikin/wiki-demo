package interview.wikicredit.controller;

import interview.wikicredit.dto.CompanyDataResponse;
import interview.wikicredit.mapper.WikipediaDataMapper;
import interview.wikicredit.service.CompanyDataService;
import interview.wikicredit.service.WikipediaCompanyDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wiki")
public class WikiLoadingController {

    private final CompanyDataService companyDataService;

    public WikiLoadingController(
        WikipediaCompanyDataService companyDataService) {
        this.companyDataService = companyDataService;
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDataResponse> getWikipediaData(@PathVariable Integer companyId) {
        return ResponseEntity.ok(WikipediaDataMapper.INSTANCE
            .wikipediaDataToResponse(companyDataService.getCompanyData(companyId)));
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyDataResponse> persistDataFromWikipedia(
        @PathVariable Integer companyId) {
        return ResponseEntity.ok(WikipediaDataMapper.INSTANCE
            .wikipediaDataToResponse(companyDataService.fetchCompanyDataFromWiki(companyId)));
    }


}
