package br.com.jmesystemv1.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeParcelaDAO;
import br.com.jmesystemv1.domain.JmeParcela;

@ViewScoped
@ManagedBean(name="MBParcelasVencidas")
public class JmeParcelasVencidasBean {

	private List<JmeParcela> listagemParcelasVencidas;
	
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
		
	}
}
