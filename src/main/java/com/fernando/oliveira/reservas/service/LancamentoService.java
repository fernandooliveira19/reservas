package com.fernando.oliveira.reservas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.dto.LancamentoDTO;
import com.fernando.oliveira.reservas.domain.enums.SituacaoPagamento;
import com.fernando.oliveira.reservas.repository.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository repository;
	
	@Autowired
	private ReservaService reservaService;
	
	private static final Integer MAX_SIZE = 5;
	
	@Transactional
	public Lancamento insert(Lancamento lancamento) {
		
		repository.save(lancamento);
		
		return lancamento;
	}
	
	public Lancamento update(Lancamento obj) {
		Lancamento lanc = find(obj.getId());
		
		return lanc;
	}
	
	public Lancamento find(Integer id) {
		Optional<Lancamento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! - id: "+ id +" ,  Tipo: " + Lancamento.class.getName(), null));
	}
	
	public List<Lancamento> findAll(){
		List<Lancamento> lista = repository.findAll();
		return lista;
	}

	public Lancamento fromDTO(@Valid LancamentoDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Lancamento> findLancamentosPendentes(){
		
		return repository.findLancamentosPendentes();
	}

	public List<Lancamento> findProximosLancamentosPendentes() {

		List<Lancamento> proximos = new ArrayList<Lancamento>(); 
		List<Lancamento> list = repository.findLancamentosPendentes();
		
		for (int i = 0; i < MAX_SIZE ; i++) {
			if(list.get(i) != null) {
			Lancamento lancamento = list.get(i);
			proximos.add(lancamento);
			}
			
		}
		return proximos;
		
	}

}
