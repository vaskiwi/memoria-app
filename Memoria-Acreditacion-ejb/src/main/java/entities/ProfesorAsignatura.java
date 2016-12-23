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
    @NamedQuery(name = "ProfesorAsignatura.findByAsignatura", query = "SELECT a FROM ProfesorAsignatura a WHERE a.id_asignatura = :id_asignatura"),
    @NamedQuery(name = "ProfesorAsignatura.findByProfesorNoFecha", query = "SELECT a FROM ProfesorAsignatura a WHERE a.rut_profesor = :rut_profesor"),
    @NamedQuery(name = "ProfesorAsignatura.findByProfesor", query = "SELECT a FROM ProfesorAsignatura a WHERE a.rut_profesor = :rut_profesor AND a.ano_profesor_asignatura =:ano_profesor_asignatura"),
    @NamedQuery(name = "ProfesorAsignatura.findByProfesorAsignatura", query = "SELECT a FROM ProfesorAsignatura a WHERE a.rut_profesor = :rut_profesor AND a.id_asignatura =:id_asignatura")
})
public class ProfesorAsignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int ano_profesor_asignatura;
    
    @ManyToOne
    private Profesor rut_profesor;
    
    @ManyToOne
    private Asignatura id_asignatura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAno_profesor_asignatura() {
        return ano_profesor_asignatura;
    }

    public void setAno_profesor_asignatura(int ano_profesor_asignatura) {
        this.ano_profesor_asignatura = ano_profesor_asignatura;
    }

    public Profesor getRut_profesor() {
        return rut_profesor;
    }

    public void setRut_profesor(Profesor rut_profesor) {
        this.rut_profesor = rut_profesor;
    }

    public Asignatura getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(Asignatura id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesorAsignatura)) {
            return false;
        }
        ProfesorAsignatura other = (ProfesorAsignatura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProfesorAsignatura[ id=" + id + " ]";
    }
    
}
