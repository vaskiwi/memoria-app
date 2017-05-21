/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Asignatura;
import entities.Carrera;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MacBookPro
 */
@Stateless
public class AsignaturaFacade extends AbstractFacade<Asignatura> implements AsignaturaFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignaturaFacade() {
        super(Asignatura.class);
    }
    
    @Override
    public Asignatura findById(Long id){
        Query query;
        query = em.createNamedQuery("Asignatura.findById")
                .setParameter("id_asignatura", id);
        return (Asignatura) query.getSingleResult();
    }
    
    @Override
    public Asignatura findByNombre(String nombre){
        Query query;
        query = em.createNamedQuery("Asignatura.findByNombre")
                .setParameter("nombre_asignatura", nombre);
        return (Asignatura) query.getSingleResult();
    }
    
    @Override
    public List<Asignatura> findByNombrelist(String nombre){
        Query query;
        query = em.createNamedQuery("Asignatura.findByNombre")
                .setParameter("nombre_asignatura", nombre);
        return query.getResultList();
    }
    
    @Override
    public List<Asignatura> findByCodigo(int codigo){
        Query query;
        query = em.createNamedQuery("Asignatura.findByCodigo")
                .setParameter("codigo", codigo);
        return query.getResultList();
    }
    
    @Override
    public List<Asignatura> findByCarrerayJornada(Carrera carrera,String jornada){
        Query query;
        query = em.createNamedQuery("Asignatura.findByCarreraYJornada")
                .setParameter("carrera", carrera)
                .setParameter("jornada_asignatura", jornada);
        return query.getResultList();
    }
    @Override
    public List<Asignatura> findByDisponible(Boolean disponible){
        Query query;
        query = em.createNamedQuery("Asignatura.findByDisponible")
                .setParameter("disponible", disponible);
        return query.getResultList();
    }
    
}
