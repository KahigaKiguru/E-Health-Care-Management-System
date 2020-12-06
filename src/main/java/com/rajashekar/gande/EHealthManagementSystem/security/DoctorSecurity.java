package com.rajashekar.gande.EHealthManagementSystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rajashekar.gande.EHealthManagementSystem.service.DoctorService;

@Configuration
@Order(1)
public class DoctorSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	DoctorService doctorService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(doctorService).passwordEncoder(passwordEncoder);
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/doctor/**")
                .authorizeRequests()
                .antMatchers("/patient/**", "/doctor/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/doctor/login")
                .defaultSuccessUrl("/doctor/doctorPage")
                .permitAll(true)
                .and()
                .logout()
                .logoutUrl("/doctor/logout")
                .logoutSuccessUrl("/doctor/login")
                .permitAll();
        http.csrf().disable();
	
	}
}
