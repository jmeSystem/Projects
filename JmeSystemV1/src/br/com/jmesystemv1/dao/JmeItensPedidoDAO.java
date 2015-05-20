package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeItensPedido;
import br.com.jmesystemv1.util.HibernateUtil;

public class JmeItensPedidoDAO {
	
	public void salvar(JmeItensPedido item){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			
			transacao = sessao.beginTransaction();
			sessao.save(item);
			transacao.commit();
			System.out.println("Passei no Salvar !!");
		}catch(RuntimeException ex){
			if (transacao != null) {
				transacao.rollback();
			}
			System.out.println("Não Passei no Salvar !!");
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<JmeItensPedido> listar(){
		
		Session sessa = HibernateUtil.getSessionFactory().openSession();
		List<JmeItensPedido> itens = null;
		
		try{
			Query consulta = sessa.getNamedQuery("JmeItensPedido.listar");
			itens = consulta.list();
			
		}catch(RuntimeException ex){
			throw ex;
		} finally{
			sessa.close();
		}
		
		return itens;
	}
	
	public JmeItensPedido buscarPorItemPedido(Integer itemPedido){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeItensPedido item = null;
		
		try{
			Query consulta = sessao.getNamedQuery("JmeItensPedido.buscarPorItemPedido");
			consulta.setInteger("idItensPedido", itemPedido);
			
			item = (JmeItensPedido) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return item;
	}
	
	public void excluir(JmeItensPedido item){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(item);
			transacao.commit();
			
		}catch(RuntimeException ex){
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
		
	}
	
	public void editar(JmeItensPedido item){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(item);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}

}
