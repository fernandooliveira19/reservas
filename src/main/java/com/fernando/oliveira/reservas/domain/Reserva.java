package com.fernando.oliveira.reservas.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fernando.oliveira.reservas.domain.enums.FormaPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoReserva;
import com.fernando.oliveira.reservas.domain.enums.TipoReserva;
import com.fernando.oliveira.reservas.domain.utils.ReservaUtils;

@Entity
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	
	@Column(name = "DATA_ENTRADA")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime dataEntrada;

	@Column(name = "DATA_SAIDA")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime dataSaida;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SituacaoReserva situacaoReserva;
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private SituacaoPagamento situacaoPagamento;
	
	@NumberFormat(style=Style.CURRENCY, pattern="#,##0.00")
	@Column(name = "VLR_TOTAL", nullable = false, columnDefinition="DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal valorTotal;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_VIAJANTE")
	private Viajante viajante;

	@OneToMany(mappedBy = "reserva")
	private List<Lancamento> lancamentos;

	@OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
	private Contrato contrato;
	
	@NumberFormat(style=Style.CURRENCY, pattern="#,##0.00")
	@Column(name = "VLR_PENDENTE", nullable = false, columnDefinition="DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal valorPendente;
	
//	@Column(nullable = true, name="TIP_RESERVA")
//	@Enumerated(EnumType.STRING)
//	private TipoReserva tipoReserva;
	
	
	public Reserva() {

	}

	public Reserva(Integer id, LocalDateTime dataEntrada, LocalDateTime dataSaida, SituacaoReserva situacaoReserva,
			Viajante viajante, BigDecimal valorTotal, Contrato contrato) {
		
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.situacaoReserva = situacaoReserva;
		this.viajante = viajante;
		this.valorTotal = valorTotal;
		this.contrato = contrato;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public SituacaoReserva getSituacaoReserva() {
		return situacaoReserva;
	}

	public void setSituacaoReserva(SituacaoReserva situacaoReserva) {
		this.situacaoReserva = situacaoReserva;
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

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public SituacaoPagamento getSituacaoPagamento() {
		return situacaoPagamento;
	}

	public void setSituacaoPagamento(SituacaoPagamento situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento;
	}
	
	
	

	public BigDecimal getValorPendente() {
		return valorPendente;
	}

	public void setValorPendente(BigDecimal valorPendente) {
		this.valorPendente = valorPendente;
	}

	public String getDataEntradaFormatada() {
		
		return ReservaUtils.formatarDataLocal(dataEntrada);
	}
	
	
	public String getDataSaidaFormatada() {
		
		return ReservaUtils.formatarDataLocal(dataSaida);
	}

	

	
	
}
