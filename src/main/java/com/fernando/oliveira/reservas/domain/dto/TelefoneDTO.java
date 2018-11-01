package com.fernando.oliveira.reservas.domain.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fernando.oliveira.reservas.domain.Telefone;

public class TelefoneDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	private String ddd;
	
	
	private String numero;
	
	private Integer tipoTelefone;

	public TelefoneDTO(Telefone obj) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(Integer tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}
	
	

}
