
package rpp2021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpp2021.model.Tim;
import rpp2021.repo.TimRepository;

@Service
public class TimService {

	@Autowired
	private TimRepository repository;
	
	public String saveTim(Tim tim) {
		try {
			repository.save(tim);
		} catch (Exception e) {
			return "Failed because: " + e.getLocalizedMessage();
		}
		return "Success";
	}
	
	public Tim getTim(String id) {
		try {
			return repository.getOne(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Tim> getAllTim() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Tim>();
		}
	}
	
	public boolean removeTim(String id) {
		try {
			repository.deleteById(Integer.valueOf(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
