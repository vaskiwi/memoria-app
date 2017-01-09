/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Vasco
 */
@Named(value = "detalleBibliografiaTotal")
@Dependent
public class DetalleBibliografiaTotal {

    /**
     * Creates a new instance of DetalleBibliografiaTotal
     */
    public DetalleBibliografiaTotal() {
    }
    private int codigo_asignatura;
    private String asignatura;
    private int cantTitulosFisicos;
    private int cantEjemplaresFisicos;
    private int cantBibliografiaObligatoria;
    private int cantBibliografiaComplementaria;
    private float promAlumnosCurso;
    private float relEjemplaresAlumnos; 

    public int getCodigo_asignatura() {
        return codigo_asignatura;
    }

    public void setCodigo_asignatura(int codigo_asignatura) {
        this.codigo_asignatura = codigo_asignatura;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public int getCantTitulosFisicos() {
        return cantTitulosFisicos;
    }

    public void setCantTitulosFisicos(int cantTitulosFisicos) {
        this.cantTitulosFisicos = cantTitulosFisicos;
    }

    public int getCantEjemplaresFisicos() {
        return cantEjemplaresFisicos;
    }

    public void setCantEjemplaresFisicos(int cantEjemplaresFisicos) {
        this.cantEjemplaresFisicos = cantEjemplaresFisicos;
    }

    public int getCantBibliografiaObligatoria() {
        return cantBibliografiaObligatoria;
    }

    public void setCantBibliografiaObligatoria(int cantBibliografiaObligatoria) {
        this.cantBibliografiaObligatoria = cantBibliografiaObligatoria;
    }

    public int getCantBibliografiaComplementaria() {
        return cantBibliografiaComplementaria;
    }

    public void setCantBibliografiaComplementaria(int cantBibliografiaComplementaria) {
        this.cantBibliografiaComplementaria = cantBibliografiaComplementaria;
    }

    public float getPromAlumnosCurso() {
        return promAlumnosCurso;
    }

    public void setPromAlumnosCurso(float promAlumnosCurso) {
        this.promAlumnosCurso = promAlumnosCurso;
    }

    public float getRelEjemplaresAlumnos() {
        return relEjemplaresAlumnos;
    }

    public void setRelEjemplaresAlumnos(float relEjemplaresAlumnos) {
        this.relEjemplaresAlumnos = relEjemplaresAlumnos;
    }
    
}
