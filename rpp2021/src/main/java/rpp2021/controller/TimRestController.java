package rpp2021.controller;

import java.net.URI;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import rpp2021.model.Tim;
import rpp2021.repo.TimRepository;

@CrossOrigin
@RestController
public class TimRestController {

	@Autowired
	private TimRepository timRepository;

	@ApiOperation(value = "Returns collection of all Tim from database")
	@GetMapping("tim")
	public Collection<Tim> getAllTim(){
		return timRepository.findAll();
	}

	@ApiOperation(value = "Returns Tim with id that was forwarded as path variable")
	@GetMapping("tim/{id}")
	public ResponseEntity<Tim> getTim(@PathVariable("id") Integer id){
		if (timRepository.findById(id).isPresent()) {
			Tim tim = timRepository.getOne(id);
			return new ResponseEntity<>(tim, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

	@PostMapping("tim")
	public ResponseEntity<Tim> addOne(@RequestBody Tim tim){
		Tim savedTim = timRepository.save(tim);
		URI location = URI.create("/tim/" + savedTim.getId());
		return ResponseEntity.created(location).body(savedTim);
	}

	@PutMapping("tim/{id}")
	public ResponseEntity<Tim> update(@PathVariable("id") Integer id,
			@RequestBody Tim tim){
		if(timRepository.existsById(id)) {
			tim.setId(id);
			Tim savedTim = timRepository.save(tim);
			return ResponseEntity.ok().body(savedTim);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("tim/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
		if (timRepository.existsById(id)) {
			timRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}

}
