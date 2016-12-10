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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vasco
 */
@Entity
@Table(name = "TipoAsignatura")
@XmlRootElement@NamedQueries({
    @NamedQuery(name = "TipoAsignatura.findByNombre", query = "SELECT a FROM TipoAsignatura a WHERE a.nombre_tipo_asignatura = :nombre_tipo_asignatura"),
    @NamedQuery(name = "TipoAsignatura.findByNombrelist", query = "SELECT a FROM TipoAsignatura a WHERE a.nombre_tipo_asignatura= :nombre_tipo_asignatura"),
})
public class TipoAsignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_tipo_asignatura;
    private String nombre_tipo_asignatura;
    
    @OneToMany(mappedBy = "tipo_asignatura")
    private Set<Asignatura> asignaturas;

    public Long getId_tipo_asignatura() {
        return id_tipo_asignatura;
    }

    public void setId_tipo_asignatura(Long id_tipo_asignatura) {
        this.id_tipo_asignatura = id_tipo_asignatura;
    }

    public String getNombre_tipo_asignatura() {
        return nombre_tipo_asignatura;
    }

    public void setNombre_tipo_asignatura(String nombre_tipo_asignatura) {
        this.nombre_tipo_asignatura = nombre_tipo_asignatura;
    }

    public Set<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Set<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_tipo_asignatura != null ? id_tipo_asignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAsignatura)) {
            return false;
        }
        TipoAsignatura other = (TipoAsignatura) object;
        if ((this.id_tipo_asignatura == null && other.id_tipo_asignatura != null) || (this.id_tipo_asignatura != null && !this.id_tipo_asignatura.equals(other.id_tipo_asignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre_tipo_asignatura;
    }
    
}
