package com.rajashekar.gande.EHealthManagementSystem.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pharmacies")
public class Pharmacy {

	int id;
	int name;
	
	List<Drug> medicines;
}
