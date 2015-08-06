package br.com.jmesystemv1.domain;

import java.io.Serializable;
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

@Entity
@Table(name="Pedido_Compra")
@NamedQueries({
	@NamedQuery(name="JmePedidoCompra.buscarPorNumeroPedido", 
			query="SELECT Pedido_Compra FROM JmePedidoCompra Pedido_Compra WHERE PCO_NUMERO=:numeroPedido"),
	@NamedQuery(name="JmePedidoCompra.listar", 
			query="SELECT Pedido_Compra FROM JmePedidoCompra Pedido_Compra")})

public class JmePedidoCompra implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Chave primaria da tabela pedido compra,
	 * que serve tambem como o numero do pedido gerado*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PCO_NUMERO")
	private Integer numeroPedido;
	
	/** Chave estrangeira do Fornecedor
	 * Um fornecedor pode ter varios pedidos de compra, e
	 * um pedido e referente a um unico fornecedor*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PCO_FOR_ID", referencedColumnName="FOR_ID", nullable=false)
	private JmeFornecedor idFornecedor = new JmeFornecedor();
	
	/** Chave estrangeira do Funcionario
	 * Um funcionario pode realizar varios pedidos de compra, e
	 * um pedido e referente a um unico funcionario*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PCO_FUN_ID", referencedColumnName="FUN_ID", nullable=false)
	private JmeFuncionario idFuncionario = new JmeFuncionario();
	
	/** Data de abertura do pedido de compra*/
	@Temporal(value = TemporalType.DATE)
	@Column(name="PCO_DATA_ABERTURA")
	private Date dataAbertura;
	
	/** Status do pedido de compra.
	 * Exemplo.: A - Aberto, R - Realizado e L - Liquidado*/
	@Column(name="PCO_STATUS")
	private String statusPedido;
	
	@Column(name="PCO_VALOR_TOTAL")
	private Double valorTotal;
	
	
	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public JmeFornecedor getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(JmeFornecedor idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public JmeFuncionario getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(JmeFuncionario idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	

	@Override
	public String toString() {
		return "JmePedidoCompra [numeroPedido=" + numeroPedido
				+ ", idFornecedor=" + idFornecedor + ", idFuncionario="
				+ idFuncionario + ", dataAbertura=" + dataAbertura
				+ ", statusPedido=" + statusPedido + ", valorTotal="
				+ valorTotal + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((numeroPedido == null) ? 0 : numeroPedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JmePedidoCompra other = (JmePedidoCompra) obj;
		if (numeroPedido == null) {
			if (other.numeroPedido != null)
				return false;
		} else if (!numeroPedido.equals(other.numeroPedido))
			return false;
		return true;
	}
	
	
	

}
