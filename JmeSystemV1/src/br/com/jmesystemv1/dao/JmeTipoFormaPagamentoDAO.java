package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



import br.com.jmesystemv1.domain.JmeTipoFormaPagamento;
import br.com.jmesystemv1.util.HibernateUtil;
import br.com.jmesystemv1.util.JSFUtil;

public class JmeTipoFormaPagamentoDAO {

	
	/**Metodo Salvar */
	public void salvar(JmeTipoFormaPagamento tipoPagamento){
		/*Abrindo uma sessão com o banco de dados*/
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		/*Criando uma transação*/
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(tipoPagamento);
			transacao.commit();
			JSFUtil.adicionarMensagemSucesso("Cadastro Salvo com Sucesso !");
		} catch (RuntimeException ex) {
			/*Se transacao for diferente de null, então a transacao e desfeita
			 * usando o metodo rollback*/
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		} finally{
			sessao.close();
		}
	}
	
	/**Metodo Excluir*/
	public void excluir(JmeTipoFormaPagamento tipoPagamento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(tipoPagamento);
			transacao.commit();
			JSFUtil.adicionarMensagemSucesso("Exclusão Realizada com Sucesso !");
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	/** Metodo Buscar por Codigo*/
	public JmeTipoFormaPagamento buscarPorCodigo(Integer idTipoFormaPagamento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeTipoFormaPagamento tipoPagamento = null;
		
		try{
			Query consulta = sessao.getNamedQuery("JmeTipoFormaPagamento.buscarPorCodigo");
			consulta.setInteger("idTipoFormaPagamento", idTipoFormaPagamento);
			
			tipoPagamento = (JmeTipoFormaPagamento) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		
		return tipoPagamento;
	}
	
	
	/**Metodo Editar*/
	public void editar(JmeTipoFormaPagamento tipoPagamento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(tipoPagamento);
			transacao.commit();
			
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	/**Metodo Listar*/
	@SuppressWarnings("unchecked")
	public List<JmeTipoFormaPagamento> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeTipoFormaPagamento> tipoPagamento = null;
		
		try{
			Query consulta = sessao.getNamedQuery("JmeTipoFormaPagamento.listar");
			tipoPagamento = consulta.list();
			
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		
		return tipoPagamento;
	}

}

