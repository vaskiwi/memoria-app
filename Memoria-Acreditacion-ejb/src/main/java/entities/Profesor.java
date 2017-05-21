/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author MacBookPro
 */
@Entity
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findByNombre", query = "SELECT a FROM Profesor a WHERE a.nombre_profesor = :nombre_profesor"),
    @NamedQuery(name = "Profesor.findByAno", query = "SELECT a FROM Profesor a WHERE a.ano_ingreso <= :ano AND a.vigente = :vigente"),
    @NamedQuery(name = "Profesor.findByAnoRetiro", query = "SELECT a FROM Profesor a WHERE a.ano_ingreso <= :ano AND a.vigente = :vigente AND a.anoRetiro >= :ano"),
    @NamedQuery(name = "Profesor.findByRut", query = "SELECT a FROM Profesor a WHERE a.rut_profesor = :rut"),
    @NamedQuery(name = "Profesor.findByDisponible", query = "SELECT a FROM Profesor a WHERE a.informacion_completa_profesor = :disponible")
})
public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_profesor;
    private String rut_profesor;
    private int anoRetiro;
    private String nombre_profesor;
    private String apellido_pat;
    private String apellido_mat;
    private boolean vigente;
    private String titulo_profesor;
    private int ano_ingreso;
    private String unidad_profesor;
    private String comuna_profesor;
    private boolean informacion_completa_profesor;
    
    @OneToMany(mappedBy = "rut_profesor",targetEntity=ProfesorAsignatura.class,fetch=FetchType.EAGER)
    private Set<ProfesorAsignatura> profesor_asignatura; 
    
    @OneToMany(mappedBy = "rut_profesor",targetEntity=ProfesorGrado.class,fetch=FetchType.EAGER)
    private Set<ProfesorGrado> profesor_grado;
    
    @OneToMany(mappedBy = "rut_profesor",targetEntity=ProfesorJerarquia.class,fetch=FetchType.EAGER)
    private Set<ProfesorJerarquia> profesor_jerarquia;
    
    @OneToMany(mappedBy = "rut_profesor",targetEntity=ProfesorContrato.class,fetch=FetchType.EAGER)
    private Set<ProfesorContrato> profesor_contrato;

    public Long getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(Long id_profesor) {
        this.id_profesor = id_profesor;
    }
    
    public String getRut_profesor() {
        return rut_profesor;
    }

    public void setRut_profesor(String rut_profesor) {
        this.rut_profesor = rut_profesor;
    }

    public String getApellido_pat() {
        return apellido_pat;
    }

    public void setApellido_pat(String apellido_pat) {
        this.apellido_pat = apellido_pat;
    }

    public String getApellido_mat() {
        return apellido_mat;
    }

    public void setApellido_mat(String apellido_mat) {
        this.apellido_mat = apellido_mat;
    }

    public int getAno_ingreso() {
        return ano_ingreso;
    }

    public void setAno_ingreso(int ano_ingreso) {
        this.ano_ingreso = ano_ingreso;
    }   

    public boolean isInformacion_completa_profesor() {
        return informacion_completa_profesor;
    }

    public void setInformacion_completa_profesor(boolean informacion_completa_profesor) {
        this.informacion_completa_profesor = informacion_completa_profesor;
    }

    public Set<ProfesorAsignatura> getProfesor_asignatura() {
        return profesor_asignatura;
    }

    public void setProfesor_asignatura(Set<ProfesorAsignatura> profesor_asignatura) {
        this.profesor_asignatura = profesor_asignatura;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public int getAnoRetiro() {
        return anoRetiro;
    }

    public void setAnoRetiro(int anoRetiro) {
        this.anoRetiro = anoRetiro;
    }

    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public String getUnidad_profesor() {
        return unidad_profesor;
    }

    public void setUnidad_profesor(String unidad_profesor) {
        this.unidad_profesor = unidad_profesor;
    }

    public String getComuna_profesor() {
        return comuna_profesor;
    }

    public void setComuna_profesor(String comuna_profesor) {
        this.comuna_profesor = comuna_profesor;
    }    

    public String getTitulo_profesor() {
        return titulo_profesor;
    }

    public void setTitulo_profesor(String titulo_profesor) {
        this.titulo_profesor = titulo_profesor;
    }

    public Set<ProfesorGrado> getProfesor_grado() {
        return profesor_grado;
    }

    public void setProfesor_grado(Set<ProfesorGrado> profesor_grado) {
        this.profesor_grado = profesor_grado;
    }

    public Set<ProfesorJerarquia> getProfesor_jerarquia() {
        return profesor_jerarquia;
    }
    
    public void setProfesor_jerarquia(Set<ProfesorJerarquia> profesor_jerarquia) {
        this.profesor_jerarquia = profesor_jerarquia;
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
        hash += (rut_profesor != null ? rut_profesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.rut_profesor == null && other.rut_profesor != null) || (this.rut_profesor != null && !this.rut_profesor.equals(other.rut_profesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return rut_profesor;
    }
    
}
