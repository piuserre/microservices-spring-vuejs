package com.piuserre.carmicroservice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piuserre.carmicroservice.model.Car;
import com.piuserre.carmicroservice.model.Rent;
import com.piuserre.carmicroservice.repository.CarRepository;
import com.piuserre.carmicroservice.repository.RentRepository;

@Service
public class CarService {

	@Autowired
	private RentRepository rentRepository;

	@Autowired
	private CarRepository carRepository;

	public List<Rent> allRents() {
		return rentRepository.findAll();
	}

	public List<Car> allCars() {
		return carRepository.findAll();
	}

	public List<Rent> findRentsOfUser(Long userId) {
		return rentRepository.findAllByUserId(userId);
	}

	public List<Rent> findRentsOfCar(Long carId) {
		return rentRepository.findAllByCarId(carId);
	}

	public Rent saveRent(Rent rent) {
		return rentRepository.save(rent);
	}

	public boolean validateRent(Long carId, LocalDate startDate, LocalDate endDate) {

		if (startDate.isAfter(endDate))
			return false;
		
		List<Rent> rentsOfCar = findRentsOfCar(carId);
		
		for (Rent rent : rentsOfCar) {
			if ((startDate.isBefore(rent.getStartRent()) && endDate.isBefore(rent.getStartRent()))
					|| (startDate.isAfter(rent.getEndRent()) && endDate.isAfter(rent.getEndRent())))
				continue;
			else
				return false;
		}

		return true;
	}

}
