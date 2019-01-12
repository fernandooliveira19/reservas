package com.fernando.oliveira.reservas.domain;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fernando.oliveira.reservas.domain.dto.ReservaDTO;
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
	
	@Column(name = "VLR_TOTAL", nullable = false)
	private String valorTotal;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_VIAJANTE")
	private Viajante viajante;

	@OneToMany(mappedBy = "reserva")
	private List<Lancamento> lancamentos;

	@OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
	private Contrato contrato;

	public Reserva() {

	}

	public Reserva(Integer id, String codigo, LocalDateTime dataEntrada, LocalDateTime dataSaida, SituacaoReserva situacaoReserva,
			Viajante viajante, String valorTotal, Contrato contrato) {
		
		this.id = id;
		this.codigo = codigo;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.situacaoReserva = situacaoReserva;
		this.viajante = viajante;
		this.valorTotal = valorTotal;
		this.contrato = contrato;
	}
	
//	public Reserva (ReservaDTO dto) {
//		this.id = dto.getId();
//		this.codigo = dto.getCodigo();
////		this.dataEntrada = DateUtils.parseStringToDate(dto.getDataEntrada(),"");
////		this.dataSaida = dto.getDataSaida();
//		this.situacaoReserva = dto.getSituacaoReserva();
//		this.valorTotal = dto.getValorTotal();
//		this.viajante = dto.getViajante();
//		this.contrato = dto.getContrato();
//	}

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

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	
}
