package rpp2021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import rpp2021.model.Igrac;
import rpp2021.model.Liga;
import rpp2021.model.Tim;
import rpp2021.service.IgracService;
import rpp2021.controller.model.IgracRequest;
import rpp2021.controller.model.LigaRequest;
import rpp2021.controller.model.TimRequest;

@CrossOrigin
@RestController
public class IgracController {

	@Autowired
	private IgracService igracService;
	
	@PostMapping("/igrac")
	public ResponseEntity<Igrac> addIgrac(@RequestBody IgracRequest igrac) {
		try {
			System.out.println("Igrac data :: " + igrac.getIme());
			Igrac igracEntity = igracService.saveIgrac(igrac);
			return ResponseEntity.ok(igracEntity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}
	
	@GetMapping("/igrac/{id}")
	public ResponseEntity<Igrac> getIgrac(@PathVariable("id") String id) {
		try {
			Igrac igrac = igracService.getIgrac(id);
			return ResponseEntity.ok(igrac);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}

	@GetMapping("/igrac")
	public ResponseEntity<List<Igrac>> getAllIgrac() {
		try {
			List<Igrac> igraci = igracService.getAllIgrac();
			return ResponseEntity.ok(igraci);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	@PutMapping("/igrac/{id}")
	public ResponseEntity<Igrac> updateIgrac(@RequestBody IgracRequest igrac,
			@PathVariable("id")Integer id){
		Igrac savedIgrac = igracService.updateIgrac(igrac, id);
		if (savedIgrac == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok().body(savedIgrac);
	}
	
	
	@DeleteMapping("/igrac/{id}")
	public ResponseEntity<String> removeIgrac(@PathVariable("id") String igracId) {
		try {
			boolean deleted = igracService.removeIgrac(igracId);
			if (deleted) {
				return ResponseEntity.ok("Igrac has been deleted.");
			} else {
				return ResponseEntity.badRequest().body("Igrac has not been deleted.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Unknown internal server error ocurred.");
		}
	}
}
