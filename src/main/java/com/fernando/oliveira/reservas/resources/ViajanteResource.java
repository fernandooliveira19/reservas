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

import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.domain.dto.ViajanteDTO;
import com.fernando.oliveira.reservas.service.ViajanteService;

@RestController
@RequestMapping(value="/viajantes")
public class ViajanteResource {
	
	@Autowired
	private ViajanteService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ViajanteDTO dto){
		Viajante obj = service.fromDTO(dto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ViajanteDTO dto, @PathVariable Integer id){
		dto.setId(id);
		Viajante obj = service.fromDTO(dto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/findAll",method=RequestMethod.GET)
	public ResponseEntity<List<ViajanteDTO>> findAll() {
		
		List<Viajante> list = service.findAll();
		List<ViajanteDTO> listDTO = list.stream().map(obj -> new ViajanteDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Viajante> find(@PathVariable Integer id){
		
		Viajante obj = service.find(id);
		return ResponseEntity.ok(obj);
		
	}
	
	@RequestMapping(value="/findByName/{nome}", method=RequestMethod.GET)
	public List<Viajante> findByNomeLike(@PathVariable String nome) {
		
		List<Viajante> lista = service.findByNomeLike(nome);
		return lista;
	}
	

}
