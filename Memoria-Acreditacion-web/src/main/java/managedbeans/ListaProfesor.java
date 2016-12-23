/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.GradoAcademico;
import entities.Jerarquia;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Vasco
 */
@Named(value = "listaProfesor")
@SessionScoped
public class ListaProfesor implements Serializable {

    /**
     * Creates a new instance of ListaProfesor
     */
    public ListaProfesor() {
    }
    private String rut_profesor;
    private int anoRetiro;
    private String nombre_profesor;
    private String apellido_pat;
    private String apellido_mat;
    private String contrato;
    private boolean vigente;
    private String titulo_profesor;
    private float dedicacion_contratada;
    private int ano_ingreso;
    private String unidad_profesor;
    private String comuna_profesor;
    private Jerarquia jerarquia_profesor;
    private GradoAcademico grado_academico_profesor;
    private List<String> asignaturas_profesor;
    private List<String> asignaturas_profesor_ano;
    private List<String> asignaturas_profesor_ano_2;
    private List<String> asignaturas_profesor_ano_3;
    private List<String> asignaturas_profesor_ano_4;
    private List<String> asignaturas_profesor_ano_5;

    public String getRut_profesor() {
        return rut_profesor;
    }

    public void setRut_profesor(String rut_profesor) {
        this.rut_profesor = rut_profesor;
    }

    public int getAnoRetiro() {
        return anoRetiro;
    }

    public void setAnoRetiro(int anoRetiro) {
        this.anoRetiro = anoRetiro;
    }

    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public String getApellido_pat() {
        return apellido_pat;
    }

    public void setApellido_pat(String apellido_pat) {
        this.apellido_pat = apellido_pat;
    }

    public String getApellido_mat() {
        return apellido_mat;
    }

    public void setApellido_mat(String apellido_mat) {
        this.apellido_mat = apellido_mat;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public String getTitulo_profesor() {
        return titulo_profesor;
    }

    public void setTitulo_profesor(String titulo_profesor) {
        this.titulo_profesor = titulo_profesor;
    }

    public float getDedicacion_contratada() {
        return dedicacion_contratada;
    }

    public void setDedicacion_contratada(float dedicacion_contratada) {
        this.dedicacion_contratada = dedicacion_contratada;
    }

    public int getAno_ingreso() {
        return ano_ingreso;
    }

    public void setAno_ingreso(int ano_ingreso) {
        this.ano_ingreso = ano_ingreso;
    }

    public String getUnidad_profesor() {
        return unidad_profesor;
    }

    public void setUnidad_profesor(String unidad_profesor) {
        this.unidad_profesor = unidad_profesor;
    }

    public String getComuna_profesor() {
        return comuna_profesor;
    }

    public void setComuna_profesor(String comuna_profesor) {
        this.comuna_profesor = comuna_profesor;
    }

    public List<String> getAsignaturas_profesor() {
        return asignaturas_profesor;
    }

    public void setAsignaturas_profesor(List<String> asignaturas_profesor) {
        this.asignaturas_profesor = asignaturas_profesor;
    }

    public List<String> getAsignaturas_profesor_ano() {
        return asignaturas_profesor_ano;
    }

    public void setAsignaturas_profesor_ano(List<String> asignaturas_profesor_ano) {
        this.asignaturas_profesor_ano = asignaturas_profesor_ano;
    }

    public List<String> getAsignaturas_profesor_ano_5() {
        return asignaturas_profesor_ano_5;
    }

    public void setAsignaturas_profesor_ano_5(List<String> asignaturas_profesor_ano_1) {
        this.asignaturas_profesor_ano_5 = asignaturas_profesor_ano_1;
    }

    public List<String> getAsignaturas_profesor_ano_2() {
        return asignaturas_profesor_ano_2;
    }

    public void setAsignaturas_profesor_ano_2(List<String> asignaturas_profesor_ano_2) {
        this.asignaturas_profesor_ano_2 = asignaturas_profesor_ano_2;
    }

    public List<String> getAsignaturas_profesor_ano_3() {
        return asignaturas_profesor_ano_3;
    }

    public void setAsignaturas_profesor_ano_3(List<String> asignaturas_profesor_ano_3) {
        this.asignaturas_profesor_ano_3 = asignaturas_profesor_ano_3;
    }

    public List<String> getAsignaturas_profesor_ano_4() {
        return asignaturas_profesor_ano_4;
    }

    public void setAsignaturas_profesor_ano_4(List<String> asignaturas_profesor_ano_4) {
        this.asignaturas_profesor_ano_4 = asignaturas_profesor_ano_4;
    }

    public Jerarquia getJerarquia_profesor() {
        return jerarquia_profesor;
    }

    public void setJerarquia_profesor(Jerarquia jerarquia_profesor) {
        this.jerarquia_profesor = jerarquia_profesor;
    }

    public GradoAcademico getGrado_academico_profesor() {
        return grado_academico_profesor;
    }

    public void setGrado_academico_profesor(GradoAcademico grado_academico_profesor) {
        this.grado_academico_profesor = grado_academico_profesor;
    }
    
    
}
