package br.com.douglas.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.douglas.domain.enums.EstadoPagamento;

@Entity(name = "PagamentoComBoleto")
@Table(name = "pagamento_com_boleto")
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Date dataVencimento;
	private Date dataPagamento;
	
	public PagamentoComBoleto() {}

	public PagamentoComBoleto(Integer id, EstadoPagamento pagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, pagamento, pedido);
		this.setDataVencimento(dataVencimento);
		this.setDataPagamento(dataPagamento);
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
}
