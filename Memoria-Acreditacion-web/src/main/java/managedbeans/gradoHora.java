package managedbeans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



@Named("gradoHora")
@SessionScoped
public class gradoHora implements Serializable {

    String grado;
    float cant_horas1;
    float cant_horas2;
    float cant_horas3;
    float cant_horas4;
    float cant_horas5;
    
    public gradoHora(){
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public float getCant_horas1() {
        return cant_horas1;
    }

    public void setCant_horas1(float cant_horas1) {
        this.cant_horas1 = cant_horas1;
    }

    public float getCant_horas2() {
        return cant_horas2;
    }

    public void setCant_horas2(float cant_horas2) {
        this.cant_horas2 = cant_horas2;
    }

    public float getCant_horas3() {
        return cant_horas3;
    }

    public void setCant_horas3(float cant_horas3) {
        this.cant_horas3 = cant_horas3;
    }

    public float getCant_horas4() {
        return cant_horas4;
    }

    public void setCant_horas4(float cant_horas4) {
        this.cant_horas4 = cant_horas4;
    }

    public float getCant_horas5() {
        return cant_horas5;
    }

    public void setCant_horas5(float cant_horas5) {
        this.cant_horas5 = cant_horas5;
    }

    
    
    
    

}
