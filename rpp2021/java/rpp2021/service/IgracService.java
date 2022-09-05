package rpp2021.service;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpp2021.controller.model.IgracRequest;
import rpp2021.controller.model.LigaRequest;
import rpp2021.controller.model.TimRequest;
import rpp2021.model.Igrac;
import rpp2021.model.Liga;
import rpp2021.model.Nacionalnost;
import rpp2021.model.Tim;
import rpp2021.repo.IgracRepository;

@Service
public class IgracService {

	@Autowired
	private IgracRepository repository;
	
	@Autowired
	private TimService timService;
	
	@Autowired 
	private NacionalnostService nacionalnostService;
	
	public Igrac saveIgrac(IgracRequest igracRequest) {
		try {
			Igrac igrac = mapToIgrac(igracRequest);
			return repository.save(igrac);
		} catch (Exception e) {
			System.out.println("Failed because: " + e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Igrac getIgrac(String id) {
		try {
			return repository.findById(Integer.valueOf(id)).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Igrac> getAllIgrac() {
		try {
			List<Igrac> igracList = repository.findAll();
			igracList.sort((i1, i2) -> i1.getId().compareTo(i2.getId()));
		
			return igracList;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
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
	
	public Igrac updateIgrac(IgracRequest request, Integer id) {
		Igrac igrac = mapToIgrac(request);
		if (repository.existsById(id)) {
			igrac.setId(id);
			Igrac savedIgrac = repository.save(igrac);
			return savedIgrac;
		}
		
		return null;
	}
	
	private Igrac mapToIgrac(IgracRequest igracRequest) {
		Igrac igrac = new Igrac();
		igrac.setIme(igracRequest.getIme());
		igrac.setPrezime(igracRequest.getPrezime());
		igrac.setBrojReg(igracRequest.getBrojReg());
		igrac.setDatumRodjenja(igracRequest.getDatumRodjenja());
		
		Nacionalnost nacionalnost = nacionalnostService.getNacionalnost(igracRequest.getNacionalnost().getId().toString());
		if (nacionalnost == null) {
			System.out.println("Nacionalnost ne postoji sa ID: " + igracRequest.getNacionalnost().getId());
		}
		igrac.setNacionalnost(nacionalnost);
		
		Tim tim= timService.getTim(igracRequest.getTim().getId().toString());
		if (tim == null) {
			System.out.println("Tim ne postoji sa ID: " + igracRequest.getTim().getId());
		}
		igrac.setTim(tim);
		
		return igrac;
	}

}


