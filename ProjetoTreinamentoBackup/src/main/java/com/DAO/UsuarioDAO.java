package com.DAO;

import com.Bean.Usuario;
import com.HibernateUtil.HibernateUtil;
import com.Util.MensagensErros;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme JC - 14-09-2020
 */
public class UsuarioDAO extends GenericDAO {
    private static final long serialVersionUID = 1L;
    private Session session;
	
    public UsuarioDAO(Session session) {
        this.session = session;
    }

    public UsuarioDAO() {
        this.session = getSession();
    }

    public int addUsuario(Usuario usuario) {
        setAtualizar(false);
            saveOrUpdatePojo(usuario);
            if (MensagensErros.isErro()) {
                return 0;
            } else {
                return usuario.getUsuCodigo();
            }   
    }

    public Usuario getUsuario(int usuarioID) {
        return getPojo(Usuario.class, usuarioID);
    }

    public void updateUsuario(Usuario usuario) {
        setAtualizar(true);
        saveOrUpdatePojo(usuario);
    }

    public void removeUsuario(Usuario usuario) {
        removePojo(usuario);
    }

    public List<Usuario> getUsuarios() {
        return getPureList(Usuario.class, "from Usuario al");
    }

    public List<Usuario> getUsuarioByClausula(String clausula){
        Session ses = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "select * from usuario u where " + clausula;
            Query q = ses.createNativeQuery(sql).addEntity(Usuario.class);
            return q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ses.close();
        }
        return new ArrayList<>();
    }

}