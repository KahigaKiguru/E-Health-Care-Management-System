package com.rajashekar.gande.EHealthManagementSystem.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PatientDTO implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2496688546485049435L;
	Patient patient;

	public PatientDTO(Patient patient) {
		super();
		this.patient = patient;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<Role> patient_roles = patient.getRoles();
		
		List<SimpleGrantedAuthority> auth = new ArrayList<SimpleGrantedAuthority>();
		
		for (Role role : patient_roles) {
			auth.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return auth;
	}

	@Override
	public String getPassword() {
		
		return patient.getPassword();
	}

	@Override
	public String getUsername() {
		return patient.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
