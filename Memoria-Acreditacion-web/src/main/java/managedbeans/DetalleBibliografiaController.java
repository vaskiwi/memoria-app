/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.DetalleBibliografia;
import java.io.Serializable;
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
        return items;
    }

    public void setItems(List<DetalleBibliografia> items) {
        this.items = items;
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
        DetalleBibliografia total = ejbFacade.findById(Long.valueOf(99900));
        total.setCantBibliografiaComplementaria(selected.getCantBibliografiaComplementaria()+total.getCantBibliografiaComplementaria());
        total.setCantBibliografiaObligatoria(selected.getCantBibliografiaObligatoria()+total.getCantBibliografiaObligatoria());
        total.setCantEjemplaresFisicos(selected.getCantEjemplaresFisicos()+total.getCantEjemplaresFisicos());
        total.setCantTitulosFisicos(selected.getCantTitulosFisicos()+total.getCantTitulosFisicos());
        total.setPromAlumnosCurso(selected.getPromAlumnosCurso()+total.getPromAlumnosCurso());
        total.setRelEjemplaresAlumnos(selected.getRelEjemplaresAlumnos()+total.getRelEjemplaresAlumnos());
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
