package br.com.jmesystemv1.relatorio;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.com.jmesystemv1.factory.ConexaoFactory;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBRelatorioVendasAnalitico")
@ViewScoped
public class JmeRelatorioVendasAnalitico {

	private Integer tipoRelatorio;
	private Integer agrupar=0;
	// Variavel para atruibuir valores padrão na data.
	String dataInicialPadrao = "1992-09-13";
	String dataFinalPadrao = "2100-01-01";

	// Parametros que contém no meu relatório
	Date dataInicial;
	Date dataFinal;

	public Integer getAgrupar() {
		return agrupar;
	}

	public void setAgrupar(Integer agrupar) {
		this.agrupar = agrupar;
	}

	public Integer getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(Integer tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
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

	// Metodo responsal para validação do relatorio, apartir dos campos
	// preenchidos na tela
	public void validacaoRelatorio() throws Exception {

		// verifica se a data final é vazio, se for exiba a menssagem
		if (dataInicial != null && (dataFinal == null)) {
			JSFUtil.adicionarMensagemErro("Por favor preencher a data Final");

			// verifica se a data inicial é vazio, se for exiba a messagem
		} else if (dataInicial == null && dataFinal != null) {
			JSFUtil.adicionarMensagemErro("Por favor preencher a data Inicial");

		} else if (agrupar != 0 && dataInicial == null && dataFinal == null) {

			// transformar a variavel String em Date
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
			// atribui as datas padrão
			dataInicial = formatter1.parse(dataInicialPadrao);
			dataFinal = formatter2.parse(dataFinalPadrao);
			this.gerarRelatorioNormal();
		}else if (agrupar!=0 && dataInicial!=null && dataFinal!=null){
			this.gerarRelatorioNormal();
		}else if(agrupar==0){
			JSFUtil.adicionarMensagemAdvertencia("Por favor selecionar tipo de agrupamento");
		}

	}
	
	public void gerarRelatorioNormal() {

		String caminhoRelatorio = null;

		if (tipoRelatorio == 1 && agrupar==1) {
			caminhoRelatorio = "jmeVendasPorMesNormal.jasper";
		} else if (tipoRelatorio == 2 && agrupar==1) {
			caminhoRelatorio = "jmeVendaPorMesBarras.jasper";
		}else if (tipoRelatorio ==3 && agrupar==1){
			caminhoRelatorio = "jmeVendasPorMesPizza.jasper";
		
		}else if (tipoRelatorio == 1 && agrupar==2) {
			caminhoRelatorio = "jmeVendasAnualNormal.jasper";
		} else if (tipoRelatorio == 2 && agrupar==2) {
			caminhoRelatorio = "jmeVendasAnualBarras.jasper";
		}else if (tipoRelatorio ==3 && agrupar==2){
			caminhoRelatorio = "jmeVendasAnualPizza.jasper";
		}
		System.out.print("passou no relatorio com paramentro");

		// responsalvel para setar o valor do paramentro
		Map<String, Object> hm = new HashMap<String, Object>();

		try {
			
			hm.put("dataInicial", dataInicial);
			hm.put("dataFinal", dataFinal);
		
			

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
			
			
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao chamar o relatório");
			e.printStackTrace();
		}

	}
}
