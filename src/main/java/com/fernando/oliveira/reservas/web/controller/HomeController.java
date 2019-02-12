package com.fernando.oliveira.reservas.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.service.LancamentoService;

@Controller
public class HomeController {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping("/")
	public String home() {
		
		return "/home";
	}
	
	@ModelAttribute("lancamentosPendentes")
	public List<Lancamento> lancamentosPendentes() {
		
		return lancamentoService.findLancamentosPendentes(); 
	}
	

}
