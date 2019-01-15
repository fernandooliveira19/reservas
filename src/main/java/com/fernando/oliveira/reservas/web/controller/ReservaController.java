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

import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.domain.enums.FormaPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoReserva;
import com.fernando.oliveira.reservas.service.ReservaService;
import com.fernando.oliveira.reservas.service.ViajanteService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private ViajanteService viajanteService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Reserva reserva) {
		return "/reserva/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		List<Reserva> reservas = reservaService.findAll();
		model.addAttribute("reservas", reservas);
		return "/reserva/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Reserva reserva, RedirectAttributes attr) {
//		Reserva reserva = new Reserva(dto); 
		reservaService.insert(reserva);
		
		attr.addFlashAttribute("success", "Reserva inserida com sucesso");
		return "redirect:/reservas/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("reserva", reservaService.find(id));
		return "/reserva/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Reserva reserva, RedirectAttributes attr) {
		reservaService.update(reserva);
		attr.addFlashAttribute("success", "Reserva editada com sucesso.");
		return "redirect:/reservas/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Integer id, ModelMap model) {
		
		Reserva reserva = reservaService.find(id);
		
			
			reserva.setSituacaoReserva(SituacaoReserva.CANCELADO);
			reservaService.update(reserva);
			model.addAttribute("success", "Reserva cancelada com sucesso.");
		
		return listar(model);
	}
	
	@ModelAttribute("viajantes")
	public List<Viajante> listaViajantes(){
			
		return viajanteService.findAll();
	}
	
	@ModelAttribute("situacoesReserva")
	public SituacaoReserva[] listaSituacoes() {
		return SituacaoReserva.values();
	}
	
	@PostMapping("/adicionarLancamento")
	public String adicionarLancamento(Reserva reserva, RedirectAttributes attr) {
//		reservaService.update(reserva);
		attr.addFlashAttribute("success", "Reserva editada com sucesso.");
		return "redirect:/reservas/cadastrar";
	}
	
	@ModelAttribute("formasPagamento")
	public FormaPagamento[] listaFormaPagamento() {
		return FormaPagamento.values();
	}

	@ModelAttribute("situacoesPagamento")
	public SituacaoPagamento[] listaSituacaoPagamento() {
		return SituacaoPagamento.values();
	}

}
