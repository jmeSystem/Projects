
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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

	@Entity
	@Table(name = "Cidade")
	@NamedQuery(name = "JmeCidade.listar", query = "select cidade from JmeCidade cidade")
	public class JmeCidade implements Serializable{
		/**
		 * 
		 */
		
		private static final long serialVersionUID = 1L;
		@Id
		// o campo codigo é a chave primária
		@GeneratedValue(strategy = GenerationType.AUTO)
		// é auto incremento
		@Column(name = "CID_ID")
		private Integer idCidade;

		@Column(name = "CID_NOME")
		private String nome;

		@ManyToOne(fetch = FetchType.EAGER) //muitas vendas pertence a muitos funcionários
		@JoinColumn(name="cid_est_uf", referencedColumnName="est_uf", nullable=false)
		private JmeEstado estado;
		
		public JmeEstado getEstado() {
			return estado;
		}

		public void setEstado(JmeEstado estado) {
			this.estado = estado;
		}

		public Integer getIdCidade() {
			return idCidade;
		}

		public void setIdCidade(Integer idCidade) {
			this.idCidade = idCidade;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		@Override
		public String toString() {
			return "JmeCidade [idCidade=" + idCidade + ", nome=" + nome
					+ ", estado=" + estado + "]";
		}

		

	}


