package rpp2021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpp2021.controller.model.LigaRequest;
import rpp2021.controller.model.NacionalnostRequest;
import rpp2021.model.Liga;
import rpp2021.model.Nacionalnost;
import rpp2021.repo.NacionalnostRepository;

@Service
public class NacionalnostService {

	@Autowired
	private NacionalnostRepository repository;
	
	public Nacionalnost saveNacionalnost(NacionalnostRequest nacionalnostRequest) {
		try {
			Nacionalnost nacionalnost = mapToNacionalnost(nacionalnostRequest);
			return repository.save(nacionalnost);
		} catch (Exception e) {
			System.out.println("Failed because: " + e.getLocalizedMessage());
			return null;
		}
	}
	
	public Nacionalnost getNacionalnost(String id) {
		try {
			return repository.findById(Integer.valueOf(id)).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Nacionalnost> getAllNacionalnost() {
		try {
			List<Nacionalnost> nacionalnostList = repository.findAll();
			nacionalnostList.sort((n1, n2) -> n1.getId().compareTo(n2.getId()));
			return nacionalnostList;
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
	public Nacionalnost updateNacionalnost(NacionalnostRequest request, Integer id) {
		Nacionalnost nacionalnost = mapToNacionalnost(request);
		if (repository.existsById(id)) {
			nacionalnost.setId(id);
			Nacionalnost savedNacionalnost = repository.save(nacionalnost);
			return savedNacionalnost;
		}
		
		return null;
	}
	
    private Nacionalnost mapToNacionalnost(NacionalnostRequest nacionalnostRequest) {
		
    	Nacionalnost nacionalnost = new Nacionalnost();
		nacionalnost.setNaziv(nacionalnostRequest.getNaziv());
		nacionalnost.setSkracenica(nacionalnostRequest.getSkracenica());
		
		return nacionalnost;
		
	}
}