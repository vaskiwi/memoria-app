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
import javax.ejb.Local;

/**
 *
 * @author Vasco
 */
@Local
public interface ProfesorContratoFacadeLocal {
    void create(ProfesorContrato profesorContrato);

    void edit(ProfesorContrato profesorContrato);

    void remove(ProfesorContrato profesorContrato);

    ProfesorContrato find(Object id);

    List<ProfesorContrato> findAll();

    List<ProfesorContrato> findRange(int[] range);
    
    List<ProfesorContrato> findByContrato(Contrato contrato,Integer ano);
    
    List<ProfesorContrato> findByProfesorNoFecha(Profesor rut);
    
    List<ProfesorContrato> findByProfesor(Profesor rut, Integer ano);
    
    List<ProfesorContrato> findByProfesorContrato(Profesor rut, Contrato contrato);

    int count();
    
}
