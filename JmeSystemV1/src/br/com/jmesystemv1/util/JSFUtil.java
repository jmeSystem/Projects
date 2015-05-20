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

		// Esse é tipo de mensagem
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				mensagem, mensagem);
		// capturar o contexto da minha aplicação
		FacesContext contexto = FacesContext.getCurrentInstance();
	
		//Funciona para manter a mensagem viva após a troca da tela
	 ExternalContext externalContext = contexto.getExternalContext();
	 Flash flash=	externalContext.getFlash();
	 flash.setKeepMessages(true);
	
	 contexto.addMessage(null, msg);
	}

	public static void adicionarMensagemErro(String mensagem) {
		// Esse é tipo de mensagem
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				mensagem, mensagem);
		// capturar o contexto da minha aplicação
		FacesContext contexto = FacesContext.getCurrentInstance();

		//Funciona para manter a mensagem viva após a troca da tela
		ExternalContext externalContext = contexto.getExternalContext();
		Flash flash=	externalContext.getFlash();
		flash.setKeepMessages(true);
		
		 
		contexto.addMessage(null, msg);

	}
	
	
	/**
	 * Método requerido para geração de relatórios
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
	 * Método  para gerar de relatórios
	 * 
	 * @return
	 */
	public static void responseComplete() {
		FacesContext.getCurrentInstance().renderResponse();
		FacesContext.getCurrentInstance().responseComplete();


	}
	
//	matedo estatico vai servir, vai extrair as coisas da url
	
	public static String getParam(String nome){
		// ele captura um contexto da aplicação como um todo
		FacesContext facesContext = FacesContext.getCurrentInstance();
		// contexto do navegador
		ExternalContext externalContext = facesContext.getExternalContext();
		
		
		
		Map<String, String> paramentros = externalContext.getRequestParameterMap();
	
		String valor = paramentros.get(nome);
		
		return valor;
	}
}
