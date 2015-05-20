package br.com.jmesystemv1.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletResponse;

// essa classe vai ficar todas as utilidades do meu sistema ex: mensagem
public class JSFUtil {

	public static void adicionarMensagemSucesso(String mensagem) {

		// Esse � tipo de mensagem
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				mensagem, mensagem);
		// capturar o contexto da minha aplica��o
		FacesContext contexto = FacesContext.getCurrentInstance();
	
		//Funciona para manter a mensagem viva ap�s a troca da tela
	 ExternalContext externalContext = contexto.getExternalContext();
	 Flash flash=	externalContext.getFlash();
	 flash.setKeepMessages(true);
	
	 contexto.addMessage(null, msg);
	}

	public static void adicionarMensagemErro(String mensagem) {
		// Esse � tipo de mensagem
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				mensagem, mensagem);
		// capturar o contexto da minha aplica��o
		FacesContext contexto = FacesContext.getCurrentInstance();

		//Funciona para manter a mensagem viva ap�s a troca da tela
		ExternalContext externalContext = contexto.getExternalContext();
		Flash flash=	externalContext.getFlash();
		flash.setKeepMessages(true);
		
		 
		contexto.addMessage(null, msg);

	}
	
	
	/**
	 * M�todo requerido para gera��o de relat�rios
	 * 
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse resp = (HttpServletResponse) externalContext
				.getResponse();
		return resp;
	}

	/**
	 * M�todo  para gerar de relat�rios
	 * 
	 * @return
	 */
	public static void responseComplete() {
		FacesContext.getCurrentInstance().renderResponse();
		FacesContext.getCurrentInstance().responseComplete();


	}
	
//	matedo estatico vai servir, vai extrair as coisas da url
	
	public static String getParam(String nome){
		// ele captura um contexto da aplica��o como um todo
		FacesContext facesContext = FacesContext.getCurrentInstance();
		// contexto do navegador
		ExternalContext externalContext = facesContext.getExternalContext();
		
		
		
		Map<String, String> paramentros = externalContext.getRequestParameterMap();
	
		String valor = paramentros.get(nome);
		
		return valor;
	}
}
