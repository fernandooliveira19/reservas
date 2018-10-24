package com.fernando.oliveira.reservas.domain.enums;

public enum SituacaoReserva {

	RESERVADO(1,"Reservado"),
	CANCELADO(2,"Cancelado"),
	PRE_RESERVA(3,"Pré Reservado");
	
	private Integer codigo;
	private String descricao;
	
	SituacaoReserva(Integer codigo, String descricao) {
	
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
    public static SituacaoReserva toEnum(Integer codigo) {
		
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
