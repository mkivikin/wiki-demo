package interview.wikicredit.dto;

import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;

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