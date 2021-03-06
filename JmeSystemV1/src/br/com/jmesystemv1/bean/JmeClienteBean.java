package br.com.jmesystemv1.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeCidadeDAO;
import br.com.jmesystemv1.dao.JmeClienteDAO;
import br.com.jmesystemv1.dao.JmeRegistroVendasDAO;
import br.com.jmesystemv1.domain.JmeCidade;
import br.com.jmesystemv1.domain.JmeCliente;
import br.com.jmesystemv1.domain.JmeRegistroVendas;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBCliente")
@ViewScoped
public class JmeClienteBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// guarda a consulta geral s�o varios clientes
	private List<JmeCliente> itens;
	// guarda a consulta filtrada, do componente filterby
	private List<JmeCliente> itensFiltrados;

	// vou guarda os dados da inclus�o
	private JmeCliente cliente;

	// vou usar para listar as cidades no combobox
	private List<JmeCidade> comboCidade;

	
	public List<JmeCliente> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(List<JmeCliente> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public List<JmeCliente> getItens() {
		return itens;
	}

	public void setItens(ArrayList<JmeCliente> itens) {
		this.itens = itens;
	}

	public JmeCliente getCliente() {
		return cliente;
	}

	public void setCliente(JmeCliente cliente) {
		this.cliente = cliente;
	}

	public List<JmeCidade> getComboCidade() {
		return comboCidade;
	}

	public void setComboCidade(ArrayList<JmeCidade> comboCidade) {
		this.comboCidade = comboCidade;
	}

	// carrega os dados na table
	public void carregarListagem() {
		try {
			JmeClienteDAO dao = new JmeClienteDAO();

			itens = dao.listar();
		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void prepararNovo(){
		try{
		cliente = new JmeCliente();
		
		JmeCidadeDAO dao = new JmeCidadeDAO();
		
		comboCidade =  dao.listar();
		System.out.print("passou no preparar novo");
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar(){
		try{
		cliente = new JmeCliente();
		
		JmeCidadeDAO dao = new JmeCidadeDAO();
		
		comboCidade =  dao.listar();
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void salvar(){
		JmeClienteDAO dao = new JmeClienteDAO();
		
	try{
		dao.salvar(cliente);
		
		// atualizar o banco
		itens =  dao.listar();
		//Serve para limpar o objeto ap�s a grava��o(ou seja limpa todos os campos do dialog)
		cliente = new JmeCliente();
		JSFUtil.adicionarMensagemSucesso("Salvo com sucesso.");
		
	}catch(Exception ex){
		ex.printStackTrace();
		JSFUtil.adicionarMensagemErro("Erro ao inserir"+ex.getMessage());
	}
		}
	
	public void excluir(){
		try{
		JmeRegistroVendasDAO daoRegistroVendas = new JmeRegistroVendasDAO();
		JmeRegistroVendas registroVendas = new JmeRegistroVendas();
		registroVendas = daoRegistroVendas.buscarPorClientes(cliente.getIdCliente());
		if(registroVendas!=null){
			JSFUtil.adicionarMensagemErro("Imposs�vel excluir o Cliente, o mesmo est� sendo utilizado na Venda. Para excluir o cliente, exclui a Venda referente ao cliente");
			return;
		}
			
		JmeClienteDAO dao = new JmeClienteDAO();
		
		dao.excluir(cliente);
		
		// atualizar o banco
		itens =  dao.listar();
		JSFUtil.adicionarMensagemSucesso("Excluido com sucesso.");
	}catch(Exception ex){
		ex.printStackTrace();
		
		JSFUtil.adicionarMensagemErro(ex.getMessage());
	}
	}
	public void editar(){
		
		try{
			JmeClienteDAO dao = new JmeClienteDAO();
			dao.editar(cliente);
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Alterado com sucesso.");
			
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	
}
