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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "Funcionario")
@NamedQueries({ 
@NamedQuery(name="JmeFuncionario.listar", 
			query="select funcionario from JmeFuncionario funcionario"),
@NamedQuery(name="JmeFuncionario.buscarPorCodigo", 
			query="select funcionario from JmeFuncionario funcionario where fun_id =:idFuncionario"),
@NamedQuery(name="JmeFuncionario.autenticar", 
			query="SELECT funcionario from JmeFuncionario funcionario WHERE funcionario.cpf=:cpf AND funcionario.senha=:senha")			
})
public class JmeFuncionario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FUN_ID")
	private Integer idFuncionario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FUN_CID_ID", referencedColumnName = "CID_ID", nullable=false)
	private JmeCidade cidade = new JmeCidade();

	@Column(name = "FUN_NOME")
	@NotEmpty(message="O campo nome é obrigatório")
	@Size(min=3, max=50, message="Tamanho inválido para o campo nome(3 - 50) caracteres")
	private String nome;

	@Size(min=6, max=50, message="Tamanho inválido para o campo endereço(3 - 50) caracteres")
	@NotEmpty(message="O campo endereço é obrigatório")
	@Column(name = "FUN_ENDERECO")
	private String endereco;

	@NotEmpty(message="O campo telefone é obrigatório")
	@Column(name = "FUN_TELEFONE")
	private String telefone;

	@CPF(message="O CPF informado é inválido")
	@Column(name = "FUN_CPF")
	private String cpf;
	
	@NotEmpty(message="O campo função é obrigatório")
	@Column(name = "FUN_FUNCAO", unique=true)
	private String funcao;
	
	@NotEmpty(message="O campo senha é obrigatório")
	@Column(name="FUN_SENHA")
	@Size(min=6, max=32, message="Tamanho inválido para o campo senha (6 - 8) caracteres")
	private String senha;
	
	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public JmeCidade getCidade() {
		return cidade;
	}

	public void setCidade(JmeCidade cidade) {
		this.cidade = cidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	@Override
	public String toString() {
		return "JmeFuncionario [idFuncionario=" + idFuncionario + ", cidade="
				+ cidade + ", nome=" + nome + ", endereco=" + endereco
				+ ", telefone=" + telefone + ", cpf=" + cpf + ", funcao="
				+ funcao + ", senha=" + senha + "]";
	}


	

	
	

}
