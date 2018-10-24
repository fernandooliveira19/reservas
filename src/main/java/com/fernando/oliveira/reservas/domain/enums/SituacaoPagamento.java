package com.fernando.oliveira.reservas.domain.enums;

public enum SituacaoPagamento {
	PENDENTE(1,"Pendente"),
	PAGO(2,"Pago");
	
	private Integer codigo;
	private String descricao;
	
	SituacaoPagamento(Integer codigo, String descricao) {
	
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
    public static SituacaoPagamento toEnum(Integer codigo) {
		
		if(codigo == null){
			return null;
		}
		
		for(SituacaoPagamento tipo: SituacaoPagamento.values()) {
			if(tipo.getCodigo().equals(codigo)) {
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("código inexistente: "+ codigo);
		
	}
}
