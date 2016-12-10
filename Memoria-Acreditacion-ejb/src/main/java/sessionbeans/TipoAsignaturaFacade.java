/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.TipoAsignatura;
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
public class TipoAsignaturaFacade extends AbstractFacade<TipoAsignatura> implements TipoAsignaturaFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoAsignaturaFacade() {
        super(TipoAsignatura.class);
    }
    
    @Override
    public TipoAsignatura findByNombre(String nombre){
        Query query;
        query = em.createNamedQuery("TipoAsignatura.findByNombre")
                .setParameter("nombre_tipo_asignatura", nombre);
        return (TipoAsignatura) query.getSingleResult();
    }
    @Override
    public List<TipoAsignatura> findByNombrelist(String nombre){
        Query query;
        query = em.createNamedQuery("TipoAsignatura.findByNombre")
                .setParameter("nombre_tipo_asignatura", nombre);
        return query.getResultList();
    }
}
