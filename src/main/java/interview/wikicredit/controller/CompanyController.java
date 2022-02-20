package interview.wikicredit.controller;

import interview.wikicredit.dto.CompanyRequest;
import interview.wikicredit.dto.CompanyResponse;
import interview.wikicredit.mapper.CompanyMapper;
import interview.wikicredit.service.CompanyService;
import interview.wikicredit.service.DefaultCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody CompanyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(CompanyMapper.INSTANCE.companyToResponse(companyService.createCompany(request)));
    }
}
