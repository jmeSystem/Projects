package br.com.jmesystemv1.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeCliente;
import br.com.jmesystemv1.domain.JmePagamento;
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
	
	public void atualizarStatusParcela(String situacao, Integer idParcela, Integer idRegistroVendas) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		System.out.println(""+situacao+"\n"+idParcela+"\n"+idRegistroVendas);
		try {
			transacao = sessao.beginTransaction();
			Query editar = sessao.getNamedQuery("JmeParcela.atualizarStatusParcela");
			editar.setString("situcao", situacao);
			editar.setInteger("numeroParcela", idParcela);
			editar.setInteger("idRegistroVenda", idRegistroVendas);
			editar.executeUpdate();
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
	
	public void editar(JmeParcela parcela) { // throws Exception quem chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sessão para conseguir fazer as operações no banco de dados
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transação pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.update(parcela);
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

	@SuppressWarnings("unchecked")
	public List<JmeParcela>listarTodasParcela(Integer idRegistroVenda) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeParcela> parcela= null;

		try {
			Query consulta = sessao.getNamedQuery("JmeParcela.listarTodasParcela");
			consulta.setInteger("idRegistroVenda", idRegistroVenda);

			parcela = consulta.list();
			
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return parcela;
	}
	
	@SuppressWarnings("unchecked")
	public List<JmeParcela>QuantidadeParcelasVencidas() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeParcela> parcela= null;

		try {
			Query consulta = sessao.getNamedQuery("JmeParcela.TotalParcelaVencidas");
			consulta.setDate("dataAtual",new Date());

			parcela = consulta.list();
			
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return parcela;
	}
}
