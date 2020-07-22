package br.com.douglas.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.douglas.domain.enums.EstadoPagamento;

@Entity(name = "PagamentoComCartao")
@Table(name = "pagamento_com_cartao")
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	public Integer numeroDeParcelas;
	
	public PagamentoComCartao() {}

	public PagamentoComCartao(Integer id, EstadoPagamento pagamento, Pedido pedido, Integer numeroDeParcelas) {
		super(id, pagamento, pedido);
		this.setNumeroDeParcelas(numeroDeParcelas);
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
}
