package br.com.jmesystemv1.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.jmesystemv1.dao.JmeLoginDAO;
import br.com.jmesystemv1.domain.JmeLogin;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBlogin")
@SessionScoped
public class JmeLoginBean {

	JmeLogin jmeLogin = new JmeLogin();
	JmeLogin loginCorrente = new JmeLogin();
	boolean validacao = false;
	boolean validaCampos = true;
	boolean validaImagem = false;
	
	/*
	 * =============================================GET-SET======================
	 */
	
	public JmeLogin getJmeLogin() {
		return jmeLogin;
	}

	public boolean isValidaCampos() {
		return validaCampos;
	}

	public void setValidaCampos(boolean validaCampos) {
		this.validaCampos = validaCampos;
	}

	public boolean isValidaImagem() {
		return validaImagem;
	}

	public void setValidaImagem(boolean validaImagem) {
		this.validaImagem = validaImagem;
	}

	public void setJmeLogin(JmeLogin jmeLogin) {
		this.jmeLogin = jmeLogin;
	}

	public JmeLogin getLoginCorrente() {
		return loginCorrente;
	}

	public void setLoginCorrente(JmeLogin loginCorrente) {
		this.loginCorrente = loginCorrente;
	}

	public boolean isValidacao() {
		return validacao;
	}

	public void setValidacão(boolean validacao) {
		this.validacao = validacao;
	}
	
	public void setValidacao(boolean validacao) {
		this.validacao = validacao;
	}

	/**
	 * método responsavel por validar o login e senha
	 */
	public void validaLogin() {

		JmeLoginDAO loginDAO = new JmeLoginDAO();

		loginCorrente = loginDAO.validaLogin(jmeLogin.getSenha(),jmeLogin.getLogin());

		if (loginCorrente != null) {
			validacao = true;
			validaCampos = false;
			validaImagem = true;
		}

		else {
			validacao = false;
			JSFUtil.adicionarMensagemErro("Usuário ou Senha Inválido!");
		}

	}
	/**
	 * método responsavel por alterar a variaves de validação
	 */
	public void alteraValidacao(){
		validacao = false;
		validaCampos = true;
		validaImagem = false;
		
	}
	
}
