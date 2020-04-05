package com.piuserre.carmicroservice.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.piuserre.carmicroservice.model.Rent;
import com.piuserre.carmicroservice.service.CarService;

@RestController
public class CarController {

	@Autowired
	private CarService carService;

	@Autowired
	private CallUserClient callUserClient;

	@GetMapping("/rent/{userId}")
	public ResponseEntity<?> findRentsOfUser(@PathVariable Long userId) {
		return ResponseEntity.ok(carService.findRentsOfUser(userId));
	}

	@GetMapping("/all")
	public ResponseEntity<?> findAllCars() {
		System.out.println("going to retrieve cars");
		return ResponseEntity.ok(carService.allCars());
	}
	
	@GetMapping("/rent/validate/{carId}/{startDate}/{endDate}")
	public ResponseEntity<?> validateRent(@PathVariable Long carId, 
			@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
			@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
		return ResponseEntity.ok(carService.validateRent(carId, startDate, endDate));
	}

	@PostMapping("/rent")
	public ResponseEntity<?> createRent(@RequestBody Rent rent) {

		if (carService.validateRent(rent.getCar().getId(), rent.getStartRent(), rent.getEndRent()))
			return new ResponseEntity<>(carService.saveRent(rent), HttpStatus.CREATED);
		else
			return ResponseEntity.badRequest().body("This car is not available.");
	}

}
