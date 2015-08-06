package br.com.jmesystemv1.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "registro_vendas")
@NamedQueries({
		@NamedQuery(name = "JmeRegistroVendas.listar", query = "select registro_vendas from JmeRegistroVendas registro_vendas"),
		@NamedQuery(name = "JmeRegistroVendas.buscarPorCodigo", query = "select registro_vendas from JmeRegistroVendas registro_vendas where RVE_ID =:idRegistroVenda"),
		@NamedQuery(name = "JmeRegistroVendas.excluirCodigoVenda", query = "DELETE from JmeRegistroVendas registro_vendas where RVE_ID=:idRegistroVenda"),
		@NamedQuery(name = "JmeRegistroVendas.buscarPorCliente", query = "select registroVendas from JmeRegistroVendas registroVendas where RVE_CLI_ID=:idCliente"),
		@NamedQuery(name = "JmeRegistroVendas.buscarPorFuncionario", query = "select registroVendas from JmeRegistroVendas registroVendas where REV_FUN_ID=:idFuncionario"),
		})
public class JmeRegistroVendas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RVE_ID")
	private Integer idRegistroVenda;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RVE_CLI_ID", referencedColumnName = "CLI_ID", nullable = false)
	private JmeCliente cliente = new JmeCliente();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REV_FUN_ID", referencedColumnName = "FUN_ID")
	private JmeFuncionario funcionario = new JmeFuncionario();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RVE_FPG_ID", referencedColumnName = "FPG_ID", nullable = false)
	private JmeFormaPagamento formaPagamento = new JmeFormaPagamento();

	@Column(name = "RVE_VALOR_TOTAL_VENDA")
	private Double valorTotal;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "RVE_DATA_VENDA")
	private Date dataVenda;

	@Column(name = "RVE_ACRESCIMO")
	private Double acrescimo;

	@Column(name = "RVE_DESCONTO")
	private Double desconto;

	@Column(name = "RVE_VALOR_PAGAR")
	private Double valorPagar;

	@Column(name = "RVE_SITUACAO")
	private String situacao;

	@Transient
	// é um anotação que é um campo temporário, existe no java, mais não na
	// tabela
	private Integer quantidade;

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public JmeFormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(JmeFormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(Double valorPagar) {
		this.valorPagar = valorPagar;
	}

	public Double getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(Double acrescimo) {
		this.acrescimo = acrescimo;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getIdRegistroVenda() {
		return idRegistroVenda;
	}

	public void setIdRegistroVenda(Integer idRegistroVenda) {
		this.idRegistroVenda = idRegistroVenda;
	}

	public JmeCliente getCliente() {
		return cliente;
	}

	public void setCliente(JmeCliente cliente) {
		this.cliente = cliente;
	}

	public JmeFuncionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(JmeFuncionario funcionario) {
		this.funcionario = funcionario;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	@Override
	public String toString() {
		return "JmeRegistroVendas [idRegistroVenda=" + idRegistroVenda
				+ ", cliente=" + cliente + ", funcionario=" + funcionario
				+ ", formaPagamento=" + formaPagamento + ", valorTotal="
				+ valorTotal + ", dataVenda=" + dataVenda + ", acrescimo="
				+ acrescimo + ", desconto=" + desconto + ", valorPagar="
				+ valorPagar + "]";
	}

}
