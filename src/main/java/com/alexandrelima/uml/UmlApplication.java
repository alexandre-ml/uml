package com.alexandrelima.uml;

import java.text.SimpleDateFormat;
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
import com.alexandrelima.uml.domain.ItemPedido;
import com.alexandrelima.uml.domain.Pagamento;
import com.alexandrelima.uml.domain.PagamentoBoleto;
import com.alexandrelima.uml.domain.PagamentoCartao;
import com.alexandrelima.uml.domain.Pedido;
import com.alexandrelima.uml.domain.Produto;
import com.alexandrelima.uml.domain.enums.EstadoPagamento;
import com.alexandrelima.uml.domain.enums.TipoCliente;
import com.alexandrelima.uml.repositories.CategoriaRepository;
import com.alexandrelima.uml.repositories.CidadeRepository;
import com.alexandrelima.uml.repositories.ClienteRepository;
import com.alexandrelima.uml.repositories.EnderecoRepository;
import com.alexandrelima.uml.repositories.EstadoRepository;
import com.alexandrelima.uml.repositories.ItemPedidoRepository;
import com.alexandrelima.uml.repositories.PagamentoRepository;
import com.alexandrelima.uml.repositories.PedidoRepository;
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
	
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
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
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est1.getCidades().addAll(Arrays.asList(c2, c3));
				
		Cliente cli1 = new Cliente(null, "Alexandre L", "email.teste@gmail.com", "12345678901`", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("1235412", "1252125"));
		
		Endereco e1 = new Endereco(null, "nome rua", "numero", "complemento", "bairro", "1234567", cli1, c2);
		Endereco e2 = new Endereco(null, "nome rua2", "numero2", "complemento2", "bairro2", "12345672", cli1, c3);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pag1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/07/2017 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.0);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip3));
		ped2.getItens().addAll(Arrays.asList(ip2));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
