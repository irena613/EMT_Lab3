package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.domain.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaSpecificationRepository<Country,Long>{
}
