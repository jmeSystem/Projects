package br.com.jmesystemv1.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeParcela;
import br.com.jmesystemv1.util.HibernateUtil;


public class JmeParcelaDAO {

	public void salvar(JmeParcela parcela){
	// pegar uma sessão para conseguir fazer as operações no banco de dados

			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Transaction transacao = null; // quando for start a transação pode dar
											// erro

			try {

				transacao = sessao.beginTransaction();
				sessao.save(parcela);
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
	
	//Excluir Parcela de acordo com o Registro de Venda

	public void excluirPorRegistroVenda(Integer idRegistroVenda) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			Query excluir = sessao.getNamedQuery("JmeParcela.excluirCodigoVenda");
			excluir.setInteger("idRegistroVenda", idRegistroVenda);
			excluir.executeUpdate();
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
