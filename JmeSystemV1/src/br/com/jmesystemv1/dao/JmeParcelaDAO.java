package br.com.jmesystemv1.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeParcela;
import br.com.jmesystemv1.util.HibernateUtil;


public class JmeParcelaDAO {

	public void salvar(JmeParcela parcela){
	// pegar uma sess�o para conseguir fazer as opera��es no banco de dados

			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Transaction transacao = null; // quando for start a transa��o pode dar
											// erro

			try {

				transacao = sessao.beginTransaction();
				sessao.save(parcela);
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
	
	//Excluir Parcela de acordo com o Registro de Venda

	public void excluirPorRegistroVenda(Integer idRegistroVenda) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			Query excluir = sessao.getNamedQuery("JmeParcela.excluirCodigoVenda");
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
}
