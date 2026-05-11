package com.Bean;

import com.Util.PropertyChange;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Guilherme JC - 14-09-2020
 */

@Entity
@Table(name = "usuario")
public class Usuario implements java.io.Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "usuCodigo", nullable = false)
   private Integer usuCodigo;

   @Column(name = "usuNome")
   private String usuNome;

   @Column(name = "usuLogin")
   private String usuLogin;

   @Column(name = "usuSenha")
   private String usuSenha;

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

   @Column(name = "usuSetor")
   private Integer usuSetor;

   @ManyToOne(optional = true)
   @JoinColumn(name = "usuSetCodigo", referencedColumnName = "setCodigo")
   private Setor usuSetCodigo;

   @Transient
   private PropertyChange propertyChange = new PropertyChange(this);

   public PropertyChange getPropertyChange() {
      return propertyChange;
   }

   public Usuario() {
   }

   public Usuario(int setor, int usuario) {
      setCodSetor(setor);
      setCodUsuario(usuario);
   }

  public Integer getUsuCodigo() {
      return usuCodigo;
   }

   public void setUsuCodigo(Integer usuCodigo) {
      this.usuCodigo = usuCodigo;
   }

   public String getUsuNome() {
      return usuNome;
   }

   public void setUsuNome(String usuNome) {
      propertyChange.setaValores("usuNome", this.usuNome, usuNome);
      this.usuNome = usuNome;
   }

   public String getUsuLogin() {
      return usuLogin;
   }
   
   public void setUsuLogin(String usuLogin) {
      propertyChange.setaValores("usuLogin", this.usuLogin, usuLogin);
      this.usuLogin = usuLogin;
   }

   public String getUsuSenha() {
      return usuSenha;
   }

   public void setUsuSenha(String usuSenha) {
      this.usuSenha = usuSenha;
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

   public Integer getUsuSetor() {
      return usuSetor;
   }

   public void setUsuSetor(Integer usuSetor) {
      propertyChange.setaValores("usuSetor", this.usuSetor, usuSetor);
      this.usuSetor = usuSetor;
   }

   public Setor getUsuSetCodigo() {
      return usuSetCodigo;
   }

   public void setUsuSetCodigo(Setor usuSetCodigo) {
      propertyChange.setaValores("usuSetCodigo", this.usuSetCodigo == null ? null : this.usuSetCodigo.getSetCodigo(), usuSetCodigo == null ? null : usuSetCodigo.getSetCodigo());
      this.usuSetCodigo = usuSetCodigo;
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
      final Usuario other = (Usuario) obj;
      if (!Objects.equals(this.usuCodigo, other.usuCodigo)) {
         return false;
      }
      if (!Objects.equals(this.usuNome, other.usuNome)) {
         return false;
      }
      if (!Objects.equals(this.usuLogin, other.usuLogin)) {
         return false;
      }
      if (!Objects.equals(this.usuSenha, other.usuSenha)) {
         return false;
      }
      if (!Objects.equals(this.usuSetCodigo, other.usuSetCodigo)) {
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
      if (!Objects.equals(this.usuSetor, other.usuSetor)) {
         return false;
      }
      return true;
   }

   @Override
   public int hashCode() {
      int hash = 5;
      hash = 41 * hash + Objects.hashCode(this.usuCodigo);
      hash = 41 * hash + Objects.hashCode(this.usuNome);
      hash = 41 * hash + Objects.hashCode(this.usuLogin);
      hash = 41 * hash + Objects.hashCode(this.usuSenha);
      hash = 41 * hash + Objects.hashCode(this.usuSetCodigo);
      hash = 41 * hash + Objects.hashCode(this.dataHoraCad);
      hash = 41 * hash + Objects.hashCode(this.codUsuario);
      hash = 41 * hash + Objects.hashCode(this.codSetor);
      hash = 41 * hash + Objects.hashCode(this.dtaHoraAtualizacao);
      hash = 41 * hash + Objects.hashCode(this.usuSetor);
      return hash;
   }

   @Override
   public String toString() {
      return usuCodigo + "";
   }
}