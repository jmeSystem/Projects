package br.com.jmesystemv1.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeFornecedorDAO;
import br.com.jmesystemv1.dao.JmeMovimentacaoDAO;
import br.com.jmesystemv1.dao.JmeProdutoDAO;
import br.com.jmesystemv1.domain.JmeFornecedor;
import br.com.jmesystemv1.domain.JmeMovimentacao;
import br.com.jmesystemv1.domain.JmeProduto;
import br.com.jmesystemv1.filter.JmeMovEntradaFilter;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBMovimentacao")
@ViewScoped
public class JmeMovimentacaoBean {

	private List<JmeMovimentacao> listagemMovimentacao;
	private List<JmeMovimentacao> itensFiltrados;
	private JmeMovimentacao movimentacao;
	private List<JmeFornecedor> comboFornecedor;
	private List<JmeProduto> comboProduto;
	private JmeMovEntradaFilter filtroMov;
	
	public JmeMovEntradaFilter getFiltroMov() {
		return filtroMov;
	}

	public void setFiltroMov(JmeMovEntradaFilter filtroMov) {
		this.filtroMov = filtroMov;
	}

	public List<JmeMovimentacao> getItensFiltrados() {
		if(filtroMov==null){
			filtroMov= new JmeMovEntradaFilter();
		}
		return itensFiltrados;
	}

	public void setItensFiltrados(List<JmeMovimentacao> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public List<JmeFornecedor> getComboFornecedor() {
		return comboFornecedor;
	}

	public void setComboFornecedor(List<JmeFornecedor> comboFornecedor) {
		this.comboFornecedor = comboFornecedor;
	}

	public List<JmeProduto> getComboProduto() {
		return comboProduto;
	}

	public void setComboProduto(List<JmeProduto> comboProduto) {
		this.comboProduto = comboProduto;
	}

	public List<JmeMovimentacao> getListagemMovimentacao() {
		return listagemMovimentacao;
	}

	public void setListagemMovimentacao(
			List<JmeMovimentacao> listagemMovimentacao) {
		this.listagemMovimentacao = listagemMovimentacao;
	}

	public JmeMovimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(JmeMovimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public void listagem() {
		JmeMovimentacaoDAO daoMovimentacao = new JmeMovimentacaoDAO();
		try {
			listagemMovimentacao = daoMovimentacao.listar();
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao listar movimentação");
		}
	}

	public void preparNovo() {
		movimentacao = new JmeMovimentacao();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
		Date dataEntrada = calendar.getTime();
		movimentacao.setDataEntrada(dataEntrada);

		JmeFornecedorDAO daoFornecedor = new JmeFornecedorDAO();
		comboFornecedor = daoFornecedor.listar();

		JmeProdutoDAO daoProduto = new JmeProdutoDAO();
		comboProduto = daoProduto.listar();

	}

	public void prepararEditar() {
		movimentacao = new JmeMovimentacao();
		JmeFornecedorDAO daoFornecedor = new JmeFornecedorDAO();
		comboFornecedor = daoFornecedor.listar();

		JmeProdutoDAO daoProduto = new JmeProdutoDAO();
		comboProduto = daoProduto.listar();

	}
public void excluir() {

		try {
			JmeMovimentacaoDAO dao = new JmeMovimentacaoDAO();	
			dao.excluir(movimentacao);
			JSFUtil.adicionarMensagemSucesso("Excluido com sucesso.");

		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao excluir o produto.");
		}
	}

	public void salvar() {
		try {
			JmeMovimentacaoDAO daoMovimentacao = new JmeMovimentacaoDAO();
			
			if(movimentacao.getQuantidade()<=0){
				JSFUtil.adicionarMensagemErro("A quantidade de entrada tem que ser maior que 0");
				return;
			}
			daoMovimentacao.salvar(movimentacao);
			listagemMovimentacao =daoMovimentacao.listar();
			movimentacao = new JmeMovimentacao();
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
			Date dataEntrada = calendar.getTime();
			movimentacao.setDataEntrada(dataEntrada);
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao salvar");
		}
	}

	public void editar() {
		JmeMovimentacaoDAO daoMovimentacao = new JmeMovimentacaoDAO();
		try {
			
			daoMovimentacao.editar(movimentacao);

			JSFUtil.adicionarMensagemSucesso("Alterado com sucesso.");

		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void buscar() {
		try {
			JmeMovimentacaoDAO dao = new JmeMovimentacaoDAO();
			
			listagemMovimentacao = dao.buscar(filtroMov);
		} catch (RuntimeException ex) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar buscar uma venda."
					+ ex.getMessage());
		}
	}

}
