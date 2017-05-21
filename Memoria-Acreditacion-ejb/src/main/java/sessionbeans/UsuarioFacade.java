/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Rol;
import entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MacBookPro
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario findByUid(String uid) {
        Query query = em.createNamedQuery("Usuario.findByUid").setParameter("uid", uid);
        try{
            return (Usuario) query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    @Override
    public Usuario findByRutUsuario(String rutUsuario) {
        Query query = em.createNamedQuery("Usuario.findByRutUsuario").setParameter("rutUsuario", rutUsuario);
        try{
            return (Usuario) query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    @Override
    public Usuario findByNombreUsuario(String nombreUsuario) {
        Query query = em.createNamedQuery("Usuario.findByNombreUsuario").setParameter("nombreUsuario", nombreUsuario);
        try{
            return (Usuario) query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    @Override
    public List<Usuario> findByRolActivo(Rol rol, Boolean activo){
        Query query;
        query = em.createNamedQuery("Usuario.findByRolActivo")
                .setParameter("rol", rol);
        query.setParameter("activo", activo);
        return query.getResultList();
    }
}
