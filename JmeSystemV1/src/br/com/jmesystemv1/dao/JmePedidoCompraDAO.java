package br.com.jmesystemv1.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmePedidoCompra;
import br.com.jmesystemv1.util.HibernateUtil;
import br.com.jmesystemv1.util.JSFUtil;

public class JmePedidoCompraDAO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void salvar(JmePedidoCompra pedido){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(pedido);
			transacao.commit();
			JSFUtil.adicionarMensagemSucesso("Pedido realizado com sucesso !!");
			
		}catch(RuntimeException ex){
			
			if (transacao != null){
				transacao.rollback();
				
			}
			throw ex;
			
		} finally{
			sessao.close();
		}
			
	}
	
	public void excluir(JmePedidoCompra pedido){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(pedido);
			transacao.commit();
			JSFUtil.adicionarMensagemSucesso("Pedido excluido com sucesso !!");
			
		}catch(RuntimeException ex){
			
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
			
		} finally{
			sessao.close();
		}
	}
	
	public JmePedidoCompra buscarPorNumeroPedido(Integer numeroPedido){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmePedidoCompra pedido = null;
		
		try{
			Query consulta = sessao.getNamedQuery("JmePedidoCompra.buscarPorNumeroPedido");
			consulta.setInteger("numeroPedido", numeroPedido);
			
			pedido = (JmePedidoCompra) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
			throw ex;
		} finally{
			sessao.close();
		}
		
		return pedido;
	}

	public void editar(JmePedidoCompra pedido){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(pedido);
			transacao.commit();
			
			JSFUtil.adicionarMensagemSucesso("Cadastro atualizado com sucesso !!");
			
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
			
		} finally{
			sessao.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<JmePedidoCompra> listar(){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmePedidoCompra> pedido = null;
		
		try{
			Query consulta = sessao.getNamedQuery("JmePedidoCompra.listar");
			pedido = consulta.list();
			
		}catch(RuntimeException ex){
			throw ex;
			
		} finally{
			sessao.close();
		}
		
		return pedido;
	}

}
