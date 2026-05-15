package Converters;


import com.Bean.Bairro;
import com.DAO.BairroDAO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="BairroConverter")
public class BairroConverter  implements Converter{

    private BairroDAO bairroDAO = new BairroDAO();

    public BairroConverter(){
    }

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }else{
            try{
                Integer idbairro = Integer.parseInt(value);
                return bairroDAO.getBairro(idbairro);
            }catch(NumberFormatException n){
                return null;
            }
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return "";
        }
        return ((Bairro)value).getCodigoBairro().toString();
    }

}
