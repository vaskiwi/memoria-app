/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Asignatura;
import entities.Carrera;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MacBookPro
 */
@Local
public interface AsignaturaFacadeLocal {

    void create(Asignatura asignatura);

    void edit(Asignatura asignatura);

    void remove(Asignatura asignatura);

    Asignatura find(Object id);

    List<Asignatura> findAll();

    List<Asignatura> findRange(int[] range);
    
    Asignatura findById(Long id);
    
    Asignatura findByNombre(String nombre);
    
    List<Asignatura> findByNombrelist(String nombre);
    
    List<Asignatura> findByCodigo(int codigo);
    
    List<Asignatura> findByCarrerayJornada(Carrera carrera, String jornada);
    
    List<Asignatura> findByDisponible(Boolean disponible);

    int count();
    
}
