package rpp2021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpp2021.model.Nacionalnost;
import rpp2021.repo.NacionalnostRepository;

@Service
public class NacionalnostService {

	@Autowired
	private NacionalnostRepository repository;
	
	public String saveNacionalnost(Nacionalnost nacionalnost) {
		try {
			repository.save(nacionalnost);
		} catch (Exception e) {
			return "Failed because: " + e.getLocalizedMessage();
		}
		return "Success";
	}
	
	public Nacionalnost getNacionalnost(String id) {
		try {
			return repository.getOne(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Nacionalnost> getAllNacionalnost() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Nacionalnost>();
		}
	}
	
	public boolean removeNacionalnost(String id) {
		try {
			repository.deleteById(Integer.valueOf(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}