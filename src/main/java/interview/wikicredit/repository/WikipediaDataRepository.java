package interview.wikicredit.repository;

import interview.wikicredit.data.WikipediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikipediaDataRepository extends JpaRepository<WikipediaData, Integer> {

}
