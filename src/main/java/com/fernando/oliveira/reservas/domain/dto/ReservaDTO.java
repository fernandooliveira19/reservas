package com.fernando.oliveira.reservas.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fernando.oliveira.reservas.domain.Contrato;
import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Viajante;

public class ReservaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String codigo;
//	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private String dataEntrada;
//	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private String dataSaida;
	private Integer situacaoReserva;
	private String valorTotal;
	@JsonIgnore
	private Viajante viajante;
	private Contrato contrato;
	
	public ReservaDTO() {
		
	}
	public ReservaDTO(Integer id, String codigo, String dataEntrada, String dataSaida, Integer situacaoReserva,
			String valorTotal, Viajante viajante, Contrato contrato) {
		
		this.id = id;
		this.codigo = codigo;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.situacaoReserva = situacaoReserva;
		this.valorTotal = valorTotal;
		this.viajante = viajante;
		this.contrato = contrato;
	}
	public ReservaDTO(Reserva obj) {
		this.id = obj.getId();
		this.codigo = obj.getCodigo();
		this.dataEntrada = obj.getDataEntrada();
		this.dataSaida = obj.getDataSaida();
		this.situacaoReserva = obj.getSituacaoReserva().getCodigo();
		this.valorTotal = obj.getValorTotal();
		this.viajante = obj.getViajante();
		this.contrato = obj.getContrato();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Integer getSituacaoReserva() {
		return situacaoReserva;
	}
	public void setSituacaoReserva(Integer situacaoReserva) {
		this.situacaoReserva = situacaoReserva;
	}
	public String getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Viajante getViajante() {
		return viajante;
	}
	public void setViajante(Viajante viajante) {
		this.viajante = viajante;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	


}
