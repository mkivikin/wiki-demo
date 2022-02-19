package interview.wikicredit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WikiSummaryResponse {

    @JsonProperty("pageid")
    private Integer pageId;

    private String extract;

}
