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
    @NamedQuery(name = "ProfesorContrato.findByContrato", query = "SELECT a FROM ProfesorContrato a WHERE a.id_contrato = :id_contrato AND a.ano_profesor_contrato =:ano_profesor_contrato"),
    @NamedQuery(name = "ProfesorContrato.findByProfesorNoFecha", query = "SELECT a FROM ProfesorContrato a WHERE a.rut_profesor = :rut_profesor"),
    @NamedQuery(name = "ProfesorContrato.findByProfesor", query = "SELECT a FROM ProfesorContrato a WHERE a.rut_profesor = :rut_profesor AND a.ano_profesor_contrato =:ano_profesor_contrato"),
    @NamedQuery(name = "ProfesorContrato.findByProfesorContrato", query = "SELECT a FROM ProfesorContrato a WHERE a.rut_profesor = :rut_profesor AND a.id_contrato =:id_contrato")
})
public class ProfesorContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_profesor_contrato;
    private int ano_profesor_contrato;
    
    @ManyToOne
    private Profesor rut_profesor;
    
    @ManyToOne
    private Contrato id_contrato;

    public Long getId_profesor_contrato() {
        return id_profesor_contrato;
    }

    public void setId_profesor_contrato(Long id_profesor_contrato) {
        this.id_profesor_contrato = id_profesor_contrato;
    }

    public int getAno_profesor_contrato() {
        return ano_profesor_contrato;
    }

    public void setAno_profesor_contrato(int ano_profesor_contrato) {
        this.ano_profesor_contrato = ano_profesor_contrato;
    }

    public Profesor getRut_profesor() {
        return rut_profesor;
    }

    public void setRut_profesor(Profesor rut_profesor) {
        this.rut_profesor = rut_profesor;
    }

    public Contrato getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(Contrato id_contrato) {
        this.id_contrato = id_contrato;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_profesor_contrato != null ? id_profesor_contrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesorContrato)) {
            return false;
        }
        ProfesorContrato other = (ProfesorContrato) object;
        if ((this.id_profesor_contrato == null && other.id_profesor_contrato != null) || (this.id_profesor_contrato != null && !this.id_profesor_contrato.equals(other.id_profesor_contrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProfesorContrato[ id=" + id_profesor_contrato + " ]";
    }
    
}
