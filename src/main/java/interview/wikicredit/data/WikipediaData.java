package interview.wikicredit.data;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "wikipedia_data", schema = "WIKICREDIT")
public class WikipediaData {

    @Id
    @Column(name = "company_id")
    Integer id;

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
