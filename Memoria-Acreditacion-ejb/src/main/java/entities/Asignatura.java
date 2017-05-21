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
import javax.persistence.ManyToOne;
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
@Table(name = "asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findById", query = "SELECT a FROM Asignatura a WHERE a.id_asignatura = :id_asignatura"),
    @NamedQuery(name = "Asignatura.findByNombre", query = "SELECT a FROM Asignatura a WHERE a.nombre_asignatura = :nombre_asignatura"),
    @NamedQuery(name = "Asignatura.findByNombrelist", query = "SELECT a FROM Asignatura a WHERE a.nombre_asignatura = :nombre_asignatura"),
    @NamedQuery(name = "Asignatura.findByCodigo", query = "SELECT a FROM Asignatura a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Asignatura.findByCarreraYJornada", query = "SELECT a FROM Asignatura a WHERE a.carrera = :carrera AND a.jornada_asignatura = :jornada_asignatura"), 
    @NamedQuery(name = "Asignatura.findByDisponible", query = "SELECT a FROM Asignatura a WHERE a.informacion_completa_asignatura = :disponible")   
})


public class Asignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_asignatura;
    
    private String nombre_asignatura;
    private float cant_horas_presenciales;
    private float cant_horas_no_presenciales;
    private int contr_perfil_egreso;
    private int semestre;
    private int codigo;
    private int creditos_asignatura;
    private String jornada_asignatura;
    private boolean informacion_completa_asignatura;
    
    @OneToMany(mappedBy = "asignatura")
    private Set<DetalleBibliografia> detalleBibliografias;
    
    @ManyToOne
    private Carrera carrera;
    @ManyToOne
    private TipoAsignatura tipo_asignatura;

    @OneToMany(mappedBy = "id_asignatura")
    private Set<ProfesorAsignatura> profesor_asignatura;  

    public int getCreditos_asignatura() {
        return creditos_asignatura;
    }

    public void setCreditos_asignatura(int creditos_asignatura) {
        this.creditos_asignatura = creditos_asignatura;
    }

    public Long getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(Long id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public String getNombre_asignatura() {
        return nombre_asignatura;
    }

    public void setNombre_asignatura(String nombre_asignatura) {
        this.nombre_asignatura = nombre_asignatura;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }   

    public float getCant_horas_presenciales() {
        return cant_horas_presenciales;
    }

    public void setCant_horas_presenciales(float cant_horas_presenciales) {
        this.cant_horas_presenciales = cant_horas_presenciales;
    }

    public float getCant_horas_no_presenciales() {
        return cant_horas_no_presenciales;
    }

    public void setCant_horas_no_presenciales(float cant_horas_no_presenciales) {
        this.cant_horas_no_presenciales = cant_horas_no_presenciales;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }   
   
    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Set<ProfesorAsignatura> getProfesor_asignatura() {
        return profesor_asignatura;
    }

    public void setProfesor_asignatura(Set<ProfesorAsignatura> profesor_asignatura) {
        this.profesor_asignatura = profesor_asignatura;
    }

    public Set<DetalleBibliografia> getDetalleBibliografias() {
        return detalleBibliografias;
    }

    public void setDetalleBibliografias(Set<DetalleBibliografia> detalleBibliografias) {
        this.detalleBibliografias = detalleBibliografias;
    }

    public int getContr_perfil_egreso() {
        return contr_perfil_egreso;
    }

    public void setContr_perfil_egreso(int contr_perfil_egreso) {
        this.contr_perfil_egreso = contr_perfil_egreso;
    }

    public TipoAsignatura getTipo_asignatura() {
        return tipo_asignatura;
    }

    public void setTipo_asignatura(TipoAsignatura tipo_asignatura) {
        this.tipo_asignatura = tipo_asignatura;
    }

    public String getJornada_asignatura() {
        return jornada_asignatura;
    }

    public void setJornada_asignatura(String jornada_asignatura) {
        this.jornada_asignatura = jornada_asignatura;
    }

    public boolean isInformacion_completa_asignatura() {
        return informacion_completa_asignatura;
    }

    public void setInformacion_completa_asignatura(boolean informacion_completa_asignatura) {
        this.informacion_completa_asignatura = informacion_completa_asignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_asignatura != null ? id_asignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.id_asignatura == null && other.id_asignatura != null) || (this.id_asignatura != null && !this.id_asignatura.equals(other.id_asignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo+" "+nombre_asignatura+" "+carrera.getNombre_carrera()+" "+jornada_asignatura;
    }
    
}
