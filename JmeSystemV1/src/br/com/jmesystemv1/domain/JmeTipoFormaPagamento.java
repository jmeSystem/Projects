package br.com.jmesystemv1.domain;

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
@Table(name="tipo_forma_pagamento")

@NamedQueries({ 
@NamedQuery(name="JmeTipoFormaPagamento.listar", 
			query="select tipo_forma_pagamento from JmeTipoFormaPagamento tipo_forma_pagamento"),
@NamedQuery(name="JmeTipoFormaPagamento.buscarPorCodigo", 
			query="select tipo_forma_pagamento from JmeTipoFormaPagamento tipo_forma_pagamento where TFP_ID =:idTipoFormaPagamento")})
public class JmeTipoFormaPagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="TFP_ID")
	private Integer idTipoFormaPagamento;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TFP_FPG_ID", referencedColumnName="FPG_ID", nullable=false)
	private JmeFormaPagamento formaPagamento;
	
	@Column(name="TFP_DESCRICAO")
	private String descricao;

	
	
	public Integer getIdTipoFormaPagamento() {
		return idTipoFormaPagamento;
	}

	public void setIdTipoFormaPagamento(Integer idTipoFormaPagamento) {
		this.idTipoFormaPagamento = idTipoFormaPagamento;
	}

	public JmeFormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(JmeFormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "JmeTipoFormaPagamento [idTipoFormaPagamento="
				+ idTipoFormaPagamento + ", formaPagamento=" + formaPagamento
				+ ", descricao=" + descricao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idTipoFormaPagamento == null) ? 0 : idTipoFormaPagamento
						.hashCode());
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
		JmeTipoFormaPagamento other = (JmeTipoFormaPagamento) obj;
		if (idTipoFormaPagamento == null) {
			if (other.idTipoFormaPagamento != null)
				return false;
		} else if (!idTipoFormaPagamento.equals(other.idTipoFormaPagamento))
			return false;
		return true;
	}
	
	
	
}
