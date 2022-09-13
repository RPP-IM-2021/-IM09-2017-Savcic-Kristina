package rpp2021.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rpp2021.model.Liga;
import rpp2021.repo.LigaRepository;

@CrossOrigin
@RestController
public class LigaRestController {

	@Autowired
	private LigaRepository ligaRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("liga")
	public Collection<Liga> getAll(){
		return ligaRepository.findAll();
	}

	@GetMapping("liga/{id}")
	public ResponseEntity<Liga> getOne(@PathVariable("id") Integer id) {
		if (ligaRepository.findById(id).isPresent()) {
			Liga liga= ligaRepository.getOne(id);
			return new ResponseEntity<>(liga, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("liga/naziv/{naziv}")
	public Collection<Liga> getByNaziv(@PathVariable("naziv") String naziv){
		return ligaRepository.findByNazivContainingIgnoreCase(naziv);
	}

	@PostMapping("liga")
	public ResponseEntity<Liga> addLiga(@RequestBody Liga liga) {
		Liga savedLiga =  ligaRepository.save(liga);
		URI location = URI.create("/liga/" + savedLiga.getId());
		return ResponseEntity.created(location).body(savedLiga);
	}

	@PutMapping("liga/{id}")
	public ResponseEntity<Liga> updateLiga(@RequestBody Liga liga,
			@PathVariable("id")Integer id){
		if (ligaRepository.existsById(id)) {
			liga.setId(id);
			Liga savedLiga = ligaRepository.save(liga);
			return ResponseEntity.ok().body(savedLiga);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);


	}

	@DeleteMapping("liga/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
		
		if (id == -100 && !ligaRepository.existsById(id)) {
			jdbcTemplate.execute("INSERT INTO \"liga\"(\"id\", \"naziv\", \"oznaka\")\r\n"
					+ "VALUES(-100, 'Liga Za Brisanje', 'ZBL')");
		}
		
		if (ligaRepository.existsById(id)) {
			ligaRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);

	}

}

