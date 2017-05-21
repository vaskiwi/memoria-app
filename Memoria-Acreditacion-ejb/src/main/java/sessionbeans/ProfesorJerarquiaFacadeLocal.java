/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Jerarquia;
import entities.Profesor;
import entities.ProfesorJerarquia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasco
 */
@Local
public interface ProfesorJerarquiaFacadeLocal {
    void create(ProfesorJerarquia profesorJerarquia);

    void edit(ProfesorJerarquia profesorJerarquia);

    void remove(ProfesorJerarquia profesorJerarquia);

    ProfesorJerarquia find(Object id);

    List<ProfesorJerarquia> findAll();

    List<ProfesorJerarquia> findRange(int[] range);
    
    List<ProfesorJerarquia> findByJerarquia(Jerarquia jerarquia,Integer ano);
    
    List<ProfesorJerarquia> findByProfesorNoFecha(Profesor rut);
    
    List<ProfesorJerarquia> findByProfesor(Profesor rut, Integer ano);
    
    List<ProfesorJerarquia> findByProfesorJerarquia(Profesor rut, Jerarquia jerarquia);

    int count();
}
