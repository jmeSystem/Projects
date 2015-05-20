package br.com.jmesystemv1.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jmesystemv1.dao.JmeItensVendaDAO;
import br.com.jmesystemv1.domain.JmeItensVenda;
import br.com.jmesystemv1.domain.JmeRegistroVendas;

@ManagedBean(name="MBItensVenda")
@ViewScoped
public class JmeItensVendaBean {
	
	private JmeRegistroVendas venda;
	
	private JmeItensVenda itens;
	
	public JmeRegistroVendas getVenda() {
		return venda;
	}

	public void setVenda(JmeRegistroVendas venda) {
		this.venda = venda;
	}

	private List<JmeItensVenda> itensVenda;

	public List<JmeItensVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<JmeItensVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	
	
	
	public JmeItensVenda getItens() {
		return itens;
	}

	public void setItens(JmeItensVenda itens) {
		this.itens = itens;
	}


}
	

