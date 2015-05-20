package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeFormaPagamento;
import br.com.jmesystemv1.util.HibernateUtil;
import br.com.jmesystemv1.util.JSFUtil;

public class JmeFormaPagamentoDAO {

	/**Metodo Salvar */
	public void salvar(JmeFormaPagamento formaPagamento){
		/*Abrindo uma sessão com o banco de dados*/
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		/*Criando uma transação*/
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(formaPagamento);
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
	public void excluir(JmeFormaPagamento formaPagamento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(formaPagamento);
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
	public JmeFormaPagamento buscarPorCodigo(Integer idFormaPagamento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeFormaPagamento formaPagamento = null;
		
		try{
			Query consulta = sessao.getNamedQuery("JmeFormaPagamento.buscarPorCodigo");
			consulta.setInteger("idFormaPagamento", idFormaPagamento);
			
			formaPagamento = (JmeFormaPagamento) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		
		return formaPagamento;
	}
	
	
	/**Metodo Editar*/
	public void editar(JmeFormaPagamento formaPagamento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(formaPagamento);
			transacao.commit();
			
			JSFUtil.adicionarMensagemSucesso("Cadastro Atualizado com Sucesso !");
			
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
	public List<JmeFormaPagamento> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeFormaPagamento> formaPagamento = null;
		
		try{
			Query consulta = sessao.getNamedQuery("JmeFormaPagamento.listar");
			formaPagamento = consulta.list();
			
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		
		return formaPagamento;
	}

}
