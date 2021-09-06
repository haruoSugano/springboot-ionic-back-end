package com.haruo.cursospring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.haruo.cursospring.domain.Categoria;
import com.haruo.cursospring.domain.Cidade;
import com.haruo.cursospring.domain.Cliente;
import com.haruo.cursospring.domain.Endereco;
import com.haruo.cursospring.domain.Estado;
import com.haruo.cursospring.domain.ItemPedido;
import com.haruo.cursospring.domain.Pagamento;
import com.haruo.cursospring.domain.PagamentoComBoleto;
import com.haruo.cursospring.domain.PagamentoComCartao;
import com.haruo.cursospring.domain.Pedido;
import com.haruo.cursospring.domain.Produto;
import com.haruo.cursospring.domain.enums.EstadoPagamento;
import com.haruo.cursospring.domain.enums.TipoCliente;
import com.haruo.cursospring.repositories.CategoriaRepository;
import com.haruo.cursospring.repositories.CidadeRepository;
import com.haruo.cursospring.repositories.ClienteRepository;
import com.haruo.cursospring.repositories.EnderecoRepository;
import com.haruo.cursospring.repositories.EstadoRepository;
import com.haruo.cursospring.repositories.ItemPedidoRepository;
import com.haruo.cursospring.repositories.PagamentoRepository;
import com.haruo.cursospring.repositories.PedidoRepository;
import com.haruo.cursospring.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner{ // CommandLineRunner, ao executar a aplicacao permite que, o metódo inicie com uma ação. 
	
	// Criando as dependências:
	@Autowired
	private CategoriaRepository categoriaRepository; // É o responsável  por salvar as categorias
	@Autowired
	private ProdutoRepository produtoRepository; // Responsável por salvar os produtos
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	// Método criado pelo CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		/**
		 * Instanciando os objetos:
		 */
		Categoria categoria1 = new Categoria(null, "Informatica"); // BD gera ID automaticamente. por isso é null.
		Categoria categoria2 = new Categoria(null, "Escritorio");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		
		/**
		 * Associando Categoria com produtos e Produto com categorias:
		 */
		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3)); // Produtos associados com categoria1
		categoria2.getProdutos().addAll(Arrays.asList(produto2)); // Produtos associados com categoria2
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1)); // Categoria associados com produtos
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));
		
		// Salvando categoria ao banco D:
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2)); // Chamando -> categoriaRepository e uma função para salvar -> saveAll() 
		// Salvando produtos ao banco D:
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		// Arrays.asList() criar uma lista automatico.
		
		/**
		 * Estado e Cidade
		 */
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlandia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		/**
		 * Cliente, endereço e telefone
		 */
		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12345678912", TipoCliente.PESSOAFISICA);
		
		cliente1.getTelefones().addAll(Arrays.asList("1234567891", "123456789"));
		
		clienteRepository.saveAll(Arrays.asList(cliente1));
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "1234567891", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "1234567891", cliente1, cidade2);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2021 10:32"),  cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("10/10/2021 19:35"), cliente1, endereco2);
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("20/10/2021 00:00"), null);
		pedido2.setPagamento(pagamento2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		
		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 0.00, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, produto3, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 100.00, 1, 800.00);
		
		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));
		
		produto1.getItens().addAll(Arrays.asList(itemPedido1));
		produto2.getItens().addAll(Arrays.asList(itemPedido3));
		produto3.getItens().addAll(Arrays.asList(itemPedido2));
		
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
	}
}
