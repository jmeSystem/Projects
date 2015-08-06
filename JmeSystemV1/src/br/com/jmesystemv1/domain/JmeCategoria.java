package br.com.jmesystemv1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Categoria")
@NamedQueries({ 
@NamedQuery(name="JmeCategoria.listar", 
			query="select categoria from JmeCategoria categoria")})
public class JmeCategoria {

	@Id
	// o campo codigo e a chave primaria
	@GeneratedValue(strategy = GenerationType.AUTO)
	// e auto incremento
	@Column(name = "cat_codigo")
	private Integer codigo;
	
	@Column(name="cat_descricao")
	@NotEmpty(message="O campo nome é obrigatório")
	@Size(min=3, max=50, message="Tamanho inválido para o campo nome (3 - 50) caracteres")
	private String descricao;
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	@Override
	public String toString() {
		return "JmeCategoria [codigo=" + codigo + ", descricao=" + descricao
				+ "]";
	}
	
	
	
}
