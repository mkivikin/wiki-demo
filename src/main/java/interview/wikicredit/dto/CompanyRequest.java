package interview.wikicredit.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CompanyRequest {

    @NotNull
    String name;

}
