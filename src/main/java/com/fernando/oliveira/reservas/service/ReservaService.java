package com.fernando.oliveira.reservas.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.domain.dto.ReservaDTO;
import com.fernando.oliveira.reservas.domain.enums.SituacaoReserva;
import com.fernando.oliveira.reservas.repository.ReservaRepository;

@Service
public class ReservaService {
	
	@Autowired
	private ReservaRepository repository;
	
	@Autowired
	private ViajanteService viajanteService;
	
	
	@Transactional
	public Reserva insert(Reserva reserva) {
		
		Viajante viajante = viajanteService.find(reserva.getViajante().getId());
		
		if(viajante == null) {
			return null;
		}
		
		reserva.setViajante(viajante);
		repository.save(reserva);
		
		return reserva;
	}

	public Reserva fromDTO(@Valid ReservaDTO dto) {
		
		Reserva viajante = new Reserva(null, dto.getCodigo(), dto.getDataEntrada(), dto.getDataSaida(), SituacaoReserva.toEnum(dto.getSituacaoReserva()),
				dto.getViajante(),dto.getValorTotal(),  null);
		
		return viajante;
	}

	public Reserva update(Reserva obj) {
		Reserva newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	private void updateData(Reserva newObj, Reserva obj) {
		
	}
	
	public Reserva find(Integer id) {
		
		Optional<Reserva> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! - id: "+ id +" ,  Tipo: " + Reserva.class.getName(), null));
	}
	
	public List<Reserva> findAll() {
		List<Reserva> lista = repository.findAll();
		return lista;
	}

}
