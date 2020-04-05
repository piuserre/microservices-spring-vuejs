package com.piuserre.carmicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piuserre.carmicroservice.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Long> {
	
	List<Rent> findAllByUserId (Long userId);
	
	List<Rent> findAllByCarId (Long carId);

}
