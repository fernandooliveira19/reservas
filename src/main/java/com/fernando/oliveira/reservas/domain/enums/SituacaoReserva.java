package com.fernando.oliveira.reservas.domain.enums;

public enum SituacaoReserva {

	RESERVADO("R","Reservado"),
	CANCELADO("C","Cancelado"),
	PRE_RESERVA("P","Pré Reservado"),
	FINALIZADO("F","Finalizado");
	
	private String codigo;
	private String descricao;
	
	SituacaoReserva(String codigo, String descricao) {
	
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
    public static SituacaoReserva toEnum(String codigo) {
		
		if(codigo == null){
			return null;
		}
		
		for(SituacaoReserva tipo: SituacaoReserva.values()) {
			if(tipo.getCodigo().equals(codigo)) {
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("código inexistente: "+ codigo);
		
	}
}
