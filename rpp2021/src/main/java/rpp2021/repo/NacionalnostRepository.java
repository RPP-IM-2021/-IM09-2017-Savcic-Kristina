package rpp2021.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import rpp2021.model.Nacionalnost;

public interface NacionalnostRepository extends JpaRepository<Nacionalnost, Integer> {

	Collection<Nacionalnost> findByNazivContainingIgnoreCase(String naziv);

}
