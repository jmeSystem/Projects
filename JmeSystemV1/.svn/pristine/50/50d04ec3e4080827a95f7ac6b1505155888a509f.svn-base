package br.com.jmesystemv1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jmesystemv1.domain.JmeCidade;
import br.com.jmesystemv1.util.HibernateUtil;

public class JmeCidadeDAO {

	@SuppressWarnings("unchecked")
	public List<JmeCidade> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<JmeCidade> cidade = null;

		try {
			Query consulta = sessao.getNamedQuery("JmeCidade.listar");
			cidade = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;

		} finally {
			sessao.close();
		}
		return cidade;
	}	
}
