package br.com.jmesystemv1.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeFornecedorDAO;
import br.com.jmesystemv1.dao.JmeProdutoDAO;
import br.com.jmesystemv1.domain.JmeFornecedor;
import br.com.jmesystemv1.domain.JmeProduto;
import br.com.jmesystemv1.util.JSFUtil;

/**
 * 
 * @author Josï¿½ Wilian
 * 
 *         Classe responsï¿½vel por gerenciar a tela de Produtos
 */
@ManagedBean(name = "MBProduto")
@ViewScoped
public class JmeProdutoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<JmeProduto> itensFiltrados;
	private List<JmeProduto> listaProdutos;
	private JmeProduto produto;
	private List<JmeFornecedor> listaFornecedores;

	/* =======================GET-SET================= */

	public List<JmeProduto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<JmeProduto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<JmeFornecedor> getListaFornecedores() {
		return listaFornecedores;
	}

	public void setListaFornecedores(List<JmeFornecedor> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
	}

	public List<JmeProduto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(List<JmeProduto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public JmeProduto getProduto() {
		return produto;
	}

	public void setProduto(JmeProduto produto) {
		this.produto = produto;
	}
	
	/**
	 * Mï¿½todo reponsavel por carregar informaï¿½ï¿½es na abertura da pagina
	 */
	public void carregarListagem() {
			
		JmeProdutoDAO dao = new JmeProdutoDAO();
		
		listaProdutos = dao.listar();
		
	}

	/**
	 * Mï¿½todo Responsavel por preparar para inserir um novo produto
	 */
	public void preparaNovo() {
		produto = new JmeProduto();
		JmeFornecedorDAO dao = new JmeFornecedorDAO();
		listaFornecedores= dao.listar();

	}
	
	public void preparaEditar() {
		produto = new JmeProduto();
		JmeFornecedorDAO dao = new JmeFornecedorDAO();
		listaFornecedores= dao.listar();

	}
	
	public void incluir(){
		
		JmeProdutoDAO proDAO= new JmeProdutoDAO();
		
		produto.setDataCadastro(new Date());
		produto.setQuantEstoque(0);
		
		proDAO.salvar(produto);
		// atualizar o banco
		listaProdutos =  proDAO.listar();
		produto = new JmeProduto();
		
		JSFUtil.adicionarMensagemSucesso("Salvo com sucesso.");
	}
	
	public void exluir() {

		

		try {
			JmeProdutoDAO dao = new JmeProdutoDAO();	
			dao.excluir(produto);
			JSFUtil.adicionarMensagemSucesso("Excluido com sucesso.");

		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao excluir o produto.");
		}
	}

	/***
	 * Mï¿½todo resposavel por editar um produto
	 */
	public void editar() {

		JmeProdutoDAO dao = new JmeProdutoDAO();

		try {

			dao.editar(produto);

			JSFUtil.adicionarMensagemSucesso("Alterado com sucesso.");

		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao alterar o produto.");
		}
	}

	

	

}
