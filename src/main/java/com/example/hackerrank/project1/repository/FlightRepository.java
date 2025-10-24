package com.example.hackerrank.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hackerrank.project1.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer>  {
	
}
