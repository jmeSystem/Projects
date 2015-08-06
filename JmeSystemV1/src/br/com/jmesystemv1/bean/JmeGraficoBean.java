//package br.com.jmesystemv1.bean;
//
//import java.util.List;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//
//import org.primefaces.component.chart.bar.BarChart;
//
//import br.com.jmesystemv1.dao.JmeProdutoDAO;
//import br.com.jmesystemv1.domain.JmeProduto;
//import br.com.jmesystemv1.util.JSFUtil;
//
//@ViewScoped
//@ManagedBean(name="MBGrafico")
//public class JmeGraficoBean {
//	
//	private List<JmeProduto> listagemProduto;
//	private BarChart barChart;
//	
//	
//	public BarChart getBarChart() {
//		return barChart;
//	}
//
//	public void setBarChart(BarChart barChart) {
//		this.barChart = barChart;
//	}
//
//	public List<JmeProduto> getListagemProduto() {
//		return listagemProduto;
//	}
//
//	public void setListagemProduto(List<JmeProduto> listagemProduto) {
//		this.listagemProduto = listagemProduto;
//	}
//	
//	
//	public void Listagem() {
//		try {
//			JmeProdutoDAO daoProduto = new JmeProdutoDAO();
//			listagemProduto = daoProduto.listar();
//			this.gerarGrafico();
//		} catch (Exception e) {
//			JSFUtil.adicionarMensagemErro("Erro ao listar Categoria");
//
//		}
//	}
//	
//	public void gerarGrafico(){
//		barChart = new BarChart();
//		
//		for(JmeProduto produto : listagemProduto){
//			barChart.setValue(produto.getDescricao());
//			
//		}
//		
//		barChart.setTitle("Vendidos");
//		barChart.setLegendPosition("e");
//		
//	}
//}
