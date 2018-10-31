package com.fernando.oliveira.reservas.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Viajante implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NOME", nullable=false)
	@NotEmpty(message="Preenchimento obrigatório")
	@Size(min=5, max=100, message="O campo nome deve ter entre 5 e 100 caracteres")
	private String nome;
	
	@Column(name="EMAIL", nullable=false)
	@NotEmpty(message="Preenchimento obrigatório")
	@Email
	private String email;
	
	@Column(name="DATA_INCLUSAO", nullable=false)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataInclusao;
	
	
	/**
	 * Relationships
	 */
	@OneToMany(mappedBy="viajante")
	private List<Telefone> telefones;
	
	@OneToMany(mappedBy="viajante")
	private List<Reserva> reservas;
	
	public Viajante() {
		
	}
	
	
	public Viajante(Integer id,
			@NotEmpty(message = "Preenchimento obrigatório") @Size(min = 5, max = 100, message = "O campo nome deve ter entre 5 e 100 caracteres") String nome,
			@NotEmpty(message = "Preenchimento obrigatório") @Email String email, Date dataInclusao) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataInclusao = dataInclusao;
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
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public Date getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	

}
