package com.fernando.oliveira.reservas.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.repository.ViajanteRepository;

@Service
public class ViajanteService {
	
	@Autowired
	private ViajanteRepository repository;
	
	@Transactional
	public Viajante insert(Viajante entity) {
		
		entity.setDataInclusao(new Date());
		repository.save(entity);
		
		return entity;
	}

}
