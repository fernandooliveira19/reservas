package com.fernando.oliveira.reservas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fernando.oliveira.reservas.domain.enums.FormaPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoPagamento;

@Entity
public class Lancamento implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="DAT_LANCAMENTO")
	private Date dataLancamento;
	
	@Column(name="VLR_LANCAMENTO")
	private Double valorLancamento;
	
	@Column(name="FRM_PAGAMENTO")
	private Integer formaPagamento;
	
	@Column(name="SIT_LANCAMENTO")
	private Integer situacaoPagamento;
	
	@ManyToOne
	@JoinColumn(name="ID_VLR_RESERVA")
	private ValorReserva valorReserva;
	
	public Lancamento() {
		
	}

	public Lancamento(Integer id, Date dataLancamento, Double valorLancamento, FormaPagamento formaPagamento,
			SituacaoPagamento situacaoPagamento, ValorReserva valorReserva) {
		
		this.id = id;
		this.dataLancamento = dataLancamento;
		this.valorLancamento = valorLancamento;
		this.formaPagamento = formaPagamento.getCodigo();
		this.situacaoPagamento = situacaoPagamento.getCodigo();
		this.valorReserva = valorReserva;
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

	public FormaPagamento getFormaPagamento() {
		return FormaPagamento.toEnum(formaPagamento);
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento.getCodigo();
	}

	public SituacaoPagamento getSituacaoPagamento() {
		return SituacaoPagamento.toEnum(situacaoPagamento);
	}

	public void setSituacaoPagamento(SituacaoPagamento situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento.getCodigo();
	}

	public ValorReserva getValorReserva() {
		return valorReserva;
	}

	public void setValorReserva(ValorReserva valorReserva) {
		this.valorReserva = valorReserva;
	}
	
	
}
