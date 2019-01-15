package com.fernando.oliveira.reservas.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fernando.oliveira.reservas.domain.Contrato;
import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Viajante;

public class ReservaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String codigo;
//	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDateTime dataEntrada;
//	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDateTime dataSaida;
	private Integer situacaoReserva;
	private BigDecimal valorTotal;
	@JsonIgnore
	private Viajante viajante;
	private Contrato contrato;
	
	public ReservaDTO() {
		
	}
	public ReservaDTO(Integer id, String codigo, LocalDateTime dataEntrada, LocalDateTime dataSaida, Integer situacaoReserva,
			BigDecimal valorTotal, Viajante viajante, Contrato contrato) {
		
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
//		this.dataEntrada = DateUtils.parseDateToString(obj.getDataEntrada());
		this.dataEntrada = obj.getDataEntrada();
		this.dataSaida = obj.getDataSaida();
//		this.situacaoReserva = obj.getSituacaoReserva().getCodigo();
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
	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public LocalDateTime getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Integer getSituacaoReserva() {
		return situacaoReserva;
	}
	public void setSituacaoReserva(Integer situacaoReserva) {
		this.situacaoReserva = situacaoReserva;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
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
