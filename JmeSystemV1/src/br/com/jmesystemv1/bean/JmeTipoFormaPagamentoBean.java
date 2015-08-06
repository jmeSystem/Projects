package br.com.jmesystemv1.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeFormaPagamentoDAO;
import br.com.jmesystemv1.dao.JmeTipoFormaPagamentoDAO;

import br.com.jmesystemv1.domain.JmeFormaPagamento;
import br.com.jmesystemv1.domain.JmeTipoFormaPagamento;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBTipoFormaPagamento")
@ViewScoped
public class JmeTipoFormaPagamentoBean {

	private List<JmeTipoFormaPagamento> listagemTipoFormaPagamento;
	private JmeTipoFormaPagamento tipoFormaPagamento;
	private List<JmeTipoFormaPagamento> itensFiltrados;
	private List<JmeFormaPagamento> comboFormaPagamento;
	
	public List<JmeFormaPagamento> getComboFormaPagamento() {
		return comboFormaPagamento;
	}
	public void setComboFormaPagamento(List<JmeFormaPagamento> comboFormaPagamento) {
		this.comboFormaPagamento = comboFormaPagamento;
	}
	public List<JmeTipoFormaPagamento> getListagemTipoFormaPagamento() {
		return listagemTipoFormaPagamento;
	}
	public void setListagemTipoFormaPagamento(
			List<JmeTipoFormaPagamento> listagemTipoFormaPagamento) {
		this.listagemTipoFormaPagamento = listagemTipoFormaPagamento;
	}
	public JmeTipoFormaPagamento getTipoFormaPagamento() {
		return tipoFormaPagamento;
	}
	public void setTipoFormaPagamento(JmeTipoFormaPagamento tipoFormaPagamento) {
		this.tipoFormaPagamento = tipoFormaPagamento;
	}
	public List<JmeTipoFormaPagamento> getItensFiltrados() {
		return itensFiltrados;
	}
	public void setItensFiltrados(List<JmeTipoFormaPagamento> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	public void prepararNovo() {
		tipoFormaPagamento = new JmeTipoFormaPagamento();
		
		JmeFormaPagamentoDAO daoFormaPagamento = new JmeFormaPagamentoDAO();
		comboFormaPagamento = daoFormaPagamento.listar();
		
		for(int i=0;i<comboFormaPagamento.size(); i++){
			System.out.println(comboFormaPagamento.get(i).getDescricao());
		}
		
	}
	
	public void prepararEditar() {
		tipoFormaPagamento = new JmeTipoFormaPagamento();
		
		JmeFormaPagamentoDAO daoFormaPagamento = new JmeFormaPagamentoDAO();
		comboFormaPagamento = daoFormaPagamento.listar();
		
		for(int i=0;i<comboFormaPagamento.size(); i++){
			System.out.println(comboFormaPagamento.get(i).getDescricao());
		}
		
	}
	
	public void carregarListagem(){
		
		try{
			JmeTipoFormaPagamentoDAO daoTipoFormaPagamento = new JmeTipoFormaPagamentoDAO();
			
			listagemTipoFormaPagamento = daoTipoFormaPagamento.listar();
		}catch(Exception e){
			JSFUtil.adicionarMensagemErro("Erro ao carregar listagem");
		}
	
		
	}
	public void salvar(){
		try{
			JmeTipoFormaPagamentoDAO daoTipoFormaPagamento = new JmeTipoFormaPagamentoDAO();
			daoTipoFormaPagamento.salvar(tipoFormaPagamento);
			JSFUtil.adicionarMensagemSucesso("Salvo com sucesso");
			
		}catch(Exception e){
			JSFUtil.adicionarMensagemErro("Erro ao salvar");
		}
	}
	
	public void excluir(){
		try{
			JmeTipoFormaPagamentoDAO daoTipoFormaPagamento = new JmeTipoFormaPagamentoDAO();
			daoTipoFormaPagamento.excluir(tipoFormaPagamento);
			JSFUtil.adicionarMensagemSucesso("Excluido com sucesso");
		}catch(Exception e){
			JSFUtil.adicionarMensagemErro("Erro ao excluir");
		}
	}
	
	public void editar(){
		try{
			JmeTipoFormaPagamentoDAO daoTipoFormaPagamento = new JmeTipoFormaPagamentoDAO();
			daoTipoFormaPagamento.editar(tipoFormaPagamento);
			JSFUtil.adicionarMensagemSucesso("Alterado com sucesso");
		}catch(Exception e){
			JSFUtil.adicionarMensagemErro("Erro ao editar");
		}
	}
}
