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
@Named(value = "gradoCant")
@SessionScoped
public class GradoCant implements Serializable {

    /**
     * Creates a new instance of GradoCant
     */
    public GradoCant() {
    }
    private String grado;
    private int cant1;
    private int cant2;
    private int cant3;
    private int cant4;
    private int sub_total;
    private int total;

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int getCant1() {
        return cant1;
    }

    public void setCant1(int cant1) {
        this.cant1 = cant1;
    }

    public int getCant2() {
        return cant2;
    }

    public void setCant2(int cant2) {
        this.cant2 = cant2;
    }

    public int getCant3() {
        return cant3;
    }

    public void setCant3(int cant3) {
        this.cant3 = cant3;
    }

    public int getCant4() {
        return cant4;
    }

    public void setCant4(int cant4) {
        this.cant4 = cant4;
    }

    public int getSub_total() {
        sub_total=cant2+cant3+cant4;
        return sub_total;
    }

    public void setSub_total(int sub_total) {
        this.sub_total = sub_total;
    }

    public int getTotal() {
        total=cant1+cant2+cant3+cant4;
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
