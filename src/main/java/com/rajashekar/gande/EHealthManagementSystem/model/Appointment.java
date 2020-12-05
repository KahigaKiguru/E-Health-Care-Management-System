package com.rajashekar.gande.EHealthManagementSystem.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {

	int id;
	
	Date time;
	
	Doctor doctor;
	
	Patient patient;
	
}
