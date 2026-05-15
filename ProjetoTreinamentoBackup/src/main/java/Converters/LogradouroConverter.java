package Converters;


import com.Bean.Logradouro;
import com.DAO.LogradouroDAO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="LogradouroConverter")
public class LogradouroConverter  implements Converter{

    private LogradouroDAO logradouroDAO = new LogradouroDAO();

    public LogradouroConverter(){
    }

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("ID que chegou no converter: " + value);
        if (value == null || value.length() == 0) {
            return null;
        }else{
            try{
                Integer idlogradouro = Integer.parseInt(value);
                return logradouroDAO.getLogradouro(idlogradouro);
            }catch(NumberFormatException n){
                return null;
            }
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return "";
        }
        return ((Logradouro)value).getCodigoLogra().toString();
    }

}
