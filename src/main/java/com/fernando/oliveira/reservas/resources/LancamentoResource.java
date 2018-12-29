package com.fernando.oliveira.reservas.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.domain.dto.LancamentoDTO;
import com.fernando.oliveira.reservas.service.LancamentoService;

@RestController
@RequestMapping(value="/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Lancamento obj){
		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody LancamentoDTO dto, @PathVariable Integer id){
		dto.setId(id);
		Lancamento obj = service.fromDTO(dto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<LancamentoDTO>> findAll() {
		
		List<Lancamento> list = service.findAll();
		List<LancamentoDTO> listDTO = list.stream().map(obj -> new LancamentoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Lancamento> find(@PathVariable Integer id){
		
		Lancamento obj = service.find(id);
		return ResponseEntity.ok(obj);
		
	}
	

}
