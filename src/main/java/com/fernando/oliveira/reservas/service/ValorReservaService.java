package com.fernando.oliveira.reservas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.ValorReserva;
import com.fernando.oliveira.reservas.repository.ValorReservaRepository;

@Service
public class ValorReservaService {
	
	@Autowired
	private ValorReservaRepository repository;
	
	@Transactional
	public ValorReserva insert(ValorReserva entity) {
		
		repository.save(entity);
		
		return entity;
	}

}
