package rpp2021.repo;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import rpp2021.model.Liga;
import rpp2021.model.Tim;

public interface TimRepository extends JpaRepository<Tim, Integer>{

	Collection<Tim> findByLiga(Liga liga);
	Collection<Tim> findByIdLessThanOrderById(Integer id);
	
}
