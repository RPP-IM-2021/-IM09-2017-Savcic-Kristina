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
import rpp2021.model.Nacionalnost;
import rpp2021.service.NacionalnostService;

@RestController
public class NacionalnostController {
	
	@Autowired
	private NacionalnostService nacionalnostService;
	
	@PostMapping("/nacionalnost")
	public ResponseEntity<String> addNacionalnost(@RequestBody Nacionalnost nacionalnost) {
		try {
			String nacionalnostId = nacionalnostService.saveNacionalnost(nacionalnost);
			return ResponseEntity.ok(nacionalnostId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Unknown internal server error ocurred.");
		}
	}
	
	@GetMapping("/nacionalnost")
	public ResponseEntity<Nacionalnost> getNacionalnost(@RequestParam String id) {
		try {
			Nacionalnost nacionalnost = nacionalnostService.getNacionalnost(id);
			return ResponseEntity.ok(nacionalnost);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}

	@GetMapping("/nacionalnost-all")
	public ResponseEntity<List<Nacionalnost>> getAllNacionalnost() {
		try {
			List<Nacionalnost> nacionalnosti = nacionalnostService.getAllNacionalnost();
			return ResponseEntity.ok(nacionalnosti);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	@DeleteMapping("/nacionalnost")
	public ResponseEntity<String> removeNacionalnost(String nacionalnostId) {
		try {
			boolean deleted = nacionalnostService.removeNacionalnost(nacionalnostId);
			if (deleted) {
				return ResponseEntity.ok("Nacionalnost has been deleted.");
			} else {
				return ResponseEntity.badRequest().body("Nacionalnost has not been deleted.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Unknown internal server error ocurred.");
		}
	}
	

}
