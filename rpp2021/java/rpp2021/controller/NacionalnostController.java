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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import rpp2021.controller.model.LigaRequest;
import rpp2021.controller.model.NacionalnostRequest;
import rpp2021.model.Liga;
import rpp2021.model.Nacionalnost;
import rpp2021.service.NacionalnostService;

@CrossOrigin
@RestController
public class NacionalnostController {
	
	@Autowired
	private NacionalnostService nacionalnostService;
	
	@PostMapping("/nacionalnost")
	public ResponseEntity<Nacionalnost> addNacionalnost(@RequestBody NacionalnostRequest nacionalnost) {
		try {
			System.out.println("Nacionalnost data :: " + nacionalnost.getNaziv());
			Nacionalnost nacionalnostEntity = nacionalnostService.saveNacionalnost(nacionalnost);
			return ResponseEntity.ok(nacionalnostEntity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}

	}
	
	@GetMapping("/nacionalnost/{id}")
	public ResponseEntity<Nacionalnost> getNacionalnost(@PathVariable("id") String id) {
		try {
			Nacionalnost nacionalnost = nacionalnostService.getNacionalnost(id);
			return ResponseEntity.ok(nacionalnost);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	@GetMapping("/nacionalnost")
	public ResponseEntity<List<Nacionalnost>> getAllNacionalnost() {
		try {
			List<Nacionalnost> nacionalnost = nacionalnostService.getAllNacionalnost();
			return ResponseEntity.ok(nacionalnost);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	@PutMapping("/nacionalnost/{id}")
	public ResponseEntity<Nacionalnost> updateNacionalnost(@RequestBody NacionalnostRequest nacionalnost,
			@PathVariable("id")Integer id){
		Nacionalnost savedNacionalnost = nacionalnostService.updateNacionalnost(nacionalnost, id);
		if (savedNacionalnost == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok().body(savedNacionalnost);
	}


	@DeleteMapping("/nacionalnost/{id}")
	public ResponseEntity<String> removeNacionalnost(@PathVariable("id") String nacionalnostId) {
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
