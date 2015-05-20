package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeItensVenda;
import br.com.jmesystemv1.domain.JmePagamento;
import br.com.jmesystemv1.util.HibernateUtil;
import br.com.jmesystemv1.util.JSFUtil;

public class JmePagamentoDAO {

	/** Metodo Salvar */
	public void salvar(JmePagamento pagamento) {
		/* Abrindo uma sess√£o com o banco de dados */
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		/* Criando uma transa√ß√£o */
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(pagamento);
			transacao.commit();
			JSFUtil.adicionarMensagemSucesso("Venda Realizada com Sucesso.");
		} catch (RuntimeException ex) {
			/*
			 * Se transacao for diferente de null, ent√£o a transacao e desfeita
			 * usando o metodo rollback
			 */
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}

	/** Metodo Excluir */
	public void excluir(JmePagamento pagamento) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(pagamento);
			transacao.commit();
			JSFUtil.adicionarMensagemSucesso("Exclus√£o Realizada com Sucesso !");
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}
	
	//Excluir Pagamento de acordo com o Registro de Venda

			public void excluirPorRegistroVenda(Integer idRegistroVenda) {

				Session sessao = HibernateUtil.getSessionFactory().openSession();
				Transaction transacao = null;
				try {
					transacao = sessao.beginTransaction();
					Query excluir = sessao.getNamedQuery("JmePagamento.excluirCodigoVenda");
					excluir.setInteger("idRegistroVenda", idRegistroVenda);
					excluir.executeUpdate();
					transacao.commit(); // confirma a transaÁ„o
					
				} catch (RuntimeException ex) {
					// se for diferente de null e desfaz toda trasaÁ„o
					if (transacao != null) {
						transacao.rollback();// desfazer toda a transaÁ„o
					}
					throw ex;
					
				} finally {
					sessao.close();
				}
			}
			

	/** Metodo Buscar por Codigo */
	public JmePagamento buscarPorCodigo(Integer idPagamento) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmePagamento pagamento = null;

		try {
			Query consulta = sessao
					.getNamedQuery("JmePagamento.buscarPorCodigo");
			consulta.setInteger("idPagamentor", idPagamento);

			pagamento = (JmePagamento) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return pagamento;
	}

	/** Metodo Editar */
	public void editar(JmePagamento pagamento) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(pagamento);
			transacao.commit();

			JSFUtil.adicionarMensagemSucesso("Cadastro Atualizado com Sucesso !");

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}

	/** Metodo Listar */
	@SuppressWarnings("unchecked")
	public List<JmePagamento> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmePagamento> pagamento = null;

		try {
			Query consulta = sessao.getNamedQuery("JmePagamento.listar");
			pagamento = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return pagamento;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<JmePagamento>listarTodosPagamento(Integer idRegistroVenda) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmePagamento> pagamentos = null;

		try {
			Query consulta = sessao.getNamedQuery("JmePagamento.listarTodosPagamento");
			consulta.setInteger("idRegistroVenda", idRegistroVenda);

			pagamentos = consulta.list();
			
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return pagamentos;
	}
	

}
