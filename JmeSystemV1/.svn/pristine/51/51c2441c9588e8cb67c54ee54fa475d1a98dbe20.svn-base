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
@Table(name="login")
@NamedQueries({ 
	@NamedQuery(name  = "JmeLogin.validaLogin", 
				query = "select login from JmeLogin login where LOG_SENHA = :senha  and  LOG_USUARIO = :login") })
public class JmeLogin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOG_ID")
	private Integer idLogin;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LOG_FUN_ID", referencedColumnName = "FUN_ID", nullable = false)
	private JmeFuncionario funcionario ;

	@Column(name = "LOG_USUARIO")
	private String login;

	@Column(name = "LOG_SENHA")
	private String senha;

	/*
	 * ===================================GET-SET=======================================
	 */
	
	public Integer getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Integer idLogin) {
		this.idLogin = idLogin;
	}

	public JmeFuncionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(JmeFuncionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
