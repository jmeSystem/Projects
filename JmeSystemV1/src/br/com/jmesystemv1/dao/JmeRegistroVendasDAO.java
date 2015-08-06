package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeProduto;
import br.com.jmesystemv1.domain.JmeRegistroVendas;
import br.com.jmesystemv1.filter.JmeVendaFilter;
import br.com.jmesystemv1.util.HibernateUtil;


public class JmeRegistroVendasDAO {
	
	// mudei o metodo para integer, pois será preciso pegar o codigo da venda, a hora que for salvar os itens
	public Integer salvar(JmeRegistroVendas venda) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		//Variavel para pegar o codigo da venda.
		Integer codigo = null;
		try {
			transacao = sessao.beginTransaction();
			codigo =(Integer) sessao.save(venda);
			transacao.commit();

		} catch (RuntimeException ex) {

			// se houver erro e a transação não for null, defaz a transação
			if (transacao != null) {
				transacao.rollback();
			}
		} finally {
			sessao.close();
		}
		
		return codigo;
	}

	public void editar(JmeRegistroVendas venda) { // throws Exception quem
		// chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sessão para conseguir fazer as operações no banco de dados

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transação pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.update(venda);
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

	public void excluir(JmeRegistroVendas venda) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			sessao.delete(venda);
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
	
	
	//Excluir RegistroVenda de acordo com o Registro de Venda

			public void excluirPorRegistroVenda(Integer idRegistroVenda) {

				Session sessao = HibernateUtil.getSessionFactory().openSession();
				Transaction transacao = null;
				try {
					transacao = sessao.beginTransaction();
					Query excluir = sessao.getNamedQuery("JmeRegistroVendas.excluirCodigoVenda");
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
			
	@SuppressWarnings("unchecked")
	public List<JmeRegistroVendas> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeRegistroVendas> venda = null;

		try {
			Query consulta = sessao.getNamedQuery("JmeRegistroVendas.listar");
			venda = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return venda;
	}
	
	public JmeRegistroVendas buscarPorClientes(Integer codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeRegistroVendas registroVendas = null;

		try {
			Query consulta = sessao
					.getNamedQuery("JmeRegistroVendas.buscarPorCliente");
			consulta.setInteger("idCliente", codigo);

			registroVendas = (JmeRegistroVendas) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return registroVendas;
	}
	
	public JmeRegistroVendas buscarPorFuncionario(Integer codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeRegistroVendas registroVendas = null;

		try {
			Query consulta = sessao
					.getNamedQuery("JmeRegistroVendas.buscarPorFuncionario");
			consulta.setInteger("idFuncionario", codigo);

			registroVendas = (JmeRegistroVendas) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return registroVendas;
	}
		

	public JmeRegistroVendas buscarPorCodigo(Integer codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeRegistroVendas venda = null;

		try {
			Query consulta = sessao
					.getNamedQuery("JmeRegistroVendas.buscarPorCodigo");
			consulta.setInteger("idRegistroVenda", codigo);

			venda = (JmeRegistroVendas) consulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return venda;
	}
	
	@SuppressWarnings("unchecked")
	public List<JmeRegistroVendas> buscar(JmeVendaFilter filter){
		List<JmeRegistroVendas> vendas = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT venda from JmeRegistroVendas venda ");
		
		if(filter.getDataInicial() !=null && filter.getDataFinal()!=null){
			sql.append("WHERE venda.dataVenda BETWEEN :dataInicial AND :dataFinal ");
		}
		
		sql.append("ORDER BY venda.dataVenda ");

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try{
			Query consulta = sessao.createQuery(sql.toString());
			if(filter.getDataInicial() !=null && filter.getDataFinal()!=null){
				sql.append("WHERE venda.dataVenda BETWEEN :dataInicial AND :dataFinal ");
			
			
			consulta.setDate("dataInicial", filter.getDataInicial());
			consulta.setDate("dataFinal", filter.getDataFinal());
			}
			vendas = consulta.list();
		}catch(RuntimeException e ){
			throw e;
		}finally{
			sessao.close();
		}
		return vendas;
	}
	
	
}
