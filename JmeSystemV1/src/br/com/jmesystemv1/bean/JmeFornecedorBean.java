package br.com.jmesystemv1.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeCidadeDAO;
import br.com.jmesystemv1.dao.JmeFornecedorDAO;
import br.com.jmesystemv1.domain.JmeCidade;
import br.com.jmesystemv1.domain.JmeFornecedor;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name="MBFornecedor")
@ViewScoped
public class JmeFornecedorBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JmeFornecedor fornecedorBean;
	private List<JmeFornecedor> itens;
	private List<JmeFornecedor> itensFiltrados;
	private List<JmeCidade> cidadeCombo;
	
	public JmeFornecedor getFornecedorBean() {
		return fornecedorBean;
	}
	public void setFornecedorBean(JmeFornecedor fornecedorBean) {
		this.fornecedorBean = fornecedorBean;
	}
	public List<JmeFornecedor> getItens() {
		return itens;
	}
	public void setItens(List<JmeFornecedor> itens) {
		this.itens = itens;
	}
	public List<JmeFornecedor> getItensFiltrados() {
		return itensFiltrados;
	}
	public void setItensFiltrados(List<JmeFornecedor> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	public List<JmeCidade> getCidadeCombo() {
		return cidadeCombo;
	}
	public void setCidadeCombo(List<JmeCidade> cidadeCombo) {
		this.cidadeCombo = cidadeCombo;
	}
	
	@PostConstruct
	public void prepararPesquisa(){
		JmeFornecedorDAO fornecedorDAO = new JmeFornecedorDAO();
		itens = fornecedorDAO.listar();
		
	}
	
	/**Listagem Geral*/
	public void listagem(){
		
		try{
		JmeFornecedorDAO fornecedorDAO = new JmeFornecedorDAO();
		
		itens = fornecedorDAO.listar();
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	/**Prepara o Objeto para receber um novo Fornecedor*/
	public void prepararNovo(){
		
		try{
		fornecedorBean = new JmeFornecedor();
		
		JmeCidadeDAO cidadeDAO = new JmeCidadeDAO();
		cidadeCombo = cidadeDAO.listar();
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
		
	
	public void prepararExcluir(){
		
		try{
			fornecedorBean = new JmeFornecedor();
			
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar(){
		try{
			fornecedorBean = new JmeFornecedor();
			
			JmeCidadeDAO cidadeDAO = new JmeCidadeDAO();
			
			cidadeCombo = cidadeDAO.listar();
			
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	/**Metodo para adicionar Fornecedor*/
	public void novo(){
		try{
		JmeFornecedorDAO fornecedorDAO = new JmeFornecedorDAO();
		
		/**Salva o objeto fornecedor no Banco*/
		fornecedorDAO.salvar(fornecedorBean);
		
		/**Atualiza a tabela e o banco*/
		itens = fornecedorDAO.listar();
		
		/**Limpa os combos depois que os dados são salvos*/
		fornecedorBean = new JmeFornecedor();
		
		JSFUtil.adicionarMensagemSucesso("Salvo com sucesso.");
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
	}
	public void excluir(){
		
		try{
			JmeFornecedorDAO fornecedorDAO = new JmeFornecedorDAO();
			fornecedorDAO.excluir(fornecedorBean);
			
			itens = fornecedorDAO.listar();
			JSFUtil.adicionarMensagemSucesso("Excluido com sucesso.");
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void editar(){
		
		try{
			JmeFornecedorDAO fornecedorDAO = new JmeFornecedorDAO();
			fornecedorDAO.editar(fornecedorBean);
			
			itens = fornecedorDAO.listar();
			JSFUtil.adicionarMensagemSucesso("Alterado com sucesso.");
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
