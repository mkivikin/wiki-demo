package interview.wikicredit.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class CompanyDataResponse {

    Long companyId;

    String companyName;

    Instant updatedAt;

    Boolean articleExists;

    Integer pageId;

    String summary;
}
