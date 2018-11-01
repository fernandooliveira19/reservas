package com.fernando.oliveira.reservas.domain.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fernando.oliveira.reservas.domain.Viajante;

public class ViajanteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Column(name="NOME", nullable=false)
	@NotEmpty(message="Preenchimento obrigatório")
	@Size(min=5, max=100, message="O campo nome deve ter entre 5 e 100 caracteres")
	private String nome;
	
	@Column(name="EMAIL", nullable=false)
	@NotEmpty(message="Preenchimento obrigatório")
	@Email
	private String email;
	
	public ViajanteDTO() {
		
	}
	
	public ViajanteDTO(Viajante obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
