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
import rpp2021.model.Tim;
import rpp2021.service.TimService;

@RestController
public class TimController {

	
	@Autowired
	private TimService timService;
	
	@PostMapping("/tim")
	public ResponseEntity<String> addTim(@RequestBody Tim tim) {
		try {
			String timId = timService.saveTim(tim);
			return ResponseEntity.ok(timId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Unknown internal server error ocurred.");
		}
	}
	
	@GetMapping("/tim")
	public ResponseEntity<Tim> getTim(@RequestParam String id) {
		try {
			Tim tim = timService.getTim(id);
			return ResponseEntity.ok(tim);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}

	@GetMapping("/tim-all")
	public ResponseEntity<List<Tim>> getAllTim() {
		try {
			List<Tim> tim = timService.getAllTim();
			return ResponseEntity.ok(tim);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	@DeleteMapping("/tim")
	public ResponseEntity<String> removeTim(String timId) {
		try {
			boolean deleted = timService.removeTim(timId);
			if (deleted) {
				return ResponseEntity.ok("Tim has been deleted.");
			} else {
				return ResponseEntity.badRequest().body("Tim has not been deleted.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Unknown internal server error ocurred.");
		}
	}
	
}
