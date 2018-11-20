package com.fernando.oliveira.reservas.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fernando.oliveira.reservas.domain.enums.SituacaoReserva;

@Entity
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "CODIGO")
	private String codigo;

	@Column(name = "DATA_ENTRADA")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataEntrada;

	@Column(name = "DATA_SAIDA")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataSaida;

	@Column(name = "SIT_RESERVA")
	private Integer situacaoReserva;
	
	@Column(name = "VLR_TOTAL")
	private Double valorTotal;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_VIAJANTE")
	private Viajante viajante;

	@OneToMany(mappedBy = "reserva")
	private List<Lancamento> lancamentos;

	@OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
	private Contrato contrato;

	public Reserva() {

	}

	public Reserva(Integer id, String codigo, Date dataEntrada, Date dataSaida, SituacaoReserva situacaoReserva,
			Viajante viajante, Double valorTotal, Contrato contrato) {
		
		this.id = id;
		this.codigo = codigo;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.situacaoReserva = situacaoReserva.getCodigo();
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public SituacaoReserva getSituacaoReserva() {
		return SituacaoReserva.toEnum(situacaoReserva);
	}

	public void setSituacaoReserva(SituacaoReserva situacaoReserva) {
		this.situacaoReserva = situacaoReserva.getCodigo();
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

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	
}
