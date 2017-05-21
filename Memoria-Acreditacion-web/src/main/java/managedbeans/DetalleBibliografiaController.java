/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.DetalleBibliografia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import managedbeans.util.JsfUtil;
import sessionbeans.AsignaturaFacadeLocal;
import sessionbeans.DetalleBibliografiaFacadeLocal;

/**
 *
 * @author Vasco
 */
@Named(value = "detalleBibliografiaController")
@SessionScoped
public class DetalleBibliografiaController implements Serializable {

    /**
     * Creates a new instance of DetalleBibliografiaController
     */
    @EJB
    private DetalleBibliografiaFacadeLocal ejbFacade;
    private List<DetalleBibliografia> items = null;
    private DetalleBibliografia selected;
    private List<DetalleBibliografia> lista_nombres = null;
    private List<DetalleBibliografiaTotal> items_total = null;
    @EJB
    private AsignaturaFacadeLocal AsignaturaFacade;
    
    public DetalleBibliografiaController() {
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public DetalleBibliografiaFacadeLocal getFacade() {
        return ejbFacade;
    }

    public void setFacade(DetalleBibliografiaFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public AsignaturaFacadeLocal getAsignaturaFacade() {
        return AsignaturaFacade;
    }

    public void setAsignaturaFacade(AsignaturaFacadeLocal AsignaturaFacade) {
        this.AsignaturaFacade = AsignaturaFacade;
    }

    public List<DetalleBibliografia> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        Collections.sort(items, new Comparator<DetalleBibliografia>(){
            @Override
            public int compare(DetalleBibliografia o1, DetalleBibliografia o2){
               return o1.getAsignatura().getNombre_asignatura().compareTo(o2.getAsignatura().getNombre_asignatura());
            }
         });
        return items;
    }

    public void setItems(List<DetalleBibliografia> items) {
        this.items = items;
    }

    public List<DetalleBibliografiaTotal> getItems_total() {
        items_total=new ArrayList<>();
        if (items == null) {
            items = getFacade().findAll();
        }
        Collections.sort(items, new Comparator<DetalleBibliografia>(){
            @Override
            public int compare(DetalleBibliografia o1, DetalleBibliografia o2){
               return o1.getAsignatura().getNombre_asignatura().compareTo(o2.getAsignatura().getNombre_asignatura());
            }
        });
        int cont1=0;
        int cont2=0;
        int cont3=0;
        int cont4=0;
        float cont5=0;
        float cont6=0;
        for (DetalleBibliografia item : items) {
            DetalleBibliografiaTotal d_b = new DetalleBibliografiaTotal();
            cont1+=item.getCantBibliografiaComplementaria();
            cont2+=item.getCantBibliografiaObligatoria();
            cont3+=item.getCantEjemplaresFisicos();
            cont4+=item.getCantTitulosFisicos();
            cont5+=item.getPromAlumnosCurso();
            cont6+=item.getRelEjemplaresAlumnos();
            d_b.setCodigo_asignatura(item.getAsignatura().getCodigo());
            d_b.setAsignatura(item.getAsignatura().getNombre_asignatura());
            d_b.setCantBibliografiaComplementaria(item.getCantBibliografiaComplementaria());
            d_b.setCantBibliografiaObligatoria(item.getCantBibliografiaObligatoria());
            d_b.setCantEjemplaresFisicos(item.getCantEjemplaresFisicos());
            d_b.setCantTitulosFisicos(item.getCantTitulosFisicos());
            d_b.setPromAlumnosCurso(item.getPromAlumnosCurso());
            d_b.setRelEjemplaresAlumnos(item.getRelEjemplaresAlumnos());
            items_total.add(d_b);
        }
        DetalleBibliografiaTotal totales = new DetalleBibliografiaTotal();
        totales.setAsignatura("Total");
        totales.setCantBibliografiaComplementaria(cont1);
        totales.setCantBibliografiaObligatoria(cont2);
        totales.setCantEjemplaresFisicos(cont3);
        totales.setCantTitulosFisicos(cont4);
        totales.setPromAlumnosCurso(cont5);
        totales.setRelEjemplaresAlumnos(cont6);
        items_total.add(totales);
        return items_total;
    }

    public void setItems_total(List<DetalleBibliografiaTotal> items_total) {
        this.items_total = items_total;
    }

    public DetalleBibliografia getSelected() {
        return selected;
    }

    public void setSelected(DetalleBibliografia selected) {
        this.selected = selected;
    }

    public List<DetalleBibliografia> getLista_nombres() {
        return lista_nombres;
    }

    public void setLista_nombres(List<DetalleBibliografia> lista_nombres) {
        this.lista_nombres = lista_nombres;
    }

    public DetalleBibliografiaFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DetalleBibliografiaFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
    
    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }
    public DetalleBibliografia prepareCreate() {
        selected = new DetalleBibliografia();
        initializeEmbeddableKey();
        return selected;
    }
    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DetalleBibliografiaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetalleBibliografiaUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DetalleBibliografiaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null;
            items = null;
        }
    }
    public DetalleBibliografia getDetalleBibliografia(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DetalleBibliografia> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DetalleBibliografia> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    @FacesConverter(forClass = DetalleBibliografia.class)
    public static class DetalleBibliografiaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleBibliografiaController controller = (DetalleBibliografiaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleBibliografiaController");
            return controller.getDetalleBibliografia(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetalleBibliografia) {
                DetalleBibliografia o = (DetalleBibliografia) object;
                return getStringKey(o.getId_detalle_bibliografia());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetalleBibliografia.class.getName()});
                return null;
            }
        }

    }
}
