/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Vasco
 */
@Named(value = "cantJerarquiaGrado")
@SessionScoped
public class CantJerarquiaGrado implements Serializable {

    /**
     * Creates a new instance of CantJererquiaGrado
     */
    public CantJerarquiaGrado() {
    }
    private String grado;
    private int[] cantjerar;
    private int total;

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int[] getCantjerar() {
        return cantjerar;
    }

    public void setCantjerar(int[] cantjerar) {
        this.cantjerar = cantjerar;
    }
    
    public int getCantjerarIndex(int i) {
        return cantjerar[i];
    }

    public int getTotal() {
        for(int i=0;i<cantjerar.length;i++){
            total+=cantjerar[i];
        }
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
}
