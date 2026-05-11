package com.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author guilherme
 */
public class metodoUtil {

   public static synchronized boolean isEmpty(Object obj) {
      if (obj == null) {
         return true;
      } else {
         if (obj instanceof String) {
            return obj.toString().trim().isEmpty() || "null".equals(obj.toString());
         }

         if (obj instanceof List) {
            return ((List) obj).isEmpty();
         }

         if (obj instanceof ArrayList) {
            return ((ArrayList) obj).isEmpty();
         }

         if (obj instanceof String[]) {
            return ((String[]) obj).length <= 0;
         }

         if (obj instanceof char[]) {
            return ((char[]) obj).length <= 0;
         }
         return false;
      }
   }

   private static boolean isAnoBissexto(int ano) {
      return ((ano % 4 == 0) && ((ano % 100 != 0) || (ano % 400 == 0)));
   }

   private static int getDiasNoMes(int ano, int mes) {
      int[] mes_dias = new int[13];

      int resultado = 0;

      mes_dias[0] = 0;
      mes_dias[1] = 31;
      mes_dias[2] = 28;
      mes_dias[3] = 31;
      mes_dias[4] = 30;
      mes_dias[5] = 31;
      mes_dias[6] = 30;
      mes_dias[7] = 31;
      mes_dias[8] = 31;
      mes_dias[9] = 30;
      mes_dias[10] = 31;
      mes_dias[11] = 30;
      mes_dias[12] = 31;

      if ((mes == 2) && (isAnoBissexto(ano))) {
         resultado = 29;
      } else {
         resultado = mes_dias[mes];
      }

      return resultado;
   }

   public static int[] getIdadeAtual(Date dataNasc, Date dataAtu) {
      // return pos[0] = anos,  pos[1] = meses,  pos[2] = dias
      Calendar cal = Calendar.getInstance();
      String idade = "";

      cal.setTime(dataNasc);

      int diaNasc = cal.get(Calendar.DAY_OF_MONTH);
      int mesNasc = (cal.get(Calendar.MONTH) + 1);
      int anoNasc = cal.get(Calendar.YEAR);

//        data = new Date();
      cal.setTime(dataAtu);

      int diaAtual = cal.get(Calendar.DAY_OF_MONTH);
      int mesAtual = (cal.get(Calendar.MONTH) + 1);
      int anoAtual = cal.get(Calendar.YEAR);

      if (diaAtual < diaNasc) {
         mesAtual--;
         diaAtual += getDiasNoMes(anoNasc, mesNasc);
      }
      if (mesAtual < mesNasc) {
         anoAtual--;
         mesAtual += 12;
      }
      int Ano = anoAtual - anoNasc;
      int Mes = mesAtual - mesNasc;
      int Dia = diaAtual - diaNasc;

      return new int[]{Ano, Mes, Dia};
   }

   public static int doAdd_(Map map, Connection con) throws SQLException {
      int index = 0;
      String camposTabela = "";
      String parans = "";
      Set<String> listChaves = map.keySet();
      String tabela = String.valueOf(map.get("tabela"));
      List<String> listParans = new ArrayList<>();
      List<String> listCampos = new ArrayList<>();
      List listValues = new ArrayList();
      for (String chave : listChaves) {
         if ("tabela".equals(chave) || "where".equals(chave) || "primaryKey".equals(chave) || "codPkPrinc".equals(chave)) {
            continue;
         }
         listParans.add("?");
         listValues.add(map.get(chave));
         listCampos.add(chave);
      }

      parans = convertListToString_(listParans);
      camposTabela = convertListToString_(listCampos);

      String sql = "insert into " + tabela + " (" + camposTabela + ") ";
      sql += "values (" + parans + ")";
      PreparedStatement pst = con.prepareStatement(sql);
      index = 1;
      for (Object obj : listValues) {
         pst.setObject(index, obj);
         index++;
      }
      pst.executeUpdate();
      ResultSet rs = null;
      rs = pst.getGeneratedKeys();
      long key = 0L;
      if (rs.next()) {
         key = rs.getLong(1);
      }
      return (int) key;
   }

   public static int doUpdate_(Map map, Connection con) throws SQLException {
      int index = 0;
      Set<String> listChaves = map.keySet();
      List<String> listCampos = new ArrayList<>();
      List listValues = new ArrayList();
      String tabela = String.valueOf(map.get("tabela"));
      String where = String.valueOf(map.get("where"));
      for (String chave : listChaves) {
         if ("tabela".equals(chave) || "where".equals(chave) || "primaryKey".equals(chave) || "codPkPrinc".equals(chave)) {
            continue;
         }
         listCampos.add(chave + " = ?");
         listValues.add(map.get(chave));
      }

      String campos = convertListToString_(listCampos);
      String sql = "update " + tabela + " set " + campos + " where " + where;
      PreparedStatement pst = con.prepareStatement(sql);
      index = 1;
      for (Object obj : listValues) {
         pst.setObject(index, obj);
         index++;
      }
      pst.executeUpdate();
      return 1;
   }

   public static String retiraCaracteresEspeciais(String stringFonte) {
      String passa = stringFonte;
      passa = passa.replaceAll("[ÂÀÁÄÃ]", "A");
      passa = passa.replaceAll("[âãàáä]", "a");
      passa = passa.replaceAll("[ÊÈÉË]", "E");
      passa = passa.replaceAll("[êèéë]", "e");
      passa = passa.replaceAll("ÎÍÌÏ", "I");
      passa = passa.replaceAll("îíìï", "i");
      passa = passa.replaceAll("[ÔÕÒÓÖ]", "O");
      passa = passa.replaceAll("[ôõòóö]", "o");
      passa = passa.replaceAll("[ÛÙÚÜ]", "U");
      passa = passa.replaceAll("[ûúùü]", "u");
      passa = passa.replaceAll("Ç", "C");
      passa = passa.replaceAll("ç", "c");
      passa = passa.replaceAll("[ýÿ]", "y");
      passa = passa.replaceAll("Ý", "Y");
      passa = passa.replaceAll("ñ", "n");
      passa = passa.replaceAll("Ñ", "N");
      passa = passa.replaceAll(" ", "");
      passa = passa.replaceAll("[-+=*&amp;%$#@!_]", "");
      passa = passa.replaceAll("['\"]", "");
      passa = passa.replaceAll("[<>()\\{\\}]", "");
      passa = passa.replaceAll("['\\\\.,()|/]", "");
      passa = passa.replaceAll("[^!-ÿ]{1}[^ -ÿ]{0,}[^!-ÿ]{1}|[^!-ÿ]{1}", " ");
      return passa;
   }

   public static synchronized boolean existeNumerosString(String str) {
      if (str != null && !str.isEmpty()) {
         for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
               return true;
            }
         }
         return false;
      } else {
         return false;
      }
   }

   public static String convertListToString_(List lista) {
      String retorno = "";
      String[] array = new String[lista.size()];
      lista.toArray(array);
      retorno = Arrays.toString(array);
      if (retorno.contains("[")) {
         retorno = retorno.replace("[", "");
      }
      if (retorno.contains("]")) {
         retorno = retorno.replace("]", "");
      }
      return retorno;
   }

   public static synchronized String LPad(String texto, int qtde) {
      if (texto == null) {
         return null;
      }
      try {
         String zeros = "";
         for (int i = 1; i <= qtde; i++) {
            zeros += "0";
         }
         String padded = zeros.substring(texto.length()) + texto;
         return padded;
      } catch (Exception ex) {
         return texto;
      }
   }

   public static synchronized String getCaractresSize(String texto, int qtde) {
      try {
         if (texto.trim().length() <= qtde) {
            return texto.trim();
         }
         return texto.trim().substring(0, qtde);
      } catch (Exception ex) {
         return texto;
      }
   }

   public static synchronized String pegaSomenteNumeros(String str) {
      if (str != null) {
         return str.replaceAll("[^0123456789]", "");
      } else {
         return "";
      }
   }

   public String getMes(Integer mes) {
      String mesf = "";
      switch (mes) {
         case 1:
            mesf = "JAN";
            break;
         case 2:
            mesf = "FEV";
            break;
         case 3:
            mesf = "MAR";
            break;
         case 4:
            mesf = "ABR";
            break;
         case 5:
            mesf = "MAI";
            break;
         case 6:
            mesf = "JUN";
            break;
         case 7:
            mesf = "JUL";
            break;
         case 8:
            mesf = "AGO";
            break;
         case 9:
            mesf = "SET";
            break;
         case 10:
            mesf = "OUT";
            break;
         case 11:
            mesf = "NOV";
            break;
         case 12:
            mesf = "DEZ";
            break;
      }
      return mesf;
   }

   public static int DiferencaEntreDatas(String data1, String data2) throws ParseException {
      GregorianCalendar ini = new GregorianCalendar();
      GregorianCalendar fim = new GregorianCalendar();
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      ini.setTime(sdf.parse(data1));
      fim.setTime(sdf.parse(data2));
      long dt1 = ini.getTimeInMillis();
      long dt2 = fim.getTimeInMillis();
      return (int) (((dt2 - dt1) / 86400000) + 1);
   }

   public static int DiferencaDias(Date data1, Date data2) {
      long diff = data2.getTime() - data1.getTime();
      String dif = (diff / (1000 * 60 * 60 * 24)) + "";
      return Integer.parseInt(dif);
   }

   public static String converterDataEuaToBr(String data) {
      String retorno = data;
      try {
         String[] split = data.split("-");
         retorno = split[2] + "/" + split[1] + "/" + split[0];
      } catch (Exception e) {
      }

      return retorno;
   }
}
