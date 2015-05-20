package br.com.jmesystemv1.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "forma_pagamento")
@NamedQueries({ 
@NamedQuery(name="JmeFormaPagamento.listar", 
			query="select forma_pagamento from JmeFormaPagamento forma_pagamento"),
@NamedQuery(name="JmeFormaPagamento.buscarPorCodigo", 
			query="select forma_pagamento from JmeFormaPagamento forma_pagamento where FPG_ID =:idFormaPagamento")})

public class JmeFormaPagamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="FPG_ID")
	private Integer idFormaPagamento;
	
	@Column(name="FPG_DESCRICAO")
	private String descricao;
	
	@Column(name="FPG_PORCENT_DESCONTO")
	private Double desconto;
	
	@Column(name="FPG_PORCENT_ACRESCIMO")
	private double acrescimo;
	
	@Column(name="FPG_QTD_PARCELA")
	private Integer quantidadeParcela;

	public Integer getIdFormaPagamento() {
		
		return idFormaPagamento;
	}

	public void setIdFormaPagamento(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public double getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(double acrescimo) {
		this.acrescimo = acrescimo;
	}

	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	
	@Override
	public String toString() {
		return "JmeFormaPagamento [idFormaPagamento=" + idFormaPagamento
				+ ", descricao=" + descricao + ", desconto=" + desconto
				+ ", acrescimo=" + acrescimo + ", quantidadeParcela="
				+ quantidadeParcela + "]";
	}
	
	
	
}
