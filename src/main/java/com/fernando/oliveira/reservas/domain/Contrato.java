package com.fernando.oliveira.reservas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contrato implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Reserva reserva;
	
	@Column(name="DESC_ENTRADA")
	private String descricaoEntrada;
	
	@Column(name="DESC_VALOR_TOTAL")
	private String descricaoValorTotal;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public String getDescricaoEntrada() {
		return descricaoEntrada;
	}
	public void setDescricaoEntrada(String descricaoEntrada) {
		this.descricaoEntrada = descricaoEntrada;
	}
	public String getDescricaoValorTotal() {
		return descricaoValorTotal;
	}
	public void setDescricaoValorTotal(String descricaoValorTotal) {
		this.descricaoValorTotal = descricaoValorTotal;
	}
	public String getTextoPagamento() {
		return textoPagamento;
	}
	public void setTextoPagamento(String textoPagamento) {
		this.textoPagamento = textoPagamento;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	private String textoPagamento;
	private String observacao;
	
	

}
