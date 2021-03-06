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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.com.jmesystemv1.dao.JmeCidadeDAO;
import br.com.jmesystemv1.domain.JmeCidade;
import br.com.jmesystemv1.factory.ConexaoFactory;
import br.com.jmesystemv1.util.JSFUtil;
@ManagedBean(name = "MBClienteRelatorio")
@ViewScoped
public class relatorioCliente extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// Parametros que cont�m no meu relat�rio
	Date dataInicial;
	Date dataFinal;
	Integer id;
	String tipoRelatorio;
	// Variavel para atruibuir valores padr�o na data.
	String dataInicialPadrao="1992-09-13";
	String dataFinalPadrao="2100-01-01";
	
	// vou usar para listar as cidades no combobox
		private List<JmeCidade> comboCidade;
	

	// Gets e Sets
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

	
	public List<JmeCidade> getComboCidade() {
		return comboCidade;
	}

	public void setComboCidade(List<JmeCidade> comboCidade) {
		this.comboCidade = comboCidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
		
	
	// Metodo responsal para valida��o do relatorio, apartir dos campos preenchidos na tela
	public void validacaoRelatorio() throws Exception {
		
		// verifica se a data final � vazio, se for exiba a menssagem
		if(dataInicial!=null && (dataFinal==null)){
			JSFUtil.adicionarMensagemErro("Por favor preencher a data Final");
		
			//verifica se a data inicial � vazio, se for exiba a messagem
		}else if (dataInicial==null && dataFinal!=null){
			JSFUtil.adicionarMensagemErro("Por favor preencher a data Inicial");
		
			// verifica se a data inicial for vazio e a data final tb�m, seta datas padr�o para poder gerar o relat�rio, chama o relatorio
		}else if(dataInicial==null && dataFinal==null && id==0){
			// transformar a variavel String em Date
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
			// atribui as datas padr�o
			dataInicial= formatter1.parse(dataInicialPadrao);
			dataFinal=formatter2.parse(dataFinalPadrao);
			tipoRelatorio="Todos";
			// Chama o relat�rio
			this.gerarRelatorioParametro();
		// E se todos os campos for preenchidos, chama o relat�rio
			
		}else if (dataInicial==null && dataFinal==null && id!=0) {
			// transformar a variavel String em Date
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
						// atribui as datas padr�o
						dataInicial= formatter1.parse(dataInicialPadrao);
						dataFinal=formatter2.parse(dataFinalPadrao);
						tipoRelatorio="Por Cidade";
						this.gerarRelatorioParametro();
			}else if (dataInicial!=null && dataFinal!=null && id==0){
				tipoRelatorio="Todos";
				this.gerarRelatorioParametro();
			}else{
				tipoRelatorio="Por Cidade";
				this.gerarRelatorioParametro();
			}
		
	}
	
	// preparar novo funciona para carregar o combobox da pagina relatorioCliente
	public void prepararNovo(){
	try{
		
		JmeCidadeDAO dao = new JmeCidadeDAO();
		comboCidade =  dao.listar();
		System.out.print("passou preparar Novo");
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}	
	
	// metodo responsavel para gerarRelat�rio de clientes sem paramentros.
//	public void gerarRelatorioComum() {
//		System.out.print("passou no gerarRelatorioComum");
//		try {
//			
//			// mudei o caminho para Cliente.jasper, pois ser� responsavel de pegar o relat�rio ja compilado (obs: tirei do pacote o relatorio Cliente.jrxml)
//			InputStream caminho = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/relatorios/Cliente.jasper");
////			toda vez que chama o relat�rio estava compilando, tirei pois passei o caminho do relat�rio no JasperPrint
////			JasperReport report = JasperCompileManager.compileReport(caminho);
//
//			// aonde est� null, � aonde fica os parametros
//			JasperPrint print = JasperFillManager.fillReport(caminho, null,
//					ConexaoFactory.conectar());
//
//			
//			byte[] bytes = JasperExportManager.exportReportToPdf(print);
//			if (bytes != null) {
//				HttpServletResponse response = JSFUtil.getResponse();
//				response.setContentType("application/pdf");
//				// Nome do relat�rio que ser� gerado
//				response.setHeader("Content-disposition","attachment;filename=ListagemClientes.pdf");
//				response.setContentLength(bytes.length);
//				response.getOutputStream().write(bytes, 0, bytes.length);
//				response.getOutputStream().flush();
//				response.flushBuffer();
//				JSFUtil.responseComplete();		
//				JasperExportManager.exportReportToPdf(print);
//				//abre o relatorio direito
//				//JasperViewer.viewReport(print, false);
//				this.prepararNovo();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	// metodo responsavel para gerar relat�rio por cidade, data ou listagem de todos
	public void gerarRelatorioParametro() {
		
		
		System.out.print("passou no relatorio com paramentro");
		
		// responsalvel para setar o valor do paramentro
		Map<String, Object> hm = new HashMap<String, Object>();

		try {	
			
			//CidId, � O NOME DA VARIAVEL QUE PASSEI COMO PARAMETRO NO IREPORT.
			hm.put("CidId", id);
			hm.put("dtInicial", dataInicial);
			hm.put("dtFinal", dataFinal);
			hm.put("tipo", tipoRelatorio);
			
			//Diret�rio do caminho, modifiquei para .jasper a exten��o do meu caminho, pois tinha que compilava toda vez o relat�rio
			InputStream caminho = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/relatorios/Cliente.jasper");
			
			
//			//compilava toda vez o relat�rio quando fosse chamado, por isso que tirei
//			JasperReport report = JasperCompileManager.compileReport(caminho);
			
			// coloquei meu caminho como paramentro
			JasperPrint print = JasperFillManager.fillReport(caminho, hm,
					ConexaoFactory.conectar());
			// Responsalvel para exportar o arquivo em pdf
			byte[] bytes = JasperExportManager.exportReportToPdf(print);
			
			if (bytes != null) {
				 HttpServletResponse response = JSFUtil.getResponse();
				 response.setContentType("application/pdf");
				 // Nome do relat�rio que ser� gerado
				 response.setHeader("Content-disposition","attachment;filename=RelatorioCliente.pdf");
				 response.setContentLength(bytes.length);
				 response.getOutputStream().write(bytes, 0, bytes.length);
				 response.getOutputStream().flush();
				 response.flushBuffer();
				 JSFUtil.responseComplete();
//				 Abre o relat�rio direto, mais esse comando � usando para sistema Desktop
//				 JasperViewer.viewReport(print, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	}

