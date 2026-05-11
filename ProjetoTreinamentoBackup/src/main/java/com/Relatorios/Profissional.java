package com.Relatorios;

import ManagedBeans.UtilFaces;
import com.Util.ConexaoMySQL;
import com.Util.jcUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Profissional {
    private Integer setorLogado;

    public Integer getSetorLogado() {
        if(setorLogado == null){
            setorLogado = new jcUtil().getSetorLogadoBean().getSetCodigo();
        }
        return setorLogado;
    }

    public void setSetorLogado(Integer setorLogado) {
        this.setorLogado = setorLogado;
    }

    public void imprimir(ActionEvent e) throws IOException, SQLException {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) context.getExternalContext().getContext();

        String realPath = scontext.getRealPath("WEB-INF/") + "/";

        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        byte[] bytes = null;
        Connection con = ConexaoMySQL.getConexaoMySQL();
        try {
            Map parametros = new HashMap();
            parametros.put("setor", getSetorLogado());
//            parametros.put("cabe", realPath + "reports/cabecalhoNovo.jasper");

            UtilFaces.adicionarLogRel(parametros, "reports/Teste.jasper");

            bytes = JasperRunManager.runReportToPdf(realPath + "reports/Teste.jasper", parametros, con);
            jcUtil.addSessao("arquivoJasper", bytes);
        } catch (JRException ex) {
            ex.printStackTrace();
        } finally {
            con.close();
        }

        if (bytes != null && bytes.length > 0) {
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            context.responseComplete();
            outputStream.flush();
            outputStream.close();
        }
    }

}
