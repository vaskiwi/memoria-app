package managedbeans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



@Named("gradoNum")
@SessionScoped
public class gradoNum implements Serializable {

    private String grado;
    int cant_prof1;
    int cant_prof2;
    int cant_prof3;
    private int cant_prof4;
    private int cant_prof5;
    private int cant_prof6;
    private int cant_prof7;
    private int cant_prof8;
    private int cant_prof9;
    private int cant_prof10;
    
    
    public gradoNum(){
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int getCant_prof1() {
        return cant_prof1;
    }

    public void setCant_prof1(int cant_prof1) {
        this.cant_prof1 = cant_prof1;
    }

    public int getCant_prof2() {
        return cant_prof2;
    }

    public void setCant_prof2(int cant_prof2) {
        this.cant_prof2 = cant_prof2;
    }

    public int getCant_prof3() {
        return cant_prof3;
    }

    public void setCant_prof3(int cant_prof3) {
        this.cant_prof3 = cant_prof3;
    }

    public int getCant_prof4() {
        return cant_prof4;
    }

    public void setCant_prof4(int cant_prof4) {
        this.cant_prof4 = cant_prof4;
    }

    public int getCant_prof5() {
        return cant_prof5;
    }

    public void setCant_prof5(int cant_prof5) {
        this.cant_prof5 = cant_prof5;
    }

    public int getCant_prof6() {
        return cant_prof6;
    }

    public void setCant_prof6(int cant_prof6) {
        this.cant_prof6 = cant_prof6;
    }

    public int getCant_prof7() {
        return cant_prof7;
    }

    public void setCant_prof7(int cant_prof7) {
        this.cant_prof7 = cant_prof7;
    }

    public int getCant_prof8() {
        return cant_prof8;
    }

    public void setCant_prof8(int cant_prof8) {
        this.cant_prof8 = cant_prof8;
    }

    public int getCant_prof9() {
        return cant_prof9;
    }

    public void setCant_prof9(int cant_prof9) {
        this.cant_prof9 = cant_prof9;
    }

    public int getCant_prof10() {
        return cant_prof10;
    }

    public void setCant_prof10(int cant_prof10) {
        this.cant_prof10 = cant_prof10;
    }
    
}
