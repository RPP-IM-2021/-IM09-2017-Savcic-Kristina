package rpp2021.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021.model.Liga;

public interface LigaRepository extends JpaRepository<Liga, Integer>{

	List<Liga> findByNaziv(String naziv);
	
}
