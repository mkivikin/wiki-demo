package interview.wikicredit.controller;

import interview.wikicredit.dto.CompanyRequest;
import interview.wikicredit.mapper.CompanyMapper;
import interview.wikicredit.dto.CompanyResponse;
import interview.wikicredit.service.CompanyService;
import interview.wikicredit.service.DefaultCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(DefaultCompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Integer companyId) {
        return ResponseEntity
            .ok(CompanyMapper.INSTANCE.companyToResponse(companyService.getCompanyById(companyId)));
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(CompanyMapper.INSTANCE.companyToResponse(companyService.createCompany(request)));
    }
}
