package com.sec01;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/travels")
@CrossOrigin(origins = "http://localhost:3000")
public class TravelController {
	
	private TravelService travelService;

	public TravelController(TravelService travelService) {
		super();
		this.travelService = travelService;
	}
	
	@GetMapping
	public List<Travel> getAll(){
		
		return travelService.getAll();
	}
	
	@PostMapping
	public Travel save(@RequestBody Travel travel) {
		
		return travelService.save(travel);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		
		travelService.delete(id);
	}
}

