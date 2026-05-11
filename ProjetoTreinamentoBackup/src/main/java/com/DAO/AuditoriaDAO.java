package com.DAO;

import com.Bean.Auditoria;
import com.Util.MensagensErros;
import org.hibernate.Session;

import java.util.List;

/**
 *
 * @author Guilherme JC - 28-08-2020
 */
public class AuditoriaDAO extends GenericDAO {
    private static final long serialVersionUID = 1L;
    private Session session;
	
    public AuditoriaDAO(Session session) {
        this.session = session;
    }

    public AuditoriaDAO() {
        this.session = getSession();
    }
    public int addAuditoria(Auditoria auditoria) {
        setAtualizar(false);
            saveOrUpdatePojo(auditoria);
            if (MensagensErros.isErro()) {
                return 0;
            } else {
                return auditoria.getAudCodigo();
            }   
    }
    public Auditoria getAuditoria(int auditoriaID) {
        return getPojo(Auditoria.class, auditoriaID);
    }
    public void updateAuditoria(Auditoria auditoria) {
        setAtualizar(true);
        saveOrUpdatePojo(auditoria);
    }
    public void removeAuditoria(Auditoria auditoria) {
        removePojo(auditoria);
    }
    public List<Auditoria> getAuditorias() {
        return getPureList(Auditoria.class, "from Auditoria ar");
    }
}