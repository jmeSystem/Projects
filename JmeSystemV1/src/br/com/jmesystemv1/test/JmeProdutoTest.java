package br.com.jmesystemv1.test;

import java.util.Date;

import br.com.jmesystemv1.dao.JmeProdutoDAO;
import br.com.jmesystemv1.domain.JmeProduto;

public class JmeProdutoTest {
	
	public static void main(String [] args){
		
		JmeProdutoDAO dao = new JmeProdutoDAO();
		JmeProduto produto = new JmeProduto();
		
		
		produto.setCodProduto("22");
		produto.setDescricao("33");
		produto.setDataCadastro(new Date());
		produto.setEstoqueMax(44);
		produto.setEstoqueMin(55);
		produto.setMarca("77");
		produto.setModelo("88");
		produto.setQuantEstoque(99);
		produto.setValorUnitario(10.0);
		produto.getFornecedor().setIdFornecedor(10);
		
		dao.salvar(produto);	
		//dao.excluir(1);
	}
}
