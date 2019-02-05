package com.fernando.oliveira.reservas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.domain.enums.FormaPagamento;
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

		if (viajante == null) {
			return null;
		}
		reserva.setViajante(viajante);

		BigDecimal valorPago = new BigDecimal(0);

		if (!reserva.getLancamentos().isEmpty()) {

			for (int i = 0; i < reserva.getLancamentos().size(); i++) {
				Lancamento lancamento = reserva.getLancamentos().get(i);

				if (lancamento.getValorLancamento() != null) {
					atribuirPagamentoSinal(i, lancamento);

					if (lancamento.getSituacaoPagamento().equals(SituacaoPagamento.PAGO)) {
						valorPago = valorPago.add(lancamento.getValorLancamento());
					}

					lancamento.setReserva(reserva);
					lancamentoService.insert(lancamento);
					if (lancamento.getFormaPagamento().equals(FormaPagamento.SITE)) {
						break;
					}
				}
			}

		}

		definirStatusReserva(reserva, valorPago);

		definirValorPendente(reserva, valorPago);

		repository.save(reserva);

		return reserva;
	}

	private void definirStatusReserva(Reserva reserva, BigDecimal valorPago) {
		if (valorPago.intValue() >= reserva.getValorTotal().intValue()) {
			reserva.setSituacaoPagamento(SituacaoPagamento.PAGO);
		} else {
			reserva.setSituacaoPagamento(SituacaoPagamento.PENDENTE);
		}
	}

	// public Reserva fromDTO(@Valid ReservaDTO dto) {
	//
	// Reserva viajante = new Reserva(null, dto.getCodigo(), dto.getDataEntrada(),
	// dto.getDataSaida(), SituacaoReserva.toEnum(dto.getSituacaoReserva()),
	// dto.getViajante(),dto.getValorTotal(), null);
	//
	// return viajante;
	// }

	public Reserva update(Reserva reserva) {

		calcularPagamentoReserva(reserva);

		return repository.save(reserva);
	}

	private void calcularPagamentoReserva(Reserva reserva) {
		BigDecimal valorPago = new BigDecimal(0);

		if (!reserva.getLancamentos().isEmpty()) {

			for (int i = 0; i < reserva.getLancamentos().size(); i++) {

				Lancamento lancamento = lancamentoService.find(reserva.getLancamentos().get(i).getId());

				lancamento.setDataLancamento(reserva.getLancamentos().get(i).getDataLancamento());
				lancamento.setDataPagamento(reserva.getLancamentos().get(i).getDataPagamento());
				lancamento.setFormaPagamento(reserva.getLancamentos().get(i).getFormaPagamento());
				lancamento.setSituacaoPagamento(reserva.getLancamentos().get(i).getSituacaoPagamento());
				lancamento.setValorLancamento(reserva.getLancamentos().get(i).getValorLancamento());

				atribuirPagamentoSinal(i, lancamento);

				if (lancamento.getSituacaoPagamento().equals(SituacaoPagamento.PAGO)) {
					valorPago = valorPago.add(lancamento.getValorLancamento());
				}

				definirValorPendente(reserva, valorPago);

				lancamento.setReserva(reserva);
				lancamentoService.update(lancamento);
			}

		}

		definirStatusReserva(reserva, valorPago);
	}

	private void atribuirPagamentoSinal(int i, Lancamento lancamento) {
		if (i == 0) {
			lancamento.setPagamentoSinal(Boolean.TRUE);
		} else {
			lancamento.setPagamentoSinal(Boolean.FALSE);
		}
	}

	private void definirValorPendente(Reserva reserva, BigDecimal valorPago) {
		BigDecimal valorPendente = reserva.getValorTotal().subtract(valorPago);
		reserva.setValorPendente(valorPendente);
	}

	public Reserva find(Integer id) {

		Optional<Reserva> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! - id: " + id + " ,  Tipo: " + Reserva.class.getName(), null));
	}

	public List<Reserva> findAll() {
		List<Reserva> lista = repository.findAll();

		// for(Reserva reserva : lista) {
		//
		// if(reserva.getLancamentos() != null
		// && reserva.getLancamentos().isEmpty()) {
		// reserva.setSituacaoPagamento(SituacaoPagamento.PENDENTE);
		// continue;
		// }else {
		//
		// for(Lancamento lancamento : reserva.getLancamentos()) {
		//
		// if(lancamento.getSituacaoPagamento().equals(SituacaoPagamento.PENDENTE)) {
		//
		// reserva.setSituacaoPagamento(SituacaoPagamento.PENDENTE);
		// break;
		// }
		//
		// reserva.setSituacaoPagamento(SituacaoPagamento.PAGO);
		// }
		//
		// }
		//
		//
		//
		// }

		return lista;
	}

}
