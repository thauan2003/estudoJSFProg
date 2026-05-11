package com.Util;

import org.joda.time.DateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

public class funcoesJava {

   /**
    * Creates a new instance of funcoesJava
    */
   public funcoesJava() {
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

   public static Date convStrToDate(String dateTime) throws ParseException {
      ZonedDateTime zdt = ZonedDateTime.parse(dateTime);
      LocalDateTime ldt = zdt.toLocalDateTime();



      DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
            .withLocale(Locale.ROOT)
            .withChronology(ISOChronology.getInstanceUTC());

      DateTime dt = formatter.parseDateTime(dateTime);

      return dt.toDate();
   }

   public static Date ConvStrToDate(String DateStr) throws ParseException {
      Date dataConv = new Date();

      DateStr = convBRtoEUA(DateStr);
      SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

      dataConv = (Date) format.parse(DateStr);

      return dataConv;
   }

   public static synchronized String ConvDateToStrEUA(Date data) {
      String dataConv = new SimpleDateFormat("yyyy-MM-dd").format(data);
      return dataConv;
   }

   public static String ConvDateToStr(String formato, Date data) {
      if (data == null) {
         return "";
      } else {
         SimpleDateFormat sdf = new SimpleDateFormat(formato, new Locale("pt", "br"));
         TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
         sdf.setTimeZone(tz);
         String dataConv = sdf.format(data);
         return dataConv;
      }
   }

   public static String DataAtual() {

      Date data = new Date();
      String dataConv;

      int data2 = new GregorianCalendar().get(Calendar.DAY_OF_YEAR);

      SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
      SimpleDateFormat formatEUA = new SimpleDateFormat("yyyy/MM/dd");

      Date data3 = new Date();

      formatador.format(data3);

      //  Este � usado para convertermos para o padr�o Nacional<br>
      return dataConv = formatador.format(data3);
   }

   public static String convBRtoEUA(String dataBRA) {

      String dia = dataBRA.substring(0, 2);
      String mes = dataBRA.substring(3, 5);
      String ano = dataBRA.substring(6, 10);

      String conv = null;
      conv = ano + "/" + mes + "/" + dia;  //yyyy/MM/dd

      return conv;
   }

   public static String convEUAtoBRA(String dataEUA) {

      String conv = "";
      if (dataEUA != null && dataEUA != "") {
         String ano = dataEUA.substring(0, 4);
         String mes = dataEUA.substring(5, 7);
         String dia = dataEUA.substring(8, 10);

         conv = dia + "/" + mes + "/" + ano;
      }  //dd/MM/yyyy

      return conv;
   }

   public static String formataMoeda(String n) {

      DecimalFormat df = new DecimalFormat("##0.00;(##0.00)");

      try {
         double nx = Double.parseDouble(n);
         String dx = Double.toString(nx);

         return dx;
      } catch (NullPointerException e) {
         return "0.00";
      } catch (Exception e) {

         return "0.00";
      }

   }

   public static String formataMoeda(double n) {
      try {
         DecimalFormat df = new DecimalFormat("##0.00;(##0.00)");

         String dx = df.format(n);

         return dx;
      } catch (NullPointerException e) {
         return "0.00";
      }
   }

   public static String getHoje() {
      String mesf = null;
      String retorno = null;

      Calendar calendar = new GregorianCalendar();
      Date hoje = new Date();
      calendar.setTime(hoje);
      int mes = calendar.get(Calendar.MONTH);
      int dia = calendar.get(Calendar.DAY_OF_MONTH);
      int ano = calendar.get(Calendar.YEAR);

      // mês
      switch (mes) {
         case 0:
            mesf = "janeiro";
            break;
         case 1:
            mesf = "fevereiro";
            break;
         case 2:
            mesf = "março";
            break;
         case 3:
            mesf = "abril";
            break;
         case 4:
            mesf = "maio";
            break;
         case 5:
            mesf = "junho";
            break;
         case 6:
            mesf = "julho";
            break;
         case 7:
            mesf = "agosto";
            break;
         case 8:
            mesf = "setembro";
            break;
         case 9:
            mesf = "outubro";
            break;
         case 10:
            mesf = "novembro";
            break;
         case 11:
            mesf = "dezembro";
            break;
      }
      retorno = " " + dia + " de " + mesf + " de " + ano;

      return retorno;
   }

   public static String getMes(Integer mes) {
      String mesf = "";
      switch (mes) {
         case 1:
            mesf = "Janeiro";
            break;
         case 2:
            mesf = "Fevereiro";
            break;
         case 3:
            mesf = "Março";
            break;
         case 4:
            mesf = "Abril";
            break;
         case 5:
            mesf = "Maio";
            break;
         case 6:
            mesf = "Junho";
            break;
         case 7:
            mesf = "Julho";
            break;
         case 8:
            mesf = "Agosto";
            break;
         case 9:
            mesf = "Setembro";
            break;
         case 10:
            mesf = "Outubro";
            break;
         case 11:
            mesf = "Novembro";
            break;
         case 12:
            mesf = "Dezembro";
            break;
      }
      return mesf;
   }

   public static String getMesAbrev(Integer mes) {
      String nomeMes = getMes(mes);
      return nomeMes.substring(0, 3).toUpperCase();
   }

   public static boolean isHoraInvalida(String hora) {
      if (hora != null && !hora.equals("")) {
         String[] maiorHoraSaiArray = hora.split(":");
         int r = Integer.parseInt(maiorHoraSaiArray[0]);
         int m = Integer.parseInt(maiorHoraSaiArray[1]);
         int s = Integer.parseInt(maiorHoraSaiArray[2]);
         if (r > 23 || m > 59 || s > 59) {
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static String DiferencaDias(Date data1, Date data2) {
      try {
         long diff = data2.getTime() - data1.getTime();
         String dif = (diff / (1000 * 60 * 60 * 24) + " dia(s)");
         return dif;
      } catch (Exception x) {
         x.printStackTrace();
         return "";
      }
   }

   public static Integer anoAtual() {
      Calendar c = new GregorianCalendar();
      c.setTime(new Date());
      return c.get(Calendar.YEAR);
   }

   public static String semAcentoNormalizer(String txt) {
      return Normalizer.normalize(txt, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
   }

   public static String semAcento(String txt) {
      String s = "";
      for (int i = 0; i < txt.length(); i++) {
         char c = txt.charAt(i);
         switch (c) {
            case 'Á':
            case 'À':
            case 'Ã':
               c = 'A';
               break;
            case 'É':
            case 'Ê':
               c = 'E';
               break;
            case 'Í':
               c = 'I';
               break;
            case 'Ó':
            case 'Õ':
            case 'Ô':
               c = 'O';
               break;
            case 'Ú':
               c = 'U';
               break;
            case 'Ç':
               c = 'C';

            case 'á':
            case 'à':
            case 'ã':
               c = 'a';
               break;
            case 'é':
            case 'ê':
               c = 'e';
               break;
            case 'í':
               c = 'i';
               break;
            case 'ó':
            case 'õ':
            case 'ô':
               c = 'o';
               break;
            case 'ú':
               c = 'u';
               break;
            case 'ç':
               c = 'c';
               break;
         }
         s += c;
      }
      return s;
   }

   public static Date incrementDateDay(Date date, int day) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(Calendar.DAY_OF_MONTH, day); // adiciona N dias na data
      return cal.getTime();
   }

   public static String nullConv(Object x) {
      try {
         if (x == null || x.equals("null") || x.equals("undefined")) {
            x = "";
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return x.toString();
   }
}
