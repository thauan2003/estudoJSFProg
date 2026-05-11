package ManagedBeans;

import com.Bean.Auditoria;
import com.Bean.Setor;
import com.Bean.Usuario;
import com.HibernateUtil.HibernateUtil;
import com.Util.ConexaoMySQL;
import com.Util.jcUtil;
import com.Util.metodoUtil;
import org.hibernate.Session;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Guilherme JC - 17-05-2019
 */
@Named
@SessionScoped
public class UtilFaces implements Serializable {
   private String contexto = "/ProjetoTreinamento";
   private Setor setorLogadoBean;
   private Usuario usuarioLogadoBean;

   public Setor getSetorLogadoBean() {
      return setorLogadoBean;
   }

   public void setSetorLogadoBean(Setor setorLogadoBean) {
      this.setorLogadoBean = setorLogadoBean;
   }

   public Usuario getUsuarioLogadoBean() {
      return usuarioLogadoBean;
   }

   public void setUsuarioLogadoBean(Usuario usuarioLogadoBean) {
      this.usuarioLogadoBean = usuarioLogadoBean;
   }

   public String getContexto() {
      return contexto;
   }

   public void setContexto(String contexto) {
      this.contexto = contexto;
   }

   private void fecharConexao(Connection con) {
      try {
         if (con != null) {
            con.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public static void adicionarLogRel(Map parametros, String rel) {
      int posto = new jcUtil().getSetorLogadoBean().getSetCodigo();
      int usuario = new jcUtil().getUsuarioLogadoBean().getUsuCodigo();
      String parametro = "";
      Collection ex = parametros.values();
      Object hist = "Imprimiu Relatório " + rel + " com os Parametros: ";
      List<String> list = new ArrayList<String>(parametros.values());
      for (Object a : list) {
         if (a == null) {
            parametro = "";
         } else {
            parametro = a.toString();
         }
         hist = hist + " , " + parametro;
      }

      Session ses = HibernateUtil.getSessionFactory().openSession();
      try {
         java.util.Date data = new Date();
         Calendar cal = Calendar.getInstance();
         String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
         String mac = jcUtil.getSessao("mac").toString();
         Auditoria aud = new Auditoria(posto, usuario);
         aud.setAudData(data);
         aud.setAudHora(hora);
         aud.setAudMac(mac);
         aud.setAudHistorico(hist + " IP: " + jcUtil.getSessao("IP") + " MAC: " + mac);

         ses.save(aud);
         ses.beginTransaction().commit();
      } catch (Exception e) {
      } finally {
         ses.close();
      }
   }
}
