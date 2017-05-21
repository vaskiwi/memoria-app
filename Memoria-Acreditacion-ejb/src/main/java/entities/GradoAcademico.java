/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MacBookPro
 */
@Entity
@Table(name = "grado_academico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GradoAcademico.findByNombre", query = "SELECT a FROM GradoAcademico a WHERE a.nombre_grado_academico = :nombre_grado_academico"),
})

public class GradoAcademico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_grado_academico;
    
    private String nombre_grado_academico;
    
/*    @JoinTable(name = "grado_profesor", joinColumns = {
        @JoinColumn(name = "grado_academico_id_grado_academico", referencedColumnName = "id_grado_academico")}, inverseJoinColumns = {
        @JoinColumn(name = "profesor_rut_profesor", referencedColumnName = "rut_profesor")})
*/   
    @OneToMany(mappedBy = "id_grado_academico")
    private Set<ProfesorGrado> profesor_grado;  

    public String getNombre_grado_academico() {
        return nombre_grado_academico;
    }

    public void setNombre_grado_academico(String nombre_grado_academico) {
        this.nombre_grado_academico = nombre_grado_academico;
    }

    public Long getId_grado_academico() {
        return id_grado_academico;
    }

    public void setId_grado_academico(Long id_grado_academico) {
        this.id_grado_academico = id_grado_academico;
    }

    public Set<ProfesorGrado> getProfesor_grado() {
        return profesor_grado;
    }

    public void setProfesor_grado(Set<ProfesorGrado> profesor_grado) {
        this.profesor_grado = profesor_grado;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_grado_academico != null ? id_grado_academico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GradoAcademico)) {
            return false;
        }
        GradoAcademico other = (GradoAcademico) object;
        if ((this.id_grado_academico == null && other.id_grado_academico != null) || (this.id_grado_academico != null && !this.id_grado_academico.equals(other.id_grado_academico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre_grado_academico;
    }

    
}
