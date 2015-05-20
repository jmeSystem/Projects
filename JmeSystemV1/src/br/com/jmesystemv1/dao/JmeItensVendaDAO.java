package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeItensVenda;
import br.com.jmesystemv1.util.HibernateUtil;
import br.com.jmesystemv1.util.JSFUtil;

public class JmeItensVendaDAO {

	public void salvar (JmeItensVenda itensVenda){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(itensVenda);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			
		}finally{
			sessao.close();
		}
			
	}
	
	@SuppressWarnings("unchecked")
	public List<JmeItensVenda> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeItensVenda> itensVenda = null;

		try {
			Query consulta = sessao.getNamedQuery("JmeItensVenda.listar");
			itensVenda = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return itensVenda;
	}
	
	public void excluir(JmeItensVenda itensVenda) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			sessao.delete(itensVenda);
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
	
	//Excluir ItensVenda de acordo com o Registro de Venda

		public void excluirPorRegistroVenda(Integer idRegistroVenda) {

			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Transaction transacao = null;
			try {
				transacao = sessao.beginTransaction();
				Query excluir = sessao.getNamedQuery("JmeItensVenda.excluirCodigoVenda");
				excluir.setInteger("idRegistroVenda", idRegistroVenda);
				excluir.executeUpdate();
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
		

	public void editar(JmeItensVenda itensVenda) { // throws Exception quem
														// chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sess�o para conseguir fazer as opera��es no banco de dados

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transa��o pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.update(itensVenda);
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

	public JmeItensVenda buscarPorCodigo(Integer codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeItensVenda itensVenda = null;

		try {
			Query consulta = sessao
					.getNamedQuery("JmeItensVenda.buscarPorCodigo");
			consulta.setInteger("idItensVenda", codigo);

			itensVenda = (JmeItensVenda) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return itensVenda;
	}

	@SuppressWarnings("unchecked")
	public List<JmeItensVenda>buscarPorCodigo2(Integer codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeItensVenda> itensVenda = null;

		try {
			Query consulta = sessao.getNamedQuery("JmeItensVenda.buscarPorCodigoVenda");
			consulta.setInteger("idItensVenda", codigo);

			itensVenda = consulta.list();
			
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return itensVenda;
	}
	
	
	
}
