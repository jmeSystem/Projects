package br.com.jmesystemv1.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jmesystemv1.dao.JmeItensPedidoDAO;
import br.com.jmesystemv1.dao.JmePedidoCompraDAO;
import br.com.jmesystemv1.dao.JmeProdutoDAO;
import br.com.jmesystemv1.domain.JmeItensPedido;
import br.com.jmesystemv1.domain.JmePedidoCompra;
import br.com.jmesystemv1.domain.JmeProduto;

public class JmeItensPedidoTest {
	
	@Test
	
	public void salvar(){
		
		JmeProdutoDAO produtoDAO = new JmeProdutoDAO();
		JmeProduto produto = produtoDAO.buscarPorCodigo(1);
		
		JmePedidoCompraDAO pedidoDAO = new JmePedidoCompraDAO();
		JmePedidoCompra pedido = pedidoDAO.buscarPorNumeroPedido(3);
		
		JmeItensPedido item = new JmeItensPedido();
		item.setIdProduto(produto);
		item.setIdPedido(pedido);
		item.setQuantidadeProduto(2);
		item.setValorUnitario(100.00);
		
		JmeItensPedidoDAO itemDAO = new JmeItensPedidoDAO();
		itemDAO.salvar(item);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		JmeItensPedidoDAO itemDAO = new JmeItensPedidoDAO();
		
		List<JmeItensPedido> itens = itemDAO.listar();
		
		System.out.println(itens);
	}
	@Test
	@Ignore
	public void buscarPorItemPedido(){
		
		JmeItensPedidoDAO itemDAO = new JmeItensPedidoDAO();
		
		JmeItensPedido item = itemDAO.buscarPorItemPedido(1);
		
		System.out.println(item);
	}
	
	@Test
	@Ignore
	public void editar(){
		
		JmeProdutoDAO produtoDAO = new JmeProdutoDAO();
		JmeProduto produto = produtoDAO.buscarPorCodigo(1);
		
		JmePedidoCompraDAO pedidoDAO = new JmePedidoCompraDAO();
		JmePedidoCompra pedido = pedidoDAO.buscarPorNumeroPedido(3);
		
		JmeItensPedidoDAO itemDAO = new JmeItensPedidoDAO();
		
		JmeItensPedido item = itemDAO.buscarPorItemPedido(1);
		
		item.setIdProduto(produto);
		item.setIdPedido(pedido);
		item.setQuantidadeProduto(4);
		item.setValorUnitario(100.00);
		
		
		itemDAO.editar(item);
	}
	
	@Test
	@Ignore
	public void excluir(){
		
		JmeItensPedidoDAO itemDAO = new JmeItensPedidoDAO();
		
		JmeItensPedido item = itemDAO.buscarPorItemPedido(1);
		
		itemDAO.excluir(item);
		
	}
	

}
