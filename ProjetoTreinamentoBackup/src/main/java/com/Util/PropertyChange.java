package com.Util;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PropertyChange {
    private Object bean;
    private List<MapJC> listCamposAlterados;
    private String tabela = "";

    public PropertyChange(Object bean) {
        this.bean = bean;
        listCamposAlterados = new ArrayList<>();
        tabela = getNomeTabela();
    }

    public List<MapJC> getListCamposAlterados() {
        if (listCamposAlterados == null) {
            listCamposAlterados = new ArrayList<>();
        }
        return listCamposAlterados;
    }

    public void setListCamposAlterados(List<MapJC> listCamposAlterados) {
        this.listCamposAlterados = listCamposAlterados;
    }

    public void setaValores(String campo, Object oldValue, Object newValue) {
        if (jcUtil.isEmpty(oldValue)) {
            oldValue = "";
        }

        if (jcUtil.isEmpty(newValue)) {
            newValue = "";
        }

        if (oldValue instanceof Date) {
            oldValue = ((Date) oldValue).toString();
        }

        if (newValue instanceof Date) {
            newValue = ((Date) newValue).toString();
        }

        if (!String.valueOf(oldValue).equals(String.valueOf(newValue))) {
            MapJC mapJC = new MapJC();
            mapJC.put("campo", campo);
            mapJC.put("oldValue", oldValue);
            mapJC.put("newValue", newValue);
            getListCamposAlterados().add(mapJC);
        }
    }

    private String getNomeTabela() {
        try {
            Class<?> thisClass = Class.forName(bean.getClass().getName());
            if (thisClass.isAnnotationPresent(Entity.class) && thisClass.isAnnotationPresent(Table.class)) {
                return thisClass.getAnnotation(Table.class).name();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
