
package rpp2021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpp2021.controller.model.LigaRequest;
import rpp2021.controller.model.TimRequest;
import rpp2021.model.Liga;
import rpp2021.model.Tim;
import rpp2021.repo.LigaRepository;
import rpp2021.repo.TimRepository;

@Service
public class TimService {

	@Autowired
	private TimRepository timRepository;

	@Autowired
	private LigaService ligaService;
	
	public Tim saveTim(TimRequest timRequest) {
		try {
			Tim tim = mapToTim(timRequest);
			return timRepository.save(tim);
		} catch (Exception e) {
			System.out.println("Failed because: " + e.getLocalizedMessage());
		}
		return null;
	}
	
	public Tim getTim(String id) {
		try {
			return timRepository.findById(Integer.valueOf(id)).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Tim> getAllTim() {
		try {
			List<Tim> timList = timRepository.findAll();
			timList.sort((t1, t2) -> t1.getId().compareTo(t2.getId()));
		
			return timList;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public boolean removeTim(String id) {
		try {
			timRepository.deleteById(Integer.valueOf(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Tim updateTim(TimRequest request, Integer id) {
		Tim tim = mapToTim(request);
		if (timRepository.existsById(id)) {
			tim.setId(id);
			Tim savedTim = timRepository.save(tim);
			return savedTim;
		}
		
		return null;
	}
	
	private Tim mapToTim(TimRequest timRequest) {
		Tim tim = new Tim();
		
		tim.setNaziv(timRequest.getNaziv());
		tim.setOsnovan(timRequest.getOsnovan());
		tim.setSediste(timRequest.getSediste());
		
		Liga liga = ligaService.getLiga(timRequest.getLiga().getId().toString());
		if (liga == null) {
			throw new RuntimeException("Ne postoji liga sa ID: " + timRequest.getLiga().getId());
		}
		tim.setLiga(liga);
		
		return tim;
		
	}
}
