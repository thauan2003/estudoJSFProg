package com.Bean;

import com.Util.PropertyChange;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "logradouro")
public class Logradouro implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "codigoLogra", nullable = false)
    private Integer codigoLogra;

    @Column(name = "enderecoLogra")
    private String enderecoLogra;

    @Column(name = "numeroLogra")
    private String numeroLogra;

    @Column(name = "complementoLogra")
    private String complementoLogra;

    @Column(name = "cidadeLogra")
    private String cidadeLogra;

    @Column(name = "cepLogra")
    private Integer cepLogra;

    @Column(name = "tipoLogra")
    private String tipoLogra;

    @Column(name = "codUsuario")
    private Integer codUsuario;

    @Column(name = "codSetor")
    private Integer codSetor;

    @Column(name = "dtaHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraAtualizacao;

    @Transient
    private PropertyChange propertyChange = new PropertyChange(this);

    public PropertyChange getPropertyChange() {
        return propertyChange;
    }

    public Logradouro(){ }

//    public Logradouro(int setor, int usuario) {
//        setCodSetor(setor);
//        setCodUsuario(usuario);
//    }


    public Date getDtaHoraAtualizacao() {
        return dtaHoraAtualizacao;
    }

    public void setDtaHoraAtualizacao(Date dtaHoraAtualizacao) {
        this.dtaHoraAtualizacao = dtaHoraAtualizacao;
    }

    public void setCodigoLogra(Integer codigoLogra) {
        this.codigoLogra = codigoLogra;
    }

    public Integer getCodigoLogra() {
        return codigoLogra;
    }

    public String getEnderecoLogra() {
        return enderecoLogra;
    }

    public void setEnderecoLogra(String enderecoLogra) {
        this.enderecoLogra = enderecoLogra;
    }

    public String getNumeroLogra() {
        return numeroLogra;
    }

    public void setNumeroLogra(String numeroLogra) {
        this.numeroLogra = numeroLogra;
    }

    public String getComplementoLogra() {
        return complementoLogra;
    }

    public void setComplementoLogra(String complementoLogra) {
        this.complementoLogra = complementoLogra;
    }

    public String getCidadeLogra() {
        return cidadeLogra;
    }

    public void setCidadeLogra(String cidadeLogra) {
        this.cidadeLogra = cidadeLogra;
    }

    public Integer getCepLogra() {
        return cepLogra;
    }

    public void setCepLogra(Integer cepLogra) {
        this.cepLogra = cepLogra;
    }

    public String getTipoLogra() {
        return tipoLogra;
    }

    public void setTipoLogra(String tipoLogra) {
        this.tipoLogra = tipoLogra;
    }

    public void setPropertyChange(PropertyChange propertyChange) {
        this.propertyChange = propertyChange;
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
        final Logradouro other = (Logradouro) obj;
        if (!Objects.equals(this.codigoLogra, other.codigoLogra)) {
            return false;
        }
        if (!Objects.equals(this.enderecoLogra, other.enderecoLogra)) {
            return false;
        }
        if (!Objects.equals(this.numeroLogra, other.numeroLogra)) {
            return false;
        }
        if (!Objects.equals(this.complementoLogra, other.complementoLogra)) {
            return false;
        }
        if (!Objects.equals(this.cidadeLogra, other.cidadeLogra)) {
            return false;
        }
        if (!Objects.equals(this.cepLogra, other.cepLogra)) {
            return false;
        }
        if (!Objects.equals(this.tipoLogra, other.tipoLogra)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.codigoLogra);
        hash = 41 * hash + Objects.hashCode(this.enderecoLogra);
        hash = 41 * hash + Objects.hashCode(this.numeroLogra);
        hash = 41 * hash + Objects.hashCode(this.complementoLogra);
        hash = 41 * hash + Objects.hashCode(this.cidadeLogra);
        hash = 41 * hash + Objects.hashCode(this.cepLogra);
        hash = 41 * hash + Objects.hashCode(this.tipoLogra);
        return hash;
    }

    @Override
    public String toString() {
        return codigoLogra + "";
    }
}
