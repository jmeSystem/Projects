package br.com.jmesystemv1.domain;

import java.io.Serializable;

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

@Entity
@Table(name="Itens_Pedidos")
@NamedQueries({
	@NamedQuery(name="JmeItensPedido.buscarPorItemPedido", 
			query="SELECT Itens_Pedidos FROM JmeItensPedido Itens_Pedidos WHERE ITP_ID=:idItensPedido"),
	@NamedQuery(name="JmeItensPedido.listar",
			query="SELECT Itens_Pedidos FROM JmeItensPedido Itens_Pedidos")})
public class JmeItensPedido implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ITP_ID")
	private Integer idItensPedido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ITP_PRO_ID", referencedColumnName="PRO_ID", nullable= false)
	private JmeProduto idProduto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ITP_PCO_NUMERO", referencedColumnName="PCO_NUMERO", nullable= false)
	private JmePedidoCompra idPedido;
	
	@Column(name="ITP_QUANTIDADE_PROD")
	private Integer quantidadeProduto;
	
	@Column(name="ITP_VALOR_UNIT_COMPRA")
	private Double valorUnitario;
	
	@Column(name="ITP_VALOR_TOTAL_ITENS")
	private Double valorTotalItens;

	public Integer getIdItensPedido() {
		return idItensPedido;
	}

	public void setIdItensPedido(Integer idItensPedido) {
		this.idItensPedido = idItensPedido;
	}

	public JmeProduto getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(JmeProduto idProduto) {
		this.idProduto = idProduto;
	}

	public JmePedidoCompra getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(JmePedidoCompra idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(Integer quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public Double getValorTotalItens() {
		return valorTotalItens;
	}

	public void setValorTotalItens(Double valorTotalItens) {
		this.valorTotalItens = valorTotalItens;
	}

	@Override
	public String toString() {
		return "JmeItensPedido [idItensPedido=" + idItensPedido
				+ ", idProduto=" + idProduto + ", idPedido=" + idPedido
				+ ", quantidadeProduto=" + quantidadeProduto
				+ ", valorUnitario=" + valorUnitario + ", valorTotalItens="
				+ valorTotalItens + "]";
	}

	
	
	
	
	

}
