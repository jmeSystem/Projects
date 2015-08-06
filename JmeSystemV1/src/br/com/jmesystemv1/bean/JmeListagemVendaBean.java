package br.com.jmesystemv1.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import br.com.jmesystemv1.domain.JmeParcela;
import br.com.jmesystemv1.domain.JmeProduto;
import br.com.jmesystemv1.domain.JmeRegistroVendas;
import br.com.jmesystemv1.domain.JmeTipoFormaPagamento;
import br.com.jmesystemv1.filter.JmeVendaFilter;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBListagemVendas")
@ViewScoped
public class JmeListagemVendaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<JmeRegistroVendas> listagemVendas;
	private JmeRegistroVendas vendaCadastro;
	private JmeVendaFilter filtro;
	private List<JmeRegistroVendas> listaVendasFiltradas;
	private List<JmePagamento> listagemPagamento;
	private List<JmeItensVenda> itensVenda;
	private List<JmeProduto> produtos;
	private JmeItensVenda vendaItens;
	private List<JmeParcela> listagemParcelas;
	private JmePagamento pagamento = new JmePagamento();
	private List<JmeFormaPagamento> comboFormaPagamento;
	private List<JmeTipoFormaPagamento> comboTipoFormaPagamento;
	private boolean abilitarEdicaoVenda = false;
	private boolean abilitarListagemVenda = true;
	private boolean abilitarParcelas = false;
	private JmeParcela parcela;
	private Date dataLocal;
	private List<JmeTipoFormaPagamento> listagemTipoFormaPagamento;

	// Responsavel por listar os registro da venda da segunda via
	private List<JmeRegistroVendas> listagemSegundaVia;
	private List<JmeItensVenda> listagemSegundaViaItens;
	private boolean abilitarSegundaVia;
	private List<JmeParcela> listagemSegundaViaParcela;
	private Double valorParcela = 0.0;
	private Integer quantidadeParcela = 0;

	public Date getDataLocal() {
		return dataLocal;
	}

	public void setDataLocal(Date dataLocal) {
		this.dataLocal = dataLocal;
	}

	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	public Double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(Double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public List<JmeParcela> getListagemSegundaViaParcela() {
		return listagemSegundaViaParcela;
	}

	public void setListagemSegundaViaParcela(
			List<JmeParcela> listagemSegundaViaParcela) {
		this.listagemSegundaViaParcela = listagemSegundaViaParcela;
	}

	public boolean isAbilitarSegundaVia() {
		return abilitarSegundaVia;
	}

	public void setAbilitarSegundaVia(boolean abilitarSegundaVia) {
		this.abilitarSegundaVia = abilitarSegundaVia;
	}

	public List<JmeItensVenda> getListagemSegundaViaItens() {
		return listagemSegundaViaItens;
	}

	public void setListagemSegundaViaItens(
			List<JmeItensVenda> listagemSegundaViaItens) {
		this.listagemSegundaViaItens = listagemSegundaViaItens;
	}

	public List<JmeRegistroVendas> getListagemSegundaVia() {
		return listagemSegundaVia;
	}

	public void setListagemSegundaVia(List<JmeRegistroVendas> listagemSegundaVia) {
		this.listagemSegundaVia = listagemSegundaVia;
	}

	public JmePagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(JmePagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<JmePagamento> getListagemPagamento() {
		return listagemPagamento;
	}

	public void setListagemPagamento(List<JmePagamento> listagemPagamento) {
		this.listagemPagamento = listagemPagamento;
	}

	public List<JmeTipoFormaPagamento> getListagemTipoFormaPagamento() {
		return listagemTipoFormaPagamento;
	}

	public void setListagemTipoFormaPagamento(
			List<JmeTipoFormaPagamento> listagemTipoFormaPagamento) {
		this.listagemTipoFormaPagamento = listagemTipoFormaPagamento;
	}

	public JmeParcela getParcela() {
		return parcela;
	}

	public void setParcela(JmeParcela parcela) {
		this.parcela = parcela;
	}

	public List<JmeParcela> getListagemParcelas() {
		return listagemParcelas;
	}

	public void setListagemParcelas(List<JmeParcela> listagemParcelas) {
		this.listagemParcelas = listagemParcelas;
	}

	public boolean isAbilitarParcelas() {
		return abilitarParcelas;
	}

	public void setAbilitarParcelas(boolean abilitarParcelas) {
		this.abilitarParcelas = abilitarParcelas;
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

	public void setComboFormaPagamento(
			List<JmeFormaPagamento> comboFormaPagamento) {
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

			for (JmeRegistroVendas vendas : listagemVendas) {
				System.out.println("Vendas" + vendas);
			}

		} catch (RuntimeException ex) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar buscar uma venda."
					+ ex.getMessage());
		}
	}

	// metodo resposavel por listar os pagamentos e parcelas referente a venda
	public void listagemPagamento() {
		JmePagamentoDAO dao = new JmePagamentoDAO();
		JmeParcelaDAO daoParcela = new JmeParcelaDAO();

		listagemPagamento = dao.listarTodosPagamento(vendaCadastro
				.getIdRegistroVenda());
		listagemParcelas = daoParcela.listarTodasParcela(vendaCadastro
				.getIdRegistroVenda());

		dataLocal = new Date();
		JmeParcela parcelaVencidas = new JmeParcela();
		
		for (int i = 0; i < listagemParcelas.size(); i++) {		
			Date vencimento = listagemParcelas.get(i).getDataVencimento();
			long diasVencidos = (dataLocal.getTime() - vencimento.getTime())
					/ (1000 * 60 * 60 * 24);
			int qtdDiasVencidos =(int) diasVencidos;
			
			//acrescentou mais 3 % no valor a pagar
			//double multa = listagemParcelas.get(i).getValor()*0.03;
			
			//multadiaria 3%
			double multaDiaria =((listagemParcelas.get(i).getValorPagar()*0.03)/30) *qtdDiasVencidos;
				 		
			parcelaVencidas.setQuantidadeDiasAtrasos(qtdDiasVencidos);
			parcelaVencidas.setIdParcela(listagemParcelas.get(i).getIdParcela());
			parcelaVencidas.setDataVencimento(listagemParcelas.get(i).getDataVencimento());
			parcelaVencidas.setNumeroParcela(listagemParcelas.get(i).getNumeroParcela());
			parcelaVencidas.setRegistroVenda(listagemParcelas.get(i).getRegistroVenda());
			parcelaVencidas.setSituacao(listagemParcelas.get(i).getSituacao());
			parcelaVencidas.setValor(listagemParcelas.get(i).getValor());
			parcelaVencidas.setValorPagar(listagemParcelas.get(i).getValor()+multaDiaria);
			//parcelaVencidas.setValorPagar(listagemParcelas.get(i).getValor()+multaDiaria+multa);
			
			System.out.println(parcelaVencidas.getQuantidadeDiasAtrasos());
			if(parcelaVencidas.getQuantidadeDiasAtrasos()<=0){
				return;
			}
			else{
				//atualizar a quantidade de dias atraso no banco
				daoParcela.editar(parcelaVencidas);
				listagemParcelas = daoParcela.listarTodasParcela(vendaCadastro
						.getIdRegistroVenda());
			}

		}
		
	}

	public void prepararPagamento() {
		pagamento = new JmePagamento();
		JmeTipoFormaPagamentoDAO daoTipoFormaPagamento = new JmeTipoFormaPagamentoDAO();
		comboTipoFormaPagamento = daoTipoFormaPagamento.listar();
		System.out.println("Passou");
	}

	public void excluirPagamento() {
		
		JmePagamentoDAO daoPagamento = new JmePagamentoDAO();
		daoPagamento.excluirPagamento(pagamento);
		listagemPagamento = daoPagamento.listarTodosPagamento(vendaCadastro
				.getIdRegistroVenda());

		JmeParcelaDAO daoParcela = new JmeParcelaDAO();
		
		daoParcela.atualizarStatusParcela("Pendente", pagamento.getNumeroParcelas(),pagamento.getRegistroVendas().getIdRegistroVenda());
				
		listagemParcelas = daoParcela.listarTodasParcela(vendaCadastro
				.getIdRegistroVenda());
		JSFUtil.adicionarMensagemSucesso("Excluido com sucesso.");
		
		JmeRegistroVendasDAO daoRegistroVendas = new JmeRegistroVendasDAO();
		vendaCadastro.setSituacao("Pendente");
		daoRegistroVendas.editar(vendaCadastro);
		listagemVendas = daoRegistroVendas.listar();
		
		
	}

	// Metodo responsavel por efetuar o pagamento
	public void efetuarPagamento() {
		JmePagamentoDAO daoPagamento = new JmePagamentoDAO();

		pagamento.setDataPagamento(new Date());
		pagamento.getRegistroVendas().setIdRegistroVenda(
				vendaCadastro.getIdRegistroVenda());
		pagamento.setNumeroParcelas(parcela.getNumeroParcela());

		// Listagem referente a única parcela
		List<JmePagamento> listagemParcelas = new ArrayList<JmePagamento>();

		// responsavel por buscar os pagamentos referente a parcela que está
		// sendo pago
		listagemParcelas = daoPagamento.buscarPorParcela(
				parcela.getNumeroParcela(), vendaCadastro.getIdRegistroVenda());

		// Se não estiver nenhum pagamento referente a essa parcela, e o valor
		// do pagamento for 0 ou menor que 0, exibi uma mensagem e não deixar
		// salvar
		if (listagemParcelas.isEmpty() && pagamento.getValorPagamento() <= 0) {
			JSFUtil.adicionarMensagemErro("O valor Pago tem que ser maior que 0.");
			return;
		}

		// Se já houver pagamento referente a essa venda
		else {
			// variavél responsavel por armazenar o pagamento referente a unica
			// parcela
			double valorPago = 0;

			// variavel responsavel por armazenar o valor que está sendo pago e
			// que já foram pagos
			double valorTotalPagamento = 0;

			for (int i = 0; i < listagemParcelas.size(); i++) {
				valorPago = valorPago
						+ listagemParcelas.get(i).getValorPagamento();
			}

			// Valor Total Pago
			valorTotalPagamento = valorPago + pagamento.getValorPagamento();
			System.out.println("Valor Pago:" + valorTotalPagamento + "/n");
			
			// se o valorTotalPagamento for maior que a parcela emiti um alerta
			if (parcela.getValor().equals(parcela.getValorPagar()) && parcela.getValor()< valorTotalPagamento) {
				JSFUtil.adicionarMensagemErro("O valor Pago tem que ser menor e/ou igual a parcela.");
				pagamento.setValorPagamento(null);
				return;
				// se o valorPagamento for 0 ou menor que 0 emiti um alerta
			}else if(parcela.getValorPagar()< valorTotalPagamento){
				JSFUtil.adicionarMensagemErro("O valor Pago tem que ser menor e/ou igual a parcela.");
				pagamento.setValorPagamento(null);
				return;
				
			}
			else if (pagamento.getValorPagamento() <= 0) {
				JSFUtil.adicionarMensagemErro("O valor Pago tem que ser maior que 0.");
				pagamento.setValorPagamento(null);
				return;
			}
			//acrescentei (|| parcela.getValorPagar()==valorTotalPagamento)
			else if (parcela.getValor() == valorTotalPagamento || parcela.getValorPagar()==valorTotalPagamento) {
				System.out.println("Parcela paga");

				JmeParcelaDAO daoParcela = new JmeParcelaDAO();
				parcela.setSituacao("Pago");
				daoParcela.editar(parcela);
				daoPagamento.salvar(pagamento);
			} else {
				daoPagamento.salvar(pagamento);
			}

			// atualizar a tabela pagamento e parcelas
			this.listagemPagamento();
			// JmePagamentoDAO dao = new JmePagamentoDAO();
			// listagemPagamento =
			// dao.listarTodosPagamento(vendaCadastro.getIdRegistroVenda());
			JSFUtil.adicionarMensagemSucesso("Pagamento Realizado com sucesso.");

			// Responsavel por verificar se tem alguma parcela pendente
			JmeParcelaDAO daoParcelas = new JmeParcelaDAO();
			List<JmeParcela> listaParcelas = new ArrayList<JmeParcela>();
			listaParcelas = daoParcelas.listarTodasParcela(vendaCadastro
					.getIdRegistroVenda());

			String situacaoParcelas = null;
			//Responsavel por verificar se todas as parcelas referente a venda está pago,
			//se todas estiver alterar o status da venda como pago
			for (int i = 0; i < listaParcelas.size(); i++) {
				situacaoParcelas = listaParcelas.get(i).getSituacao();

				if (situacaoParcelas.equals("Pendente")) {
					situacaoParcelas = "Pendente";
					break;
				} else {
					situacaoParcelas = "Pago";
				}
			}

			if (situacaoParcelas == "Pago") {
				System.out.println("Venda Paga");
				JmeRegistroVendasDAO daoVendas = new JmeRegistroVendasDAO();
				vendaCadastro.setSituacao("Pago");
				daoVendas.editar(vendaCadastro);
				this.carregarListagem();
			}

			pagamento.setValorPagamento(null);
		}
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

	// carrega os dados na tabela
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

			listagemVendas = daoRegistroVendasDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Exluido com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao excluir a venda" + e.getMessage());
		}
	}

	public void segundaViaVenda(JmeRegistroVendas vendas) {
		abilitarListagemVenda = false;
		abilitarSegundaVia = true;

		JmeItensVendaDAO daoItens = new JmeItensVendaDAO();
		listagemSegundaViaItens = daoItens.buscarPorCodigo2(vendas
				.getIdRegistroVenda());

		JmeParcelaDAO daoParcela = new JmeParcelaDAO();
		listagemSegundaViaParcela = daoParcela.listarTodasParcela(vendas
				.getIdRegistroVenda());

		int cont = 0;

		for (int i = 0; i < listagemSegundaViaParcela.size(); i++) {

			valorParcela = listagemSegundaViaParcela.get(i).getValor();

			cont++;
			quantidadeParcela = quantidadeParcela + cont;
			System.out.println(cont + "\n" + valorParcela);
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
		System.out.println("IDVenda:" + vendaCadastro.getIdRegistroVenda()
				+ "Total da Venda" + vendaCadastro.getValorTotal());
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
