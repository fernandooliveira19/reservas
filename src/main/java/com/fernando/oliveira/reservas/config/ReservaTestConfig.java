package com.fernando.oliveira.reservas.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fernando.oliveira.reservas.service.DataBaseService;

@Configuration
@Profile("test")
public class ReservaTestConfig {

	
	@Autowired
	private DataBaseService dataBaseService;
	
	@Bean 
	public boolean instantiateDatabase() throws ParseException{
		
//		dataBaseService.instantiateDatabase();
		
		return true;
	}
}
