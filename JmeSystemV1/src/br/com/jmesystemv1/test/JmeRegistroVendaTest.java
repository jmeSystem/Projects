package br.com.jmesystemv1.test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;





import br.com.jmesystemv1.dao.JmeRegistroVendasDAO;
import br.com.jmesystemv1.domain.JmeCliente;
import br.com.jmesystemv1.domain.JmeFuncionario;
import br.com.jmesystemv1.domain.JmeRegistroVendas;
import br.com.jmesystemv1.filter.JmeVendaFilter;

public class JmeRegistroVendaTest {
	
	JmeFuncionario fun = new JmeFuncionario();
	JmeCliente cli = new JmeCliente();
	JmeRegistroVendasDAO dao = new JmeRegistroVendasDAO();
	JmeRegistroVendas venda = new JmeRegistroVendas();
	
	@Ignore
	public void salvar(){
		
		fun.setIdFuncionario(1);
		cli.setIdCliente(2);
	
		venda.setCliente(cli);
		venda.setFuncionario(fun);
		venda.setDataVenda(new Date());
		venda.setValorTotal(12.8);
		
		dao.salvar(venda);
		
	}
	
	@Ignore
	public void excluir (){
		venda = dao.buscarPorCodigo(1);
		
		dao.excluir(venda);
	}
	

	@Ignore
	public void editar(){
		
		venda = dao.buscarPorCodigo(2);
		
		venda.setFuncionario(fun);
		venda.setCliente(cli);
		venda.setDataVenda(new Date());
		venda.setValorTotal(100.00);
		
		dao.editar(venda);
	}

	@Test
	@Ignore
	public void listar(){
	
	List<JmeRegistroVendas> vendas = dao.listar();
	
	for(JmeRegistroVendas lista : vendas ){
	System.out.println(lista);
	
	}
	
	}
	@Test
	public void buscar() throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		JmeVendaFilter filtro = new JmeVendaFilter();
		filtro.setDataInicial(formato.parse("01/05/2015"));
		filtro.setDataFinal(formato.parse("01/05/2015"));
		
		JmeRegistroVendasDAO dao = new JmeRegistroVendasDAO();
		
		List<JmeRegistroVendas> vendas = dao.buscar(filtro);
		
		for(JmeRegistroVendas venda: vendas){
			System.out.println(venda);
		}
		
	}
}