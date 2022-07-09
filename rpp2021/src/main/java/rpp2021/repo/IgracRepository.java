package rpp2021.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rpp2021.model.Igrac;

@Repository
public interface IgracRepository extends JpaRepository<Igrac, Integer>{
	
}
