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

import rpp2021.model.Igrac;
import rpp2021.repo.IgracRepository;

@CrossOrigin
@RestController
public class IgracRestController {

	@Autowired
	private IgracRepository igracRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("igrac")
	public Collection<Igrac> getAll(){
		return igracRepository.findAll();
	}

	@GetMapping("igrac/{id}")
	public ResponseEntity<Igrac> getOne(@PathVariable("id") Integer id){
		if(igracRepository.findById(id).isPresent()) {
			Igrac savedIgrac = igracRepository.getOne(id);
			return new ResponseEntity<>(savedIgrac, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping("igrac")
	public ResponseEntity<Igrac> addOne(@RequestBody Igrac igrac){
		igrac.setBrojReg(
				igracRepository.nextRBR(igrac.getTim().getId()));
		Igrac savedIgrac = igracRepository.save(igrac);
		URI location = URI.create("/igrac/" + savedIgrac.getId());
		return ResponseEntity.created(location).body(savedIgrac);
	}

	@PutMapping("igrac/{id}")
	public ResponseEntity<Igrac> update(@PathVariable("id") Integer id,
			@RequestBody Igrac igrac){
		if(igracRepository.existsById(id)) {
			igrac.setId(id);
			Igrac savedIgrac = igracRepository.save(igrac);
			return ResponseEntity.ok().body(savedIgrac);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("igrac/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id){

		if (id == -100 && !igracRepository.existsById(id)) {
			jdbcTemplate.execute("INSERT INTO igrac "
					+ "(\"id\", \"tim\", \"broj_reg\", \"nacionalnost\", \"prezime\", \"ime\", \"datum_rodjenja\") "
					+ "VALUES (-100, 3, 4, 3, 'Majkl', 'Majklovski', to_date('11.03.1993.', 'dd.mm.yyyy.'))");
		}

		if(igracRepository.existsById(id)) {
			igracRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);

	}



}