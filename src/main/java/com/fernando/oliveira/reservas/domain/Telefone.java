package com.fernando.oliveira.reservas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fernando.oliveira.reservas.domain.enums.TipoTelefone;

@Entity
public class Telefone implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="DDD", nullable=false)
	@NotEmpty(message="Preenchimento obrigatório")
	@Size(min=2, max=2, message="O campo ddd deve ter 2 digitos")
	private String ddd;
	
	@Column(name="NUMERO", nullable=false)
	@NotEmpty(message="Preenchimento obrigatório")
	@Size(min=8, max=9, message="O campo nome deve ter entre 8 e 9 dígitos")
	private String numero;
	
	@Column(name="TIP_TELEFONE")
	private Integer tipoTelefone;
	
	/**
	 * Relationships
	 */
	
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="ID_VIAJANTE")
	private Viajante viajante;

	public Telefone() {
		
	}
	
	public Telefone(Integer id,
			@NotEmpty(message = "Preenchimento obrigatório") @Size(min = 2, max = 2, message = "O campo ddd deve ter 2 digitos") String ddd,
			@NotEmpty(message = "Preenchimento obrigatório") @Size(min = 8, max = 9, message = "O campo nome deve ter entre 8 e 9 dígitos") String numero,
			TipoTelefone tipoTelefone, Viajante viajante) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
		this.tipoTelefone = tipoTelefone.getCodigo();
		this.viajante = viajante;
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
	public TipoTelefone getTipoTelefone() {
		return TipoTelefone.toEnum(tipoTelefone);
	}
	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone.getCodigo();
	}
	public Viajante getViajante() {
		return viajante;
	}
	public void setViajante(Viajante viajante) {
		this.viajante = viajante;
	}
	
	

}
