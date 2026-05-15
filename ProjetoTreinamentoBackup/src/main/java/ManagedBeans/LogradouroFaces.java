package ManagedBeans;

import java.io.Serializable;

import com.Bean.*;
import com.DAO.LogradouroDAO;
import com.DAO.SetorDAO;
import com.HibernateUtil.HibernateUtil;
import com.Util.MensagensErros;
import com.Util.jcUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.primefaces.event.SelectEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Named
@SessionScoped
public class LogradouroFaces implements Serializable {

    //Instanciamentos Diretos:
    private LogradouroDAO logradouroDAO = new LogradouroDAO();
    @Inject
    private UtilFaces utilFaces;

    //Instanciamentos que serão tratados
    private List<Logradouro> cachedLogradouros = null;
    private Logradouro logradouroSelected;
    private List<Logradouro> listLogradouros = null;
    private List<Logradouro> selectedLogradouros;
    private Logradouro logradouro;
    private Setor setor;

    //Instanciamentos Locais
    private String chave;
    private String include = "/WEB-INF/template.xhtml";
    private SelectItem[] optionsLogradouro = {
            new SelectItem("Aeroporto", "Aeroporto"),
            new SelectItem("Alameda", "Alameda"),
            new SelectItem("Avenida", "Avenida"),
            new SelectItem("Beco", "Beco"),
            new SelectItem("Campo", "Campo"),
            new SelectItem("Chácara", "Chácara"),
            new SelectItem("Condomínio", "Condomínio"),
            new SelectItem("Conjunto", "Conjunto"),
            new SelectItem("Distrito", "Distrito"),
            new SelectItem("Estrada", "Estrada"),
            new SelectItem("Favela", "Favela"),
            new SelectItem("Fazenda", "Fazenda"),
            new SelectItem("Largo", "Largo"),
            new SelectItem("Loteamento", "Loteamento"),
            new SelectItem("Praça", "Praça"),
            new SelectItem("Rodovia", "Rodovia"),
            new SelectItem("Rua", "Rua"),
            new SelectItem("Travessa", "Travessa"),
            new SelectItem("Vale", "Vale"),
            new SelectItem("Vila", "Vila")
    };
    private SelectItem[] optionsSearchLogradouro = {
            new SelectItem("1", "Endereço"),
            new SelectItem("2", "Código"),
            new SelectItem("3", "Listar Todos")
    };
    private String txtLogradouroSearch = null;
    private String optionSearchSelected;

    //Getters e Setters(alguns com tratamentos internos)

    public UtilFaces getUtilFaces() {
        return utilFaces;
    }

    public void setUtilFaces(UtilFaces utilFaces) {
        this.utilFaces = utilFaces;
    }

    public String getOptionSearchSelected() {
        return optionSearchSelected;
    }

    public void setOptionSearchSelected(String optionSearchSelected) {
        this.optionSearchSelected = optionSearchSelected;
    }

    public String getTxtLogradouroSearch() {
        return txtLogradouroSearch;
    }

    public void setTxtLogradouroSearch(String txtLogradouroSearch) {
        this.txtLogradouroSearch = txtLogradouroSearch;
    }

    public SelectItem[] getOptionsSearchLogradouro() {
        return optionsSearchLogradouro;
    }

    public void setOptionsSearchLogradouro(SelectItem[] optionsSearchLogradouro) {
        this.optionsSearchLogradouro = optionsSearchLogradouro;
    }

    public SelectItem[] getOptionsLogradouro() {
        return optionsLogradouro;
    }

    public void setOptionsLogradouro(SelectItem[] optionsLogradouro) {
        this.optionsLogradouro = optionsLogradouro;
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

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public List<Logradouro> getSelectedLogradouros() {
        return selectedLogradouros;
    }

    public void setSelectedLogradouros(List<Logradouro> selectedLogradouros) {
        this.selectedLogradouros = selectedLogradouros;
    }

    public List<Logradouro> getListLogradouros() {
        return listLogradouros;
    }

    public void setListLogradouros(List<Logradouro> listLogradouros) {
        this.listLogradouros = listLogradouros;
    }

    public Logradouro getLogradouroSelected() {
        if (logradouroSelected == null) {
            logradouroSelected = new Logradouro();
        }
        return logradouroSelected;
    }

    public void setLogradouroSelected(Logradouro logradouroSelected) {
        this.logradouroSelected = logradouroSelected;
    }

    public LogradouroDAO getLogradouroDAO() {
        return logradouroDAO;
    }

    public void setLogradouroDAO(LogradouroDAO logradouroDAO) {
        this.logradouroDAO = logradouroDAO;
    }

    public List<Logradouro> getCachedLogradouros() {
        if (cachedLogradouros == null) {
            this.cachedLogradouros = logradouroDAO.getLogradouros();
        }
        return cachedLogradouros;
    }

    public void setCachedLogradouros(List<Logradouro> cachedLogradouros) {
        this.cachedLogradouros = cachedLogradouros;
    }





    public void rowSelect(SelectEvent e) {
        logradouroSelected = (Logradouro) e.getObject();
    }


    public void onRowSelect(SelectEvent event) {
        logradouroSelected = (Logradouro) event.getObject();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(new UtilFaces().getContexto() + "/logradouro/logradouro.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    //Método pré Create/Update
    public String doSaveOrUpdateLogradouro() {
        if (logradouroSelected.getCodigoLogra() == null || logradouroSelected.getCodigoLogra() == 0) {
            return dofinishAddLogradouro();
        } else {
            return dofinishUpdateLogradouro();
        }
    }



    //Métodos Create
    public String doAddLogradouro() {
//        int usuario = new jcUtil().getUsuarioLogadoBean().getUsuCodigo();
//        int setor = new jcUtil().getSetorLogadoBean().getSetCodigo();
        logradouroSelected = new Logradouro ();
        listLogradouros = logradouroDAO.getLogradouros();
        return "logradouro";
    }

    public String dofinishAddLogradouro() {
//        Date date = new Date();
//        logradouroSelected.setDtaHoraAtualizacao(date);
        logradouroDAO.addLogradouro(logradouroSelected);
        cachedLogradouros = null;
        if (MensagensErros.isErro()) {
            return "";
        } else {
            return "/logradouro/logradouroList?faces-redirect=true";
        }
    }

    //Métodos Update
    public String doUpdateLogradouro() {
        LogradouroDAO lograDAO = new LogradouroDAO();
        logradouro = lograDAO.getLogradouro(logradouroSelected.getCodigoLogra());
        SetorDAO posDAO = new SetorDAO();
        setor = posDAO.getSetor(logradouroSelected.getCodSetor());
        return "logradouro";
    }

    public String dofinishUpdateLogradouro() {
        Date date = new Date();
        int usuario = new jcUtil().getUsuarioLogadoBean().getUsuCodigo();
        int setor = new jcUtil().getSetorLogadoBean().getSetCodigo();
        logradouroSelected.setCodUsuario(usuario);
        logradouroSelected.setCodSetor(setor);
        logradouroSelected.setDtaHoraAtualizacao(date);
        logradouroDAO.updateLogradouro(logradouroSelected);
        cachedLogradouros = null;
        if (MensagensErros.isErro()) {
            return "";
        } else {
            return "/logradouro/logradouroList?faces-redirect=true";
        }
    }





    //Método Remove
    public String doRemoveLogradouro() {
        logradouroDAO.removeLogradouro(logradouroSelected);
        cachedLogradouros = null;
        if (MensagensErros.isErro()) {
            return "";
        } else {
            return "logradouroList";
        }
    }



    //Método Read
    public void buscaLogradourosList() {
        listLogradouros = new ArrayList<>();
        if (jcUtil.isEmpty(optionSearchSelected)) {
            return;
        }
        String claEndereco = "true", claCodigo = "true";
        if ("3".equals(optionSearchSelected)) {
            listLogradouros = new LogradouroDAO().getLogradouros();
            return;
        } else if ("1".equals(optionSearchSelected)) {
            if (jcUtil.isEmpty(txtLogradouroSearch)) {
                return;
            }
            claEndereco = "enderecoLogra like '%" + txtLogradouroSearch + "%'";
        } else if ("2".equals(optionSearchSelected)) {
            if (jcUtil.isEmpty(txtLogradouroSearch)) {
                return;
            }
            claCodigo = "codigoLogra = " + txtLogradouroSearch;
        }

        listLogradouros = new LogradouroDAO().getLogradouroByClausula(claEndereco+" and "+claCodigo);
    }

    public List<Logradouro> lograBuscaAutoComplete(String busca) {
        busca = busca.replace(" - ", "");
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<Logradouro> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM logradouro WHERE enderecoLogra like '%" + busca + "%' ORDER BY enderecoLogra";

            Query q = ses.createNativeQuery(sql, Logradouro.class);

            lista = q.list();

            return lista;

        } catch (Exception e) {
            MensagensErros.setMsgErro(e.getMessage());
            return null;
        } finally {
            ses.close();
        }
    }



    //Outros Métodos
    public String doCancelLogradouro() {
        cachedLogradouros = null;
        return "logradouroList";
    }

    public int getSizeListLogradouros(){
        if (listLogradouros == null){
            return 0;
        }
        return listLogradouros.size();
    }



    public void cleanSession() {
        jcUtil.removeSessao("usuario");
        jcUtil.removeSessao("posto");
        setLogradouroSelected(null);
        getLogradouroSelected();
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

//
//    public void genesisLogin() {
//        try {
//            getUsuarioSelected();
//            String pass = new String(new org.apache.commons.codec.binary.Base64().decode(chave.getBytes()));
//
//            String[] parts = pass.split(":", 5);
//            String usuario = parts[0];
//            String senha = parts[1];
//            String setor = parts[2];
//
//            usuarioSelected.setUsuLogin(usuario);
//            usuarioSelected.setUsuSenha(senha);
//            usuarioSelected.setUsuSetor(Integer.parseInt(setor));
//            doLogin();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
