package com.fernando.oliveira.reservas.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fernando.oliveira.reservas.domain.Telefone;
import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.domain.dto.ViajanteDTO;
import com.fernando.oliveira.reservas.service.TelefoneService;
import com.fernando.oliveira.reservas.service.ViajanteService;

@Controller
@RequestMapping("/viajantes")
public class ViajanteController {
	
	@Autowired
	private ViajanteService viajanteService;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Viajante viajante) {
		
		return "viajante/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		List<Viajante> viajantes = viajanteService.findAll();
		model.addAttribute("viajantes", viajantes);
		return "viajante/lista";
	}
	
	@GetMapping("/filter")
	public String filter(Viajante viajante) {
		return "viajante/filter";
	}
	
	@PostMapping("/salvar")
	public String salvar(Viajante viajante, RedirectAttributes attr) {
		
		telefoneService.insert(viajante.getTelefone());
		
		viajanteService.insert(viajante);
		
		attr.addFlashAttribute("success", "Viajante inserido com sucesso");
		return "redirect:cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("viajante", viajanteService.find(id));
		return "viajante/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Viajante viajante, RedirectAttributes attr) {
		telefoneService.update(viajante.getTelefone());
		viajanteService.update(viajante);
		attr.addFlashAttribute("success", "Viajante editado com sucesso.");
		return "redirect:cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Integer id, ModelMap model) {
		
		Viajante viajante = viajanteService.find(id);
		
		if (!viajante.getReservas().isEmpty()) {
			model.addAttribute("fail", "Viajante não removido. Possui reserva(s) vinculada(s).");
		} else {
			
			viajanteService.update(viajante);
			model.addAttribute("success", "Viajante inativado com sucesso.");
		}
		
		return listar(model);
	}
	
	@GetMapping("/find")
	public String filter(@RequestParam("nome") String nome, ModelMap model) {
		
		List<Viajante> list = viajanteService.findByNomeLike(nome);
		
		model.addAttribute("viajantes", list);
		
		return "viajante/filter"; 
	}
}
