package com.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Guilherme JC - 28-08-2020
 */

@Entity
@Table(name = "auditoria")
public class Auditoria implements java.io.Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "AUDCODIGO", nullable = false)
   private Integer audCodigo;

   @Column(name = "AUDDATA")
   @Temporal(TemporalType.DATE)
   private Date audData;

   @Column(name = "AUDHORA")
   private String audHora;

   @Column(name = "audmac")
   private String audMac;

   @Column(name = "CODUSUARIO")
   private Integer codUsuario;

   @Column(name = "AUDHISTORICO")
   private String audHistorico;

   @Column(name = "audCamposAlterados")
   private String audCamposAlterados;

   @Column(name = "codSetor")
   private Integer codSetor;

   public Auditoria() {
   }

   public Auditoria(int setor, int usuario) {
      setCodSetor(setor);
      setCodUsuario(usuario);
   }

   public String getAudCamposAlterados() {
      return audCamposAlterados;
   }

   public void setAudCamposAlterados(String audCamposAlterados) {
      this.audCamposAlterados = audCamposAlterados;
   }

   public Integer getAudCodigo() {
      return audCodigo;
   }

   public void setAudCodigo(Integer audCodigo) {
      this.audCodigo = audCodigo;
   }

   public Date getAudData() {
      return audData;
   }

   public void setAudData(Date audData) {
      this.audData = audData;
   }

   public String getAudHora() {
      return audHora;
   }

   public void setAudHora(String audHora) {
      this.audHora = audHora;
   }

   public String getAudMac() {
      return audMac;
   }

   public void setAudMac(String audMac) {
      this.audMac = audMac;
   }

   public Integer getCodUsuario() {
      return codUsuario;
   }

   public void setCodUsuario(Integer codUsuario) {
      this.codUsuario = codUsuario;
   }

   public String getAudHistorico() {
      return audHistorico;
   }

   public void setAudHistorico(String audHistorico) {
      this.audHistorico = audHistorico;
   }

   public Integer getCodSetor() {
      return codSetor;
   }

   public void setCodSetor(Integer codSetor) {
      this.codSetor = codSetor;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Auditoria)) return false;
      Auditoria auditoria = (Auditoria) o;
      return Objects.equals(getAudCodigo(), auditoria.getAudCodigo()) &&
            Objects.equals(getAudData(), auditoria.getAudData()) &&
            Objects.equals(getAudHora(), auditoria.getAudHora()) &&
            Objects.equals(getCodUsuario(), auditoria.getCodUsuario()) &&
            Objects.equals(getAudHistorico(), auditoria.getAudHistorico()) &&
            Objects.equals(getCodSetor(), auditoria.getCodSetor());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getAudCodigo(), getAudData(), getAudHora(), getCodUsuario(), getAudHistorico(), getCodSetor());
   }

   @Override
   public String toString() {
      return audCodigo + "";
   }
}