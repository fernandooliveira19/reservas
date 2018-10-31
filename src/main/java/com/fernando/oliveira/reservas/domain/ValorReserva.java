package com.fernando.oliveira.reservas.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ValorReserva implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="VLR_TOTAL")
	private Double valorTotal;
	
	@Column(name="VLR_PAGO")
	private Double valorPago;
	
	@Column(name="VLR_PENDENTE")
	private Double valorPendente;
	
	@OneToOne
	@JoinColumn(name="ID_RESERVA")
	@JsonIgnore
	private Reserva reserva;
	
	@OneToMany(mappedBy="valorReserva")
	private List<Lancamento> lancamentos;
	
	public ValorReserva() {
		
	}

	public ValorReserva(Integer id, Double valorTotal, Double valorPago, Double valorPendente, Reserva reserva) {
		
		this.id = id;
		this.valorTotal = valorTotal;
		this.valorPago = valorPago;
		this.valorPendente = valorPendente;
		this.reserva = reserva;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public Double getValorPendente() {
		return valorPendente;
	}

	public void setValorPendente(Double valorPendente) {
		this.valorPendente = valorPendente;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	

}
