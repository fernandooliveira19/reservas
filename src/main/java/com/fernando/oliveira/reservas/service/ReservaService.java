package com.fernando.oliveira.reservas.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.domain.enums.SituacaoPagamento;
import com.fernando.oliveira.reservas.repository.ReservaRepository;

@Service
public class ReservaService {
	
	@Autowired
	private ReservaRepository repository;
	
	@Autowired
	private ViajanteService viajanteService;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	
	@Transactional
	public Reserva insert(Reserva reserva) {
		
		Viajante viajante = viajanteService.find(reserva.getViajante().getId());
		
		if(viajante == null) {
			return null;
		}
		reserva.setViajante(viajante);
		
		if(!reserva.getLancamentos().isEmpty()) {
			
			for (int i = 0; i < reserva.getLancamentos().size(); i++) {
				Lancamento lancamento = reserva.getLancamentos().get(i);
				
				if(i == 0) {
					lancamento.setPagamentoSinal(Boolean.TRUE);
				}else {
					lancamento.setPagamentoSinal(Boolean.FALSE);
				}
				
				lancamento.setReserva(reserva);
				lancamentoService.insert(lancamento);
			}
						
		}

		repository.save(reserva);
		
		return reserva;
	}

//	public Reserva fromDTO(@Valid ReservaDTO dto) {
//		
//		Reserva viajante = new Reserva(null, dto.getCodigo(), dto.getDataEntrada(), dto.getDataSaida(), SituacaoReserva.toEnum(dto.getSituacaoReserva()),
//				dto.getViajante(),dto.getValorTotal(),  null);
//		
//		return viajante;
//	}

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
		
		for(Reserva reserva : lista) {
			
			if(reserva.getLancamentos() != null
					&& reserva.getLancamentos().isEmpty()) {
				reserva.setSituacaoPagamento(SituacaoPagamento.PENDENTE);
				continue;
			}else {
				
				for(Lancamento lancamento : reserva.getLancamentos()) {
					
					if(lancamento.getSituacaoPagamento().equals(SituacaoPagamento.PENDENTE)) {
						
						reserva.setSituacaoPagamento(SituacaoPagamento.PENDENTE);
						break;
					}
					
					reserva.setSituacaoPagamento(SituacaoPagamento.PAGO);
				}
				
			}
			
			
			
		}
		
		return lista;
	}

}
