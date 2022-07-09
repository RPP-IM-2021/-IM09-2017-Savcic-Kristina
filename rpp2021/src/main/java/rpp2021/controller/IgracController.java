package rpp2021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import rpp2021.model.Igrac;
import rpp2021.service.IgracService;

@RestController
public class IgracController {

	@Autowired
	private IgracService igracService;
	
	@PostMapping("/igrac")
	public ResponseEntity<String> addIgrac(@RequestBody Igrac igrac) {
		try {
			String igracId = igracService.saveIgrac(igrac);
			return ResponseEntity.ok(igracId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Unknown internal server error ocurred.");
		}
	}
	
	@GetMapping("/igrac")
	public ResponseEntity<Igrac> getIgrac(@RequestParam String id) {
		try {
			Igrac igrac = igracService.getIgrac(id);
			return ResponseEntity.ok(igrac);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}

	@GetMapping("/igrac-all")
	public ResponseEntity<List<Igrac>> getAllIgrac() {
		try {
			List<Igrac> igraci = igracService.getAllIgrac();
			return ResponseEntity.ok(igraci);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	@DeleteMapping("/igrac")
	public ResponseEntity<String> removeIgrac(String igracId) {
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
