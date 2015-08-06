package br.com.jmesystemv1.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.jmesystemv1.dao.JmeFuncionarioDAO;
import br.com.jmesystemv1.domain.JmeCidade;
import br.com.jmesystemv1.domain.JmeFuncionario;

public class JmeFuncionarioTest {

	public static void main(String args[]) {

//		JmeFuncionarioDAO dao = new JmeFuncionarioDAO();
//		JmeFuncionario fun = new JmeFuncionario();
//		JmeCidade cidade = new JmeCidade();
//
//		// Salvar
//		cidade.setIdCidade(1);
//		fun.setCidade(cidade);
//		fun.setNome("Mário");
//		fun.setEndereco("testeteste");
//		fun.setTelefone("433");
//		fun.setCpf("086.765.359-06");
//		fun.setFuncao("Administrador");
//		fun.setSenha("12345678");
//
//		dao.salvar(fun);
//
//		// Listar
//		// List<JmeFuncionario> funcionarios = dao.listar();
//		//
//		// for (JmeFuncionario lista : funcionarios) {
//		// System.out.println(lista);
//		//
//		// }

	}
	@Test
	public void autenticar(){
		JmeFuncionarioDAO funcionarioDAO = new JmeFuncionarioDAO();
		
		JmeFuncionario funcionario = funcionarioDAO.autenticar("086.765.359-06", "21112121");
		
		//System.out.println("Funcionario:"+ funcionario);
		
		Assert.assertNotNull(funcionario);
	}

}
