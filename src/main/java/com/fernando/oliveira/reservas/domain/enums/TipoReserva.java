package com.fernando.oliveira.reservas.domain.enums;

public enum TipoReserva {
	
	SINAL_PAGO("","");
	
	private String codigo;
	private String descricao;
	
	TipoReserva(String codigo, String descricao) {
	
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
    public static TipoReserva toEnum(String codigo) {
		
		if(codigo == null){
			return null;
		}
		
		for(TipoReserva tipo: TipoReserva.values()) {
			if(tipo.getCodigo().equals(codigo)) {
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("código inexistente: "+ codigo);
		
	}

}
