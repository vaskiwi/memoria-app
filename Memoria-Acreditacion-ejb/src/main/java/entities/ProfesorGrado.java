/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vasco
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfesorGrado.findByGrado", query = "SELECT a FROM ProfesorGrado a WHERE a.id_grado_academico = :id_grado_academico AND a.ano_profesor_grado =:ano_profesor_grado"),
    @NamedQuery(name = "ProfesorGrado.findByProfesorNoFecha", query = "SELECT a FROM ProfesorGrado a WHERE a.rut_profesor = :rut_profesor"),
    @NamedQuery(name = "ProfesorGrado.findByProfesor", query = "SELECT a FROM ProfesorGrado a WHERE a.rut_profesor = :rut_profesor AND a.ano_profesor_grado =:ano_profesor_grado"),
    @NamedQuery(name = "ProfesorGrado.findByProfesorGrado", query = "SELECT a FROM ProfesorGrado a WHERE a.rut_profesor = :rut_profesor AND a.id_grado_academico =:id_grado_academico"),
    @NamedQuery(name = "ProfesorGrado.findByProfesorGradoAno", query = "SELECT a FROM ProfesorGrado a WHERE a.rut_profesor = :rut_profesor AND a.id_grado_academico =:id_grado_academico AND a.ano_profesor_grado =:ano_profesor_grado")
})
public class ProfesorGrado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_profesor_grado;
    private int ano_profesor_grado;
    
    @ManyToOne
    private Profesor rut_profesor;
    
    @ManyToOne
    private GradoAcademico id_grado_academico;

    public Long getId_profesor_grado() {
        return id_profesor_grado;
    }

    public void setId_profesor_grado(Long id_profesor_grado) {
        this.id_profesor_grado = id_profesor_grado;
    }

    public int getAno_profesor_grado() {
        return ano_profesor_grado;
    }

    public void setAno_profesor_grado(int ano_profesor_grado) {
        this.ano_profesor_grado = ano_profesor_grado;
    }

    public Profesor getRut_profesor() {
        return rut_profesor;
    }

    public void setRut_profesor(Profesor rut_profesor) {
        this.rut_profesor = rut_profesor;
    }

    public GradoAcademico getId_grado_academico() {
        return id_grado_academico;
    }

    public void setId_grado_academico(GradoAcademico id_grado_academico) {
        this.id_grado_academico = id_grado_academico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_profesor_grado != null ? id_profesor_grado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesorGrado)) {
            return false;
        }
        ProfesorGrado other = (ProfesorGrado) object;
        if ((this.id_profesor_grado == null && other.id_profesor_grado != null) || (this.id_profesor_grado != null && !this.id_profesor_grado.equals(other.id_profesor_grado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProfesorGrado[ id=" + id_profesor_grado + " ]";
    }
    
}
