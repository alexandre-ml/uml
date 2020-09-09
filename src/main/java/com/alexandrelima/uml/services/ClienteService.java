package com.alexandrelima.uml.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandrelima.uml.domain.Cliente;
import com.alexandrelima.uml.repositories.ClienteRepository;
import com.alexandrelima.uml.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "
				+ id + ", Tipo: " + Cliente.class.getName()));
	}
}
