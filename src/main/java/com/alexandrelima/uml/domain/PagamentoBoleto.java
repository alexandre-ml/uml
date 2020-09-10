package com.alexandrelima.uml.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.alexandrelima.uml.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoBoleto extends Pagamento{
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVenc;
	
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date dataPag;
	
	public PagamentoBoleto() {
		
	}

	public PagamentoBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVenc, Date dataPag) {
		super(id, estado, pedido);
		this.dataVenc = dataVenc;
		this.dataPag = dataPag;
	}

	public Date getDataVenc() {
		return dataVenc;
	}

	public void setDataVenc(Date dataVenc) {
		this.dataVenc = dataVenc;
	}

	public Date getDataPag() {
		return dataPag;
	}

	public void setDataPag(Date dataPag) {
		this.dataPag = dataPag;
	}
	
}
