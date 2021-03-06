package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeMovimentacao;
import br.com.jmesystemv1.domain.JmeProduto;
import br.com.jmesystemv1.util.HibernateUtil;


public class JmeProdutoDAO {

	public void salvar(JmeProduto produto) { // throws Exception quem chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sess�o para conseguir fazer as opera��es no banco de dados

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transa��o pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.save(produto);
			transacao.commit(); // confirma a transa��o
			System.out.println("teste");
		} catch (RuntimeException ex) {
			
			// se for diferente de null e desfaz toda trasa��o
			if (transacao != null) {
				transacao.rollback();// desfazer toda a transa��o
				System.out.println("teste esta errado ai");
			}

		} finally {
			sessao.close();
		}
	}

	/**
	 * M�todo responsavel por listar todos os produtos
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<JmeProduto> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeProduto> produtos = null;

		try {
			Query consulta = sessao.getNamedQuery("JmeProduto.listar");
			produtos = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return produtos;
	}
	
	/**
	 * M�todo Responsavel por excluir um produto selecionado
	 * 
	 * @param codigo
	 */
	public void excluir(JmeProduto produto) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			sessao.delete(produto);
			transacao.commit(); // confirma a transa��o
		} catch (RuntimeException ex) {
			// se for diferente de null e desfaz toda trasa��o
			if (transacao != null) {
				transacao.rollback();// desfazer toda a transa��o
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}

	/**
	 * M�todo responsavel por buscar um produto a partir de seu c�digo
	 * 
	 * @param codigo
	 * @return
	 */
	public JmeProduto buscarPorCodigo(Integer codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeProduto produto = null;

		try {
			Query consulta = sessao.getNamedQuery("JmeProduto.buscarPorCodigo");
			consulta.setInteger("idProduto", codigo);

			produto = (JmeProduto) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return produto;
	}

	/**
	 * M�todo reponsavel por alterar um produto
	 * 
	 * @param cliente
	 */
	public void editar(JmeProduto produto) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			sessao.update(produto);
			transacao.commit();
		} catch (RuntimeException ex) {

			if (transacao != null) {
				transacao.rollback();// desfaz toda a transa��o
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}
	
	public JmeProduto buscarPorCategoria(Integer codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeProduto produto = null;

		try {
			Query consulta = sessao
					.getNamedQuery("JmeProduto.buscarPorCategoria");
			consulta.setInteger("idCategoria", codigo);

			produto = (JmeProduto) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return produto;
	}
	
	public JmeProduto buscarPorFornecedor(Integer codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeProduto produto = null;

		try {
			Query consulta = sessao
					.getNamedQuery("JmeProduto.buscarPorFornecedor");
			consulta.setInteger("idFornecedor", codigo);

			produto = (JmeProduto) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return produto;
	}

}
