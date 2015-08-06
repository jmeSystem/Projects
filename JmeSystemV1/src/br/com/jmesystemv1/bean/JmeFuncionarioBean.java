package br.com.jmesystemv1.bean;

import java.io.Serializable;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.jmesystemv1.dao.JmeCidadeDAO;
import br.com.jmesystemv1.dao.JmeClienteDAO;
import br.com.jmesystemv1.dao.JmeFuncionarioDAO;
import br.com.jmesystemv1.dao.JmeRegistroVendasDAO;
import br.com.jmesystemv1.domain.JmeCidade;
import br.com.jmesystemv1.domain.JmeFuncionario;
import br.com.jmesystemv1.domain.JmeRegistroVendas;
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

	//checkBox responsabel por selecionar as permissões referente ao funcionário, ou seja telas que ele pode ver
	boolean selecionarTudo=true;	
	boolean selecionarNada=false;
	
	
	
	public boolean isSelecionarTudo() {
		return selecionarTudo;
	}

	public void setSelecionarTudo(boolean selecionarTudo) {
		this.selecionarTudo = selecionarTudo;
	}

	public boolean isSelecionarNada() {
		return selecionarNada;
	}

	public void setSelecionarNada(boolean selecionarNada) {
		this.selecionarNada = selecionarNada;
	}

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
			System.out.println("Tela Categoria"+funcionario.getFuncao());	
			
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
	
	//Resposavel por selecionar todas as permissões de uma vez só
	public void selecionarTudo(){
		selecionarTudo=true;
		
		funcionario.setTelaCategoria(true);
		funcionario.setTelaConfiguracaoPagamento(true);
		funcionario.setTelaFornecedor(true);
		funcionario.setTelaFuncionario(true);
		funcionario.setTelaMovEntrada(true);
		funcionario.setTelaProduto(true);
		funcionario.setTelaTipoFormaPagamento(true);
		
		System.out.println("Passou no selecionar Tudo");
	}
	
	//Resposavel por desmarcar todas as permissões de uma vez só
	public void selecionarNada2(){
		selecionarNada=false;
		
		funcionario.setTelaCategoria(false);
		funcionario.setTelaConfiguracaoPagamento(false);
		funcionario.setTelaFornecedor(false);
		funcionario.setTelaFuncionario(false);
		funcionario.setTelaMovEntrada(false);
		funcionario.setTelaProduto(false);
		funcionario.setTelaTipoFormaPagamento(false);
	}
	
	public void excluir(){
		try{
			JmeRegistroVendasDAO daoRegistroVendas = new JmeRegistroVendasDAO();
			JmeRegistroVendas vendas = new JmeRegistroVendas();
			vendas = daoRegistroVendas.buscarPorFuncionario(funcionario.getIdFuncionario());
			if(vendas !=null){
				JSFUtil.adicionarMensagemErro("Impossível excluir o Funcionário, o mesmo está sendo utilizado na venda. Para excluir o Funcionário, exclui a venda que o funcionário efetuou.");
			}
			
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
			JSFUtil.adicionarMensagemAdvertencia("Para que as permissões tenha efeitos, sai do sistema e loga denovo ");
			
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
