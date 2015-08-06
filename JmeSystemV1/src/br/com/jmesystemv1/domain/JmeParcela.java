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

@Entity
@Table(name="parcela")
@NamedQueries({
@NamedQuery(name="JmeParcela.excluirCodigoVenda", 
query="DELETE from JmeParcela parcela where par_ven_codigo=:idRegistroVenda"),
@NamedQuery(name="JmeParcela.atualizarStatusParcela", 
query="UPDATE from JmeParcela parcela set par_situacao=:situcao where par_numeroParcela=:numeroParcela and par_ven_codigo=:idRegistroVenda"),
@NamedQuery(name="JmeParcela.listarTodasParcela", 
query="select parcela from JmeParcela parcela where par_ven_codigo=:idRegistroVenda"),
@NamedQuery(name="JmeParcela.TotalParcelaVencidas", 
query="select parcela from JmeParcela parcela where par_dataVencimento<:dataAtual and par_situacao='Pendente'")})
public class JmeParcela {
	
	@Id
	// o campo codigo e a chave primaria
	@GeneratedValue(strategy = GenerationType.AUTO)
	// e auto incremento
	@Column(name="par_codigo")
	private Integer idParcela;
	
	@Column(name="par_valor")
	private Double valor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="par_ven_codigo", referencedColumnName="RVE_ID", nullable=false)
	private JmeRegistroVendas registroVenda;

	@Column(name="par_situacao")
	private String situacao;
	
	@Temporal(value = TemporalType.DATE) // que dizer que e um campo do tipo data e horario
	@Column(name="par_dataVencimento")
	private Date dataVencimento;
	
	@Column(name="par_numeroParcela")
	private Integer numeroParcela;
	
	@Column(name="par_qtdDiasVencidos")
	private int quantidadeDiasAtrasos;
	
	@Column(name="par_valorPagar")
	private Double valorPagar;
	
	
	
	public Double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(Double valorPagar) {
		this.valorPagar = valorPagar;
	}

	public void setQuantidadeDiasAtrasos(int quantidadeDiasAtrasos) {
		this.quantidadeDiasAtrasos = quantidadeDiasAtrasos;
	}

	public Integer getQuantidadeDiasAtrasos() {
		return quantidadeDiasAtrasos;
	}

	public void setQuantidadeDiasAtrasos(Integer quantidadeDiasAtrasos) {
		this.quantidadeDiasAtrasos = quantidadeDiasAtrasos;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Integer getIdParcela() {
		return idParcela;
	}

	public void setIdParcela(Integer idParcela) {
		this.idParcela = idParcela;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public JmeRegistroVendas getRegistroVenda() {
		return registroVenda;
	}

	public void setRegistroVenda(JmeRegistroVendas registroVenda) {
		this.registroVenda = registroVenda;
	}

	@Override
	public String toString() {
		return "JmeParcela [idParcela=" + idParcela + ", valor=" + valor
				+ ", registroVenda=" + registroVenda + "]";
	}
	
	
	
}
