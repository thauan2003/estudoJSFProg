package com.Bean;

import com.Util.PropertyChange;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bairro")
public class Bairro implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "codigoBairro", nullable = false)
    private Integer codigoBairro;

    @Column(name = "bairro")
    private String bairro;

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

    public Bairro() {}

    public Integer getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Integer codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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
        final Bairro other = (Bairro) obj;
        if (!Objects.equals(this.codigoBairro, other.codigoBairro)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.codigoBairro);
        hash = 41 * hash + Objects.hashCode(this.bairro);
        return hash;
    }

    @Override
    public String toString() {
        return codigoBairro + "";
    }

}
