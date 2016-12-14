/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.DetalleBibliografia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Vasco
 */
@Stateless
public class DetalleBibliografiaFacade extends AbstractFacade<DetalleBibliografia> implements DetalleBibliografiaFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleBibliografiaFacade() {
        super(DetalleBibliografia.class);
    }
    
    @Override
    public DetalleBibliografia findById(Long id){
        Query query;
        query = em.createNamedQuery("DetalleBibliografia.findById")
                .setParameter("id_detalle_bibliografia", id);
        return (DetalleBibliografia) query.getSingleResult();
    }
}
