package br.com.douglas;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.douglas.domain.Categoria;
import br.com.douglas.domain.Cidade;
import br.com.douglas.domain.Cliente;
import br.com.douglas.domain.Endereco;
import br.com.douglas.domain.Estado;
import br.com.douglas.domain.ItemPedido;
import br.com.douglas.domain.PagamentoComBoleto;
import br.com.douglas.domain.PagamentoComCartao;
import br.com.douglas.domain.Pedido;
import br.com.douglas.domain.Produto;
import br.com.douglas.domain.enums.EstadoPagamento;
import br.com.douglas.domain.enums.TipoCliente;
import br.com.douglas.repository.CategoriaRepository;
import br.com.douglas.repository.CidadeRepository;
import br.com.douglas.repository.ClienteRepository;
import br.com.douglas.repository.EnderecoRepository;
import br.com.douglas.repository.EstadoRepository;
import br.com.douglas.repository.ItemPedidoRepository;
import br.com.douglas.repository.PedidoRepository;
import br.com.douglas.repository.ProdutoRepository;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
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
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Cama, mesa e banho");
		Categoria c3 = new Categoria(null, "Cama mesa e banho");
		Categoria c4 = new Categoria(null, "Eletrônicos");
		Categoria c5 = new Categoria(null, "Jardinagem");
		Categoria c6 = new Categoria(null, "Decoração");
		Categoria c7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cit1 = new Cidade(null, "Uberlândia", est1);
		Cidade cit2 = new Cidade(null, "São Paulo", est2);
		Cidade cit3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cit1));
		est2.getCidades().addAll(Arrays.asList(cit2, cit3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cit1, cit2, cit3));
		
		Cliente cli1 = new Cliente(null, "Douglas Silva", "dougailva226@gmail.com", "50651784026", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Douglas Monteles", "dougailva226@gmail.com", "61724797000164", TipoCliente.PESSOAJURIDICA);
		
		cli1.getTelefones().addAll(Arrays.asList("11 1111-1111", "22 2222-2222"));
		cli2.getTelefones().addAll(Arrays.asList("33 3333-3333"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, cit1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli2, cit2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1));
		cli2.getEnderecos().addAll(Arrays.asList(e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("22/07/2020 17:22"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("21/07/2020 13:10"), cli1, e2);
		
		PagamentoComCartao pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		PagamentoComBoleto pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("30/07/2020 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip2));
		p3.getItens().addAll(Arrays.asList(ip3));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
