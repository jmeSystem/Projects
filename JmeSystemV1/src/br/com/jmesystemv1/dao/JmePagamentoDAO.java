package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeCategoria;
import br.com.jmesystemv1.domain.JmeItensVenda;
import br.com.jmesystemv1.domain.JmePagamento;
import br.com.jmesystemv1.util.HibernateUtil;
import br.com.jmesystemv1.util.JSFUtil;

public class JmePagamentoDAO {

	/** Metodo Salvar */
	public void salvar(JmePagamento pagamento) {
		/* Abrindo uma sessÃ£o com o banco de dados */
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		/* Criando uma transaÃ§Ã£o */
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(pagamento);
			transacao.commit();
			
		} catch (RuntimeException ex) {
			/*
			 * Se transacao for diferente de null, entÃ£o a transacao e desfeita
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
			JSFUtil.adicionarMensagemSucesso("ExclusÃ£o Realizada com Sucesso !");
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
			
			public void excluirPagamento(JmePagamento pagamento) { // throws Exception quem chamar
				// esse metodo vai ser obrigado
				// a tratar o erro

				// pegar uma sessão para conseguir fazer as operações no banco de dados

				Session sessao = HibernateUtil.getSessionFactory().openSession();
				Transaction transacao = null; // quando for start a transação pode dar
				// erro

				try {

					transacao = sessao.beginTransaction();
					sessao.delete(pagamento);
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
	
	/** Metodo Buscar por Codigo */
	@SuppressWarnings("unchecked")
	public List<JmePagamento> buscarPorParcela(Integer idParcela, Integer idRegistroVenda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmePagamento> pagamento = null;

		try {
			Query consulta = sessao
					.getNamedQuery("JmePagamento.buscarPorParcela");
			consulta.setInteger("idParcela", idParcela);
			consulta.setInteger("idRegistroVenda", idRegistroVenda);

			pagamento = consulta.list();

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
