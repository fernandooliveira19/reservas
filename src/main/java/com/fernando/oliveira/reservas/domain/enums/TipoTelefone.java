package com.fernando.oliveira.reservas.domain.enums;

public enum TipoTelefone {

	CELULAR(1,"Celular"),
	COMERCIAL(2,"Comercial"),
	RESIDENCIAL(3,"Residencial");
	private Integer codigo;
	private String descricao;
	
	TipoTelefone(Integer codigo, String descricao) {
	
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
    public static TipoTelefone toEnum(Integer codigo) {
		
		if(codigo == null){
			return null;
		}
		
		for(TipoTelefone tipo: TipoTelefone.values()) {
			if(tipo.getCodigo().equals(codigo)) {
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("código inexistente: "+ codigo);
		
	}

	
	
	
}
