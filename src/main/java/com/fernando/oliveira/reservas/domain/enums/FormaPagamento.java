package com.fernando.oliveira.reservas.domain.enums;

public enum FormaPagamento {
	
	TRANSFERENCIA("T","Transferencia"),
	DEPOSITO("D","Depósito"),
	SITE("S","Site"),
	LOCAL("L","Local");
	
	private String codigo;
	private String descricao;
	
	FormaPagamento(String codigo, String descricao) {
	
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
    public static FormaPagamento toEnum(String codigo) {
		
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
