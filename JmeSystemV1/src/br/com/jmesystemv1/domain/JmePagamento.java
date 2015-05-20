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
@Table(name="Pagamento")
@NamedQueries({ 
@NamedQuery(name="JmePagamento.listar", 
query="select pagamento from JmePagamento pagamento"),
@NamedQuery(name="JmePagamento.buscarPorCodigo", 
query="select pagamento from JmePagamento pagamento where PAG_ID =:idPagamento"),
@NamedQuery(name="JmePagamento.listarTodosPagamento", 
query="select pagamento from JmePagamento pagamento where PAG_RVE_ID=:idRegistroVenda"),
@NamedQuery(name="JmePagamento.excluirCodigoVenda", 
query="DELETE from JmePagamento pagamento where PAG_RVE_ID=:idRegistroVenda")
})
public class JmePagamento implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PAG_ID")
	private Integer idPagamento;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PAG_RVE_ID", referencedColumnName="RVE_ID", nullable=false)
	private JmeRegistroVendas registroVendas;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PAG_TFP_ID", referencedColumnName="TFP_ID", nullable=false)
	private JmeTipoFormaPagamento tipoFormaPagamento = new JmeTipoFormaPagamento();
		
	@Temporal(value = TemporalType.DATE)
	@Column(name="PAG_DATA_PAGAMENTO")
	private Date dataPagamento;
	
	@Column(name="PAG_VALOR")
	private Double valorPagamento;
	
	@Column(name="PAG_NUMERO_PARCELA")
	private Integer numeroParcelas;
	
	
	
	
	
	
	

	public JmeTipoFormaPagamento getTipoFormaPagamento() {
		return tipoFormaPagamento;
	}

	public void setTipoFormaPagamento(JmeTipoFormaPagamento tipoFormaPagamento) {
		this.tipoFormaPagamento = tipoFormaPagamento;
	}

	public Integer getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}


	public JmeRegistroVendas getRegistroVendas() {
		return registroVendas;
	}

	public void setRegistroVendas(JmeRegistroVendas registroVendas) {
		this.registroVendas = registroVendas;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Double getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(Double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	@Override
	public String toString() {
		return "JmePagamento [idPagamento=" + idPagamento 
				 + ", registroVendas=" + registroVendas
				+ ", dataPagamento=" + dataPagamento + ", valorPagamento="
				+ valorPagamento + ", numeroParcelas=" + numeroParcelas + "]";
	}
	
	
	
	
	
}
