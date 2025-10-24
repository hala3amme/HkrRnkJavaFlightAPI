package com.example.hackerrank.project1.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.api.model.Flight;
import com.hackerrank.api.repository.FlightRepository;

@RestController
@RequestMapping("/flight")
public class FlightController {
	private final FlightRepository flightRepository;

	public FlightController(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	@PostMapping
	public ResponseEntity<Flight> create(@RequestBody Flight flight) {
		Flight saved = flightRepository.save(flight);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@GetMapping
	public ResponseEntity<List<Flight>> list(
			@RequestParam(name = "origin", required = false) String origin,
			@RequestParam(name = "orderBy", required = false) String orderBy) {

		List<Flight> results = flightRepository.findAll();

		if (origin != null) {
			results = results.stream()
					.filter(f -> origin.equals(f.getOrigin()))
					.collect(Collectors.toList());
		}

		Comparator<Flight> byId = Comparator.comparing(Flight::getId);

		if (orderBy == null || orderBy.isBlank()) {
			results = results.stream().sorted(byId).collect(Collectors.toList());
		} else if ("destination".equals(orderBy)) {
			results = results.stream()
					.sorted(Comparator.comparing(Flight::getDestination)
							.thenComparing(byId))
					.collect(Collectors.toList());
		} else if ("-destination".equals(orderBy)) {
			results = results.stream()
					.sorted(Comparator.comparing(Flight::getDestination, Comparator.reverseOrder())
							.thenComparing(byId))
					.collect(Collectors.toList());
		} else {
			// Fallback to id ordering if unknown
			results = results.stream().sorted(byId).collect(Collectors.toList());
		}

		return ResponseEntity.ok(results);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Flight> getById(@PathVariable("id") Integer id) {
		Optional<Flight> found = flightRepository.findById(id);
		return found.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
