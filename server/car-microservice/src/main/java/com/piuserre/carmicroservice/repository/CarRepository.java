package com.piuserre.carmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piuserre.carmicroservice.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
