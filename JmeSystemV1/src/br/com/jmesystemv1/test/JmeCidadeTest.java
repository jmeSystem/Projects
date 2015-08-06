package br.com.jmesystemv1.test;

import java.util.List;

import org.junit.Test;

import br.com.jmesystemv1.dao.JmeCidadeDAO;
import br.com.jmesystemv1.domain.JmeCidade;

public class JmeCidadeTest {

	

	@Test
	public void listar(){
		
		JmeCidadeDAO dao = new JmeCidadeDAO();

		List<JmeCidade> cidades = dao.listar();

		for(JmeCidade cidade : cidades ){
			System.out.println(cidade);
		}
	}
}
