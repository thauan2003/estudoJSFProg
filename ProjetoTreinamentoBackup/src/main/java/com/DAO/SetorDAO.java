package com.DAO;

import com.Bean.Setor;
import com.Util.MensagensErros;
import org.hibernate.Session;

import java.util.List;

/**
 *
 * @author Guilherme JC - 14-09-2020
 */
public class SetorDAO extends GenericDAO {
    private static final long serialVersionUID = 1L;
    private Session session;
	
    public SetorDAO(Session session) {
        this.session = session;
    }


    public SetorDAO() {
        this.session = getSession();
    }


    public int addSetor(Setor setor) {
        setAtualizar(false);
            saveOrUpdatePojo(setor);
            if (MensagensErros.isErro()) {
                return 0;
            } else {
                return setor.getSetCodigo();
            }   
    }


    public Setor getSetor(int setorID) {
        return getPojo(Setor.class, setorID);
    }


    public void updateSetor(Setor setor) {
        setAtualizar(true);
        saveOrUpdatePojo(setor);
    }
    public void removeSetor(Setor setor) {
        removePojo(setor);
    }


    public List<Setor> getSetors() {
        return getPureList(Setor.class, "from Setor ar");
    }

    public List<Setor> getSetorRecebePlano() {
        return getPureList(Setor.class, "from Setor ar where ar.setRecConPlanoAcao = true");
    }
}