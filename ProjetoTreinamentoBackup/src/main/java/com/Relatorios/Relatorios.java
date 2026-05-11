package com.Relatorios;

import com.Util.jcUtil;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class Relatorios implements Serializable {
    private Profissional profissional;

    public Profissional getProfissional() {
        if(profissional == null){
            profissional = new Profissional();
        }
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
}
