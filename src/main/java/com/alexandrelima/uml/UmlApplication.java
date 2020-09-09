package com.alexandrelima.uml;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alexandrelima.uml.domain.Categoria;
import com.alexandrelima.uml.domain.Cidade;
import com.alexandrelima.uml.domain.Cliente;
import com.alexandrelima.uml.domain.Endereco;
import com.alexandrelima.uml.domain.Estado;
import com.alexandrelima.uml.domain.Produto;
import com.alexandrelima.uml.domain.enums.TipoCliente;
import com.alexandrelima.uml.repositories.CategoriaRepository;
import com.alexandrelima.uml.repositories.CidadeRepository;
import com.alexandrelima.uml.repositories.ClienteRepository;
import com.alexandrelima.uml.repositories.EnderecoRepository;
import com.alexandrelima.uml.repositories.EstadoRepository;
import com.alexandrelima.uml.repositories.ProdutoRepository;

@SpringBootApplication
public class UmlApplication implements CommandLineRunner{
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(UmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Categoria 1");
		Categoria cat2 = new Categoria(null, "Categoria 2");
		
		Produto p1 = new Produto(null, "Produto 1", 2000.00);
		Produto p2 = new Produto(null, "Produto 2", 800.00);
		Produto p3 = new Produto(null, "Produto 3", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est1.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Alexandre L", "email.teste@gmail.com", "12345678901`", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("1235412", "1252125"));
		
		Endereco e1 = new Endereco(null, "nome rua", "numero", "complemento", "bairro", "1234567", cli1, c2);
		Endereco e2 = new Endereco(null, "nome rua2", "numero2", "complemento2", "bairro2", "12345672", cli1, c3);
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}
