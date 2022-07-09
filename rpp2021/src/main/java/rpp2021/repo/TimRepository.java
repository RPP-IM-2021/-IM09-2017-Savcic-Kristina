package rpp2021.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rpp2021.model.Tim;

@Repository
public interface TimRepository extends JpaRepository <Tim, Integer> {

}
