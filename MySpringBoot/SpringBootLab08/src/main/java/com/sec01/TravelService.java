package com.sec01;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TravelService {

	private final TravelRepository travelRepository;

	public TravelService(TravelRepository travelRepository) {
		super();
		this.travelRepository = travelRepository;
	}
	
	public List<Travel> getAll(){
		
		return travelRepository.findAll();
	}
	
	public Travel save(Travel travel) {
		
		return travelRepository.save(travel);
	}
	
	public void delete(Integer id) {
		
		travelRepository.deleteById(id);
	}
}
