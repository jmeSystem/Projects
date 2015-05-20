package br.com.jmesystemv1.test;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jmesystemv1.dao.JmePedidoCompraDAO;
import br.com.jmesystemv1.domain.JmeFornecedor;
import br.com.jmesystemv1.domain.JmeFuncionario;
import br.com.jmesystemv1.domain.JmePedidoCompra;

public class JmePedidoCompraTest {
	
	@Test

	public void salvar(){
		
		JmePedidoCompra pedido = new JmePedidoCompra();
		JmeFornecedor fornecedor = new JmeFornecedor();
		JmeFuncionario funcionario = new JmeFuncionario();
		JmePedidoCompraDAO pedidoDao = new JmePedidoCompraDAO();
				
//		Calendar calendar = Calendar.getInstance();
//		
//		
//		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 10);
//		Date data = calendar.getTime();
		
		fornecedor.setIdFornecedor(15);
		funcionario.setIdFuncionario(1);
		
		pedido.setIdFornecedor(fornecedor);
		pedido.setIdFuncionario(funcionario);
		pedido.setDataAbertura(new Date());
		pedido.setStatusPedido("A");
		
		pedidoDao.salvar(pedido);
		
		System.out.println(pedido);
	}
	
	@Test
	@Ignore
	public void buscarPorNumeroPedido(){
		
		JmePedidoCompraDAO pedidoDAO = new JmePedidoCompraDAO();
		
		JmePedidoCompra pedido = pedidoDAO.buscarPorNumeroPedido(1);
		
		System.out.println(pedido);
	}
	
	@Test
	@Ignore
	public void editar(){
		
		JmePedidoCompraDAO pedidoDAO = new JmePedidoCompraDAO();
//		JmeFornecedor fornecedor = new JmeFornecedor();
//		JmeFuncionario funcionario = new JmeFuncionario();
		
		JmePedidoCompra pedido = pedidoDAO.buscarPorNumeroPedido(1);
		
//		pedido.setIdFornecedor(fornecedor);
//		pedido.setIdFuncionario(funcionario);
		pedido.setStatusPedido("R");
//		pedido.setDataAbertura(new Date());
		pedidoDAO.editar(pedido);
		
		System.out.println(pedido);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		JmePedidoCompraDAO pedidoDAO = new JmePedidoCompraDAO();
		
		List<JmePedidoCompra> pedido = pedidoDAO.listar();
		
		for(JmePedidoCompra jmePedido : pedido){
			System.out.println(jmePedido);
		}
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		
		JmePedidoCompraDAO pedidoDAO = new JmePedidoCompraDAO();
		
		JmePedidoCompra pedido = pedidoDAO.buscarPorNumeroPedido(1);
		
		if (pedido != null){
			
			pedidoDAO.excluir(pedido);
		}
		
	}
	

}
