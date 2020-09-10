package com.alexandrelima.uml.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descr;
	
	private EstadoPagamento(int cod, String descr) {
		this.cod = cod;
		this.descr = descr;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	static public EstadoPagamento toEnum(Integer cod) {
		
		if(cod == null)
			return null;
		
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCod()))
				return x;
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}
}
