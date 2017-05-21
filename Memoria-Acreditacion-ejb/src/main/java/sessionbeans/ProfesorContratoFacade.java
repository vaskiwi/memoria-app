/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Contrato;
import entities.Profesor;
import entities.ProfesorContrato;
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
public class ProfesorContratoFacade extends AbstractFacade<ProfesorContrato>  implements ProfesorContratoFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorContratoFacade() {
        super(ProfesorContrato.class);
    }
    @Override
    public List<ProfesorContrato> findByContrato(Contrato contrato,Integer ano){
        Query query;
        query = em.createNamedQuery("ProfesorContrato.findByContrato")
                .setParameter("id_contrato", contrato);
        query.setParameter("ano_profesor_contrato", ano);
        return query.getResultList();
    }
    @Override
    public List<ProfesorContrato> findByProfesorNoFecha(Profesor rut){
        Query query;
        query = em.createNamedQuery("ProfesorContrato.findByProfesorNoFecha")
                .setParameter("rut_profesor", rut);
        return query.getResultList();
    }
    @Override
    public List<ProfesorContrato> findByProfesor(Profesor rut, Integer ano){
        Query query;
        query = em.createNamedQuery("ProfesorContrato.findByProfesor")
                .setParameter("rut_profesor", rut);
        query.setParameter("ano_profesor_contrato", ano);
        return query.getResultList();
    }
    @Override
    public List<ProfesorContrato> findByProfesorContrato(Profesor rut, Contrato contrato){
        Query query;
        query = em.createNamedQuery("ProfesorContrato.findByProfesorContrato")
                .setParameter("rut_profesor", rut);
        query.setParameter("id_asignatura", contrato);
        return query.getResultList();
    }
}
