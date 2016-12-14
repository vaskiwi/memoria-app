package managedbeans;

import entities.Asignatura;
import entities.GradoAcademico;
import entities.Jerarquia;
import entities.Profesor;
import entities.ProfesorAsignatura;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.ProfesorFacadeLocal;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import sessionbeans.AsignaturaFacadeLocal;
import sessionbeans.GradoAcademicoFacade;
import sessionbeans.GradoAcademicoFacadeLocal;
import sessionbeans.JerarquiaFacade;
import sessionbeans.JerarquiaFacadeLocal;
import sessionbeans.ProfesorAsignaturaFacadeLocal;
import sessionbeans.RolFacadeLocal;

@Named("profesorController")
@SessionScoped
public class ProfesorController implements Serializable {

    @EJB
    private ProfesorFacadeLocal ejbFacade;
    @EJB
    private AsignaturaFacadeLocal asgFacade;
    
    @EJB
    private JerarquiaFacadeLocal jerarquiaFacade;
    
    @EJB
    private GradoAcademicoFacadeLocal gradoFacade;
    
    @EJB
    private ProfesorAsignaturaFacadeLocal profeAsigFacade;
    
    private List<Profesor> items = null;
    private List<Profesor> profeJerarGrado = null;
    private List<Asignatura> cursos = null;
    private List<Asignatura> cursos_totales = null;
    private Profesor selected;
    private Asignatura curso_add;
    private ProfesorAsignatura curso_del;
    private Jerarquia jerarq_actual;
    private GradoAcademico grado_actual;
    private Date fecha_actual;
    private List<Profesor> profe_jerarq = null;
    private List<Profesor> profe_grado = null;
    private List<renta> listR = null;
    private List<ContratoNumHora> listCNH = null;
    private List<edad> listEdad = null;
    private List<ProfesorAsignatura> profeAsig = null;
    private List<ProfesorAsignatura> profeAsig_profe = null;
    private ProfesorAsignaturaAno profeAsigAno = null;
    private List<ListaProfesor> lista_profesor_diurno = null;
    private List<ListaProfesor> lista_profesor_vespertino = null;
    @Inject
    private ProfesorAsignaturaController profeasigCtrl;
  
    private UploadedFile file;

    public List<Asignatura> getCursos() {
        return cursos;
    }

    public void setCursos(List<Asignatura> cursos) {
        this.cursos = cursos;
    }

    public List<ProfesorAsignatura> getProfeAsig_profe() {
        profeAsig_profe = profeAsigFacade.findByProfesorNoFecha(selected);
        return profeAsig_profe;
    }

    public void setProfeAsig_profe(List<ProfesorAsignatura> profeAsig_profe) {
        this.profeAsig_profe = profeAsig_profe;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Date getFecha_actual() {
        fecha_actual = new Date();
        return fecha_actual;
    }

    public void setFecha_actual(Date fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public ProfesorFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ProfesorFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public AsignaturaFacadeLocal getAsgFacade() {
        return asgFacade;
    }

    public void setAsgFacade(AsignaturaFacadeLocal asgFacade) {
        this.asgFacade = asgFacade;
    }

    public JerarquiaFacadeLocal getJerarquiaFacade() {
        return jerarquiaFacade;
    }

    public void setJerarquiaFacade(JerarquiaFacadeLocal jerarquiaFacade) {
        this.jerarquiaFacade = jerarquiaFacade;
    }

    public GradoAcademicoFacadeLocal getGradoFacade() {
        return gradoFacade;
    }

    public void setGradoFacade(GradoAcademicoFacadeLocal gradoFacade) {
        this.gradoFacade = gradoFacade;
    }

    public List<Profesor> getProfeJerarGrado() {
        return profeJerarGrado;
    }

    public void setProfeJerarGrado(List<Profesor> profeJerarGrado) {
        this.profeJerarGrado = profeJerarGrado;
    }

    public List<edad> getListEdad() {
        return listEdad;
    }

    public void setListEdad(List<edad> listEdad) {
        this.listEdad = listEdad;
    }

    public ProfesorAsignaturaController getProfeasigCtrl() {
        return profeasigCtrl;
    }

    public void setProfeasigCtrl(ProfesorAsignaturaController profeasigCtrl) {
        this.profeasigCtrl = profeasigCtrl;
    }

    public List<ProfesorAsignatura> getProfeAsig() {
        return profeAsig;
    }

    public void setProfeAsig(List<ProfesorAsignatura> profeAsig) {
        this.profeAsig = profeAsig;
    }

    public ProfesorAsignaturaFacadeLocal getProfeAsigFacade() {
        return profeAsigFacade;
    }

    public void setProfeAsigFacade(ProfesorAsignaturaFacadeLocal profeAsigFacade) {
        this.profeAsigFacade = profeAsigFacade;
    }

    public List<ListaProfesor> getLista_profesor_diurno() {
        lista_profesor_diurno= new ArrayList<>();
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
        items = ejbFacade.findByDiurnoVespertino("DIURNO");
        for (Profesor item : items) {
            profeAsig=profeAsigFacade.findByProfesorNoFecha(item);
            ListaProfesor datos_profesor= new ListaProfesor();
            List<String> lista_asignaturas_profesor = new ArrayList<>();
            List<String> lista_asignaturas_profesor_ano = new ArrayList<>();
            List<String> lista_asignaturas_profesor_ano2 = new ArrayList<>();
            List<String> lista_asignaturas_profesor_ano3 = new ArrayList<>();
            List<String> lista_asignaturas_profesor_ano4 = new ArrayList<>();
            List<String> lista_asignaturas_profesor_ano5 = new ArrayList<>();
            datos_profesor.setRut_profesor(item.getRut_profesor());
            datos_profesor.setNombre_profesor(item.getNombre_profesor());
            datos_profesor.setApellido_pat(item.getApellido_pat());
            datos_profesor.setApellido_mat(item.getApellido_mat());
            datos_profesor.setTitulo_profesor(item.getTitulo_profesor());
            datos_profesor.setGrado_academico_profesor(item.getGrado());
            datos_profesor.setAno_ingreso(item.getAno_ingreso());
            datos_profesor.setJerarquia_profesor(item.getJerarquia());
            datos_profesor.setDedicacion_contratada(item.getDedicacion_contratada());
            datos_profesor.setContrato(item.getContrato());
            datos_profesor.setUnidad_profesor(item.getUnidad_profesor());
            datos_profesor.setComuna_profesor(item.getComuna_profesor());
            for(ProfesorAsignatura profeA: profeAsig){
                if(lista_asignaturas_profesor.contains(profeA.getId_asignatura().getNombre_asignatura())==false){
                    lista_asignaturas_profesor.add(profeA.getId_asignatura().getNombre_asignatura());
                }
                if(profeA.getAno_profesor_asignatura()==year){
                    if(lista_asignaturas_profesor_ano.contains(profeA.getId_asignatura().getNombre_asignatura())==false){
                        lista_asignaturas_profesor_ano.add(profeA.getId_asignatura().getNombre_asignatura());
                    }
                }
                else if(profeA.getAno_profesor_asignatura()==year2){
                    if(lista_asignaturas_profesor_ano2.contains(profeA.getId_asignatura().getNombre_asignatura())==false){
                        lista_asignaturas_profesor_ano2.add(profeA.getId_asignatura().getNombre_asignatura());
                    }
                }
                else if(profeA.getAno_profesor_asignatura()==year3){
                    if(lista_asignaturas_profesor_ano3.contains(profeA.getId_asignatura().getNombre_asignatura())==false){
                        lista_asignaturas_profesor_ano3.add(profeA.getId_asignatura().getNombre_asignatura());
                    }
                }
                else if(profeA.getAno_profesor_asignatura()==year4){
                    if(lista_asignaturas_profesor_ano4.contains(profeA.getId_asignatura().getNombre_asignatura())==false){
                        lista_asignaturas_profesor_ano4.add(profeA.getId_asignatura().getNombre_asignatura());
                    }
                }
                else if(profeA.getAno_profesor_asignatura()==year5){
                    if(lista_asignaturas_profesor_ano5.contains(profeA.getId_asignatura().getNombre_asignatura())==false){
                        lista_asignaturas_profesor_ano5.add(profeA.getId_asignatura().getNombre_asignatura());
                    }
                }
            }
            datos_profesor.setAsignaturas_profesor(lista_asignaturas_profesor);
            datos_profesor.setAsignaturas_profesor_ano(lista_asignaturas_profesor_ano);
            datos_profesor.setAsignaturas_profesor_ano_2(lista_asignaturas_profesor_ano2);
            datos_profesor.setAsignaturas_profesor_ano_3(lista_asignaturas_profesor_ano3);
            datos_profesor.setAsignaturas_profesor_ano_4(lista_asignaturas_profesor_ano4);
            datos_profesor.setAsignaturas_profesor_ano_5(lista_asignaturas_profesor_ano5);
            lista_profesor_diurno.add(datos_profesor);
        }
        return lista_profesor_diurno;
    }
    
    public void setLista_profesor_diurno(List<ListaProfesor> lista_profesor_diurno) {
        this.lista_profesor_diurno = lista_profesor_diurno;
    }

    public List<ListaProfesor> getLista_profesor_vespertino() {
        lista_profesor_vespertino= new ArrayList<>();
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
        items = ejbFacade.findByDiurnoVespertino("VESPERTINO");
        for (Profesor item : items) {
            profeAsig=profeAsigFacade.findByProfesorNoFecha(item);
            ListaProfesor datos_profesor= new ListaProfesor();
            List<String> lista_asignaturas_profesor = new ArrayList<>();
            List<String> lista_asignaturas_profesor_ano = new ArrayList<>();
            List<String> lista_asignaturas_profesor_ano2 = new ArrayList<>();
            List<String> lista_asignaturas_profesor_ano3 = new ArrayList<>();
            List<String> lista_asignaturas_profesor_ano4 = new ArrayList<>();
            List<String> lista_asignaturas_profesor_ano5 = new ArrayList<>();
            datos_profesor.setRut_profesor(item.getRut_profesor());
            datos_profesor.setNombre_profesor(item.getNombre_profesor());
            datos_profesor.setApellido_pat(item.getApellido_pat());
            datos_profesor.setApellido_mat(item.getApellido_mat());
            datos_profesor.setTitulo_profesor(item.getTitulo_profesor());
            datos_profesor.setGrado_academico_profesor(item.getGrado());
            datos_profesor.setAno_ingreso(item.getAno_ingreso());
            datos_profesor.setJerarquia_profesor(item.getJerarquia());
            datos_profesor.setDedicacion_contratada(item.getDedicacion_contratada());
            datos_profesor.setContrato(item.getContrato());
            datos_profesor.setUnidad_profesor(item.getUnidad_profesor());
            datos_profesor.setComuna_profesor(item.getComuna_profesor());
            for(ProfesorAsignatura profeA: profeAsig){
                if(lista_asignaturas_profesor.contains(profeA.getId_asignatura().getNombre_asignatura())==false){
                    lista_asignaturas_profesor.add(profeA.getId_asignatura().getNombre_asignatura());
                }
                if(profeA.getAno_profesor_asignatura()==year){
                    if(lista_asignaturas_profesor_ano.contains(profeA.getId_asignatura().getNombre_asignatura())==false){
                        lista_asignaturas_profesor_ano.add(profeA.getId_asignatura().getNombre_asignatura());
                    }
                }
                else if(profeA.getAno_profesor_asignatura()==year2){
                    if(lista_asignaturas_profesor_ano2.contains(profeA.getId_asignatura().getNombre_asignatura())==false){
                        lista_asignaturas_profesor_ano2.add(profeA.getId_asignatura().getNombre_asignatura());
                    }
                }
                else if(profeA.getAno_profesor_asignatura()==year3){
                    if(lista_asignaturas_profesor_ano3.contains(profeA.getId_asignatura().getNombre_asignatura())==false){
                        lista_asignaturas_profesor_ano3.add(profeA.getId_asignatura().getNombre_asignatura());
                    }
                }
                else if(profeA.getAno_profesor_asignatura()==year4){
                    if(lista_asignaturas_profesor_ano4.contains(profeA.getId_asignatura().getNombre_asignatura())==false){
                        lista_asignaturas_profesor_ano4.add(profeA.getId_asignatura().getNombre_asignatura());
                    }
                }
                else if(profeA.getAno_profesor_asignatura()==year5){
                    if(lista_asignaturas_profesor_ano5.contains(profeA.getId_asignatura().getNombre_asignatura())==false){
                        lista_asignaturas_profesor_ano5.add(profeA.getId_asignatura().getNombre_asignatura());
                    }
                }
            }
            datos_profesor.setAsignaturas_profesor(lista_asignaturas_profesor);
            datos_profesor.setAsignaturas_profesor_ano(lista_asignaturas_profesor_ano);
            datos_profesor.setAsignaturas_profesor_ano_2(lista_asignaturas_profesor_ano2);
            datos_profesor.setAsignaturas_profesor_ano_3(lista_asignaturas_profesor_ano3);
            datos_profesor.setAsignaturas_profesor_ano_4(lista_asignaturas_profesor_ano4);
            datos_profesor.setAsignaturas_profesor_ano_5(lista_asignaturas_profesor_ano5);
            lista_profesor_vespertino.add(datos_profesor);
        }
        return lista_profesor_vespertino;
    }

    public void setLista_profesor_vespertino(List<ListaProfesor> lista_profesor_vespertino) {
        this.lista_profesor_vespertino = lista_profesor_vespertino;
    }   
    
    public ProfesorController() {
    }
    
    public void upload(FileUploadEvent event){
      FileReader fr = null;
      BufferedReader br = null;
 
      try {
         UploadedFile file= event.getFile();
         br = new BufferedReader(new InputStreamReader(event.getFile().getInputstream()));
         String linea;
         br.readLine();
         String[] texto=null;
       
         
         while((linea=br.readLine())!=null){
             texto = linea.split(";");
             Profesor profesorNuevo = new Profesor();
             if( ejbFacade.find(Long.parseLong(texto[0]))!=null )continue ;
             profesorNuevo.setRut_profesor(Long.parseLong(texto[0]) );
             profesorNuevo.setApellido_pat(texto[1]);
             profesorNuevo.setApellido_mat(texto[2]);
             profesorNuevo.setNombre_profesor(texto[3]);
             profesorNuevo.setJerarquia(jerarquiaFacade.findByNombre(texto[6]));
             Date añoIngreso = new SimpleDateFormat("dd/MM/yyyy").parse(texto[5]);
             profesorNuevo.setAno_ingreso(añoIngreso. getYear()+1900);
             profesorNuevo.setGrado(gradoFacade.findByNombre(texto[7]));
             profesorNuevo.setContrato(texto[8]);
             profesorNuevo.setVigente(true);
               if ("TITULAR".equals(profesorNuevo.getJerarquia().getNombre_jerarquia())) {
            profesorNuevo.setRenta(90);
            }
        else{    
            if ("ASOCIADO".equals(profesorNuevo.getJerarquia().getNombre_jerarquia())) {
                profesorNuevo.setRenta(80);
            }
            else{    
                if ("ASISTENTE".equals(profesorNuevo.getJerarquia().getNombre_jerarquia())) {
                    profesorNuevo.setRenta(70);
                }
                else{    
                    if ("INSTRUCTOR".equals(profesorNuevo.getJerarquia().getNombre_jerarquia())) {
                        profesorNuevo.setRenta(60);
                    }
                    else{    
                        if ("AYUDANTE".equals(profesorNuevo.getJerarquia().getNombre_jerarquia())) {
                            profesorNuevo.setRenta(50);
                        }   
                        else{    
                            if ("ADJUNTO CATEGORÍA I".equals(profesorNuevo.getJerarquia().getNombre_jerarquia())) {
                                profesorNuevo.setRenta(40);
                            }
                            else{    
                                if ("ADJUNTO CATEGORÍA II".equals(profesorNuevo.getJerarquia().getNombre_jerarquia())) {
                                    profesorNuevo.setRenta(40);
                                } 
                                else{    
                                    if ("INSTRUCTOR CATEGORÍA I".equals(profesorNuevo.getJerarquia().getNombre_jerarquia())) {
                                        profesorNuevo.setRenta(30);
                                    }
                                    else{    
                                        if ("INSTRUCTOR CATEGORÍA II".equals(profesorNuevo.getJerarquia().getNombre_jerarquia())) {
                                        profesorNuevo.setRenta(20);
                                        }
                                        else{    
                                            if ("AYUDANTE PROFESOR".equals(profesorNuevo.getJerarquia().getNombre_jerarquia())) {
                                                profesorNuevo.setRenta(10);
                                            }    
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
             ejbFacade.create(profesorNuevo);
             
             
             
         }
         FacesMessage message = new FacesMessage("Succesful", "Profesores importados correctamente ");
          FacesContext.getCurrentInstance().addMessage(null, message);
      }
      catch(Exception e){
         
         FacesMessage message = new FacesMessage("Succesful", "Profesores importados correctamente ");
          FacesContext.getCurrentInstance().addMessage(null, message);
             e.printStackTrace();
      }
        
    }

    public List<renta> getListR() {
        listR = new ArrayList<>();
        Date fecha = new Date();
        int year;
        int year2;
        int year3;
        year = fecha.getYear() + 1900;
        year2 = year - 1;
        year3 = year - 2;
        renta nuevo = new renta();
        renta nuevo2 = new renta();
        nuevo.nombre = "Promedio de renta para jornada completa (UF)";
        nuevo2.nombre = "Promedio del valor hora contrato (UF)";
        float renta1 = 0;
        float renta2 = 0;
        float renta3 = 0;
        int cont = 0;
        double horaContrato;
        double horaContrato2;
        double horaContrato3;
        
        List <Profesor> pyear1aux1 = getFacade().findByAno(year3);
        List <Profesor> pyear1aux2 = getFacade().findByAnoRetiro(year3);
        List <Profesor> pyear1 = new ArrayList<Profesor>();
        pyear1.addAll(pyear1aux1);
        pyear1.addAll(pyear1aux2);
        List <Profesor> pyear2aux1 = getFacade().findByAno(year2);
        List <Profesor> pyear2aux2 = getFacade().findByAnoRetiro(year2);
        List <Profesor> pyear2 = new ArrayList<Profesor>();
        pyear2.addAll(pyear2aux1);
        pyear2.addAll(pyear2aux2);
        List <Profesor> pyear3aux1 = getFacade().findByAno(year);
        List <Profesor> pyear3aux2 = getFacade().findByAnoRetiro(year);
        List <Profesor> pyear3 = new ArrayList<Profesor>();
        pyear3.addAll(pyear3aux1);
        pyear3.addAll(pyear3aux2);
        
        for (int i = 0; i < pyear1.size(); i++) {
            if ("Completa".equals(pyear1.get(i).getContrato())) {
                renta1+= pyear1.get(i).getRenta();
                cont+= 1;
            }

        }
        renta1 = renta1 / cont;
        cont = 0;
        for (int i = 0; i < pyear2.size(); i++) {
            if ("Completa".equals(pyear2.get(i).getContrato())) {
                renta2+= pyear2.get(i).getRenta();
                cont+= 1;
            }
            
        }
        renta2 = renta2 / cont;
        cont = 0;
        for (int i = 0; i < pyear3.size(); i++) {
            if ("Completa".equals(pyear3.get(i).getContrato())) {
                renta3+= pyear3.get(i).getRenta();
                cont+= 1;
            }
        
        }
        renta3 = renta3 / cont;
        cont = 0;
        
        horaContrato = renta1/44;
        horaContrato2 = renta2/44;
        horaContrato3 = renta3/44;
        nuevo.renta1 = Math.rint(renta1*100)/100;
        nuevo.renta2 = Math.rint(renta2*100)/100;
        nuevo.renta3 = Math.rint(renta3*100)/100;
        nuevo2.renta1 = Math.rint(horaContrato*100)/100;
        nuevo2.renta2 = Math.rint(horaContrato2*100)/100;
        nuevo2.renta3 = Math.rint(horaContrato3*100)/100;
        listR.add(nuevo);
        listR.add(nuevo2);
        return listR;
    }
    
    /*************************LISTO***********************
     * 
     *****************************************************/
    public List<ContratoNumHora> getListCNH(){
        listCNH = new ArrayList<>();
        Date fecha = new Date();
        int year;
        int year2;
        int year3;
        year = fecha.getYear() + 1900;
        year2 = year - 1;
        year3 = year - 2;
        int totald = 0;
        int totalc1 = 0; int totalc2 = 0; int totalc3 = 0;
        int totalp1 = 0; int totalp2 = 0; int totalp3 = 0;
        int totalh1 = 0; int totalh2 = 0; int totalh3 = 0;
        float horasc1 = 0; float horasc2 = 0; float horasc3 = 0;
        float horasp1 = 0; float horasp2 = 0; float horasp3 = 0;
        float horash1 = 0; float horash2 = 0; float horash3 = 0;
        float totalhoras = 0;
        
        List<Profesor> total = getFacade().findAll();
        for (int i = 0; i < total.size() ; i++) {
            
            if("Completa".equals(total.get(i).getContrato())){
                    if((total.get(i).getAno_ingreso() <= year3) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro()>= year3)){
                        totalc1+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year2) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year2)){
                        totalc2+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year)) {
                        totalc3+= 1;

                        }
                    }
            if("Parcial".equals(total.get(i).getContrato())){
                    if((total.get(i).getAno_ingreso() <= year3) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year3)){
                        totalp1+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year2) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year2)){
                        totalp2+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year)){
                        totalp3+= 1;

                        }

                    }
            
            if("Por Hora".equals(total.get(i).getContrato())){
                    if((total.get(i).getAno_ingreso() <= year3) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year3)){
                       totalh1+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year2) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year2)){
                        totalh2+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year)){
                        totalh3+= 1;

                        }

                    }
            /*
            for (int j = 0; j < total.get(i).getAsignaturaList().size(); j++) {
                
                if("Completa".equals(total.get(i).getContrato())){
                    if((total.get(i).getAno_ingreso() <= year3) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro()>= year3)){
                        horasc1 += total.get(i).getAsignaturaList().get(j).getCant_horas_presenciales();
                        }
                    if((total.get(i).getAno_ingreso() <= year2) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year2)){
                        horasc2+=total.get(i).getAsignaturaList().get(j).getCant_horas_presenciales();
                        }
                    if((total.get(i).getAno_ingreso() <= year) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year)) {
                        horasc3+=total.get(i).getAsignaturaList().get(j).getCant_horas_presenciales();

                        }
                    }
                
            
                if("Parcial".equals(total.get(i).getContrato())){
                    if((total.get(i).getAno_ingreso() <= year3) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year3)){
                        horasp1 = total.get(i).getAsignaturaList().get(j).getCant_horas_presenciales();
                        }
                    if((total.get(i).getAno_ingreso() <= year2) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year2)){
                        horasp2+=total.get(i).getAsignaturaList().get(j).getCant_horas_presenciales();
                        }
                    if((total.get(i).getAno_ingreso() <= year) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year)){
                        horasp3+=total.get(i).getAsignaturaList().get(j).getCant_horas_presenciales();
                        }

                    }
                
        
                if("Por Hora".equals(total.get(i).getContrato())){
                    if((total.get(i).getAno_ingreso() <= year3) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year3)){
                        horash1 += total.get(i).getAsignaturaList().get(j).getCant_horas_presenciales();
                        totalh1+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year2) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year2)){
                        horash2+=total.get(i).getAsignaturaList().get(j).getCant_horas_presenciales();
                        totalh2+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year)){
                        horash3+=total.get(i).getAsignaturaList().get(j).getCant_horas_presenciales();
                        totalh3+= 1;

                        }

                    }
                }*/
        
                
            }
        
        int totald1 = totalc1 + totalp1 + totalh1;
        int totald2 = totalc2 + totalp2 + totalh2;
        int totald3 = totalc3 + totalp3 + totalh3;
        float totalhoras1 = horasc1 + horasp1 + horash1;
        float totalhoras2 = horasc2 + horasp2 + horash2;
        float totalhoras3 = horasc3 + horasp3 + horash3; 
        
        ContratoNumHora nuevo1 = new ContratoNumHora();
        ContratoNumHora nuevo2 = new ContratoNumHora();
        ContratoNumHora nuevo3 = new ContratoNumHora();
        ContratoNumHora nuevo4 = new ContratoNumHora();
        ContratoNumHora nuevo5 = new ContratoNumHora();
        ContratoNumHora nuevo6 = new ContratoNumHora();
        nuevo1.nombre = "Número de docentes Jornada Completa";
        nuevo2.nombre = "Horas de docentes Jornada Completa";
        nuevo3.nombre = "Número de docentes Jornada Parcial";
        nuevo4.nombre = "Horas de docentes Jornada Parcial";
        nuevo5.nombre = "Número de docentes Jornada Por Hora";
        nuevo6.nombre = "Horas de docentes contratados Por Hora";
        
        nuevo1.horast1 = totalc1;
        nuevo1.horast2 = totalc2;
        nuevo1.horast3 = totalc3;
        nuevo2.horast1 = horasc1;
        nuevo2.horast2 = horasc2;
        nuevo2.horast3 = horasc3;
        nuevo3.horast1 = totalp1;
        nuevo3.horast2 = totalp2;
        nuevo3.horast3 = totalp3;
        nuevo4.horast1 = horasp1;
        nuevo4.horast2 = horasp2;
        nuevo4.horast3 = horasp3;
        nuevo5.horast1 = totalh1;
        nuevo5.horast2 = totalh2;
        nuevo5.horast3 = totalh3;
        nuevo6.horast1 = horash1;
        nuevo6.horast2 = horash2;
        nuevo6.horast3 = horash3;
        
        listCNH.add(nuevo1);
        listCNH.add(nuevo2);
        listCNH.add(nuevo3);
        listCNH.add(nuevo4);
        listCNH.add(nuevo5);
        listCNH.add(nuevo6);
        
        ContratoNumHora docentes = new ContratoNumHora();
        ContratoNumHora horas = new ContratoNumHora();
        
        docentes.nombre = "TOTAL DOCENTES";
        docentes.horast1 = totald1;
        docentes.horast2 = totald2;
        docentes.horast3 = totald3;
        
        horas.nombre = "TOTAL HORAS";
        horas.horast1 = totalhoras1;
        horas.horast2 = totalhoras2;
        horas.horast3 = totalhoras3;
        
        
        listCNH.add(docentes);
        listCNH.add(horas);
        totalhoras = 0;
        
        
        
        return listCNH;
    }
    
    
    public void desvincular(){
        selected.setVigente(false);
        Date fecha = new Date();
        int ano = fecha.getYear() +1900;
        selected.setAnoRetiro(ano);
        ejbFacade.edit(selected);
    }
    
    
    public Profesor getSelected() {
        return selected;
    }

    public Jerarquia getJerarq_actual() {
        return jerarq_actual;
    }

    public void setJerarq_actual(Jerarquia jerarq_actual) {
        this.jerarq_actual = jerarq_actual;
    }

    public List<Profesor> getProfe_jerarq() {
        profe_jerarq = ejbFacade.findByJerarquia(jerarq_actual);
        return profe_jerarq;
    }

    public GradoAcademico getGrado_actual() {
        return grado_actual;
    }

    public void setGrado_actual(GradoAcademico grado_actual) {
        this.grado_actual = grado_actual;
    }

    public List<Profesor> getProfe_grado() {
        return profe_grado;
    }

    public void setProfe_grado(List<Profesor> profe_grado) {
        this.profe_grado = profe_grado;
    }

    public void setProfe_jerarq(List<Profesor> profe_jerarq) {
        this.profe_jerarq = profe_jerarq;
    }

    public void setSelected(Profesor selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProfesorFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Asignatura getCurso_add() {
        return curso_add;
    }

    public void setCurso_add(Asignatura curso_add) {
        this.curso_add = curso_add;
    }

    public ProfesorAsignatura getCurso_del() {
        return curso_del;
    }

    public void setCurso_del(ProfesorAsignatura curso_del) {
        this.curso_del = curso_del;
    }
    
    public List<Asignatura> getCursos_totales() {
        cursos_totales = asgFacade.findAll();
        return cursos_totales;
    }

    public void setCursos_totales(List<Asignatura> cursos_totales) {
        this.cursos_totales = cursos_totales;
    }
    
    public void addAsignatura(){
        Date fecha = new Date();
        int year;
        year = fecha.getYear() + 1900;
        float horas_profesor;
        float horas_asignatura;
        float horas_total;
        if(selected.getContrato().equals("Completa")==false){
            horas_profesor=selected.getDedicacion_contratada();
            horas_asignatura=curso_add.getCant_horas_presenciales()/5;
            horas_total=horas_profesor+horas_asignatura;
            selected.setDedicacion_contratada(horas_total);
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorUpdated"));
        }
        profeasigCtrl.prepareCreate();
        profeasigCtrl.getSelected().setRut_profesor(selected);
        profeasigCtrl.getSelected().setId_asignatura(curso_add);
        profeasigCtrl.getSelected().setAno_profesor_asignatura(year);
        profeasigCtrl.create();
    }
    
    public void delAsignatura(){
        float horas_profesor;
        float horas_asignatura;
        float horas_total;
        if(selected.getContrato().equals("Completa")==false){
            horas_profesor=selected.getDedicacion_contratada();
            horas_asignatura=curso_del.getId_asignatura().getCant_horas_presenciales()/5;
            horas_total=horas_profesor-horas_asignatura;
            selected.setDedicacion_contratada(horas_total);
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorUpdated"));
        }
        profeasigCtrl.setSelected(curso_del);
        profeasigCtrl.destroy();
    }
    
    public void editProfeAsignatura(){
        profeasigCtrl.setSelected(curso_del);
        profeasigCtrl.update();
    }

    public int calcularHorasJerarquia(){
        int horas = 0;
        /*
        for (int i = 0; i < profe_jerarq.size(); i++) {
            for (int j = 0; j < profe_jerarq.get(i).getAsignaturaList().size(); j++) {
                horas = (int) (horas + profe_jerarq.get(i).getAsignaturaList().get(j).getCant_horas_presenciales());
            }
        }
        */
        return horas;
    }
    
    
    
    public int calcularHorasGrado(){
        int horas = 0;
        /*
        for (int i = 0; i < profe_grado.size(); i++) {
            for (int j = 0; j < profe_grado.get(i).getAsignaturaList().size(); j++) {
                horas = (int) (horas + profe_grado.get(i).getAsignaturaList().get(j).getCant_horas_presenciales());
            }
        }
        */
        return horas;
    }
    
    public Profesor prepareCreate() {
        selected = new Profesor();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if ("Completa".equals(selected.getContrato())) {
            selected.setDedicacion_contratada(45);
        }
        else{    
            selected.setDedicacion_contratada(0);
        }
        selected.setVigente(true);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProfesorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public ProfesorAsignaturaAno getProfeAsigAno() {
        List<String> ano1 = new ArrayList<>();
        List<String> ano2 = new ArrayList<>();
        List<String> ano3 = new ArrayList<>();
        List<String> ano4 = new ArrayList<>();
        List<String> ano5 = new ArrayList<>();
        List<String> ano = new ArrayList<>();
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
        profeAsig=profeAsigFacade.findByProfesor(selected, year);
        System.out.println(profeAsig);
        for(int i=0;i<profeAsig.size();i++){
            ano.add(profeAsig.get(i).getId_asignatura().getNombre_asignatura());
            ano1.add(profeAsig.get(i).getId_asignatura().getNombre_asignatura());
        }
        System.out.println(profeAsigAno);
        profeAsig=profeAsigFacade.findByProfesor(selected, year2);
        System.out.println(profeAsig);
        for(int i=0;i<profeAsig.size();i++){
            ano.add(profeAsig.get(i).getId_asignatura().getNombre_asignatura());
            ano2.add(profeAsig.get(i).getId_asignatura().getNombre_asignatura());
        }
        profeAsig=profeAsigFacade.findByProfesor(selected, year3);
        for(int i=0;i<profeAsig.size();i++){
            ano.add(profeAsig.get(i).getId_asignatura().getNombre_asignatura());
            ano3.add(profeAsig.get(i).getId_asignatura().getNombre_asignatura());
        }
        profeAsig=profeAsigFacade.findByProfesor(selected, year4);
        for(int i=0;i<profeAsig.size();i++){
            ano.add(profeAsig.get(i).getId_asignatura().getNombre_asignatura());
            ano4.add(profeAsig.get(i).getId_asignatura().getNombre_asignatura());
        }
        profeAsig=profeAsigFacade.findByProfesor(selected, year5);
        for(int i=0;i<profeAsig.size();i++){
            ano.add(profeAsig.get(i).getId_asignatura().getNombre_asignatura());
            ano5.add(profeAsig.get(i).getId_asignatura().getNombre_asignatura());
        }
        System.out.println(profeAsigAno);
        return profeAsigAno;
    }

    public void setProfeAsigAno(ProfesorAsignaturaAno profeAsigAno) {
        this.profeAsigAno = profeAsigAno;
    }
    
    public List<Profesor> getItems() {
        
            items = getFacade().findAll();
        
        return items;
    }

    public List<Profesor> getProfeJerarGrado(Jerarquia j , GradoAcademico g) {
        profeJerarGrado = getFacade().findByJerarYGrado(j, g);
        return profeJerarGrado;
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

    public Profesor getProfesor(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Profesor> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Profesor> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Profesor.class)
    public static class ProfesorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProfesorController controller = (ProfesorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "profesorController");
            return controller.getProfesor(getKey(value));
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
            if (object instanceof Profesor) {
                Profesor o = (Profesor) object;
                return getStringKey(o.getRut_profesor());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Profesor.class.getName()});
                return null;
            }
        }

    }

}
