package rpp2021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpp2021.controller.model.IgracRequest;
import rpp2021.model.Igrac;
import rpp2021.model.Liga;
import rpp2021.model.Nacionalnost;
import rpp2021.repo.LigaRepository;
import rpp2021.controller.model.LigaRequest;

@Service
public class LigaService {

	@Autowired
	private LigaRepository repository;
	
	public Liga saveLiga(LigaRequest ligaRequest) {
		try {
			Liga liga = mapToLiga(ligaRequest);
			return repository.save(liga);
		} catch (Exception e) {
			System.out.println("Failed because: " + e.getLocalizedMessage());
			return null;
		}
	}
	
	public Liga getLiga(String id) {
		try {
			return repository.findById(Integer.valueOf(id)).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Liga> getAllLiga() {
		try {
			List<Liga> ligaList = repository.findAll();
			ligaList.sort((l1, l2) -> l1.getId().compareTo(l2.getId()));
		
			return ligaList;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public boolean removeLiga(String id) {
		try {
			repository.deleteById(Integer.valueOf(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Liga updateLiga(LigaRequest request, Integer id) {
		Liga liga = mapToLiga(request);
		if (repository.existsById(id)) {
			liga.setId(id);
			Liga savedLiga = repository.save(liga);
			return savedLiga;
		}
		
		return null;
	}
	
	private Liga mapToLiga(LigaRequest ligaRequest) {
		
		Liga liga = new Liga();
		liga.setNaziv(ligaRequest.getNaziv());
		liga.setOznaka(ligaRequest.getOznaka());
		
		return liga;
		
	}
}