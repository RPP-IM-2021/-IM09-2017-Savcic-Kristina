package rpp2021.repo;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rpp2021.model.Tim;
import rpp2021.model.Igrac;

public interface IgracRepository extends JpaRepository<Igrac, Integer>{

	Collection<Igrac> findByTim(Tim tim);
	Collection<Igrac> findByImeLessThanOrderById(BigDecimal ime);

	@Query(value = "select coalesce(max(broj_reg)+1, 1) from igrac where igrac.tim = ?1", nativeQuery = true)
	Integer nextRBR(Integer timId);

}
