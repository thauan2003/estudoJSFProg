/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.Bean.Auditoria;
import com.HibernateUtil.HibernateUtil;
import com.Util.MapJC;
import com.Util.MensagensErros;
import com.Util.PropertyChange;
import com.Util.jcUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.spi.SQLExceptionConverter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Carlos Mazzi Classe abstrata para métodos genéricos dentro de nossa
 * aplicaçao Fenix.
 * <p>
 * Data:16/09/2009 09:20 AM
 * <p>
 * #Atualização: Danilo {
 * <p>
 * Data Indefinida - Adcionado auditoria ao realizar o CRUD.
 * <p>
 * 19/07/2010 - Adcionado tratamento de Exceções basicas no CRUD.
 * <p>
 * }
 */
public abstract class GenericDAO {

   private static final long serialVersionUID = 1L;
   protected boolean atualizar;

   protected Session getSession() {

      return HibernateUtil.getSessionFactory().openSession();
   }

   protected void saveOrUpdatePojo(Serializable pojo) {
      Session ses = getSession();
      try {
         Transaction trs = ses.beginTransaction();
         saveOrUpdatePojo(ses, pojo);
         trs.commit();
         ses.refresh(pojo);
         MensagensErros.setErro(false);
      } catch (org.hibernate.exception.ConstraintViolationException e) {
         e.printStackTrace();
         MensagensErros.erroConstrain();
      } catch (NullPointerException e) {
         e.printStackTrace();
         MensagensErros.erroNullPointer();
      } catch (org.hibernate.exception.GenericJDBCException e) {
         e.printStackTrace();
         MensagensErros.erroHibernateJdbc();
      } catch (org.hibernate.HibernateException e) {
         e.printStackTrace();
         MensagensErros.erroHibernate();
      } catch (Exception e) {
         e.printStackTrace();
         MensagensErros.erroGenericos();
      } finally {
         ses.close();
      }
   }

   /**
    * Guilherme 12/3/2019.
    *
    * @param ses
    * @param pojo
    * @throws ClassNotFoundException
    * @throws IllegalAccessException
    */
   protected void saveOrUpdatePojo(Session ses, Serializable pojo)  {
      Object classe = pojo.getClass().getName();
      JSONArray jsonArray = null;
      try {
         Class<?> thisClass = Class.forName(pojo.getClass().getName());
         Field[] campos = thisClass.getDeclaredFields();
         Object valuePrimayKey = null;
         for (Field field : campos) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Id.class)) {
               valuePrimayKey = field.get(pojo);
               break;
            }
         }

         if (valuePrimayKey != null) {
            setAtualizar(true);
            Method method = null;
            try {
               method = thisClass.getDeclaredMethod("getPropertyChange", new Class[]{});
               if(method != null) {
                  method.setAccessible(true);
                  Object objProperty = method.invoke(pojo, new Object[]{});
                  if (objProperty != null) {
                     PropertyChange propertyChange = (PropertyChange) objProperty;
                     JSONObject jsonObject = null;
                     jsonArray = new JSONArray();
                     for (MapJC campoAlterado : propertyChange.getListCamposAlterados()) {
                        jsonObject = new JSONObject();
                        jsonObject.put("oldValue", campoAlterado.get("oldValue"));
                        jsonObject.put("newValue", campoAlterado.get("newValue"));
                        jsonArray.put(new JSONObject().put(campoAlterado.getString("campo"), jsonObject));
                     }
                  }
               }
            } catch (IllegalArgumentException | NoSuchMethodException | InvocationTargetException | JSONException e) {
//               e.printStackTrace();
            }
         }
      } catch (ClassNotFoundException | IllegalAccessException ex) {
//         ex.printStackTrace();
      }

      String hist;
      classe = classe.toString().replace("com.Bean.", "");
      try {
         ses.saveOrUpdate(pojo);
         Object reg = pojo.toString();
         if (isAtualizar()) {
            hist = "Alterou um registro na tabela: " + classe + ". Registro: " + reg;
         } else {
            hist = "Inseriu um registro na tabela: " + classe + ". Registro: " + reg;
         }

         try {
            jcUtil.adicionarLogSession(ses, new jcUtil().getSetorLogadoBean().getSetCodigo(), new jcUtil().getUsuarioLogadoBean().getUsuCodigo(), hist, jsonArray);
         } catch (NullPointerException xx) {

         }
      } catch (org.hibernate.exception.ConstraintViolationException e) {
         e.printStackTrace();
         MensagensErros.erroConstrain();
         throw e;
      } catch (NullPointerException e) {
         e.printStackTrace();
         MensagensErros.erroNullPointer();
         throw e;
      } catch (org.hibernate.exception.GenericJDBCException e) {
         e.printStackTrace();
         MensagensErros.erroHibernateJdbc();
         throw e;
      } catch (org.hibernate.HibernateException e) {
         e.printStackTrace();
         MensagensErros.erroHibernate();
         throw e;
      } catch (Exception e) {
         e.printStackTrace();
         MensagensErros.erroGenericos();
         throw e;
      }
   }

   protected <T extends Serializable> T getPojo(Class<T> classToSearch, Serializable key) {
      Session ses = getSession();
      try {
         ses.getTransaction().begin();
         Serializable toReturn = (Serializable) ses.get(classToSearch, key);
         ses.getTransaction().commit();
         return (T) toReturn;
      } finally {
         ses.close();
      }
   }

   protected void removePojo(Serializable pojoToRemove) {
      Session ses = getSession();
      try {
         ses.getTransaction().begin();
         removePojo(ses, pojoToRemove);
         ses.getTransaction().commit();
         MensagensErros.setErro(false);
      } catch (org.hibernate.exception.ConstraintViolationException e) {
         e.printStackTrace();
         MensagensErros.erroConstrain();
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", MensagensErros.getMsgErro());
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage("removePojo", msg);
      } catch (NullPointerException e) {
         e.printStackTrace();
         MensagensErros.erroNullPointer();
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", MensagensErros.getMsgErro());
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage("removePojo", msg);
      } catch (org.hibernate.exception.GenericJDBCException e) {
         e.printStackTrace();
         MensagensErros.erroHibernateJdbc();
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", MensagensErros.getMsgErro());
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage("removePojo", msg);
      } catch (org.hibernate.HibernateException e) {
         e.printStackTrace();
         MensagensErros.erroHibernate();
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", MensagensErros.getMsgErro());
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage("removePojo", msg);
      } catch (Exception e) {
         e.printStackTrace();
         MensagensErros.erroGenericos();
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", MensagensErros.getMsgErro());
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage("removePojo", msg);
      } finally {
         ses.close();
      }
   }

   /**
    * Guilherme 12/03/2019
    *
    * @param ses
    * @param pojoToRemove
    */
   protected void removePojo(Session ses, Serializable pojoToRemove) {
      Object classe = pojoToRemove.getClass().getName();
      Object reg = pojoToRemove.toString();
      classe = classe.toString().replace("com.Bean.", "");
      String hist = "Apagou um registro na tabela: " + classe + ". Registro: " + reg;

      ses.delete(pojoToRemove);
      jcUtil.adicionarLogSession(ses, new jcUtil().getSetorLogadoBean().getSetCodigo(), new jcUtil().getUsuarioLogadoBean().getUsuCodigo(), hist);
      MensagensErros.setErro(false);
   }

   protected <T extends Serializable> List<T> getPureList(Class<T> classtoCast, String query, Object... params) {
      Session ses = getSession();
      try {
         ses.getTransaction().begin();
         Query qr = ses.createQuery(query);
         for (int i = 1; i <= params.length; i++) {
            qr.setParameter(i - 1, params[i - 1]);
         }
         @SuppressWarnings("unchecked")
         List<T> toReturn = qr.list();

         ses.getTransaction().commit();
         return toReturn;
      } catch (Exception x) {
         return null;
      } finally {
         ses.close();
      }

   }

   protected Serializable getPurePojo(String query, Object... params) {
      Session ses = getSession();
      try {
         ses.getTransaction().begin();
         Query qr = ses.createQuery(query);
         for (int i = 1; i <= params.length; i++) {
            qr.setParameter(i, params[i - 1]);
         }
         Object toReturn = qr.uniqueResult();

         ses.getTransaction().commit();


         MensagensErros.setErro(false);
         return (Serializable) toReturn;
      } catch (org.hibernate.exception.ConstraintViolationException e) {

         MensagensErros.erroConstrain();
         return null;
      } catch (NullPointerException e) {

         MensagensErros.erroNullPointer();
         return null;
      } catch (org.hibernate.exception.GenericJDBCException e) {

         MensagensErros.erroHibernateJdbc();
         return null;
      } catch (org.hibernate.HibernateException e) {

         MensagensErros.erroHibernate();
         return null;
      } catch (Exception e) {

         MensagensErros.erroGenericos();
         return null;
      } finally {
         ses.close();
      }
   }

   /**
    * Função de Adcionar log criada por: Danilo - 25/03/2010
    * <p>
    * Este método recebe como parametros o setor e o usuario logado no sistema
    * e também o Histórico. Instancio um objeto do tipo Bean de LogUser e
    * atribuo os valores e em seguida insiro no banco utilizando o comando
    * save();
    * <p>
    * Tudo isso é realizado dentro de um bloco Try, no finally é realizado um
    * commit() e fechada a sessão.
    *
    * @param setor     Setor do usuario logado no sistema
    * @param usuario   Usuario logado no sistema
    * @param historico Ação que foi realizada pelo Usuario
    */
   protected void adicionarLog(int setor, int usuario, String historico) {
      Session ses = getSession();

      try {
         ses.getTransaction().begin();
         Date data = new Date();
         Calendar cal = Calendar.getInstance();
         String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
         String mac = jcUtil.getSessao("mac").toString();
         Auditoria aud = new Auditoria(setor, usuario);
         aud.setAudData(data);
         aud.setAudHora(hora);
         aud.setAudMac(mac);
         aud.setAudHistorico(historico + " IP: " + jcUtil.getSessao("IP") + " MAC: " + mac);

         ses.save(aud);
      } finally {
         ses.getTransaction().commit();
         ses.close();
         setAtualizar(false);
      }

   }

   protected boolean isAtualizar() {
      return atualizar;
   }

   protected void setAtualizar(boolean atualizar) {
      this.atualizar = atualizar;
   }

   /**
    * Danilo - 26/03/2010
    * <p>
    * Método criado para verificar se o objeto já existe antes de inseri-lo no
    * banco Exemplo: param classe = Produto e param clausula = obj.proNome =
    * 'GILETE' Com isso o método verifica se já existe algum Produto com o Nome
    * GILETE no banco e retona o Objeto ou Null
    *
    * @param classe   Nome da Classe a ser verificada
    * @param clausula Clausula de verificação por padrao o prefixo adotado por
    *                 todos os campos devem ser obj
    * @return Retorna um Objeto ou Null
    */
   protected Object jaExiste(String classe, String clausula) {
      Session ses = getSession();

      try {
         Query q = ses.createQuery("SELECT obj FROM " + classe + " obj WHERE " + clausula).setMaxResults(1);
         return q.uniqueResult();
      } finally {
         ses.close();
      }
   }

   protected Object jaExiste(Session ses, String classe, String clausula) {
      try {
         Query q = ses.createQuery("SELECT obj FROM " + classe + " obj WHERE " + clausula).setMaxResults(1);
         return q.uniqueResult();
      }catch (Exception ex){
         ex.printStackTrace();
      }
      return null;
   }
}
