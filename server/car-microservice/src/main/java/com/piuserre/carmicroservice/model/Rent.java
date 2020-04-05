package com.piuserre.carmicroservice.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "rent")
public class Rent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="car_id",referencedColumnName="id")
	private Car car;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name = "start_rent")
	private LocalDate startRent;
	
	@Column(name="end_rent")
	private LocalDate endRent;

}
