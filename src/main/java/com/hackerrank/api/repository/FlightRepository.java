package com.hackerrank.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.api.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
