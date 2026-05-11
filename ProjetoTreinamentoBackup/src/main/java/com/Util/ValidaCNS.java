package com.Util;

/**
 *
 * @author guilherme
 */
public class ValidaCNS {

   //    public static void main(String[] args){
//        // TODO code application logic here
//        System.out.println(checkCNS("123371566800018"));
//    }
   public static boolean checkCNS(String value) {
      value = safeTrim(value);
      if (!checkIsValidNumberAndSize(value, 15)) {
         return true;
      } else {
         String inicia = value.substring(0, 1);
         if(inicia.equals("1") || inicia.equals("2")){
            return (!isCnsValido(value));
         }else if(inicia.equals("7") || inicia.equals("8") || inicia.equals("9")){
            return (!isCnsProvisorioValido(value));
         }else{
            return true;
         }
      }
   }

   private static boolean isCnsProvisorioValido(String cns) {
      cns = safeTrim(cns);
      if (!checkIsValidNumberAndSize(cns, 15)) {
         return false;
      }
      float soma = Integer.valueOf(cns.substring(0, 1)).intValue() * 15 + Integer.valueOf(cns.substring(1, 2)).intValue() * 14 + Integer.valueOf(cns.substring(2, 3)).intValue() * 13
            + Integer.valueOf(cns.substring(3, 4)).intValue() * 12 + Integer.valueOf(cns.substring(4, 5)).intValue() * 11 + Integer.valueOf(cns.substring(5, 6)).intValue()
            * 10 + Integer.valueOf(cns.substring(6, 7)).intValue() * 9 + Integer.valueOf(cns.substring(7, 8)).intValue() * 8 + Integer.valueOf(cns.substring(8, 9)).intValue()
            * 7 + Integer.valueOf(cns.substring(9, 10)).intValue() * 6 + Integer.valueOf(cns.substring(10, 11)).intValue() * 5
            + Integer.valueOf(cns.substring(11, 12)).intValue() * 4 + Integer.valueOf(cns.substring(12, 13)).intValue() * 3 + Integer.valueOf(cns.substring(13, 14)).intValue()
            * 2 + Integer.valueOf(cns.substring(14, 15)).intValue() * 1;

      float resto = soma % 11.0F;
      if (resto != 0.0F) {
         return false;
      }
      return true;
   }

   private static boolean isCnsValido(String cns) {
      cns = safeTrim(cns);
      if (!checkIsValidNumberAndSize(cns, 15)) {
         return false;
      }
      String pis = cns.substring(0, 11);
      float soma = Integer.valueOf(cns.substring(0, 1)).intValue() * 15 + Integer.valueOf(cns.substring(1, 2)).intValue() * 14 + Integer.valueOf(cns.substring(2, 3)).intValue() * 13
            + Integer.valueOf(cns.substring(3, 4)).intValue() * 12 + Integer.valueOf(cns.substring(4, 5)).intValue() * 11 + Integer.valueOf(cns.substring(5, 6)).intValue()
            * 10 + Integer.valueOf(cns.substring(6, 7)).intValue() * 9 + Integer.valueOf(cns.substring(7, 8)).intValue() * 8 + Integer.valueOf(cns.substring(8, 9)).intValue()
            * 7 + Integer.valueOf(cns.substring(9, 10)).intValue() * 6 + Integer.valueOf(cns.substring(10, 11)).intValue() * 5;
      float resto = soma % 11.0F;
      Float dv = Float.valueOf(11.0F - resto);
      if (dv.floatValue() == 11.0F) {
         dv = Float.valueOf(0.0F);
      }
      String resultado = null;
      if (dv.floatValue() == 10.0F) {
         soma = Integer.valueOf(cns.substring(0, 1)).intValue() * 15 + Integer.valueOf(cns.substring(1, 2)).intValue() * 14 + Integer.valueOf(cns.substring(2, 3)).intValue()
               * 13 + Integer.valueOf(cns.substring(3, 4)).intValue() * 12 + Integer.valueOf(cns.substring(4, 5)).intValue() * 11
               + Integer.valueOf(cns.substring(5, 6)).intValue() * 10 + Integer.valueOf(cns.substring(6, 7)).intValue() * 9 + Integer.valueOf(cns.substring(7, 8)).intValue()
               * 8 + Integer.valueOf(cns.substring(8, 9)).intValue() * 7 + Integer.valueOf(cns.substring(9, 10)).intValue() * 6
               + Integer.valueOf(cns.substring(10, 11)).intValue() * 5 + 2;
         resto = soma % 11.0F;
         dv = Float.valueOf(11.0F - resto);
         resultado = pis.concat("001").concat(String.valueOf(dv.intValue()));
      } else {
         resultado = pis.concat("000").concat(String.valueOf(dv.intValue()));
      }
      if (cns.compareTo(resultado) != 0) {
         return false;
      }
      return true;
   }

   private static boolean checkIsValidNumberAndSize(String cns, int length) {
      if (isBlank(cns)) {
         return false;
      }
      if (!isOnlyNumber(cns)) {
         return false;
      }
      if (cns.length() != length) {
         return false;
      }
      return true;
   }

   public static boolean isBlank(String text) {
      if ((text != null) && (text.length() > 0)) {
         int i = 0;
         for (int iSize = text.length(); i < iSize; i++) {
            if (text.charAt(i) != ' ') {
               return false;
            }
         }
      }
      return true;
   }

   public static boolean isOnlyNumber(String value) {
      if (isNotBlank(value)) {
         return value.matches("[0-9]+");
      }
      return false;
   }

   public static boolean isNotBlank(String nome) {
      return !isBlank(nome);
   }

   public static String safeTrim(String string) {
      if (string != null) {
         return string.trim();
      }
      return null;
   }
}
