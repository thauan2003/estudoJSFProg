package com.Bean;

import com.Util.PropertyChange;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Guilherme JC - 14-09-2020
 */

@Entity
@Table(name = "setor")
public class Setor implements java.io.Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "setCodigo", nullable = false)
   private Integer setCodigo;

   @Column(name = "setDescricao")
   private String setDescricao;

   @Column(name = "setEndereco")
   private String setEndereco;

   @Column(name = "setTelefone")
   private String setTelefone;

   @Column(name = "setEmail")
   private String setEmail;

   @Column(name = "dataHoraCad")
   @Temporal(TemporalType.TIMESTAMP)
   private Date dataHoraCad;

   @Column(name = "codUsuario")
   private Integer codUsuario;

   @Column(name = "codSetor")
   private Integer codSetor;

   @Column(name = "dtaHoraAtualizacao")
   @Temporal(TemporalType.TIMESTAMP)
   private Date dtaHoraAtualizacao;

   @Column(name = "setImgDepto")
   private String setImgDepto;

   @Transient
   private byte[] setImgDeptoFile;

   @Column(name = "setImgBrasao")
   private String setImgBrasao;

   @Column(name = "setRecConPlanoAcao")
   private boolean setRecConPlanoAcao;

   @Transient
   private byte[] setImgBrasaoFile;

   @Transient
   private PropertyChange propertyChange = new PropertyChange(this);

   public PropertyChange getPropertyChange() {
      return propertyChange;
   }

   public Setor() {
   }

   public Setor(int setor, int usuario) {
      setCodSetor(setor);
      setCodUsuario(usuario);
   }

   public boolean isSetRecConPlanoAcao() {
      return setRecConPlanoAcao;
   }

   public void setSetRecConPlanoAcao(boolean setRecConPlanoAcao) {
      propertyChange.setaValores("setRecConPlanoAcao", this.setRecConPlanoAcao, setRecConPlanoAcao);
      this.setRecConPlanoAcao = setRecConPlanoAcao;
   }

   public Integer getSetCodigo() {
      return setCodigo;
   }

   public void setSetCodigo(Integer setCodigo) {
      this.setCodigo = setCodigo;
   }

   public String getSetDescricao() {
      return setDescricao;
   }

   public void setSetDescricao(String setDescricao) {
      propertyChange.setaValores("setDescricao", this.setDescricao, setDescricao);
      this.setDescricao = setDescricao;
   }

   public String getSetEndereco() {
      return setEndereco;
   }

   public void setSetEndereco(String setEndereco) {
      propertyChange.setaValores("setEndereco", this.setEndereco, setEndereco);
      this.setEndereco = setEndereco;
   }

   public String getSetTelefone() {
      return setTelefone;
   }

   public void setSetTelefone(String setTelefone) {
      propertyChange.setaValores("setTelefone", this.setTelefone, setTelefone);
      this.setTelefone = setTelefone;
   }

   public String getSetEmail() {
      return setEmail;
   }

   public void setSetEmail(String setEmail) {
      propertyChange.setaValores("setEmail", this.setEmail, setEmail);
      this.setEmail = setEmail;
   }

   public String getSetImgDepto() {
      return setImgDepto;
   }

   public void setSetImgDepto(String setImgDepto) {
      this.setImgDepto = setImgDepto;
   }

   public String getSetImgBrasao() {
      return setImgBrasao;
   }

   public void setSetImgBrasao(String setImgBrasao) {
      this.setImgBrasao = setImgBrasao;
   }

   public Date getDataHoraCad() {
      return dataHoraCad;
   }

   public void setDataHoraCad(Date dataHoraCad) {
      this.dataHoraCad = dataHoraCad;
   }

   public Integer getCodUsuario() {
      return codUsuario;
   }

   public void setCodUsuario(Integer codUsuario) {
      this.codUsuario = codUsuario;
   }

   public Integer getCodSetor() {
      return codSetor;
   }

   public void setCodSetor(Integer codSetor) {
      this.codSetor = codSetor;
   }

   public Date getDtaHoraAtualizacao() {
      return dtaHoraAtualizacao;
   }

   public void setDtaHoraAtualizacao(Date dtaHoraAtualizacao) {
      this.dtaHoraAtualizacao = dtaHoraAtualizacao;
   }

   public byte[] getSetImgDeptoFile() {
      return setImgDeptoFile;
   }

   public void setSetImgDeptoFile(byte[] setImgDeptoFile) {
      this.setImgDeptoFile = setImgDeptoFile;
   }

   public byte[] getSetImgBrasaoFile() {
      return setImgBrasaoFile;
   }

   public void setSetImgBrasaoFile(byte[] setImgBrasaoFile) {
      this.setImgBrasaoFile = setImgBrasaoFile;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final Setor other = (Setor) obj;
      if (!Objects.equals(this.setCodigo, other.setCodigo)) {
         return false;
      }
      if (!Objects.equals(this.setDescricao, other.setDescricao)) {
         return false;
      }
      if (!Objects.equals(this.dataHoraCad, other.dataHoraCad)) {
         return false;
      }
      if (!Objects.equals(this.codUsuario, other.codUsuario)) {
         return false;
      }
      if (!Objects.equals(this.codSetor, other.codSetor)) {
         return false;
      }
      if (!Objects.equals(this.dtaHoraAtualizacao, other.dtaHoraAtualizacao)) {
         return false;
      }
      return true;
   }

   @Override
   public int hashCode() {
      int hash = 5;
      hash = 41 * hash + Objects.hashCode(this.setCodigo);
      hash = 41 * hash + Objects.hashCode(this.setDescricao);
      hash = 41 * hash + Objects.hashCode(this.dataHoraCad);
      hash = 41 * hash + Objects.hashCode(this.codUsuario);
      hash = 41 * hash + Objects.hashCode(this.codSetor);
      hash = 41 * hash + Objects.hashCode(this.dtaHoraAtualizacao);
      return hash;
   }

   @Override
   public String toString() {
      return setCodigo + "";
   }
}