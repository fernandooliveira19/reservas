package com.fernando.oliveira.reservas.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Telefone;
import com.fernando.oliveira.reservas.domain.ValorReserva;
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
	private ValorReservaService valorReservaService;
	
	@Autowired
	private LancamentoService lancamentoService;

	public void instantiateDatabase() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		
		Viajante bruno = new Viajante(null, "Bruno Maretta","bruno.maretta@gmail.com",new Date());
		
		Telefone t1 = new Telefone(null, "11", "999998888", TipoTelefone.CELULAR, bruno);
		
		
		//cria um viajante
		viajanteService.insert(bruno);
		//cria um telefone e atribui ao viajante
		telefoneService.insert(t1);
		
		//cria uma reserva
		Reserva reservaBruno = new Reserva(null,"", sdf.parse("28/12/2017 18:00"),sdf.parse("03/01/2018 20:00"),SituacaoReserva.RESERVADO,bruno, null, null);
		reservaService.insert(reservaBruno);
		
		//cria um valorReserva e atribui ao uma reserva
		ValorReserva valorReserva01 = new ValorReserva(null, 2000.00, 0.00, 2000.00, reservaBruno);
		valorReservaService.insert(valorReserva01);
		
		
		Lancamento lancamento1 = new Lancamento(null, sdf.parse("01/01/2018 13:00"), 500.00, FormaPagamento.TRANSFERENCIA, SituacaoPagamento.PENDENTE, valorReserva01);
		Lancamento lancamento2 = new Lancamento(null, sdf.parse("03/01/2018 13:00"), 1500.00, FormaPagamento.TRANSFERENCIA, SituacaoPagamento.PENDENTE, valorReserva01);
		
		lancamentoService.insert(lancamento1);
		lancamentoService.insert(lancamento2);
		
		
		
		//
		
		
	}
}
