package rpp2021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpp2021.model.Liga;
import rpp2021.repo.LigaRepository;

@Service

public class LigaService {

	@Autowired
	private LigaRepository repository;
	
	public String saveLiga(Liga liga) {
		try {
			repository.save(liga);
		} catch (Exception e) {
			return "Failed because: " + e.getLocalizedMessage();
		}
		return "Success";
	}
	
	public Liga getLiga(String id) {
		try {
			return repository.getOne(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Liga> getAllLiga() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Liga>();
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
}