/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Rol;
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
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @Override
    public Rol findByNombre(String nombre){
        Query query;
        query = em.createNamedQuery("Rol.findByNombre")
                .setParameter("nombre", nombre);
        return (Rol) query.getSingleResult();
    }
    
    @Override
    public List<Rol> findByNombreList(String nombre){
        Query query;
        query = em.createNamedQuery("Rol.findByNombreList")
                .setParameter("nombre", nombre);
        return query.getResultList();
    }

    public RolFacade() {
        super(Rol.class);
    }
    
}
