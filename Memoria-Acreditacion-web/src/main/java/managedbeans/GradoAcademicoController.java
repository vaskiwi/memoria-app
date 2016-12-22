package managedbeans;

import entities.GradoAcademico;
import entities.Jerarquia;
import entities.Profesor;
import entities.ProfesorAsignatura;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.GradoAcademicoFacadeLocal;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessionbeans.AsignaturaFacadeLocal;
import sessionbeans.JerarquiaFacadeLocal;
import sessionbeans.ProfesorAsignaturaFacadeLocal;
import sessionbeans.ProfesorFacadeLocal;

@Named("gradoAcademicoController")
@SessionScoped
public class GradoAcademicoController implements Serializable {

    @EJB
    private GradoAcademicoFacadeLocal ejbFacade;
    @EJB
    private ProfesorFacadeLocal profeFacade;
    @EJB
    private JerarquiaFacadeLocal jeraFacade;
    @EJB
    private AsignaturaFacadeLocal asigFacade;
    @EJB
    private ProfesorAsignaturaFacadeLocal profeAsigFacade;
    
    private List<GradoAcademico> items = null;
    private GradoAcademico selected;
    
    private List<gradoContrato> listaGC = null;
    
    private List<gradoHora> listaGH = null;
    
    private List<gradoNum> listaGN = null;
    
    private List<gradoNum> listaGJ = null;
    
    private List<GradoAcademico> lista_nombres = null;
    
    private List<CantJerarquiaGrado> cant_jerar_grado_diurno = null;
    
    private List<CantJerarquiaGrado> cant_jerar_grado_vespertino = null;
    
    private List<GradoCant> grado_cant_ano_1_diurno;
    
    private List<GradoCant> grado_cant_ano_2_diurno;
    
    private List<GradoCant> grado_cant_ano_3_diurno;
    
    private List<GradoCant> grado_cant_ano_4_diurno;
    
    private List<GradoCant> grado_cant_ano_1_vespertino;
    
    private List<GradoCant> grado_cant_ano_2_vespertino;
    
    private List<GradoCant> grado_cant_ano_3_vespertino;
    
    private List<GradoCant> grado_cant_ano_4_vespertino;
    
     /*************************************************
      *               INICIO
                    JERARQUIAS
      *************************************************/   
    
    private List<JerarquiaGrado> listaJT = null;
    private List<JerarquiaGrado> listaJAsoc = null;
    private List<JerarquiaGrado> listaJAsis = null;
    private List<JerarquiaGrado> listaJInst = null;
    private List<JerarquiaGrado> listaJAyud = null;
    private List<JerarquiaGrado> listaJAdj1 = null;
    private List<JerarquiaGrado> listaJAdj2 = null;
    private List<JerarquiaGrado> listaJInst1 = null;
    private List<JerarquiaGrado> listaJInst2 = null;
    private List<JerarquiaGrado> listaJAyudP = null;
    
    
    public GradoAcademicoController() {
    }

    public List<gradoNum> getListaGJ() {
        return listaGJ;
    }

    public void setListaGJ(List<gradoNum> listaGJ) {
        this.listaGJ = listaGJ;
    }

    public List<CantJerarquiaGrado> getCant_jerar_grado_diurno() {
        cant_jerar_grado_diurno = new ArrayList<>();
        items=ejbFacade.findAll();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for (GradoAcademico item : items) {
            for(Jerarquia jerarquia : jerarquias){
                List<Profesor> profesores=profeFacade.findByJerarYGradoYDiurnoVespertino(jerarquia, item,"DIURNO");
                listas_profesores.add(profesores);
            }
        }
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice=0;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            for(int i=0;i<totaljerar;i++){
                for(int j=0;j<listas_profesores.get(indice).size();j++){
                    if((listas_profesores.get(indice).get(j).getAno_ingreso() <= year) && (listas_profesores.get(indice).get(j).isVigente() == true || listas_profesores.get(indice).get(j).getAnoRetiro() >= year)){
                        cont[i]+=1;
                    }
                }
                indice++;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_jerar_grado_diurno.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_jerar_grado_diurno.add(nuevototales);
        return cant_jerar_grado_diurno;
    }

    public void setCant_jerar_grado_diurno(List<CantJerarquiaGrado> cant_jerar_grado_diurno) {
        this.cant_jerar_grado_diurno = cant_jerar_grado_diurno;
    }

    public List<CantJerarquiaGrado> getCant_jerar_grado_vespertino() {
        cant_jerar_grado_vespertino = new ArrayList<>();
        items=ejbFacade.findAll();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for (GradoAcademico item : items) {
            for(Jerarquia jerarquia : jerarquias){
                List<Profesor> profesores=profeFacade.findByJerarYGradoYDiurnoVespertino(jerarquia, item,"VESPERTINO");
                listas_profesores.add(profesores);
            }
        }
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice=0;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            for(int i=0;i<totaljerar;i++){
                for(int j=0;j<listas_profesores.get(indice).size();j++){
                    if((listas_profesores.get(indice).get(j).getAno_ingreso() <= year) && (listas_profesores.get(indice).get(j).isVigente() == true || listas_profesores.get(indice).get(j).getAnoRetiro() >= year)){
                        cont[i]+=1;
                    }
                }
                indice++;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_jerar_grado_vespertino.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_jerar_grado_vespertino.add(nuevototales);
        return cant_jerar_grado_vespertino;
    }

    public void setCant_jerar_grado_vespertino(List<CantJerarquiaGrado> cant_jerar_grado_vespertino) {
        this.cant_jerar_grado_vespertino = cant_jerar_grado_vespertino;
    }

    public List<GradoCant> getGrado_cant_ano_1_diurno() {
        grado_cant_ano_1_diurno = new ArrayList<>();
        items=ejbFacade.findAll();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for (GradoAcademico item : items) {
            List<Profesor> profesores=profeFacade.findByGradoYDiurnoVespertino(item,"DIURNO");
            listas_profesores.add(profesores);
        }
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        int[] cont = new int[4];
        int[] totales = new int[4];
        for (int i=0;i<4;i++){
            cont[i]=0;
        }
        for (int i=0;i<4;i++){
            totales[i]=0;
        }
        int indice=0;
        for (GradoAcademico item : items) {
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getContrato().equals("Completa")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[0]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=10) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[1]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=11 && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=21) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[2]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=22) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[3]+=1;
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_1_diurno.add(nuevo);
            for(int j=0;j<4;j++){
                totales[j]+=cont[j];
                cont[j]=0;
            }
            indice++;
        }
        GradoCant nuevototales = new GradoCant();
        nuevototales.setCant1(totales[0]);
        nuevototales.setCant2(totales[1]);
        nuevototales.setCant3(totales[2]);
        nuevototales.setCant4(totales[3]);
        nuevototales.setGrado("Total");
        grado_cant_ano_1_diurno.add(nuevototales);
        return grado_cant_ano_1_diurno;
    }

    public void setGrado_cant_ano_1_diurno(List<GradoCant> grado_cant_ano_1_diurno) {
        this.grado_cant_ano_1_diurno = grado_cant_ano_1_diurno;
    }

    public List<GradoCant> getGrado_cant_ano_2_diurno() {
        grado_cant_ano_2_diurno = new ArrayList<>();
        items=ejbFacade.findAll();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for (GradoAcademico item : items) {
            List<Profesor> profesores=profeFacade.findByGradoYDiurnoVespertino(item,"DIURNO");
            listas_profesores.add(profesores);
        }
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        int[] cont = new int[4];
        int[] totales = new int[4];
        for (int i=0;i<4;i++){
            cont[i]=0;
        }
        for (int i=0;i<4;i++){
            totales[i]=0;
        }
        int indice=0;
        for (GradoAcademico item : items) {
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getContrato().equals("Completa")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[0]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=10) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[1]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=11 && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=21) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[2]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=22) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[3]+=1;
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_2_diurno.add(nuevo);
            for(int j=0;j<4;j++){
                totales[j]+=cont[j];
                cont[j]=0;
            }
            indice++;
        }
        GradoCant nuevototales = new GradoCant();
        nuevototales.setCant1(totales[0]);
        nuevototales.setCant2(totales[1]);
        nuevototales.setCant3(totales[2]);
        nuevototales.setCant4(totales[3]);
        nuevototales.setGrado("Total");
        grado_cant_ano_2_diurno.add(nuevototales);
        return grado_cant_ano_2_diurno;
    }

    public void setGrado_cant_ano_2_diurno(List<GradoCant> grado_cant_ano_2_diurno) {
        this.grado_cant_ano_2_diurno = grado_cant_ano_2_diurno;
    }

    public List<GradoCant> getGrado_cant_ano_3_diurno() {
        grado_cant_ano_3_diurno = new ArrayList<>();
        items=ejbFacade.findAll();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for (GradoAcademico item : items) {
            List<Profesor> profesores=profeFacade.findByGradoYDiurnoVespertino(item,"DIURNO");
            listas_profesores.add(profesores);
        }
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        int[] cont = new int[4];
        int[] totales = new int[4];
        for (int i=0;i<4;i++){
            cont[i]=0;
        }
        for (int i=0;i<4;i++){
            totales[i]=0;
        }
        int indice=0;
        for (GradoAcademico item : items) {
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getContrato().equals("Completa")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[0]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=10) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[1]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=11 && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=21) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[2]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=22) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[3]+=1;
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_3_diurno.add(nuevo);
            for(int j=0;j<4;j++){
                totales[j]+=cont[j];
                cont[j]=0;
            }
            indice++;
        }
        GradoCant nuevototales = new GradoCant();
        nuevototales.setCant1(totales[0]);
        nuevototales.setCant2(totales[1]);
        nuevototales.setCant3(totales[2]);
        nuevototales.setCant4(totales[3]);
        nuevototales.setGrado("Total");
        grado_cant_ano_3_diurno.add(nuevototales);
        return grado_cant_ano_3_diurno;
    }

    public void setGrado_cant_ano_3_diurno(List<GradoCant> grado_cant_ano_3_diurno) {
        this.grado_cant_ano_3_diurno = grado_cant_ano_3_diurno;
    }

    public List<GradoCant> getGrado_cant_ano_4_diurno() {
        grado_cant_ano_4_diurno = new ArrayList<>();
        items=ejbFacade.findAll();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for (GradoAcademico item : items) {
            List<Profesor> profesores=profeFacade.findByGradoYDiurnoVespertino(item,"DIURNO");
            listas_profesores.add(profesores);
        }
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        int[] cont = new int[4];
        int[] totales = new int[4];
        for (int i=0;i<4;i++){
            cont[i]=0;
        }
        for (int i=0;i<4;i++){
            totales[i]=0;
        }
        int indice=0;
        for (GradoAcademico item : items) {
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getContrato().equals("Completa")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[0]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=10) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[1]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=11 && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=21) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[2]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=22) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[3]+=1;
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_4_diurno.add(nuevo);
            for(int j=0;j<4;j++){
                totales[j]+=cont[j];
                cont[j]=0;
            }
            indice++;
        }
        GradoCant nuevototales = new GradoCant();
        nuevototales.setCant1(totales[0]);
        nuevototales.setCant2(totales[1]);
        nuevototales.setCant3(totales[2]);
        nuevototales.setCant4(totales[3]);
        nuevototales.setGrado("Total");
        grado_cant_ano_4_diurno.add(nuevototales);
        return grado_cant_ano_4_diurno;
    }

    public void setGrado_cant_ano_4_diurno(List<GradoCant> grado_cant_ano_4_diurno) {
        this.grado_cant_ano_4_diurno = grado_cant_ano_4_diurno;
    }

    public List<GradoCant> getGrado_cant_ano_1_vespertino() {
        grado_cant_ano_1_vespertino = new ArrayList<>();
        items=ejbFacade.findAll();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for (GradoAcademico item : items) {
            List<Profesor> profesores=profeFacade.findByGradoYDiurnoVespertino(item,"VESPERTINO");
            listas_profesores.add(profesores);
        }
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        int[] cont = new int[4];
        int[] totales = new int[4];
        for (int i=0;i<4;i++){
            cont[i]=0;
        }
        for (int i=0;i<4;i++){
            totales[i]=0;
        }
        int indice=0;
        for (GradoAcademico item : items) {
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getContrato().equals("Completa")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[0]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=10) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[1]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=11 && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=21) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[2]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=22) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[3]+=1;
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_1_vespertino.add(nuevo);
            for(int j=0;j<4;j++){
                totales[j]+=cont[j];
                cont[j]=0;
            }
            indice++;
        }
        GradoCant nuevototales = new GradoCant();
        nuevototales.setCant1(totales[0]);
        nuevototales.setCant2(totales[1]);
        nuevototales.setCant3(totales[2]);
        nuevototales.setCant4(totales[3]);
        nuevototales.setGrado("Total");
        grado_cant_ano_1_vespertino.add(nuevototales);
        return grado_cant_ano_1_vespertino;
    }

    public void setGrado_cant_ano_1_vespertino(List<GradoCant> grado_cant_ano_1_vespertino) {
        this.grado_cant_ano_1_vespertino = grado_cant_ano_1_vespertino;
    }

    public List<GradoCant> getGrado_cant_ano_2_vespertino() {
        grado_cant_ano_2_vespertino = new ArrayList<>();
        items=ejbFacade.findAll();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for (GradoAcademico item : items) {
            List<Profesor> profesores=profeFacade.findByGradoYDiurnoVespertino(item,"VESPERTINO");
            listas_profesores.add(profesores);
        }
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        int[] cont = new int[4];
        int[] totales = new int[4];
        for (int i=0;i<4;i++){
            cont[i]=0;
        }
        for (int i=0;i<4;i++){
            totales[i]=0;
        }
        int indice=0;
        for (GradoAcademico item : items) {
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getContrato().equals("Completa")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[0]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=10) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[1]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=11 && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=21) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[2]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=22) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[3]+=1;
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_2_vespertino.add(nuevo);
            for(int j=0;j<4;j++){
                totales[j]+=cont[j];
                cont[j]=0;
            }
            indice++;
        }
        GradoCant nuevototales = new GradoCant();
        nuevototales.setCant1(totales[0]);
        nuevototales.setCant2(totales[1]);
        nuevototales.setCant3(totales[2]);
        nuevototales.setCant4(totales[3]);
        nuevototales.setGrado("Total");
        grado_cant_ano_2_vespertino.add(nuevototales);
        return grado_cant_ano_2_vespertino;
    }

    public void setGrado_cant_ano_2_vespertino(List<GradoCant> grado_cant_ano_2_vespertino) {
        this.grado_cant_ano_2_vespertino = grado_cant_ano_2_vespertino;
    }

    public List<GradoCant> getGrado_cant_ano_3_vespertino() {
        grado_cant_ano_3_vespertino = new ArrayList<>();
        items=ejbFacade.findAll();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for (GradoAcademico item : items) {
            List<Profesor> profesores=profeFacade.findByGradoYDiurnoVespertino(item,"VESPERTINO");
            listas_profesores.add(profesores);
        }
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        int[] cont = new int[4];
        int[] totales = new int[4];
        for (int i=0;i<4;i++){
            cont[i]=0;
        }
        for (int i=0;i<4;i++){
            totales[i]=0;
        }
        int indice=0;
        for (GradoAcademico item : items) {
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getContrato().equals("Completa")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[0]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=10) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[1]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=11 && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=21) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[2]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=22) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[3]+=1;
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_3_vespertino.add(nuevo);
            for(int j=0;j<4;j++){
                totales[j]+=cont[j];
                cont[j]=0;
            }
            indice++;
        }
        GradoCant nuevototales = new GradoCant();
        nuevototales.setCant1(totales[0]);
        nuevototales.setCant2(totales[1]);
        nuevototales.setCant3(totales[2]);
        nuevototales.setCant4(totales[3]);
        nuevototales.setGrado("Total");
        grado_cant_ano_3_vespertino.add(nuevototales);
        return grado_cant_ano_3_vespertino;
    }

    public void setGrado_cant_ano_3_vespertino(List<GradoCant> grado_cant_ano_3_vespertino) {
        this.grado_cant_ano_3_vespertino = grado_cant_ano_3_vespertino;
    }

    public List<GradoCant> getGrado_cant_ano_4_vespertino() {
        grado_cant_ano_4_vespertino = new ArrayList<>();
        items=ejbFacade.findAll();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for (GradoAcademico item : items) {
            List<Profesor> profesores=profeFacade.findByGradoYDiurnoVespertino(item,"VESPERTINO");
            listas_profesores.add(profesores);
        }
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        int[] cont = new int[4];
        int[] totales = new int[4];
        for (int i=0;i<4;i++){
            cont[i]=0;
        }
        for (int i=0;i<4;i++){
            totales[i]=0;
        }
        int indice=0;
        for (GradoAcademico item : items) {
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getContrato().equals("Completa")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[0]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=10) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[1]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=11 && listas_profesores.get(indice).get(i).getDedicacion_contratada()<=21) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[2]+=1;
                }
                else if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year && listas_profesores.get(indice).get(i).getDedicacion_contratada()>=22) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
                    cont[3]+=1;
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_4_vespertino.add(nuevo);
            for(int j=0;j<4;j++){
                totales[j]+=cont[j];
                cont[j]=0;
            }
            indice++;
        }
        GradoCant nuevototales = new GradoCant();
        nuevototales.setCant1(totales[0]);
        nuevototales.setCant2(totales[1]);
        nuevototales.setCant3(totales[2]);
        nuevototales.setCant4(totales[3]);
        nuevototales.setGrado("Total");
        grado_cant_ano_4_vespertino.add(nuevototales);
        return grado_cant_ano_4_vespertino;
    }

    public void setGrado_cant_ano_4_vespertino(List<GradoCant> grado_cant_ano_4_vespertino) {
        this.grado_cant_ano_4_vespertino = grado_cant_ano_4_vespertino;
    }
    
    public List<JerarquiaGrado> getListaJT(){
        listaJT = new ArrayList<>();
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        Jerarquia jtitular = (Jerarquia) jeraFacade.findByNombre("TITULAR");
        
        List<Profesor> doctor;
        List<Profesor> magister;
        List<Profesor> titulado;
        
        doctor = profeFacade.findByJerarYGrado(jtitular, gdoctor);
        magister = profeFacade.findByJerarYGrado(jtitular, gmagister);
        titulado = profeFacade.findByJerarYGrado(jtitular, gtitulado);
        
        Date fecha = new Date();
        int year;
        int year2;
        int year3;
        int year4;
        int year5;
        year = fecha.getYear() + 1900;
        year2 = year - 1;
        year3 = year - 2;
        year4 = year - 3;
        year5 = year - 4;
        
        JerarquiaGrado nuevod = new JerarquiaGrado();
        JerarquiaGrado nuevom = new JerarquiaGrado();
        JerarquiaGrado nuevot = new JerarquiaGrado();
        JerarquiaGrado nuevototal = new JerarquiaGrado();
        
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        int cont5 = 0;
        
        for (int i = 0; i < doctor.size(); i++) {
            if((doctor.get(i).getAno_ingreso() <= year5) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year5)){
                cont5+=1;
            }
            if((doctor.get(i).getAno_ingreso() <= year4) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year4)){
                cont4+=1;
            }
            if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
                cont3+=1;
            }
            if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
                cont2+=1;
            }
            if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                    cont1+=1;                    
            }
        }
        nuevod.year1 = cont1;
        nuevod.year2 = cont2;
        nuevod.year3 = cont3;
        nuevod.year4 = cont4;
        nuevod.year5 = cont5;
        
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0; cont5 = 0;
        
        
        for (int i = 0; i < magister.size(); i++) {
            if((magister.get(i).getAno_ingreso() <= year5) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year5)){
                cont5+=1;
            }
            
            if((magister.get(i).getAno_ingreso() <= year4) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year4)){
                cont4+=1;
                }
            if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
                cont3+=1;
            }
            
            if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
                cont2+=1;
                }
            if ((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                    cont1+=1;
                    
                }
            
        }
        
        nuevom.year1 = cont1;
        nuevom.year2 = cont2;
        nuevom.year3 = cont3;
        nuevom.year4 = cont4;
        nuevom.year5 = cont5;
        
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0; cont5 = 0;
        
        for (int i = 0; i < titulado.size(); i++) {
            if((titulado.get(i).getAno_ingreso() <= year5) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year5)){
                cont5+=1;
            }
            if((titulado.get(i).getAno_ingreso() <= year4) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year4)){
                cont4+=1;
            }
            if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
                cont3+=1;
            }
            if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
                cont2+=1;
            }
            if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                    cont1+=1;
                    
            }
           
        }
        
        nuevot.year1 = cont1;
        nuevot.year2 = cont2;
        nuevot.year3 = cont3;
        nuevot.year4 = cont4;
        nuevot.year5 = cont5;
       
        
        nuevod.jerarquia = "Número de docentes Jerarquía Titular con grado de Doctor";
        nuevom.jerarquia = "Número de docentes Jerarquía Titular con grado Magister";
        nuevot.jerarquia = "Número de docentes Jerarquía Titular licenciados o con título profesional";
        nuevototal.jerarquia = "Total docentes Jerarquía Titular";
        nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
        nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
        nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;
        nuevototal.year4 = nuevod.year4 + nuevom.year4 + nuevot.year4;
        nuevototal.year5 = nuevod.year5 + nuevom.year5 + nuevot.year5;
        
        listaJT.add(nuevod);
        listaJT.add(nuevom);
        listaJT.add(nuevot);
        listaJT.add(nuevototal);
        
        return listaJT;        
        
    }
    
    
    public List<JerarquiaGrado> getListaJAsoc(){
    listaJAsoc = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jasociado = (Jerarquia) jeraFacade.findByNombre("ASOCIADO");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jasociado, gdoctor);
    magister = profeFacade.findByJerarYGrado(jasociado, gmagister);
    titulado = profeFacade.findByJerarYGrado(jasociado, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    int year4;
    int year5;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;
    year4 = year - 3;
    year5 = year - 4;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;
    int cont4 = 0;
    int cont5 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year5) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year5)){
            cont5+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year4) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year4)){
            cont4+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
            cont1+=1;
        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;
    nuevod.year4 = cont4;
    nuevod.year5 = cont5;

    cont1 = 0; cont2 = 0; cont3 = 0; cont4 =0; cont5=0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year5) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year5)){
            cont5+=1;
        }
        if((magister.get(i).getAno_ingreso() <= year4) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year4)){
            cont4+=1;
        }
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if ((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;
        }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;
    nuevom.year4 = cont4;
    nuevom.year5 = cont5;

    cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0; cont5 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year5) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year5)){
            cont5+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year4) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year4)){
            cont4+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;
    nuevot.year4 = cont4;
    nuevot.year5 = cont5;


    nuevod.jerarquia = "Número de docentes Jerarquía Asociado con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Asociado con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Asociado licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Asociado";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;
    nuevototal.year4 = nuevod.year4 + nuevom.year4 + nuevot.year4;
    nuevototal.year5 = nuevod.year5 + nuevom.year5 + nuevot.year5;

    listaJAsoc.add(nuevod);
    listaJAsoc.add(nuevom);
    listaJAsoc.add(nuevot);
    listaJAsoc.add(nuevototal);

    return listaJAsoc;        

    }
    
    public List<JerarquiaGrado> getListaJAsis(){
    listaJAsis = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jasistente = (Jerarquia) jeraFacade.findByNombre("ASISTENTE");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jasistente, gdoctor);
    magister = profeFacade.findByJerarYGrado(jasistente, gmagister);
    titulado = profeFacade.findByJerarYGrado(jasistente, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    int year4;
    int year5;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;
    year4 = year - 3;
    year5 = year - 4;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;
    int cont4 = 0;
    int cont5 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year5)  && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year5)){
            cont5+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year4)  && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year4)){
            cont4+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year3)  && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2)  && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year)  && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if ((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2)  && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Asistente con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Asistente  con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Asistente  licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Asistente";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJAsis.add(nuevod);
    listaJAsis.add(nuevom);
    listaJAsis.add(nuevot);
    listaJAsis.add(nuevototal);

    return listaJAsis;        

    } 
    
    
    public List<JerarquiaGrado> getListaJInst(){
    listaJInst = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jinstructor = (Jerarquia) jeraFacade.findByNombre("INSTRUCTOR");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jinstructor, gdoctor);
    magister = profeFacade.findByJerarYGrado(jinstructor, gmagister);
    titulado = profeFacade.findByJerarYGrado(jinstructor, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if ((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Instructor con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Instructor  con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Instructor  licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Instructor";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJInst.add(nuevod);
    listaJInst.add(nuevom);
    listaJInst.add(nuevot);
    listaJInst.add(nuevototal);

    return listaJInst;        

    }
    
    public List<JerarquiaGrado> getListaJInst1(){
    listaJInst1 = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jinstructor1 = (Jerarquia) jeraFacade.findByNombre("INSTRUCTOR CATEGORÍA I");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jinstructor1, gdoctor);
    magister = profeFacade.findByJerarYGrado(jinstructor1, gmagister);
    titulado = profeFacade.findByJerarYGrado(jinstructor1, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if ((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Instructor Categoría I con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Instructor Categoría I con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Instructor Categoría I licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Instructor Categoría I";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJInst1.add(nuevod);
    listaJInst1.add(nuevom);
    listaJInst1.add(nuevot);
    listaJInst1.add(nuevototal);

    return listaJInst1;        

    }
    
    public List<JerarquiaGrado> getListaJInst2(){
    listaJInst2 = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jinstructor2 = (Jerarquia) jeraFacade.findByNombre("INSTRUCTOR CATEGORÍA II");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jinstructor2, gdoctor);
    magister = profeFacade.findByJerarYGrado(jinstructor2, gmagister);
    titulado = profeFacade.findByJerarYGrado(jinstructor2, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Instructor Categoría II con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Instructor Categoría II con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Instructor Categoría II licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Instructor Categoría II";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJInst2.add(nuevod);
    listaJInst2.add(nuevom);
    listaJInst2.add(nuevot);
    listaJInst2.add(nuevototal);

    return listaJInst2;        

    }
    
    public List<JerarquiaGrado> getListaJAyud(){
    listaJAyud = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jayudante = (Jerarquia) jeraFacade.findByNombre("AYUDANTE");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jayudante, gdoctor);
    magister = profeFacade.findByJerarYGrado(jayudante, gmagister);
    titulado = profeFacade.findByJerarYGrado(jayudante, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Ayudante con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Ayudante  con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Ayudante  licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Ayudante";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJAyud.add(nuevod);
    listaJAyud.add(nuevom);
    listaJAyud.add(nuevot);
    listaJAyud.add(nuevototal);

    return listaJAyud;        

    }
    
    
    public List<JerarquiaGrado> getListaJAyudP(){
    listaJAyudP = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jayudantep = (Jerarquia) jeraFacade.findByNombre("AYUDANTE PROFESOR");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jayudantep, gdoctor);
    magister = profeFacade.findByJerarYGrado(jayudantep, gmagister);
    titulado = profeFacade.findByJerarYGrado(jayudantep, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Ayudante Profesor con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Ayudante Profesor con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Ayudante Profesor licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Ayudante Profesor";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJAyudP.add(nuevod);
    listaJAyudP.add(nuevom);
    listaJAyudP.add(nuevot);
    listaJAyudP.add(nuevototal);

    return listaJAyudP;        

    }
    
    public List<JerarquiaGrado> getListaJAdj1(){
    listaJAdj1 = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jadjunto1 = (Jerarquia) jeraFacade.findByNombre("ADJUNTO CATEGORÍA I");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jadjunto1, gdoctor);
    magister = profeFacade.findByJerarYGrado(jadjunto1, gmagister);
    titulado = profeFacade.findByJerarYGrado(jadjunto1, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Adjunto Categoría I con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Adjunto Categoría I con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Adjunto Categoría I licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Adjunto Categoría I";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJAdj1.add(nuevod);
    listaJAdj1.add(nuevom);
    listaJAdj1.add(nuevot);
    listaJAdj1.add(nuevototal);

    return listaJAdj1;        

    }
    
    public List<JerarquiaGrado> getListaJAdj2(){
    listaJAdj2 = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jadjunto2 = (Jerarquia) jeraFacade.findByNombre("ADJUNTO CATEGORÍA II");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jadjunto2, gdoctor);
    magister = profeFacade.findByJerarYGrado(jadjunto2, gmagister);
    titulado = profeFacade.findByJerarYGrado(jadjunto2, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Adjunto Categoría II con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Adjunto Categoría II con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Adjunto Categoría II licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Adjunto Categoría II";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJAdj2.add(nuevod);
    listaJAdj2.add(nuevom);
    listaJAdj2.add(nuevot);
    listaJAdj2.add(nuevototal);

    return listaJAdj2;        

    }
    
 /*************************************************
      *               FIN
                    JERARQUIAS
 *************************************************/   
    
    /***************LISTO*****************************
     * 
     ************************************************/
    
    /**
     * LISTO*****************************
     * @return
     */
    public List<gradoNum> getListaGN() {
        listaGN = new ArrayList<>();
        Date fecha = new Date();
        int year;
        int year2;
        int year3;
        year = fecha.getYear() + 1900;
        System.out.println(year);
        year2 = year - 1;
        year3 = year - 2;
            
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gnotitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        List<Profesor> doctor;
        List<Profesor> magister;
        List<Profesor> titulado;
        List<Profesor> notitulado;

        doctor = profeFacade.findByGrado(gdoctor);
        //System.out.println("Dc " + doctor.get(0).getAno_ingreso() + doctor.get(0).isVigente());
        magister = profeFacade.findByGrado(gmagister);
        titulado = profeFacade.findByGrado(gtitulado);
        notitulado = profeFacade.findByGrado(gnotitulado);
        
        gradoNum nuevod = new gradoNum();
        gradoNum nuevom = new gradoNum();
        gradoNum nuevot = new gradoNum();
        gradoNum nuevont = new gradoNum();
        gradoNum nuevototal = new gradoNum();

        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;

        for (int i = 0; i < doctor.size(); i++) {
            if((doctor.get(i).getAno_ingreso() <= year3) && ((doctor.get(i).getAnoRetiro() >= year3) || doctor.get(i).isVigente() == true)){
                cont3+=1;
            }
            if((doctor.get(i).getAno_ingreso() <= year2) && ((doctor.get(i).getAnoRetiro() >= year2) || doctor.get(i).isVigente() == true)){
                cont2+=1;
                }
            if((doctor.get(i).getAno_ingreso() <= year) && ((doctor.get(i).getAnoRetiro() >= year) || doctor.get(i).isVigente() == true)){
                    cont1+=1;

            }
        }
        nuevod.setCant_prof1(cont3);
        nuevod.setCant_prof2(cont2);
        nuevod.setCant_prof3(cont1);

        cont1 = 0; cont2 = 0; cont3 = 0;


        for (int i = 0; i < magister.size(); i++) {
            if((magister.get(i).getAno_ingreso() <= year3) && ((magister.get(i).getAnoRetiro() >= year3) || magister.get(i).isVigente() == true)){
                cont3+=1;
            }

            if((magister.get(i).getAno_ingreso() <= year2) && ((magister.get(i).getAnoRetiro() >= year2) || magister.get(i).isVigente() == true)){
                cont2+=1;
                }
            if ((magister.get(i).getAno_ingreso() <= year) && ((magister.get(i).getAnoRetiro() >= year) || magister.get(i).isVigente() == true)){
                    cont1+=1;

                }

        }

        nuevom.setCant_prof1(cont3);
        nuevom.setCant_prof2(cont2);
        nuevom.setCant_prof3(cont1);

        cont1 = 0; cont2 = 0; cont3 = 0;

        for (int i = 0; i < titulado.size(); i++) {
            if((titulado.get(i).getAno_ingreso() <= year3) && ((titulado.get(i).getAnoRetiro() >= year3) || titulado.get(i).isVigente() == true)){
                cont3+=1;
            }
            if((titulado.get(i).getAno_ingreso() <= year2) && ((titulado.get(i).getAnoRetiro() >= year2) || titulado.get(i).isVigente() == true)){
                cont2+=1;
                }
            if((titulado.get(i).getAno_ingreso() <= year) && ((titulado.get(i).getAnoRetiro() >= year) || titulado.get(i).isVigente() == true)){
                    cont1+=1;

            }
        }
        nuevot.setCant_prof1(cont3);
        nuevot.setCant_prof2(cont2);
        nuevot.setCant_prof3(cont1);

        cont1 = 0; cont2 = 0; cont3 = 0;
        
        for (int i = 0; i < notitulado.size(); i++) {
            if((notitulado.get(i).getAno_ingreso() <= year3)&& ((notitulado.get(i).getAnoRetiro() >= year3) || notitulado.get(i).isVigente() == true)){
                cont3+=1;
            }
            if((notitulado.get(i).getAno_ingreso() <= year2)&& ((notitulado.get(i).getAnoRetiro() >= year2) || notitulado.get(i).isVigente() == true)){
                cont2+=1;
            }
            if((notitulado.get(i).getAno_ingreso() <= year)&& ((notitulado.get(i).getAnoRetiro() >= year) || notitulado.get(i).isVigente() == true)){
                    cont1+=1;

            }

        }

        nuevont.setCant_prof1(cont3);
        nuevont.setCant_prof2(cont2);
        nuevont.setCant_prof3(cont1);


        nuevod.setGrado("Número de Doctores (incluído PhD)");
        nuevom.setGrado("Número de Magister");
        nuevot.setGrado("Número de Licenciados o Titulados");
        nuevont.setGrado("Número de No Titulados ni Graduados");
        nuevototal.setGrado("TOTAL");
        
        nuevototal.cant_prof1 = nuevod.cant_prof1 + nuevom.cant_prof1 + nuevot.cant_prof1 + nuevont.cant_prof1;
        nuevototal.cant_prof2 = nuevod.cant_prof2 + nuevom.cant_prof2 + nuevot.cant_prof2 + nuevont.cant_prof2;
        nuevototal.cant_prof3 = nuevod.cant_prof3 + nuevom.cant_prof3 + nuevot.cant_prof3 + nuevont.cant_prof3;

        listaGN.add(nuevod);
        listaGN.add(nuevom);
        listaGN.add(nuevot);
        listaGN.add(nuevont);
        listaGN.add(nuevototal);
        System.out.println(listaGN);
    
        return listaGN;
    }

    /***************LISTO*****************************
     * 
     * @return 
     ************************************************/
    
    public List<gradoHora> getListaGH() {
        listaGH = new ArrayList<>();
        Date fecha = new Date();
        int year;
        int year2;
        int year3;
        int year4;
        int year5;
        year = fecha.getYear() + 1900;
        year2 = year - 1;
        year3 = year - 2;
        year4 = year - 3;
        year5 = year - 4;
            
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gnotitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        List<Profesor> doctor;
        List<Profesor> magister;
        List<Profesor> titulado;
        List<Profesor> notitulado;
        List<ProfesorAsignatura> listaprofeasig;

        doctor = profeFacade.findByGrado(gdoctor);
        magister = profeFacade.findByGrado(gmagister);
        titulado = profeFacade.findByGrado(gtitulado);
        notitulado = profeFacade.findByGrado(gnotitulado);
        
        gradoHora nuevod = new gradoHora();
        gradoHora nuevom = new gradoHora();
        gradoHora nuevot = new gradoHora();
        gradoHora nuevont = new gradoHora();
        gradoHora nuevototal = new gradoHora();

        float cont1 = 0;
        float cont2 = 0;
        float cont3 = 0;
        float cont4 = 0;
        float cont5 = 0;

        for (int i = 0; i < doctor.size(); i++) {
                if((doctor.get(i).getAno_ingreso() <= year5) && ((doctor.get(i).getAnoRetiro() >= year5) || doctor.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(doctor.get(i), year5);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont5+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
                if((doctor.get(i).getAno_ingreso() <= year4) && ((doctor.get(i).getAnoRetiro() >= year4) || doctor.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(doctor.get(i), year4);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont4+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
                if((doctor.get(i).getAno_ingreso() <= year3) && ((doctor.get(i).getAnoRetiro() >= year3) || doctor.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(doctor.get(i), year3);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont3+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
                if((doctor.get(i).getAno_ingreso() <= year2) && ((doctor.get(i).getAnoRetiro() >= year2) || doctor.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(doctor.get(i), year2);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont2+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                    }
                if((doctor.get(i).getAno_ingreso() <= year)  && ((doctor.get(i).getAnoRetiro() >= year) || doctor.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(doctor.get(i), year);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont1+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
        }
        nuevod.cant_horas1 = cont5;
        nuevod.cant_horas2 = cont4;
        nuevod.cant_horas3 = cont3;
        nuevod.cant_horas4 = cont2;
        nuevod.cant_horas5 = cont1;

        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0; cont5 = 0;


        for (int i = 0; i < magister.size(); i++) {
                if((magister.get(i).getAno_ingreso() <= year5) && ((magister.get(i).getAnoRetiro() >= year5) || magister.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(magister.get(i), year5);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont5+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
                if((magister.get(i).getAno_ingreso() <= year4) && ((magister.get(i).getAnoRetiro() >= year4) || magister.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(magister.get(i), year4);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont4+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
                if((magister.get(i).getAno_ingreso() <= year3) && ((magister.get(i).getAnoRetiro() >= year3) || magister.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(magister.get(i), year3);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont3+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
                if((magister.get(i).getAno_ingreso() <= year2) && ((magister.get(i).getAnoRetiro() >= year2) || magister.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(magister.get(i), year2);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont2+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
                if((magister.get(i).getAno_ingreso() <= year) && ((magister.get(i).getAnoRetiro() >= year) || magister.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(magister.get(i), year);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont1+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
        }

        nuevom.cant_horas1 = cont3;
        nuevom.cant_horas2 = cont2;
        nuevom.cant_horas3 = cont1;

        cont1 = 0; cont2 = 0; cont3 = 0;

        for (int i = 0; i < titulado.size(); i++) {
                if((titulado.get(i).getAno_ingreso() <= year5)  && ((titulado.get(i).getAnoRetiro() >= year5) || titulado.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(titulado.get(i), year5);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont5+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
                if((titulado.get(i).getAno_ingreso() <= year4) && ((titulado.get(i).getAnoRetiro()>= year4) || titulado.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(titulado.get(i), year4);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont4+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
                if((titulado.get(i).getAno_ingreso() <= year3)  && ((titulado.get(i).getAnoRetiro() >= year3) || titulado.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(titulado.get(i), year3);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont3+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
                if((titulado.get(i).getAno_ingreso() <= year2) && ((titulado.get(i).getAnoRetiro()>= year2) || titulado.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(titulado.get(i), year2);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont2+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
                if((titulado.get(i).getAno_ingreso() <= year) && ((titulado.get(i).getAnoRetiro() >= year) || titulado.get(i).isVigente() == true)){
                    listaprofeasig=profeAsigFacade.findByProfesor(titulado.get(i), year2);
                    for(int j=0;j<listaprofeasig.size();j++){
                        cont2+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                    }
                }
        }
        
        nuevot.cant_horas1 = cont5;
        nuevot.cant_horas2 = cont4;
        nuevot.cant_horas3 = cont3;
        nuevot.cant_horas4 = cont4;
        nuevot.cant_horas5 = cont5;

        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0; cont5 = 0;
        
        for (int i = 0; i < notitulado.size(); i++) {
            if((notitulado.get(i).getAno_ingreso() <= year5) && ((notitulado.get(i).getAnoRetiro() >= year5) || notitulado.get(i).isVigente() == true)){
                listaprofeasig=profeAsigFacade.findByProfesor(notitulado.get(i), year5);
                for(int j=0;j<listaprofeasig.size();j++){
                    cont3+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                }
            }
            if((notitulado.get(i).getAno_ingreso() <= year4) && ((notitulado.get(i).getAnoRetiro() >= year4) || notitulado.get(i).isVigente() == true)){
                listaprofeasig=profeAsigFacade.findByProfesor(notitulado.get(i), year2);
                for(int j=0;j<listaprofeasig.size();j++){
                    cont2+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                }
            }
            if((notitulado.get(i).getAno_ingreso() <= year3) && ((notitulado.get(i).getAnoRetiro() >= year3) || notitulado.get(i).isVigente() == true)){
                listaprofeasig=profeAsigFacade.findByProfesor(notitulado.get(i), year3);
                for(int j=0;j<listaprofeasig.size();j++){
                    cont3+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                }
            }
            if((notitulado.get(i).getAno_ingreso() <= year2) && ((notitulado.get(i).getAnoRetiro() >= year2) || notitulado.get(i).isVigente() == true)){
                listaprofeasig=profeAsigFacade.findByProfesor(notitulado.get(i), year2);
                for(int j=0;j<listaprofeasig.size();j++){
                    cont2+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                }
            }
            if((notitulado.get(i).getAno_ingreso() <= year) && ((notitulado.get(i).getAnoRetiro() >= year) || notitulado.get(i).isVigente() == true)){
                listaprofeasig=profeAsigFacade.findByProfesor(notitulado.get(i), year);
                for(int j=0;j<listaprofeasig.size();j++){
                    cont1+=listaprofeasig.get(j).getId_asignatura().getCant_horas_presenciales();
                }

            }
        }
        nuevont.cant_horas1 = cont5;
        nuevont.cant_horas2 = cont4;
        nuevont.cant_horas3 = cont3;
        nuevont.cant_horas4 = cont2;
        nuevont.cant_horas5 = cont1;

        nuevod.grado = "Cantidad de horas semanales Doctores PhD";
        nuevom.grado = "Cantidad de horas semanales Magister";
        nuevot.grado = "Cantidad de horas semanales Licenciados o Titulados";
        nuevont.grado = "Cantidad de horas semanales No Titulados ni Graduados";
        nuevototal.grado = "TOTAL HORAS";
        
        nuevototal.cant_horas1 = nuevod.cant_horas1 + nuevom.cant_horas1 + nuevot.cant_horas1 + nuevont.cant_horas1;
        nuevototal.cant_horas2 = nuevod.cant_horas2 + nuevom.cant_horas2 + nuevot.cant_horas2 + nuevont.cant_horas2;
        nuevototal.cant_horas3 = nuevod.cant_horas3 + nuevom.cant_horas3 + nuevot.cant_horas3 + nuevont.cant_horas3;
        nuevototal.cant_horas4 = nuevod.cant_horas4 + nuevom.cant_horas4 + nuevot.cant_horas4 + nuevont.cant_horas4;
        nuevototal.cant_horas5 = nuevod.cant_horas5 + nuevom.cant_horas5 + nuevot.cant_horas5 + nuevont.cant_horas5;

        listaGH.add(nuevod);
        listaGH.add(nuevom);
        listaGH.add(nuevot);
        listaGH.add(nuevont);
        listaGH.add(nuevototal);
        
        return listaGH;
    }

    /***************LISTO*****************************
     * 
     * @return 
     ************************************************/
    
    public List<gradoContrato> getListaGC(){
        listaGC = new ArrayList<>();
        int tcc = 0;
        int tch = 0;
        int tcp = 0;
        Date fecha2 = new Date();
            int year;
            int year2;
            int year3;
            year = fecha2.getYear() + 1900;
            
        List<GradoAcademico> total = getFacade().findAll();
        for (int i = 0; i < total.size(); i++) {
            
            gradoContrato grado = new gradoContrato();
            grado.grado = total.get(i).getNombre_grado_academico();
            int cc = 0;
            int ch = 0;
            int cp = 0;
            
            
            List<Profesor> profesores = profeFacade.findByGrado(total.get(i));
            for (int j = 0; j < profesores.size(); j++) {
                if  (profesores.get(j).getAnoRetiro() >= year || profesores.get(j).isVigente() == true){
                    if ("Completa".equals(profesores.get(j).getContrato())){
                        cc+=1;
                        tcc+=1;
                    }
                    if ("Parcial".equals(profesores.get(j).getContrato())){
                        cp+=1;
                        tcp+=1;
                    }
                    if ("Por Hora".equals(profesores.get(j).getContrato())){
                        ch+=1;
                        tch+=1;
                    }
                }
            }
            grado.contratoC = cc;
            grado.contratoH = ch;
            grado.contratoP = cp;
            
            listaGC.add(grado);
        }
        gradoContrato gradot = new gradoContrato();
        gradot.grado = "Total";
        gradot.contratoC = tcc;
        gradot.contratoP = tcp;
        gradot.contratoH = tch;
        
        listaGC.add(gradot);
        
        return listaGC;
    }

    public GradoAcademico getSelected() {
        return selected;
    }

    public void setSelected(GradoAcademico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private GradoAcademicoFacadeLocal getFacade() {
        return ejbFacade;
    }

    public GradoAcademicoFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(GradoAcademicoFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public ProfesorFacadeLocal getProfeFacade() {
        return profeFacade;
    }

    public void setProfeFacade(ProfesorFacadeLocal profeFacade) {
        this.profeFacade = profeFacade;
    }

    public JerarquiaFacadeLocal getJeraFacade() {
        return jeraFacade;
    }

    public void setJeraFacade(JerarquiaFacadeLocal jeraFacade) {
        this.jeraFacade = jeraFacade;
    }

    public AsignaturaFacadeLocal getAsigFacade() {
        return asigFacade;
    }

    public void setAsigFacade(AsignaturaFacadeLocal asigFacade) {
        this.asigFacade = asigFacade;
    }

    public ProfesorAsignaturaFacadeLocal getProfeAsigFacade() {
        return profeAsigFacade;
    }

    public void setProfeAsigFacade(ProfesorAsignaturaFacadeLocal profeAsigFacade) {
        this.profeAsigFacade = profeAsigFacade;
    }

    public List<GradoAcademico> getLista_nombres() {
        return lista_nombres;
    }

    public void setLista_nombres(List<GradoAcademico> lista_nombres) {
        this.lista_nombres = lista_nombres;
    }

    public GradoAcademico prepareCreate() {
        selected = new GradoAcademico();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        FacesContext context = FacesContext.getCurrentInstance();
        lista_nombres = ejbFacade.findByNombrelist(selected.getNombre_grado_academico());
        if(lista_nombres.isEmpty()){
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("JerarquiaCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }
        else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Grado Académico ya existe", null));
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("GradoAcademicoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("GradoAcademicoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<GradoAcademico> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public GradoAcademico getGradoAcademico(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<GradoAcademico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<GradoAcademico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = GradoAcademico.class)
    public static class GradoAcademicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GradoAcademicoController controller = (GradoAcademicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "gradoAcademicoController");
            return controller.getGradoAcademico(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof GradoAcademico) {
                GradoAcademico o = (GradoAcademico) object;
                return getStringKey(o.getId_grado_academico());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), GradoAcademico.class.getName()});
                return null;
            }
        }

    }

}
