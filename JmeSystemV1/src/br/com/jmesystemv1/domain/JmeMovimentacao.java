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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "movimentacao")
@NamedQueries({
		@NamedQuery(name = "JmeMovimentacao.listar", query = "select movimentacao from JmeMovimentacao movimentacao"),
		@NamedQuery(name = "JmeMovimentacao.buscarPorProduto", query = "select movimentacao from JmeMovimentacao movimentacao where mov_pro_codigo=:idProduto") })
public class JmeMovimentacao {

	@Id
	// o campo codigo e a chave primaria
	@GeneratedValue(strategy = GenerationType.AUTO)
	// e auto incremento
	@Column(name = "mov_codigo")
	private Integer codigo;

	@Column(name = "mov_numeroNF")
	@NotEmpty(message = "O campo NF È obrigatÛrio")
	private String numeroNF;

	@ManyToOne(fetch = FetchType.EAGER)
	// muitas vendas pertence a muitos funcion√°rios
	@JoinColumn(name = "mov_pro_codigo", referencedColumnName = "pro_id", nullable = false)
	private JmeProduto produto = new JmeProduto();

	@ManyToOne(fetch = FetchType.EAGER)
	// muitas vendas pertence a muitos funcion√°rios
	@JoinColumn(name = "mov_for_codigo", referencedColumnName = "for_id", nullable = false)
	private JmeFornecedor fornecedor = new JmeFornecedor();

	@Temporal(value = TemporalType.DATE)
	// que dizer que e um campo do tipo data e horario
	@Column(name = "mov_data")
	private Date dataEntrada;

	@Column(name = "mov_observacao")
	private String observacao;

	@Column(name = "mov_quantidade")
	private Integer quantidade;

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNumeroNF() {
		return numeroNF;
	}

	public void setNumeroNF(String numeroNF) {
		this.numeroNF = numeroNF;
	}

	public JmeProduto getProduto() {
		return produto;
	}

	public void setProduto(JmeProduto produto) {
		this.produto = produto;
	}

	public JmeFornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(JmeFornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "JmeMovimentacao [codigo=" + codigo + ", numeroNF=" + numeroNF
				+ ", produto=" + produto + ", fornecedor=" + fornecedor
				+ ", dataEntrada=" + dataEntrada + ", observacao=" + observacao
				+ "]";
	}

}
