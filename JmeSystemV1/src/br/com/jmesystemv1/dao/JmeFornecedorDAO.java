package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeFornecedor;
import br.com.jmesystemv1.util.HibernateUtil;

public class JmeFornecedorDAO {

	/**Metodo Salvar */
	public void salvar(JmeFornecedor fornecedor){
		/*Abrindo uma sessão com o banco de dados*/
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		/*Criando uma transação*/
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(fornecedor);
			transacao.commit();
			
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
	public void excluir(JmeFornecedor fornecedor){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(fornecedor);
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
	
	/** Metodo Buscar por Codigo*/
	public JmeFornecedor buscarPorCodigo(Integer idFornecedor){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeFornecedor fornecedor = null;
		
		try{
			Query consulta = sessao.getNamedQuery("JmeFornecedor.buscarPorCodigo");
			consulta.setInteger("idFornecedor", idFornecedor);
			
			fornecedor = (JmeFornecedor) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		
		return fornecedor;
	}
	
	
	/**Metodo Editar*/
	public void editar(JmeFornecedor fornecedor){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(fornecedor);
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
	public List<JmeFornecedor> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeFornecedor> fornecedor = null;
		
		try{
			Query consulta = sessao.getNamedQuery("JmeFornecedor.listar");
			fornecedor = consulta.list();
			
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		
		return fornecedor;
	}

}
