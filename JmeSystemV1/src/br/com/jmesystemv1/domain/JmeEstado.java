package br.com.jmesystemv1.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Estado")
public class JmeEstado implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EST_UF")
	private String siglaEstado;
	
	@Column(name="EST_NOME")
	private String nomeEstado;
	
	
	public String getSiglaEstado() {
		return siglaEstado;
	}
	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}
	public String getNomeEstado() {
		return nomeEstado;
	}
	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
	@Override
	public String toString() {
		return "JmeEstado [siglaEstado=" + siglaEstado + ", nomeEstado="
				+ nomeEstado + "]";
	}
	
	

}
