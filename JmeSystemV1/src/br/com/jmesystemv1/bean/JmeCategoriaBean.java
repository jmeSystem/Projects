package br.com.jmesystemv1.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeCategoriaDAO;
import br.com.jmesystemv1.dao.JmeClienteDAO;
import br.com.jmesystemv1.dao.JmeFornecedorDAO;
import br.com.jmesystemv1.dao.JmeProdutoDAO;
import br.com.jmesystemv1.domain.JmeCategoria;
import br.com.jmesystemv1.domain.JmeCliente;
import br.com.jmesystemv1.domain.JmeProduto;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBCategoria")
@ViewScoped
public class JmeCategoriaBean {

	private List<JmeCategoria> listagemCategoria;
	private List<JmeCategoria> itensFiltrados;
	private JmeCategoria categoria;

	public List<JmeCategoria> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(List<JmeCategoria> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public JmeCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(JmeCategoria categoria) {
		this.categoria = categoria;
	}

	public List<JmeCategoria> getListagemCategoria() {
		return listagemCategoria;
	}

	public void setListagemCategoria(List<JmeCategoria> listagemCategoria) {
		this.listagemCategoria = listagemCategoria;
	}

	public void carregarListagem() {
		try {
			JmeCategoriaDAO daoCategoria = new JmeCategoriaDAO();
			listagemCategoria = daoCategoria.listar();
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao listar Categoria");

		}
	}

	public void salvar() {
		JmeCategoriaDAO dao = new JmeCategoriaDAO();

		try {
			dao.salvar(categoria);

			// atualizar o banco
			listagemCategoria = dao.listar();
			// Serve para limpar o objeto após a gravação(ou seja limpa todos os
			// campos do dialog)
			categoria = new JmeCategoria();
			JSFUtil.adicionarMensagemSucesso("Salvo com sucesso.");

		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao inserir" + ex.getMessage());
		}
	}

	public void excluir() {
		JmeCategoriaDAO dao = new JmeCategoriaDAO();
		try {
			JmeProdutoDAO daoProduto = new JmeProdutoDAO();
			JmeProduto produto = new JmeProduto();

			produto = daoProduto.buscarPorCategoria(categoria.getCodigo());
			if (produto != null) {
				JSFUtil.adicionarMensagemErro("Impossível excluir a Categoria, o mesmo está sendo utilizado no Produto. Para excluir a categoria, exclui o produto que está utilizando a categoria");
				return;
			}

			dao.excluir(categoria);

			JSFUtil.adicionarMensagemSucesso("Excluido com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();

			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void editar() {
		JmeCategoriaDAO dao = new JmeCategoriaDAO();
		try {

			dao.editar(categoria);
			JSFUtil.adicionarMensagemSucesso("Alterado com sucesso.");

		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void prepararNovo() {
		categoria = new JmeCategoria();

	}
}
