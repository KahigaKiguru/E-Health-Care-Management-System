package com.rajashekar.gande.EHealthManagementSystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rajashekar.gande.EHealthManagementSystem.service.PatientService;

@Configuration
@Order(2)
public class PatientSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private PatientService patientService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(patientService).passwordEncoder(passwordEncoder);
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/patient/**")
                .authorizeRequests()
                .antMatchers("/patient/**", "/doctor/**", "/pharmacy/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/patient/login")
                .defaultSuccessUrl("/patient/patientPage")
                .permitAll(true)
                .and()
                .logout()
                .logoutUrl("/patient/logout")
                .logoutSuccessUrl("/patient/login")
                .permitAll();
        http.csrf().disable();
	
	}
}
