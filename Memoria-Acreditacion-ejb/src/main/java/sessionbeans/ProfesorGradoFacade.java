/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.GradoAcademico;
import entities.Profesor;
import entities.ProfesorGrado;
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
public class ProfesorGradoFacade extends AbstractFacade<ProfesorGrado>  implements ProfesorGradoFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorGradoFacade() {
        super(ProfesorGrado.class);
    }
    @Override
    public List<ProfesorGrado> findByGrado(GradoAcademico grado, Integer ano){
        Query query;
        query = em.createNamedQuery("ProfesorGrado.findByGrado")
                .setParameter("id_grado_academico", grado);
        query.setParameter("ano_profesor_grado", ano);
        return query.getResultList();
    }
    @Override
    public List<ProfesorGrado> findByProfesorNoFecha(Profesor rut){
        Query query;
        query = em.createNamedQuery("ProfesorGrado.findByProfesorNoFecha")
                .setParameter("rut_profesor", rut);
        return query.getResultList();
    }
    @Override
    public List<ProfesorGrado> findByProfesor(Profesor rut, Integer ano){
        Query query;
        query = em.createNamedQuery("ProfesorGrado.findByProfesor")
                .setParameter("rut_profesor", rut);
        query.setParameter("ano_profesor_grado", ano);
        return query.getResultList();
    }
    @Override
    public List<ProfesorGrado> findByProfesorGrado(Profesor rut, GradoAcademico grado){
        Query query;
        query = em.createNamedQuery("ProfesorGrado.findByProfesorGrado")
                .setParameter("rut_profesor", rut);
        query.setParameter("id_asignatura", grado);
        return query.getResultList();
    }
    @Override
    public List<ProfesorGrado> findByProfesorGradoAno(Profesor rut, GradoAcademico grado, Integer ano){
        Query query;
        query = em.createNamedQuery("ProfesorGrado.findByProfesorGrado")
                .setParameter("rut_profesor", rut);
        query.setParameter("id_asignatura", grado);
        query.setParameter("ano_profesor_grado", ano);
        return query.getResultList();
    }
}
