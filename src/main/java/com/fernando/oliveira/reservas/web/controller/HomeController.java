package com.fernando.oliveira.reservas.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.service.LancamentoService;
import com.fernando.oliveira.reservas.service.ReservaService;

@Controller
public class HomeController {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ReservaService reservaService;
	
	@GetMapping("/")
	public String home() {
		
		return "home";
	}
	
	@ModelAttribute("proximosLancamentosPendentes")
	public List<Lancamento> proximosLancamentosPendentes() {
		
		return lancamentoService.findProximosLancamentosPendentes(); 
	}
	
	@ModelAttribute("nextReserves")
	public List<Reserva> nextReserves() {
		
		return reservaService.nextReserves(); 
	}
	

}
