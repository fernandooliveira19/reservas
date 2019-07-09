package com.fernando.oliveira.reservas.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.domain.enums.FormaPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoReserva;
import com.fernando.oliveira.reservas.domain.utils.ReservaUtils;
import com.fernando.oliveira.reservas.repository.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository repository;

	@Autowired
	private ViajanteService viajanteService;

	@Autowired
	private LancamentoService lancamentoService;
	
	private static final Integer MAX_SIZE = 5;

	/**
	 * 
	 * @param reserva
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Reserva insert(Reserva reserva) {

		Viajante viajante = viajanteService.find(reserva.getViajante().getId());
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();

		if (viajante == null) {
			return null;
		}
		reserva.setViajante(viajante);

		BigDecimal valorPago = new BigDecimal(0);

		if (!reserva.getLancamentos().isEmpty()) {

			for (int i = 0; i < reserva.getLancamentos().size(); i++) {
				Lancamento lancamento = new Lancamento();
				
				setLancamentosData(reserva.getLancamentos().get(i), lancamento);

				if (lancamento.getValorLancamento() != null && lancamento.getValorLancamento().doubleValue() > 0) {
					atribuirPagamentoSinal(i, lancamento);

					if (lancamento.getSituacaoPagamento().equals(SituacaoPagamento.PAGO)) {
						valorPago = valorPago.add(lancamento.getValorLancamento());
					}

					lancamento.setReserva(reserva);
					lancamentoService.insert(lancamento);
					lancamentos.add(lancamento);
					if (lancamento.getFormaPagamento().equals(FormaPagamento.SITE)) {
						break;
					}
				}
			}

		}

		setReservaStatus(reserva, valorPago);

		setValuePending(reserva, valorPago);
		
		reserva.setLancamentos(lancamentos);

		repository.save(reserva);

		return reserva;
	}

	private void setLancamentosData(Lancamento lancamentoReserva, Lancamento lancamento) {
		lancamento.setDataLancamento(lancamentoReserva.getDataLancamento());
		lancamento.setDataPagamento(lancamentoReserva.getDataPagamento());
		lancamento.setFormaPagamento(lancamentoReserva.getFormaPagamento());
		lancamento.setReserva(lancamentoReserva.getReserva());
		lancamento.setSituacaoPagamento(lancamentoReserva.getSituacaoPagamento());
		lancamento.setValorLancamento(lancamentoReserva.getValorLancamento());
		
	}

	private void setReservaStatus(Reserva reserva, BigDecimal valorPago) {
		if (valorPago.intValue() >= reserva.getValorTotal().intValue()) {
			reserva.setSituacaoPagamento(SituacaoPagamento.PAGO);
		} else {
			reserva.setSituacaoPagamento(SituacaoPagamento.PENDENTE);
		}
	}

	public Reserva update(Reserva reserva) {
		
		atribuirLancamentos(reserva);
		
		calcularPagamentos(reserva);
		
		atribuirSituacao(reserva);

//		calculatePayment(reserva);

		return repository.save(reserva);
	}

	private void atribuirSituacao(Reserva reserva) {
		
		if(reserva.getSituacaoPagamento().equals(SituacaoPagamento.PAGO)
				|| reserva.getValorPago().intValue() > 0) {
			reserva.setSituacaoReserva(SituacaoReserva.RESERVADO);
		}else {
			reserva.setSituacaoReserva(SituacaoReserva.PRE_RESERVA);
		}
		Date dataSaida = ReservaUtils.converterLocalDateTime(reserva.getDataSaida());
		if(dataSaida.before(new Date())
				&& !reserva.getSituacaoReserva().equals(SituacaoReserva.CANCELADO)) {
			
			reserva.setSituacaoReserva(SituacaoReserva.FINALIZADO);
		}
	}

	private void calcularPagamentos(Reserva reserva) {
		
		
		BigDecimal valorPago = new BigDecimal(0);
		BigDecimal valorPendente = new BigDecimal(0);
		
		BigDecimal valorTotal = reserva.getValorTotal();
		
		if(!reserva.getLancamentos().isEmpty()) {
			
			for(Lancamento lancamento : reserva.getLancamentos()) {
				if(lancamento.getValorLancamento()!= null
						&& lancamento.getValorLancamento().intValue() > 0
						&& lancamento.getSituacaoPagamento().equals(SituacaoPagamento.PAGO)) {
					valorPago = valorPago.add(lancamento.getValorLancamento());
				}
			}
		}
		
		
		if(valorPago.compareTo(valorTotal) >= 0 ) {
			reserva.setSituacaoPagamento(SituacaoPagamento.PAGO);
			
		}else {
			reserva.setSituacaoPagamento(SituacaoPagamento.PENDENTE);
		}
	
		valorPendente = valorTotal.subtract(valorPago);
		
		reserva.setValorPendente(valorPendente);
	}

	private void atribuirLancamentos(Reserva reserva) {
		
		List<Lancamento> listaLancamentos = new ArrayList<Lancamento>();
		List<Lancamento> listaTemp = reserva.getLancamentos();
		reserva.setLancamentos(null);
		
		if(!listaTemp.isEmpty()) {
			
			
			int index = 0;
			for(Lancamento lancamento : listaTemp) {
				
				if(lancamento.getValorLancamento() == null
						|| lancamento.getValorLancamento().intValue() <= 0) {
					continue;
				}
				
				if(lancamento.getId() != null) {
					Lancamento lancamentoSalvo = lancamentoService.find(lancamento.getId());
					lancamentoSalvo = atribuirDadosLancamento(lancamentoSalvo,lancamento, index);
					lancamentoSalvo.setReserva(reserva);
					lancamentoService.update(lancamentoSalvo);
					listaLancamentos.add(lancamentoSalvo);
					index++;
					
				}else {
					Lancamento novoLancamento = new Lancamento();
					novoLancamento=atribuirDadosLancamento(novoLancamento, lancamento, index);
					novoLancamento.setReserva(reserva);
					lancamentoService.insert(novoLancamento);
					listaLancamentos.add(novoLancamento);
					index++;
					
				}
				
				
			}
			reserva.setLancamentos(listaLancamentos);
		}
		
	}

	private Lancamento atribuirDadosLancamento(Lancamento lancamentoDestino, Lancamento lancamentoOrigem, int i) {
		lancamentoDestino.setDataLancamento(lancamentoOrigem.getDataLancamento());
		lancamentoDestino.setDataPagamento(lancamentoOrigem.getDataPagamento());
		lancamentoDestino.setFormaPagamento(lancamentoOrigem.getFormaPagamento());
		lancamentoDestino.setValorLancamento(lancamentoOrigem.getValorLancamento());
		lancamentoDestino.setSituacaoPagamento(lancamentoOrigem.getSituacaoPagamento());
		
		atribuirPagamentoSinal(i, lancamentoDestino);
		
		return lancamentoDestino;
		
	}

	private void calculatePayment(Reserva reserva) {
		BigDecimal valorPago = new BigDecimal(0);

		if (!reserva.getLancamentos().isEmpty()) {

			for (int i = 0; i < reserva.getLancamentos().size(); i++) {

				if (reserva.getLancamentos().get(i) != null && reserva.getLancamentos().get(i).getId() != null) {
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

					setValuePending(reserva, valorPago);

					lancamento.setReserva(reserva);
					lancamentoService.update(lancamento);
				}
			}
		}

		if(reserva.getValorPendente() == null) {
			reserva.setValorPendente(new BigDecimal(0));
		}
		
		setReservaStatus(reserva, valorPago);
		
		
	}

	private void atribuirPagamentoSinal(int i, Lancamento lancamento) {
		if (i == 0) {
			lancamento.setPagamentoSinal(Boolean.TRUE);
		} else {
			lancamento.setPagamentoSinal(Boolean.FALSE);
		}
	}

	private void setValuePending(Reserva reserva, BigDecimal valorPago) {
		BigDecimal valorPendente = reserva.getValorTotal().subtract(valorPago);
		
		if(valorPendente == null) {
			valorPendente = new BigDecimal(0);
		}
		reserva.setValorPendente(valorPendente);
	}

	public Reserva find(Integer id) {

		Optional<Reserva> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! - id: " + id + " ,  Tipo: " + Reserva.class.getName(), null));
	}

	public List<Reserva> findAll() {
		List<Reserva> lista = repository.findAllOrderByDataEntradaAsc();

		
		return lista;
	}

	public List<Reserva> nextReservas() {

		List<Reserva> lista = repository.findProximasReservas();

		return lista;
	}

	public List<Reserva> findReservasByNomeViajante(String nome) {
		List<Reserva> lista = repository.findReservasByNomeViajante(nome);
		return lista;
	}

	public List<Reserva> nextReserves() {
		try {
			List<Reserva> list = repository.findProximasReservas();

			if (!list.isEmpty()) {

				if (list.size() < MAX_SIZE) {
					return list.subList(0, list.size());
				} else {
					return list.subList(0, MAX_SIZE);
				}

			}
			return new ArrayList<Reserva>();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
