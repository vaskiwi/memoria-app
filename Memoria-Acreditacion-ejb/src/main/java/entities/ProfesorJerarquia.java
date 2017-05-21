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
    @NamedQuery(name = "ProfesorJerarquia.findByJerarquia", query = "SELECT a FROM ProfesorJerarquia a WHERE a.id_jerarquia = :id_jerarquia AND a.ano_profesor_jerarquia =:ano_profesor_jerarquia"),
    @NamedQuery(name = "ProfesorJerarquia.findByProfesorNoFecha", query = "SELECT a FROM ProfesorJerarquia a WHERE a.rut_profesor = :rut_profesor"),
    @NamedQuery(name = "ProfesorJerarquia.findByProfesor", query = "SELECT a FROM ProfesorJerarquia a WHERE a.rut_profesor = :rut_profesor AND a.ano_profesor_jerarquia =:ano_profesor_jerarquia"),
    @NamedQuery(name = "ProfesorJerarquia.findByProfesorJerarquia", query = "SELECT a FROM ProfesorJerarquia a WHERE a.rut_profesor = :rut_profesor AND a.id_jerarquia =:id_jerarquia")
})
public class ProfesorJerarquia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_profesor_jerarquia;
    private int ano_profesor_jerarquia;
    
    @ManyToOne
    private Profesor rut_profesor;
    
    @ManyToOne
    private Jerarquia id_jerarquia;

    public Long getId_profesor_jerarquia() {
        return id_profesor_jerarquia;
    }

    public void setId_profesor_jerarquia(Long id_profesor_jerarquia) {
        this.id_profesor_jerarquia = id_profesor_jerarquia;
    }

    public int getAno_profesor_jerarquia() {
        return ano_profesor_jerarquia;
    }

    public void setAno_profesor_jerarquia(int ano_profesor_jerarquia) {
        this.ano_profesor_jerarquia = ano_profesor_jerarquia;
    }

    public Profesor getRut_profesor() {
        return rut_profesor;
    }

    public void setRut_profesor(Profesor rut_profesor) {
        this.rut_profesor = rut_profesor;
    }

    public Jerarquia getId_jerarquia() {
        return id_jerarquia;
    }

    public void setId_jerarquia(Jerarquia id_jerarquia) {
        this.id_jerarquia = id_jerarquia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_profesor_jerarquia != null ? id_profesor_jerarquia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesorJerarquia)) {
            return false;
        }
        ProfesorJerarquia other = (ProfesorJerarquia) object;
        if ((this.id_profesor_jerarquia == null && other.id_profesor_jerarquia != null) || (this.id_profesor_jerarquia != null && !this.id_profesor_jerarquia.equals(other.id_profesor_jerarquia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProfesorJerarquia[ id=" + id_profesor_jerarquia + " ]";
    }
    
}
