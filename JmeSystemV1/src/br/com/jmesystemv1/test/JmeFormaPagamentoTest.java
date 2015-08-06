package br.com.jmesystemv1.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jmesystemv1.dao.JmeFormaPagamentoDAO;
import br.com.jmesystemv1.domain.JmeFormaPagamento;

public class JmeFormaPagamentoTest {

	@Test
	@Ignore
	public void salvar(){
		JmeFormaPagamento pagamento = new JmeFormaPagamento();
		JmeFormaPagamentoDAO dao = new JmeFormaPagamentoDAO();
		
		pagamento.setDescricao("A Vista");
		pagamento.setDesconto(10.0);
		
		dao.salvar(pagamento);
		
	}
	
	@Test
	public void listar(){
		
		JmeFormaPagamentoDAO dao = new JmeFormaPagamentoDAO();
		
		List<JmeFormaPagamento> formaPagamento = dao.listar();
		
		for(JmeFormaPagamento listar : formaPagamento){
			System.out.println(listar);
		}
		
		
	}
	
	
			
}
