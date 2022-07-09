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
import rpp2021.model.Liga;
import rpp2021.service.LigaService;

@RestController
public class LigaController {
	
	@Autowired
	private LigaService ligaService;
	
	@PostMapping("/liga")
	public ResponseEntity<String> addLiga(@RequestBody Liga liga) {
		try {
			String ligaId = ligaService.saveLiga(liga);
			return ResponseEntity.ok(ligaId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Unknown internal server error ocurred.");
		}
	}
	
	@GetMapping("/liga")
	public ResponseEntity<Liga> getLiga(@RequestParam String id) {
		try {
			Liga liga = ligaService.getLiga(id);
			return ResponseEntity.ok(liga);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}

	@GetMapping("/liga-all")
	public ResponseEntity<List<Liga>> getAllLiga() {
		try {
			List<Liga> lige = ligaService.getAllLiga();
			return ResponseEntity.ok(lige);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	@DeleteMapping("/liga")
	public ResponseEntity<String> removeIgrac(String ligaId) {
		try {
			boolean deleted = ligaService.removeLiga(ligaId);
			if (deleted) {
				return ResponseEntity.ok("Liga has been deleted.");
			} else {
				return ResponseEntity.badRequest().body("Liga has not been deleted.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Unknown internal server error ocurred.");
		}
	}
	

}