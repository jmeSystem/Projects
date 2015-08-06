package br.com.jmesystemv1.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeItensVendaDAO;
import br.com.jmesystemv1.dao.JmeMovimentacaoDAO;
import br.com.jmesystemv1.domain.JmeItensVenda;
import br.com.jmesystemv1.filter.JmeMovSaida;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBMovimentacaoSaida")
@ViewScoped
public class JmeMovimentacaoSaidaBean {

	private List<JmeItensVenda> listagemItens;
	private JmeItensVenda itensVenda;
	private List<JmeItensVenda> itensFiltrados;
	private JmeMovSaida filter;
	
	
	
	public JmeMovSaida getFilter() {
		if(filter==null){
			filter = new JmeMovSaida();
		}
		return filter;
	}
	public void setFilter(JmeMovSaida filter) {
		this.filter = filter;
	}
	public List<JmeItensVenda> getListagemItens() {
		return listagemItens;
	}
	public void setListagemItens(List<JmeItensVenda> listagemItens) {
		this.listagemItens = listagemItens;
	}
	public JmeItensVenda getItensVenda() {
		return itensVenda;
	}
	public void setItensVenda(JmeItensVenda itensVenda) {
		this.itensVenda = itensVenda;
	}
	public List<JmeItensVenda> getItensFiltrados() {
		return itensFiltrados;
	}
	public void setItensFiltrados(List<JmeItensVenda> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	
	public void carregarListagem(){
		
		try{
			JmeItensVendaDAO daoItens = new JmeItensVendaDAO();
			listagemItens = daoItens.listar();
			
			
		}catch(Exception e){
			JSFUtil.adicionarMensagemErro("Erro ao listar");
		}
	}
	
	public void buscar() {
		try {
			JmeItensVendaDAO dao = new JmeItensVendaDAO();
			
			listagemItens= dao.buscar(filter);
		} catch (RuntimeException ex) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar buscar uma venda."
					+ ex.getMessage());
		}
	}

}
