package com.piuserre.carmicroservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "brand")
	private String brand;

	@Column(name = "name")
	private String name;

	@Column(name = "year")
	private Long year;

	@Column(name = "prize")
	private Long prize;

	@Column(name = "km")
	private Long km;
}
