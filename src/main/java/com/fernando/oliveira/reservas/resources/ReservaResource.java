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

import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.dto.ReservaDTO;
import com.fernando.oliveira.reservas.service.ReservaService;

@RestController
@RequestMapping(value="/reservas")
public class ReservaResource {
	
	@Autowired
	private ReservaService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ReservaDTO dto){
		Reserva obj = service.fromDTO(dto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ReservaDTO dto, @PathVariable Integer id){
		dto.setId(id);
		Reserva obj = service.fromDTO(dto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ReservaDTO>> findAll() {
		
		List<Reserva> list = service.findAll();
		List<ReservaDTO> listDTO = list.stream().map(obj -> new ReservaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Reserva> find(@PathVariable Integer id){
		
		Reserva obj = service.find(id);
		return ResponseEntity.ok(obj);
		
	}
	

}
