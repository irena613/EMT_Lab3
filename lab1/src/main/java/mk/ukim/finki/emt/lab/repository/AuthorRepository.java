package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.domain.Author;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaSpecificationRepository<Author,Long>{
}
