/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.GradoAcademico;
import entities.Profesor;
import entities.ProfesorGrado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasco
 */
@Local
public interface ProfesorGradoFacadeLocal {
    void create(ProfesorGrado profesorGrado);

    void edit(ProfesorGrado profesorGrado);

    void remove(ProfesorGrado profesorGrado);

    ProfesorGrado find(Object id);

    List<ProfesorGrado> findAll();

    List<ProfesorGrado> findRange(int[] range);
    
    List<ProfesorGrado> findByGrado(GradoAcademico grado, Integer ano);
    
    List<ProfesorGrado> findByProfesorNoFecha(Profesor rut);
    
    List<ProfesorGrado> findByProfesor(Profesor rut, Integer ano);
    
    List<ProfesorGrado> findByProfesorGrado(Profesor rut, GradoAcademico grado);
    
    List<ProfesorGrado> findByProfesorGradoAno(Profesor rut, GradoAcademico grado, Integer ano);

    int count();
    
}
