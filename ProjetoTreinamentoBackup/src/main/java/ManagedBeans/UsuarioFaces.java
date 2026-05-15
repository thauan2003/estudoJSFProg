package ManagedBeans;

import com.Bean.*;
import com.Bean.Usuario;
import com.DAO.LogradouroDAO;
import com.DAO.SetorDAO;
import com.DAO.UsuarioDAO;
import com.HibernateUtil.HibernateUtil;
import com.Util.MensagensErros;
import com.Util.jcUtil;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.Session;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Guilherme JC - 14-09-2020
 */
@Named
@SessionScoped
public class UsuarioFaces implements Serializable {

   private List<Usuario> cachedUsuarios = null;
   private UsuarioDAO usuarioDAO = new UsuarioDAO();
   private Usuario usuarioSelected;
   private List<Usuario> listUsuarios = null;
   private List<Usuario> selectedUsuarios;
   private Usuario usuario;
   private Setor setor;
   private String chave;
   private String include = "/WEB-INF/template.xhtml";
   private SelectItem[] optionsSearchUsuario = {
         new SelectItem("1", "Nome"),
         new SelectItem("2", "Código"),
         new SelectItem("3", "Listar Todos")
   };
   private String txtUsuarioSearch = null;
   private String optionSearchSelected;
   @Inject
   private UtilFaces utilFaces;



   public List<Usuario> getSelectedUsuarios() {
      return selectedUsuarios;
   }

   public void setSelectedUsuarios(List<Usuario> selectedUsuarios) {
      this.selectedUsuarios = selectedUsuarios;
   }

   public String getTxtUsuarioSearch() {
      return txtUsuarioSearch;
   }

   public void setTxtUsuarioSearch(String txtUsuarioSearch) {
      this.txtUsuarioSearch = txtUsuarioSearch;
   }

   public String getOptionSearchSelected() {
      return optionSearchSelected;
   }

   public void setOptionSearchSelected(String optionSearchSelected) {
      this.optionSearchSelected = optionSearchSelected;
   }

   public SelectItem[] getOptionsSearchUsuario() {
      return optionsSearchUsuario;
   }

   public void setOptionsSearchUsuario(SelectItem[] optionsSearchUsuario) {
      this.optionsSearchUsuario = optionsSearchUsuario;
   }

   public List<Usuario> getListUsuarios() {
      return listUsuarios;
   }

   public void setListUsuarios(List<Usuario> listUsuarios) {
      this.listUsuarios = listUsuarios;
   }

   public String getInclude() {
      return include;
   }

   public void setInclude(String include) {
      this.include = include;
   }


   public String getChave() {
      return chave;
   }

   public void setChave(String chave) {
      if (chave != null && !"".equals(chave)) {
         this.chave = chave;
      }
   }

   public Setor getSetor() {
      return setor;
   }

   public void setSetor(Setor setor) {
      this.setor = setor;
   }

   public Usuario getUsuario() {
      return usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public UsuarioDAO getUsuarioDAO() {
      return usuarioDAO;
   }

   public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
      this.usuarioDAO = usuarioDAO;
   }

   public Usuario getUsuarioSelected() {
      if (usuarioSelected == null) {
         usuarioSelected = new Usuario();
      }
      return usuarioSelected;
   }

   public void setUsuarioSelected(Usuario usuarioSelected) {
      this.usuarioSelected = usuarioSelected;
   }

   public List<Usuario> getCachedUsuarios() {
      if (cachedUsuarios == null) {
         this.cachedUsuarios = usuarioDAO.getUsuarios();
      }
      return cachedUsuarios;
   }

   public void rowSelect(SelectEvent e) {
      usuarioSelected = (Usuario) e.getObject();
   }


   public String doAddUsuario() {
//      int usuario = new jcUtil().getUsuarioLogadoBean().getUsuCodigo();
//      int setor = new jcUtil().getSetorLogadoBean().getSetCodigo();
      usuarioSelected = new Usuario();
//      listUsuarios = usuarioDAO.getUsuarios();
      return "usuario";
   }

   public String dofinishAddUsuario() {
      Date date = new Date();
      usuarioSelected.setDtaHoraAtualizacao(date);
      usuarioDAO.addUsuario(usuarioSelected);
      cachedUsuarios = null;
      if (MensagensErros.isErro()) {
         return "";
      } else {
         return "usuarioList";
      }
   }

   public String doSaveOrUpdateUsuario() {
      if (usuarioSelected.getUsuCodigo() == null) {
         dofinishAddUsuario();
      }
      dofinishUpdateUsuario();
      if (MensagensErros.isErro()) {
         return "";
      } else {
         return "usuarioList";
      }
   }

   public String doRemoveUsuario() {
      Usuario usuarioLocal = usuarioSelected;
      LogradouroDAO daoLocal = new LogradouroDAO();
      Logradouro logradouroLocal = daoLocal.getLogradouro(usuarioSelected.getUsuCodigoLogra().getCodigoLogra());
      if (logradouroLocal.getEnderecoLogra() != null){
         MensagensErros.isErro();
         return "";      }
      usuarioDAO.removeUsuario(usuarioSelected);
      cachedUsuarios = null;
      if (MensagensErros.isErro()) {
         return "";
      } else {
         return "usuarioList";
      }
   }

   public String doUpdateUsuario() {
      UsuarioDAO usuDAO = new UsuarioDAO();
      usuario = usuDAO.getUsuario(usuarioSelected.getCodUsuario());
      SetorDAO posDAO = new SetorDAO();
      setor = posDAO.getSetor(usuarioSelected.getCodSetor());
      return "usuario";
   }

   public String dofinishUpdateUsuario() {
      Date date = new Date();
      int usuario = new jcUtil().getUsuarioLogadoBean().getUsuCodigo();
      int setor = new jcUtil().getSetorLogadoBean().getSetCodigo();
      usuarioSelected.setCodUsuario(usuario);
      usuarioSelected.setCodSetor(setor);
      usuarioSelected.setDtaHoraAtualizacao(date);
      usuarioDAO.updateUsuario(usuarioSelected);
      cachedUsuarios = null;
      if (MensagensErros.isErro()) {
         return "";
      } else {
         return "usuarioList";
      }
   }

   public String doCancelUsuario() {
      cachedUsuarios = null;
      return "usuarioList";
   }

   public void doLogin() {
      if (jcUtil.isEmpty(usuarioSelected.getUsuLogin())) {
         jcUtil.setFocusCampoRequired("form:usuLogin", "Informe o Login");
//            FacesContext.getCurrentInstance().validationFailed();
//            PrimeFaces.current().ajax().update("form:erro");
         return;
      }
      if (jcUtil.isEmpty(usuarioSelected.getUsuSenha())) {
         jcUtil.setFocusCampoRequired("form:usuSenha", "Informe uma senha");
         FacesContext.getCurrentInstance().validationFailed();
         PrimeFaces.current().ajax().update("form:erro");
         return;
      }
      if (jcUtil.isEmpty(usuarioSelected.getUsuSetor())) {
         jcUtil.setFocusCampoRequired("form:usuSenha", "Informe um setor");
         FacesContext.getCurrentInstance().validationFailed();
         PrimeFaces.current().ajax().update("form:erro");
         return;
      }

      Object obj = jcUtil.getObjectQuerySQL(Usuario.class, "select * from usuario u where u.usuLogin = '" + usuarioSelected.getUsuLogin() + "' \n"
            + "and u.usuSenha = '" + usuarioSelected.getUsuSenha() + "' and u.usuSetCodigo = " + usuarioSelected.getUsuSetor() + " limit 1");
      if (obj == null) {
         jcUtil.addMensagem("Usuário, senha ou setor inválidos", FacesMessage.SEVERITY_ERROR, " ");
         FacesContext.getCurrentInstance().validationFailed();
         PrimeFaces.current().ajax().update("form:erro");
         return;
      }

      usuarioSelected = (Usuario) obj;
      new jcUtil().setUsuarioLogadoBean(usuarioSelected);
      new jcUtil().setSetorLogadoBean(usuarioSelected.getUsuSetCodigo());
//        new jcUtil().setProfissionalLogadoBean(usuarioSelected.getUsuProfissional());

//        try {
//            String sql = "select n.* from nivelacesso n inner join usuario u on u.usunivcodigo = n.nivcodigo where u.usucodigo = " + usuarioSelected.getUsuCodigo() + " limit 1";
//            NivelAcesso nivel = null;
//            Object object = jcUtil.getObjectQuerySQL(NivelAcesso.class, sql);
//            if (object != null) {
//                nivel = (NivelAcesso) object;
//            }
//            utilFaces.setNivelAcessolLogadoBean(nivel);
//        } catch (Exception ex) {
//            utilFaces.setNivelAcessolLogadoBean(null);
//        }

//        FacesContext context = FacesContext.getCurrentInstance();
//        UtilFaces bean = context.getApplication().evaluateExpressionGet(context, "#{utilFaces}", UtilFaces.class);
//        bean.doGerarMenu();
//        bean.setMenuGeral(bean.getMenu());
      try {
         FacesContext.getCurrentInstance().getExternalContext().redirect(new UtilFaces().getContexto() + "/menuSetor/menuSetor.xhtml");
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public void verificarSelecao() {
      if (usuarioSelected.getUsuCodigoLogra() != null) {
         System.out.println("Logradouro selecionado: " + usuarioSelected.getUsuCodigoLogra().getEnderecoLogra());
         System.out.println("Número que veio do banco: " + usuarioSelected.getUsuCodigoLogra().getNumeroLogra());
      } else {
         System.out.println("Objeto ainda está nulo!");
      }
   }

   public void cleanSession() {
      jcUtil.removeSessao("usuario");
      jcUtil.removeSessao("posto");
      setUsuarioSelected(null);
      getUsuarioSelected();
      new jcUtil().setSetorLogadoBean(null);
      new jcUtil().setUsuarioLogadoBean(null);
//        new jcUtil().setProfissionalLogadoBean(null);
      FacesContext fc = FacesContext.getCurrentInstance();
      HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
      session.invalidate();
      HibernateUtil.getSessionFactory().openSession().close();
   }

   public void verificaUsuarioSessao() {
      if (new jcUtil().getUsuarioLogadoBean() == null || new jcUtil().getUsuarioLogadoBean().getUsuCodigo() == null) {
         try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(new UtilFaces().getContexto() + "/login.xhtml");
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public void genesisLogin() {
      try {
         getUsuarioSelected();
         String pass = new String(new org.apache.commons.codec.binary.Base64().decode(chave.getBytes()));

         String[] parts = pass.split(":", 5);
         String usuario = parts[0];
         String senha = parts[1];
         String setor = parts[2];

         usuarioSelected.setUsuLogin(usuario);
         usuarioSelected.setUsuSenha(senha);
         usuarioSelected.setUsuSetor(Integer.parseInt(setor));
         doLogin();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void buscaUsuariosList() {
      listUsuarios = new ArrayList<>();
      if (jcUtil.isEmpty(optionSearchSelected)) {
         return;
      }
      String claNome = "true", claCodigo = "true";
      if ("3".equals(optionSearchSelected)) {
         listUsuarios = new UsuarioDAO().getUsuarios();
         return;
      } else if ("1".equals(optionSearchSelected)) {
         if (jcUtil.isEmpty(txtUsuarioSearch)) {
            return;
         }
         claNome = "usuNome like '%" + txtUsuarioSearch + "%'";
      } else if ("2".equals(optionSearchSelected)) {
         if (jcUtil.isEmpty(txtUsuarioSearch)) {
            return;
         }
         claNome = "usuCodigo = " + txtUsuarioSearch;
      }

      listUsuarios = new UsuarioDAO().getUsuarioByClausula(claNome+" and "+claCodigo);
   }

   public void onRowSelect(SelectEvent event) {
      usuarioSelected = (Usuario) event.getObject();
      try {
         FacesContext.getCurrentInstance().getExternalContext().redirect(new UtilFaces().getContexto() + "/usuario/usuario.xhtml");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}