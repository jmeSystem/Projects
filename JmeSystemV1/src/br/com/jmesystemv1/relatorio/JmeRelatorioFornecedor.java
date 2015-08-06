package br.com.jmesystemv1.relatorio;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

@ManagedBean(name="MBRelatorioFornecedor")
@ViewScoped
public class JmeRelatorioFornecedor extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	/** Essa Lista foi criada para abastecer o combo cidade na tela, com todas as cidades disponiveis no banco*/
	private List<JmeCidade> cidadeCombo;
	/** Passa a chave primaria da cidade escolhida no combo pelo usuário, para realizar o filtro do relatorio por cidade*/
	private Integer idCidade;
	/** dataIni e dataFin serve para gerar um relatorio por datas escolhidas pelo usuario, data de inicio e a data final */
	private Date dataIni;
	private Date dataFin;
	private String tipoRelat;

	
	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
	
	public List<JmeCidade> getCidadeCombo() {
		return cidadeCombo;
	}

	public void setCidadeCombo(List<JmeCidade> cidadeCombo) {
		this.cidadeCombo = cidadeCombo;
	}
	
	public Date getDataIni() {
		return dataIni;
	}

	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}

	public Date getDataFin() {
		return dataFin;
	}

	public void setDataFin(Date dataFin) {
		this.dataFin = dataFin;
	}
	
	public String getTipoRelat() {
		return tipoRelat;
	}

	public void setTipoRelat(String tipoRelat) {
		this.tipoRelat = tipoRelat;
	}

	/**Carrega o combo com todas as cidades disponiveis no banco de dados
	 * para realizar o filtro do relatorio*/
	public void prepararNovo(){
		
		try{
		JmeCidadeDAO cidadeDAO = new JmeCidadeDAO();
		cidadeCombo = cidadeDAO.listar();
		
		System.out.println("Hey, preparando Novo !!!");
		}catch(Exception ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void validarParametro() throws Exception{
//		String dtInicial = "1900/12/31";
//		String dtFinal = "2100/12/31";
		
		Date data = new Date();

		
		
		/** Se o usuario escolher gerar o relatorio sem escolher data inicial ou data final, então
		 * será gerado o relatorio a partir da data atual do sistema */
		if (dataIni == null && dataFin == null) {
			
//			SimpleDateFormat formatterDtInicial = new SimpleDateFormat("yyyy/MM/dd");
//			SimpleDateFormat formatterDtFinal = new SimpleDateFormat("yyyy/MM/dd");

			dataIni = data;
			dataFin = data;
			
			this.relatorioParametro();
		}
		
		/** Se o usuario for realizar um relatorio por data e ele esquece de preencher um campo, por exmeplo
		 * Data Inicial ou Data Final, então é mostrada uma mensagem de erro. Dizendo que é necessário
		 * que os dois campos estejam totalmente preenchidos para realizar um relatorio por data.
		 * Se os campos forem preenchidos com as datas, então é gerado o relatorio apartir da data escolhida
		 * pelo o usuario*/
		if ((dataIni == null && dataFin != null) || (dataIni != null && dataFin == null)) {
			JSFUtil.adicionarMensagemErro(
					"Para gerar relatorio por data, todos os campos devem estar preenchidos");
			
			
		}else{
			this.relatorioParametro();
		}
		
		
		
		
		
	}
	
	
	/** Metodo Gera Relatorio com parametro*/
	public void relatorioParametro(){
		
		/**Chave(nome da variavel criada no iReport) e o 
		 * valor(Variavel ou valor fixo do parametro do relatorio ).*/
		HashMap<String, Object> parametro = new HashMap<String, Object>();
		
//		String dtInicial = "31/12/1900" ; 

//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//		dataIni = new java.sql.Date(((java.util.Date)formatter.parse(dtInicial)).getTime());

//		dataIni = formatter.parse(dtInicial);
		
		try {
			
			/** Define o tipo do relatorio, por cidade ou 
			 * todos os fornecedores*/
			if(idCidade != 0){
				tipoRelat = "Por Cidade";
			}else{
				tipoRelat = "Todos os Fornecedores";
			}
			

			/**
			 * Para o Objeto do tipo HashMap denominado "parametro", 
			 * estamos passando "codigo" que é o parametro criado no iReport, 
			 * e o "idCidade" que é o código da cidade que será selecionada para realizar o filtro*/
			
			//Parametros para gerar relatorio por filtro
			parametro.put("codigo", idCidade);
			parametro.put("dataInicial", dataIni);
			parametro.put("dataFinal", dataFin);
			parametro.put("tipoRelatorio", tipoRelat);
			
			System.out.println(idCidade);
			System.out.println(dataIni);
			System.out.println(dataFin);
			
			
			
			InputStream caminho = FacesContext.getCurrentInstance().
					getExternalContext().getResourceAsStream("/relatorios/JmeSystemRelatorioFornecedor.jasper");

			//JasperReport report = JasperCompileManager.compileReport(caminho);

			JasperPrint print = JasperFillManager.fillReport(caminho, parametro,
					ConexaoFactory.conectar());
			
			

			byte[] bytes = JasperExportManager.exportReportToPdf(print);
			if (bytes != null) {
				 HttpServletResponse response = JSFUtil.getResponse();
				 response.setContentType("application/pdf");
				 /** Nome do Relatório,*/
				 response.setHeader("Content-disposition","attachment;filename=RelatorioFornecedor.pdf");
				 response.setContentLength(bytes.length);
				 response.getOutputStream().write(bytes, 0, bytes.length);
				 response.getOutputStream().flush();
				 response.flushBuffer();
				 JSFUtil.responseComplete();
				 JasperExportManager.exportReportToPdf(print);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
