package com.fernando.oliveira.reservas.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.fernando.oliveira.reservas.domain.enums.SituacaoPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoReserva;

@Entity
public class Reserva implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="CODIGO")
	private String codigo;
	
	@Column(name="DATA_ENTRADA", nullable=false)
	@NotEmpty(message="Preenchimento obrigatório")
	private Date dataEntrada;
	
	@Column(name="DATA_SAIDA", nullable=false)
	@NotEmpty(message="Preenchimento obrigatório")
	private Date dataSaida;
	
	@Column(name="VALOR_RESERVA", nullable=false)
	@NotEmpty(message="Preenchimento obrigatório")
	private Double valorTotal;
	
	@Column(name="VALOR_PAGO")
	private Double valorPago;
	
	@Column(name="DATA_PENDENTE")
	private Double valorPendente;
	
	@Column(name="SIT_RESERVA")
	private SituacaoReserva situacaoReserva;
	
	
	private Viajante viajante;
	
	@Column(name="SIT_PAGAMENTO")
	private SituacaoPagamento situacaoPagamento;
	

	private List<Pagamento> pagamentos;
	
	
	private Contrato contrato;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public SituacaoPagamento getSituacaoPagamento() {
		return situacaoPagamento;
	}
	public void setSituacaoPagamento(SituacaoPagamento situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento;
	}
	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}
	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	

}
