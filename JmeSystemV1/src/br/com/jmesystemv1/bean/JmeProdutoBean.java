package br.com.jmesystemv1.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeCategoriaDAO;
import br.com.jmesystemv1.dao.JmeFornecedorDAO;
import br.com.jmesystemv1.dao.JmeItensVendaDAO;
import br.com.jmesystemv1.dao.JmeMovimentacaoDAO;
import br.com.jmesystemv1.dao.JmeProdutoDAO;
import br.com.jmesystemv1.domain.JmeCategoria;
import br.com.jmesystemv1.domain.JmeFornecedor;
import br.com.jmesystemv1.domain.JmeItensVenda;
import br.com.jmesystemv1.domain.JmeMovimentacao;
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
public class JmeProdutoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<JmeProduto> itensFiltrados;
	private List<JmeProduto> listaProdutos;
	private JmeProduto produto;
	private List<JmeFornecedor> listaFornecedores;
	private List<JmeCategoria> listaCategoria;
	private Double valorTotal=0.0;
	/* =======================GET-SET================= */

	
	
	public List<JmeProduto> getListaProdutos() {
		if(listaProdutos==null){
			listaProdutos= new ArrayList<JmeProduto>();
		}
		return listaProdutos;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<JmeCategoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<JmeCategoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
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
		
		for(int i=0;i<listaProdutos.size();i++){
			
			valorTotal=listaProdutos.get(i).getValorUnitario()*listaProdutos.get(i).getQuantEstoque()+valorTotal;
		System.out.println("Valor Total:"+ valorTotal);
		}

	}

	/**
	 * Mï¿½todo Responsavel por preparar para inserir um novo produto
	 */
	public void preparaNovo() {
		produto = new JmeProduto();
		JmeFornecedorDAO dao = new JmeFornecedorDAO();
		listaFornecedores = dao.listar();

		JmeCategoriaDAO daoCategoria = new JmeCategoriaDAO();
		listaCategoria = daoCategoria.listar();

	}

	public void preparaEditar() {
		produto = new JmeProduto();
		JmeFornecedorDAO dao = new JmeFornecedorDAO();
		listaFornecedores = dao.listar();

	}

	public void incluir() {
		try {
			JmeProdutoDAO proDAO = new JmeProdutoDAO();

			produto.setDataCadastro(new Date());
			produto.setQuantEstoque(0);
			
			if(produto.getMarca().isEmpty()){
				produto.setMarca("0");
			}
			if(produto.getModelo().isEmpty()){
				produto.setModelo("0");
			}
			proDAO.salvar(produto);
			// atualizar o banco
			listaProdutos = proDAO.listar();
			produto = new JmeProduto();

			JSFUtil.adicionarMensagemSucesso("Salvo com sucesso.");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao Tentar incluir");
		}
	}

	public void exluir() {
		try {
			JmeItensVendaDAO daoItens = new JmeItensVendaDAO();
			JmeItensVenda itensVenda = new JmeItensVenda();
			itensVenda = daoItens.buscarPorProduto(produto.getIdProduto());
			if (itensVenda != null) {
				JSFUtil.adicionarMensagemErro("Impossível excluir Produto, o mesmo está sendo utilizado na venda. Para excluir o produto, exclui a venda");
				return;
			}
			
			JmeMovimentacaoDAO daoMovimentacao = new JmeMovimentacaoDAO();
			JmeMovimentacao movimentacao = new JmeMovimentacao();
			movimentacao = daoMovimentacao.buscarPorProduto(produto.getIdProduto());
			if(movimentacao!=null){
				JSFUtil.adicionarMensagemErro("Impossível excluir Produto, o mesmo está sendo utilizado na Movimentação - Entrada. Para excluir o produto, exclui a Entrada do produto");
			return;
			}

			JmeProdutoDAO dao = new JmeProdutoDAO();
			dao.excluir(produto);
			JSFUtil.adicionarMensagemSucesso("Excluido com sucesso.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
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
