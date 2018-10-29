package com.fernando.oliveira.reservas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Telefone;
import com.fernando.oliveira.reservas.repository.TelefoneRepository;

@Service
public class TelefoneService {
	@Autowired
	private TelefoneRepository repository;

	@Transactional
	public Telefone insert(Telefone entity) {

		repository.save(entity);

		return entity;
	}

}
