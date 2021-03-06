package com.fernando.oliveira.reservas.domain;

import java.io.Serializable;

public class Contrato implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Reserva reserva;
	private String descricaoEntrada;
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
