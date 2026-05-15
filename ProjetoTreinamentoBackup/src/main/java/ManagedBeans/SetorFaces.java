package ManagedBeans;

import com.Bean.Setor;
import com.Bean.Usuario;
import com.DAO.SetorDAO;
import com.DAO.UsuarioDAO;
import com.HibernateUtil.HibernateUtil;
import com.Util.MensagensErros;
import com.Util.jcUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Matheus JC - 05-10-2020
 */
@Named
@SessionScoped
public class SetorFaces implements Serializable {

    private List<Setor> cachedSetors = null;
    private List<Setor> listSetors = null;
    private SetorDAO setorDAO = new SetorDAO();
    private Setor setorSelected;
    private Usuario usuario;
    private Setor setor;
    private String modal = "N";
    private String include = "/WEB-INF/template.xhtml";
    private String paramUpload;

    public String getParamUpload() {
        return paramUpload;
    }

    public void setParamUpload(String paramUpload) {
        this.paramUpload = paramUpload;
    }

    public String getModal() {
        return modal;
    }

    public void setModal(String modal) {
        this.modal = modal;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public List<Setor> getListSetors() {
        return listSetors;
    }

    public void setListSetors(List<Setor> listSetors) {
        this.listSetors = listSetors;
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

    public SetorDAO getSetorDAO() {
        return setorDAO;
    }

    public void setSetorDAO(SetorDAO setorDAO) {
        this.setorDAO = setorDAO;
    }

    public Setor getSetorSelected() {
        return setorSelected;
    }

    public void setSetorSelected(Setor setorSelected) {
        this.setorSelected = setorSelected;
    }

    public List<Setor> getCachedSetors() {
        if (cachedSetors == null) {
            this.cachedSetors = setorDAO.getSetors();
        }
        return cachedSetors;
    }

    public void doAddSetor() {
        Setor setor = new jcUtil().getSetorLogadoBean();
        Usuario usuario = new jcUtil().getUsuarioLogadoBean();
        setorSelected = new Setor(setor.getSetCodigo(), usuario.getUsuCodigo());
        listSetors = setorDAO.getSetors();
    }

    public String dofinishAddSetor() {
        Date date = new Date();
        setorSelected.setDtaHoraAtualizacao(date);
        setorDAO.addSetor(setorSelected);
        cachedSetors = null;
        if(setorSelected.getSetCodigo() == new jcUtil().getSetorLogadoBean().getSetCodigo()){
            new jcUtil().setSetorLogadoBean(setorSelected);
        }
        if (MensagensErros.isErro()) {
            return "";
        } else {
            return "setorList";
        }
    }

    public String doRemoveSetor() {
        setorDAO.removeSetor(setorSelected);
        cachedSetors = null;
        if (MensagensErros.isErro()) {
            return "";
        } else {
            return "setorList";
        }
    }

    public String doUpdateSetor() {
        UsuarioDAO usuDAO = new UsuarioDAO();
        usuario = usuDAO.getUsuario(setorSelected.getCodUsuario());
        setor = setorDAO.getSetor(setorSelected.getCodSetor());
        return "setorEdit";
    }

    public String dofinishUpdateSetor() {
        Date date = new Date();
        Usuario codUsuario = new jcUtil().getUsuarioLogadoBean();
        setorSelected.setCodUsuario(codUsuario.getUsuCodigo());
        Setor codSetor = new jcUtil().getSetorLogadoBean();
        setorSelected.setCodSetor(codSetor.getSetCodigo());
        setorSelected.setDtaHoraAtualizacao(date);
        setorDAO.updateSetor(setorSelected);
        cachedSetors = null;
        if(setorSelected.getSetCodigo() == new jcUtil().getSetorLogadoBean().getSetCodigo()){
            new jcUtil().setSetorLogadoBean(setorSelected);
        }
        if (MensagensErros.isErro()) {
            return "";
        } else {
            return "setorList";
        }
    }

    public String doCancelSetor() {
        cachedSetors = null;
        return "setorList";
    }

    public void doSaveSetor(){
        if(setorSelected.getSetCodigo() == null){
            dofinishAddSetor();
            return;
        }
        dofinishUpdateSetor();
    }

    public void rowSelect(SelectEvent e){
        setorSelected = (Setor) e.getObject();
    }

    public List<SelectItem> getListaSetor() {
        List<SelectItem> toReturn_A = new LinkedList<SelectItem>();
        for (Setor ar : setorDAO.getSetors()) {
            toReturn_A.add(new SelectItem(ar, ar.getSetDescricao()));
        }
        return toReturn_A;
    } /*Esse método que possibilita guardar um id no Banco e mostrar um valor String na tela, em resumo ele: Cria uma lista de SelectItem, utiliza o DAO para dar um
        get numa lista de todos os Setores(obj) corre 1 setor por 1, e adicionar o valor setDescricao na lista criada, e retorna essa lista*/

    public List<Setor> setorBuscaAutoComplete(String busca) {
        busca = busca.replace(" - ", "");
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<Setor> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM setor WHERE setDescricao like '%"+busca+"%' ORDER BY setDescricao";
            Query q = ses.createNativeQuery(sql, Setor.class);
            lista = q.list();
            return lista;
        } catch (Exception e) {
            MensagensErros.setMsgErro(e.getMessage());
            return null;
        } finally {
            ses.close();
        }
    }

    public StreamedContent getExibirSetImgDepto() throws Exception {
        try {
            InputStream img = new ByteArrayInputStream(setorSelected.getSetImgDeptoFile());
            DefaultStreamedContent imgStream = new DefaultStreamedContent(img);
            return imgStream;
        } catch (Exception e) {
            ServletContext serv = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            File file = new File(serv.getRealPath("/imagens/") + "/semFoto.jpg");
            InputStream img = new ByteArrayInputStream(jcUtil.fileToByte(file));
            DefaultStreamedContent imgStream = new DefaultStreamedContent(img);
            return imgStream;
        }
    }

    public StreamedContent getExibirSetImgBrasaoEstado() throws Exception {
        try {
            InputStream img = new ByteArrayInputStream(setorSelected.getSetImgBrasaoFile());
            DefaultStreamedContent imgStream = new DefaultStreamedContent(img);
            return imgStream;
        } catch (Exception e) {
            ServletContext serv = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            File file = new File(serv.getRealPath("/imagens/") + "/semFoto.jpg");
            InputStream img = new ByteArrayInputStream(jcUtil.fileToByte(file));
            DefaultStreamedContent imgStream = new DefaultStreamedContent(img);
            return imgStream;
        }
    }
}