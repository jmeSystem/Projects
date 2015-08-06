package br.com.jmesystemv1.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jmesystemv1.dao.JmeItensVendaDAO;
import br.com.jmesystemv1.domain.JmeItensVenda;
import br.com.jmesystemv1.domain.JmeProduto;
import br.com.jmesystemv1.domain.JmeRegistroVendas;

public class JmeItensVendaTest {
	JmeItensVendaDAO dao = new JmeItensVendaDAO();
	JmeItensVenda itensVenda = new JmeItensVenda();
	JmeRegistroVendas registroVenda = new JmeRegistroVendas();
	JmeProduto produto = new JmeProduto();
	
	@Test
	@Ignore
	public void salvar(){
		
		registroVenda.setIdRegistroVenda(1);
		//produto.setIdProduto(1);
		
		itensVenda.setProduto(produto);
		itensVenda.setRegistroVendas(registroVenda);
		itensVenda.setQuantidade(2);
		itensVenda.setValorTotalItens(233.0);
		
		dao.salvar(itensVenda);
		
		
	}
	@Test
	@Ignore
	public void listar(){
		
		List<JmeItensVenda> itensVendas = dao.listar();
		
		for(JmeItensVenda lista : itensVendas ){
		System.out.println(lista);
		
		}
}
	@Test
	@Ignore
	public void excluir(){
		itensVenda = dao.buscarPorCodigo(1);
		dao.excluir(itensVenda);
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
	itensVenda = dao.buscarPorCodigo(3);
		
	System.out.println(itensVenda);
	}
	
	@Test
	public void editar(){
		itensVenda = dao.buscarPorCodigo(3);
		
		//produto.setIdProduto(1);
		registroVenda.setIdRegistroVenda(1);
		
		itensVenda.setProduto(produto);
		itensVenda.setRegistroVendas(registroVenda);
		itensVenda.setQuantidade(500);
		itensVenda.setValorTotalItens(5000.0);
		
		dao.editar(itensVenda);
		
		
	}
}
