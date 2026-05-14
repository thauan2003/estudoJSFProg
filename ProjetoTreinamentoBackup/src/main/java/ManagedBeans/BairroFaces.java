package ManagedBeans;

import java.io.Serializable;

import com.Bean.Bairro;
import com.Bean.Setor;
import com.DAO.BairroDAO;
import com.DAO.SetorDAO;
import com.HibernateUtil.HibernateUtil;
import com.Util.MensagensErros;
import com.Util.jcUtil;
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
public class BairroFaces implements Serializable {
    private List<Bairro> cachedBairros = null;
    private BairroDAO bairroDAO = new BairroDAO();
    private Bairro bairroSelected;
    private List<Bairro> listBairros = null;
    private List<Bairro> selectedBairros;
    private Bairro bairro;
    private String chave;
    private String include = "/WEB-INF/template.xhtml";
    private Setor setor;

    private SelectItem[] optionsSearchBairro = {
            new SelectItem("1", "Bairro"),
            new SelectItem("2", "Código"),
            new SelectItem("3", "Listar Todos")
    };

    private String txtBairroSearch = null;
    private String optionSearchSelected;

    @Inject
    private UtilFaces utilFaces;

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

    public String getTxtBairroSearch() {
        return txtBairroSearch;
    }

    public void setTxtBairroSearch(String txtBairroSearch) {
        this.txtBairroSearch = txtBairroSearch;
    }

    public SelectItem[] getOptionsSearchBairro() {
        return optionsSearchBairro;
    }

    public void setOptionsSearchBairro(SelectItem[] optionsSearchBairro) {
        this.optionsSearchBairro = optionsSearchBairro;
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

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public List<Bairro> getSelectedBairros() {
        return selectedBairros;
    }

    public void setSelectedBairros(List<Bairro> selectedBairros) {
        this.selectedBairros = selectedBairros;
    }

    public List<Bairro> getListBairros() {
        return listBairros;
    }

    public void setListBairros(List<Bairro> listBairros) {
        this.listBairros = listBairros;
    }

    public Bairro getBairroSelected() {
        if (bairroSelected == null) {
            bairroSelected = new Bairro();
        }
        return bairroSelected;
    }

    public void setBairroSelected(Bairro bairroSelected) {
        this.bairroSelected = bairroSelected;
    }

    public BairroDAO getBairroDAO() {
        return bairroDAO;
    }

    public void setBairroDAO(BairroDAO bairroDAO) {
        this.bairroDAO = bairroDAO;
    }

    public List<Bairro> getCachedBairros() {
        if (cachedBairros == null) {
            this.cachedBairros = bairroDAO.getBairros();
        }
        return cachedBairros;
    }

    public void setCachedBairros(List<Bairro> cachedBairros) {
        this.cachedBairros = cachedBairros;
    }

    public void rowSelect(SelectEvent e) {
        bairroSelected = (Bairro) e.getObject();
    }


    public String doAddBairro() {
        int usuario = new jcUtil().getUsuarioLogadoBean().getUsuCodigo();
        int setor = new jcUtil().getSetorLogadoBean().getSetCodigo();
        bairroSelected = new Bairro ();
        listBairros = bairroDAO.getBairros();
        return "bairro";
    }

    public String dofinishAddBairro() {
        Date date = new Date();
        bairroSelected.setDtaHoraAtualizacao(date);
        bairroDAO.addBairro(bairroSelected);
        cachedBairros = null;
        if (MensagensErros.isErro()) {
            return "";
        } else {
            return "/bairro/bairroList?faces-redirect=true";
        }
    }

    public String doSaveOrUpdateBairro() {
        if (bairroSelected.getCodigoBairro() == null || bairroSelected.getCodigoBairro() == 0) {
            return dofinishAddBairro();
        } else {
            return dofinishUpdateBairro();
        }
    }

    public String doRemoveBairro() {
        bairroDAO.removeBairro(bairroSelected);
        cachedBairros = null;
        if (MensagensErros.isErro()) {
            return "";
        } else {
            return "bairroList";
        }
    }

    public String doUpdateBairro() {
        BairroDAO bairroDAO = new BairroDAO();
        bairro = bairroDAO.getBairro(bairroSelected.getCodigoBairro());
        SetorDAO posDAO = new SetorDAO();
        setor = posDAO.getSetor(bairroSelected.getCodSetor());
        return "bairro";
    }

    public String dofinishUpdateBairro() {
        Date date = new Date();
        int usuario = new jcUtil().getUsuarioLogadoBean().getUsuCodigo();
        int setor = new jcUtil().getSetorLogadoBean().getSetCodigo();
        bairroSelected.setCodUsuario(usuario);
        bairroSelected.setCodSetor(setor);
        bairroSelected.setDtaHoraAtualizacao(date);
        bairroDAO.updateBairro(bairroSelected);
        cachedBairros = null;
        if (MensagensErros.isErro()) {
            return "";
        } else {
            return "/bairro/bairroList?faces-redirect=true";
        }
    }


    public String doCancelBairro() {
        cachedBairros = null;
        return "bairroList";
    }

    public int getSizeListBairros(){
        if (listBairros == null){
            return 0;
        }
        return listBairros.size();
    }

    public void buscaBairrosList() {
        listBairros = new ArrayList<>();
        if (jcUtil.isEmpty(optionSearchSelected)) {
            return;
        }
        String claBairro = "true", claCodigo = "true";
        if ("3".equals(optionSearchSelected)) {
            listBairros = new BairroDAO().getBairros();
            return;
        } else if ("1".equals(optionSearchSelected)) {
            if (jcUtil.isEmpty(txtBairroSearch)) {
                return;
            }
            claBairro = "bairro like '%" + txtBairroSearch + "%'";
        } else if ("2".equals(optionSearchSelected)) {
            if (jcUtil.isEmpty(txtBairroSearch)) {
                return;
            }
            claCodigo = "codigoBairro = " + txtBairroSearch;
        }

        listBairros = new BairroDAO().getBairroByClausula(claBairro+" and "+claCodigo);
    }

    public void onRowSelect(SelectEvent event) {
        bairroSelected = (Bairro) event.getObject();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(new UtilFaces().getContexto() + "/bairro/bairro.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String doCancelUsuario() {
        cachedBairros = null;
        return "bairroList";
    }

    public void cleanSession() {
        jcUtil.removeSessao("usuario");
        jcUtil.removeSessao("posto");
        setBairroSelected(null);
        getBairroSelected();
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

}

