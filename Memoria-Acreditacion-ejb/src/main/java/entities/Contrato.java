/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Vasco
 */
@Entity
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_contrato;
    
    private String nombre_contrato;
    
    private int horas_contrato;
    
    private boolean dependencia_asignaturas_contrato;    
    
    @OneToMany(mappedBy = "id_contrato")
    private Set<ProfesorContrato> profesor_contrato;  

    public Long getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(Long id_contrato) {
        this.id_contrato = id_contrato;
    }

    public String getNombre_contrato() {
        return nombre_contrato;
    }

    public void setNombre_contrato(String nombre_contrato) {
        this.nombre_contrato = nombre_contrato;
    }

    public int getHoras_contrato() {
        return horas_contrato;
    }

    public void setHoras_contrato(int horas_contrato) {
        this.horas_contrato = horas_contrato;
    }

    public boolean isDependencia_asignaturas_contrato() {
        return dependencia_asignaturas_contrato;
    }

    public void setDependencia_asignaturas_contrato(boolean dependencia_asignaturas_contrato) {
        this.dependencia_asignaturas_contrato = dependencia_asignaturas_contrato;
    }
    
    

    public Set<ProfesorContrato> getProfesor_contrato() {
        return profesor_contrato;
    }

    public void setProfesor_contrato(Set<ProfesorContrato> profesor_contrato) {
        this.profesor_contrato = profesor_contrato;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_contrato != null ? id_contrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.id_contrato == null && other.id_contrato != null) || (this.id_contrato != null && !this.id_contrato.equals(other.id_contrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre_contrato;
    }
    
}
