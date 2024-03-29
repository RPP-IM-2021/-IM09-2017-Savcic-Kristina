package rpp2021.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021.model.Liga;

public interface LigaRepository extends JpaRepository<Liga, Integer>{

	Collection<Liga> findByNazivContainingIgnoreCase(String naziv);

}
