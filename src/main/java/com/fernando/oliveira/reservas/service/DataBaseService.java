package com.fernando.oliveira.reservas.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Telefone;
import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.domain.enums.FormaPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoReserva;
import com.fernando.oliveira.reservas.domain.enums.TipoTelefone;

@Service
public class DataBaseService {
	
	@Autowired
	private ViajanteService viajanteService;
	@Autowired
	private TelefoneService telefoneService;
	@Autowired
	private ReservaService reservaService;
	
	
	
	@Autowired
	private LancamentoService lancamentoService;

	public void instantiateDatabase() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		Viajante bruno = new Viajante(null, "Bruno Maretta","bruno.maretta@gmail.com",new Date());
		
		Telefone foneBruno = new Telefone(null, "11", "999998888", TipoTelefone.CELULAR, bruno);
		
		//cria um telefone e atribui ao viajante
		telefoneService.insert(foneBruno);
		
		//cria um viajante
		bruno.setTelefone(foneBruno);
		viajanteService.insert(bruno);
		
		LocalDateTime dataEntrada = LocalDateTime.of(2019,Month.JANUARY,1,10,0,0); 
		LocalDateTime dataSaida = LocalDateTime.of(2019,Month.JANUARY,10,18,0,0); 
		
		//cria uma reserva
		Reserva reserva = new Reserva();
		reserva.setDataEntrada(dataEntrada);
		reserva.setDataSaida(dataSaida);
		reserva.setSituacaoPagamento(SituacaoPagamento.PENDENTE);
		reserva.setSituacaoReserva(SituacaoReserva.RESERVADO);
		reserva.setValorTotal(new BigDecimal(3600.00));
		reserva.setValorPendente(new BigDecimal(1000.00));
		reserva.setViajante(bruno);
		
		
		LocalDate dataLancamento = LocalDate.of(2019, Month.JANUARY, 3);
		
		Lancamento lanc1= new Lancamento();
		lanc1.setDataLancamento(dataLancamento);
		lanc1.setDataPagamento(LocalDate.now());
		lanc1.setReserva(reserva);
		lanc1.setValorLancamento(new BigDecimal(2600.00));
		lanc1.setSituacaoPagamento(SituacaoPagamento.PAGO);
		lanc1.setFormaPagamento(FormaPagamento.TRANSFERENCIA);
		
		Lancamento lanc2= new Lancamento();
		lanc2.setDataLancamento(dataLancamento);
		lanc2.setReserva(reserva);
		lanc2.setValorLancamento(new BigDecimal(1000.00));
		lanc2.setSituacaoPagamento(SituacaoPagamento.PENDENTE);
		lanc2.setFormaPagamento(FormaPagamento.LOCAL);
		
		List<Lancamento> lancamentos  = new ArrayList<Lancamento>();
		lancamentos.add(lanc1);
		lancamentos.add(lanc2);
		
		reserva.setLancamentos(lancamentos);
		
		reservaService.insert(reserva);
		
		//cria um valorReserva e atribui ao uma reserva
//		ValorReserva valorReserva01 = new ValorReserva(null, 2000.00, 0.00, 2000.00, reservaBruno);
//		valorReservaService.insert(valorReserva01);
		
		
//		Lancamento lancamento1 = new Lancamento(null, sdf.parse("20/12/2017 13:00"), 1830.00, FormaPagamento.TRANSFERENCIA, SituacaoPagamento.PAGO, reservaBruno, null);
//		Lancamento lancamento2 = new Lancamento(null, sdf.parse("27/12/2017 13:00"), 1830.00, FormaPagamento.TRANSFERENCIA, SituacaoPagamento.PENDENTE, reservaBruno, null);
//		
//		lancamentoService.insert(lancamento1);
//		lancamentoService.insert(lancamento2);
		
		
		
		
	}
}
