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
    
    
    public GradoAcademicoController() {
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
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year) && (listas_profesores.get(indice).get(i).getContrato().equals("POR JORNADA") || listas_profesores.get(indice).get(i).getContrato().equals("INVESTIGADORES ASOCIADOS")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
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
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year) && (listas_profesores.get(indice).get(i).getContrato().equals("POR JORNADA") || listas_profesores.get(indice).get(i).getContrato().equals("INVESTIGADORES ASOCIADOS")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
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
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year) && (listas_profesores.get(indice).get(i).getContrato().equals("POR JORNADA") || listas_profesores.get(indice).get(i).getContrato().equals("INVESTIGADORES ASOCIADOS")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
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
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year) && (listas_profesores.get(indice).get(i).getContrato().equals("POR JORNADA") || listas_profesores.get(indice).get(i).getContrato().equals("INVESTIGADORES ASOCIADOS")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
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
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year) && (listas_profesores.get(indice).get(i).getContrato().equals("POR JORNADA") || listas_profesores.get(indice).get(i).getContrato().equals("INVESTIGADORES ASOCIADOS")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
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
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year) && (listas_profesores.get(indice).get(i).getContrato().equals("POR JORNADA") || listas_profesores.get(indice).get(i).getContrato().equals("INVESTIGADORES ASOCIADOS")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
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
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year) && (listas_profesores.get(indice).get(i).getContrato().equals("POR JORNADA") || listas_profesores.get(indice).get(i).getContrato().equals("INVESTIGADORES ASOCIADOS")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
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
                if((listas_profesores.get(indice).get(i).getAno_ingreso() <= year) && (listas_profesores.get(indice).get(i).getContrato().equals("POR JORNADA") || listas_profesores.get(indice).get(i).getContrato().equals("INVESTIGADORES ASOCIADOS")) && (listas_profesores.get(indice).get(i).isVigente() == true || listas_profesores.get(indice).get(i).getAnoRetiro() >= year)){
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
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("GradoAcademicoCreated"));
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
