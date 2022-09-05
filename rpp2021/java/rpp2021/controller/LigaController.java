package rpp2021.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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

import org.springframework.web.bind.annotation.RequestBody ;
import rpp2021.model.Liga;
import rpp2021.service.LigaService;
import rpp2021.controller.model.LigaRequest;

@CrossOrigin
@RestController
public class LigaController {
	
	@Autowired
	private LigaService ligaService;
	
	@PostMapping("liga")
	public ResponseEntity<Liga> addLiga(@RequestBody LigaRequest liga) {
		try {
			System.out.println("Liga data :: " + liga.getNaziv());
			Liga ligaEntity = ligaService.saveLiga(liga);
			return ResponseEntity.ok(ligaEntity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}
	
	@GetMapping("/liga/{id}")
	public ResponseEntity<Liga> getLiga(@PathVariable("id") String id) {
		try {
			Liga liga = ligaService.getLiga(id);
			return ResponseEntity.ok(liga);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}

	@GetMapping("/liga")
	public ResponseEntity<List<Liga>> getAllLiga() {
		try {
			List<Liga> lige = ligaService.getAllLiga();
			return ResponseEntity.ok(lige);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	@PutMapping("/liga/{id}")
	public ResponseEntity<Liga> updateLiga(@RequestBody LigaRequest liga,
			@PathVariable("id")Integer id){
		Liga savedLiga = ligaService.updateLiga(liga, id);
		if (savedLiga == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok().body(savedLiga);
	}
	
	@DeleteMapping("/liga/{id}")
	public ResponseEntity<String> removeIgrac(@PathVariable("id") String ligaId) {
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