/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.HibernateUtil;

import com.Util.ConexaoMySQL;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Carlos Essa classe é a responsável de carregar o engine do Hibernate
 * inteiro afim de nao deixar isso para o servidor escolher, mas sim
 * dinamicamente por demanda, só quando foi util abrir e quando para fechar,
 * instanciando a conexao com designe patterns singletown
 * <p>
 * Ela é uma unica instancia da classe para o application server inteiro a carga
 * toda é carregada uma unica vez.
 * <p>
 * <p>
 * <p>
 * Este é um bom exemplo do meu singleTown
 */
public class HibernateUtil implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static String ip;
    private static String banco;
    private static String usuario;
    private static String senha;

    public HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        ip = ConexaoMySQL.getServerName();
        banco = ConexaoMySQL.getMydatabase();
        usuario = ConexaoMySQL.getUsername();
        senha = ConexaoMySQL.getPassword();
        Configuration conf = new Configuration().configure("hibernate.cfg.xml");
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return conf.setProperty("hibernate.hikari.dataSource.url", "jdbc:mysql://" + ip + ":3306/" + banco)
                    .setProperty("hibernate.hikari.dataSource.user", usuario)
                    .setProperty("hibernate.hikari.dataSource.password", senha).buildSessionFactory();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory.isClosed()) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
