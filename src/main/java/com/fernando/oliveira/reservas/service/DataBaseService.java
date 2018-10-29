package com.fernando.oliveira.reservas.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Telefone;
import com.fernando.oliveira.reservas.domain.Viajante;
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
		
		
		
		Viajante v1 = new Viajante(null, "Bruno Maretta","bruno.maretta@gmail.com",new Date());
		
		Telefone t1 = new Telefone(null, "11", "999998888", TipoTelefone.CELULAR, v1);
		
		viajanteService.insert(v1);
		telefoneService.insert(t1);
		
		Reserva r1 = new Reserva(null,"", sdf.parse("28/12/2017 18:00"),sdf.parse("03/01/2018 20:00"),3600.00,0.00, 0.00, SituacaoReserva.RESERVADO,SituacaoPagamento.PENDENTE,v1, null);
		
		reservaService.insert(r1);
		
	}
}
