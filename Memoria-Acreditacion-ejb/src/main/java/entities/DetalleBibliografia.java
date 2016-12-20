/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vasco
 */
@Entity
@Table(name = "DetalleBibliografia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleBibliografia.findById", query = "SELECT a FROM DetalleBibliografia a WHERE a.id_detalle_bibliografia = :id_detalle_bibliografia"),
})
public class DetalleBibliografia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_detalle_bibliografia;
    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;
    @NotNull
    @Column(name = "cantidad_titulos_fisicos")
    private int cantTitulosFisicos;
    @NotNull
    @Column(name = "cantidad_ejemplares_fisicos")
    private int cantEjemplaresFisicos;
    @NotNull
    @Column(name = "cantidad_bibliografia_obligatoria")
    private int cantBibliografiaObligatoria;
    @NotNull
    @Column(name = "cantidad_bibliografia_complementaria")
    private int cantBibliografiaComplementaria;
    @Column(name = "promedio_alumnos_curso")
    private float promAlumnosCurso;
    @Column(name = "relacion_ejemplares_alumnos")
    private float relEjemplaresAlumnos;

    public Long getId_detalle_bibliografia() {
        return id_detalle_bibliografia;
    }

    public void setId_detalle_bibliografia(Long id_detalle_bibliografia) {
        this.id_detalle_bibliografia = id_detalle_bibliografia;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_detalle_bibliografia != null ? id_detalle_bibliografia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleBibliografia)) {
            return false;
        }
        DetalleBibliografia other = (DetalleBibliografia) object;
        if ((this.id_detalle_bibliografia == null && other.id_detalle_bibliografia != null) || (this.id_detalle_bibliografia != null && !this.id_detalle_bibliografia.equals(other.id_detalle_bibliografia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DetalleBibliografia[ id=" + id_detalle_bibliografia + " ]";
    }
    
}
