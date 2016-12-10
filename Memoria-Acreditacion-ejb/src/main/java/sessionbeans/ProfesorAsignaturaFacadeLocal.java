/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Profesor;
import entities.ProfesorAsignatura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasco
 */
@Local
public interface ProfesorAsignaturaFacadeLocal {
    void create(ProfesorAsignatura asignatura);

    void edit(ProfesorAsignatura asignatura);

    void remove(ProfesorAsignatura asignatura);

    ProfesorAsignatura find(Object id);

    List<ProfesorAsignatura> findAll();

    List<ProfesorAsignatura> findRange(int[] range);
    
    List<Profesor> findByAsignatura(String nombre);
    
    List<ProfesorAsignatura> findByProfesorNoFecha(Profesor rut);
    
    List<ProfesorAsignatura> findByProfesor(Profesor rut, Integer ano);

    int count();
    
}
