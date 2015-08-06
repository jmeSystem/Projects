package br.com.jmesystemv1.relatorio;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.com.jmesystemv1.dao.JmeFuncionarioDAO;
import br.com.jmesystemv1.dao.JmeProdutoDAO;
import br.com.jmesystemv1.domain.JmeFuncionario;
import br.com.jmesystemv1.domain.JmeProduto;
import br.com.jmesystemv1.factory.ConexaoFactory;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBRelatorioVendas")
@ViewScoped
public class JmeRelatorioVendas {

	private List<JmeProduto> comboProduto;
	private Integer idFuncionario;
	private Integer idProduto;

	private List<JmeFuncionario> comboFuncionario;
	
	private boolean abilitarCombo = false;
	private Integer tipoRelatorio;
	
	// Parametros que contém no meu relatório
	Date dataInicial;
	Date dataFinal;

	// Variavel para atruibuir valores padrão na data.
	String dataInicialPadrao = "1992-09-13";
	String dataFinalPadrao = "2100-01-01";

	
	
	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(Integer tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public boolean isAbilitarCombo() {
		return abilitarCombo;
	}

	public void setAbilitarCombo(boolean abilitarCombo) {
		this.abilitarCombo = abilitarCombo;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<JmeFuncionario> getComboFuncionario() {
		return comboFuncionario;
	}

	public void setComboFuncionario(List<JmeFuncionario> comboFuncionario) {
		this.comboFuncionario = comboFuncionario;
	}

	

	public List<JmeProduto> getComboProduto() {
		return comboProduto;
	}

	public void setComboProduto(List<JmeProduto> comboProduto) {
		this.comboProduto = comboProduto;
	}

	

	public void carregarListagem() {
		JmeProdutoDAO daoProduto = new JmeProdutoDAO();
		JmeFuncionarioDAO daoFuncionario = new JmeFuncionarioDAO();

		comboFuncionario = daoFuncionario.listar();
		comboProduto = daoProduto.listar();
	}

	// Metodo responsal para validação do relatorio, apartir dos campos
	// preenchidos na tela
	public void validacaoRelatorio() throws Exception {

		// verifica se a data final é vazio, se for exiba a menssagem
		if (dataInicial != null && (dataFinal == null)) {
			JSFUtil.adicionarMensagemErro("Por favor preencher a data Final");

			// verifica se a data inicial é vazio, se for exiba a messagem
		} else if (dataInicial == null && dataFinal != null) {
			JSFUtil.adicionarMensagemErro("Por favor preencher a data Inicial");

			// verifica se a data inicial for vazio e a data final tbém, seta
			// datas padrão para poder gerar o relatório, chama o relatorio
		} else if (dataInicial == null && dataFinal == null && idProduto == 0
				&& idFuncionario == 0) {
			// transformar a variavel String em Date
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
			// atribui as datas padrão
			dataInicial = formatter1.parse(dataInicialPadrao);
			dataFinal = formatter2.parse(dataFinalPadrao);

			// Chama o relatório
			this.gerarRelatorioNormal();
		} else if (dataInicial != null && dataFinal != null
				&& idProduto != 0
				&& idFuncionario == 0) {

			// listar Todos os funcionário que realizou a venda
			idFuncionario=-1;
			this.gerarRelatorioNormal();
		} else if (dataInicial == null && dataFinal == null
				&& idProduto != 0
				) {

			// transformar a variavel String em Date
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
			// atribui as datas padrão
			dataInicial = formatter1.parse(dataInicialPadrao);
			dataFinal = formatter2.parse(dataFinalPadrao);
			// listar Todos os funcionário que realizou a venda
			idFuncionario=-1;
			this.gerarRelatorioNormal();
		
		}else if(dataInicial != null && dataFinal != null
				&& idProduto == 0
				&& idFuncionario == 0){
			this.gerarRelatorioNormal();
		}
		else if(dataInicial != null && dataFinal != null
				&& idProduto == 0
				&& idFuncionario != 0){
			this.gerarRelatorioNormal();
		}

	}

	public void abilitarCampo() {
		if (idProduto == 0) {
			abilitarCombo = false;
			System.out.println("Passou no 0");
		} else {
			abilitarCombo = true;
			System.out.println("Passou no diferente de 0");
		}
	}

	// metodo responsavel para gerar relatório por cidade, data ou listagem de
	// todos
	

	public void gerarRelatorioNormal() {

		String caminhoRelatorio = null;

		if (tipoRelatorio == 1) {
			caminhoRelatorio = "JmeVendas.jasper";
		} else if (tipoRelatorio == 2) {
			caminhoRelatorio = "jmeVendasGraficoBarras.jasper";
		}else if (tipoRelatorio ==3){
			caminhoRelatorio = "jmeVendasGraficoPizza.jasper";
		}
		System.out.print("passou no relatorio com paramentro");

		// responsalvel para setar o valor do paramentro
		Map<String, Object> hm = new HashMap<String, Object>();

		try {
			//Parametros do relatorio
			if(idProduto!=0){
				hm.put("IdFun", -1);
				hm.put("IdProduto", idProduto);
				hm.put("dtInicial", dataInicial);
				hm.put("dtFinal", dataFinal);
			}else{
			hm.put("IdFun", idFuncionario);
			hm.put("IdProduto", idProduto);
			hm.put("dtInicial", dataInicial);
			hm.put("dtFinal", dataFinal);
			}
			// Filtro - Funcionário e Produto
			JmeFuncionarioDAO daoFuncionario = new JmeFuncionarioDAO();
			JmeFuncionario funcionarioFiltro = new JmeFuncionario();
			JmeProdutoDAO daoProduto = new JmeProdutoDAO();
			JmeProduto produtoFiltro = new JmeProduto();

			produtoFiltro = daoProduto.buscarPorCodigo(idProduto);
			funcionarioFiltro = daoFuncionario.buscarPorCodigo(idFuncionario);
			
			if(funcionarioFiltro==null && produtoFiltro==null){
				// Parametros dos filtros que foram feitos no relatórios
				hm.put("filtroFuncionario", "Todos");
				hm.put("filtroProduto", "Todos");
				hm.put("filtroDataInicial", dataInicial);
				hm.put("filtroDataFinal", dataFinal);
			}else if (produtoFiltro==null && funcionarioFiltro.getIdFuncionario()!=null){
				hm.put("filtroProduto", "Todos");
				hm.put("filtroFuncionario", funcionarioFiltro.getNome());
				hm.put("filtroDataInicial", dataInicial);
				hm.put("filtroDataFinal", dataFinal);
			}else if (produtoFiltro!=null && funcionarioFiltro==null){
				hm.put("filtroProduto", produtoFiltro.getDescricao());
				hm.put("filtroFuncionario", "Todos");
				hm.put("filtroDataInicial", dataInicial);
				hm.put("filtroDataFinal", dataFinal);
			}
			

			// Diretório do caminho, modifiquei para .jasper a extenção do meu
			// caminho, pois tinha que compilava toda vez o relatório
			InputStream caminho = FacesContext.getCurrentInstance()
					.getExternalContext()
					.getResourceAsStream("/relatorios/" + caminhoRelatorio);

			// //compilava toda vez o relatório quando fosse chamado, por isso
			// que tirei
			// JasperReport report =
			// JasperCompileManager.compileReport(caminho);

			// coloquei meu caminho como paramentro
			JasperPrint print = JasperFillManager.fillReport(caminho, hm,
					ConexaoFactory.conectar());
			// Responsalvel para exportar o arquivo em pdf
			byte[] bytes = JasperExportManager.exportReportToPdf(print);

			if (bytes != null) {
				HttpServletResponse response = JSFUtil.getResponse();
				response.setContentType("application/pdf");
				// Nome do relatório que será gerado
				response.setHeader("Content-disposition",
						"attachment;filename=RelatorioVendas.pdf");
				response.setContentLength(bytes.length);
				response.getOutputStream().write(bytes, 0, bytes.length);
				response.getOutputStream().flush();
				response.flushBuffer();
				JSFUtil.responseComplete();
				// Abre o relatório direto, mais esse comando é usando para
				// sistema Desktop
				// JasperViewer.viewReport(print, false);
			}

			tipoRelatorio = 0;
			idFuncionario=0;
			idProduto=0;
			
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao chamar o relatório");
			e.printStackTrace();
		}

	}

}
