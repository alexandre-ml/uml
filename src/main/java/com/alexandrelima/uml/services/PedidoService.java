package com.alexandrelima.uml.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandrelima.uml.domain.Pedido;
import com.alexandrelima.uml.repositories.PedidoRepository;
import com.alexandrelima.uml.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "
				+ id + ", Tipo: " + Pedido.class.getName()));
	}
}
