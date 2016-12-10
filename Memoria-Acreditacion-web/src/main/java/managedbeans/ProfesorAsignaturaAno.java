/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Vasco
 */
@Named(value = "profesorAsignaturaAno")
@SessionScoped
public class ProfesorAsignaturaAno implements Serializable {

    /**
     * Creates a new instance of ProfesorAsignaturaAno
     */
    public ProfesorAsignaturaAno() {
    }
    private List<String> todos_cursos;
    private List<String> cursos_ano1;
    private List<String> cursos_ano2;
    private List<String> cursos_ano3;
    private List<String> cursos_ano4;
    private List<String> cursos_ano5;

    public List<String> getTodos_cursos() {
        return todos_cursos;
    }

    public void setTodos_cursos(List<String> todos_cursos) {
        this.todos_cursos = todos_cursos;
    }

    public List<String> getCursos_ano1() {
        return cursos_ano1;
    }

    public void setCursos_ano1(List<String> cursos_ano1) {
        this.cursos_ano1 = cursos_ano1;
    }

    public List<String> getCursos_ano2() {
        return cursos_ano2;
    }

    public void setCursos_ano2(List<String> cursos_ano2) {
        this.cursos_ano2 = cursos_ano2;
    }

    public List<String> getCursos_ano3() {
        return cursos_ano3;
    }

    public void setCursos_ano3(List<String> cursos_ano3) {
        this.cursos_ano3 = cursos_ano3;
    }

    public List<String> getCursos_ano4() {
        return cursos_ano4;
    }

    public void setCursos_ano4(List<String> cursos_ano4) {
        this.cursos_ano4 = cursos_ano4;
    }

    public List<String> getCursos_ano5() {
        return cursos_ano5;
    }

    public void setCursos_ano5(List<String> cursos_ano5) {
        this.cursos_ano5 = cursos_ano5;
    }
    
}
