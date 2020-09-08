package com.alexandrelima.uml;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alexandrelima.uml.domain.Categoria;
import com.alexandrelima.uml.repositories.CategoriaRepository;

@SpringBootApplication
public class UmlApplication implements CommandLineRunner{
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(UmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Categoria 1");
		Categoria cat2 = new Categoria(null, "Categoria 2");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
