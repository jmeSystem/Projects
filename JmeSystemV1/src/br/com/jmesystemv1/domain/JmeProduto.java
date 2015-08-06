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

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Josï¿½ Wilian Classe responsï¿½vel por conter os atributos do Produto
 */

@NamedQueries({
		@NamedQuery(name = "JmeProduto.listar", query = "select Produto from JmeProduto Produto"),
		@NamedQuery(name = "JmeProduto.buscarPorCodigo", query = "select Produto from JmeProduto Produto where PRO_ID =:idProduto"),
		@NamedQuery(name = "JmeProduto.buscarPorCategoria", query = "select produto from JmeProduto produto where PRO_CAT_CODIGO=:idCategoria"),
		@NamedQuery(name = "JmeProduto.buscarPorFornecedor", query = "select produto from JmeProduto produto where PRO_FOR_ID=:idFornecedor")})
@Entity
@Table(name = "produto")
public class JmeProduto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRO_ID")
	private Integer idProduto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRO_FOR_ID", referencedColumnName = "FOR_ID", nullable = false)
	private JmeFornecedor fornecedor = new JmeFornecedor();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRO_CAT_CODIGO", referencedColumnName = "cat_codigo", nullable = false)
	private JmeCategoria categoria = new JmeCategoria();

	@Column(name = "PRO_COD", unique = true)
	@NotEmpty(message = "O campo código é obrigatório")
	private String codProduto;

	@Column(name = "PRO_DESCRICAO")
	@NotEmpty(message = "O campo descrição é obrigatório")
	private String descricao;

	@Column(name = "PRO_ESTOQUE_MIN")
	private Integer estoqueMin;

	@Column(name = "PRO_ESTOQUE_MAX")
	private Integer estoqueMax;

	@Column(name = "PRO_MODELO")
	private String modelo;

	
	@Column(name = "PRO_MARCA")
	private String marca;

	@Column(name = "PRO_VALOR_UNIT")
	private Double valorUnitario;

	@Column(name = "PRO_QUANT_ESTOQUE")
	private Integer quantEstoque;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "PRO_DATA_CADASTRO")
	private Date dataCadastro;

	/* ================================GET-SET======================== */

	public Integer getIdProduto() {
		return idProduto;
	}

	public JmeCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(JmeCategoria categoria) {
		this.categoria = categoria;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public JmeFornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(JmeFornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getEstoqueMin() {
		return estoqueMin;
	}

	public void setEstoqueMin(Integer estoqueMin) {
		this.estoqueMin = estoqueMin;
	}

	public Integer getEstoqueMax() {
		return estoqueMax;
	}

	public void setEstoqueMax(Integer estoqueMax) {
		this.estoqueMax = estoqueMax;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getQuantEstoque() {
		return quantEstoque;
	}

	public void setQuantEstoque(Integer quantEstoque) {
		this.quantEstoque = quantEstoque;
	}

	@Override
	public String toString() {
		return "JmeProduto [idProduto=" + idProduto + ", fornecedor="
				+ fornecedor + ", codProduto=" + codProduto + ", descricao="
				+ descricao + ", estoqueMin=" + estoqueMin + ", estoqueMax="
				+ estoqueMax + ", modelo=" + modelo + ", marca=" + marca
				+ ", valorUnitario=" + valorUnitario + ", quantEstoque="
				+ quantEstoque + ", dataCadastro=" + dataCadastro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idProduto == null) ? 0 : idProduto.hashCode());
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
		JmeProduto other = (JmeProduto) obj;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		return true;
	}

}
