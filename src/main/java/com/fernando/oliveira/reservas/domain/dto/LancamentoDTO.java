package com.fernando.oliveira.reservas.domain.dto;

import java.util.Date;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.domain.Reserva;

public class LancamentoDTO {

	private Integer id;
	private Date dataLancamento;
	private Double valorLancamento;
	private Integer formaPagamento;
	private Integer situacaoPagamento;
	private Date dataPagamento;
	private Reserva reserva;
	
	public LancamentoDTO() {
		
	}
	
	public LancamentoDTO(Lancamento obj) {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public Double getValorLancamento() {
		return valorLancamento;
	}
	public void setValorLancamento(Double valorLancamento) {
		this.valorLancamento = valorLancamento;
	}
	public Integer getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(Integer formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Integer getSituacaoPagamento() {
		return situacaoPagamento;
	}
	public void setSituacaoPagamento(Integer situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	
}
