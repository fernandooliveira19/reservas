package com.fernando.oliveira.reservas.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Telefone;
import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.domain.dto.TelefoneDTO;
import com.fernando.oliveira.reservas.domain.enums.TipoTelefone;
import com.fernando.oliveira.reservas.repository.TelefoneRepository;

@Service
public class TelefoneService {
	
	
	@Autowired
	private TelefoneRepository repository;
	
	@Autowired
	private ViajanteService viajanteService;

	@Transactional
	public Telefone insert(Telefone telefone) {

		prepareTelefone(telefone);
		
		repository.save(telefone);

		return telefone;
	}
	
	private void prepareTelefone(Telefone telefone) {
		
		telefone.setNumero(telefone.getNumero().replace("-",""));
		telefone.setTipoTelefone(TipoTelefone.CELULAR);
		
	}

	

	public Telefone fromDTO(@Valid TelefoneDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}



	public Telefone update(Telefone obj) {
		// TODO Auto-generated method stub
		return null;
	}



	public List<Telefone> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
