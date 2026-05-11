package com.Util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author guilherme
 */
public class FuncoesUtil {

   public static String LPad_10Digitos(String texto) {
      try {
         if (texto.length() > 10) {
            texto = texto.substring(0, 10);
         }
         String padded = "0000000000".substring(texto.length()) + texto;
         if (padded.equals("0000000000")) {
            return null;
         }
         return padded;
      } catch (Exception ex) {
         return texto;
      }
   }

   public static String RPad_10Digitos(String texto) {
      try {
         String padded = texto + "0000000000".substring(texto.length());
         return padded;
      } catch (Exception ex) {
         return texto;
      }
   }

   public static int getIdade(java.util.Date dataNasc) {
      Calendar dateOfBirth = new GregorianCalendar();
      dateOfBirth.setTime(dataNasc);

      // Cria um objeto calendar com a data atual
      Calendar today = Calendar.getInstance();

      // Obtém a idade baseado no ano
      int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

      dateOfBirth.add(Calendar.YEAR, age);

      //se a data de hoje é antes da data de Nascimento, então diminui 1(um)
      if (today.before(dateOfBirth)) {
         age--;
      }
      return age;
   }

   public static Integer getIdade(Date dataNasc, Date dataBase) {
      Calendar dataNascimento = Calendar.getInstance();
      dataNascimento.setTime(dataNasc);
      Calendar dataAtual = Calendar.getInstance();
      dataAtual.setTime(dataBase);

      Integer diferencaMes = dataAtual.get(Calendar.MONTH) - dataNascimento.get(Calendar.MONTH);
      Integer diferencaDia = dataAtual.get(Calendar.DAY_OF_MONTH) - dataNascimento.get(Calendar.DAY_OF_MONTH);
      Integer idade = (dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR));

      if (diferencaMes < 0 || (diferencaMes == 0 && diferencaDia < 0)) {
         idade--;
      }
      return idade;
   }

   public static synchronized String convertArrayToString(Object[] array) {
      try {
         String string = Arrays.toString(array);
         if (string.contains("[")) {
            string = string.replace("[", "");
         }
         if (string.contains("]")) {
            string = string.replace("]", "");
         }
         return string;
      } catch (Exception e) {
         return "0";
      }

   }
}
