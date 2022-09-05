package rpp2021.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;

import rpp2021.controller.model.LigaRequest;
import rpp2021.controller.model.TimRequest;
import rpp2021.model.Liga;
import rpp2021.model.Tim;
import rpp2021.service.TimService;
import rpp2021.repo.TimRepository;
import rpp2021.repo.LigaRepository;


@CrossOrigin
@RestController
public class TimController {
	
	@Autowired
	private TimService timService;
	
	@PostMapping("/tim")
	public ResponseEntity<Tim> addTim(@RequestBody TimRequest tim) {
		try {
			Tim timEntity = timService.saveTim(tim);
			return ResponseEntity.ok(timEntity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}
	
	@GetMapping("/tim/{id}")
	public ResponseEntity<Tim> getTim(@RequestParam String id) {
		try {
			Tim tim = timService.getTim(id);
			return ResponseEntity.ok(tim);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}

	@GetMapping("/tim")
	public ResponseEntity<List<Tim>> getAllTim() {
		try {
			List<Tim> tim = timService.getAllTim();
			return ResponseEntity.ok(tim);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	@PutMapping("/tim/{id}")
	public ResponseEntity<Tim> updateLiga(@RequestBody TimRequest tim,
			@PathVariable("id")Integer id){
		Tim savedTim = timService.updateTim(tim, id);
		if (savedTim == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok().body(savedTim);
	}
	
	
	@DeleteMapping("/tim/{id}")
	public ResponseEntity removeTim(@PathVariable("id") String timId) {
		try {
			boolean deleted = timService.removeTim(timId);
			if (deleted) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.badRequest().body("Tim has not been deleted.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Unknown internal server error ocurred.");
		}
	}

}
