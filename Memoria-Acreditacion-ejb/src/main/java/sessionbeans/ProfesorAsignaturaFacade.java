/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Asignatura;
import entities.Profesor;
import entities.ProfesorAsignatura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Vasco
 */
@Stateless
public class ProfesorAsignaturaFacade extends AbstractFacade<ProfesorAsignatura> implements ProfesorAsignaturaFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorAsignaturaFacade() {
        super(ProfesorAsignatura.class);
    }
    @Override
    public List<Profesor> findByAsignatura(String nombre){
        Query query;
        query = em.createNamedQuery("Asignatura.findByNombre")
                .setParameter("nombre_asignatura", nombre);
        return query.getResultList();
    }
    @Override
    public List<ProfesorAsignatura> findByProfesorNoFecha(Profesor rut){
        Query query;
        query = em.createNamedQuery("ProfesorAsignatura.findByProfesorNoFecha")
                .setParameter("rut_profesor", rut);
        return query.getResultList();
    }
    @Override
    public List<ProfesorAsignatura> findByProfesor(Profesor rut, Integer ano){
        Query query;
        query = em.createNamedQuery("ProfesorAsignatura.findByProfesor")
                .setParameter("rut_profesor", rut);
        query.setParameter("ano_profesor_asignatura", ano);
        return query.getResultList();
    }
    @Override
    public List<ProfesorAsignatura> findByProfesorAsignatura(Profesor rut, Asignatura asignatura){
        Query query;
        query = em.createNamedQuery("ProfesorAsignatura.findByProfesorAsignatura")
                .setParameter("rut_profesor", rut);
        query.setParameter("id_asignatura", asignatura);
        return query.getResultList();
    }
}
