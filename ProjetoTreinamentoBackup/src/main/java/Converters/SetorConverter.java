package Converters;


import com.Bean.Setor;
import com.DAO.SetorDAO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Guilherme JC - 28-08-2020
 */
@FacesConverter(value="SetorConverter")
public class SetorConverter  implements Converter{

    private SetorDAO setorDAO = new SetorDAO();

    public SetorConverter(){
    }

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if (value == null || value.length() == 0) {
            return null;
        }else{
          try{ 
              Integer idsetor = Integer.parseInt(value);
              return setorDAO.getSetor(idsetor);
          }catch(NumberFormatException n){
          return null;    
          }
       }  
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return "";
        }
        return ((Setor)value).getSetCodigo().toString();
    }

}
