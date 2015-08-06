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
@Table(name = "itens_venda")
@NamedQueries({
		@NamedQuery(name = "JmeItensVenda.listar", query = "select itens_venda from JmeItensVenda itens_venda"),
		@NamedQuery(name = "JmeItensVenda.buscarPorCodigo", query = "select itens_venda from JmeItensVenda itens_venda where ITV_ID =:idItensVenda"),
		@NamedQuery(name = "JmeItensVenda.buscarPorCodigoVenda", query = "select itens_venda from JmeItensVenda itens_venda where ITV_RVE_ID=:idItensVenda"),
		@NamedQuery(name = "JmeItensVenda.buscarPorProduto", query = "select itens_venda from JmeItensVenda itens_venda where ITV_PRO_ID=:idProduto"),
		@NamedQuery(name = "JmeItensVenda.excluirCodigoVenda", query = "DELETE from JmeItensVenda itens_venda where ITV_RVE_ID=:idRegistroVenda")

})
public class JmeItensVenda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ITV_ID")
	private Integer idItensVenda;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITV_RVE_ID", referencedColumnName = "RVE_ID", nullable = false)
	private JmeRegistroVendas registroVendas;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITV_PRO_ID", referencedColumnName = "PRO_ID", nullable = false)
	private JmeProduto produto;

	@Column(name = "ITV_VALOR_TOTAL_ITENS")
	private Double valorTotalItens;

	@Column(name = "ITV_QUANTIDADE")
	private Integer quantidade;

	@Column(name = "ITV_VALOR_UNIT_ITENS")
	private Double valorUnitario;

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getIdItensVenda() {
		return idItensVenda;
	}

	public void setIdItensVenda(Integer idItensVenda) {
		this.idItensVenda = idItensVenda;
	}

	public JmeRegistroVendas getRegistroVendas() {
		return registroVendas;
	}

	public void setRegistroVendas(JmeRegistroVendas registroVendas) {
		this.registroVendas = registroVendas;
	}

	public JmeProduto getProduto() {
		return produto;
	}

	public void setProduto(JmeProduto produto) {
		this.produto = produto;
	}

	public Double getValorTotalItens() {
		return valorTotalItens;
	}

	public void setValorTotalItens(Double valorTotalItens) {
		this.valorTotalItens = valorTotalItens;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	// Funciona para exibir os dados no log
	@Override
	public String toString() {
		return "JmeItensVenda [idItensVenda=" + idItensVenda
				+ ", registroVendas=" + registroVendas + ", produto=" + produto
				+ ", valorTotalItens=" + valorTotalItens + ", quantidade="
				+ quantidade + "]";
	}

	// funcionar para excluir o objeto itens de venda
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idItensVenda == null) ? 0 : idItensVenda.hashCode());
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
		JmeItensVenda other = (JmeItensVenda) obj;
		if (idItensVenda == null) {
			if (other.idItensVenda != null)
				return false;
		} else if (!idItensVenda.equals(other.idItensVenda))
			return false;
		return true;
	}

}
