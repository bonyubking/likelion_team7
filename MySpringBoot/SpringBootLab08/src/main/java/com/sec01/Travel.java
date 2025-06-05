package com.sec01;

import java.time.LocalDate;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Travels")
@AllArgsConstructor
@NoArgsConstructor
public @Data class Travel{
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	  
	  private String title;
	  
	  private String location;
	  
	  private String googleMapsUrl;
	  
	  private LocalDate startDate;
	 
	  private LocalDate endDate;
	  
	  private String description;
	  
	  private String imageUrl;
}
