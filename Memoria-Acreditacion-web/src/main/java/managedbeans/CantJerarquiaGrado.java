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
    private int jerar1;
    private int jerar2;
    private int jerar3;
    private int jerar4;
    private int jerar5;
    private int jerar6;
    private int jerar7;
    private int jerar8;
    private int jerar9;
    private int jerar10;
    private int total;

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int getJerar1() {
        return jerar1;
    }

    public void setJerar1(int jerar1) {
        this.jerar1 = jerar1;
    }

    public int getJerar2() {
        return jerar2;
    }

    public void setJerar2(int jerar2) {
        this.jerar2 = jerar2;
    }

    public int getJerar3() {
        return jerar3;
    }

    public void setJerar3(int jerar3) {
        this.jerar3 = jerar3;
    }

    public int getJerar4() {
        return jerar4;
    }

    public void setJerar4(int jerar4) {
        this.jerar4 = jerar4;
    }

    public int getJerar5() {
        return jerar5;
    }

    public void setJerar5(int jerar5) {
        this.jerar5 = jerar5;
    }

    public int getJerar6() {
        return jerar6;
    }

    public void setJerar6(int jerar6) {
        this.jerar6 = jerar6;
    }

    public int getJerar7() {
        return jerar7;
    }

    public void setJerar7(int jerar7) {
        this.jerar7 = jerar7;
    }

    public int getJerar8() {
        return jerar8;
    }

    public void setJerar8(int jerar8) {
        this.jerar8 = jerar8;
    }

    public int getJerar9() {
        return jerar9;
    }

    public void setJerar9(int jerar9) {
        this.jerar9 = jerar9;
    }

    public int getJerar10() {
        return jerar10;
    }

    public void setJerar10(int jerar10) {
        this.jerar10 = jerar10;
    }

    public int getTotal() {
        total=jerar1+jerar2+jerar3+jerar4+jerar5+jerar6+jerar7+jerar8+jerar9+jerar10;
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
}
