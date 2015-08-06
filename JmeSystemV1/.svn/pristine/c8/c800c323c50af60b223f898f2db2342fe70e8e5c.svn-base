package br.com.jmesystemv1.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeFornecedorDAO;
import br.com.jmesystemv1.dao.JmeFuncionarioDAO;
import br.com.jmesystemv1.dao.JmeProdutoDAO;
import br.com.jmesystemv1.domain.JmeFornecedor;
import br.com.jmesystemv1.domain.JmeFuncionario;
import br.com.jmesystemv1.domain.JmeItensPedido;
import br.com.jmesystemv1.domain.JmePedidoCompra;
import br.com.jmesystemv1.domain.JmeProduto;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBPedidoCompra")
@ViewScoped
public class JmePedidoCompraBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JmeFornecedor fornecedor;
	private JmeFuncionario funcionario;

	private List<JmePedidoCompra> itens;
	private List<JmePedidoCompra> itensFiltrados;
	private List<JmeFornecedor> comboFornecedor;
	private List<JmeFuncionario> comboFuncionario;

	/** Listagem de Produtos na Tela de Pedidos */
	private List<JmeProduto> listaProdutos;
	private List<JmeProduto> listaProdutosFiltrados;

	/** Adicionar Itens na lista de itens pedidos */
	private List<JmeItensPedido> listaItens;

	private JmePedidoCompra pedidoCadastro;
	
	private String status;
	
	
	

	public JmeFornecedor getFornecedor() {
		if (fornecedor == null) {
			fornecedor = new JmeFornecedor();
		}
		return fornecedor;
	}

	public void setFornecedor(JmeFornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public JmeFuncionario getFuncionario() {
		if (funcionario == null) {
			funcionario = new JmeFuncionario();
		}
		return funcionario;
	}

	public void setFuncionario(JmeFuncionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<JmePedidoCompra> getItens() {
		return itens;
	}

	public void setItens(List<JmePedidoCompra> itens) {
		this.itens = itens;
	}

	public List<JmePedidoCompra> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(List<JmePedidoCompra> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public List<JmeFornecedor> getComboFornecedor() {
		return comboFornecedor;
	}

	public void setComboFornecedor(List<JmeFornecedor> comboFornecedor) {
		this.comboFornecedor = comboFornecedor;
	}

	public List<JmeFuncionario> getComboFuncionario() {
		return comboFuncionario;
	}

	public void setComboFuncionario(List<JmeFuncionario> comboFuncionario) {
		this.comboFuncionario = comboFuncionario;
	}

	public List<JmeProduto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<JmeProduto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<JmeProduto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}

	public void setListaProdutosFiltrados(
			List<JmeProduto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}

	public List<JmeItensPedido> getListaItens() {
		if (listaItens == null) {
			listaItens = new ArrayList<JmeItensPedido>();
		}
		return listaItens;
	}

	public void setListaItens(List<JmeItensPedido> listaItens) {
		this.listaItens = listaItens;
	}

	public JmePedidoCompra getPedidoCadastro() {
		if (pedidoCadastro == null) {
			pedidoCadastro = new JmePedidoCompra();
			pedidoCadastro.setValorTotal(new Double("0.00"));
		}
		return pedidoCadastro;
	}

	public void setPedidoCadastro(JmePedidoCompra pedidoCadastro) {
		this.pedidoCadastro = pedidoCadastro;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

	public void listagem() {

		try {

			JmeProdutoDAO produtoDAO = new JmeProdutoDAO();
			listaProdutos = produtoDAO.listar();
			
			JmeFornecedorDAO fornecedorDAO = new JmeFornecedorDAO();
			comboFornecedor = fornecedorDAO.listar();
			
			JmeFuncionarioDAO funcionarioDAO = new JmeFuncionarioDAO();
			comboFuncionario = funcionarioDAO.listar();

		} catch (RuntimeException ex) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar listar os produtos: "
					+ ex.getMessage());
		}
	}

	public void adicionar(JmeProduto produto) {
		int posicaoEncontrada = -1;

		/** posicao vai de 0 ate listaItens -1 (0 ate 499 (500 -1)) */
		for (int posicao = 0; posicao < listaItens.size()
				&& posicaoEncontrada < 0; posicao++) {
			JmeItensPedido itemTemporario = listaItens.get(posicao);

			if (itemTemporario.getIdProduto().equals(produto)) {
				posicaoEncontrada = posicao;
			}

		}

		JmeItensPedido item = new JmeItensPedido();
		item.setIdProduto(produto);

		/**
		 * Se posicaoEncontrada for menor que zero, então o item não foi
		 * encontrado. Senão altera a quantidade do item
		 */
		if (produto.getQuantEstoque() >= produto.getEstoqueMax()) {
			JSFUtil.adicionarMensagemErro
			("Quantidade de estoque do produto está no máximo, verificar se há necessidade de pedir o mesmo");

		} else {
			if (posicaoEncontrada < 0) {
				item.setQuantidadeProduto(1);
				item.setValorUnitario(produto.getValorUnitario());
				item.setValorTotalItens(produto.getValorUnitario()
						* item.getQuantidadeProduto());
				
				listaItens.add(item);
				pedidoCadastro.setValorTotal(pedidoCadastro.getValorTotal()
						+ produto.getValorUnitario());
			} else {
				JmeItensPedido itemTemp = listaItens.get(posicaoEncontrada);
				item.setQuantidadeProduto(itemTemp.getQuantidadeProduto() + 1);
				item.setValorUnitario(produto.getValorUnitario());
				item.setValorTotalItens(produto.getValorUnitario()
						* item.getQuantidadeProduto());

				listaItens.set(posicaoEncontrada, item);
				pedidoCadastro.setValorTotal(pedidoCadastro.getValorTotal()
						+ produto.getValorUnitario());
			}
		}

	}

	public void remover(JmeItensPedido item) {

		int posicaoEncontrada = -1;

		/** posicao vai de 0 ate listaItens -1 (0 ate 499 (500 -1)) */
		for (int posicao = 0; posicao < listaItens.size()
				&& posicaoEncontrada < 0; posicao++) {
			JmeItensPedido itemTemporario = listaItens.get(posicao);

			if (itemTemporario.getIdProduto().equals(item.getIdProduto())) {
				posicaoEncontrada = posicao;
			}

		}

		if (posicaoEncontrada > -1) {
			listaItens.remove(posicaoEncontrada);
			pedidoCadastro.setValorTotal(pedidoCadastro.getValorTotal()
					- item.getValorTotalItens());

		}

	}

}
