package br.com.jmesystemv1.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeClienteDAO;
import br.com.jmesystemv1.dao.JmeFormaPagamentoDAO;
import br.com.jmesystemv1.dao.JmeFuncionarioDAO;
import br.com.jmesystemv1.dao.JmeItensVendaDAO;
import br.com.jmesystemv1.dao.JmePagamentoDAO;
import br.com.jmesystemv1.dao.JmeParcelaDAO;
import br.com.jmesystemv1.dao.JmeProdutoDAO;
import br.com.jmesystemv1.dao.JmeRegistroVendasDAO;
import br.com.jmesystemv1.dao.JmeTipoFormaPagamentoDAO;
import br.com.jmesystemv1.domain.JmeCliente;
import br.com.jmesystemv1.domain.JmeFormaPagamento;
import br.com.jmesystemv1.domain.JmeFuncionario;
import br.com.jmesystemv1.domain.JmeItensVenda;
import br.com.jmesystemv1.domain.JmePagamento;
import br.com.jmesystemv1.domain.JmeParcela;
import br.com.jmesystemv1.domain.JmeProduto;
import br.com.jmesystemv1.domain.JmeRegistroVendas;
import br.com.jmesystemv1.domain.JmeTipoFormaPagamento;
import br.com.jmesystemv1.filter.JmeVendaFilter;
import br.com.jmesystemv1.util.JSFUtil;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

@ManagedBean(name = "MBRegistroVenda")
@ViewScoped
public class JmeRegistroVendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<JmeRegistroVendas> listagemVendas;
	private JmeVendaFilter filtro;
	private List<JmeRegistroVendas> listaVendasFiltradas;

	@ManagedProperty(value = "#{MBAutentificacao}")
	private JmeAutenticacaoBean autenticacaoBean;

	private List<JmeItensVenda> itensVenda;

	// ARMAZANA OS PRODUTOS FILTRADOS
	private List<JmeProduto> itensFiltrados;

	// LISTAGEM DE TODOS OS PRODUTOS
	private List<JmeProduto> listaProdutos;

	private Integer controlador = 0;

	private Double valorDesconto;
	// Responsavel por limitar a quantidade de parcela, de acordo com a forma de
	// pagamento escolhido
	private Integer quantidadeParcela;

	// ARMAZENA OS CLIENTES CADASTRADOS
	private List<JmeCliente> comboCliente;

	// ARMAZENA AS FORMAS DE PAGAMENTO
	private List<JmeFormaPagamento> comboPagamento;

	private List<JmeTipoFormaPagamento> comboTipoFormaPagamento;

	private JmeFuncionario funcionario;

	private JmeParcela parcela = new JmeParcela();
	// Objeto Pagamento
	private JmePagamento pagamento = new JmePagamento();

	private boolean skip;

	private boolean abilitarPagamento = false;

	private Double desconto = 0.0;

	private Double acrescimo = 0.0;

	private Double valorRecebido = 0.0;

	private Double valorVoltar = 0.0;

	private Double valorDescontoAcrescimo = 0.0;

	private int tipoAcrescimo;
	// Será responsavel para guarda a venda que eu estou realizando
	private JmeRegistroVendas vendaCadastro;

	private List<JmeItensVenda> listaItens;

	/* =======================GET-SET================= */

	
	public boolean isSkip() {
		return skip;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public List<JmeRegistroVendas> getListagemVendas() {
		return listagemVendas;
	}

	public void setListagemVendas(List<JmeRegistroVendas> listagemVendas) {
		this.listagemVendas = listagemVendas;
	}

	public JmeVendaFilter getFiltro() {
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

	public List<JmeItensVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<JmeItensVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public JmeFuncionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(JmeFuncionario funcionario) {
		this.funcionario = funcionario;
	}

	public JmeAutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(JmeAutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public Double getValorVoltar() {
		return valorVoltar;
	}

	public void setValorVoltar(Double valorVoltar) {
		this.valorVoltar = valorVoltar;
	}

	public Double getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(Double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public boolean isAbilitarPagamento() {
		return abilitarPagamento;
	}

	public void setAbilitarPagamento(boolean abilitarPagamento) {
		this.abilitarPagamento = abilitarPagamento;
	}

	public List<JmeTipoFormaPagamento> getComboTipoFormaPagamento() {
		return comboTipoFormaPagamento;
	}

	public void setComboTipoFormaPagamento(
			List<JmeTipoFormaPagamento> comboTipoFormaPagamento) {
		this.comboTipoFormaPagamento = comboTipoFormaPagamento;
	}

	public JmeParcela getParcela() {
		return parcela;
	}

	public void setParcela(JmeParcela parcela) {
		this.parcela = parcela;
	}

	public Integer getControlador() {
		return controlador;
	}

	public void setControlador(Integer controlador) {
		this.controlador = controlador;
	}

	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	public Double getValorDescontoAcrescimo() {
		return valorDescontoAcrescimo;
	}

	public void setValorDescontoAcrescimo(Double valorDescontoAcrescimo) {
		this.valorDescontoAcrescimo = valorDescontoAcrescimo;
	}

	public int getTipoAcrescimo() {
		return tipoAcrescimo;
	}

	public void setTipoAcrescimo(int tipoAcrescimo) {
		this.tipoAcrescimo = tipoAcrescimo;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(Double acrescimo) {
		this.acrescimo = acrescimo;
	}

	public JmePagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(JmePagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<JmeFormaPagamento> getComboPagamento() {
		return comboPagamento;
	}

	public void setComboPagamento(List<JmeFormaPagamento> comboPagamento) {
		this.comboPagamento = comboPagamento;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public List<JmeProduto> getListaProdutos() {
		return listaProdutos;
	}

	public List<JmeProduto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(List<JmeProduto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public void setListaProdutos(List<JmeProduto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<JmeItensVenda> getListaItens() {
		if (listaItens == null) {
			listaItens = new ArrayList<JmeItensVenda>();
		}

		return listaItens;
	}

	public void setListaItens(List<JmeItensVenda> listaItens) {
		this.listaItens = listaItens;
	}

	public List<JmeCliente> getComboCliente() {
		return comboCliente;
	}

	public void setComboCliente(List<JmeCliente> comboCliente) {
		this.comboCliente = comboCliente;
	}

	public JmeRegistroVendas getVendaCadastro() {

		if (vendaCadastro == null) {
			vendaCadastro = new JmeRegistroVendas();
			vendaCadastro.setValorTotal(0.00);
			pagamento.setNumeroParcelas(0);
			vendaCadastro.setQuantidade(0);
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(JmeRegistroVendas vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	/* =======================METODOS================= */

	// metodo responsalvel por calcular desconto da venda
	public void calcularDesconto() {
		// se a valor do desconto ou acrescimo for nulo, ele atribuir valor 0.0
		// tanto para desconto, quanto para acrescimo
		if (valorDescontoAcrescimo == null || valorDescontoAcrescimo == 0.0) {
			valorDescontoAcrescimo = 0.0;
			desconto = 0.0;
			acrescimo = 0.0;
			vendaCadastro.setValorPagar(vendaCadastro.getValorTotal());
		}

		if (tipoAcrescimo == 0 && valorDescontoAcrescimo != 0) {

			desconto = valorDescontoAcrescimo;
			acrescimo = 0.0;

			vendaCadastro.setValorPagar((vendaCadastro.getValorTotal())
					- (vendaCadastro.getValorTotal() * desconto) / 100);

			vendaCadastro.setDesconto(desconto);
			vendaCadastro.setAcrescimo(acrescimo);

			if (pagamento.getNumeroParcelas() == 0) {
				parcela.setValor(0.0);
				return;
			}
			parcela.setValor(pagamento.getValorPagamento()
					/ pagamento.getNumeroParcelas());

		} else if (tipoAcrescimo == 1 && valorDescontoAcrescimo != 0) {
			acrescimo = valorDescontoAcrescimo;
			desconto = 0.0;

			vendaCadastro.setValorPagar((vendaCadastro.getValorTotal())
					+ (vendaCadastro.getValorTotal() * acrescimo) / 100);

			vendaCadastro.setAcrescimo(acrescimo);
			vendaCadastro.setDesconto(desconto);

			if (pagamento.getNumeroParcelas() == 0) {
				parcela.setValor(0.0);
				return;
			}

			parcela.setValor(pagamento.getValorPagamento()
					/ pagamento.getNumeroParcelas());
			System.out.println("Passou no acrescimo"
					+ pagamento.getValorPagamento());
		}

	}

	/**
	 * Método responsavel por listar todos os produtos cadastrados
	 */
	public void listar() {
		JmeProdutoDAO dao = new JmeProdutoDAO();

		try {

			listaProdutos = new ArrayList<JmeProduto>();

			listaProdutos = dao.listar();

		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao listar Produtos!");
		}
	}

	// metodo responsavel por adicionar o produto na tabela de itens
	public void adicionarProduto(JmeProduto produto) {

		// porque -1, quando não achar nenhum item corresponde ao produto, pois
		// não achei nenhum produto.
		int posicaoEncontrada = -1;

		// posicaoEncontrada<0 que dizer que percorre o vetor até achar o
		// objeto, se achou para o for de executar
		for (int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++) {
			// vai capturar o item de acordo com a sua posição dentro da lista
			JmeItensVenda itemTemporario = listaItens.get(pos);

			// se o produto do item é igual a produto que veio como paramentro
			if (itemTemporario.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}
		}

		JmeItensVenda itens = new JmeItensVenda();
		itens.setProduto(produto);

		if (produto.getQuantEstoque() == 0) {
			JSFUtil.adicionarMensagemErro(produto.getDescricao() + " - "
					+ "Saldo indisponível.");

			// caso não foi encontrado nenhum produto na lista do item, adiciona
			// um
			// novo produto
		} else if (posicaoEncontrada < 0) {
			itens.setValorUnitario(produto.getValorUnitario());
			itens.setQuantidade(1);
			itens.setValorTotalItens(produto.getValorUnitario());
			listaItens.add(itens);

			// Soma os valores dos itens
			vendaCadastro.setValorTotal(vendaCadastro.getValorTotal()
					+ (produto.getValorUnitario()));

			vendaCadastro.setValorPagar(vendaCadastro.getValorTotal());

			pagamento.setValorPagamento(vendaCadastro.getValorTotal());

			parcela.setValor(0.0);

			System.out.println("parcela" + parcela.getValor());

			// responsavel por carregar os combobox da forma de pagamento
			this.carregarDadosVenda();
			vendaCadastro.setQuantidade(vendaCadastro.getQuantidade() + 1);
			// se achou um produto já na lista do item, soma a quantidade e
			// mutiplica o valor parcial
		} else {

			// vai pegar o item que foi encontrado no for.
			JmeItensVenda itemTemp = listaItens.get(posicaoEncontrada);
			System.out.println("achou produto igual");

			if (itemTemp.getQuantidade() == produto.getQuantEstoque()) {
				JSFUtil.adicionarMensagemErro("Impossivel adicionar, quantidade de estoque indisponivel");
				return;

			}
			itens.setQuantidade(itemTemp.getQuantidade() + 1);
			itens.setValorTotalItens(produto.getValorUnitario()
					* (itens.getQuantidade()));

			listaItens.set(posicaoEncontrada, itens);

			// Soma os valores dos itens
			vendaCadastro.setValorTotal(vendaCadastro.getValorTotal()
					+ (produto.getValorUnitario()));

			vendaCadastro.setValorPagar(vendaCadastro.getValorTotal());

			pagamento.setValorPagamento(vendaCadastro.getValorTotal());

			parcela.setValor(0.0);

			// responsavel por aumentar a quantidade na tabela itens de venda
			vendaCadastro.setQuantidade(vendaCadastro.getQuantidade() + 1);
			System.out.println(parcela.getValor());
			// responsalvel por carregar os combobox da forma de pagamento
			this.carregarDadosVenda();

		}
	}

	// metodo responsavel por excluir um item da tabela de item
	public void removerItem(JmeItensVenda item) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++) {
			JmeItensVenda itemTemp = listaItens.get(pos);

			if (itemTemp.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = pos;
			}
		}
		if (posicaoEncontrada > -1) {

			listaItens.remove(posicaoEncontrada);
			vendaCadastro.setValorTotal(vendaCadastro.getValorTotal()
					- item.getValorTotalItens());

			vendaCadastro.setValorPagar(vendaCadastro.getValorTotal());
			// Responsavel por diminuir a quantidade que aparece na tabela de
			// itens
			vendaCadastro.setQuantidade(vendaCadastro.getQuantidade()
					- item.getQuantidade());
		}

	}

	// METODO RESPONSAVEL POR EDITAR UM ITEM
	public void editarItem(JmeItensVenda item) {

		if (item.getQuantidade() > item.getProduto().getQuantEstoque()) {
			System.out.println("passou quantidade");
			item.setQuantidade(1);
			JSFUtil.adicionarMensagemErro("Saldo indisponivel");
			return;
		}

		// // porque -1, quando não achar nenhum item corresponde ao produto,
		// pois não achei nenhum produto.
		int posicaoEncontrada = 0;

		// posicaoEncontrada<0 que dizer que percorre o vetor até achar o
		// objeto, se achou para o for de executar
		for (int pos = 0; pos < listaItens.size(); pos++) {
			// vai capturar o item de acordo com a sua posição dentro do vetor
			JmeItensVenda itemTemporario = listaItens.get(pos);

			// se o produto do item é igual a produto que veio como paramentro
			if (itemTemporario.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = pos;

				System.out.println("passou no if");

			}
		}

		JmeItensVenda editar = new JmeItensVenda();

		JmeItensVenda itemTemp = listaItens.get(posicaoEncontrada);

		// atribui o obejto produto
		editar.setProduto(item.getProduto());
		// pega a nova quantidade digitada
		editar.setQuantidade(itemTemp.getQuantidade());

		editar.setValorUnitario(itemTemp.getValorUnitario());

		editar.setValorTotalItens(itemTemp.getValorUnitario()
				* (editar.getQuantidade()));

		// set, os novos valores na listaItens
		listaItens.set(posicaoEncontrada, editar);

		// Variavel responsavel por receber os valores totalItens, (com a
		// somatória de todos os produtos dentro da lista)
		double valor = 0.0;

		// percorre toda a lista de itens, pega os valoresTotal de cada itens e
		// depois armazena na variavel valor
		for (JmeItensVenda valorTotalItens : listaItens) {

			valor = valor + valorTotalItens.getValorTotalItens();

		}

		vendaCadastro.setValorPagar(valor);

		JSFUtil.adicionarMensagemSucesso("Quantidade Alterada: "
				+ editar.getQuantidade());
		// Valor Total de Itens
		vendaCadastro.setValorTotal(valor);
		System.out.println("ValorTotal" + vendaCadastro.getValorTotal());
	}

	// metodo responsalvel para carregar o combobox do cliente e do funcionario.
	public void carregarDadosVenda() {
		if (comboCliente == null || comboPagamento == null
				|| comboTipoFormaPagamento == null) {

			vendaCadastro.setDataVenda(new Date());

			JmeClienteDAO clienteDAO = new JmeClienteDAO();
			comboCliente = clienteDAO.listar();

			JmeFormaPagamentoDAO dao = new JmeFormaPagamentoDAO();

			comboPagamento = dao.listar();

			System.out.println(comboPagamento);
			JmeFuncionarioDAO funcionarioDAO = new JmeFuncionarioDAO();

			funcionario = funcionarioDAO.buscarPorCodigo(autenticacaoBean
					.getFuncionarioLogado().getIdFuncionario());

			JmeTipoFormaPagamentoDAO tipoFormaPagamentoDAO = new JmeTipoFormaPagamentoDAO();

			comboTipoFormaPagamento = tipoFormaPagamentoDAO.listar();
		}

	}

	// Metodo responsalvel por registrar a venda e inclusão dos itens
	public void salvar() {
		try {

			vendaCadastro.setFuncionario(funcionario);
			if (vendaCadastro.getDesconto() == null
					&& vendaCadastro.getAcrescimo() == null) {
				vendaCadastro.setAcrescimo(0.0);
				vendaCadastro.setDesconto(0.0);
			}

			// Se for a vista, pago. Se não a venda está pendente
			if (vendaCadastro.getFormaPagamento().getIdFormaPagamento() == 1
					&& pagamento.getValorPagamento() == vendaCadastro
							.getValorPagar()
					|| valorRecebido >= vendaCadastro.getValorPagar()) {
				vendaCadastro.setSituacao("Pago");

			} else {
				vendaCadastro.setSituacao("Pendente");
			}

			JmeRegistroVendasDAO vendaDAO = new JmeRegistroVendasDAO();
			// Pegar o codigo da venda
			Integer codigoVenda = vendaDAO.salvar(vendaCadastro);

			// vendaFK recebe a chave primaria da ultima venda
			JmeRegistroVendas vendaFK = vendaDAO.buscarPorCodigo(codigoVenda);

			for (JmeItensVenda item : listaItens) {
				item.setRegistroVendas(vendaFK);
				JmeItensVendaDAO itemDAO = new JmeItensVendaDAO();
				itemDAO.salvar(item);

				System.out.println("Produtos"
						+ item.getProduto().getDescricao());
			}

			JmePagamentoDAO dao = new JmePagamentoDAO();
			pagamento.setRegistroVendas(vendaFK);

			pagamento.setDataPagamento(new Date());

			if (vendaCadastro.getFormaPagamento().getIdFormaPagamento() == 1) {

				pagamento.setValorPagamento(vendaCadastro.getValorPagar());

				dao.salvar(pagamento);
			} else if (vendaCadastro.getFormaPagamento().getIdFormaPagamento() == 2
					&& valorRecebido == 0) {
				JSFUtil.adicionarMensagemSucesso("Venda Realizada com sucesso"
						+ "\n" + "Pagamento pendente.");
			} else if (vendaCadastro.getFormaPagamento().getIdFormaPagamento() == 2
					&& valorRecebido > 0) {

				pagamento.setValorPagamento(valorRecebido);
				dao.salvar(pagamento);
			}

			int cont = 0;
			JmeParcelaDAO daoParcela = new JmeParcelaDAO();
			if (pagamento.getNumeroParcelas() <= 1) {

			} else {
				int quantidadeMeses = 30;
				while (cont < pagamento.getNumeroParcelas()) {

					parcela.setRegistroVenda(vendaFK);
					parcela.setValor(parcela.getValor());

					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.DAY_OF_MONTH,
							calendar.get(Calendar.DAY_OF_MONTH)
									+ quantidadeMeses);

					Date data = calendar.getTime();
					parcela.setDataVencimento(data);
					parcela.setSituacao("pendente");
					daoParcela.salvar(parcela);

					cont++;

					quantidadeMeses = quantidadeMeses + 30;
					System.out.println(cont);
					System.out.println("Meses" + quantidadeMeses);

				}
			}

			// Serve para limpar a vendaCadastro
			vendaCadastro = new JmeRegistroVendas();

			// serve para zerar o valor total da venda
			vendaCadastro.setValorTotal(0.00);

			// Serve para limpar a tabela de itens
			listaItens = new ArrayList<JmeItensVenda>();

		} catch (RuntimeException ex) {
			JSFUtil.adicionarMensagemErro("Erro tentar salvar a venda");
		}
	}

	// Vai receber os valores setados pelos os combox

	public void confirmacao() {

		JmeClienteDAO daoCliente = new JmeClienteDAO();
		JmeFormaPagamentoDAO daoFormaPagamento = new JmeFormaPagamentoDAO();
		JmeTipoFormaPagamentoDAO daoTipoFormaPagamento = new JmeTipoFormaPagamentoDAO();

		// se o pagamento for diferente de nulo, ele faz a consulta no banco
		// referente ao pagamento pega a quantidade de parcela maxima de acordo
		// com o pagamento(a vista ou prazo)
		// e atribui o valor na variavel quantidadeParcela
		if (vendaCadastro.getFormaPagamento().getIdFormaPagamento() != null) {

			// Pesquisa a forma de pagamento a vista ou a prazo
			JmeFormaPagamento formaPagamento = daoFormaPagamento
					.buscarPorCodigo(vendaCadastro.getFormaPagamento()
							.getIdFormaPagamento());

			// atribui o valor quantidade
			quantidadeParcela = formaPagamento.getQuantidadeParcela();
			valorDesconto = formaPagamento.getDesconto();
			vendaCadastro.getFormaPagamento().setDescricao(
					formaPagamento.getDescricao());
			System.out.println("passou" + quantidadeParcela);

			if (vendaCadastro.getFormaPagamento().getIdFormaPagamento() == 2) {
				abilitarPagamento = true;
			} else {
				abilitarPagamento = false;
			}

		}
		if (vendaCadastro.getCliente().getIdCliente() != null) {
			// Pesquisa o cliente
			JmeCliente cliente = daoCliente.buscarPorCodigo(vendaCadastro
					.getCliente().getIdCliente());
			System.out.println(cliente.getNome());

			// atribui o nome do cliente de acordo na variavel
			vendaCadastro.getCliente().setNome(cliente.getNome());
			vendaCadastro.getCliente().setCpf(cliente.getCpf());
		}

		if (vendaCadastro.getFuncionario().getIdFuncionario() != null) {
			JmeFuncionarioDAO funcionarioDAO = new JmeFuncionarioDAO();
			funcionario = funcionarioDAO.buscarPorCodigo(autenticacaoBean
					.getFuncionarioLogado().getIdFuncionario());

			System.out.println(funcionario.getNome());
			// atribui o nome do funcionario na variavel
			vendaCadastro.getFuncionario().setNome(funcionario.getNome());
		}

		if (pagamento.getTipoFormaPagamento().getIdTipoFormaPagamento() != null) {
			JmeTipoFormaPagamento tipoFormaPagamento = daoTipoFormaPagamento
					.buscarPorCodigo(pagamento.getTipoFormaPagamento()
							.getIdTipoFormaPagamento());

			pagamento.getTipoFormaPagamento().setDescricao(
					tipoFormaPagamento.getDescricao());

		}

	}

	public void cancelarVenda() {
		JSFUtil.adicionarMensagemErro("Venda Cancelada");
	}

	public void calcularParcela() {

		if (pagamento.getNumeroParcelas() <= 1) {
			parcela.setValor(0.0);
			return;
		}
		parcela.setValor(vendaCadastro.getValorPagar()
				/ pagamento.getNumeroParcelas());

		System.out.println(parcela.getValor());
	}

	public String handleFlow(FlowEvent event) {

		String currentStepId = event.getOldStep();
		String stepToGo = event.getNewStep();

		if (listaItens.isEmpty()) {
			JSFUtil.adicionarMensagemErro("Selecione um produto!");
			RequestContext.getCurrentInstance().update("msgGlobal");
			return currentStepId;
		} else {
			return stepToGo;
		}
	}

	public void calcularTroco() {
		if (valorRecebido == vendaCadastro.getValorPagar()) {
			valorVoltar = 0.0;
		} else if (valorRecebido < vendaCadastro.getValorPagar()) {
			valorVoltar = 0.0;
		} else {
			valorVoltar = valorRecebido - vendaCadastro.getValorPagar();
		}
	}

	public void buscar() {
		try {
			JmeRegistroVendasDAO dao = new JmeRegistroVendasDAO();
			listaVendasFiltradas = dao.buscar(filtro);
		} catch (RuntimeException ex) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar buscar uma venda."
					+ ex.getMessage());
		}
	}

}
