package br.com.jmesystemv1.test;


import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jmesystemv1.dao.JmeClienteDAO;
import br.com.jmesystemv1.domain.JmeCidade;
import br.com.jmesystemv1.domain.JmeCliente;

public class JmeClienteTest {

	

	
	@Test
	
	public void salvar(){
		
		JmeClienteDAO dao = new JmeClienteDAO();
		JmeCliente cliente = new JmeCliente();
		JmeCidade cidade = new JmeCidade();
		
		cidade.setIdCidade(1);
		
		cliente.setCidade(cidade);
		cliente.setCpf("086.765.359-06");
		cliente.setDataCadastro(new Date());
		cliente.setDataNascimento(new Date());
		cliente.setEndereco("Rua Waldomirosasaa");
		cliente.setInforComplementar("Trabalhsasasa");
		cliente.setNome("Mario Octavio Busasasa");
		cliente.setTelefone("43353sasasaa");
		cliente.setTelefoneTrabalho("4335sasasasa");

		dao.salvar(cliente);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		JmeClienteDAO dao = new JmeClienteDAO();
		List<JmeCliente> clientes = dao.listar();
		
		for(JmeCliente lista : clientes ){
		System.out.println(lista);
		
		}
	}
	
	@Test
	@Ignore
	public void editar(){
		JmeClienteDAO dao = new JmeClienteDAO();
		JmeCidade cid = new JmeCidade();

		JmeCliente clientes = dao.buscarPorCodigo(1);
		cid.setIdCidade(578);
		clientes.setCidade(cid);
		clientes.setCpf("1234");
		clientes.setDataCadastro(new Date());
		clientes.setDataNascimento(new Date());
		clientes.setEndereco("Rua Waldomiro");
		clientes.setInforComplementar("Trabalha");
		clientes.setNome("Olivia");
		clientes.setTelefone("55566");
		clientes.setTelefoneTrabalho("55599");
		
		dao.editar(clientes);
		
	}
//		// excluir
	@Test
	@Ignore
	public void excluir(){
	JmeCliente clientes = new JmeCliente();
	clientes.setIdCliente(2);
	}	
		
	

}
