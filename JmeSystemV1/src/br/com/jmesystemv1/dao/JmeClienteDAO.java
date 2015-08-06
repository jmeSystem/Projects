package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeCliente;
import br.com.jmesystemv1.util.HibernateUtil;


public class JmeClienteDAO {

	public void salvar(JmeCliente cliente) { // throws Exception quem chamar
												// esse metodo vai ser obrigado
												// a tratar o erro

		// pegar uma sessão para conseguir fazer as operações no banco de dados

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transação pode dar
										// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.save(cliente);
			transacao.commit(); // confirma a transação
			
		} catch (RuntimeException ex) {
			// se for diferente de null e desfaz toda trasação
			if (transacao != null) {
				transacao.rollback();// desfazer toda a transação
			}

		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<JmeCliente> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeCliente> clientes = null;

		try {
			Query consulta = sessao.getNamedQuery("JmeCliente.listar");
			clientes = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return clientes;
	}

	public JmeCliente buscarPorCodigo(Integer codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeCliente cliente = null;

		try {
			Query consulta = sessao.getNamedQuery("JmeCliente.buscarPorCodigo");
			consulta.setInteger("idCliente", codigo);

			cliente = (JmeCliente) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return cliente;
	}

	public void editar(JmeCliente cliente) { // throws Exception quem chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sessão para conseguir fazer as operações no banco de dados
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transação pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.update(cliente);
			transacao.commit(); // confirma a transação
		} catch (RuntimeException ex) {
			// se for diferente de null e desfaz toda trasação
			if (transacao != null) {
				transacao.rollback();// desfazer toda a transação
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}
	
	public void excluir(JmeCliente clientes) { // throws Exception quem chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sessão para conseguir fazer as operações no banco de dados

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transação pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.delete(clientes);
			transacao.commit(); // confirma a transação
			
		} catch (RuntimeException ex) {
			// se for diferente de null e desfaz toda trasação
			if (transacao != null) {
				transacao.rollback();// desfazer toda a transação
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}
}
