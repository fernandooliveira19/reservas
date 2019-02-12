package com.fernando.oliveira.reservas.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fernando.oliveira.reservas.domain.enums.FormaPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoPagamento;
import com.fernando.oliveira.reservas.domain.utils.ReservaUtils;

@Entity
public class Lancamento implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "DAT_LANCAMENTO")
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataLancamento;
	
	@NumberFormat(style=Style.CURRENCY, pattern="#,##0.00")
	@Column(name = "VLR_LANCAMENTO", nullable = false, columnDefinition="DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal valorLancamento;
	
	@Column(nullable = true, name="FRM_PAGAMENTO")
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;
	
	@Column(name="SIT_LANCAMENTO")
	@Enumerated(EnumType.STRING)
	private SituacaoPagamento situacaoPagamento;
	
	@Column(name="DAT_PAGAMENTO")
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPagamento;
	
	@Column(name="PGTO_SINAL")
	private Boolean pagamentoSinal;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ID_RESERVA")
	private Reserva reserva;
	
	public Lancamento() {
		
	}

	public Lancamento(Integer id, LocalDate dataLancamento, BigDecimal valorLancamento, FormaPagamento formaPagamento,
			SituacaoPagamento situacaoPagamento, Reserva reserva,LocalDate dataPagamento) {
		
		this.id = id;
		this.dataLancamento = dataLancamento;
		this.valorLancamento = valorLancamento;
		this.formaPagamento = formaPagamento;
		this.situacaoPagamento = situacaoPagamento;
		this.reserva = reserva;
		this.dataPagamento = dataPagamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public SituacaoPagamento getSituacaoPagamento() {
		return situacaoPagamento;
	}

	public void setSituacaoPagamento(SituacaoPagamento situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Boolean getPagamentoSinal() {
		return pagamentoSinal;
	}

	public void setPagamentoSinal(Boolean pagamentoSinal) {
		this.pagamentoSinal = pagamentoSinal;
	}

	public String getValorFormatado() {
		return ReservaUtils.formatarValorMonetario(valorLancamento);
	}
	
	public String getDataLancamentoFormatada() {
		return ReservaUtils.formatarDataLocal(dataLancamento);
	}
	
	
	
}
