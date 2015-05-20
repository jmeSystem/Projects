package br.com.jmesystemv1.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import br.com.jmesystemv1.dao.JmeFormaPagamentoDAO;
import br.com.jmesystemv1.dao.JmeItensVendaDAO;
import br.com.jmesystemv1.dao.JmePagamentoDAO;
import br.com.jmesystemv1.dao.JmeParcelaDAO;
import br.com.jmesystemv1.dao.JmeProdutoDAO;
import br.com.jmesystemv1.dao.JmeRegistroVendasDAO;
import br.com.jmesystemv1.dao.JmeTipoFormaPagamentoDAO;
import br.com.jmesystemv1.domain.JmeFormaPagamento;
import br.com.jmesystemv1.domain.JmeItensVenda;
import br.com.jmesystemv1.domain.JmePagamento;
import br.com.jmesystemv1.domain.JmeProduto;
import br.com.jmesystemv1.domain.JmeRegistroVendas;
import br.com.jmesystemv1.domain.JmeTipoFormaPagamento;
import br.com.jmesystemv1.filter.JmeVendaFilter;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBListagemVendas")
@ViewScoped
public class JmeListagemVendaBean {

	private List<JmeRegistroVendas> listagemVendas;
	private JmeRegistroVendas vendaCadastro;
	private JmeVendaFilter filtro;
	private List<JmeRegistroVendas> listaVendasFiltradas;
	private List<JmePagamento> pagamento;
	private List<JmeItensVenda> itensVenda;
	private List<JmeProduto> produtos;
	private JmeItensVenda vendaItens;
	private JmePagamento pagamento2 = new JmePagamento();
	private List<JmeFormaPagamento> comboFormaPagamento;
	private List<JmeTipoFormaPagamento> comboTipoFormaPagamento;
	private boolean abilitarEdicaoVenda = false;
	private boolean abilitarListagemVenda = true;

	
	
	public JmePagamento getPagamento2() {
		return pagamento2;
	}

	public void setPagamento2(JmePagamento pagamento2) {
		this.pagamento2 = pagamento2;
	}

	public List<JmeTipoFormaPagamento> getComboTipoFormaPagamento() {
		return comboTipoFormaPagamento;
	}

	public void setComboTipoFormaPagamento(
			List<JmeTipoFormaPagamento> comboTipoFormaPagamento) {
		this.comboTipoFormaPagamento = comboTipoFormaPagamento;
	}

	public List<JmeFormaPagamento> getComboFormaPagamento() {
		return comboFormaPagamento;
	}

	public void setComboFormaPagamento(List<JmeFormaPagamento> comboFormaPagamento) {
		this.comboFormaPagamento = comboFormaPagamento;
	}

	public List<JmeProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<JmeProduto> produtos) {
		this.produtos = produtos;
	}

	public boolean isAbilitarListagemVenda() {
		return abilitarListagemVenda;
	}

	public void setAbilitarListagemVenda(boolean abilitarListagemVenda) {
		this.abilitarListagemVenda = abilitarListagemVenda;
	}

	public boolean isAbilitarEdicaoVenda() {
		return abilitarEdicaoVenda;
	}

	public void setAbilitarEdicaoVenda(boolean abilitarEdicaoVenda) {
		this.abilitarEdicaoVenda = abilitarEdicaoVenda;
	}

	public JmeItensVenda getVendaItens() {
		return vendaItens;
	}

	public void setVendaItens(JmeItensVenda vendaItens) {
		this.vendaItens = vendaItens;
	}

	public List<JmeItensVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<JmeItensVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public List<JmePagamento> getPagamento() {
		return pagamento;
	}

	public void setPagamento(List<JmePagamento> pagamento) {
		this.pagamento = pagamento;
	}

	public List<JmeRegistroVendas> getListagemVendas() {
		return listagemVendas;
	}

	public void setListagemVendas(List<JmeRegistroVendas> listagemVendas) {
		this.listagemVendas = listagemVendas;
	}

	public JmeRegistroVendas getVendaCadastro() {

		return vendaCadastro;
	}

	public void setVendaCadastro(JmeRegistroVendas vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public JmeVendaFilter getFiltro() {
		if (filtro == null) {
			filtro = new JmeVendaFilter();
		}
		return filtro;
	}

	public void setFiltro(JmeVendaFilter filtro) {
		this.filtro = filtro;
	}

	public List<JmeRegistroVendas> getListaVendasFiltradas() {
		return listaVendasFiltradas;
	}

	public void setListaVendasFiltradas(
			List<JmeRegistroVendas> listaVendasFiltradas) {
		this.listaVendasFiltradas = listaVendasFiltradas;
	}

	public void buscar() {
		try {
			JmeRegistroVendasDAO dao = new JmeRegistroVendasDAO();
			listagemVendas = dao.buscar(filtro);
		} catch (RuntimeException ex) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar buscar uma venda."
					+ ex.getMessage());
		}
	}

	// metodo resposavel por listar os pagamentos referente a venda
	public void listagemPagemento() {
		JmePagamentoDAO dao = new JmePagamentoDAO();

		pagamento = dao
				.listarTodosPagamento(vendaCadastro.getIdRegistroVenda());

	}

	// Metodo responsavel por buscar os itens referente a venda selecionada
	public void buscarItens() {
		JmeItensVendaDAO dao = new JmeItensVendaDAO();

		System.out.println("id" + vendaCadastro.getIdRegistroVenda());
		itensVenda = dao.buscarPorCodigo2(vendaCadastro.getIdRegistroVenda());

		// for(JmeItensVenda venda: itensVenda){
		// System.out.println(venda.getProduto().getDescricao());
		// }

	}

	// carrega os dados na table
	public void carregarListagem() {
		try {
			JmeRegistroVendasDAO dao = new JmeRegistroVendasDAO();

			listagemVendas = dao.listar();
		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void excluir() {

		try {
			JmeItensVendaDAO daoItensVenda = new JmeItensVendaDAO();
			JmePagamentoDAO daoPagamento = new JmePagamentoDAO();
			JmeRegistroVendasDAO daoRegistroVendasDAO = new JmeRegistroVendasDAO();
			JmeParcelaDAO daoParcela = new JmeParcelaDAO();

			daoParcela.excluirPorRegistroVenda(vendaCadastro
					.getIdRegistroVenda());
			daoItensVenda.excluirPorRegistroVenda(vendaCadastro
					.getIdRegistroVenda());
			daoPagamento.excluirPorRegistroVenda(vendaCadastro
					.getIdRegistroVenda());
			daoRegistroVendasDAO.excluirPorRegistroVenda(vendaCadastro
					.getIdRegistroVenda());

			JSFUtil.adicionarMensagemSucesso("Exluido com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao excluir a venda" + e.getMessage());
		}
	}

	// Metodo responsavel por carregar a venda, e abilitar tela de edição da
	// venda
	public void prepararEditar() {
		JmeProdutoDAO daoProdutos = new JmeProdutoDAO();
		JmeItensVendaDAO daoItensVenda = new JmeItensVendaDAO();
		JmeFormaPagamentoDAO daoFormaPagamento = new JmeFormaPagamentoDAO();
		JmeTipoFormaPagamentoDAO daoTipoFormaPagamento = new JmeTipoFormaPagamentoDAO();
		System.out.println("Passou no preparar Editar");
		abilitarEdicaoVenda = true;
		abilitarListagemVenda = false;

		produtos = daoProdutos.listar();
		itensVenda = daoItensVenda.buscarPorCodigo2(vendaCadastro
				.getIdRegistroVenda());
		comboFormaPagamento = daoFormaPagamento.listar();
		comboTipoFormaPagamento = daoTipoFormaPagamento.listar();
		System.out.println("IDVenda:" + vendaCadastro.getIdRegistroVenda()+"Total da Venda"+ vendaCadastro.getValorTotal());
	}

	public String handleFlow(FlowEvent event) {

		String currentStepId = event.getOldStep();
		String stepToGo = event.getNewStep();

		if (itensVenda.isEmpty()) {
			JSFUtil.adicionarMensagemErro("Selecione um produto!");
			RequestContext.getCurrentInstance().update("msgGlobal");
			return currentStepId;
		} else {
			return stepToGo;
		}
	}
}
