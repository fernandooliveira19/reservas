package com.fernando.oliveira.reservas.domain.enums;

public enum FormaPagamento {
	
	TRANSFERENCIA(1,"Transferencia"),
	DEPOSITO(2,"Depósito"),
	SITE(3,"Site"),
	LOCAL(4,"Local");
	
	private Integer codigo;
	private String descricao;
	
	FormaPagamento(Integer codigo, String descricao) {
	
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
    public static FormaPagamento toEnum(Integer codigo) {
		
		if(codigo == null){
			return null;
		}
		
		for(FormaPagamento tipo: FormaPagamento.values()) {
			if(tipo.getCodigo().equals(codigo)) {
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("código inexistente: "+ codigo);
		
	}

}
