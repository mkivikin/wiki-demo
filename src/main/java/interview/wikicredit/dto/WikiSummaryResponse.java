package interview.wikicredit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WikiSummaryResponse {

    @JsonProperty("pageid")
    private Integer pageId;

    private String extract;

}
