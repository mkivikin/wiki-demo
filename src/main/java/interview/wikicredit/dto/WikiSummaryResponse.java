package interview.wikicredit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WikiSummaryResponse {

    @NotNull
    @JsonProperty("pageid")
    private Integer pageId;

    @NotNull
    private String extract;

}
