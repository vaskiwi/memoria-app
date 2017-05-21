package managedbeans;

import entities.Asignatura;
import entities.Carrera;
import entities.Contrato;
import entities.GradoAcademico;
import entities.Jerarquia;
import entities.Profesor;
import entities.ProfesorAsignatura;
import entities.ProfesorContrato;
import entities.ProfesorGrado;
import entities.ProfesorJerarquia;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.GradoAcademicoFacadeLocal;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import sessionbeans.CarreraFacadeLocal;
import sessionbeans.ContratoFacadeLocal;
import sessionbeans.JerarquiaFacadeLocal;
import sessionbeans.ProfesorAsignaturaFacadeLocal;
import sessionbeans.ProfesorContratoFacadeLocal;
import sessionbeans.ProfesorFacadeLocal;
import sessionbeans.ProfesorGradoFacadeLocal;
import sessionbeans.ProfesorJerarquiaFacadeLocal;

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
    private ContratoFacadeLocal contratoFacade;
    @EJB
    private AsignaturaFacadeLocal asigFacade;
    @EJB
    private ProfesorAsignaturaFacadeLocal profeAsigFacade;
    @EJB
    private ProfesorContratoFacadeLocal profeContFacade;
    
    @EJB
    private ProfesorGradoFacadeLocal profeGradoFacade;
    
    @EJB
    private ProfesorJerarquiaFacadeLocal profeJerarFacade;
    
    @EJB
    private CarreraFacadeLocal carreraFacade;
    
    private List<GradoAcademico> items = null;
    
    private GradoAcademico selected;
    
    private List<GradoAcademico> lista_nombres = null;
    
    private List<CantJerarquiaGrado> cant_ano0_jerar_grado_diurno_civil = null;
    
    private List<CantJerarquiaGrado> cant_ano0_jerar_grado_diurno_ejecu = null;
    
    private List<CantJerarquiaGrado> cant_ano0_jerar_grado_vespertino_civil = null;
    
    private List<CantJerarquiaGrado> cant_ano0_jerar_grado_vespertino_ejecu = null;
    
    private List<CantJerarquiaGrado> cant_ano1_jerar_grado_diurno_civil = null;
    
    private List<CantJerarquiaGrado> cant_ano1_jerar_grado_diurno_ejecu = null;
    
    private List<CantJerarquiaGrado> cant_ano1_jerar_grado_vespertino_civil = null;
    
    private List<CantJerarquiaGrado> cant_ano1_jerar_grado_vespertino_ejecu = null;
    
    private List<CantJerarquiaGrado> cant_ano2_jerar_grado_diurno_civil = null;
    
    private List<CantJerarquiaGrado> cant_ano2_jerar_grado_diurno_ejecu = null;
    
    private List<CantJerarquiaGrado> cant_ano2_jerar_grado_vespertino_civil = null;
    
    private List<CantJerarquiaGrado> cant_ano2_jerar_grado_vespertino_ejecu = null;
    
    private List<CantJerarquiaGrado> cant_ano3_jerar_grado_diurno_civil = null;
    
    private List<CantJerarquiaGrado> cant_ano3_jerar_grado_diurno_ejecu = null;
    
    private List<CantJerarquiaGrado> cant_ano3_jerar_grado_vespertino_civil = null;
    
    private List<CantJerarquiaGrado> cant_ano3_jerar_grado_vespertino_ejecu = null;
    
    private List<CantJerarquiaGrado> cant_ano4_jerar_grado_diurno_civil = null;
    
    private List<CantJerarquiaGrado> cant_ano4_jerar_grado_diurno_ejecu = null;
    
    private List<CantJerarquiaGrado> cant_ano4_jerar_grado_vespertino_civil = null;
    
    private List<CantJerarquiaGrado> cant_ano4_jerar_grado_vespertino_ejecu = null;
    
    private List<GradoCant> grado_cant_ano_0_diurno_civil;
    
    private List<GradoCant> grado_cant_ano_1_diurno_civil;
    
    private List<GradoCant> grado_cant_ano_2_diurno_civil;
    
    private List<GradoCant> grado_cant_ano_3_diurno_civil;
    
    private List<GradoCant> grado_cant_ano_4_diurno_civil;
    
    private List<GradoCant> grado_cant_ano_0_vespertino_civil;
    
    private List<GradoCant> grado_cant_ano_1_vespertino_civil;
    
    private List<GradoCant> grado_cant_ano_2_vespertino_civil;
    
    private List<GradoCant> grado_cant_ano_3_vespertino_civil;
    
    private List<GradoCant> grado_cant_ano_4_vespertino_civil;
    
    private List<GradoCant> grado_cant_ano_0_diurno_ejecu;
    
    private List<GradoCant> grado_cant_ano_1_diurno_ejecu;
    
    private List<GradoCant> grado_cant_ano_2_diurno_ejecu;
    
    private List<GradoCant> grado_cant_ano_3_diurno_ejecu;
    
    private List<GradoCant> grado_cant_ano_4_diurno_ejecu;
    
    private List<GradoCant> grado_cant_ano_0_vespertino_ejecu;
    
    private List<GradoCant> grado_cant_ano_1_vespertino_ejecu;
    
    private List<GradoCant> grado_cant_ano_2_vespertino_ejecu;
    
    private List<GradoCant> grado_cant_ano_3_vespertino_ejecu;
    
    private List<GradoCant> grado_cant_ano_4_vespertino_ejecu;
    
    private List<Asignatura> asignaturas;
    
    private List<ProfesorAsignatura> profesores_seleccionados;
    
    private List<ProfesorAsignatura> lista_asignaturas;
    
    private List<ProfesorGrado> profesor_grado;
    
    private List<ProfesorJerarquia> profesor_jerarquia;
    
    private List<ProfesorContrato> profesor_contrato;
    
    private Carrera carrera_profesor;
    
    private Contrato contrato_profesor;
    
    private List<ProfesorGrado> ano0_diurno_civil = null;
    
    private List<ProfesorGrado> ano0_vespertino_civil = null;
    
    private List<ProfesorGrado> ano0_diurno_ejecu = null;
    
    private List<ProfesorGrado> ano0_vespertino_ejecu = null;
    
    private List<ProfesorGrado> ano1_diurno_civil = null;
    
    private List<ProfesorGrado> ano1_vespertino_civil = null;
    
    private List<ProfesorGrado> ano1_diurno_ejecu = null;
    
    private List<ProfesorGrado> ano1_vespertino_ejecu = null;
    
     private List<ProfesorGrado> ano2_diurno_civil = null;
    
    private List<ProfesorGrado> ano2_vespertino_civil = null;
    
    private List<ProfesorGrado> ano2_diurno_ejecu = null;
    
    private List<ProfesorGrado> ano2_vespertino_ejecu = null;
    
     private List<ProfesorGrado> ano3_diurno_civil = null;
    
    private List<ProfesorGrado> ano3_vespertino_civil = null;
    
    private List<ProfesorGrado> ano3_diurno_ejecu = null;
    
    private List<ProfesorGrado> ano3_vespertino_ejecu = null;
    
    private List<ProfesorGrado> ano4_diurno_civil = null;
    
    private List<ProfesorGrado> ano4_vespertino_civil = null;
    
    private List<ProfesorGrado> ano4_diurno_ejecu = null;
    
    private List<ProfesorGrado> ano4_vespertino_ejecu = null;
    
    private List<ProfesorJerarquia> ano0_jerar_diurno_civil = null;
    
    private List<ProfesorJerarquia> ano0_jerar_vespertino_civil = null;
    
    private List<ProfesorJerarquia> ano0_jerar_diurno_ejecu = null;
    
    private List<ProfesorJerarquia> ano0_jerar_vespertino_ejecu = null;
    
    private List<ProfesorJerarquia> ano1_jerar_diurno_civil = null;
    
    private List<ProfesorJerarquia> ano1_jerar_vespertino_civil = null;
    
    private List<ProfesorJerarquia> ano1_jerar_diurno_ejecu = null;
    
    private List<ProfesorJerarquia> ano1_jerar_vespertino_ejecu = null;
    
    private List<ProfesorJerarquia> ano2_jerar_diurno_civil = null;
    
    private List<ProfesorJerarquia> ano2_jerar_vespertino_civil = null;
    
    private List<ProfesorJerarquia> ano2_jerar_diurno_ejecu = null;
    
    private List<ProfesorJerarquia> ano2_jerar_vespertino_ejecu = null;
    
    private List<ProfesorJerarquia> ano3_jerar_diurno_civil = null;
    
    private List<ProfesorJerarquia> ano3_jerar_vespertino_civil = null;
    
    private List<ProfesorJerarquia> ano3_jerar_diurno_ejecu = null;
    
    private List<ProfesorJerarquia> ano3_jerar_vespertino_ejecu = null;
    
    private List<ProfesorJerarquia> ano4_jerar_diurno_civil = null;
    
    private List<ProfesorJerarquia> ano4_jerar_vespertino_civil = null;
    
    private List<ProfesorJerarquia> ano4_jerar_diurno_ejecu = null;
    
    private List<ProfesorJerarquia> ano4_jerar_vespertino_ejecu = null;
    
    private List<ProfesorContrato> ano0_cont_diurno_civil = null;
    
    private List<ProfesorContrato> ano0_cont_vespertino_civil = null;
    
    private List<ProfesorContrato> ano0_cont_diurno_ejecu = null;
    
    private List<ProfesorContrato> ano0_cont_vespertino_ejecu = null;
    
    private List<ProfesorContrato> ano1_cont_diurno_civil = null;
    
    private List<ProfesorContrato> ano1_cont_vespertino_civil = null;
    
    private List<ProfesorContrato> ano1_cont_diurno_ejecu = null;
    
    private List<ProfesorContrato> ano1_cont_vespertino_ejecu = null;
    
    private List<ProfesorContrato> ano2_cont_diurno_civil = null;
    
    private List<ProfesorContrato> ano2_cont_vespertino_civil = null;
    
    private List<ProfesorContrato> ano2_cont_diurno_ejecu = null;
    
    private List<ProfesorContrato> ano2_cont_vespertino_ejecu = null;
    
    private List<ProfesorContrato> ano3_cont_diurno_civil = null;
    
    private List<ProfesorContrato> ano3_cont_vespertino_civil = null;
    
    private List<ProfesorContrato> ano3_cont_diurno_ejecu = null;
    
    private List<ProfesorContrato> ano3_cont_vespertino_ejecu = null;
    
    private List<ProfesorContrato> ano4_cont_diurno_civil = null;
    
    private List<ProfesorContrato> ano4_cont_vespertino_civil = null;
    
    private List<ProfesorContrato> ano4_cont_diurno_ejecu = null;
    
    private List<ProfesorContrato> ano4_cont_vespertino_ejecu = null;
    
    public GradoAcademicoController() {
    }

    public List<ProfesorGrado> getAno0_diurno_civil() {
        ano0_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_civil_diurno.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano0_diurno_civil.add(prof_gra);
            }
        }
        return ano0_diurno_civil;
    }

    public List<ProfesorGrado> getAno0_vespertino_civil() {
        ano0_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_civil_vespertino.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano0_vespertino_civil.add(prof_gra);
            }
        }
        return ano0_vespertino_civil;
    }

    public void setAno0_vespertino_civil(List<ProfesorGrado> ano0_vespertino_civil) {
        this.ano0_vespertino_civil = ano0_vespertino_civil;
    }

    public List<ProfesorGrado> getAno0_diurno_ejecu() {
        ano0_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_ejecu_diurno.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano0_diurno_ejecu.add(prof_gra);
            }
        }
        return ano0_diurno_ejecu;
    }

    public void setAno0_diurno_ejecu(List<ProfesorGrado> ano0_diurno_ejecu) {
        this.ano0_diurno_ejecu = ano0_diurno_ejecu;
    }

    public List<ProfesorGrado> getAno0_vespertino_ejecu() {
        ano0_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_ejecu_vespertino.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano0_vespertino_ejecu.add(prof_gra);
            }
        }
        return ano0_vespertino_ejecu;
    }

    public void setAno0_vespertino_ejecu(List<ProfesorGrado> ano0_vespertino_ejecu) {
        this.ano0_vespertino_ejecu = ano0_vespertino_ejecu;
    }

    public List<ProfesorGrado> getAno1_diurno_civil() {
        ano1_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_civil_diurno.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano1_diurno_civil.add(prof_gra);
            }
        }
        return ano1_diurno_civil;
    }

    public void setAno1_diurno_civil(List<ProfesorGrado> ano1_diurno_civil) {
        this.ano1_diurno_civil = ano1_diurno_civil;
    }

    public List<ProfesorGrado> getAno1_vespertino_civil() {
        ano1_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_civil_vespertino.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano1_vespertino_civil.add(prof_gra);
            }
        }
        return ano1_vespertino_civil;
    }

    public void setAno1_vespertino_civil(List<ProfesorGrado> ano1_vespertino_civil) {
        this.ano1_vespertino_civil = ano1_vespertino_civil;
    }

    public List<ProfesorGrado> getAno1_diurno_ejecu() {
        ano1_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_ejecu_diurno.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano1_diurno_ejecu.add(prof_gra);
            }
        }
        return ano1_diurno_ejecu;
    }

    public void setAno1_diurno_ejecu(List<ProfesorGrado> ano1_diurno_ejecu) {
        this.ano1_diurno_ejecu = ano1_diurno_ejecu;
    }

    public List<ProfesorGrado> getAno1_vespertino_ejecu() {
        ano1_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_ejecu_vespertino.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano1_vespertino_ejecu.add(prof_gra);
            }
        }
        return ano1_vespertino_ejecu;
    }

    public void setAno1_vespertino_ejecu(List<ProfesorGrado> ano1_vespertino_ejecu) {
        this.ano1_vespertino_ejecu = ano1_vespertino_ejecu;
    }

    public List<ProfesorGrado> getAno2_diurno_civil() {
        ano2_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_civil_diurno.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano2_diurno_civil.add(prof_gra);
            }
        }
        return ano2_diurno_civil;
    }

    public void setAno2_diurno_civil(List<ProfesorGrado> ano2_diurno_civil) {
        this.ano2_diurno_civil = ano2_diurno_civil;
    }

    public List<ProfesorGrado> getAno2_vespertino_civil() {
        ano2_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_civil_vespertino.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano2_vespertino_civil.add(prof_gra);
            }
        }
        return ano2_vespertino_civil;
    }

    public void setAno2_vespertino_civil(List<ProfesorGrado> ano2_vespertino_civil) {
        this.ano2_vespertino_civil = ano2_vespertino_civil;
    }

    public List<ProfesorGrado> getAno2_diurno_ejecu() {
        ano2_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_ejecu_diurno.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano2_diurno_ejecu.add(prof_gra);
            }
        }
        return ano2_diurno_ejecu;
    }

    public void setAno2_diurno_ejecu(List<ProfesorGrado> ano2_diurno_ejecu) {
        this.ano2_diurno_ejecu = ano2_diurno_ejecu;
    }

    public List<ProfesorGrado> getAno2_vespertino_ejecu() {
        ano2_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_ejecu_vespertino.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano2_vespertino_ejecu.add(prof_gra);
            }
        }
        return ano2_vespertino_ejecu;
    }

    public void setAno2_vespertino_ejecu(List<ProfesorGrado> ano2_vespertino_ejecu) {
        this.ano2_vespertino_ejecu = ano2_vespertino_ejecu;
    }

    public List<ProfesorGrado> getAno3_diurno_civil() {
        ano3_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_civil_diurno.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano3_diurno_civil.add(prof_gra);
            }
        }
        return ano3_diurno_civil;
    }

    public void setAno3_diurno_civil(List<ProfesorGrado> ano3_diurno_civil) {
        this.ano3_diurno_civil = ano3_diurno_civil;
    }

    public List<ProfesorGrado> getAno3_vespertino_civil() {
        ano3_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_civil_vespertino.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano3_vespertino_civil.add(prof_gra);
            }
        }
        return ano3_vespertino_civil;
    }

    public void setAno3_vespertino_civil(List<ProfesorGrado> ano3_vespertino_civil) {
        this.ano3_vespertino_civil = ano3_vespertino_civil;
    }

    public List<ProfesorGrado> getAno3_diurno_ejecu() {
        ano3_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_ejecu_diurno.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano3_diurno_ejecu.add(prof_gra);
            }
        }
        return ano3_diurno_ejecu;
    }

    public void setAno3_diurno_ejecu(List<ProfesorGrado> ano3_diurno_ejecu) {
        this.ano3_diurno_ejecu = ano3_diurno_ejecu;
    }

    public List<ProfesorGrado> getAno3_vespertino_ejecu() {
        ano3_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_ejecu_vespertino.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano3_vespertino_ejecu.add(prof_gra);
            }
        }
        return ano3_vespertino_ejecu;
    }

    public void setAno3_vespertino_ejecu(List<ProfesorGrado> ano3_vespertino_ejecu) {
        this.ano3_vespertino_ejecu = ano3_vespertino_ejecu;
    }

    public List<ProfesorGrado> getAno4_diurno_civil() {
        ano4_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_civil_diurno.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano4_diurno_civil.add(prof_gra);
            }
        }
        return ano4_diurno_civil;
    }

    public void setAno4_diurno_civil(List<ProfesorGrado> ano4_diurno_civil) {
        this.ano4_diurno_civil = ano4_diurno_civil;
    }

    public List<ProfesorGrado> getAno4_vespertino_civil() {
        ano4_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_civil_vespertino.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano4_vespertino_civil.add(prof_gra);
            }
        }
        return ano4_vespertino_civil;
    }

    public void setAno4_vespertino_civil(List<ProfesorGrado> ano4_vespertino_civil) {
        this.ano4_vespertino_civil = ano4_vespertino_civil;
    }

    public List<ProfesorGrado> getAno4_diurno_ejecu() {
        ano4_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_ejecu_diurno.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano4_diurno_ejecu.add(prof_gra);
            }
        }
        return ano4_diurno_ejecu;
    }

    public void setAno4_diurno_ejecu(List<ProfesorGrado> ano4_diurno_ejecu) {
        this.ano4_diurno_ejecu = ano4_diurno_ejecu;
    }

    public List<ProfesorGrado> getAno4_vespertino_ejecu() {
        ano4_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (GradoAcademico item : items) {
            profesor_grado=profeGradoFacade.findByGrado(item, year);
            List<ProfesorGrado> lista_grado_actual= new ArrayList<>();
            for(ProfesorGrado profe_grado: profesor_grado){
                if(lista_profesor_ejecu_vespertino.contains(profe_grado.getRut_profesor())){
                    lista_grado_actual.add(profe_grado);
                }
            }
            Collections.sort(lista_grado_actual, new Comparator<ProfesorGrado>(){
            @Override
            public int compare(ProfesorGrado o1, ProfesorGrado o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorGrado prof_gra: lista_grado_actual){
                ano4_vespertino_ejecu.add(prof_gra);
            }
        }
        return ano4_vespertino_ejecu;
    }

    public void setAno4_vespertino_ejecu(List<ProfesorGrado> ano4_vespertino_ejecu) {
        this.ano4_vespertino_ejecu = ano4_vespertino_ejecu;
    }

    public List<ProfesorJerarquia> getAno0_jerar_diurno_civil() {
        ano0_jerar_diurno_civil = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_civil_diurno.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano0_jerar_diurno_civil.add(prof_jerar);
            }
        }
        return ano0_jerar_diurno_civil;
    }

    public void setAno0_jerar_diurno_civil(List<ProfesorJerarquia> ano0_jerar_diurno_civil) {
        this.ano0_jerar_diurno_civil = ano0_jerar_diurno_civil;
    }

    public List<ProfesorJerarquia> getAno0_jerar_vespertino_civil() {
        ano0_jerar_vespertino_civil = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_civil_vespertino.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano0_jerar_vespertino_civil.add(prof_jerar);
            }
        }
        return ano0_jerar_vespertino_civil;
    }

    public void setAno0_jerar_vespertino_civil(List<ProfesorJerarquia> ano0_jerar_vespertino_civil) {
        this.ano0_jerar_vespertino_civil = ano0_jerar_vespertino_civil;
    }

    public List<ProfesorJerarquia> getAno0_jerar_diurno_ejecu() {
        ano0_jerar_diurno_ejecu = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_ejecu_diurno.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano0_jerar_diurno_ejecu.add(prof_jerar);
            }
        }
        return ano0_jerar_diurno_ejecu;
    }

    public void setAno0_jerar_diurno_ejecu(List<ProfesorJerarquia> ano0_jerar_diurno_ejecu) {
        this.ano0_jerar_diurno_ejecu = ano0_jerar_diurno_ejecu;
    }

    public List<ProfesorJerarquia> getAno0_jerar_vespertino_ejecu() {
        ano0_jerar_vespertino_ejecu = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_ejecu_vespertino.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano0_jerar_vespertino_ejecu.add(prof_jerar);
            }
        }
        return ano0_jerar_vespertino_ejecu;
    }

    public void setAno0_jerar_vespertino_ejecu(List<ProfesorJerarquia> ano0_jerar_vespertino_ejecu) {
        this.ano0_jerar_vespertino_ejecu = ano0_jerar_vespertino_ejecu;
    }

    public List<ProfesorJerarquia> getAno1_jerar_diurno_civil() {
        ano1_jerar_diurno_civil = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_civil_diurno.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano1_jerar_diurno_civil.add(prof_jerar);
            }
        }
        return ano1_jerar_diurno_civil;
    }

    public void setAno1_jerar_diurno_civil(List<ProfesorJerarquia> ano1_jerar_diurno_civil) {
        this.ano1_jerar_diurno_civil = ano1_jerar_diurno_civil;
    }

    public List<ProfesorJerarquia> getAno1_jerar_vespertino_civil() {
        ano1_jerar_vespertino_civil = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_civil_vespertino.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano1_jerar_vespertino_civil.add(prof_jerar);
            }
        }
        return ano1_jerar_vespertino_civil;
    }

    public void setAno1_jerar_vespertino_civil(List<ProfesorJerarquia> ano1_jerar_vespertino_civil) {
        this.ano1_jerar_vespertino_civil = ano1_jerar_vespertino_civil;
    }

    public List<ProfesorJerarquia> getAno1_jerar_diurno_ejecu() {
        ano1_jerar_diurno_ejecu = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_ejecu_diurno.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano1_jerar_diurno_ejecu.add(prof_jerar);
            }
        }
        return ano1_jerar_diurno_ejecu;
    }

    public void setAno1_jerar_diurno_ejecu(List<ProfesorJerarquia> ano1_jerar_diurno_ejecu) {
        this.ano1_jerar_diurno_ejecu = ano1_jerar_diurno_ejecu;
    }

    public List<ProfesorJerarquia> getAno1_jerar_vespertino_ejecu() {
        ano1_jerar_vespertino_ejecu = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_ejecu_vespertino.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano1_jerar_vespertino_ejecu.add(prof_jerar);
            }
        }
        return ano1_jerar_vespertino_ejecu;
    }

    public void setAno1_jerar_vespertino_ejecu(List<ProfesorJerarquia> ano1_jerar_vespertino_ejecu) {
        this.ano1_jerar_vespertino_ejecu = ano1_jerar_vespertino_ejecu;
    }

    public List<ProfesorJerarquia> getAno2_jerar_diurno_civil() {
        ano2_jerar_diurno_civil = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_civil_diurno.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano2_jerar_diurno_civil.add(prof_jerar);
            }
        }
        return ano2_jerar_diurno_civil;
    }

    public void setAno2_jerar_diurno_civil(List<ProfesorJerarquia> ano2_jerar_diurno_civil) {
        this.ano2_jerar_diurno_civil = ano2_jerar_diurno_civil;
    }

    public List<ProfesorJerarquia> getAno2_jerar_vespertino_civil() {
        ano2_jerar_vespertino_civil = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_civil_vespertino.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano2_jerar_vespertino_civil.add(prof_jerar);
            }
        }
        return ano2_jerar_vespertino_civil;
    }

    public void setAno2_jerar_vespertino_civil(List<ProfesorJerarquia> ano2_jerar_vespertino_civil) {
        this.ano2_jerar_vespertino_civil = ano2_jerar_vespertino_civil;
    }

    public List<ProfesorJerarquia> getAno2_jerar_diurno_ejecu() {
        ano2_jerar_diurno_ejecu = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_ejecu_diurno.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano2_jerar_diurno_ejecu.add(prof_jerar);
            }
        }
        return ano2_jerar_diurno_ejecu;
    }

    public void setAno2_jerar_diurno_ejecu(List<ProfesorJerarquia> ano2_jerar_diurno_ejecu) {
        this.ano2_jerar_diurno_ejecu = ano2_jerar_diurno_ejecu;
    }

    public List<ProfesorJerarquia> getAno2_jerar_vespertino_ejecu() {
        ano2_jerar_vespertino_ejecu = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_ejecu_vespertino.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano2_jerar_vespertino_ejecu.add(prof_jerar);
            }
        }
        return ano2_jerar_vespertino_ejecu;
    }

    public void setAno2_jerar_vespertino_ejecu(List<ProfesorJerarquia> ano2_jerar_vespertino_ejecu) {
        this.ano2_jerar_vespertino_ejecu = ano2_jerar_vespertino_ejecu;
    }

    public List<ProfesorJerarquia> getAno3_jerar_diurno_civil() {
        ano3_jerar_diurno_civil = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_civil_diurno.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano3_jerar_diurno_civil.add(prof_jerar);
            }
        }
        return ano3_jerar_diurno_civil;
    }

    public void setAno3_jerar_diurno_civil(List<ProfesorJerarquia> ano3_jerar_diurno_civil) {
        this.ano3_jerar_diurno_civil = ano3_jerar_diurno_civil;
    }

    public List<ProfesorJerarquia> getAno3_jerar_vespertino_civil() {
        ano3_jerar_vespertino_civil = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_civil_vespertino.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano3_jerar_vespertino_civil.add(prof_jerar);
            }
        }
        return ano3_jerar_vespertino_civil;
    }

    public void setAno3_jerar_vespertino_civil(List<ProfesorJerarquia> ano3_jerar_vespertino_civil) {
        this.ano3_jerar_vespertino_civil = ano3_jerar_vespertino_civil;
    }

    public List<ProfesorJerarquia> getAno3_jerar_diurno_ejecu() {
        ano3_jerar_diurno_ejecu = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_ejecu_diurno.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano3_jerar_diurno_ejecu.add(prof_jerar);
            }
        }
        return ano3_jerar_diurno_ejecu;
    }

    public void setAno3_jerar_diurno_ejecu(List<ProfesorJerarquia> ano3_jerar_diurno_ejecu) {
        this.ano3_jerar_diurno_ejecu = ano3_jerar_diurno_ejecu;
    }

    public List<ProfesorJerarquia> getAno3_jerar_vespertino_ejecu() {
        ano3_jerar_vespertino_ejecu = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_ejecu_vespertino.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano3_jerar_vespertino_ejecu.add(prof_jerar);
            }
        }
        return ano3_jerar_vespertino_ejecu;
    }

    public void setAno3_jerar_vespertino_ejecu(List<ProfesorJerarquia> ano3_jerar_vespertino_ejecu) {
        this.ano3_jerar_vespertino_ejecu = ano3_jerar_vespertino_ejecu;
    }

    public List<ProfesorJerarquia> getAno4_jerar_diurno_civil() {
        ano4_jerar_diurno_civil = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_civil_diurno.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano4_jerar_diurno_civil.add(prof_jerar);
            }
        }
        return ano4_jerar_diurno_civil;
    }

    public void setAno4_jerar_diurno_civil(List<ProfesorJerarquia> ano4_jerar_diurno_civil) {
        this.ano4_jerar_diurno_civil = ano4_jerar_diurno_civil;
    }

    public List<ProfesorJerarquia> getAno4_jerar_vespertino_civil() {
        ano4_jerar_vespertino_civil = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_civil_vespertino.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano4_jerar_vespertino_civil.add(prof_jerar);
            }
        }
        return ano4_jerar_vespertino_civil;
    }

    public void setAno4_jerar_vespertino_civil(List<ProfesorJerarquia> ano4_jerar_vespertino_civil) {
        this.ano4_jerar_vespertino_civil = ano4_jerar_vespertino_civil;
    }

    public List<ProfesorJerarquia> getAno4_jerar_diurno_ejecu() {
        ano4_jerar_diurno_ejecu = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_ejecu_diurno.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano4_jerar_diurno_ejecu.add(prof_jerar);
            }
        }
        return ano4_jerar_diurno_ejecu;
    }

    public void setAno4_jerar_diurno_ejecu(List<ProfesorJerarquia> ano4_jerar_diurno_ejecu) {
        this.ano4_jerar_diurno_ejecu = ano4_jerar_diurno_ejecu;
    }

    public List<ProfesorJerarquia> getAno4_jerar_vespertino_ejecu() {
        ano4_jerar_vespertino_ejecu = new ArrayList<>();
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Jerarquia jerarquia : jerarquias) {
            profesor_jerarquia=profeJerarFacade.findByJerarquia(jerarquia, year);
            List<ProfesorJerarquia> lista_jerarquia_actual= new ArrayList<>();
            for(ProfesorJerarquia profe_jerarquia: profesor_jerarquia){
                if(lista_profesor_ejecu_vespertino.contains(profe_jerarquia.getRut_profesor())){
                    lista_jerarquia_actual.add(profe_jerarquia);
                }
            }
            Collections.sort(lista_jerarquia_actual, new Comparator<ProfesorJerarquia>(){
            @Override
            public int compare(ProfesorJerarquia o1, ProfesorJerarquia o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorJerarquia prof_jerar: lista_jerarquia_actual){
                ano4_jerar_vespertino_ejecu.add(prof_jerar);
            }
        }
        return ano4_jerar_vespertino_ejecu;
    }

    public void setAno4_jerar_vespertino_ejecu(List<ProfesorJerarquia> ano4_jerar_vespertino_ejecu) {
        this.ano4_jerar_vespertino_ejecu = ano4_jerar_vespertino_ejecu;
    }  

    public List<ProfesorContrato> getAno0_cont_diurno_civil() {
        ano0_cont_diurno_civil = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_civil_diurno.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano0_cont_diurno_civil.add(prof_cont);
            }
        }
        return ano0_cont_diurno_civil;
    }

    public void setAno0_cont_diurno_civil(List<ProfesorContrato> ano0_cont_diurno_civil) {
        this.ano0_cont_diurno_civil = ano0_cont_diurno_civil;
    }

    public List<ProfesorContrato> getAno0_cont_vespertino_civil() {
        ano0_cont_vespertino_civil = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_civil_vespertino.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano0_cont_vespertino_civil.add(prof_cont);
            }
        }
        return ano0_cont_vespertino_civil;
    }

    public void setAno0_cont_vespertino_civil(List<ProfesorContrato> ano0_cont_vespertino_civil) {
        this.ano0_cont_vespertino_civil = ano0_cont_vespertino_civil;
    }

    public List<ProfesorContrato> getAno0_cont_diurno_ejecu() {
        ano0_cont_diurno_ejecu = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_ejecu_diurno.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano0_cont_diurno_ejecu.add(prof_cont);
            }
        }
        return ano0_cont_diurno_ejecu;
    }

    public void setAno0_cont_diurno_ejecu(List<ProfesorContrato> ano0_cont_diurno_ejecu) {
        this.ano0_cont_diurno_ejecu = ano0_cont_diurno_ejecu;
    }

    public List<ProfesorContrato> getAno0_cont_vespertino_ejecu() {
        ano0_cont_vespertino_ejecu = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_ejecu_vespertino.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano0_cont_vespertino_ejecu.add(prof_cont);
            }
        }
        return ano0_cont_vespertino_ejecu;
    }

    public void setAno0_cont_vespertino_ejecu(List<ProfesorContrato> ano0_cont_vespertino_ejecu) {
        this.ano0_cont_vespertino_ejecu = ano0_cont_vespertino_ejecu;
    }

    public List<ProfesorContrato> getAno1_cont_diurno_civil() {
        ano1_cont_diurno_civil = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_civil_diurno.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano1_cont_diurno_civil.add(prof_cont);
            }
        }
        return ano1_cont_diurno_civil;
    }

    public void setAno1_cont_diurno_civil(List<ProfesorContrato> ano1_cont_diurno_civil) {
        this.ano1_cont_diurno_civil = ano1_cont_diurno_civil;
    }

    public List<ProfesorContrato> getAno1_cont_vespertino_civil() {
        ano1_cont_vespertino_civil = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_civil_vespertino.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano1_cont_vespertino_civil.add(prof_cont);
            }
        }
        return ano1_cont_vespertino_civil;
    }

    public void setAno1_cont_vespertino_civil(List<ProfesorContrato> ano1_cont_vespertino_civil) {
        this.ano1_cont_vespertino_civil = ano1_cont_vespertino_civil;
    }

    public List<ProfesorContrato> getAno1_cont_diurno_ejecu() {
        ano1_cont_diurno_ejecu = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_ejecu_diurno.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano1_cont_diurno_ejecu.add(prof_cont);
            }
        }
        return ano1_cont_diurno_ejecu;
    }

    public void setAno1_cont_diurno_ejecu(List<ProfesorContrato> ano1_cont_diurno_ejecu) {
        this.ano1_cont_diurno_ejecu = ano1_cont_diurno_ejecu;
    }

    public List<ProfesorContrato> getAno1_cont_vespertino_ejecu() {
        ano1_cont_vespertino_ejecu = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_ejecu_vespertino.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano1_cont_vespertino_ejecu.add(prof_cont);
            }
        }
        return ano1_cont_vespertino_ejecu;
    }

    public void setAno1_cont_vespertino_ejecu(List<ProfesorContrato> ano1_cont_vespertino_ejecu) {
        this.ano1_cont_vespertino_ejecu = ano1_cont_vespertino_ejecu;
    }

    public List<ProfesorContrato> getAno2_cont_diurno_civil() {
        ano2_cont_diurno_civil = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_civil_diurno.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano2_cont_diurno_civil.add(prof_cont);
            }
        }
        return ano2_cont_diurno_civil;
    }

    public void setAno2_cont_diurno_civil(List<ProfesorContrato> ano2_cont_diurno_civil) {
        this.ano2_cont_diurno_civil = ano2_cont_diurno_civil;
    }

    public List<ProfesorContrato> getAno2_cont_vespertino_civil() {
        ano2_cont_vespertino_civil = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_civil_vespertino.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano2_cont_vespertino_civil.add(prof_cont);
            }
        }
        return ano2_cont_vespertino_civil;
    }

    public void setAno2_cont_vespertino_civil(List<ProfesorContrato> ano2_cont_vespertino_civil) {
        this.ano2_cont_vespertino_civil = ano2_cont_vespertino_civil;
    }

    public List<ProfesorContrato> getAno2_cont_diurno_ejecu() {
        ano2_cont_diurno_ejecu = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_ejecu_diurno.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano2_cont_diurno_ejecu.add(prof_cont);
            }
        }
        return ano2_cont_diurno_ejecu;
    }

    public void setAno2_cont_diurno_ejecu(List<ProfesorContrato> ano2_cont_diurno_ejecu) {
        this.ano2_cont_diurno_ejecu = ano2_cont_diurno_ejecu;
    }

    public List<ProfesorContrato> getAno2_cont_vespertino_ejecu() {
        ano2_cont_vespertino_ejecu = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_ejecu_vespertino.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano2_cont_vespertino_ejecu.add(prof_cont);
            }
        }
        return ano2_cont_vespertino_ejecu;
    }

    public void setAno2_cont_vespertino_ejecu(List<ProfesorContrato> ano2_cont_vespertino_ejecu) {
        this.ano2_cont_vespertino_ejecu = ano2_cont_vespertino_ejecu;
    }

    public List<ProfesorContrato> getAno3_cont_diurno_civil() {
        ano3_cont_diurno_civil = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_civil_diurno.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano3_cont_diurno_civil.add(prof_cont);
            }
        }
        return ano3_cont_diurno_civil;
    }

    public void setAno3_cont_diurno_civil(List<ProfesorContrato> ano3_cont_diurno_civil) {
        this.ano3_cont_diurno_civil = ano3_cont_diurno_civil;
    }

    public List<ProfesorContrato> getAno3_cont_vespertino_civil() {
        ano3_cont_vespertino_civil = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_civil_vespertino.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano3_cont_vespertino_civil.add(prof_cont);
            }
        }
        return ano3_cont_vespertino_civil;
    }

    public void setAno3_cont_vespertino_civil(List<ProfesorContrato> ano3_cont_vespertino_civil) {
        this.ano3_cont_vespertino_civil = ano3_cont_vespertino_civil;
    }

    public List<ProfesorContrato> getAno3_cont_diurno_ejecu() {
        ano3_cont_diurno_ejecu = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_ejecu_diurno.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano3_cont_diurno_ejecu.add(prof_cont);
            }
        }
        return ano3_cont_diurno_ejecu;
    }

    public void setAno3_cont_diurno_ejecu(List<ProfesorContrato> ano3_cont_diurno_ejecu) {
        this.ano3_cont_diurno_ejecu = ano3_cont_diurno_ejecu;
    }

    public List<ProfesorContrato> getAno3_cont_vespertino_ejecu() {
        ano3_cont_vespertino_ejecu = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_ejecu_vespertino.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano3_cont_vespertino_ejecu.add(prof_cont);
            }
        }
        return ano3_cont_vespertino_ejecu;
    }

    public void setAno3_cont_vespertino_ejecu(List<ProfesorContrato> ano3_cont_vespertino_ejecu) {
        this.ano3_cont_vespertino_ejecu = ano3_cont_vespertino_ejecu;
    }

    public List<ProfesorContrato> getAno4_cont_diurno_civil() {
        ano4_cont_diurno_civil = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_civil_diurno.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano4_cont_diurno_civil.add(prof_cont);
            }
        }
        return ano4_cont_diurno_civil;
    }

    public void setAno4_cont_diurno_civil(List<ProfesorContrato> ano4_cont_diurno_civil) {
        this.ano4_cont_diurno_civil = ano4_cont_diurno_civil;
    }

    public List<ProfesorContrato> getAno4_cont_vespertino_civil() {
        ano4_cont_vespertino_civil = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_civil_vespertino.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano4_cont_vespertino_civil.add(prof_cont);
            }
        }
        return ano4_cont_vespertino_civil;
    }

    public void setAno4_cont_vespertino_civil(List<ProfesorContrato> ano4_cont_vespertino_civil) {
        this.ano4_cont_vespertino_civil = ano4_cont_vespertino_civil;
    }

    public List<ProfesorContrato> getAno4_cont_diurno_ejecu() {
        ano4_cont_diurno_ejecu = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_ejecu_diurno.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano4_cont_diurno_ejecu.add(prof_cont);
            }
        }
        return ano4_cont_diurno_ejecu;
    }

    public void setAno4_cont_diurno_ejecu(List<ProfesorContrato> ano4_cont_diurno_ejecu) {
        this.ano4_cont_diurno_ejecu = ano4_cont_diurno_ejecu;
    }

    public List<ProfesorContrato> getAno4_cont_vespertino_ejecu() {
        ano4_cont_vespertino_ejecu = new ArrayList<>();
        List<Contrato> contratos= contratoFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Contrato contrato : contratos) {
            profesor_contrato=profeContFacade.findByContrato(contrato, year);
            List<ProfesorContrato> lista_contrato_actual= new ArrayList<>();
            for(ProfesorContrato profe_contrato: profesor_contrato){
                if(lista_profesor_ejecu_vespertino.contains(profe_contrato.getRut_profesor())){
                    lista_contrato_actual.add(profe_contrato);
                }
            }
            Collections.sort(lista_contrato_actual, new Comparator<ProfesorContrato>(){
            @Override
            public int compare(ProfesorContrato o1, ProfesorContrato o2){
               return o1.getRut_profesor().getApellido_pat().compareTo(o2.getRut_profesor().getApellido_pat());
            }
            });
            for(ProfesorContrato prof_cont: lista_contrato_actual){
                ano4_cont_vespertino_ejecu.add(prof_cont);
            }
        }
        return ano4_cont_vespertino_ejecu;
    }

    public void setAno4_cont_vespertino_ejecu(List<ProfesorContrato> ano4_cont_vespertino_ejecu) {
        this.ano4_cont_vespertino_ejecu = ano4_cont_vespertino_ejecu;
    }

    public List<CantJerarquiaGrado> getCant_ano0_jerar_grado_diurno_civil() {
        cant_ano0_jerar_grado_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_civil_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_civil_diurno.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_civil_diurno.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano0_jerar_grado_diurno_civil.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano0_jerar_grado_diurno_civil.add(nuevototales);
        return cant_ano0_jerar_grado_diurno_civil;
    }

    public void setCant_ano0_jerar_grado_diurno_civil(List<CantJerarquiaGrado> cant_ano0_jerar_grado_diurno_civil) {
        this.cant_ano0_jerar_grado_diurno_civil = cant_ano0_jerar_grado_diurno_civil;
    }

    public List<CantJerarquiaGrado> getCant_ano0_jerar_grado_diurno_ejecu() {
        cant_ano0_jerar_grado_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_ejecu_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_ejecu_diurno.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_ejecu_diurno.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano0_jerar_grado_diurno_ejecu.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano0_jerar_grado_diurno_ejecu.add(nuevototales);
        return cant_ano0_jerar_grado_diurno_ejecu;
    }

    public void setCant_ano0_jerar_grado_diurno_ejecu(List<CantJerarquiaGrado> cant_ano0_jerar_grado_diurno_ejecu) {
        this.cant_ano0_jerar_grado_diurno_ejecu = cant_ano0_jerar_grado_diurno_ejecu;
    }

    public List<CantJerarquiaGrado> getCant_ano0_jerar_grado_vespertino_civil() {
        cant_ano0_jerar_grado_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_civil_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_civil_vespertino.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_civil_vespertino.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano0_jerar_grado_vespertino_civil.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano0_jerar_grado_vespertino_civil.add(nuevototales);
        return cant_ano0_jerar_grado_vespertino_civil;
    }

    public void setCant_ano0_jerar_grado_vespertino_civil(List<CantJerarquiaGrado> cant_ano0_jerar_grado_vespertino_civil) {
        this.cant_ano0_jerar_grado_vespertino_civil = cant_ano0_jerar_grado_vespertino_civil;
    }

    public List<CantJerarquiaGrado> getCant_ano0_jerar_grado_vespertino_ejecu() {
        cant_ano0_jerar_grado_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_ejecu_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_ejecu_vespertino.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_ejecu_vespertino.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano0_jerar_grado_vespertino_ejecu.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano0_jerar_grado_vespertino_ejecu.add(nuevototales);
        return cant_ano0_jerar_grado_vespertino_ejecu;
    }

    public void setCant_ano0_jerar_grado_vespertino_ejecu(List<CantJerarquiaGrado> cant_ano0_jerar_grado_vespertino_ejecu) {
        this.cant_ano0_jerar_grado_vespertino_ejecu = cant_ano0_jerar_grado_vespertino_ejecu;
    }

    public List<CantJerarquiaGrado> getCant_ano1_jerar_grado_diurno_civil() {
        cant_ano1_jerar_grado_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_civil_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_civil_diurno.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_civil_diurno.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano1_jerar_grado_diurno_civil.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano1_jerar_grado_diurno_civil.add(nuevototales);
        return cant_ano1_jerar_grado_diurno_civil;
    }

    public void setCant_ano1_jerar_grado_diurno_civil(List<CantJerarquiaGrado> cant_ano1_jerar_grado_diurno_civil) {
        this.cant_ano1_jerar_grado_diurno_civil = cant_ano1_jerar_grado_diurno_civil;
    }

    public List<CantJerarquiaGrado> getCant_ano1_jerar_grado_diurno_ejecu() {
        cant_ano1_jerar_grado_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_ejecu_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_ejecu_diurno.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_ejecu_diurno.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano1_jerar_grado_diurno_ejecu.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano1_jerar_grado_diurno_ejecu.add(nuevototales);
        return cant_ano1_jerar_grado_diurno_ejecu;
    }

    public void setCant_ano1_jerar_grado_diurno_ejecu(List<CantJerarquiaGrado> cant_ano1_jerar_grado_diurno_ejecu) {
        this.cant_ano1_jerar_grado_diurno_ejecu = cant_ano1_jerar_grado_diurno_ejecu;
    }

    public List<CantJerarquiaGrado> getCant_ano1_jerar_grado_vespertino_civil() {
        cant_ano1_jerar_grado_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_civil_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_civil_vespertino.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_civil_vespertino.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano1_jerar_grado_vespertino_civil.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano1_jerar_grado_vespertino_civil.add(nuevototales);
        return cant_ano1_jerar_grado_vespertino_civil;
    }

    public void setCant_ano1_jerar_grado_vespertino_civil(List<CantJerarquiaGrado> cant_ano1_jerar_grado_vespertino_civil) {
        this.cant_ano1_jerar_grado_vespertino_civil = cant_ano1_jerar_grado_vespertino_civil;
    }

    public List<CantJerarquiaGrado> getCant_ano1_jerar_grado_vespertino_ejecu() {
        cant_ano1_jerar_grado_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_ejecu_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_ejecu_vespertino.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_ejecu_vespertino.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano1_jerar_grado_vespertino_ejecu.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano1_jerar_grado_vespertino_ejecu.add(nuevototales);
        return cant_ano1_jerar_grado_vespertino_ejecu;
    }

    public void setCant_ano1_jerar_grado_vespertino_ejecu(List<CantJerarquiaGrado> cant_ano1_jerar_grado_vespertino_ejecu) {
        this.cant_ano1_jerar_grado_vespertino_ejecu = cant_ano1_jerar_grado_vespertino_ejecu;
    }

    public List<CantJerarquiaGrado> getCant_ano2_jerar_grado_diurno_civil() {
        cant_ano2_jerar_grado_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_civil_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_civil_diurno.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_civil_diurno.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano2_jerar_grado_diurno_civil.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano2_jerar_grado_diurno_civil.add(nuevototales);
        return cant_ano2_jerar_grado_diurno_civil;
    }

    public void setCant_ano2_jerar_grado_diurno_civil(List<CantJerarquiaGrado> cant_ano2_jerar_grado_diurno_civil) {
        this.cant_ano2_jerar_grado_diurno_civil = cant_ano2_jerar_grado_diurno_civil;
    }

    public List<CantJerarquiaGrado> getCant_ano2_jerar_grado_diurno_ejecu() {
        cant_ano2_jerar_grado_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_ejecu_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_ejecu_diurno.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_ejecu_diurno.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano2_jerar_grado_diurno_ejecu.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano2_jerar_grado_diurno_ejecu.add(nuevototales);
        return cant_ano2_jerar_grado_diurno_ejecu;
    }

    public void setCant_ano2_jerar_grado_diurno_ejecu(List<CantJerarquiaGrado> cant_ano2_jerar_grado_diurno_ejecu) {
        this.cant_ano2_jerar_grado_diurno_ejecu = cant_ano2_jerar_grado_diurno_ejecu;
    }

    public List<CantJerarquiaGrado> getCant_ano2_jerar_grado_vespertino_civil() {
        cant_ano2_jerar_grado_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_civil_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_civil_vespertino.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_civil_vespertino.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano2_jerar_grado_vespertino_civil.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano2_jerar_grado_vespertino_civil.add(nuevototales);
        return cant_ano2_jerar_grado_vespertino_civil;
    }

    public void setCant_ano2_jerar_grado_vespertino_civil(List<CantJerarquiaGrado> cant_ano2_jerar_grado_vespertino_civil) {
        this.cant_ano2_jerar_grado_vespertino_civil = cant_ano2_jerar_grado_vespertino_civil;
    }

    public List<CantJerarquiaGrado> getCant_ano2_jerar_grado_vespertino_ejecu() {
        cant_ano2_jerar_grado_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_ejecu_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_ejecu_vespertino.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_ejecu_vespertino.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano2_jerar_grado_vespertino_ejecu.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano2_jerar_grado_vespertino_ejecu.add(nuevototales);
        return cant_ano2_jerar_grado_vespertino_ejecu;
    }

    public void setCant_ano2_jerar_grado_vespertino_ejecu(List<CantJerarquiaGrado> cant_ano2_jerar_grado_vespertino_ejecu) {
        this.cant_ano2_jerar_grado_vespertino_ejecu = cant_ano2_jerar_grado_vespertino_ejecu;
    }

    public List<CantJerarquiaGrado> getCant_ano3_jerar_grado_diurno_civil() {
        cant_ano3_jerar_grado_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_civil_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_civil_diurno.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_civil_diurno.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano3_jerar_grado_diurno_civil.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano3_jerar_grado_diurno_civil.add(nuevototales);
        return cant_ano3_jerar_grado_diurno_civil;
    }

    public void setCant_ano3_jerar_grado_diurno_civil(List<CantJerarquiaGrado> cant_ano3_jerar_grado_diurno_civil) {
        this.cant_ano3_jerar_grado_diurno_civil = cant_ano3_jerar_grado_diurno_civil;
    }

    public List<CantJerarquiaGrado> getCant_ano3_jerar_grado_diurno_ejecu() {
        cant_ano3_jerar_grado_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_ejecu_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_ejecu_diurno.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_ejecu_diurno.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano3_jerar_grado_diurno_ejecu.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano3_jerar_grado_diurno_ejecu.add(nuevototales);
        return cant_ano3_jerar_grado_diurno_ejecu;
    }

    public void setCant_ano3_jerar_grado_diurno_ejecu(List<CantJerarquiaGrado> cant_ano3_jerar_grado_diurno_ejecu) {
        this.cant_ano3_jerar_grado_diurno_ejecu = cant_ano3_jerar_grado_diurno_ejecu;
    }

    public List<CantJerarquiaGrado> getCant_ano3_jerar_grado_vespertino_civil() {
        cant_ano3_jerar_grado_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_civil_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_civil_vespertino.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_civil_vespertino.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano3_jerar_grado_vespertino_civil.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano3_jerar_grado_vespertino_civil.add(nuevototales);
        return cant_ano3_jerar_grado_vespertino_civil;
    }

    public void setCant_ano3_jerar_grado_vespertino_civil(List<CantJerarquiaGrado> cant_ano3_jerar_grado_vespertino_civil) {
        this.cant_ano3_jerar_grado_vespertino_civil = cant_ano3_jerar_grado_vespertino_civil;
    }

    public List<CantJerarquiaGrado> getCant_ano3_jerar_grado_vespertino_ejecu() {
        cant_ano3_jerar_grado_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_ejecu_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_ejecu_vespertino.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_ejecu_vespertino.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano3_jerar_grado_vespertino_ejecu.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano3_jerar_grado_vespertino_ejecu.add(nuevototales);
        return cant_ano3_jerar_grado_vespertino_ejecu;
    }

    public void setCant_ano3_jerar_grado_vespertino_ejecu(List<CantJerarquiaGrado> cant_ano3_jerar_grado_vespertino_ejecu) {
        this.cant_ano3_jerar_grado_vespertino_ejecu = cant_ano3_jerar_grado_vespertino_ejecu;
    }

    public List<CantJerarquiaGrado> getCant_ano4_jerar_grado_diurno_civil() {
        cant_ano4_jerar_grado_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_civil_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_civil_diurno.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_civil_diurno.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano4_jerar_grado_diurno_civil.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano4_jerar_grado_diurno_civil.add(nuevototales);
        return cant_ano4_jerar_grado_diurno_civil;
    }

    public void setCant_ano4_jerar_grado_diurno_civil(List<CantJerarquiaGrado> cant_ano4_jerar_grado_diurno_civil) {
        this.cant_ano4_jerar_grado_diurno_civil = cant_ano4_jerar_grado_diurno_civil;
    }

    public List<CantJerarquiaGrado> getCant_ano4_jerar_grado_diurno_ejecu() {
        cant_ano4_jerar_grado_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_ejecu_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_ejecu_diurno.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_ejecu_diurno.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano4_jerar_grado_diurno_ejecu.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano4_jerar_grado_diurno_ejecu.add(nuevototales);
        return cant_ano4_jerar_grado_diurno_ejecu;
    }

    public void setCant_ano4_jerar_grado_diurno_ejecu(List<CantJerarquiaGrado> cant_ano4_jerar_grado_diurno_ejecu) {
        this.cant_ano4_jerar_grado_diurno_ejecu = cant_ano4_jerar_grado_diurno_ejecu;
    }

    public List<CantJerarquiaGrado> getCant_ano4_jerar_grado_vespertino_civil() {
        cant_ano4_jerar_grado_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_civil_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_civil_vespertino.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_civil_vespertino.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano4_jerar_grado_vespertino_civil.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano4_jerar_grado_vespertino_civil.add(nuevototales);
        return cant_ano4_jerar_grado_vespertino_civil;
    }

    public void setCant_ano4_jerar_grado_vespertino_civil(List<CantJerarquiaGrado> cant_ano4_jerar_grado_vespertino_civil) {
        this.cant_ano4_jerar_grado_vespertino_civil = cant_ano4_jerar_grado_vespertino_civil;
    }

    public List<CantJerarquiaGrado> getCant_ano4_jerar_grado_vespertino_ejecu() {
        cant_ano4_jerar_grado_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Profesor> profesores_eliminar = new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<ProfesorJerarquia>> profesores_jerarquias = new ArrayList<List<ProfesorJerarquia>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        List<Jerarquia> jerarquias= jeraFacade.findAll();
        for(Profesor profesor: lista_profesor_ejecu_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesor_jerarquia=profeJerarFacade.findByProfesor(profesor, year);
            if(profesor_grado.size()>0 && profesor_jerarquia.size()>0){
                profesores_grados.add(profesor_grado);
                profesores_jerarquias.add(profesor_jerarquia);
            }
            else{
                profesores_eliminar.add(profesor);
            }
        }
        lista_profesor_ejecu_vespertino.removeAll(profesores_eliminar);
        int totaljerar=jerarquias.size();
        int[] totales = new int[totaljerar];
        for (int i=0;i<totaljerar;i++){
            totales[i]=0;
        }
        int indice;
        for (GradoAcademico item : items) {
            int[] cont = new int[totaljerar];
            indice=0;
            for(Jerarquia jerarquia : jerarquias){
                for(int i=0;i<lista_profesor_ejecu_vespertino.size();i++){
                    if(profesores_grados.get(i).get(0).getId_grado_academico().getId_grado_academico()==item.getId_grado_academico() && profesores_jerarquias.get(i).get(0).getId_jerarquia().getId_jerarquia()==jerarquia.getId_jerarquia()){
                        cont[indice]+=1;
                    }
                }
                indice+=1;
            }
            CantJerarquiaGrado nuevo = new CantJerarquiaGrado();
            nuevo.setCantjerar(cont);
            nuevo.setGrado(item.getNombre_grado_academico());
            cant_ano4_jerar_grado_vespertino_ejecu.add(nuevo);
            for(int j=0;j<totaljerar;j++){
                totales[j]+=cont[j];
            }
        }
        CantJerarquiaGrado nuevototales = new CantJerarquiaGrado();
        nuevototales.setCantjerar(totales);
        nuevototales.setGrado("Total");
        cant_ano4_jerar_grado_vespertino_ejecu.add(nuevototales);
        return cant_ano4_jerar_grado_vespertino_ejecu;
    }

    public void setCant_ano4_jerar_grado_vespertino_ejecu(List<CantJerarquiaGrado> cant_ano4_jerar_grado_vespertino_ejecu) {
        this.cant_ano4_jerar_grado_vespertino_ejecu = cant_ano4_jerar_grado_vespertino_ejecu;
    }

    public List<GradoCant> getGrado_cant_ano_0_diurno_civil() {
        grado_cant_ano_0_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_civil_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_0_diurno_civil.add(nuevo);
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
        grado_cant_ano_0_diurno_civil.add(nuevototales);
        return grado_cant_ano_0_diurno_civil;
    }

    public void setGrado_cant_ano_0_diurno_civil(List<GradoCant> grado_cant_ano_0_diurno_civil) {
        this.grado_cant_ano_0_diurno_civil = grado_cant_ano_0_diurno_civil;
    }
        
    public List<GradoCant> getGrado_cant_ano_1_diurno_civil() {
        grado_cant_ano_1_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_civil_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_1_diurno_civil.add(nuevo);
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
        grado_cant_ano_1_diurno_civil.add(nuevototales);
        return grado_cant_ano_1_diurno_civil;
     }

    public List<GradoCant> getGrado_cant_ano_2_diurno_civil() {
        grado_cant_ano_2_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_civil_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_2_diurno_civil.add(nuevo);
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
        grado_cant_ano_2_diurno_civil.add(nuevototales);
        return grado_cant_ano_2_diurno_civil;
    }

    public void setGrado_cant_ano_2_diurno_civil(List<GradoCant> grado_cant_ano_2_diurno_civil) {
        this.grado_cant_ano_2_diurno_civil = grado_cant_ano_2_diurno_civil;
    }

    public List<GradoCant> getGrado_cant_ano_3_diurno_civil() {
        grado_cant_ano_3_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_civil_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_3_diurno_civil.add(nuevo);
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
        grado_cant_ano_3_diurno_civil.add(nuevototales);
        return grado_cant_ano_3_diurno_civil;
    }

    public void setGrado_cant_ano_3_diurno_civil(List<GradoCant> grado_cant_ano_3_diurno_civil) {
        this.grado_cant_ano_3_diurno_civil = grado_cant_ano_3_diurno_civil;
    }

    public List<GradoCant> getGrado_cant_ano_4_diurno_civil() {
        grado_cant_ano_4_diurno_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_civil_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_4_diurno_civil.add(nuevo);
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
        grado_cant_ano_4_diurno_civil.add(nuevototales);
        return grado_cant_ano_4_diurno_civil;
    }

    public void setGrado_cant_ano_4_diurno_civil(List<GradoCant> grado_cant_ano_4_diurno_civil) {
        this.grado_cant_ano_4_diurno_civil = grado_cant_ano_4_diurno_civil;
    }

    public List<GradoCant> getGrado_cant_ano_0_vespertino_civil() {
        grado_cant_ano_0_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_civil_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_0_vespertino_civil.add(nuevo);
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
        grado_cant_ano_0_vespertino_civil.add(nuevototales);
        return grado_cant_ano_0_vespertino_civil;
    }

    public void setGrado_cant_ano_0_vespertino_civil(List<GradoCant> grado_cant_ano_0_vespertino_civil) {
        this.grado_cant_ano_0_vespertino_civil = grado_cant_ano_0_vespertino_civil;
    }
    
    public List<GradoCant> getGrado_cant_ano_1_vespertino_civil() {
        grado_cant_ano_1_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_civil_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_1_vespertino_civil.add(nuevo);
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
        grado_cant_ano_1_vespertino_civil.add(nuevototales);
        return grado_cant_ano_1_vespertino_civil;
    }

    public void setGrado_cant_ano_1_vespertino_civil(List<GradoCant> grado_cant_ano_1_vespertino_civil) {
        this.grado_cant_ano_1_vespertino_civil = grado_cant_ano_1_vespertino_civil;
    }

    public List<GradoCant> getGrado_cant_ano_2_vespertino_civil() {
        grado_cant_ano_2_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_civil_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_2_vespertino_civil.add(nuevo);
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
        grado_cant_ano_2_vespertino_civil.add(nuevototales);
        return grado_cant_ano_2_vespertino_civil;
    }

    public void setGrado_cant_ano_2_vespertino_civil(List<GradoCant> grado_cant_ano_2_vespertino_civil) {
        this.grado_cant_ano_2_vespertino_civil = grado_cant_ano_2_vespertino_civil;
    }

    public List<GradoCant> getGrado_cant_ano_3_vespertino_civil() {
        grado_cant_ano_3_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_civil_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_3_vespertino_civil.add(nuevo);
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
        grado_cant_ano_3_vespertino_civil.add(nuevototales);
        return grado_cant_ano_3_vespertino_civil;
    }

    public void setGrado_cant_ano_3_vespertino_civil(List<GradoCant> grado_cant_ano_3_vespertino_civil) {
        this.grado_cant_ano_3_vespertino_civil = grado_cant_ano_3_vespertino_civil;
    }

    public List<GradoCant> getGrado_cant_ano_4_vespertino_civil() {
        grado_cant_ano_4_vespertino_civil = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_civil_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_4_vespertino_civil.add(nuevo);
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
        grado_cant_ano_4_vespertino_civil.add(nuevototales);
        return grado_cant_ano_4_vespertino_civil;
    }

    public void setGrado_cant_ano_4_vespertino_civil(List<GradoCant> grado_cant_ano_4_vespertino_civil) {
        this.grado_cant_ano_4_vespertino_civil = grado_cant_ano_4_vespertino_civil;
    }

    public List<GradoCant> getGrado_cant_ano_0_diurno_ejecu() {
        grado_cant_ano_0_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_ejecu_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_0_diurno_ejecu.add(nuevo);
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
        grado_cant_ano_0_diurno_ejecu.add(nuevototales);
        return grado_cant_ano_0_diurno_ejecu;
    }

    public void setGrado_cant_ano_0_diurno_ejecu(List<GradoCant> grado_cant_ano_0_diurno_ejecu) {
        this.grado_cant_ano_0_diurno_ejecu = grado_cant_ano_0_diurno_ejecu;
    }

    public List<GradoCant> getGrado_cant_ano_1_diurno_ejecu() {
        grado_cant_ano_1_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_ejecu_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_1_diurno_ejecu.add(nuevo);
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
        grado_cant_ano_1_diurno_ejecu.add(nuevototales);
        return grado_cant_ano_1_diurno_ejecu;
    }

    public void setGrado_cant_ano_1_diurno_ejecu(List<GradoCant> grado_cant_ano_1_diurno_ejecu) {
        this.grado_cant_ano_1_diurno_ejecu = grado_cant_ano_1_diurno_ejecu;
    }

    public List<GradoCant> getGrado_cant_ano_2_diurno_ejecu() {
        grado_cant_ano_2_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_ejecu_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_2_diurno_ejecu.add(nuevo);
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
        grado_cant_ano_2_diurno_ejecu.add(nuevototales);
        return grado_cant_ano_2_diurno_ejecu;
    }

    public void setGrado_cant_ano_2_diurno_ejecu(List<GradoCant> grado_cant_ano_2_diurno_ejecu) {
        this.grado_cant_ano_2_diurno_ejecu = grado_cant_ano_2_diurno_ejecu;
    }

    public List<GradoCant> getGrado_cant_ano_3_diurno_ejecu() {
        grado_cant_ano_3_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_ejecu_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_3_diurno_ejecu.add(nuevo);
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
        grado_cant_ano_3_diurno_ejecu.add(nuevototales);
        return grado_cant_ano_3_diurno_ejecu;
    }

    public void setGrado_cant_ano_3_diurno_ejecu(List<GradoCant> grado_cant_ano_3_diurno_ejecu) {
        this.grado_cant_ano_3_diurno_ejecu = grado_cant_ano_3_diurno_ejecu;
    }

    public List<GradoCant> getGrado_cant_ano_4_diurno_ejecu() {
        grado_cant_ano_4_diurno_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_ejecu_diurno){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_4_diurno_ejecu.add(nuevo);
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
        grado_cant_ano_4_diurno_ejecu.add(nuevototales);
        return grado_cant_ano_4_diurno_ejecu;
    }

    public void setGrado_cant_ano_4_diurno_ejecu(List<GradoCant> grado_cant_ano_4_diurno_ejecu) {
        this.grado_cant_ano_4_diurno_ejecu = grado_cant_ano_4_diurno_ejecu;
    }

    public List<GradoCant> getGrado_cant_ano_0_vespertino_ejecu() {
        grado_cant_ano_0_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_ejecu_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_0_vespertino_ejecu.add(nuevo);
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
        grado_cant_ano_0_vespertino_ejecu.add(nuevototales);
        return grado_cant_ano_0_vespertino_ejecu;
    }

    public void setGrado_cant_ano_0_vespertino_ejecu(List<GradoCant> grado_cant_ano_0_vespertino_ejecu) {
        this.grado_cant_ano_0_vespertino_ejecu = grado_cant_ano_0_vespertino_ejecu;
    }

    public List<GradoCant> getGrado_cant_ano_1_vespertino_ejecu() {
        grado_cant_ano_1_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_ejecu_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_1_vespertino_ejecu.add(nuevo);
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
        grado_cant_ano_1_vespertino_ejecu.add(nuevototales);
        return grado_cant_ano_1_vespertino_ejecu;
    }

    public void setGrado_cant_ano_1_vespertino_ejecu(List<GradoCant> grado_cant_ano_1_vespertino_ejecu) {
        this.grado_cant_ano_1_vespertino_ejecu = grado_cant_ano_1_vespertino_ejecu;
    }

    public List<GradoCant> getGrado_cant_ano_2_vespertino_ejecu() {
        grado_cant_ano_2_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_ejecu_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_2_vespertino_ejecu.add(nuevo);
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
        grado_cant_ano_2_vespertino_ejecu.add(nuevototales);
        return grado_cant_ano_2_vespertino_ejecu;
    }

    public void setGrado_cant_ano_2_vespertino_ejecu(List<GradoCant> grado_cant_ano_2_vespertino_ejecu) {
        this.grado_cant_ano_2_vespertino_ejecu = grado_cant_ano_2_vespertino_ejecu;
    }

    public List<GradoCant> getGrado_cant_ano_3_vespertino_ejecu() {
        grado_cant_ano_3_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_ejecu_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_3_vespertino_ejecu.add(nuevo);
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
        grado_cant_ano_3_vespertino_ejecu.add(nuevototales);
        return grado_cant_ano_3_vespertino_ejecu;
    }

    public void setGrado_cant_ano_3_vespertino_ejecu(List<GradoCant> grado_cant_ano_3_vespertino_ejecu) {
        this.grado_cant_ano_3_vespertino_ejecu = grado_cant_ano_3_vespertino_ejecu;
    }

    public List<GradoCant> getGrado_cant_ano_4_vespertino_ejecu() {
        grado_cant_ano_4_vespertino_ejecu = new ArrayList<>();
        items=ejbFacade.findAll();
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        List<Profesor> lista_profesor_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        List<List<ProfesorGrado>> profesores_grados = new ArrayList<List<ProfesorGrado>>();
        List<List<Profesor>> listas_profesores = new ArrayList<List<Profesor>>();
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignatura(asignatura, year);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if(lista_profesor_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false){
                    lista_profesor_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for(Profesor profesor: lista_profesor_ejecu_vespertino){
            profesor_grado=profeGradoFacade.findByProfesor(profesor, year);
            profesores_grados.add(profesor_grado);
        }
        for (GradoAcademico item : items) {
            List<Profesor> lista_grado_actual= new ArrayList<>();
            for(List<ProfesorGrado> profe_grado: profesores_grados){
                for(ProfesorGrado profe_gra: profe_grado){
                    if(item.getId_grado_academico()==profe_gra.getId_grado_academico().getId_grado_academico()){
                        lista_grado_actual.add(profe_gra.getRut_profesor());
                    }
                }
            }
            listas_profesores.add(lista_grado_actual);
        }
        float horas;
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
            horas=0;
            for(int i=0;i<listas_profesores.get(indice).size();i++){
                profesor_contrato=profeContFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                for(ProfesorContrato profe_cont: profesor_contrato){
                    contrato_profesor=profe_cont.getId_contrato();
                }
                if(contrato_profesor!=null){
                    if(contrato_profesor.isDependencia_asignaturas_contrato()==false){
                        cont[0]+=1;
                    }
                    else{
                        lista_asignaturas=profeAsigFacade.findByProfesor(listas_profesores.get(indice).get(i), year);
                        for(ProfesorAsignatura lista_asignatura: lista_asignaturas){
                            horas+=lista_asignatura.getId_asignatura().getCant_horas_presenciales();
                        }
                        if(horas<=10){
                            cont[1]+=1;
                        }
                        else if (horas>=11 && horas<=21){
                            cont[2]+=1;
                        }
                        else{
                            cont[3]+=1;
                        }
                    }
                }
            }
            GradoCant nuevo = new GradoCant();
            nuevo.setCant1(cont[0]);
            nuevo.setCant2(cont[1]);
            nuevo.setCant3(cont[2]);
            nuevo.setCant4(cont[3]);
            nuevo.setGrado(item.getNombre_grado_academico());
            grado_cant_ano_4_vespertino_ejecu.add(nuevo);
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
        grado_cant_ano_4_vespertino_ejecu.add(nuevototales);
        return grado_cant_ano_4_vespertino_ejecu;
    }

    public void setGrado_cant_ano_4_vespertino_ejecu(List<GradoCant> grado_cant_ano_4_vespertino_ejecu) {
        this.grado_cant_ano_4_vespertino_ejecu = grado_cant_ano_4_vespertino_ejecu;
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
