package com.DAO;

import com.Bean.Logradouro;
import com.HibernateUtil.HibernateUtil;
import com.Util.MensagensErros;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class LogradouroDAO extends GenericDAO {
    private static final long serialVersionUID = 1L;
    private Session session;

    public LogradouroDAO(Session session) {
        this.session = session;
    }

    public LogradouroDAO() {
        this.session = getSession();
    }

    public int addLogradouro(Logradouro logradouro) {
        setAtualizar(false);
        saveOrUpdatePojo(logradouro);
        if (MensagensErros.isErro()) {
            return 0;
        } else {
            return logradouro.getCodigoLogra();
        }
    }

    public Logradouro getLogradouro(int logradouroID) {
        return getPojo(Logradouro.class, logradouroID);
    }

    public void updateLogradouro(Logradouro logradouro) {
        setAtualizar(true);
        saveOrUpdatePojo(logradouro);
    }

    public void removeLogradouro(Logradouro logradouro) {
        removePojo(logradouro);
    }

    public List<Logradouro> getLogradouros() {
        return getPureList(Logradouro.class, "from Logradouro ar");
    }

    public List<Logradouro> getLogradouroByClausula(String clausula) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "select * from logradouro l where " + clausula;
            Query q = ses.createNativeQuery(sql).addEntity(Logradouro.class);
            return q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ses.close();
        }
        return new ArrayList<>();
    }


}
