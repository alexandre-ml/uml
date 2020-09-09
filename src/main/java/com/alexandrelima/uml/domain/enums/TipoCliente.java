package com.alexandrelima.uml.domain.enums;

public enum TipoCliente {
	PESSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descr;
	
	private TipoCliente(int cod, String descr) {
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
	
	static public TipoCliente toEnum(Integer cod) {
		
		if(cod == null)
			return null;
		
		for (TipoCliente x : TipoCliente.values()) {
			if(cod.equals(x.getCod()))
				return x;
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}
}
