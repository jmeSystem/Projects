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
query="DELETE from JmeParcela parcela where par_ven_codigo=:idRegistroVenda")})
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
