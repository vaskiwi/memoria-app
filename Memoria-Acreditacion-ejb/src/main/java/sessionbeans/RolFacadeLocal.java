/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Rol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MacBookPro
 */
@Local
public interface RolFacadeLocal {

    void create(Rol rol);

    void edit(Rol rol);

    void remove(Rol rol);

    Rol find(Object id);
    
    Rol findByNombre(String nombre);
    
    List<Rol> findByNombreList(String nombre);

    List<Rol> findAll();

    List<Rol> findRange(int[] range);

    int count();
    
}
