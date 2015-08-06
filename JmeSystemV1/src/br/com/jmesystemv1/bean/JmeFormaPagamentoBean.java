package br.com.jmesystemv1.bean;



import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeFormaPagamentoDAO;
import br.com.jmesystemv1.domain.JmeFormaPagamento;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBFormaPagamento")
@ViewScoped
public class JmeFormaPagamentoBean {

	private List<JmeFormaPagamento> listagemFormaPagamento;
	private JmeFormaPagamento formaPagamento;
	private List<JmeFormaPagamento> itensFiltrados;
	
	
	
	public List<JmeFormaPagamento> getListagemFormaPagamento() {
		return listagemFormaPagamento;
	}



	public void setListagemFormaPagamento(
			List<JmeFormaPagamento> listagemFormaPagamento) {
		this.listagemFormaPagamento = listagemFormaPagamento;
	}



	public JmeFormaPagamento getFormaPagamento() {
		return formaPagamento;
	}



	public void setFormaPagamento(JmeFormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}



	public List<JmeFormaPagamento> getItensFiltrados() {
		return itensFiltrados;
	}



	public void setItensFiltrados(List<JmeFormaPagamento> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	public void carregarListagem(){
		
		try{
			JmeFormaPagamentoDAO daoFormaPagamento = new JmeFormaPagamentoDAO();
			
			listagemFormaPagamento = daoFormaPagamento.listar();
		}catch(Exception e){
			JSFUtil.adicionarMensagemErro("Erro ao carregar listagem");
		}
		
	}
	
	public void editar(){
		try{
			JmeFormaPagamentoDAO daoFormaPagamento = new JmeFormaPagamentoDAO();
			
			if(formaPagamento.getQuantidadeParcela()<=0 && formaPagamento.getIdFormaPagamento()==2){
				JSFUtil.adicionarMensagemErro("Quantidade tem ser maior que 0");
				return;
			}
			if(formaPagamento.getDesconto()<0 && formaPagamento.getIdFormaPagamento()==1){
				JSFUtil.adicionarMensagemErro("Desconto tem ser maior e/ou a 0");
				return;
			}
			daoFormaPagamento.editar(formaPagamento);
			JSFUtil.adicionarMensagemSucesso("Alterado com sucesso");
		} catch(Exception e){
			JSFUtil.adicionarMensagemErro("Erro ao alterar");
		}
	}
	
}
