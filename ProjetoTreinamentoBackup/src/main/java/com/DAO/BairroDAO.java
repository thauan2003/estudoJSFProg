package com.DAO;

import com.Bean.Bairro;
import com.Bean.Logradouro;
import com.HibernateUtil.HibernateUtil;
import com.Util.MensagensErros;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class BairroDAO extends GenericDAO{
    private static final long serialVersionUID = 1L;

    private Session session;

    public BairroDAO(Session session) {
        this.session = session;
    }

    public BairroDAO() {
        this.session = getSession();
    }

    public int addBairro(Bairro bairro) {
        setAtualizar(false);
        saveOrUpdatePojo(bairro);
        if (MensagensErros.isErro()) {
            return 0;
        } else {
            return bairro.getCodigoBairro();
        }
    }

    public void updateBairro(Bairro bairro) {
        setAtualizar(true);
        saveOrUpdatePojo(bairro);
    }

    public Bairro getBairro(int bairroID) {
        return getPojo(Bairro.class, bairroID);
    } //Buscar por Código

    public List<Bairro> getBairros() {
        return getPureList(Bairro.class, "from Bairro ar");
    } //Listar Todos

    public List<Bairro> getBairroByClausula(String clausula) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "select * from bairro b where " + clausula;
            Query q = ses.createNativeQuery(sql).addEntity(Bairro.class);
            return q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ses.close();
        }
        return new ArrayList<>();
    } //Buscar por Termo

    public void removeBairro(Bairro bairro) {
        removePojo(bairro);
    }

}
