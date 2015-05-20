package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jmesystemv1.domain.JmeFuncionario;
import br.com.jmesystemv1.util.HibernateUtil;
import br.com.jmesystemv1.util.JSFUtil;

public class JmeFuncionarioDAO {

	public void salvar(JmeFuncionario funcionario) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(funcionario);
			transacao.commit();

		} catch (RuntimeException ex) {

			// se houver erro e a transa��o n�o for null, defaz a transa��o
			if (transacao != null) {
				transacao.rollback();
				
				JSFUtil.adicionarMensagemErro("Erro ao inserir"+ex.getMessage());
			}
		} finally {
			sessao.close();
		}
	}

	/**
	 * M�todo responsavel por listar todos os funcionarios
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<JmeFuncionario> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeFuncionario> funcionarios = null;

		try {
			Query consulta = sessao.getNamedQuery("JmeFuncionario.listar");
			funcionarios = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return funcionarios;
	}

	/**
	 * M�todo Responsavel por excluir um funcionario selecionado
	 * 
	 * @param codigo
	 */
	/** Metodo Excluir */
	public void excluir(JmeFuncionario funcionario) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			sessao.delete(funcionario);
			transacao.commit(); // confirma a transa��o
		} catch (RuntimeException ex) {
			// se for diferente de null e desfaz toda trasa��o
			if (transacao != null) {
				transacao.rollback();// desfazer toda a transa��o
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}

	public void editar(JmeFuncionario funcionarios) { // throws Exception quem
														// chamar
		// esse metodo vai ser obrigado
		// a tratar o erro

		// pegar uma sess�o para conseguir fazer as opera��es no banco de dados

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // quando for start a transa��o pode dar
		// erro

		try {

			transacao = sessao.beginTransaction();
			sessao.update(funcionarios);
			transacao.commit(); // confirma a transa��o
		} catch (RuntimeException ex) {
			// se for diferente de null e desfaz toda trasa��o
			if (transacao != null) {
				transacao.rollback();// desfazer toda a transa��o
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}

	public JmeFuncionario buscarPorCodigo(Integer codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeFuncionario funcionario = null;

		try {
			Query consulta = sessao
					.getNamedQuery("JmeFuncionario.buscarPorCodigo");
			consulta.setInteger("idFuncionario", codigo);

			funcionario = (JmeFuncionario) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return funcionario;
	}
	
	public JmeFuncionario autenticar(String cpf, String senha){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		JmeFuncionario funcionario=null;
		
		try{
			Query consulta = sessao.getNamedQuery("JmeFuncionario.autenticar");
			consulta.setString("cpf", cpf);
			consulta.setString("senha", senha);
			
			funcionario = (JmeFuncionario) consulta.uniqueResult();
		}catch(RuntimeException e){
			throw e;
		}finally{
			sessao.close();
		}
		return funcionario;
	}

}