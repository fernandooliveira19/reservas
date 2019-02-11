package com.fernando.oliveira.reservas.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.domain.dto.ViajanteDTO;
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

	public Viajante fromDTO(@Valid ViajanteDTO dto) {
		
		Viajante viajante = new Viajante(null,dto.getNome(), dto.getEmail(), new Date());
		
		return viajante;
	}

	public Viajante update(Viajante obj) {
		Viajante newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	private void updateData(Viajante newObj, Viajante obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Viajante find(Integer id) {
		
		Optional<Viajante> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! - id: "+ id +" ,  Tipo: " + Viajante.class.getName(), null));
	}
	
	public List<Viajante> findAll() {
		List<Viajante> lista = repository.findAllOrderByNomeAsc();
		return lista;
	}
	public boolean viajanteTemReservas(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
