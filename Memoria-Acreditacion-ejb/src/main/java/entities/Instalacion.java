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

/**
 *
 * @author Vasco
 */
@Entity
public class Instalacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_instalacion;
    private String nombre_instalacion;
    private String direccion_instalacion;
    private String descripcion_instalacion;
    private String horario_instalación;

    public Long getId_instalacion() {
        return id_instalacion;
    }

    public void setId_instalacion(Long id_instalacion) {
        this.id_instalacion = id_instalacion;
    }

    public String getNombre_instalacion() {
        return nombre_instalacion;
    }

    public void setNombre_instalacion(String nombre_instalacion) {
        this.nombre_instalacion = nombre_instalacion;
    }

    public String getDireccion_instalacion() {
        return direccion_instalacion;
    }

    public void setDireccion_instalacion(String direccion_instalacion) {
        this.direccion_instalacion = direccion_instalacion;
    }

    public String getDescripcion_instalacion() {
        return descripcion_instalacion;
    }

    public void setDescripcion_instalacion(String descripcion_instalacion) {
        this.descripcion_instalacion = descripcion_instalacion;
    }

    public String getHorario_instalación() {
        return horario_instalación;
    }

    public void setHorario_instalación(String horario_instalación) {
        this.horario_instalación = horario_instalación;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_instalacion != null ? id_instalacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instalacion)) {
            return false;
        }
        Instalacion other = (Instalacion) object;
        if ((this.id_instalacion == null && other.id_instalacion != null) || (this.id_instalacion != null && !this.id_instalacion.equals(other.id_instalacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre_instalacion;
    }
    
}
