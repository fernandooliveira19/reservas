package com.fernando.oliveira.reservas.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.domain.enums.FormaPagamento;
import com.fernando.oliveira.reservas.service.LancamentoService;
import com.fernando.oliveira.reservas.service.ReservaService;
import com.fernando.oliveira.reservas.service.ViajanteService;

@Controller
@RequestMapping("/lancamentos")
public class LancamentoController {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ViajanteService viajanteService;
	
	@Autowired
	private ReservaService reservaService;
	
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Lancamento lancamento) {
		
		return "lancamento/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		List<Lancamento> lancamentos = lancamentoService.findAll();
		model.addAttribute("lancamentos", lancamentos);
		return "lancamento/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Lancamento lancamento, RedirectAttributes attr) {
		
		
		
		lancamentoService.insert(lancamento);
		
		attr.addFlashAttribute("success", "Lancamento inserido com sucesso");
		return "redirect:/lancamentos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("lancamento", lancamentoService.find(id));
		return "/lancamento/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Lancamento lancamento, RedirectAttributes attr) {
		
		lancamentoService.update(lancamento);
		attr.addFlashAttribute("success", "Lancamento editado com sucesso.");
		return "redirect:/lancamentos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Integer id, ModelMap model) {
		
		return listar(model);
	}
	
	
	
	@ModelAttribute("viajantes")
	public List<Viajante> listaViajantes(){
			
		return viajanteService.findAll();
	}
	
	@ModelAttribute("reservas")
	public List<Reserva> listaReservas(){
			
		return reservaService.findAll();
	}
	
	@ModelAttribute("formasPagamento")
	public FormaPagamento[] listaFormaPagamento() {
		return FormaPagamento.values();
	}
}
