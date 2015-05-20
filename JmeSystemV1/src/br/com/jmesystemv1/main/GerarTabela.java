package br.com.jmesystemv1.main;

import br.com.jmesystemv1.util.HibernateUtil;

public class GerarTabela {

	public static void main(String[] args) {
		/*Abrindo e fechando conexão com o SessionFactory*/
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();

	}

}
