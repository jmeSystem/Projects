package br.com.jmesystemv1.relatorio;

import java.io.InputStream;
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
import br.com.jmesystemv1.dao.JmeFornecedorDAO;
import br.com.jmesystemv1.domain.JmeFornecedor;
import br.com.jmesystemv1.factory.ConexaoFactory;
import br.com.jmesystemv1.util.JSFUtil;

@ManagedBean(name = "MBProdutoRelatorio")
@ViewScoped
public class relatorioProduto extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Date dataIni;
	Date dataFim;
	Integer idFornecedor;
	String tipoFiltro;

	List<JmeFornecedor> comboFornecedor;

	/* ===========================GET-SET============================= */

	/**
	 * Método resposavel por carregar objetos necessários para a abertura da
	 * tela
	 */
	public void prepararNovo() {
		JmeFornecedorDAO dao = new JmeFornecedorDAO();
		comboFornecedor = dao.listar();

	}

	public String getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public List<JmeFornecedor> getComboFornecedor() {
		return comboFornecedor;
	}

	public void setComboFornecedor(List<JmeFornecedor> comboFornecedor) {
		this.comboFornecedor = comboFornecedor;
	}

	public Date getDataIni() {
		return dataIni;
	}

	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	/**
	 * Método responsavel por chamar o relatorio
	 */
	public void gerarRelatorio() {
		System.out.println("passou no relatorio com paramentro");

		// responsalvel para setar o valor do paramentro
		Map<String, Object> hm = new HashMap<String, Object>();

		/* verrificando os tipos de filtros usados para inserir no relatório */
		if (idFornecedor != 0) {
			tipoFiltro = "Fornecedor Selecionado";
		} else {
			tipoFiltro = "Todos os Fornecedores";
		}
		try {
			hm.put("P_DATA_INI", dataIni);
			hm.put("P_DATA_FIM", dataFim);
			hm.put("P_COD_FOR", idFornecedor);
			hm.put("P_TIPO_FILTRO", tipoFiltro);

			System.out.println(dataIni);
			System.out.println(dataFim);
			System.out.println(idFornecedor);

			InputStream caminho = FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.getResourceAsStream("/relatorios/relatorioProdutos.jasper");

			JasperPrint print = JasperFillManager.fillReport(caminho, hm,
					ConexaoFactory.conectar());

			byte[] bytes = JasperExportManager.exportReportToPdf(print);

			if (bytes != null) {
				HttpServletResponse response = JSFUtil.getResponse();
				response.setContentType("application/pdf");
				// Nome do relatório que será gerado
				response.setHeader("Content-disposition",
						"attachment;filename=relatorioProdutos.pdf");
				response.setContentLength(bytes.length);
				response.getOutputStream().write(bytes, 0, bytes.length);
				response.getOutputStream().flush();
				response.flushBuffer();
				JSFUtil.responseComplete();
				// Abre o relatório direto, mais esse comando é usando para
				// sistema Desktop
				// JasperViewer.viewReport(print, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
