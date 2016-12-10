/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.DetalleBibliografia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasco
 */
@Local
public interface DetalleBibliografiaFacadeLocal {
    void create(DetalleBibliografia ampliado);

    void edit(DetalleBibliografia ampliado);

    void remove(DetalleBibliografia ampliado);

    DetalleBibliografia find(Object id);

    List<DetalleBibliografia> findAll();

    List<DetalleBibliografia> findRange(int[] range);

    int count();
    
}
