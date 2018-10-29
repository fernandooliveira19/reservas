package com.fernando.oliveira.reservas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.repository.ReservaRepository;

@Service
public class ReservaService {
	
	@Autowired
	private ReservaRepository repository;
	
	@Transactional
	public Reserva insert(Reserva entity) {
		
		repository.save(entity);
		
		return entity;
	}

}
