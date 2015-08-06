package br.com.jmesystemv1.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeParcelaDAO;
import br.com.jmesystemv1.domain.JmeParcela;

@ManagedBean(name = "MBPrincipal")
@ViewScoped
public class JmePrincipalBean {

	private List<JmeParcela> listagemParcelasVencidas;
	private Integer totalVencidas=0;
	

	public Integer getTotalVencidas() {
		return totalVencidas;
	}

	public void setTotalVencidas(Integer totalVencidas) {
		this.totalVencidas = totalVencidas;
	}

	public List<JmeParcela> getListagemParcelasVencidas() {
		return listagemParcelasVencidas;
	}

	public void setListagemParcelasVencidas(
			List<JmeParcela> listagemParcelasVencidas) {
		this.listagemParcelasVencidas = listagemParcelasVencidas;
	}
	
	public void listarParcelasVencidas(){
		JmeParcelaDAO daoParcela = new JmeParcelaDAO();
		listagemParcelasVencidas= daoParcela.QuantidadeParcelasVencidas();
		
		for(int i=0; i<listagemParcelasVencidas.size(); i++){
			totalVencidas= totalVencidas+1;
		}
	}
	public String buscar(){
		return "/pages/jmeParcelasVencidas.xhtml?faces-redirect=true";
	}
}
