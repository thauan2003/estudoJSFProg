/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Util;

/**
 *
 * @author Danilo
 */
public class MensagensErros {
    private static String msgErro;
    private static boolean erro;

    public static boolean isErro() {
        return erro;
    }

    public static void setErro(boolean erro) {
        MensagensErros.erro = erro;
    }
    
    public static String getMsgErro() {
        return msgErro;
    }

    public static void setMsgErro(String msgErro) {
        MensagensErros.msgErro = msgErro;
    }

    /**
     * Método utilizado para realizar os tratamentos de campos requeridos.
     * @param campo Nome do Campo que vai aparecer na Mensagem: "É necessário preencher o campo: + campo"
     */
    public static void setMsgCampoRequerido(String campo){
        setMsgErro("É necessário preencher o campo: " + campo);
    }
    /**
     *
     * Método utilizado para fazer o tratamento de ConstrainViolation, ou seja método utilizado para tratar o erro que é gerado
     * ao tentar excluir um registro que possui filhos e que não pode ser excluido para não deixar os registros filhos sem ligação.
     *
     * Esse método deve ser tratado com o catch(org.hibernate.exception.ConstraintViolationException)
     */
    public static void erroConstrain() {
        setErro(true);
        setMsgErro("Tentou excluir um registro que possui registros dependentes. Para Apagar este registro deve-se apagar os registros dependentes.");
    }

    /**
     * Método utilizado para tratar o NullPointer.
     *
     * Esse método deve ser tratado com o catch(NullPointerException)
     */
    public static void erroNullPointer() {
        setErro(true);
        setMsgErro("Ocorreu um erro interno no servidor.");
        //setMsgErro("Null Pointer Exception.");
    }

    /**
     * Método utilizado para tratar o TimeOut que é gerado ao tentar excluir algum registro que possui registros filhos,
     *  pode ser gerado um TimeOut por outras razões também.
     *
     * Esse método deve ser tratado com o catch(org.hibernate.exception.GenericJDBCException)
     */
    public static void erroHibernateJdbc(){
        setErro(true);
        //setMsgErro("Tempo limite de tentativa de manipulação de dados excedida.");
        setMsgErro("Tentou excluir um registro que possui registros dependentes. Para Apagar este registro deve-se apagar os registros dependentes.");
        //setMsgErro("TimeOut Exception.");
    }

    /**
     * Método utilizado para tratar um erro que ocorre quando tenta se alterar/salvar um objeto que está sendo manipulado por outra
     *  sessão, gerando assim um erro que é genérico do Hibernate.
     *
     * Esse método deve ser tratado com o catch(org.hibernate.HibernateException)
     * 
     * Observações: Cuidado ao colocar esse catch ele pode capturar todas as exceptions do Hibernate, caso possua outras exceptions mais
     *  especificas deixar esta por ultimo para não atrapalhar nas outras.
     */
    public static void erroHibernate(){
        setErro(true);
        setMsgErro("Ocorreu um erro interno no servidor enquanto se comunicava com o banco de dados");
        //setMsgErro("Erro generico do Hibernate");
    }

    /**
     * Método utilizado para tratar os erros de maneira generica.
     *
     * Esse método deve ser tratado com o catch(Exception)
     */
    public static void erroGenericos(){
        setErro(true);
        setMsgErro("Ocorreu um erro ao tentar realizar está ação.");
        //setMsgErro("Erro generico");
    }
}