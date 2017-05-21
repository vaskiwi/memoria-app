/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Jerarquia;
import entities.Profesor;
import entities.ProfesorJerarquia;
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
public class ProfesorJerarquiaFacade extends AbstractFacade<ProfesorJerarquia>  implements ProfesorJerarquiaFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorJerarquiaFacade() {
        super(ProfesorJerarquia.class);
    }
    @Override
    public List<ProfesorJerarquia> findByJerarquia(Jerarquia jerarquia,Integer ano){
        Query query;
        query = em.createNamedQuery("ProfesorJerarquia.findByJerarquia")
                .setParameter("id_jerarquia", jerarquia);
        query.setParameter("ano_profesor_jerarquia", ano);
        return query.getResultList();
    }
    @Override
    public List<ProfesorJerarquia> findByProfesorNoFecha(Profesor rut){
        Query query;
        query = em.createNamedQuery("ProfesorJerarquia.findByProfesorNoFecha")
                .setParameter("rut_profesor", rut);
        return query.getResultList();
    }
    @Override
    public List<ProfesorJerarquia> findByProfesor(Profesor rut, Integer ano){
        Query query;
        query = em.createNamedQuery("ProfesorJerarquia.findByProfesor")
                .setParameter("rut_profesor", rut);
        query.setParameter("ano_profesor_jerarquia", ano);
        return query.getResultList();
    }
    @Override
    public List<ProfesorJerarquia> findByProfesorJerarquia(Profesor rut, Jerarquia jerarquia){
        Query query;
        query = em.createNamedQuery("ProfesorJerarquia.findByProfesorJerarquia")
                .setParameter("rut_profesor", rut);
        query.setParameter("id_asignatura", jerarquia);
        return query.getResultList();
    }
}
