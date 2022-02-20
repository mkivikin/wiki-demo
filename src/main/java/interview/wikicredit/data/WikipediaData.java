package interview.wikicredit.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "wikipedia_data", schema = "WIKICREDIT")
public class WikipediaData {

    @Id
    @Column(name = "company_id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "article_exists")
    private Boolean articleExists;

    @Column(name = "page_id")
    private Integer pageId;

    private String summary;
}
