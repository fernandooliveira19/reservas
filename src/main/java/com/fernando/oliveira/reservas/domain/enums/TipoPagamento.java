package com.fernando.oliveira.reservas.domain.enums;

public enum TipoPagamento {
	
	TRANSFERENCIA(1,"Transferencia"),
	DEPOSITO(2,"Depósito"),
	SITE(3,"Site"),
	LOCAL(4,"Local");
	
	private Integer codigo;
	private String descricao;
	
	TipoPagamento(Integer codigo, String descricao) {
	
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
    public static TipoPagamento toEnum(Integer codigo) {
		
		if(codigo == null){
			return null;
		}
		
		for(TipoPagamento tipo: TipoPagamento.values()) {
			if(tipo.getCodigo().equals(codigo)) {
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("código inexistente: "+ codigo);
		
	}

}
