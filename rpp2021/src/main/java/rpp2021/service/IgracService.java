package rpp2021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpp2021.model.Igrac;
import rpp2021.repo.IgracRepository;

@Service
public class IgracService {

	@Autowired
	private IgracRepository repository;
	
	public String saveIgrac(Igrac igrac) {
		try {
			repository.save(igrac);
		} catch (Exception e) {
			return "Failed because: " + e.getLocalizedMessage();
		}
		return "Success";
	}
	
	public Igrac getIgrac(String id) {
		try {
			return repository.getOne(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Igrac> getAllIgrac() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Igrac>();
		}
	}
	
	public boolean removeIgrac(String id) {
		try {
			repository.deleteById(Integer.valueOf(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
