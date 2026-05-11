package com.Util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mazzi
 */
public class ConexaoMySQL {

    /**
    * Creates a new instance of ConexaoMySQL
    */
    public static String status = "Não conectou...";
    private static String Path = null;
    private static Connection connection = null;
    private static String serverName = "localhost";
    private static String mydatabase = "treinamento";
    private static String username = "root";
    private static String password = "deus";
    private static String cidade = "Cidade";
    private static String cnsDireto = "nao";
    private static String comSeguranca = "nao";     //sim     //nao
    private static String servidorCns = "http://192.168.0.200:8080";
    private static String serverPort = "8080";
    
    
    private static String maestroProtocol = "http"; // http ou https
    private static String maestroServer = "localhost"; //ip ou dns do servidor
    private static String maestroPort = ":8085"; // porta caso precise tem que ter os : ex = :8080
    private static HikariDataSource hikariDataSource = null;

    public static String getMaestroProtocol() {
        return maestroProtocol;
    }

    public static void setMaestroProtocol(String maestroProtocol) {
        ConexaoMySQL.maestroProtocol = maestroProtocol;
    }

    public static String getMaestroServer() {
        return maestroServer;
    }

    public static void setMaestroServer(String maestroServer) {
        ConexaoMySQL.maestroServer = maestroServer;
    }

    public static String getMaestroPort() {
        return maestroPort;
    }

    public static void setMaestroPort(String maestroPort) {
        ConexaoMySQL.maestroPort = maestroPort;
    }
    
    public static String getServerPort() {
        return serverPort;
    }

    public static void setServerPort(String serverPort) {
        ConexaoMySQL.serverPort = serverPort;
    }
    
    public static String getComSeguranca() {
        return comSeguranca;
    }

    public static void setComSeguranca(String comSeguranca) {
        ConexaoMySQL.comSeguranca = comSeguranca;
    }
    
    public static String getCidade() {
        return cidade;
    }

    public static void setCidade(String cidade) {
        ConexaoMySQL.cidade = cidade;
    }
    
    public static String getCnsDireto() {
        return cnsDireto;
    }

    public static void setCnsDireto(String cnsDireto) {
        ConexaoMySQL.cnsDireto = cnsDireto;
    }

    public static String getServidorCns() {
        return servidorCns;
    }

    public static void setServidorCns(String servidorCns) {
        ConexaoMySQL.servidorCns = servidorCns;
    }
    
    public static String getServerName() {
        return serverName;
    }

    public static void setServerName(String serverName) {
        ConexaoMySQL.serverName = serverName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ConexaoMySQL.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ConexaoMySQL.username = username;
    }

    public static String getMydatabase() {
        return mydatabase;
    }

    public static void setMydatabase(String mydatabase) {
        ConexaoMySQL.mydatabase = mydatabase;
    }

    public static String getPath() {
        return Path;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        ConexaoMySQL.connection = connection;
    }

    public static void setPath(String Path) {
        ConexaoMySQL.Path = Path;
    }

    public ConexaoMySQL() {
    }

    public static Connection getConexaoMySQL() {
        try {
            if (hikariDataSource == null) {
                HikariConfig config = new HikariConfig();
                config.setMaximumPoolSize(40000);
                config.setMinimumIdle(300);
                config.setLeakDetectionThreshold(190000);
                config.setConnectionTimeout(30000);
                config.setIdleTimeout(0);
                config.setAutoCommit(true);

                config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
                config.addDataSourceProperty("serverName", serverName);
                config.addDataSourceProperty("port", "3306");
                config.addDataSourceProperty("databaseName", mydatabase);
                config.addDataSourceProperty("user", username);
                config.addDataSourceProperty("password", password);
                //config.addDataSourceProperty("useSSL", false);

                hikariDataSource = new HikariDataSource(config);
                return hikariDataSource.getConnection();
            } else {
                return hikariDataSource.getConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Connection getConexaoMySQL2() {
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase; // a JDBC url
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        } catch (InstantiationException ie) {
            System.out.println("Erro InstantiationException.");
            return null;
        } catch (IllegalAccessException ia) {
            System.out.println("Erro IllegalAccessException.");
            return null;
        }
    }

    public static Connection getConexaoMySQLOld() {

        if (connection != null) {
            try {
                if (connection.isClosed()) {
                    try {
                        connection = null;
                        // Carregando o JDBC Driver
                        String driverName = "com.mysql.jdbc.Driver"; // MySQL MM JDBC driver
                        Class.forName(driverName);

                        // Criando a conexÃ£o com o Banco de Dados
                        String url = "jdbc:mysql://" + serverName + "/" + mydatabase; // a JDBC url

                        connection = DriverManager.getConnection(url, username, password);

                        if (connection != null) {
                            status = ("STATUS--->Conectado com sucesso!");
                        } else {
                            status = ("STATUS--->NÃ£o foi possivel habilitar a conexÃ£o");
                        }

                        return connection;

                    } catch (ClassNotFoundException e) {
                        //Driver nÃ£o encontrado
                        System.out.println("O driver expecificado nao foi encontrado.");
                        return null;
                    } catch (SQLException e) {
                        //NÃ£o estÃ¡ conseguindo se conectar ao banco
                        System.out.println("Nao foi possivel conectar ao Banco de Dados.");
                        return null;
                    }
                } else {
                    return connection;
                }
            } catch (Exception ex) {

                return null;
            }
        } else {
            try {
                // Carregando o JDBC Driver
                String driverName = "com.mysql.jdbc.Driver"; // MySQL MM JDBC driver
                Class.forName(driverName);

                // Criando a conexÃ£o com o Banco de Dados
                String url = "jdbc:mysql://" + serverName + "/" + mydatabase; // a JDBC url
                connection = DriverManager.getConnection(url, username, password);

                if (connection != null) {
                    status = ("STATUS--->Conectado com sucesso!");
                } else {
                    status = ("STATUS--->NÃ£o foi possivel habilitar a conexÃ£o");
                }

                return connection;

            } catch (ClassNotFoundException e) {
                //Driver nÃ£o encontrado
                System.out.println("O driver expecificado nao foi encontrado.");
                return null;
            } catch (SQLException e) {
                //NÃ£o estÃ¡ conseguindo se conectar ao banco
                System.out.println("Nao foi possivel conectar ao Banco de Dados.");
                return null;
            }
        }
    }

    public static String statusConection() {
        return status;
    }

    public static boolean FecharConexao() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
                return true;
            } else {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static Connection ReiniciarConexao() {
        FecharConexao();

        return ConexaoMySQL.getConexaoMySQL();
    }

    public static boolean AdicinarLog(String vData,
            String vHora, int vUsuario, String vHistorico) throws SQLException {
        Connection con = ConexaoMySQL.getConexaoMySQL();
        try {
            Statement st_store = (Statement) con.createStatement();
            st_store.execute("Call AddLog('" + vData + "',CURTIME()," + vUsuario
                    + ",'" + vHistorico + "')");
            return true;
        } catch (SQLException erro) {
            ConexaoMySQL.FecharConexao();
            return false;
        } finally {
            con.close();
        }
    }

    public static boolean verificarNivelAcesso(String paramTela) throws SQLException {
        String usu = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario").toString();
        Connection con = ConexaoMySQL.getConexaoMySQL();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select " + paramTela + " from nivelacesso , usuario where usunivcodigo = nivcodigo and usucodigo = " + usu);
            rs.next();

            if (rs.getString(paramTela).equals("N") == true) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {

            return false;
        }finally{
            con.close();
        }
    }

}
