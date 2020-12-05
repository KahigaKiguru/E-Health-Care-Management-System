package com.rajashekar.gande.EHealthManagementSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drugs")
public class Drug {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "grug_id")
	private int id;
	
	private String name;
	private int quantity;
	private int price;
	
	private String dosage;
	private String description;
	
	private Pharmacy pharmacy;
}
