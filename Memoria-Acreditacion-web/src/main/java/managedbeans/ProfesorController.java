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
import sessionbeans.ProfesorFacadeLocal;

import java.io.Serializable;
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
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import sessionbeans.AsignaturaFacadeLocal;
import sessionbeans.GradoAcademicoFacadeLocal;
import sessionbeans.JerarquiaFacadeLocal;
import sessionbeans.ProfesorAsignaturaFacadeLocal;
import sessionbeans.ProfesorContratoFacadeLocal;
import sessionbeans.ProfesorGradoFacadeLocal;
import sessionbeans.ProfesorJerarquiaFacadeLocal;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import sessionbeans.CarreraFacadeLocal;

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
    
    @EJB
    private ProfesorContratoFacadeLocal profeContFacade;
    
    @EJB
    private ProfesorGradoFacadeLocal profeGradoFacade;
    
    @EJB
    private ProfesorJerarquiaFacadeLocal profeJerarFacade;
    
    @EJB
    private CarreraFacadeLocal carreraFacade;
    
    @EJB
    private AsignaturaFacadeLocal asigFacade;
    
    private List<Profesor> items = null;
    private List<Profesor> itemsSortRut = null;
    private List<Profesor> itemsSortNombre = null;
    private List<Profesor> itemsSortApellidoMaterno = null;
    private List<Profesor> itemsSortTitulo = null;
    private List<Profesor> itemsSortUnidad = null;
    private List<Profesor> itemsSortComuna = null;
    private List<Profesor> itemsSortAnoIngreso = null;
    private List<Profesor> itemsSortAnoRetiro = null;
    private List<Profesor> itemsSortVigente = null;
    private List<Profesor> itemsSortInfo = null;
    private List<Profesor> profeJerarGrado = null;
    private List<Asignatura> cursos = null;
    private List<Asignatura> cursos_totales = null;
    private Profesor selected;
    private Asignatura curso_add;
    private ProfesorAsignatura curso_del;
    private GradoAcademico grado_add;
    private ProfesorGrado grado_del;
    private Jerarquia jerarquia_add;
    private ProfesorJerarquia jerarquia_del;
    private Contrato contrato_add;
    private ProfesorContrato contrato_del;
    private Jerarquia jerarq_actual;
    private GradoAcademico grado_actual;
    private Date fecha_actual;
    private List<Profesor> profe_jerarq = null;
    private List<Profesor> profe_grado = null;
    private List<ProfesorAsignatura> profeAsig = null;
    private List<ProfesorAsignatura> profeAsig_profe = null;
    private List<ProfesorContrato> profeCont_profe = null;
    private List<ProfesorJerarquia> profeJerar_profe = null;
    private List<ProfesorGrado> profeGrado_profe = null;
    private ProfesorAsignaturaAno profeAsigAno = null;
    private List<ListaProfesor> lista_profesor_civil_diurno = null;
    private List<ListaProfesor> lista_profesor_civil_vespertino = null;
    private List<ListaProfesor> lista_profesor_ejecu_diurno = null;
    private List<ListaProfesor> lista_profesor_ejecu_vespertino = null;
    private int ano_asig = 0;
    private Carrera carrera_profesor;
    private Contrato contrato_actual;
    @Inject
    private ProfesorAsignaturaController profeasigCtrl;
    @Inject
    private ProfesorGradoController profegradoCtrl;
    @Inject
    private ProfesorJerarquiaController profejerarCtrl;
    @Inject
    private ProfesorContratoController profecontCtrl;
  
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

    public List<ProfesorContrato> getProfeCont_profe() {
        profeCont_profe = profeContFacade.findByProfesorNoFecha(selected);
        return profeCont_profe;
    }

    public void setProfeCont_profe(List<ProfesorContrato> profeCont_profe) {
        this.profeCont_profe = profeCont_profe;
    }

    public List<ProfesorJerarquia> getProfeJerar_profe() {
        profeJerar_profe = profeJerarFacade.findByProfesorNoFecha(selected);
        return profeJerar_profe;
    }

    public void setProfeJerar_profe(List<ProfesorJerarquia> profeJerar_profe) {
        this.profeJerar_profe = profeJerar_profe;
    }

    public List<ProfesorGrado> getProfeGrado_profe() {
        profeGrado_profe = profeGradoFacade.findByProfesorNoFecha(selected);
        return profeGrado_profe;
    }

    public void setProfeGrado_profe(List<ProfesorGrado> profeGrado_profe) {
        this.profeGrado_profe = profeGrado_profe;
    }
    
    

    public int getAño_asig() {
        return ano_asig;
    }

    public void setAño_asig(int año_asig) {
        this.ano_asig = año_asig;
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

    public GradoAcademico getGrado_add() {
        return grado_add;
    }

    public void setGrado_add(GradoAcademico grado_add) {
        this.grado_add = grado_add;
    }

    public ProfesorGrado getGrado_del() {
        return grado_del;
    }

    public void setGrado_del(ProfesorGrado grado_del) {
        this.grado_del = grado_del;
    }

    public Jerarquia getJerarquia_add() {
        return jerarquia_add;
    }

    public void setJerarquia_add(Jerarquia jerarquia_add) {
        this.jerarquia_add = jerarquia_add;
    }

    public ProfesorJerarquia getJerarquia_del() {
        return jerarquia_del;
    }

    public void setJerarquia_del(ProfesorJerarquia jerarquia_del) {
        this.jerarquia_del = jerarquia_del;
    }

    public Contrato getContrato_add() {
        return contrato_add;
    }

    public void setContrato_add(Contrato contrato_add) {
        this.contrato_add = contrato_add;
    }

    public ProfesorContrato getContrato_del() {
        return contrato_del;
    }

    public void setContrato_del(ProfesorContrato contrato_del) {
        this.contrato_del = contrato_del;
    }

    public int getAno_asig() {
        return ano_asig;
    }

    public void setAno_asig(int ano_asig) {
        this.ano_asig = ano_asig;
    }

    public ProfesorGradoController getProfegradoCtrl() {
        return profegradoCtrl;
    }

    public void setProfegradoCtrl(ProfesorGradoController profegradoCtrl) {
        this.profegradoCtrl = profegradoCtrl;
    }

    public ProfesorJerarquiaController getProfejerarCtrl() {
        return profejerarCtrl;
    }

    public void setProfejerarCtrl(ProfesorJerarquiaController profejerarCtrl) {
        this.profejerarCtrl = profejerarCtrl;
    }

    public ProfesorContratoController getProfecontCtrl() {
        return profecontCtrl;
    }

    public void setProfecontCtrl(ProfesorContratoController profecontCtrl) {
        this.profecontCtrl = profecontCtrl;
    }
    
    public void upload(FileUploadEvent event) throws IOException{
      HSSFWorkbook workbook = new HSSFWorkbook(event.getFile().getInputstream());
        int contador;
	HSSFSheet sheet = workbook.getSheetAt(0);
	Iterator<Row> rowIterator = sheet.iterator();
	Row row;
	while (rowIterator.hasNext()){
	    row = rowIterator.next();
	    Iterator<Cell> cellIterator = row.cellIterator();
	    Cell celda;
            contador=1;
	    while (cellIterator.hasNext()){
		celda = cellIterator.next();
                if(contador==1){
                    cursos=asgFacade.findByCodigo((int) celda.getNumericCellValue());
                    for(Asignatura curso : cursos){
                        curso_add=curso;
                    }
                }
                else if(contador==5){
                    ano_asig=(int) celda.getNumericCellValue();
                    profeasigCtrl.prepareCreate();
                    profeasigCtrl.getSelected().setRut_profesor(selected);
                    profeasigCtrl.getSelected().setId_asignatura(curso_add);
                    profeasigCtrl.getSelected().setAno_profesor_asignatura(ano_asig);
                    profeasigCtrl.create();
                }
                contador++;
	    }
	}
	workbook.close();  
    }

    public List<ListaProfesor> getLista_profesor_civil_diurno() {
        lista_profesor_civil_diurno= new ArrayList<>();
        Date fecha = new Date();
        float horas;
        List<Asignatura> asignaturas;
        List<ProfesorAsignatura> profesores_seleccionados;
        List<ProfesorGrado> profesor_grado;
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
        List<Profesor> lista_profesores_civil_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignaturaNoFecha(asignatura);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if((lista_profesores_civil_diurno.contains(profesor_seleccionado.getRut_profesor())==false)&&(profesor_seleccionado.getRut_profesor().isInformacion_completa_profesor())){
                    lista_profesores_civil_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Profesor item : lista_profesores_civil_diurno) {
            horas=0;
            profeAsig=profeAsigFacade.findByProfesorNoFecha(item);
            profesor_grado=profeGradoFacade.findByProfesor(item, year);
            for(ProfesorGrado profe_gra: profesor_grado){
                grado_actual=profe_gra.getId_grado_academico();
            }
            profeJerar_profe=profeJerarFacade.findByProfesor(item,year);
            for(ProfesorJerarquia profe_jerar: profeJerar_profe){
                jerarq_actual=profe_jerar.getId_jerarquia();
            }
            profeCont_profe=profeContFacade.findByProfesor(item,year);
            for(ProfesorContrato profe_contr: profeCont_profe){
                contrato_actual=profe_contr.getId_contrato();
            }
            if(contrato_actual!=null){
                if(contrato_actual.isDependencia_asignaturas_contrato()){
                    for(ProfesorAsignatura profeA: profeAsig){
                        horas+=profeA.getId_asignatura().getCant_horas_presenciales();
                    }
                }
                else{
                    horas=contrato_actual.getHoras_contrato();
                }
            }
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
            datos_profesor.setGrado_academico_profesor(grado_actual);
            datos_profesor.setAno_ingreso(item.getAno_ingreso());
            datos_profesor.setJerarquia_profesor(jerarq_actual);
            datos_profesor.setDedicacion_contratada(horas);
            datos_profesor.setContrato(contrato_actual);
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
            lista_profesor_civil_diurno.add(datos_profesor);
        }
        return lista_profesor_civil_diurno;
    }

    public void setLista_profesor_civil_diurno(List<ListaProfesor> lista_profesor_civil_diurno) {
        this.lista_profesor_civil_diurno = lista_profesor_civil_diurno;
    }

    public List<ListaProfesor> getLista_profesor_civil_vespertino() {
        lista_profesor_civil_vespertino= new ArrayList<>();
        Date fecha = new Date();
        float horas;
        List<Asignatura> asignaturas;
        List<ProfesorAsignatura> profesores_seleccionados;
        List<ProfesorGrado> profesor_grado;
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
        List<Profesor> lista_profesores_civil_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería Civil Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignaturaNoFecha(asignatura);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if((lista_profesores_civil_vespertino.contains(profesor_seleccionado.getRut_profesor())==false)&&(profesor_seleccionado.getRut_profesor().isInformacion_completa_profesor())){
                    lista_profesores_civil_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Profesor item : lista_profesores_civil_vespertino) {
            horas=0;
            profeAsig=profeAsigFacade.findByProfesorNoFecha(item);
            profesor_grado=profeGradoFacade.findByProfesor(item, year);
            for(ProfesorGrado profe_gra: profesor_grado){
                grado_actual=profe_gra.getId_grado_academico();
            }
            profeJerar_profe=profeJerarFacade.findByProfesor(item,year);
            for(ProfesorJerarquia profe_jerar: profeJerar_profe){
                jerarq_actual=profe_jerar.getId_jerarquia();
            }
            profeCont_profe=profeContFacade.findByProfesor(item,year);
            for(ProfesorContrato profe_contr: profeCont_profe){
                contrato_actual=profe_contr.getId_contrato();
            }
            if(contrato_actual!=null){
                if(contrato_actual.isDependencia_asignaturas_contrato()){
                    for(ProfesorAsignatura profeA: profeAsig){
                        horas+=profeA.getId_asignatura().getCant_horas_presenciales();
                    }
                }
                else{
                    horas=contrato_actual.getHoras_contrato();
                }
            }
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
            datos_profesor.setGrado_academico_profesor(grado_actual);
            datos_profesor.setAno_ingreso(item.getAno_ingreso());
            datos_profesor.setJerarquia_profesor(jerarq_actual);
            datos_profesor.setDedicacion_contratada(horas);
            datos_profesor.setContrato(contrato_actual);
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
            lista_profesor_civil_vespertino.add(datos_profesor);
        }
        return lista_profesor_civil_vespertino;
    }

    public void setLista_profesor_civil_vespertino(List<ListaProfesor> lista_profesor_civil_vespertino) {
        this.lista_profesor_civil_vespertino = lista_profesor_civil_vespertino;
    }

    public List<ListaProfesor> getLista_profesor_ejecu_diurno() {
        lista_profesor_ejecu_diurno= new ArrayList<>();
        Date fecha = new Date();
        float horas;
        List<Asignatura> asignaturas;
        List<ProfesorAsignatura> profesores_seleccionados;
        List<ProfesorGrado> profesor_grado;
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
        List<Profesor> lista_profesores_ejecu_diurno= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "DIURNO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignaturaNoFecha(asignatura);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if((lista_profesores_ejecu_diurno.contains(profesor_seleccionado.getRut_profesor())==false)&&(profesor_seleccionado.getRut_profesor().isInformacion_completa_profesor())){
                    lista_profesores_ejecu_diurno.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Profesor item : lista_profesores_ejecu_diurno) {
            horas=0;
            profeAsig=profeAsigFacade.findByProfesorNoFecha(item);
            profesor_grado=profeGradoFacade.findByProfesor(item, year);
            for(ProfesorGrado profe_gra: profesor_grado){
                grado_actual=profe_gra.getId_grado_academico();
            }
            profeJerar_profe=profeJerarFacade.findByProfesor(item,year);
            for(ProfesorJerarquia profe_jerar: profeJerar_profe){
                jerarq_actual=profe_jerar.getId_jerarquia();
            }
            profeCont_profe=profeContFacade.findByProfesor(item,year);
            for(ProfesorContrato profe_contr: profeCont_profe){
                contrato_actual=profe_contr.getId_contrato();
            }
            if(contrato_actual!=null){
                if(contrato_actual.isDependencia_asignaturas_contrato()){
                    for(ProfesorAsignatura profeA: profeAsig){
                        horas+=profeA.getId_asignatura().getCant_horas_presenciales();
                    }
                }
                else{
                    horas=contrato_actual.getHoras_contrato();
                }
            }
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
            datos_profesor.setGrado_academico_profesor(grado_actual);
            datos_profesor.setAno_ingreso(item.getAno_ingreso());
            datos_profesor.setJerarquia_profesor(jerarq_actual);
            datos_profesor.setDedicacion_contratada(horas);
            datos_profesor.setContrato(contrato_actual);
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
            lista_profesor_ejecu_diurno.add(datos_profesor);
        }
        return lista_profesor_ejecu_diurno;
    }

    public void setLista_profesor_ejecu_diurno(List<ListaProfesor> lista_profesor_ejecu_diurno) {
        this.lista_profesor_ejecu_diurno = lista_profesor_ejecu_diurno;
    }

    public List<ListaProfesor> getLista_profesor_ejecu_vespertino() {
        lista_profesor_ejecu_vespertino= new ArrayList<>();
        Date fecha = new Date();
        float horas;
        List<Asignatura> asignaturas;
        List<ProfesorAsignatura> profesores_seleccionados;
        List<ProfesorGrado> profesor_grado;
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
        List<Profesor> lista_profesores_ejecu_vespertino= new ArrayList<>();
        List<Carrera>carreras = carreraFacade.findByNombrelist("Ingeniería de Ejecución en Computación e Informática");
        for(Carrera carrera : carreras){
            carrera_profesor=carrera;
        }
        asignaturas=asigFacade.findByCarrerayJornada(carrera_profesor, "VESPERTINO");
        for(Asignatura asignatura: asignaturas){
            profesores_seleccionados=profeAsigFacade.findByAsignaturaNoFecha(asignatura);
            for(ProfesorAsignatura profesor_seleccionado: profesores_seleccionados){
                if((lista_profesores_ejecu_vespertino.contains(profesor_seleccionado.getRut_profesor())==false)&&(profesor_seleccionado.getRut_profesor().isInformacion_completa_profesor())){
                    lista_profesores_ejecu_vespertino.add(profesor_seleccionado.getRut_profesor());
                }          
            }
        }
        for (Profesor item : lista_profesores_ejecu_vespertino) {
            horas=0;
            profeAsig=profeAsigFacade.findByProfesorNoFecha(item);
            profesor_grado=profeGradoFacade.findByProfesor(item, year);
            for(ProfesorGrado profe_gra: profesor_grado){
                grado_actual=profe_gra.getId_grado_academico();
            }
            profeJerar_profe=profeJerarFacade.findByProfesor(item,year);
            for(ProfesorJerarquia profe_jerar: profeJerar_profe){
                jerarq_actual=profe_jerar.getId_jerarquia();
            }
            profeCont_profe=profeContFacade.findByProfesor(item,year);
            for(ProfesorContrato profe_contr: profeCont_profe){
                contrato_actual=profe_contr.getId_contrato();
            }
            if(contrato_actual!=null){
                if(contrato_actual.isDependencia_asignaturas_contrato()){
                    for(ProfesorAsignatura profeA: profeAsig){
                        horas+=profeA.getId_asignatura().getCant_horas_presenciales();
                    }
                }
                else{
                    horas=contrato_actual.getHoras_contrato();
                }
            }
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
            datos_profesor.setGrado_academico_profesor(grado_actual);
            datos_profesor.setAno_ingreso(item.getAno_ingreso());
            datos_profesor.setJerarquia_profesor(jerarq_actual);
            datos_profesor.setDedicacion_contratada(horas);
            datos_profesor.setContrato(contrato_actual);
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
            lista_profesor_ejecu_vespertino.add(datos_profesor);
        }
        return lista_profesor_ejecu_vespertino;
    }

    public void setLista_profesor_ejecu_vespertino(List<ListaProfesor> lista_profesor_ejecu_vespertino) {
        this.lista_profesor_ejecu_vespertino = lista_profesor_ejecu_vespertino;
    } 
    
    public ProfesorController() {
    }
    
    public void desvincular(){
        selected.setVigente(false);
        Date fecha = new Date();
        int ano = fecha.getYear() +1900;
        selected.setAnoRetiro(ano);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorUpdated"));
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
        profeasigCtrl.prepareCreate();
        profeasigCtrl.getSelected().setRut_profesor(selected);
        profeasigCtrl.getSelected().setId_asignatura(curso_add);
        profeasigCtrl.getSelected().setAno_profesor_asignatura(ano_asig);
        profeasigCtrl.create();
    }
    
    public void delAsignatura(){
        profeasigCtrl.setSelected(curso_del);
        profeasigCtrl.destroy();
    }
    
    public void editProfeAsignatura(){
        profeasigCtrl.setSelected(curso_del);
        profeasigCtrl.update();
    }
    
     public void addContrato(){
        profecontCtrl.prepareCreate();
        profecontCtrl.getSelected().setRut_profesor(selected);
        profecontCtrl.getSelected().setId_contrato(contrato_add);
        profecontCtrl.getSelected().setAno_profesor_contrato(ano_asig);
        profecontCtrl.create();
    }
    
    public void delContrato(){
        profecontCtrl.setSelected(contrato_del);
        profecontCtrl.destroy();
    }
    
    public void editProfeContrato(){
        profecontCtrl.setSelected(contrato_del);
        profecontCtrl.update();
    }
    
     public void addGrado(){
        profegradoCtrl.prepareCreate();
        profegradoCtrl.getSelected().setRut_profesor(selected);
        profegradoCtrl.getSelected().setId_grado_academico(grado_add);
        profegradoCtrl.getSelected().setAno_profesor_grado(ano_asig);
        profegradoCtrl.create();
    }
    
    public void delGrado(){
        profegradoCtrl.setSelected(grado_del);
        profegradoCtrl.destroy();
    }
    
    public void editProfeGrado(){
        profegradoCtrl.setSelected(grado_del);
        profegradoCtrl.update();
    }
    
    public void addJerarquia(){
        profejerarCtrl.prepareCreate();
        profejerarCtrl.getSelected().setRut_profesor(selected);
        profejerarCtrl.getSelected().setId_jerarquia(jerarquia_add);
        profejerarCtrl.getSelected().setAno_profesor_jerarquia(ano_asig);
        profejerarCtrl.create();
    }
    
    public void delJerarquia(){
        profejerarCtrl.setSelected(jerarquia_del);
        profejerarCtrl.destroy();
    }
    
    public void editProfeJerar(){
        profejerarCtrl.setSelected(jerarquia_del);
        profejerarCtrl.update();
    }
    
    public Profesor prepareCreate() {
        selected = new Profesor();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
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
        for(int i=0;i<profeAsig.size();i++){
            ano.add(profeAsig.get(i).getId_asignatura().getNombre_asignatura());
            ano1.add(profeAsig.get(i).getId_asignatura().getNombre_asignatura());
        }
        profeAsig=profeAsigFacade.findByProfesor(selected, year2);
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
        return profeAsigAno;
    }

    public void setProfeAsigAno(ProfesorAsignaturaAno profeAsigAno) {
        this.profeAsigAno = profeAsigAno;
    }
    
    public List<Profesor> getItems() {
        items = getFacade().findAll();
        Collections.sort(items, new Comparator<Profesor>(){
            @Override
            public int compare(Profesor o1, Profesor o2){
               return o1.getApellido_pat().compareTo(o2.getApellido_pat());
            }
         });
        return items;
    }

    public List<Profesor> getItemsSortRut() {
        itemsSortRut = getFacade().findAll();
        Collections.sort(itemsSortRut, new Comparator<Profesor>(){
            @Override
            public int compare(Profesor o1, Profesor o2){
               return o1.getRut_profesor().compareTo(o2.getRut_profesor());
            }
         });
        return itemsSortRut;
    }

    public void setItemsSortRut(List<Profesor> itemsSortRut) {
        this.itemsSortRut = itemsSortRut;
    }

    public List<Profesor> getItemsSortNombre() {
        itemsSortNombre = getFacade().findAll();
        Collections.sort(itemsSortNombre, new Comparator<Profesor>(){
            @Override
            public int compare(Profesor o1, Profesor o2){
               return o1.getNombre_profesor().compareTo(o2.getNombre_profesor());
            }
         });
        return itemsSortNombre;
    }

    public void setItemsSortNombre(List<Profesor> itemsSortNombre) {
        this.itemsSortNombre = itemsSortNombre;
    }

    public List<Profesor> getItemsSortApellidoMaterno() {
        itemsSortApellidoMaterno = getFacade().findAll();
        Collections.sort(itemsSortApellidoMaterno, new Comparator<Profesor>(){
            @Override
            public int compare(Profesor o1, Profesor o2){
               return o1.getApellido_mat().compareTo(o2.getApellido_mat());
            }
         });
        return itemsSortApellidoMaterno;
    }

    public void setItemsSortApellidoMaterno(List<Profesor> itemsSortApellidoMaterno) {
        this.itemsSortApellidoMaterno = itemsSortApellidoMaterno;
    }

    public List<Profesor> getItemsSortTitulo() {
        itemsSortTitulo = getFacade().findAll();
        Collections.sort(itemsSortTitulo, new Comparator<Profesor>(){
            @Override
            public int compare(Profesor o1, Profesor o2){
               return o1.getTitulo_profesor().compareTo(o2.getTitulo_profesor());
            }
         });
        return itemsSortTitulo;
    }

    public void setItemsSortTitulo(List<Profesor> itemsSortTitulo) {
        this.itemsSortTitulo = itemsSortTitulo;
    }

    public List<Profesor> getItemsSortUnidad() {
        itemsSortUnidad = getFacade().findAll();
        Collections.sort(itemsSortUnidad, new Comparator<Profesor>(){
            @Override
            public int compare(Profesor o1, Profesor o2){
               return o1.getUnidad_profesor().compareTo(o2.getUnidad_profesor());
            }
         });
        return itemsSortUnidad;
    }

    public void setItemsSortUnidad(List<Profesor> itemsSortUnidad) {
        this.itemsSortUnidad = itemsSortUnidad;
    }

    public List<Profesor> getItemsSortComuna() {
        itemsSortComuna = getFacade().findAll();
        Collections.sort(itemsSortComuna, new Comparator<Profesor>(){
            @Override
            public int compare(Profesor o1, Profesor o2){
               return o1.getComuna_profesor().compareTo(o2.getComuna_profesor());
            }
         });
        return itemsSortComuna;
    }

    public void setItemsSortComuna(List<Profesor> itemsSortComuna) {
        this.itemsSortComuna = itemsSortComuna;
    }

    public List<Profesor> getItemsSortVigente() {
        itemsSortVigente = getFacade().findAll();
        Collections.sort(itemsSortVigente, new Comparator<Profesor>(){
            @Override
            public int compare(Profesor o1, Profesor o2){
                return Boolean.compare(o1.isVigente(),o2.isVigente());
            }
         });
        return itemsSortVigente;
    }

    public void setItemsSortVigente(List<Profesor> itemsSortVigente) {
        this.itemsSortVigente = itemsSortVigente;
    }

    public List<Profesor> getItemsSortAnoIngreso() {
        itemsSortAnoIngreso = getFacade().findAll();
        Collections.sort(itemsSortAnoIngreso, new Comparator<Profesor>(){
            @Override
            public int compare(Profesor o1, Profesor o2){
               return o1.getAno_ingreso() - o2.getAno_ingreso();
            }
         });
        return itemsSortAnoIngreso;
    }

    public List<Profesor> getItemsSortAnoRetiro() {
        itemsSortAnoRetiro = getFacade().findAll();
        Collections.sort(itemsSortAnoRetiro, new Comparator<Profesor>(){
            @Override
            public int compare(Profesor o1, Profesor o2){
               return o1.getAnoRetiro() - o2.getAnoRetiro();
            }
         });
        return itemsSortAnoRetiro;
    }

    public void setItemsSortAnoRetiro(List<Profesor> itemsSortAnoRetiro) {
        this.itemsSortAnoRetiro = itemsSortAnoRetiro;
    }
    
    

    public List<Profesor> getItemsSortInfo() {
        itemsSortInfo = getFacade().findAll();
        Collections.sort(itemsSortInfo, new Comparator<Profesor>(){
            @Override
            public int compare(Profesor o1, Profesor o2){
                return Boolean.compare(o1.isInformacion_completa_profesor(),o2.isInformacion_completa_profesor());
            }
         });
        return itemsSortInfo;
    }

    public void setItemsSortInfo(List<Profesor> itemsSortInfo) {
        this.itemsSortInfo = itemsSortInfo;
    }
    
    public void setItemsSortAnoIngreso(List<Profesor> itemsSortAnoIngreso) {
        this.itemsSortAnoIngreso = itemsSortAnoIngreso;
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
                return getStringKey(o.getId_profesor());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Profesor.class.getName()});
                return null;
            }
        }

    }

}
