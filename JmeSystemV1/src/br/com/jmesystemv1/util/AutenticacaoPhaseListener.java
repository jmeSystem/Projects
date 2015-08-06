package br.com.jmesystemv1.util;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.jmesystemv1.bean.JmeAutenticacaoBean;
import br.com.jmesystemv1.domain.JmeFuncionario;

//Classe responsavel por não deixar o usário digitar a url sem está logado
//essa página tem que registrar no faces-config.xml
public class AutenticacaoPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		//descobrir a página que estou
		FacesContext facesContext = event.getFacesContext();
		
		//atras do view root pego a página corrente
		UIViewRoot uiViewRoot= facesContext.getViewRoot();
		
		String paginaAtual = uiViewRoot.getViewId();
		
		boolean ehPaginaAutenticacao= paginaAtual.contains("jmeAutenticacao.xhtml");
		
		if(!ehPaginaAutenticacao){
		ExternalContext externalContext = facesContext.getExternalContext();
		 Map<String, Object> mapa=	externalContext.getSessionMap();
		JmeAutenticacaoBean autenticacaoBean = (JmeAutenticacaoBean)mapa.get("MBAutentificacao");
		System.out.println("autenticacaoBena"+autenticacaoBean);
		
		JmeFuncionario funcionario = autenticacaoBean.getFuncionarioLogado();
		System.out.println("fun"+funcionario);
		
		if(funcionario.getFuncao()==null){
			JSFUtil.adicionarMensagemErro("Funcionário não autenticado.");
		Application application =	facesContext.getApplication();
		NavigationHandler navigationHandler= application.getNavigationHandler();
		
		navigationHandler.handleNavigation(facesContext, null, "/pages/jmeAutenticacao.xhtml?faces-redirect=true");
		}
		
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
		
	}

	@Override
	public PhaseId getPhaseId() {
		// Defini em que fase o Phaselistener vai agir
		// no momento que vou montar a minha arvore de componente, ou seja a estrutura da minha pagina
		return PhaseId.RESTORE_VIEW;
	}

}
