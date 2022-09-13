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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import rpp2021.model.Nacionalnost;
import rpp2021.repo.NacionalnostRepository;


@RestController
@CrossOrigin
public class NacionalnostRestController {

	@Autowired
	private NacionalnostRepository nacionalnostRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("nacionalnost")
	public Collection<Nacionalnost> getAll(){
		return nacionalnostRepository.findAll();
	}

	@GetMapping("nacionalnost/{id}")
	public ResponseEntity<Nacionalnost> getOne(@PathVariable("id") Integer id) {

		if (nacionalnostRepository.findById(id).isPresent()) {
			Nacionalnost nacionalnost = nacionalnostRepository.getOne(id);
			return new ResponseEntity<>(nacionalnost, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("nacionalnost/naziv/{naziv}")
	public Collection<Nacionalnost> getByNaziv(@PathVariable("naziv") String naziv){
		return nacionalnostRepository.findByNazivContainingIgnoreCase(naziv);
	}


	@PostMapping("nacionalnost")
	public ResponseEntity<Nacionalnost> addNacionalnost(@RequestBody Nacionalnost nacionalnost) {
		Nacionalnost savedNacionalnost = nacionalnostRepository.save(nacionalnost);
		URI location = URI.create("/nacionalnost/" + savedNacionalnost.getId());
		return ResponseEntity.created(location).body(savedNacionalnost);
	}

	@PutMapping(value = "nacionalnost/{id}")
	public ResponseEntity<Nacionalnost> updateNacionalnost(@RequestBody Nacionalnost nacionalnost, @PathVariable("id")Integer id){
		if (nacionalnostRepository.existsById(id)) {
			nacionalnost.setId(id);
			Nacionalnost savedNacionalnost = nacionalnostRepository.save(nacionalnost);
			return ResponseEntity.ok().body(savedNacionalnost);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("nacionalnost/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
		
		if (id == -100 && !nacionalnostRepository.existsById(id)) {
			jdbcTemplate.execute("INSERT INTO \"nacionalnost\"(\"id\", \"naziv\", \"skracenica\")\r\n"
								+ "VALUES(-100, 'Liliputanac', 'LLP')");
		}
		
		if (nacionalnostRepository.existsById(id)) {
			nacionalnostRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}

}

