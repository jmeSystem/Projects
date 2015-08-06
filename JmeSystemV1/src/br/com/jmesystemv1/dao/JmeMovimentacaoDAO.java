package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;







import br.com.jmesystemv1.domain.JmeCliente;
import br.com.jmesystemv1.domain.JmeItensVenda;
import br.com.jmesystemv1.domain.JmeMovimentacao;
import br.com.jmesystemv1.domain.JmeRegistroVendas;
import br.com.jmesystemv1.filter.JmeMovEntradaFilter;
import br.com.jmesystemv1.filter.JmeVendaFilter;
import br.com.jmesystemv1.util.HibernateUtil;

public class JmeMovimentacaoDAO {

	public void salvar(JmeMovimentacao movimentacao) { // throws Exception quem chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sessão para conseguir fazer as operações no banco de dados

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transação pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.save(movimentacao);
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

	@SuppressWarnings("unchecked")
	public List<JmeMovimentacao> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeMovimentacao> movimentacao = null;

		try {
			Query consulta = sessao.getNamedQuery("JmeMovimentacao.listar");
			movimentacao = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return movimentacao ;
	}
	
	public void editar(JmeMovimentacao movimentacao) { // throws Exception quem chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sessão para conseguir fazer as operações no banco de dados
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transação pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.update(movimentacao);
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
	
	public void excluir(JmeMovimentacao movimentacao) { // throws Exception quem chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sessão para conseguir fazer as operações no banco de dados

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transação pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.delete(movimentacao);
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
	
	public JmeMovimentacao buscarPorProduto(Integer codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeMovimentacao movimentacao = null;

		try {
			Query consulta = sessao
					.getNamedQuery("JmeMovimentacao.buscarPorProduto");
			consulta.setInteger("idProduto", codigo);

			movimentacao = (JmeMovimentacao) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return movimentacao;
	}
	
	@SuppressWarnings("unchecked")
	public List<JmeMovimentacao> buscar(JmeMovEntradaFilter filter){
		List<JmeMovimentacao> movEntrada = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT movEntrada from JmeMovimentacao movEntrada ");
		
		if(filter.getDataInicial() !=null && filter.getDataFinal()!=null){
			sql.append("WHERE movEntrada.dataEntrada BETWEEN :dataInicial AND :dataFinal ");
		}
		
		sql.append("ORDER BY movEntrada.dataEntrada ");

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try{
			Query consulta = sessao.createQuery(sql.toString());
			if(filter.getDataInicial() !=null && filter.getDataFinal()!=null){
				sql.append("WHERE vmovEntrada.dataEntrada BETWEEN :dataInicial AND :dataFinal ");
			
			
			consulta.setDate("dataInicial", filter.getDataInicial());
			consulta.setDate("dataFinal", filter.getDataFinal());
			}
			movEntrada = consulta.list();
		}catch(RuntimeException e ){
			throw e;
		}finally{
			sessao.close();
		}
		return movEntrada;
	}
	
}
