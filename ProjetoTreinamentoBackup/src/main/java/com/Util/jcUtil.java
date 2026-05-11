package com.Util;

import ManagedBeans.UtilFaces;
import com.Bean.Auditoria;
import com.Bean.Setor;
import com.Bean.Usuario;
import com.HibernateUtil.HibernateUtil;
import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.type.Type;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.primefaces.PrimeFaces;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.model.SelectItem;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.*;
import java.lang.InstantiationException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static com.Util.funcoesJava.DiferencaEntreDatas;

/**
 * Essa classe possui métodos de uso geral como salvar um valor na sessão,
 * remover o valor, criar FacesMessages, etc.
 * @author Danilo
 */
public class jcUtil {

   private UtilFaces utilFaces = null;

   static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   static String relogioJC;
   private String contexto;
   private Setor setorLogadoBean;
   private Usuario usuarioLogadoBean;
   private List<String> bloqueioLogadoBean;

   public UtilFaces getUtilFaces() {
      if(utilFaces == null){
         ELContext elContext = FacesContext.getCurrentInstance().getELContext();
         utilFaces = (UtilFaces) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "utilFaces");
      }
      return utilFaces;
   }

   public void setUtilFaces(UtilFaces utilFaces) {
      this.utilFaces = utilFaces;
   }

   public String getContexto() {
      contexto = getUtilFaces().getContexto();
      return contexto;
   }

   public Setor getSetorLogadoBean() {
      ELContext elContext = FacesContext.getCurrentInstance().getELContext();
      utilFaces = (UtilFaces) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "utilFaces");
      setorLogadoBean = utilFaces.getSetorLogadoBean();
      return setorLogadoBean;
   }

   public void setSetorLogadoBean(Setor setorLogadoBean) {
      ELContext elContext = FacesContext.getCurrentInstance().getELContext();
      utilFaces = (UtilFaces) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "utilFaces");
      utilFaces.setSetorLogadoBean(setorLogadoBean);
      this.setorLogadoBean = setorLogadoBean;
   }

   public Usuario getUsuarioLogadoBean() {
      ELContext elContext = FacesContext.getCurrentInstance().getELContext();
      utilFaces = (UtilFaces) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "utilFaces");
      usuarioLogadoBean = utilFaces.getUsuarioLogadoBean();
      return usuarioLogadoBean;
   }

   public void setUsuarioLogadoBean(Usuario usuarioLogadoBean) {
      ELContext elContext = FacesContext.getCurrentInstance().getELContext();
      utilFaces = (UtilFaces) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "utilFaces");
      utilFaces.setUsuarioLogadoBean(usuarioLogadoBean);
      this.usuarioLogadoBean = usuarioLogadoBean;
   }

   public static SimpleDateFormat getSdf() {
      return sdf;
   }

   public static void setSdf(SimpleDateFormat sdf) {
      jcUtil.sdf = sdf;
   }

   /**
    * Verivica se um determinado valor existe na sessão
    * @param valor
    * @return true ou false
    */
   public static boolean existeNaSessao(String valor) {

      Object verifica = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(valor);

      if (verifica != null) { //valor existe na sessão
         return true;
      }

      return false;
   }

   /**
    * Adiciona um dado(valor) na sessão
    * @param Key   A chave
    * @param valor O valor
    */
   public static void addSessao(String Key, Object valor) {
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Key, valor);
   }

   /**
    * Remove um valor da sessão
    * @param key
    */
   public static void removeSessao(String key) {
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
   }

   /**
    * Retorna um objeto que está na sessão
    * @param Key
    * @return
    */
   public static Object getSessao(String Key) {
      return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Key);
   }

   /**
    * Adiciona uma mensagem no ciclo atual do faces
    * @param msg
    * @param severity
    * @param titulo
    */
   public static void addMensagem(String msg, FacesMessage.Severity severity, String... titulo) {
      FacesMessage mensagem = new FacesMessage();
      mensagem.setSeverity(severity);
      mensagem.setSummary(msg);
      mensagem.setDetail(msg);
      if (titulo != null && titulo.length > 0) {
         mensagem.setSummary(titulo[0]);
      }
      FacesContext.getCurrentInstance().addMessage(null, mensagem);
   }

   /**
    * Retorna um parâmetro da url
    * @param chave -O parâmetro
    * @return
    */
   public static String getParametro(String chave) {
      return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(chave);
   }

   /**
    * Lista de Ufs
    */
   public static SelectItem[] siglasEstado = {
         new SelectItem("SP", "SP"),
         new SelectItem("AC", "AC"),
         new SelectItem("AL", "AL"),
         new SelectItem("AM", "AM"),
         new SelectItem("AP", "AP"),
         new SelectItem("BA", "BA"),
         new SelectItem("CE", "CE"),
         new SelectItem("DF", "DF"),
         new SelectItem("ES", "ES"),
         new SelectItem("GO", "GO"),
         new SelectItem("MA", "MA"),
         new SelectItem("MG", "MG"),
         new SelectItem("MS", "MS"),
         new SelectItem("MT", "MT"),
         new SelectItem("PA", "PA"),
         new SelectItem("PB", "PB"),
         new SelectItem("PE", "PE"),
         new SelectItem("PI", "PI"),
         new SelectItem("PR", "PR"),
         new SelectItem("RJ", "RJ"),
         new SelectItem("RN", "RN"),
         new SelectItem("RO", "RO"),
         new SelectItem("RR", "RR"),
         new SelectItem("RS", "RS"),
         new SelectItem("SC", "SC"),
         new SelectItem("SE", "SE"),
         new SelectItem("SP", "SP"),
         new SelectItem("TO", "TO")
   };
   /**
    * Select item de sexo
    */
   public static SelectItem[] sexo = {
         new SelectItem("M", "Masculino"),
         new SelectItem("F", "Feminino")
   };
   /**
    * Tipode de logradouro
    */
   public static SelectItem[] tipoLogradouro = {
         new SelectItem("RUA", "Rua"),
         new SelectItem("AVENIDA", "Avenida"),
         new SelectItem("TRAVESSA", "Travessa"),
         new SelectItem("ALAMEDA", "Alemeda"),
         new SelectItem("BECO", "Beco")
   };
   /**
    * Estado civil
    */
   public static SelectItem[] estadoCivil = {
         new SelectItem("SOLTEIRO(A)", "SOLTEIRO(A)"),
         new SelectItem("CASADO(A)", "CASADO(A)"),
         new SelectItem("DIVORCIADO(A)", "DIVORCIADO(A)"),
         new SelectItem("SEPARADO(A)", "SEPARADO(A)"),
         new SelectItem("VIÚVO(A)", "VIÚVO(A)")
   };
   /**
    * EScolha Sim/Não
    */
   public static SelectItem[] simNao = {
         new SelectItem("SIM", "SIM"),
         new SelectItem("NÃO", "NÃO")
   };
   public static SelectItem[] mes = {
         new SelectItem("JANEIRO", "JANEIRO"),
         new SelectItem("FEVEREIRO", "FEVEREIRO"),
         new SelectItem("MARCO", "MARCO"),
         new SelectItem("ABRIL", "ABRIL"),
         new SelectItem("MAIO", "MAIO"),
         new SelectItem("JUNHO", "JUNHO"),
         new SelectItem("JULHO", "JULHO"),
         new SelectItem("AGOSTO", "AGOSTO"),
         new SelectItem("SETEMBRO", "SETEMBRO"),
         new SelectItem("OUTUBRO", "OUTUBRO"),
         new SelectItem("NOVEMBRO", "NOVEMBRO"),
         new SelectItem("DEZEMBRO", "DEZEMBRO"),};

   public static String[] mesReduzido;

   public static String[] getMesReduzido() {
      mesReduzido = new String[12];
      mesReduzido[0] = "JAN";
      mesReduzido[1] = "FEV";
      mesReduzido[2] = "MAR";
      mesReduzido[3] = "ABR";
      mesReduzido[4] = "MAI";
      mesReduzido[5] = "JUN";
      mesReduzido[6] = "JUL";
      mesReduzido[7] = "AGO";
      mesReduzido[8] = "SET";
      mesReduzido[9] = "OUT";
      mesReduzido[10] = "NOV";
      mesReduzido[11] = "DEZ";
      return mesReduzido;
   }

   /**
    * Verifica se uma string contém somente números inteiros
    * @param string
    * @return true ou false
    */
   public static boolean isInteger(String string) {
      try {
         Integer.valueOf(string);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }

   //Luiz Ant.
   public static Date subtraiOuSomaDiasEmData(Date dataRecebida, int dias, String operacao)
   {
      Calendar data = Calendar.getInstance();
      data.setTime(dataRecebida);
      if(operacao.equals("-"))
         data.add(data.DATE, -dias); //subtrai em dias a data informada
      else
         data.add(data.DATE, dias); //soma em dias a data informada
      return data.getTime(); //data Final Subtraida ou Somada
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

   public static String getIdadePorExtenso(Date dataNasc) {
      return getIdadePorExtenso(dataNasc, new Date());
   }

   public static String getIdadePorExtenso(Date dataNasc, Date dataBase) {
      Calendar cal = Calendar.getInstance();
      String idade = "";

      cal.setTime(dataNasc);

      int diaNasc = cal.get(Calendar.DAY_OF_MONTH);
      int mesNasc = (cal.get(Calendar.MONTH) + 1);
      int anoNasc = cal.get(Calendar.YEAR);

      cal.setTime(dataBase);

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

      String AnoSingPlural = "";
      String MesSingPlural = "";
      String DiaSingPlural = "";
      String ano = String.valueOf(Ano);
      String mes = String.valueOf(Mes);
      String dia = String.valueOf(Dia);
      if (Ano > 1) {
         AnoSingPlural = " Anos ";
      } else {
         AnoSingPlural = " Ano ";
      }
      if (Mes > 1) {
         MesSingPlural = " Meses ";
      } else {
         MesSingPlural = " Mês ";
      }
      if (Dia > 1) {
         DiaSingPlural = " Dias";
      } else {
         DiaSingPlural = " Dia";
      }
      if (Ano == 0) {
         ano = "";
         AnoSingPlural = "";
      }
      if (Mes == 0) {
         mes = "";
         MesSingPlural = "";
      }
      if (Dia == 0) {
         dia = "";
         DiaSingPlural = "";
      }
      idade = ano + AnoSingPlural + mes + MesSingPlural + dia + DiaSingPlural;
      return idade;
   }

   public static int[] getIdadeAtualAnoMesDia(Date dataNasc, Date dataAtu) {
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

   public static String getIdadeAtual(Date dataNasc, Date dataAtu) {
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

      String AnoSingPlural = "";
      String MesSingPlural = "";
      String DiaSingPlural = "";
      String ano = String.valueOf(Ano);
      String mes = String.valueOf(Mes);
      String dia = String.valueOf(Dia);
      if (Ano > 1) {
         AnoSingPlural = " anos";
      } else {
         AnoSingPlural = " ano";
      }
      if (Mes > 1) {
         MesSingPlural = " meses";
      } else {
         MesSingPlural = " mês";
      }
      if (Dia > 1) {
         DiaSingPlural = " dias";
      } else {
         DiaSingPlural = " dia";
      }
      if (Ano == 0) {
         ano = "";
         AnoSingPlural = "";
      }
      if (Mes == 0) {
         mes = "";
         MesSingPlural = "";
      }
      if (Dia == 0) {
         dia = "";
         DiaSingPlural = "";
      }
      if (Ano == 0) {
         if (Mes == 0) {
            idade = dia + DiaSingPlural;
         } else {
            idade = mes + MesSingPlural;
         }
      } else {
         idade = ano + AnoSingPlural;
      }
      return idade;
   }

   public static byte[] fileToByte(File imagem) throws Exception {
      FileInputStream fis = new FileInputStream(imagem);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte[] buffer = new byte[8192];
      int bytesRead = 0;
      while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
         baos.write(buffer, 0, bytesRead);
      }
      return baos.toByteArray();
   }

   public static InputStream byteToInputStream(byte[] bytes) throws Exception {
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
      return bais;
   }

   public static String getHoraCorrente() {
      return new SimpleDateFormat("HH:mm:ss", new Locale("pt", "BR")).format(new Date());
   }

   /**
    * Método para comparar as das e retornar o numero de dias de diferença
    * entre elas
    * <p>
    * Compare two date and return the difference between them in days.
    * @param dataLow  The lowest date
    * @param dataHigh The highest date
    * @return int
    */
   public static int dataDiff(Date dataLow, Date dataHigh) {
      org.joda.time.Days d = org.joda.time.Days.daysBetween(new DateTime(dataLow), new DateTime(dataHigh));
      return d.getDays();
   }

   public static String formataData(Date data) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(data);

      Integer dia = cal.get(Calendar.DAY_OF_MONTH);
      Integer mes = (cal.get(Calendar.MONTH) + 1);
      Integer ano = cal.get(Calendar.YEAR);

      String dtaFormatada;

      if (dia < 10) {
         dtaFormatada = "0" + dia + "/";
      } else {
         dtaFormatada = dia + "/";
      }

      if (mes < 10) {
         dtaFormatada = dtaFormatada + "0" + mes + "/";
      } else {
         dtaFormatada = dtaFormatada + mes + "/";
      }

      dtaFormatada = dtaFormatada + ano;

      return dtaFormatada;
   }

   public static String calculaDataSql(Date data) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(data);
      int dia = cal.get(Calendar.DAY_OF_MONTH);
      int mes = cal.get(Calendar.MONTH) + 1;
      int ano = cal.get(Calendar.YEAR);
      String dataBusca = ano + "-" + mes + "-" + dia;
      return dataBusca;
   }

   public static int calculaIdade(Date data) {

      Calendar cal = Calendar.getInstance();
      cal.setTime(data);

      int dia = cal.get(Calendar.DAY_OF_MONTH);
      int mes = (cal.get(Calendar.MONTH) + 1);
      int ano = cal.get(Calendar.YEAR);

      int idade;

      Date dataHoje = new Date();
      Calendar calHoje = GregorianCalendar.getInstance();
      int diaGregorianoDeHoje = calHoje.get(Calendar.DAY_OF_YEAR);
      int anoGregorianoDeHoje = calHoje.get(Calendar.YEAR);
      System.out.println("Dia de hoje = " + diaGregorianoDeHoje);
      System.out.println("Ano de hoje = " + anoGregorianoDeHoje);
      System.out.println("Data Hoje = " + dataHoje);

      GregorianCalendar gc = new GregorianCalendar();
      gc.set(GregorianCalendar.DAY_OF_MONTH, dia);
      gc.set(GregorianCalendar.MONTH, mes);
      gc.set(GregorianCalendar.YEAR, ano);
      int diaGregorianoDoNasc = gc.get(GregorianCalendar.DAY_OF_YEAR);
      int mesGregorianoDoNasc = gc.get(GregorianCalendar.MONTH);
      int anoGregorianoDoNasc = gc.get(Calendar.YEAR);
      anoGregorianoDoNasc = anoGregorianoDoNasc + 1900;
      System.out.println("Dia do nascimento = " + diaGregorianoDoNasc);
      System.out.println("Mes do nascimento = " + mesGregorianoDoNasc);
      System.out.println("Ano do nascimento = " + anoGregorianoDoNasc);
      idade = anoGregorianoDeHoje - anoGregorianoDoNasc;

      if (diaGregorianoDoNasc > diaGregorianoDeHoje) {
         idade--;
      }
      System.out.println("idade calculada = " + idade);

      return (idade);

   }

   /*Estes dois métodos abaixo funcionam perfeitamente para o calculo de idade*/
   public static int calculaIdade2(Date dataNasc) {
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

   public static Integer getIdade(Date data) {
      return getIdade(data, new Date());
   }

   public static Integer getIdade(Date data, Date dataBase) {
      Calendar dataNascimento = Calendar.getInstance();
      dataNascimento.setTime(data);
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

   public static boolean verificaPeriodo(Date dta1, Date dta2) {
      try {
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
         String data1 = format.format(dta1);
         String data2 = format.format(dta2);
         int dif = DiferencaEntreDatas(data1, data2);
         if (dif > 31) {
            return false;
         } else {
            return true;
         }
      } catch (Exception e) {
         e.printStackTrace();
         return true;
      }
   }

   public static boolean verificaPeriodo(Date dta1, Date dta2, int dias) {
      try {
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
         String data1 = format.format(dta1);
         String data2 = format.format(dta2);
         int dif = DiferencaEntreDatas(data1, data2);
         if (dif > dias) {
            return false;
         } else {
            return true;
         }
      } catch (Exception e) {
         e.printStackTrace();
         return true;
      }
   }

   public static boolean verificaPeriodo90(Date dta1, Date dta2) {
      try {
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
         String data1 = format.format(dta1);
         String data2 = format.format(dta2);
         int dif = DiferencaEntreDatas(data1, data2);
         if (dif > 90) {
            return false;
         } else {
            return true;
         }
      } catch (Exception e) {
         e.printStackTrace();
         return true;
      }
   }

   public static int dataDiffMeses(Date dataI, Date dataF) {
      org.joda.time.Months m = org.joda.time.Months.monthsBetween(new DateTime(dataI), new DateTime(dataF));
      //retorna a diferença em meses das duas datas
      return m.getMonths();
   }

   public static synchronized int dataDiffSemanas(Date dataI, Date dataF) {
      org.joda.time.Weeks w = org.joda.time.Weeks.weeksBetween(new DateTime(dataI), new DateTime(dataF));
      return w.getWeeks();
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

   public static synchronized boolean isEmpty(Object obj) {
      if (obj == null) {
         return true;
      } else {
         if (obj instanceof String) {
            return obj.toString().trim().isEmpty() || "null".equals(obj.toString());
         }

         if (obj instanceof StringBuilder) {
            return ((StringBuilder) obj).length() <= 0;
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

         try {
            Class<?> thisClass = Class.forName(obj.getClass().getName());
            if (thisClass.isAnnotationPresent(Entity.class) && thisClass.isAnnotationPresent(Table.class)) {
               Field[] fields = thisClass.getDeclaredFields();
               Object valuePrimayKey = null;
               boolean temId = false;
               for (Field field : fields) {
                  field.setAccessible(true);
                  if (field.isAnnotationPresent(Id.class)) {
                     temId = true;
                     valuePrimayKey = field.get(obj);
                     break;
                  }
               }
               return temId && valuePrimayKey == null;
            }
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         } catch (IllegalAccessException e) {
            e.printStackTrace();
         }

         return false;
      }
   }

   public static synchronized boolean isEmpty(String string) {
      return (string == null || string.trim().isEmpty() || "null".equals(string));
   }

   public static synchronized boolean isEmpty(List lista) {
      return (lista == null || lista.isEmpty());
   }

   public static synchronized boolean isBetween(Integer value, Integer minValue, Integer maxValue) {
      if (value == null || minValue == null || maxValue == null) {
         return false;
      }
      value = value.intValue();
      minValue = minValue.intValue();
      maxValue = maxValue.intValue();

      return (value >= minValue && value <= maxValue);
   }

   public static String safeTrim(String string) {
      if (string != null) {
         return string.trim();
      }
      return null;
   }

   /*Métodos para validar o CNS*/
   //Término

   public static boolean validaCPF(String CPF) {
      if(isEmpty(CPF)){
         return (false);
      }
      CPF = retornaSomenteNumeros(CPF);
      // considera-se erro CPF's formados por uma sequencia de numeros iguais
      if (CPF.equals("00000000000")
            || CPF.equals("11111111111")
            || CPF.equals("22222222222") || CPF.equals("33333333333")
            || CPF.equals("44444444444") || CPF.equals("55555555555")
            || CPF.equals("66666666666") || CPF.equals("77777777777")
            || CPF.equals("88888888888") || CPF.equals("99999999999")
            || (CPF.length() != 11)) {
         return (false);
      }

      char dig10, dig11;
      int sm, i, r, num, peso;

      // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
      try {
         // Calculo do 1o. Digito Verificador
         sm = 0;
         peso = 10;
         for (i = 0; i < 9; i++) {
            // converte o i-esimo caractere do CPF em um numero:
            // por exemplo, transforma o caractere '0' no inteiro 0
            // (48 eh a posicao de '0' na tabela ASCII)
            num = (int) (CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
         }

         r = 11 - (sm % 11);
         if ((r == 10) || (r == 11)) {
            dig10 = '0';
         } else {
            dig10 = (char) (r + 48); // converte no respectivo caractere numerico
         }
         // Calculo do 2o. Digito Verificador
         sm = 0;
         peso = 11;
         for (i = 0; i < 10; i++) {
            num = (int) (CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
         }

         r = 11 - (sm % 11);
         if ((r == 10) || (r == 11)) {
            dig11 = '0';
         } else {
            dig11 = (char) (r + 48);
         }

         // Verifica se os digitos calculados conferem com os digitos informados.
         if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
            return (true);
         } else {
            return (false);
         }
      } catch (InputMismatchException erro) {
         return (false);
      }
   }

   /**
    * @param <T>
    * @param classtoCast
    * @param query
    * @param params
    * @return
    */
   public static <T extends Serializable> List<T> getBuscaGeneric(Class<T> classtoCast, String query, Object... params) {
      Session ses = HibernateUtil.getSessionFactory().openSession();
      try {
         List<T> toReturn = getBuscaGeneric(ses, classtoCast, query, params);
         return toReturn;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      } finally {
         ses.close();
      }
   }

   /**
    * @param <T>
    * @param ses
    * @param classtoCast
    * @param query
    * @param params
    * @return
    */
   public static <T extends Serializable> List<T> getBuscaGeneric(Session ses, Class<T> classtoCast, String query, Object... params) {
      try {
         //fazer consulta sql não hql
         Query qr = ses.createSQLQuery(query).addEntity(classtoCast);
         for (int i = 1; i <= params.length; i++) {
            qr.setParameter(i - 1, params[i - 1]);
         }
         @SuppressWarnings("unchecked")
         List<T> toReturn = qr.list();
         return toReturn;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * @param query
    * @param params
    * @param campos
    * @return
    */
   public static List<Object[]> getQuerySQLGeneric(String query, String[] campos, Type... params) {
      Session ses = HibernateUtil.getSessionFactory().openSession();
      List<Object[]> toReturn = new ArrayList<Object[]>();
      try {
         //fazer consulta sql não hql
         SQLQuery qr = ses.createSQLQuery(query);
         for (int i = 1; i <= params.length; i++) {
            qr.addScalar(campos[i - 1], params[i - 1]);
         }
         toReturn = (List<Object[]>) qr.list();

         ses.beginTransaction().commit();
         return toReturn;
      } catch (Exception e) {
         e.printStackTrace();
         return toReturn;
      } finally {
         ses.close();
      }
   }

   public static List getQuerySQLGeneric_MAP(String query, List<Object[]> listParam) {
      Session ses = HibernateUtil.getSessionFactory().openSession();
      List toReturn = null;
      try {
         toReturn = getQuerySQLGeneric_MAP(ses, query, listParam);
         return toReturn;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      } finally {
         ses.close();
      }
   }

   public static List getQuerySQLGeneric_MAP(Session ses, String query, List<Object[]> listParam) {
      List toReturn = null;
      try {
         //fazer consulta sql não hql
         SQLQuery qr = ses.createSQLQuery(query);
         for (Object[] param : listParam) {
            qr.addScalar(param[0].toString(), (Type) param[1]);
         }
         qr.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         toReturn = qr.list();
         return toReturn;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   public static List<Object[]> getQuerySQLGenericTypeString(String query, String[] campos, Type type) {
      Session ses = HibernateUtil.getSessionFactory().openSession();
      List<Object[]> toReturn = new ArrayList<Object[]>();
      try {
         //fazer consulta sql não hql
         SQLQuery qr = ses.createSQLQuery(query);
         if (campos != null) {
            for (int i = 1; i <= campos.length; i++) {
               qr.addScalar(campos[i - 1], type);
            }
         }
         toReturn = (List<Object[]>) qr.list();

         ses.beginTransaction().commit();
         return toReturn;
      } catch (Exception e) {
         e.printStackTrace();
         return toReturn;
      } finally {
         ses.close();
      }
   }

   public static <T extends Serializable> Object getObjectQuerySQL(Class<T> classtoCast, String query) {
      Session ses = HibernateUtil.getSessionFactory().openSession();
      try {
         //fazer consulta sql não hql
         Query qr = ses.createSQLQuery(query).addEntity(classtoCast);
         return qr.uniqueResult();
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      } finally {
         ses.close();
      }
   }

   public static <T extends Serializable> Object getObjectQuerySQL(Session ses, Class<T> classtoCast, String query) {
      try {
         SQLQuery qr = ses.createSQLQuery(query).addEntity(classtoCast);
         return qr.uniqueResult();
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   public static void addBean(Object obj) {
      Session ses = HibernateUtil.getSessionFactory().openSession();
      try {
         ses.save(obj);
         ses.beginTransaction().commit();
         ses.refresh(obj);
      } catch (ConstraintViolationException e) {
         e.printStackTrace();
      } catch (NullPointerException e) {
         e.printStackTrace();
      } catch (GenericJDBCException e) {
         e.printStackTrace();
      } catch (HibernateException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         ses.close();
      }
   }

   public static void updateBean(Object obj) {
      Session ses = HibernateUtil.getSessionFactory().openSession();
      try {
         //sql normal, não HQL
         ses.update(obj);
         ses.beginTransaction().commit();
         ses.refresh(obj);
      } catch (ConstraintViolationException e) {
         e.printStackTrace();
      } catch (NullPointerException e) {
         e.printStackTrace();
      } catch (GenericJDBCException e) {
         e.printStackTrace();
      } catch (HibernateException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         ses.close();
      }
   }

   public static void RemoveBean(Object obj) {
      Session ses = HibernateUtil.getSessionFactory().openSession();
      Transaction trs = null;
      try {
         trs = ses.beginTransaction();
         //sql normal, não HQL
         ses.delete(obj);
         ses.getTransaction().commit();
      } catch (ConstraintViolationException e) {
         e.printStackTrace();
      } catch (NullPointerException e) {
         e.printStackTrace();
      } catch (GenericJDBCException e) {
         e.printStackTrace();
      } catch (HibernateException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         ses.close();
      }
   }

   public static void deleteInsertUpdateGeneric(String sql) {
      Session ses = HibernateUtil.getSessionFactory().openSession();
      try {
         //sql normal, não HQL
         Query q = ses.createSQLQuery(sql);
         q.executeUpdate();
         ses.beginTransaction().commit();
      } catch (ConstraintViolationException e) {
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", e.getMessage());
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage("removePojo", msg);
      } catch (NullPointerException e) {
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", e.getMessage());
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage("removePojo", msg);
      } catch (GenericJDBCException e) {
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", e.getMessage());
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage("removePojo", msg);
      } catch (HibernateException e) {
         e.printStackTrace();
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", e.getMessage());
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage("removePojo", msg);
      } catch (Exception e) {
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", e.getMessage());
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage("removePojo", msg);
      } finally {
         ses.close();
      }
   }

   public static void addValidationExceptionError(String titu, String descricao) {
      FacesMessage f = new FacesMessage(FacesMessage.SEVERITY_ERROR, titu, descricao);
      FacesContext.getCurrentInstance().addMessage(null, f);
      throw new AbortProcessingException();
   }

   public static Calendar getCalendarPtBr() {
      TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
      Calendar cal = Calendar.getInstance(tz, new Locale("pt", "br"));
      return cal;
   }

   public static synchronized void executeUpdate(String sql) {
      Connection con = null;
      Statement st = null;
      try {
         con = ConexaoMySQL.getConexaoMySQL();
         st = con.createStatement();
         st.executeUpdate(sql);
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         fecharConexao(null, st, con);
      }
   }

   public static synchronized void executeUpdate(String sql, MapJC mapJC) {
      Connection con = null;
      try {
         con = ConexaoMySQL.getConexaoMySQL();
         PreparedStatement pst = con.prepareStatement(sql);
         for (Object key : mapJC.keySet()) {
            pst.setObject((Integer) key, mapJC.getString(key));
         }
         pst.executeUpdate();
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         fecharConexao(null, null, con);
      }
   }

   public static synchronized void fecharConexao(ResultSet rset, Statement stmt, Connection conn) {
      try {
         if (rset != null && !rset.isClosed()) {
            rset.close();
         }
         if (stmt != null && !stmt.isClosed()) {
            stmt.close();
         }
         if (conn != null && !conn.isClosed()) {
            conn.close();
         }
      } catch (Exception e) {
      }
   }

   /**
    * Equivalente ao IN do SQL "WHERE campo IN(1,2,3)" Exemplo:
    * if(funcoesJava.equals("AZUL","AMARELO","VERDE","AZUL")){ // código java }
    * @param param1 O primeiro parâmetro será comparado com os demais
    *               parâmetros.
    * @param param2
    * @return boolean
    * @author Honorato
    */
   public static boolean equals(Object param1, Object... param2) {
      for (Object obj : param2) {
         if (Objects.equals(param1, obj)) {
            return true;
         }
      }
      return false;
   }

   /**
    * Comparação entre datas (<b>não considera a hora</b>).
    * @param date1
    * @param date2
    * @return boolean
    */
   public static boolean compareDate(Date date1, Date date2) {
      return compareDate(date1, date2, false);
   }

   /**
    * Comparação entre datas.
    * @param date1
    * @param date2
    * @param compareTime <b>TRUE</b> considera a hora na comparação,
    *                    <b>FALSE</b> não considera.
    * @return
    */
   public static boolean compareDate(Date date1, Date date2, boolean compareTime) {
      if (date1 == null || date2 == null) {
         return false;
      }
      if (!compareTime) {
         Calendar cal = Calendar.getInstance();
         cal.setTime(date1);
         cal.set(Calendar.HOUR_OF_DAY, 0);
         cal.set(Calendar.MINUTE, 0);
         cal.set(Calendar.SECOND, 0);
         date1 = cal.getTime();

         cal.setTime(date2);
         cal.set(Calendar.HOUR_OF_DAY, 0);
         cal.set(Calendar.MINUTE, 0);
         cal.set(Calendar.SECOND, 0);
         date2 = cal.getTime();
      }
      return date1.compareTo(date2) == 0;
   }

   public static <T> T getManagedBean(Class<T> type) {
      try {
         String[] split = type.toString().split(Pattern.quote("."));
         String key = split[split.length - 1];
         key = key.substring(0, 1).toLowerCase() + key.substring(1, key.length());
         ELContext elContext = FacesContext.getCurrentInstance().getELContext();
         Object managedBean = FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, key);
         if (managedBean == null) {
            managedBean = type.newInstance();
            FacesContext.getCurrentInstance().getApplication().getELResolver().setValue(elContext, null, key, managedBean);
         }
         return (T) managedBean;
      } catch (InstantiationException e) {
         e.printStackTrace();
         return null;
      } catch (IllegalAccessException e) {
         e.printStackTrace();
         return null;
      }
   }

   public static String getLabelComboMultipleSelected(Object[] arrays) {
      int count = 0;
      for (Object obj : arrays) {
         if (obj instanceof Object[]) {
            count += ((Object[]) obj).length;
         } else if (obj instanceof List) {
            count += ((List) obj).size();
         }
      }
      switch (count) {
         case 0:
            return "TODOS";
         case 1:
            return count + " SELECIONADO";
         default:
            return count + " SELECIONADOS";
      }
   }

   public Double doubleUmaCasa(Double value, int casas) {
      BigDecimal valueDecimal = new BigDecimal(value).setScale(casas, RoundingMode.HALF_EVEN);
      return valueDecimal.doubleValue();
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
      passa = passa.replaceAll("  ", " ");
      passa = passa.replaceAll("[-+=*&amp;%$#@!?¿_]", "");
      passa = passa.replaceAll("['\"]", "");
      passa = passa.replaceAll("[<>()\\{\\}]", "");
      passa = passa.replaceAll("['\\\\.,()|/]", "");
      passa = passa.replaceAll("[^!-ÿ]{1}[^ -ÿ]{0,}[^!-ÿ]{1}|[^!-ÿ]{1}", " ");
      return passa;
   }

   public static String retiraNumeros(String stringFonte) {
      if (stringFonte == null) {
         return null;
      }
      String passa = stringFonte;
      passa = passa.replaceAll("[0-9]", "");
      return passa;
   }

   public static boolean validaNome(String nome) {
      if (nome != null && !nome.trim().equals("")) {
         String[] caracteres = {"-", "+", "=", "*", "&amp;", "%", "$", "#", "@", "!", "?", "�", "¿", "_", "<", ">", "(", ")", "|", ".", ",", ";", "\\", "/", "[", "]", "{", "}", "\"", "&"};
         char[] array = nome.toCharArray();
         for (char str : array) {
            if (Arrays.asList(caracteres).contains(String.valueOf(str))) {
               return false;
            }
         }

         nome = retiraCaracteresEspeciais(retiraNumeros(nome)).trim();
         if (nome.length() < 3) {
            return false;
         }
         String[] ns = nome.split(" ");
         if (ns == null || ns.length == 1) {
            return false;
         } else {
            int index = 0;
            for (String val : ns) {
               if (index == 0) {
                  index++;
                  continue;
               }
               if (val.length() == 1) {
                  if (!val.equals("E") && !val.equals("Y")) {
                     return false;
                  }
               }
               index++;
            }
            if (ns.length == 2) {
               String val1 = ns[0];
               String val2 = ns[1];
               if (val1.length() <= 2 && val2.length() <= 2) {
                  return false;
               }
            }

            if (ns.length >= 2) {
               String val1 = ns[0];
               String val2 = ns[1];
               if (val1.length() <= 1 && val2.length() <= 1) {
                  return false;
               }
            }
         }
      }
      return true;
   }

   public static boolean validaNomeSocial(String nome) {
      if (existeNumerosString(nome)) {
         return false;
      } else if (nome.contains(".") || nome.contains(",") || nome.contains(";") || nome.contains("-") || nome.contains("\"") || nome.contains("/")) {
         return false;
      }
      return true;
   }

   public static String removerAcentos(String str) {
      return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
   }

   public static boolean existeNumerosString(String str) {
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

   public static String retornaSomenteNumeros(String str) {
      if (str != null) {
         return str.replaceAll("[^0123456789]", "");
      } else {
         return "";
      }
   }

   public static boolean isCnpjValido(String cnpj) {
      // Guilherme 26-01-2018
      if (!cnpj.substring(0, 1).equals("")) {
         try {
            cnpj = cnpj.replace('.', ' ');//onde há ponto coloca espaço
            cnpj = cnpj.replace('/', ' ');//onde há barra coloca espaço
            cnpj = cnpj.replace('-', ' ');//onde há traço coloca espaço
            cnpj = cnpj.replaceAll(" ", "");//retira espaço
            if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                  || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                  || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                  || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                  || cnpj.equals("88888888888888") || cnpj.equals("99999999999999")
                  || (cnpj.length() != 14)) {
               return (false);
            }

            int soma = 0, dig;
            String cnpj_calc = cnpj.substring(0, 12);
            if (cnpj.length() != 14) {
               return false;
            }
            char[] chr_cnpj = cnpj.toCharArray();
            /* Primeira parte */
            for (int i = 0; i < 4; i++) {
               if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                  soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
               }
            }
            for (int i = 0; i < 8; i++) {
               if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                  soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
               }
            }
            dig = 11 - (soma % 11);
            cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(
                  dig);
            /* Segunda parte */
            soma = 0;
            for (int i = 0; i < 5; i++) {
               if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                  soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
               }
            }
            for (int i = 0; i < 8; i++) {
               if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                  soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
               }
            }
            dig = 11 - (soma % 11);
            cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(
                  dig);
            return cnpj.equals(cnpj_calc);
         } catch (Exception e) {
            return false;
         }
      } else {
         return false;
      }
   }

   public static String ifNullString(Object str) {
      if (str == null) {
         return "";
      }

      return String.valueOf(str);
   }

   public static Object ifNullOrEmpty(Object str, Object retorno) {
      if (jcUtil.isEmpty(str)) {
         return retorno;
      }

      return str;
   }

   public static Boolean ifNullBoolean(Boolean str) {
      if (str == null) {
         return false;
      }

      return str;
   }

   public static Integer ifNullInteger(Integer value, Integer retorno) {
      if (value == null) {
         return retorno;
      }

      return retorno;
   }

   public static synchronized String convertListToString(List lista) {
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


  public static BigDecimal replaceVirgulaPonto(String value) {
      BigDecimal val = new BigDecimal(value.replace(",", "."));
      return val;
   }

  public static synchronized void showDialogMesage(String detail, String sumary, FacesMessage.Severity severity, String... width) {
      if (severity == null) {
         severity = FacesMessage.SEVERITY_ERROR;
      }
      String backgroundColor = "#ec5151";
      if (severity == FacesMessage.SEVERITY_INFO) {
         backgroundColor = "#3094fb";
      }
     PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(severity, sumary, detail));
      PrimeFaces.current().executeScript("$('.ui-message-dialog .ui-dialog-titlebar').css('padding','2px 3px');");
      PrimeFaces.current().executeScript("$('.ui-message-dialog .ui-dialog-titlebar').css('background','none');");
      PrimeFaces.current().executeScript("$('.ui-message-dialog .ui-dialog-titlebar').css('background-color','" + backgroundColor + "');");
      PrimeFaces.current().executeScript("$('.ui-message-dialog .ui-dialog-content').css('padding-top','15px');");
      if (width != null && width.length > 0) {
         PrimeFaces.current().executeScript("$('#primefacesmessagedlg').width(" + width[0] + ");");
      }
   }

   public static synchronized void showDialogMesage(String detail, String sumary, String... width) {
      showDialogMesage(detail, sumary, null, width);
   }

   public static synchronized void setFocusCampoRequired(String nomeCampo, String... message) {
      FacesContext context = FacesContext.getCurrentInstance();
      UIComponent component = context.getViewRoot().findComponent(nomeCampo);
      if (component != null) {
         if (component instanceof org.primefaces.component.calendar.Calendar) {
            ((org.primefaces.component.calendar.Calendar) component).setValid(false);
         } else if (component instanceof org.primefaces.component.inputtext.InputText) {
            ((org.primefaces.component.inputtext.InputText) component).setValid(false);
         } else if (component instanceof org.primefaces.component.autocomplete.AutoComplete) {
            ((org.primefaces.component.autocomplete.AutoComplete) component).setValid(false);
         } else if (component instanceof org.primefaces.component.inputtextarea.InputTextarea) {
            ((org.primefaces.component.inputtextarea.InputTextarea) component).setValid(false);
         } else if (component instanceof org.primefaces.component.inputmask.InputMask) {
            ((org.primefaces.component.inputmask.InputMask) component).setValid(false);
         } else if (component instanceof org.primefaces.component.inputnumber.InputNumber) {
            ((org.primefaces.component.inputnumber.InputNumber) component).setValid(false);
         }
      }
      if(message != null && message.length > 0){
         jcUtil.addMensagem(message[0], FacesMessage.SEVERITY_ERROR,"");
      }
      PrimeFaces.current().ajax().update(nomeCampo);
   }


   public static String mascaraCPF(String cpf) {
      if (isEmpty(cpf)) {
         return "";
      }
      if (cpf.length() != 11) {
         return cpf;
      }
      cpf = removerAcentos(cpf);

      try {
         StringBuilder stringBuilder = new StringBuilder(cpf);
         stringBuilder.insert(3, '.').insert(7, '.').insert(11, '-');
         return stringBuilder.toString();
      } catch (Exception e) {
         return "";
      }
   }

   public static synchronized void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
      try (FileOutputStream outputStream = new FileOutputStream(file)) {
         int read;
         byte[] bytes = new byte[2048];
         while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
         }
      }
   }

   public static synchronized Object jaExiste(String classe, String clausula) {
      Session ses = HibernateUtil.getSessionFactory().openSession();
      try {
         Query q = ses.createQuery("SELECT obj FROM " + classe + " obj WHERE " + clausula).setMaxResults(1);
         return q.uniqueResult();
      } finally {
         ses.close();
      }
   }

   public static void adicionarLogSession(Session ses, int setor, int usuario, String historico) {
      Date data = new Date();
      Calendar cal = Calendar.getInstance();
      String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
      String mac = jcUtil.getSessao("mac") == null ? "" : jcUtil.getSessao("mac").toString();
      String ip = jcUtil.getSessao("IP") == null ? "" : jcUtil.getSessao("IP").toString();
      Auditoria aud = new Auditoria(setor, usuario);
      aud.setAudData(data);
      aud.setAudHora(hora);
      aud.setAudMac(mac);
      aud.setAudHistorico(historico + " IP: " + ip + " MAC: " + mac);
      ses.save(aud);
   }

   public static void adicionarLogSession(Session ses, int setor, int usuario, String historico, JSONArray camposAlterados) {
      Date data = new Date();
      Calendar cal = Calendar.getInstance();
      String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
      String mac = jcUtil.getSessao("mac") == null ? "" : jcUtil.getSessao("mac").toString();
      String ip = jcUtil.getSessao("IP") == null ? "" : jcUtil.getSessao("IP").toString();
      Auditoria aud = new Auditoria(setor, usuario);
      aud.setAudData(data);
      aud.setAudHora(hora);
      aud.setAudMac(mac);
      aud.setAudHistorico(historico + " IP: " + ip + " MAC: " + mac);
      if(camposAlterados != null && camposAlterados.length() > 0){
         try {
            aud.setAudCamposAlterados(camposAlterados.toString(4));
         } catch (JSONException e) {
            e.printStackTrace();
         }
      }
      ses.save(aud);
   }

   public static void adicionarLogSession(Session ses, int setor, int usuario, String historico, String mac, String ip) {
      //Guilherme 29-11-2017 - Criei este outro metodo para ser utilizado em Threads, pois o outro da erro no jcUtil.getSessao()
      Date data = new Date();
      String hora = jcUtil.getHoraCorrente();
      Auditoria aud = new Auditoria(setor, usuario);
      aud.setAudData(data);
      aud.setAudHora(hora);
      aud.setAudMac(mac);
      aud.setAudHistorico(historico + " IP: " + ip + " MAC: " + mac);
      ses.save(aud);
   }
   public static synchronized String getFacesRedirect() {
      return "?faces-redirect=true";
   }

}
