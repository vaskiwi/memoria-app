/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.TipoAsignatura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasco
 */
@Local
public interface TipoAsignaturaFacadeLocal {
    void create(TipoAsignatura tipo_asignatura);

    void edit(TipoAsignatura tipo_asignatura);

    void remove(TipoAsignatura tipo_asignatura);

    TipoAsignatura find(Object id);

    List<TipoAsignatura> findAll();

    List<TipoAsignatura> findRange(int[] range);
    
    TipoAsignatura findByNombre(String nombre);
    
    List<TipoAsignatura> findByNombrelist(String nombre);
    
    int count();
}
