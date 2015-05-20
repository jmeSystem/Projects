package br.com.jmesystemv1.bean;

import java.io.Serializable;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.jmesystemv1.dao.JmeCidadeDAO;
import br.com.jmesystemv1.dao.JmeClienteDAO;
import br.com.jmesystemv1.dao.JmeFuncionarioDAO;
import br.com.jmesystemv1.domain.JmeCidade;
import br.com.jmesystemv1.domain.JmeFuncionario;
import br.com.jmesystemv1.util.JSFUtil;



@ManagedBean (name = "MBFuncionario")
@ViewScoped
public class JmeFuncionarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// guarda a consulta geral são varios Funcionarios
	private List<JmeFuncionario> itens;
	
	// guardar os dados da inclusão
	private JmeFuncionario funcionario;
	
	// guarda a consulta filtrada, do componente filterby
	private List<JmeFuncionario> itensFiltrados;
	
	// combo da cidade
	private List<JmeCidade> comboCidade;

	
	public JmeFuncionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(JmeFuncionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public List<JmeFuncionario> getItens() {
		return itens;
	}

	public void setItens(List<JmeFuncionario> itens) {
		this.itens = itens;
	}

	public List<JmeFuncionario> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(List<JmeFuncionario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public List<JmeCidade> getComboCidade() {
		return comboCidade;
	}

	public void setComboCidade(List<JmeCidade> comboCidade) {
		this.comboCidade = comboCidade;
	}

	public void carregarListagem(){
		try{
			JmeFuncionarioDAO dao = new JmeFuncionarioDAO();
			
			itens = dao.listar();
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
		
	}
	public void prepararNovo(){
		try{
			funcionario = new JmeFuncionario();
			
			JmeCidadeDAO dao = new JmeCidadeDAO();
			
			comboCidade = dao.listar();
			System.out.print("passou no preparar Novo Funcionario");
		}catch(Exception ex){
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar(){
		try{
			funcionario = new JmeFuncionario();
			
			JmeCidadeDAO dao = new JmeCidadeDAO();
			
			comboCidade = dao.listar();
		}catch(Exception ex){
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void salvar(){
		JmeFuncionarioDAO dao = new JmeFuncionarioDAO();
		funcionario.setSenha(DigestUtils.md5Hex(funcionario.getSenha()));
		dao.salvar(funcionario);
		
		itens = dao.listar();
		
		funcionario = new JmeFuncionario();
		
		JSFUtil.adicionarMensagemSucesso("Cadastro Salvo com Sucesso !");
	}
	
	public void excluir(){
		try{
		JmeFuncionarioDAO dao = new JmeFuncionarioDAO();
		dao.excluir(funcionario);
		
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
			JmeFuncionarioDAO dao = new JmeFuncionarioDAO();
			funcionario.setSenha(DigestUtils.md5Hex(funcionario.getSenha()));
			dao.editar(funcionario);
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Alterado com sucesso.");
			
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
