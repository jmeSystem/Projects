package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeCategoria;
import br.com.jmesystemv1.domain.JmeCliente;
import br.com.jmesystemv1.util.HibernateUtil;

public class JmeCategoriaDAO {

	public void salvar(JmeCategoria categoria) { // throws Exception quem chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sess�o para conseguir fazer as opera��es no banco de dados

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transa��o pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.save(categoria);
			transacao.commit(); // confirma a transa��o

		} catch (RuntimeException ex) {
			// se for diferente de null e desfaz toda trasa��o
			if (transacao != null) {
				transacao.rollback();// desfazer toda a transa��o
			}

		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<JmeCategoria> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeCategoria> categoria = null;

		try {
			Query consulta = sessao.getNamedQuery("JmeCategoria.listar");
			categoria = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return categoria;
	}
	
	
	public void editar(JmeCategoria categoria) { // throws Exception quem chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sess�o para conseguir fazer as opera��es no banco de dados
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transa��o pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.update(categoria);
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
	
	public void excluir(JmeCategoria categoria) { // throws Exception quem chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sess�o para conseguir fazer as opera��es no banco de dados

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transa��o pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.delete(categoria);
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
}
