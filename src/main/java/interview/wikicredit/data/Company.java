package interview.wikicredit.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "company", schema = "WIKICREDIT")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @NotNull
    String name;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    WikipediaData wikipediaData;
}
