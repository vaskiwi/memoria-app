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
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico glicenciado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        Jerarquia jtitular = (Jerarquia) jeraFacade.findByNombre("TITULAR");
        Jerarquia jasociado = (Jerarquia) jeraFacade.findByNombre("ASOCIADO");
        Jerarquia jasistente = (Jerarquia) jeraFacade.findByNombre("ASISTENTE");
        Jerarquia jinstructor = (Jerarquia) jeraFacade.findByNombre("INSTRUCTOR");
        Jerarquia jayudante = (Jerarquia) jeraFacade.findByNombre("AYUDANTE");
        Jerarquia jadjcat1 = (Jerarquia) jeraFacade.findByNombre("ADJUNTO CATEGORÍA I");
        Jerarquia jadjcat2 = (Jerarquia) jeraFacade.findByNombre("ADJUNTO CATEGORÍA II");
        Jerarquia jinstructor1 = (Jerarquia) jeraFacade.findByNombre("INSTRUCTOR CATEGORÍA I");
        Jerarquia jinstructor2 = (Jerarquia) jeraFacade.findByNombre("INSTRUCTOR CATEGORÍA II");
        Jerarquia jayudantep = (Jerarquia) jeraFacade.findByNombre("AYUDANTE PROFESOR");
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> doctortitular;
        List<Profesor> doctorasociado;
        List<Profesor> doctorasistente;
        List<Profesor> doctorinstructor;
        List<Profesor> doctorayudante;
        List<Profesor> doctoradjcat1;
        List<Profesor> doctoradjcat2;
        List<Profesor> doctorinstructor1;
        List<Profesor> doctorinstructor2;
        List<Profesor> doctorayudantep;
        List<Profesor> magistertitular;
        List<Profesor> magisterasociado;
        List<Profesor> magisterasistente;
        List<Profesor> magisterinstructor;
        List<Profesor> magisterayudante;
        List<Profesor> magisteradjcat1;
        List<Profesor> magisteradjcat2;
        List<Profesor> magisterinstructor1;
        List<Profesor> magisterinstructor2;
        List<Profesor> magisterayudantep;
        List<Profesor> licenciadotitular;
        List<Profesor> licenciadoasociado;
        List<Profesor> licenciadoasistente;
        List<Profesor> licenciadoinstructor;
        List<Profesor> licenciadoayudante;
        List<Profesor> licenciadoadjcat1;
        List<Profesor> licenciadoadjcat2;
        List<Profesor> licenciadoinstructor1;
        List<Profesor> licenciadoinstructor2;
        List<Profesor> licenciadoayudantep;
        List<Profesor> tituladotitular;
        List<Profesor> tituladoasociado;
        List<Profesor> tituladoasistente;
        List<Profesor> tituladoinstructor;
        List<Profesor> tituladoayudante;
        List<Profesor> tituladoadjcat1;
        List<Profesor> tituladoadjcat2;
        List<Profesor> tituladoinstructor1;
        List<Profesor> tituladoinstructor2;
        List<Profesor> tituladoayudantep;
        
        doctortitular = profeFacade.findByJerarYGradoYDiurnoVespertino(jtitular, gdoctor,"DIURNO");
        doctorasociado = profeFacade.findByJerarYGradoYDiurnoVespertino(jasociado, gdoctor,"DIURNO");
        doctorasistente = profeFacade.findByJerarYGradoYDiurnoVespertino(jasistente, gdoctor,"DIURNO");
        doctorinstructor = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor, gdoctor,"DIURNO");
        doctorayudante = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudante, gdoctor,"DIURNO");
        doctoradjcat1 = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat1, gdoctor,"DIURNO");
        doctoradjcat2 = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat2, gdoctor,"DIURNO");
        doctorinstructor1 = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor1, gdoctor,"DIURNO");
        doctorinstructor2 = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor2, gdoctor,"DIURNO");
        doctorayudantep = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudantep, gdoctor,"DIURNO");
        magistertitular = profeFacade.findByJerarYGradoYDiurnoVespertino(jtitular, gmagister,"DIURNO");
        magisterasociado  = profeFacade.findByJerarYGradoYDiurnoVespertino(jasociado, gmagister,"DIURNO");
        magisterasistente  = profeFacade.findByJerarYGradoYDiurnoVespertino(jasistente, gmagister,"DIURNO");
        magisterinstructor = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor, gmagister,"DIURNO");
        magisterayudante  = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudante, gmagister,"DIURNO");
        magisteradjcat1  = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat1, gmagister,"DIURNO");
        magisteradjcat2  = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat2, gmagister,"DIURNO");
        magisterinstructor1  = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor1, gmagister,"DIURNO");
        magisterinstructor2  = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor2, gmagister,"DIURNO");
        magisterayudantep  = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudantep, gmagister,"DIURNO");
        licenciadotitular  = profeFacade.findByJerarYGradoYDiurnoVespertino(jtitular, glicenciado,"DIURNO");
        licenciadoasociado  = profeFacade.findByJerarYGradoYDiurnoVespertino(jasociado, glicenciado,"DIURNO");
        licenciadoasistente  = profeFacade.findByJerarYGradoYDiurnoVespertino(jasistente, glicenciado,"DIURNO");
        licenciadoinstructor  = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor, glicenciado,"DIURNO");
        licenciadoayudante  = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudante, glicenciado,"DIURNO") ;
        licenciadoadjcat1 = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat1, glicenciado,"DIURNO");
        licenciadoadjcat2   = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat2, glicenciado,"DIURNO");
        licenciadoinstructor1 = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor1, glicenciado,"DIURNO");
        licenciadoinstructor2   = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor2, glicenciado,"DIURNO");
        licenciadoayudantep = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudantep, glicenciado,"DIURNO");
        tituladotitular = profeFacade.findByJerarYGradoYDiurnoVespertino(jtitular, gtitulado,"DIURNO");
        tituladoasociado = profeFacade.findByJerarYGradoYDiurnoVespertino(jasociado, gtitulado,"DIURNO");
        tituladoasistente  = profeFacade.findByJerarYGradoYDiurnoVespertino(jasistente, gtitulado,"DIURNO");
        tituladoinstructor  = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor, gtitulado,"DIURNO");
        tituladoayudante  = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudante, gtitulado,"DIURNO");
        tituladoadjcat1  = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat1, gtitulado,"DIURNO");
        tituladoadjcat2  = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat2, gtitulado,"DIURNO");
        tituladoinstructor1 = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor1, gtitulado,"DIURNO");
        tituladoinstructor2  = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor2, gtitulado,"DIURNO");
        tituladoayudantep  = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudantep, gtitulado,"DIURNO");
        CantJerarquiaGrado nuevod = new CantJerarquiaGrado();
        CantJerarquiaGrado nuevom = new CantJerarquiaGrado();
        CantJerarquiaGrado nuevol = new CantJerarquiaGrado();
        CantJerarquiaGrado nuevot = new CantJerarquiaGrado();
        CantJerarquiaGrado nuevototal = new CantJerarquiaGrado();
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        int cont5 = 0;
        int cont6 = 0;
        int cont7 = 0;
        int cont8 = 0;
        int cont9 = 0;
        int cont10 = 0;
        for (int i = 0; i < doctortitular.size(); i++) {
            if((doctortitular.get(i).getAno_ingreso() <= year) && (doctortitular.get(i).isVigente() == true || doctortitular.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
        }
        for (int i = 0; i < doctorasociado.size(); i++) {
            if((doctorasociado.get(i).getAno_ingreso() <= year) && (doctorasociado.get(i).isVigente() == true || doctorasociado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
        }
        for (int i = 0; i < doctorasistente.size(); i++) {
            if((doctorasistente.get(i).getAno_ingreso() <= year) && (doctorasistente.get(i).isVigente() == true || doctorasistente.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
        }
        for (int i = 0; i < doctorinstructor.size(); i++) {
            if((doctorinstructor.get(i).getAno_ingreso() <= year) && (doctorinstructor.get(i).isVigente() == true || doctorinstructor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        for (int i = 0; i < doctorayudante.size(); i++) {
            if((doctorayudante.get(i).getAno_ingreso() <= year) && (doctorayudante.get(i).isVigente() == true || doctorayudante.get(i).getAnoRetiro() >= year)){
                cont5+=1;
            }
        }
        for (int i = 0; i < doctoradjcat1.size(); i++) {
            if((doctoradjcat1.get(i).getAno_ingreso() <= year) && (doctoradjcat1.get(i).isVigente() == true || doctoradjcat1.get(i).getAnoRetiro() >= year)){
                cont6+=1;
            }
        }
        for (int i = 0; i < doctoradjcat2.size(); i++) {
            if((doctoradjcat2.get(i).getAno_ingreso() <= year) && (doctoradjcat2.get(i).isVigente() == true || doctoradjcat2.get(i).getAnoRetiro() >= year)){
                cont7+=1;
            }
        }
        for (int i = 0; i < doctorinstructor1.size(); i++) {
            if((doctorinstructor1.get(i).getAno_ingreso() <= year) && (doctorinstructor1.get(i).isVigente() == true || doctorinstructor1.get(i).getAnoRetiro() >= year)){
                cont8+=1;
            }
        }
        for (int i = 0; i < doctorinstructor2.size(); i++) {
            if((doctorinstructor2.get(i).getAno_ingreso() <= year) && (doctorinstructor2.get(i).isVigente() == true || doctorinstructor2.get(i).getAnoRetiro() >= year)){
                cont9+=1;
            }
        }
        for (int i = 0; i < doctorayudantep.size(); i++) {
            if((doctorayudantep.get(i).getAno_ingreso() <= year) && (doctorayudantep.get(i).isVigente() == true || doctorayudantep.get(i).getAnoRetiro() >= year)){
                cont10+=1;
            }
        }
        nuevod.setJerar1(cont1);
        nuevod.setJerar2(cont2);
        nuevod.setJerar3(cont3);
        nuevod.setJerar4(cont4);
        nuevod.setJerar5(cont5);
        nuevod.setJerar6(cont6);
        nuevod.setJerar7(cont7);
        nuevod.setJerar8(cont8);
        nuevod.setJerar9(cont9);
        nuevod.setJerar10(cont10);
        nuevod.setGrado("Doctor");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0; cont5 = 0; cont6 = 0; cont7 = 0; cont8 = 0; cont9 = 0; cont10 = 0;
        for (int i = 0; i < magistertitular.size(); i++) {
            if((magistertitular.get(i).getAno_ingreso() <= year) && (magistertitular.get(i).isVigente() == true || magistertitular.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
        }
        for (int i = 0; i < doctorasociado.size(); i++) {
            if((magisterasociado.get(i).getAno_ingreso() <= year) && (magisterasociado.get(i).isVigente() == true || magisterasociado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
        }
        for (int i = 0; i < magisterasistente.size(); i++) {
            if((magisterasistente.get(i).getAno_ingreso() <= year) && (magisterasistente.get(i).isVigente() == true || magisterasistente.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
        }
        for (int i = 0; i < magisterinstructor.size(); i++) {
            if((magisterinstructor.get(i).getAno_ingreso() <= year) && (magisterinstructor.get(i).isVigente() == true || magisterinstructor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        for (int i = 0; i < magisterayudante.size(); i++) {
            if((magisterayudante.get(i).getAno_ingreso() <= year) && (magisterayudante.get(i).isVigente() == true || magisterayudante.get(i).getAnoRetiro() >= year)){
                cont5+=1;
            }
        }
        for (int i = 0; i < magisteradjcat1.size(); i++) {
            if((magisteradjcat1.get(i).getAno_ingreso() <= year) && (magisteradjcat1.get(i).isVigente() == true || magisteradjcat1.get(i).getAnoRetiro() >= year)){
                cont6+=1;
            }
        }
        for (int i = 0; i < doctoradjcat2.size(); i++) {
            if((magisteradjcat2.get(i).getAno_ingreso() <= year) && (magisteradjcat2.get(i).isVigente() == true || magisteradjcat2.get(i).getAnoRetiro() >= year)){
                cont7+=1;
            }
        }
        for (int i = 0; i < magisterinstructor1.size(); i++) {
            if((magisterinstructor1.get(i).getAno_ingreso() <= year) && (magisterinstructor1.get(i).isVigente() == true || magisterinstructor1.get(i).getAnoRetiro() >= year)){
                cont8+=1;
            }
        }
        for (int i = 0; i < magisterinstructor2.size(); i++) {
            if((magisterinstructor2.get(i).getAno_ingreso() <= year) && (magisterinstructor2.get(i).isVigente() == true || magisterinstructor2.get(i).getAnoRetiro() >= year)){
                cont9+=1;
            }
        }
        for (int i = 0; i < magisterayudantep.size(); i++) {
            if((magisterayudantep.get(i).getAno_ingreso() <= year) && (magisterayudantep.get(i).isVigente() == true || magisterayudantep.get(i).getAnoRetiro() >= year)){
                cont10+=1;
            }
        }
        nuevom.setJerar1(cont1);
        nuevom.setJerar2(cont2);
        nuevom.setJerar3(cont3);
        nuevom.setJerar4(cont4);
        nuevom.setJerar5(cont5);
        nuevom.setJerar6(cont6);
        nuevom.setJerar7(cont7);
        nuevom.setJerar8(cont8);
        nuevom.setJerar9(cont9);
        nuevom.setJerar10(cont10);
        nuevom.setGrado("Magister");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0; cont5 = 0; cont6 = 0; cont7 = 0; cont8 = 0; cont9 = 0; cont10 = 0;
        for (int i = 0; i < licenciadotitular.size(); i++) {
            if((licenciadotitular.get(i).getAno_ingreso() <= year) && (licenciadotitular.get(i).isVigente() == true || licenciadotitular.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
        }
        for (int i = 0; i < licenciadoasociado.size(); i++) {
            if((licenciadoasociado.get(i).getAno_ingreso() <= year) && (licenciadoasociado.get(i).isVigente() == true || licenciadoasociado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
        }
        for (int i = 0; i < licenciadoasistente.size(); i++) {
            if((licenciadoasistente.get(i).getAno_ingreso() <= year) && (licenciadoasistente.get(i).isVigente() == true || licenciadoasistente.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
        }
        for (int i = 0; i < licenciadoinstructor.size(); i++) {
            if((licenciadoinstructor.get(i).getAno_ingreso() <= year) && (licenciadoinstructor.get(i).isVigente() == true || licenciadoinstructor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        for (int i = 0; i < licenciadoayudante.size(); i++) {
            if((licenciadoayudante.get(i).getAno_ingreso() <= year) && (licenciadoayudante.get(i).isVigente() == true || licenciadoayudante.get(i).getAnoRetiro() >= year)){
                cont5+=1;
            }
        }
        for (int i = 0; i < licenciadoadjcat1.size(); i++) {
            if((licenciadoadjcat1.get(i).getAno_ingreso() <= year) && (licenciadoadjcat1.get(i).isVigente() == true || licenciadoadjcat1.get(i).getAnoRetiro() >= year)){
                cont6+=1;
            }
        }
        for (int i = 0; i < licenciadoadjcat2.size(); i++) {
            if((licenciadoadjcat2.get(i).getAno_ingreso() <= year) && (licenciadoadjcat2.get(i).isVigente() == true || licenciadoadjcat2.get(i).getAnoRetiro() >= year)){
                cont7+=1;
            }
        }
        for (int i = 0; i < licenciadoinstructor1.size(); i++) {
            if((licenciadoinstructor1.get(i).getAno_ingreso() <= year) && (licenciadoinstructor1.get(i).isVigente() == true || licenciadoinstructor1.get(i).getAnoRetiro() >= year)){
                cont8+=1;
            }
        }
        for (int i = 0; i < licenciadoinstructor2.size(); i++) {
            if((licenciadoinstructor2.get(i).getAno_ingreso() <= year) && (licenciadoinstructor2.get(i).isVigente() == true || licenciadoinstructor2.get(i).getAnoRetiro() >= year)){
                cont9+=1;
            }
        }
        for (int i = 0; i < licenciadoayudantep.size(); i++) {
            if((licenciadoayudantep.get(i).getAno_ingreso() <= year) && (licenciadoayudantep.get(i).isVigente() == true || licenciadoayudantep.get(i).getAnoRetiro() >= year)){
                cont10+=1;
            }
        }
        nuevol.setJerar1(cont1);
        nuevol.setJerar2(cont2);
        nuevol.setJerar3(cont3);
        nuevol.setJerar4(cont4);
        nuevol.setJerar5(cont5);
        nuevol.setJerar6(cont6);
        nuevol.setJerar7(cont7);
        nuevol.setJerar8(cont8);
        nuevol.setJerar9(cont9);
        nuevol.setJerar10(cont10);
        nuevol.setGrado("Licenciado");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0; cont5 = 0; cont6 = 0; cont7 = 0; cont8 = 0; cont9 = 0; cont10 = 0;
        for (int i = 0; i < tituladotitular.size(); i++) {
            if((licenciadotitular.get(i).getAno_ingreso() <= year) && (licenciadotitular.get(i).isVigente() == true || licenciadotitular.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
        }
        for (int i = 0; i < tituladoasociado.size(); i++) {
            if((tituladoasociado.get(i).getAno_ingreso() <= year) && (tituladoasociado.get(i).isVigente() == true || tituladoasociado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
        }
        for (int i = 0; i < tituladoasistente.size(); i++) {
            if((tituladoasistente.get(i).getAno_ingreso() <= year) && (tituladoasistente.get(i).isVigente() == true || tituladoasistente.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
        }
        for (int i = 0; i < tituladoinstructor.size(); i++) {
            if((tituladoinstructor.get(i).getAno_ingreso() <= year) && (tituladoinstructor.get(i).isVigente() == true || tituladoinstructor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        for (int i = 0; i < tituladoayudante.size(); i++) {
            if((tituladoayudante.get(i).getAno_ingreso() <= year) && (tituladoayudante.get(i).isVigente() == true || tituladoayudante.get(i).getAnoRetiro() >= year)){
                cont5+=1;
            }
        }
        for (int i = 0; i < tituladoadjcat1.size(); i++) {
            if((tituladoadjcat1.get(i).getAno_ingreso() <= year) && (tituladoadjcat1.get(i).isVigente() == true || tituladoadjcat1.get(i).getAnoRetiro() >= year)){
                cont6+=1;
            }
        }
        for (int i = 0; i < tituladoadjcat2.size(); i++) {
            if((tituladoadjcat2.get(i).getAno_ingreso() <= year) && (tituladoadjcat2.get(i).isVigente() == true || tituladoadjcat2.get(i).getAnoRetiro() >= year)){
                cont7+=1;
            }
        }
        for (int i = 0; i < tituladoinstructor1.size(); i++) {
            if((tituladoinstructor1.get(i).getAno_ingreso() <= year) && (tituladoinstructor1.get(i).isVigente() == true || tituladoinstructor1.get(i).getAnoRetiro() >= year)){
                cont8+=1;
            }
        }
        for (int i = 0; i < tituladoinstructor2.size(); i++) {
            if((tituladoinstructor2.get(i).getAno_ingreso() <= year) && (tituladoinstructor2.get(i).isVigente() == true || tituladoinstructor2.get(i).getAnoRetiro() >= year)){
                cont9+=1;
            }
        }
        for (int i = 0; i < tituladoayudantep.size(); i++) {
            if((tituladoayudantep.get(i).getAno_ingreso() <= year) && (tituladoayudantep.get(i).isVigente() == true || tituladoayudantep.get(i).getAnoRetiro() >= year)){
                cont10+=1;
            }
        }
        nuevot.setJerar1(cont1);
        nuevot.setJerar2(cont2);
        nuevot.setJerar3(cont3);
        nuevot.setJerar4(cont4);
        nuevot.setJerar5(cont5);
        nuevot.setJerar6(cont6);
        nuevot.setJerar7(cont7);
        nuevot.setJerar8(cont8);
        nuevot.setJerar9(cont9);
        nuevot.setJerar10(cont10);
        nuevod.setTotal(cont1+cont2+cont3+cont4+cont5+cont6+cont7+cont8+cont9+cont10);
        nuevot.setGrado("Titulado");
        nuevototal.setGrado("Total");
        nuevototal.setJerar1(nuevod.getJerar1()+nuevom.getJerar1()+nuevol.getJerar1()+nuevot.getJerar1());
        nuevototal.setJerar2(nuevod.getJerar2()+nuevom.getJerar2()+nuevol.getJerar2()+nuevot.getJerar2());
        nuevototal.setJerar3(nuevod.getJerar3()+nuevom.getJerar3()+nuevol.getJerar3()+nuevot.getJerar3());
        nuevototal.setJerar4(nuevod.getJerar4()+nuevom.getJerar4()+nuevol.getJerar4()+nuevot.getJerar4());
        nuevototal.setJerar5(nuevod.getJerar5()+nuevom.getJerar5()+nuevol.getJerar5()+nuevot.getJerar5());
        nuevototal.setJerar6(nuevod.getJerar6()+nuevom.getJerar6()+nuevol.getJerar6()+nuevot.getJerar6());
        nuevototal.setJerar7(nuevod.getJerar7()+nuevom.getJerar7()+nuevol.getJerar7()+nuevot.getJerar7());
        nuevototal.setJerar8(nuevod.getJerar8()+nuevom.getJerar8()+nuevol.getJerar8()+nuevot.getJerar8());
        nuevototal.setJerar9(nuevod.getJerar9()+nuevom.getJerar9()+nuevol.getJerar9()+nuevot.getJerar9());
        nuevototal.setJerar10(nuevod.getJerar10()+nuevom.getJerar10()+nuevol.getJerar10()+nuevot.getJerar10());
        nuevototal.setTotal(nuevod.getTotal()+nuevom.getTotal()+nuevol.getTotal()+nuevot.getTotal());
        cant_jerar_grado_diurno.add(nuevod);
        cant_jerar_grado_diurno.add(nuevom);
        cant_jerar_grado_diurno.add(nuevol);
        cant_jerar_grado_diurno.add(nuevot);
        cant_jerar_grado_diurno.add(nuevototal);
        return cant_jerar_grado_diurno;
    }

    public void setCant_jerar_grado_diurno(List<CantJerarquiaGrado> cant_jerar_grado_diurno) {
        this.cant_jerar_grado_diurno = cant_jerar_grado_diurno;
    }

    public List<CantJerarquiaGrado> getCant_jerar_grado_vespertino() {
        cant_jerar_grado_vespertino = new ArrayList<>();
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico glicenciado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        Jerarquia jtitular = (Jerarquia) jeraFacade.findByNombre("TITULAR");
        Jerarquia jasociado = (Jerarquia) jeraFacade.findByNombre("ASOCIADO");
        Jerarquia jasistente = (Jerarquia) jeraFacade.findByNombre("ASISTENTE");
        Jerarquia jinstructor = (Jerarquia) jeraFacade.findByNombre("INSTRUCTOR");
        Jerarquia jayudante = (Jerarquia) jeraFacade.findByNombre("AYUDANTE");
        Jerarquia jadjcat1 = (Jerarquia) jeraFacade.findByNombre("ADJUNTO CATEGORÍA I");
        Jerarquia jadjcat2 = (Jerarquia) jeraFacade.findByNombre("ADJUNTO CATEGORÍA II");
        Jerarquia jinstructor1 = (Jerarquia) jeraFacade.findByNombre("INSTRUCTOR CATEGORÍA I");
        Jerarquia jinstructor2 = (Jerarquia) jeraFacade.findByNombre("INSTRUCTOR CATEGORÍA II");
        Jerarquia jayudantep = (Jerarquia) jeraFacade.findByNombre("AYUDANTE PROFESOR");
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        List<Profesor> doctortitular;
        List<Profesor> doctorasociado;
        List<Profesor> doctorasistente;
        List<Profesor> doctorinstructor;
        List<Profesor> doctorayudante;
        List<Profesor> doctoradjcat1;
        List<Profesor> doctoradjcat2;
        List<Profesor> doctorinstructor1;
        List<Profesor> doctorinstructor2;
        List<Profesor> doctorayudantep;
        List<Profesor> magistertitular;
        List<Profesor> magisterasociado;
        List<Profesor> magisterasistente;
        List<Profesor> magisterinstructor;
        List<Profesor> magisterayudante;
        List<Profesor> magisteradjcat1;
        List<Profesor> magisteradjcat2;
        List<Profesor> magisterinstructor1;
        List<Profesor> magisterinstructor2;
        List<Profesor> magisterayudantep;
        List<Profesor> licenciadotitular;
        List<Profesor> licenciadoasociado;
        List<Profesor> licenciadoasistente;
        List<Profesor> licenciadoinstructor;
        List<Profesor> licenciadoayudante;
        List<Profesor> licenciadoadjcat1;
        List<Profesor> licenciadoadjcat2;
        List<Profesor> licenciadoinstructor1;
        List<Profesor> licenciadoinstructor2;
        List<Profesor> licenciadoayudantep;
        List<Profesor> tituladotitular;
        List<Profesor> tituladoasociado;
        List<Profesor> tituladoasistente;
        List<Profesor> tituladoinstructor;
        List<Profesor> tituladoayudante;
        List<Profesor> tituladoadjcat1;
        List<Profesor> tituladoadjcat2;
        List<Profesor> tituladoinstructor1;
        List<Profesor> tituladoinstructor2;
        List<Profesor> tituladoayudantep;
        
        doctortitular = profeFacade.findByJerarYGradoYDiurnoVespertino(jtitular, gdoctor,"VESPERTINO");
        doctorasociado = profeFacade.findByJerarYGradoYDiurnoVespertino(jasociado, gdoctor,"VESPERTINO");
        doctorasistente = profeFacade.findByJerarYGradoYDiurnoVespertino(jasistente, gdoctor,"VESPERTINO");
        doctorinstructor = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor, gdoctor,"VESPERTINO");
        doctorayudante = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudante, gdoctor,"VESPERTINO");
        doctoradjcat1 = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat1, gdoctor,"VESPERTINO");
        doctoradjcat2 = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat2, gdoctor,"VESPERTINO");
        doctorinstructor1 = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor1, gdoctor,"VESPERTINO");
        doctorinstructor2 = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor2, gdoctor,"VESPERTINO");
        doctorayudantep = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudantep, gdoctor,"VESPERTINO");
        magistertitular = profeFacade.findByJerarYGradoYDiurnoVespertino(jtitular, gmagister,"VESPERTINO");
        magisterasociado  = profeFacade.findByJerarYGradoYDiurnoVespertino(jasociado, gmagister,"VESPERTINO");
        magisterasistente  = profeFacade.findByJerarYGradoYDiurnoVespertino(jasistente, gmagister,"VESPERTINO");
        magisterinstructor = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor, gmagister,"VESPERTINO");
        magisterayudante  = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudante, gmagister,"VESPERTINO");
        magisteradjcat1  = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat1, gmagister,"VESPERTINO");
        magisteradjcat2  = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat2, gmagister,"VESPERTINO");
        magisterinstructor1  = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor1, gmagister,"VESPERTINO");
        magisterinstructor2  = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor2, gmagister,"VESPERTINO");
        magisterayudantep  = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudantep, gmagister,"VESPERTINO");
        licenciadotitular  = profeFacade.findByJerarYGradoYDiurnoVespertino(jtitular, glicenciado,"VESPERTINO");
        licenciadoasociado  = profeFacade.findByJerarYGradoYDiurnoVespertino(jasociado, glicenciado,"VESPERTINO");
        licenciadoasistente  = profeFacade.findByJerarYGradoYDiurnoVespertino(jasistente, glicenciado,"VESPERTINO");
        licenciadoinstructor  = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor, glicenciado,"VESPERTINO");
        licenciadoayudante  = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudante, glicenciado,"VESPERTINO") ;
        licenciadoadjcat1 = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat1, glicenciado,"VESPERTINO");
        licenciadoadjcat2   = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat2, glicenciado,"VESPERTINO");
        licenciadoinstructor1 = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor1, glicenciado,"VESPERTINO");
        licenciadoinstructor2   = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor2, glicenciado,"VESPERTINO");
        licenciadoayudantep = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudantep, glicenciado,"VESPERTINO");
        tituladotitular = profeFacade.findByJerarYGradoYDiurnoVespertino(jtitular, gtitulado,"VESPERTINO");
        tituladoasociado = profeFacade.findByJerarYGradoYDiurnoVespertino(jasociado, gtitulado,"VESPERTINO");
        tituladoasistente  = profeFacade.findByJerarYGradoYDiurnoVespertino(jasistente, gtitulado,"VESPERTINO");
        tituladoinstructor  = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor, gtitulado,"VESPERTINO");
        tituladoayudante  = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudante, gtitulado,"VESPERTINO");
        tituladoadjcat1  = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat1, gtitulado,"VESPERTINO");
        tituladoadjcat2  = profeFacade.findByJerarYGradoYDiurnoVespertino(jadjcat2, gtitulado,"VESPERTINO");
        tituladoinstructor1 = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor1, gtitulado,"VESPERTINO");
        tituladoinstructor2  = profeFacade.findByJerarYGradoYDiurnoVespertino(jinstructor2, gtitulado,"VESPERTINO");
        tituladoayudantep  = profeFacade.findByJerarYGradoYDiurnoVespertino(jayudantep, gtitulado,"VESPERTINO");
        CantJerarquiaGrado nuevod = new CantJerarquiaGrado();
        CantJerarquiaGrado nuevom = new CantJerarquiaGrado();
        CantJerarquiaGrado nuevol = new CantJerarquiaGrado();
        CantJerarquiaGrado nuevot = new CantJerarquiaGrado();
        CantJerarquiaGrado nuevototal = new CantJerarquiaGrado();
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        int cont5 = 0;
        int cont6 = 0;
        int cont7 = 0;
        int cont8 = 0;
        int cont9 = 0;
        int cont10 = 0;
        for (int i = 0; i < doctortitular.size(); i++) {
            if((doctortitular.get(i).getAno_ingreso() <= year) && (doctortitular.get(i).isVigente() == true || doctortitular.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
        }
        for (int i = 0; i < doctorasociado.size(); i++) {
            if((doctorasociado.get(i).getAno_ingreso() <= year) && (doctorasociado.get(i).isVigente() == true || doctorasociado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
        }
        for (int i = 0; i < doctorasistente.size(); i++) {
            if((doctorasistente.get(i).getAno_ingreso() <= year) && (doctorasistente.get(i).isVigente() == true || doctorasistente.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
        }
        for (int i = 0; i < doctorinstructor.size(); i++) {
            if((doctorinstructor.get(i).getAno_ingreso() <= year) && (doctorinstructor.get(i).isVigente() == true || doctorinstructor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        for (int i = 0; i < doctorayudante.size(); i++) {
            if((doctorayudante.get(i).getAno_ingreso() <= year) && (doctorayudante.get(i).isVigente() == true || doctorayudante.get(i).getAnoRetiro() >= year)){
                cont5+=1;
            }
        }
        for (int i = 0; i < doctoradjcat1.size(); i++) {
            if((doctoradjcat1.get(i).getAno_ingreso() <= year) && (doctoradjcat1.get(i).isVigente() == true || doctoradjcat1.get(i).getAnoRetiro() >= year)){
                cont6+=1;
            }
        }
        for (int i = 0; i < doctoradjcat2.size(); i++) {
            if((doctoradjcat2.get(i).getAno_ingreso() <= year) && (doctoradjcat2.get(i).isVigente() == true || doctoradjcat2.get(i).getAnoRetiro() >= year)){
                cont7+=1;
            }
        }
        for (int i = 0; i < doctorinstructor1.size(); i++) {
            if((doctorinstructor1.get(i).getAno_ingreso() <= year) && (doctorinstructor1.get(i).isVigente() == true || doctorinstructor1.get(i).getAnoRetiro() >= year)){
                cont8+=1;
            }
        }
        for (int i = 0; i < doctorinstructor2.size(); i++) {
            if((doctorinstructor2.get(i).getAno_ingreso() <= year) && (doctorinstructor2.get(i).isVigente() == true || doctorinstructor2.get(i).getAnoRetiro() >= year)){
                cont9+=1;
            }
        }
        for (int i = 0; i < doctorayudantep.size(); i++) {
            if((doctorayudantep.get(i).getAno_ingreso() <= year) && (doctorayudantep.get(i).isVigente() == true || doctorayudantep.get(i).getAnoRetiro() >= year)){
                cont10+=1;
            }
        }
        nuevod.setJerar1(cont1);
        nuevod.setJerar2(cont2);
        nuevod.setJerar3(cont3);
        nuevod.setJerar4(cont4);
        nuevod.setJerar5(cont5);
        nuevod.setJerar6(cont6);
        nuevod.setJerar7(cont7);
        nuevod.setJerar8(cont8);
        nuevod.setJerar9(cont9);
        nuevod.setJerar10(cont10);
        nuevod.setGrado("Doctor");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0; cont5 = 0; cont6 = 0; cont7 = 0; cont8 = 0; cont9 = 0; cont10 = 0;
        for (int i = 0; i < magistertitular.size(); i++) {
            if((magistertitular.get(i).getAno_ingreso() <= year) && (magistertitular.get(i).isVigente() == true || magistertitular.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
        }
        for (int i = 0; i < doctorasociado.size(); i++) {
            if((magisterasociado.get(i).getAno_ingreso() <= year) && (magisterasociado.get(i).isVigente() == true || magisterasociado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
        }
        for (int i = 0; i < magisterasistente.size(); i++) {
            if((magisterasistente.get(i).getAno_ingreso() <= year) && (magisterasistente.get(i).isVigente() == true || magisterasistente.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
        }
        for (int i = 0; i < magisterinstructor.size(); i++) {
            if((magisterinstructor.get(i).getAno_ingreso() <= year) && (magisterinstructor.get(i).isVigente() == true || magisterinstructor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        for (int i = 0; i < magisterayudante.size(); i++) {
            if((magisterayudante.get(i).getAno_ingreso() <= year) && (magisterayudante.get(i).isVigente() == true || magisterayudante.get(i).getAnoRetiro() >= year)){
                cont5+=1;
            }
        }
        for (int i = 0; i < magisteradjcat1.size(); i++) {
            if((magisteradjcat1.get(i).getAno_ingreso() <= year) && (magisteradjcat1.get(i).isVigente() == true || magisteradjcat1.get(i).getAnoRetiro() >= year)){
                cont6+=1;
            }
        }
        for (int i = 0; i < doctoradjcat2.size(); i++) {
            if((magisteradjcat2.get(i).getAno_ingreso() <= year) && (magisteradjcat2.get(i).isVigente() == true || magisteradjcat2.get(i).getAnoRetiro() >= year)){
                cont7+=1;
            }
        }
        for (int i = 0; i < magisterinstructor1.size(); i++) {
            if((magisterinstructor1.get(i).getAno_ingreso() <= year) && (magisterinstructor1.get(i).isVigente() == true || magisterinstructor1.get(i).getAnoRetiro() >= year)){
                cont8+=1;
            }
        }
        for (int i = 0; i < magisterinstructor2.size(); i++) {
            if((magisterinstructor2.get(i).getAno_ingreso() <= year) && (magisterinstructor2.get(i).isVigente() == true || magisterinstructor2.get(i).getAnoRetiro() >= year)){
                cont9+=1;
            }
        }
        for (int i = 0; i < magisterayudantep.size(); i++) {
            if((magisterayudantep.get(i).getAno_ingreso() <= year) && (magisterayudantep.get(i).isVigente() == true || magisterayudantep.get(i).getAnoRetiro() >= year)){
                cont10+=1;
            }
        }
        nuevom.setJerar1(cont1);
        nuevom.setJerar2(cont2);
        nuevom.setJerar3(cont3);
        nuevom.setJerar4(cont4);
        nuevom.setJerar5(cont5);
        nuevom.setJerar6(cont6);
        nuevom.setJerar7(cont7);
        nuevom.setJerar8(cont8);
        nuevom.setJerar9(cont9);
        nuevom.setJerar10(cont10);
        nuevom.setGrado("Magister");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0; cont5 = 0; cont6 = 0; cont7 = 0; cont8 = 0; cont9 = 0; cont10 = 0;
        for (int i = 0; i < licenciadotitular.size(); i++) {
            if((licenciadotitular.get(i).getAno_ingreso() <= year) && (licenciadotitular.get(i).isVigente() == true || licenciadotitular.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
        }
        for (int i = 0; i < licenciadoasociado.size(); i++) {
            if((licenciadoasociado.get(i).getAno_ingreso() <= year) && (licenciadoasociado.get(i).isVigente() == true || licenciadoasociado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
        }
        for (int i = 0; i < licenciadoasistente.size(); i++) {
            if((licenciadoasistente.get(i).getAno_ingreso() <= year) && (licenciadoasistente.get(i).isVigente() == true || licenciadoasistente.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
        }
        for (int i = 0; i < licenciadoinstructor.size(); i++) {
            if((licenciadoinstructor.get(i).getAno_ingreso() <= year) && (licenciadoinstructor.get(i).isVigente() == true || licenciadoinstructor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        for (int i = 0; i < licenciadoayudante.size(); i++) {
            if((licenciadoayudante.get(i).getAno_ingreso() <= year) && (licenciadoayudante.get(i).isVigente() == true || licenciadoayudante.get(i).getAnoRetiro() >= year)){
                cont5+=1;
            }
        }
        for (int i = 0; i < licenciadoadjcat1.size(); i++) {
            if((licenciadoadjcat1.get(i).getAno_ingreso() <= year) && (licenciadoadjcat1.get(i).isVigente() == true || licenciadoadjcat1.get(i).getAnoRetiro() >= year)){
                cont6+=1;
            }
        }
        for (int i = 0; i < licenciadoadjcat2.size(); i++) {
            if((licenciadoadjcat2.get(i).getAno_ingreso() <= year) && (licenciadoadjcat2.get(i).isVigente() == true || licenciadoadjcat2.get(i).getAnoRetiro() >= year)){
                cont7+=1;
            }
        }
        for (int i = 0; i < licenciadoinstructor1.size(); i++) {
            if((licenciadoinstructor1.get(i).getAno_ingreso() <= year) && (licenciadoinstructor1.get(i).isVigente() == true || licenciadoinstructor1.get(i).getAnoRetiro() >= year)){
                cont8+=1;
            }
        }
        for (int i = 0; i < licenciadoinstructor2.size(); i++) {
            if((licenciadoinstructor2.get(i).getAno_ingreso() <= year) && (licenciadoinstructor2.get(i).isVigente() == true || licenciadoinstructor2.get(i).getAnoRetiro() >= year)){
                cont9+=1;
            }
        }
        for (int i = 0; i < licenciadoayudantep.size(); i++) {
            if((licenciadoayudantep.get(i).getAno_ingreso() <= year) && (licenciadoayudantep.get(i).isVigente() == true || licenciadoayudantep.get(i).getAnoRetiro() >= year)){
                cont10+=1;
            }
        }
        nuevol.setJerar1(cont1);
        nuevol.setJerar2(cont2);
        nuevol.setJerar3(cont3);
        nuevol.setJerar4(cont4);
        nuevol.setJerar5(cont5);
        nuevol.setJerar6(cont6);
        nuevol.setJerar7(cont7);
        nuevol.setJerar8(cont8);
        nuevol.setJerar9(cont9);
        nuevol.setJerar10(cont10);
        nuevol.setGrado("Licenciado");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0; cont5 = 0; cont6 = 0; cont7 = 0; cont8 = 0; cont9 = 0; cont10 = 0;
        for (int i = 0; i < tituladotitular.size(); i++) {
            if((licenciadotitular.get(i).getAno_ingreso() <= year) && (licenciadotitular.get(i).isVigente() == true || licenciadotitular.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
        }
        for (int i = 0; i < tituladoasociado.size(); i++) {
            if((tituladoasociado.get(i).getAno_ingreso() <= year) && (tituladoasociado.get(i).isVigente() == true || tituladoasociado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
        }
        for (int i = 0; i < tituladoasistente.size(); i++) {
            if((tituladoasistente.get(i).getAno_ingreso() <= year) && (tituladoasistente.get(i).isVigente() == true || tituladoasistente.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
        }
        for (int i = 0; i < tituladoinstructor.size(); i++) {
            if((tituladoinstructor.get(i).getAno_ingreso() <= year) && (tituladoinstructor.get(i).isVigente() == true || tituladoinstructor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        for (int i = 0; i < tituladoayudante.size(); i++) {
            if((tituladoayudante.get(i).getAno_ingreso() <= year) && (tituladoayudante.get(i).isVigente() == true || tituladoayudante.get(i).getAnoRetiro() >= year)){
                cont5+=1;
            }
        }
        for (int i = 0; i < tituladoadjcat1.size(); i++) {
            if((tituladoadjcat1.get(i).getAno_ingreso() <= year) && (tituladoadjcat1.get(i).isVigente() == true || tituladoadjcat1.get(i).getAnoRetiro() >= year)){
                cont6+=1;
            }
        }
        for (int i = 0; i < tituladoadjcat2.size(); i++) {
            if((tituladoadjcat2.get(i).getAno_ingreso() <= year) && (tituladoadjcat2.get(i).isVigente() == true || tituladoadjcat2.get(i).getAnoRetiro() >= year)){
                cont7+=1;
            }
        }
        for (int i = 0; i < tituladoinstructor1.size(); i++) {
            if((tituladoinstructor1.get(i).getAno_ingreso() <= year) && (tituladoinstructor1.get(i).isVigente() == true || tituladoinstructor1.get(i).getAnoRetiro() >= year)){
                cont8+=1;
            }
        }
        for (int i = 0; i < tituladoinstructor2.size(); i++) {
            if((tituladoinstructor2.get(i).getAno_ingreso() <= year) && (tituladoinstructor2.get(i).isVigente() == true || tituladoinstructor2.get(i).getAnoRetiro() >= year)){
                cont9+=1;
            }
        }
        for (int i = 0; i < tituladoayudantep.size(); i++) {
            if((tituladoayudantep.get(i).getAno_ingreso() <= year) && (tituladoayudantep.get(i).isVigente() == true || tituladoayudantep.get(i).getAnoRetiro() >= year)){
                cont10+=1;
            }
        }
        nuevot.setJerar1(cont1);
        nuevot.setJerar2(cont2);
        nuevot.setJerar3(cont3);
        nuevot.setJerar4(cont4);
        nuevot.setJerar5(cont5);
        nuevot.setJerar6(cont6);
        nuevot.setJerar7(cont7);
        nuevot.setJerar8(cont8);
        nuevot.setJerar9(cont9);
        nuevot.setJerar10(cont10);
        nuevod.setTotal(cont1+cont2+cont3+cont4+cont5+cont6+cont7+cont8+cont9+cont10);
        nuevot.setGrado("Titulado");
        nuevototal.setGrado("Total");
        nuevototal.setJerar1(nuevod.getJerar1()+nuevom.getJerar1()+nuevol.getJerar1()+nuevot.getJerar1());
        nuevototal.setJerar2(nuevod.getJerar2()+nuevom.getJerar2()+nuevol.getJerar2()+nuevot.getJerar2());
        nuevototal.setJerar3(nuevod.getJerar3()+nuevom.getJerar3()+nuevol.getJerar3()+nuevot.getJerar3());
        nuevototal.setJerar4(nuevod.getJerar4()+nuevom.getJerar4()+nuevol.getJerar4()+nuevot.getJerar4());
        nuevototal.setJerar5(nuevod.getJerar5()+nuevom.getJerar5()+nuevol.getJerar5()+nuevot.getJerar5());
        nuevototal.setJerar6(nuevod.getJerar6()+nuevom.getJerar6()+nuevol.getJerar6()+nuevot.getJerar6());
        nuevototal.setJerar7(nuevod.getJerar7()+nuevom.getJerar7()+nuevol.getJerar7()+nuevot.getJerar7());
        nuevototal.setJerar8(nuevod.getJerar8()+nuevom.getJerar8()+nuevol.getJerar8()+nuevot.getJerar8());
        nuevototal.setJerar9(nuevod.getJerar9()+nuevom.getJerar9()+nuevol.getJerar9()+nuevot.getJerar9());
        nuevototal.setJerar10(nuevod.getJerar10()+nuevom.getJerar10()+nuevol.getJerar10()+nuevot.getJerar10());
        nuevototal.setTotal(nuevod.getTotal()+nuevom.getTotal()+nuevol.getTotal()+nuevot.getTotal());
        cant_jerar_grado_vespertino.add(nuevod);
        cant_jerar_grado_vespertino.add(nuevom);
        cant_jerar_grado_vespertino.add(nuevol);
        cant_jerar_grado_vespertino.add(nuevot);
        cant_jerar_grado_vespertino.add(nuevototal);
        return cant_jerar_grado_vespertino;
    }

    public void setCant_jerar_grado_vespertino(List<CantJerarquiaGrado> cant_jerar_grado_vespertino) {
        this.cant_jerar_grado_vespertino = cant_jerar_grado_vespertino;
    }

    public List<GradoCant> getGrado_cant_ano_1_diurno() {
        grado_cant_ano_1_diurno = new ArrayList<>();
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico glicenciado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        List<Profesor> doctor = profeFacade.findByGradoYDiurnoVespertino(gdoctor,"DIURNO");
        List<Profesor> magister = profeFacade.findByGradoYDiurnoVespertino(gmagister,"DIURNO");
        List<Profesor> licenciado = profeFacade.findByGradoYDiurnoVespertino(glicenciado,"DIURNO");
        List<Profesor> titulado = profeFacade.findByGradoYDiurnoVespertino(gtitulado,"DIURNO");
        GradoCant nuevod = new GradoCant();
        GradoCant nuevom = new GradoCant();
        GradoCant nuevol = new GradoCant();
        GradoCant nuevot = new GradoCant();
        GradoCant nuevototal = new GradoCant();
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        for (int i = 0; i < doctor.size(); i++) {
            if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getContrato().equals("Completa")) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()<=10) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=11 && doctor.get(i).getDedicacion_contratada()<=21) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=22) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevod.setCant1(cont1);
        nuevod.setCant2(cont2);
        nuevod.setCant3(cont3);
        nuevod.setCant4(cont4);
        nuevod.setGrado("Doctor");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < magister.size(); i++) {
            if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getContrato().equals("Completa")) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()<=10) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=11 && magister.get(i).getDedicacion_contratada()<=21) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=22) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevom.setCant1(cont1);
        nuevom.setCant2(cont2);
        nuevom.setCant3(cont3);
        nuevom.setCant4(cont4);
        nuevom.setGrado("Magister");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < licenciado.size(); i++) {
            if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getContrato().equals("Completa")) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()<=10) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=11 && licenciado.get(i).getDedicacion_contratada()<=21) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=22) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevol.setCant1(cont1);
        nuevol.setCant2(cont2);
        nuevol.setCant3(cont3);
        nuevol.setCant4(cont4);
        nuevol.setGrado("Licenciado");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < titulado.size(); i++) {
            if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getContrato().equals("Completa")) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()<=10) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=11 && titulado.get(i).getDedicacion_contratada()<=21) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=22) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevot.setCant1(cont1);
        nuevot.setCant2(cont2);
        nuevot.setCant3(cont3);
        nuevot.setCant4(cont4);
        nuevot.setGrado("Titulado");
        nuevototal.setCant1(nuevod.getCant1()+nuevom.getCant1()+nuevol.getCant1()+nuevot.getCant1());
        nuevototal.setCant2(nuevod.getCant2()+nuevom.getCant2()+nuevol.getCant2()+nuevot.getCant2());
        nuevototal.setCant3(nuevod.getCant3()+nuevom.getCant3()+nuevol.getCant3()+nuevot.getCant3());
        nuevototal.setCant4(nuevod.getCant4()+nuevom.getCant4()+nuevol.getCant4()+nuevot.getCant4());
        nuevototal.setGrado("Total");
        grado_cant_ano_1_diurno.add(nuevod);
        grado_cant_ano_1_diurno.add(nuevom);
        grado_cant_ano_1_diurno.add(nuevol);
        grado_cant_ano_1_diurno.add(nuevot);
        grado_cant_ano_1_diurno.add(nuevototal);
        return grado_cant_ano_1_diurno;
    }

    public void setGrado_cant_ano_1_diurno(List<GradoCant> grado_cant_ano_1_diurno) {
        this.grado_cant_ano_1_diurno = grado_cant_ano_1_diurno;
    }

    public List<GradoCant> getGrado_cant_ano_2_diurno() {
        grado_cant_ano_2_diurno = new ArrayList<>();
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico glicenciado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        List<Profesor> doctor = profeFacade.findByGradoYDiurnoVespertino(gdoctor,"DIURNO");
        List<Profesor> magister = profeFacade.findByGradoYDiurnoVespertino(gmagister,"DIURNO");
        List<Profesor> licenciado = profeFacade.findByGradoYDiurnoVespertino(glicenciado,"DIURNO");
        List<Profesor> titulado = profeFacade.findByGradoYDiurnoVespertino(gtitulado,"DIURNO");
        GradoCant nuevod = new GradoCant();
        GradoCant nuevom = new GradoCant();
        GradoCant nuevol = new GradoCant();
        GradoCant nuevot = new GradoCant();
        GradoCant nuevototal = new GradoCant();
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        for (int i = 0; i < doctor.size(); i++) {
            if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getContrato().equals("Completa")) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()<=10) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=11 && doctor.get(i).getDedicacion_contratada()<=21) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=22) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevod.setCant1(cont1);
        nuevod.setCant2(cont2);
        nuevod.setCant3(cont3);
        nuevod.setCant4(cont4);
        nuevod.setGrado("Doctor");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < magister.size(); i++) {
            if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getContrato().equals("Completa")) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()<=10) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=11 && magister.get(i).getDedicacion_contratada()<=21) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=22) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevom.setCant1(cont1);
        nuevom.setCant2(cont2);
        nuevom.setCant3(cont3);
        nuevom.setCant4(cont4);
        nuevom.setGrado("Magister");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < licenciado.size(); i++) {
            if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getContrato().equals("Completa")) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()<=10) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=11 && licenciado.get(i).getDedicacion_contratada()<=21) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=22) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevol.setCant1(cont1);
        nuevol.setCant2(cont2);
        nuevol.setCant3(cont3);
        nuevol.setCant4(cont4);
        nuevol.setGrado("Licenciado");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < titulado.size(); i++) {
            if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getContrato().equals("Completa")) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()<=10) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=11 && titulado.get(i).getDedicacion_contratada()<=21) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=22) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevot.setCant1(cont1);
        nuevot.setCant2(cont2);
        nuevot.setCant3(cont3);
        nuevot.setCant4(cont4);
        nuevot.setGrado("Titulado");
        nuevototal.setCant1(nuevod.getCant1()+nuevom.getCant1()+nuevol.getCant1()+nuevot.getCant1());
        nuevototal.setCant2(nuevod.getCant2()+nuevom.getCant2()+nuevol.getCant2()+nuevot.getCant2());
        nuevototal.setCant3(nuevod.getCant3()+nuevom.getCant3()+nuevol.getCant3()+nuevot.getCant3());
        nuevototal.setCant4(nuevod.getCant4()+nuevom.getCant4()+nuevol.getCant4()+nuevot.getCant4());
        nuevototal.setGrado("Total");
        grado_cant_ano_2_diurno.add(nuevod);
        grado_cant_ano_2_diurno.add(nuevom);
        grado_cant_ano_2_diurno.add(nuevol);
        grado_cant_ano_2_diurno.add(nuevot);
        grado_cant_ano_2_diurno.add(nuevototal);
        return grado_cant_ano_2_diurno;
    }

    public void setGrado_cant_ano_2_diurno(List<GradoCant> grado_cant_ano_2_diurno) {
        this.grado_cant_ano_2_diurno = grado_cant_ano_2_diurno;
    }

    public List<GradoCant> getGrado_cant_ano_3_diurno() {
        grado_cant_ano_3_diurno = new ArrayList<>();
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico glicenciado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        List<Profesor> doctor = profeFacade.findByGradoYDiurnoVespertino(gdoctor,"DIURNO");
        List<Profesor> magister = profeFacade.findByGradoYDiurnoVespertino(gmagister,"DIURNO");
        List<Profesor> licenciado = profeFacade.findByGradoYDiurnoVespertino(glicenciado,"DIURNO");
        List<Profesor> titulado = profeFacade.findByGradoYDiurnoVespertino(gtitulado,"DIURNO");
        GradoCant nuevod = new GradoCant();
        GradoCant nuevom = new GradoCant();
        GradoCant nuevol = new GradoCant();
        GradoCant nuevot = new GradoCant();
        GradoCant nuevototal = new GradoCant();
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        for (int i = 0; i < doctor.size(); i++) {
            if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getContrato().equals("Completa")) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()<=10) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=11 && doctor.get(i).getDedicacion_contratada()<=21) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=22) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevod.setCant1(cont1);
        nuevod.setCant2(cont2);
        nuevod.setCant3(cont3);
        nuevod.setCant4(cont4);
        nuevod.setGrado("Doctor");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < magister.size(); i++) {
            if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getContrato().equals("Completa")) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()<=10) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=11 && magister.get(i).getDedicacion_contratada()<=21) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=22) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevom.setCant1(cont1);
        nuevom.setCant2(cont2);
        nuevom.setCant3(cont3);
        nuevom.setCant4(cont4);
        nuevom.setGrado("Magister");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < licenciado.size(); i++) {
            if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getContrato().equals("Completa")) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()<=10) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=11 && licenciado.get(i).getDedicacion_contratada()<=21) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=22) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevol.setCant1(cont1);
        nuevol.setCant2(cont2);
        nuevol.setCant3(cont3);
        nuevol.setCant4(cont4);
        nuevol.setGrado("Licenciado");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < titulado.size(); i++) {
            if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getContrato().equals("Completa")) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()<=10) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=11 && titulado.get(i).getDedicacion_contratada()<=21) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=22) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevot.setCant1(cont1);
        nuevot.setCant2(cont2);
        nuevot.setCant3(cont3);
        nuevot.setCant4(cont4);
        nuevot.setGrado("Titulado");
        nuevototal.setCant1(nuevod.getCant1()+nuevom.getCant1()+nuevol.getCant1()+nuevot.getCant1());
        nuevototal.setCant2(nuevod.getCant2()+nuevom.getCant2()+nuevol.getCant2()+nuevot.getCant2());
        nuevototal.setCant3(nuevod.getCant3()+nuevom.getCant3()+nuevol.getCant3()+nuevot.getCant3());
        nuevototal.setCant4(nuevod.getCant4()+nuevom.getCant4()+nuevol.getCant4()+nuevot.getCant4());
        nuevototal.setGrado("Total");
        grado_cant_ano_3_diurno.add(nuevod);
        grado_cant_ano_3_diurno.add(nuevom);
        grado_cant_ano_3_diurno.add(nuevol);
        grado_cant_ano_3_diurno.add(nuevot);
        grado_cant_ano_3_diurno.add(nuevototal);
        return grado_cant_ano_3_diurno;
    }

    public void setGrado_cant_ano_3_diurno(List<GradoCant> grado_cant_ano_3_diurno) {
        this.grado_cant_ano_3_diurno = grado_cant_ano_3_diurno;
    }

    public List<GradoCant> getGrado_cant_ano_4_diurno() {
        grado_cant_ano_4_diurno = new ArrayList<>();
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico glicenciado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        List<Profesor> doctor = profeFacade.findByGradoYDiurnoVespertino(gdoctor,"DIURNO");
        List<Profesor> magister = profeFacade.findByGradoYDiurnoVespertino(gmagister,"DIURNO");
        List<Profesor> licenciado = profeFacade.findByGradoYDiurnoVespertino(glicenciado,"DIURNO");
        List<Profesor> titulado = profeFacade.findByGradoYDiurnoVespertino(gtitulado,"DIURNO");
        GradoCant nuevod = new GradoCant();
        GradoCant nuevom = new GradoCant();
        GradoCant nuevol = new GradoCant();
        GradoCant nuevot = new GradoCant();
        GradoCant nuevototal = new GradoCant();
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1896;
        for (int i = 0; i < doctor.size(); i++) {
            if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getContrato().equals("Completa")) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()<=10) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=11 && doctor.get(i).getDedicacion_contratada()<=21) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=22) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevod.setCant1(cont1);
        nuevod.setCant2(cont2);
        nuevod.setCant3(cont3);
        nuevod.setCant4(cont4);
        nuevod.setGrado("Doctor");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < magister.size(); i++) {
            if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getContrato().equals("Completa")) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()<=10) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=11 && magister.get(i).getDedicacion_contratada()<=21) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=22) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevom.setCant1(cont1);
        nuevom.setCant2(cont2);
        nuevom.setCant3(cont3);
        nuevom.setCant4(cont4);
        nuevom.setGrado("Magister");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < licenciado.size(); i++) {
            if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getContrato().equals("Completa")) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()<=10) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=11 && licenciado.get(i).getDedicacion_contratada()<=21) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=22) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevol.setCant1(cont1);
        nuevol.setCant2(cont2);
        nuevol.setCant3(cont3);
        nuevol.setCant4(cont4);
        nuevol.setGrado("Licenciado");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < titulado.size(); i++) {
            if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getContrato().equals("Completa")) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()<=10) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=11 && titulado.get(i).getDedicacion_contratada()<=21) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=22) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevot.setCant1(cont1);
        nuevot.setCant2(cont2);
        nuevot.setCant3(cont3);
        nuevot.setCant4(cont4);
        nuevot.setGrado("Titulado");
        nuevototal.setCant1(nuevod.getCant1()+nuevom.getCant1()+nuevol.getCant1()+nuevot.getCant1());
        nuevototal.setCant2(nuevod.getCant2()+nuevom.getCant2()+nuevol.getCant2()+nuevot.getCant2());
        nuevototal.setCant3(nuevod.getCant3()+nuevom.getCant3()+nuevol.getCant3()+nuevot.getCant3());
        nuevototal.setCant4(nuevod.getCant4()+nuevom.getCant4()+nuevol.getCant4()+nuevot.getCant4());
        nuevototal.setGrado("Total");
        grado_cant_ano_4_diurno.add(nuevod);
        grado_cant_ano_4_diurno.add(nuevom);
        grado_cant_ano_4_diurno.add(nuevol);
        grado_cant_ano_4_diurno.add(nuevot);
        grado_cant_ano_4_diurno.add(nuevototal);
        return grado_cant_ano_4_diurno;
    }

    public void setGrado_cant_ano_4_diurno(List<GradoCant> grado_cant_ano_4_diurno) {
        this.grado_cant_ano_4_diurno = grado_cant_ano_4_diurno;
    }

    public List<GradoCant> getGrado_cant_ano_1_vespertino() {
        grado_cant_ano_1_vespertino = new ArrayList<>();
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico glicenciado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        List<Profesor> doctor = profeFacade.findByGradoYDiurnoVespertino(gdoctor,"VESPERTINO");
        List<Profesor> magister = profeFacade.findByGradoYDiurnoVespertino(gmagister,"VESPERTINO");
        List<Profesor> licenciado = profeFacade.findByGradoYDiurnoVespertino(glicenciado,"VESPERTINO");
        List<Profesor> titulado = profeFacade.findByGradoYDiurnoVespertino(gtitulado,"VESPERTINO");
        GradoCant nuevod = new GradoCant();
        GradoCant nuevom = new GradoCant();
        GradoCant nuevol = new GradoCant();
        GradoCant nuevot = new GradoCant();
        GradoCant nuevototal = new GradoCant();
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        for (int i = 0; i < doctor.size(); i++) {
            if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getContrato().equals("Completa")) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()<=10) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=11 && doctor.get(i).getDedicacion_contratada()<=21) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=22) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevod.setCant1(cont1);
        nuevod.setCant2(cont2);
        nuevod.setCant3(cont3);
        nuevod.setCant4(cont4);
        nuevod.setGrado("Doctor");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < magister.size(); i++) {
            if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getContrato().equals("Completa")) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()<=10) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=11 && magister.get(i).getDedicacion_contratada()<=21) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=22) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevom.setCant1(cont1);
        nuevom.setCant2(cont2);
        nuevom.setCant3(cont3);
        nuevom.setCant4(cont4);
        nuevom.setGrado("Magister");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < licenciado.size(); i++) {
            if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getContrato().equals("Completa")) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()<=10) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=11 && licenciado.get(i).getDedicacion_contratada()<=21) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=22) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevol.setCant1(cont1);
        nuevol.setCant2(cont2);
        nuevol.setCant3(cont3);
        nuevol.setCant4(cont4);
        nuevol.setGrado("Licenciado");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < titulado.size(); i++) {
            if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getContrato().equals("Completa")) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()<=10) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=11 && titulado.get(i).getDedicacion_contratada()<=21) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=22) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevot.setCant1(cont1);
        nuevot.setCant2(cont2);
        nuevot.setCant3(cont3);
        nuevot.setCant4(cont4);
        nuevot.setGrado("Titulado");
        nuevototal.setCant1(nuevod.getCant1()+nuevom.getCant1()+nuevol.getCant1()+nuevot.getCant1());
        nuevototal.setCant2(nuevod.getCant2()+nuevom.getCant2()+nuevol.getCant2()+nuevot.getCant2());
        nuevototal.setCant3(nuevod.getCant3()+nuevom.getCant3()+nuevol.getCant3()+nuevot.getCant3());
        nuevototal.setCant4(nuevod.getCant4()+nuevom.getCant4()+nuevol.getCant4()+nuevot.getCant4());
        nuevototal.setGrado("Total");
        grado_cant_ano_1_vespertino.add(nuevod);
        grado_cant_ano_1_vespertino.add(nuevom);
        grado_cant_ano_1_vespertino.add(nuevol);
        grado_cant_ano_1_vespertino.add(nuevot);
        grado_cant_ano_1_vespertino.add(nuevototal);
        return grado_cant_ano_1_vespertino;
    }

    public void setGrado_cant_ano_1_vespertino(List<GradoCant> grado_cant_ano_1_vespertino) {
        this.grado_cant_ano_1_vespertino = grado_cant_ano_1_vespertino;
    }

    public List<GradoCant> getGrado_cant_ano_2_vespertino() {
        grado_cant_ano_2_vespertino = new ArrayList<>();
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico glicenciado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        List<Profesor> doctor = profeFacade.findByGradoYDiurnoVespertino(gdoctor,"VESPERTINO");
        List<Profesor> magister = profeFacade.findByGradoYDiurnoVespertino(gmagister,"VESPERTINO");
        List<Profesor> licenciado = profeFacade.findByGradoYDiurnoVespertino(glicenciado,"VESPERTINO");
        List<Profesor> titulado = profeFacade.findByGradoYDiurnoVespertino(gtitulado,"VESPERTINO");
        GradoCant nuevod = new GradoCant();
        GradoCant nuevom = new GradoCant();
        GradoCant nuevol = new GradoCant();
        GradoCant nuevot = new GradoCant();
        GradoCant nuevototal = new GradoCant();
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1898;
        for (int i = 0; i < doctor.size(); i++) {
            if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getContrato().equals("Completa")) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()<=10) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=11 && doctor.get(i).getDedicacion_contratada()<=21) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=22) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevod.setCant1(cont1);
        nuevod.setCant2(cont2);
        nuevod.setCant3(cont3);
        nuevod.setCant4(cont4);
        nuevod.setGrado("Doctor");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < magister.size(); i++) {
            if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getContrato().equals("Completa")) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()<=10) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=11 && magister.get(i).getDedicacion_contratada()<=21) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=22) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevom.setCant1(cont1);
        nuevom.setCant2(cont2);
        nuevom.setCant3(cont3);
        nuevom.setCant4(cont4);
        nuevom.setGrado("Magister");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < licenciado.size(); i++) {
            if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getContrato().equals("Completa")) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()<=10) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=11 && licenciado.get(i).getDedicacion_contratada()<=21) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=22) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevol.setCant1(cont1);
        nuevol.setCant2(cont2);
        nuevol.setCant3(cont3);
        nuevol.setCant4(cont4);
        nuevol.setGrado("Licenciado");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < titulado.size(); i++) {
            if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getContrato().equals("Completa")) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()<=10) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=11 && titulado.get(i).getDedicacion_contratada()<=21) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=22) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevot.setCant1(cont1);
        nuevot.setCant2(cont2);
        nuevot.setCant3(cont3);
        nuevot.setCant4(cont4);
        nuevot.setGrado("Titulado");
        nuevototal.setCant1(nuevod.getCant1()+nuevom.getCant1()+nuevol.getCant1()+nuevot.getCant1());
        nuevototal.setCant2(nuevod.getCant2()+nuevom.getCant2()+nuevol.getCant2()+nuevot.getCant2());
        nuevototal.setCant3(nuevod.getCant3()+nuevom.getCant3()+nuevol.getCant3()+nuevot.getCant3());
        nuevototal.setCant4(nuevod.getCant4()+nuevom.getCant4()+nuevol.getCant4()+nuevot.getCant4());
        nuevototal.setGrado("Total");
        grado_cant_ano_2_vespertino.add(nuevod);
        grado_cant_ano_2_vespertino.add(nuevom);
        grado_cant_ano_2_vespertino.add(nuevol);
        grado_cant_ano_2_vespertino.add(nuevot);
        grado_cant_ano_2_vespertino.add(nuevototal);
        return grado_cant_ano_2_vespertino;
    }

    public void setGrado_cant_ano_2_vespertino(List<GradoCant> grado_cant_ano_2_vespertino) {
        this.grado_cant_ano_2_vespertino = grado_cant_ano_2_vespertino;
    }

    public List<GradoCant> getGrado_cant_ano_3_vespertino() {
        grado_cant_ano_3_vespertino = new ArrayList<>();
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico glicenciado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        List<Profesor> doctor = profeFacade.findByGradoYDiurnoVespertino(gdoctor,"VESPERTINO");
        List<Profesor> magister = profeFacade.findByGradoYDiurnoVespertino(gmagister,"VESPERTINO");
        List<Profesor> licenciado = profeFacade.findByGradoYDiurnoVespertino(glicenciado,"VESPERTINO");
        List<Profesor> titulado = profeFacade.findByGradoYDiurnoVespertino(gtitulado,"VESPERTINO");
        GradoCant nuevod = new GradoCant();
        GradoCant nuevom = new GradoCant();
        GradoCant nuevol = new GradoCant();
        GradoCant nuevot = new GradoCant();
        GradoCant nuevototal = new GradoCant();
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1897;
        for (int i = 0; i < doctor.size(); i++) {
            if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getContrato().equals("Completa")) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()<=10) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=11 && doctor.get(i).getDedicacion_contratada()<=21) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=22) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevod.setCant1(cont1);
        nuevod.setCant2(cont2);
        nuevod.setCant3(cont3);
        nuevod.setCant4(cont4);
        nuevod.setGrado("Doctor");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < magister.size(); i++) {
            if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getContrato().equals("Completa")) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()<=10) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=11 && magister.get(i).getDedicacion_contratada()<=21) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=22) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevom.setCant1(cont1);
        nuevom.setCant2(cont2);
        nuevom.setCant3(cont3);
        nuevom.setCant4(cont4);
        nuevom.setGrado("Magister");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < licenciado.size(); i++) {
            if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getContrato().equals("Completa")) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()<=10) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=11 && licenciado.get(i).getDedicacion_contratada()<=21) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=22) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevol.setCant1(cont1);
        nuevol.setCant2(cont2);
        nuevol.setCant3(cont3);
        nuevol.setCant4(cont4);
        nuevol.setGrado("Licenciado");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < titulado.size(); i++) {
            if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getContrato().equals("Completa")) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()<=10) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=11 && titulado.get(i).getDedicacion_contratada()<=21) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=22) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevot.setCant1(cont1);
        nuevot.setCant2(cont2);
        nuevot.setCant3(cont3);
        nuevot.setCant4(cont4);
        nuevot.setGrado("Titulado");
        nuevototal.setCant1(nuevod.getCant1()+nuevom.getCant1()+nuevol.getCant1()+nuevot.getCant1());
        nuevototal.setCant2(nuevod.getCant2()+nuevom.getCant2()+nuevol.getCant2()+nuevot.getCant2());
        nuevototal.setCant3(nuevod.getCant3()+nuevom.getCant3()+nuevol.getCant3()+nuevot.getCant3());
        nuevototal.setCant4(nuevod.getCant4()+nuevom.getCant4()+nuevol.getCant4()+nuevot.getCant4());
        nuevototal.setGrado("Total");
        grado_cant_ano_3_vespertino.add(nuevod);
        grado_cant_ano_3_vespertino.add(nuevom);
        grado_cant_ano_3_vespertino.add(nuevol);
        grado_cant_ano_3_vespertino.add(nuevot);
        grado_cant_ano_3_vespertino.add(nuevototal);
        return grado_cant_ano_3_vespertino;
    }

    public void setGrado_cant_ano_3_vespertino(List<GradoCant> grado_cant_ano_3_vespertino) {
        this.grado_cant_ano_3_vespertino = grado_cant_ano_3_vespertino;
    }

    public List<GradoCant> getGrado_cant_ano_4_vespertino() {
        grado_cant_ano_4_vespertino = new ArrayList<>();
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico glicenciado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        List<Profesor> doctor = profeFacade.findByGradoYDiurnoVespertino(gdoctor,"VESPERTINO");
        List<Profesor> magister = profeFacade.findByGradoYDiurnoVespertino(gmagister,"VESPERTINO");
        List<Profesor> licenciado = profeFacade.findByGradoYDiurnoVespertino(glicenciado,"VESPERTINO");
        List<Profesor> titulado = profeFacade.findByGradoYDiurnoVespertino(gtitulado,"VESPERTINO");
        GradoCant nuevod = new GradoCant();
        GradoCant nuevom = new GradoCant();
        GradoCant nuevol = new GradoCant();
        GradoCant nuevot = new GradoCant();
        GradoCant nuevototal = new GradoCant();
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1899;
        for (int i = 0; i < doctor.size(); i++) {
            if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getContrato().equals("Completa")) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()<=10) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=11 && doctor.get(i).getDedicacion_contratada()<=21) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((doctor.get(i).getAno_ingreso() <= year && doctor.get(i).getDedicacion_contratada()>=22) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevod.setCant1(cont1);
        nuevod.setCant2(cont2);
        nuevod.setCant3(cont3);
        nuevod.setCant4(cont4);
        nuevod.setGrado("Doctor");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < magister.size(); i++) {
            if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getContrato().equals("Completa")) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()<=10) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=11 && magister.get(i).getDedicacion_contratada()<=21) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((magister.get(i).getAno_ingreso() <= year && magister.get(i).getDedicacion_contratada()>=22) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevom.setCant1(cont1);
        nuevom.setCant2(cont2);
        nuevom.setCant3(cont3);
        nuevom.setCant4(cont4);
        nuevom.setGrado("Magister");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < licenciado.size(); i++) {
            if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getContrato().equals("Completa")) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()<=10) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=11 && licenciado.get(i).getDedicacion_contratada()<=21) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((licenciado.get(i).getAno_ingreso() <= year && licenciado.get(i).getDedicacion_contratada()>=22) && (licenciado.get(i).isVigente() == true || licenciado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevol.setCant1(cont1);
        nuevol.setCant2(cont2);
        nuevol.setCant3(cont3);
        nuevol.setCant4(cont4);
        nuevol.setGrado("Licenciado");
        cont1 = 0; cont2 = 0; cont3 = 0; cont4 = 0;
        for (int i = 0; i < titulado.size(); i++) {
            if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getContrato().equals("Completa")) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()<=10) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont2+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=11 && titulado.get(i).getDedicacion_contratada()<=21) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont3+=1;
            }
            else if((titulado.get(i).getAno_ingreso() <= year && titulado.get(i).getDedicacion_contratada()>=22) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont4+=1;
            }
        }
        nuevot.setCant1(cont1);
        nuevot.setCant2(cont2);
        nuevot.setCant3(cont3);
        nuevot.setCant4(cont4);
        nuevot.setGrado("Titulado");
        nuevototal.setCant1(nuevod.getCant1()+nuevom.getCant1()+nuevol.getCant1()+nuevot.getCant1());
        nuevototal.setCant2(nuevod.getCant2()+nuevom.getCant2()+nuevol.getCant2()+nuevot.getCant2());
        nuevototal.setCant3(nuevod.getCant3()+nuevom.getCant3()+nuevol.getCant3()+nuevot.getCant3());
        nuevototal.setCant4(nuevod.getCant4()+nuevom.getCant4()+nuevol.getCant4()+nuevot.getCant4());
        nuevototal.setGrado("Total");
        grado_cant_ano_4_vespertino.add(nuevod);
        grado_cant_ano_4_vespertino.add(nuevom);
        grado_cant_ano_4_vespertino.add(nuevol);
        grado_cant_ano_4_vespertino.add(nuevot);
        grado_cant_ano_4_vespertino.add(nuevototal);
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
