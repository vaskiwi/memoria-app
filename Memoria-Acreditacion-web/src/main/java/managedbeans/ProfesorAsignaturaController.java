/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.ProfesorAsignatura;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import managedbeans.util.JsfUtil;
import sessionbeans.ProfesorAsignaturaFacadeLocal;

/**
 *
 * @author Vasco
 */
@Named(value = "profesorAsignaturaController")
@SessionScoped
public class ProfesorAsignaturaController implements Serializable {

    /**
     * Creates a new instance of ProfesorAsignaturaController
     */
    public ProfesorAsignaturaController() {
    }
    @EJB
    private ProfesorAsignaturaFacadeLocal ejbFacade;
    private List<ProfesorAsignatura> items = null;
    private ProfesorAsignatura selected;
    protected void setEmbeddableKeys() {
    }
    protected void initializeEmbeddableKey() {
    }

    public ProfesorAsignaturaFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ProfesorAsignaturaFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<ProfesorAsignatura> getItems() {
        if (items == null) {
            items = getEjbFacade().findAll();
        }
        return items;
    }

    public void setItems(List<ProfesorAsignatura> items) {
        this.items = items;
    }

    public ProfesorAsignatura getSelected() {
        return selected;
    }

    public void setSelected(ProfesorAsignatura selected) {
        this.selected = selected;
    }
    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getEjbFacade().edit(selected);
                } else {
                    getEjbFacade().remove(selected);
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
    public ProfesorAsignatura prepareCreate() {
        selected = new ProfesorAsignatura();
        initializeEmbeddableKey();
        return selected;
    }
    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorAsignaturaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorAsignaturaUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProfesorAsignaturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null;
            items = null;
        }
    }
    public ProfesorAsignatura getProfesorAsignatura(java.lang.Long id) {
        return getEjbFacade().find(id);
    }

    public List<ProfesorAsignatura> getItemsAvailableSelectMany() {
        return getEjbFacade().findAll();
    }

    public List<ProfesorAsignatura> getItemsAvailableSelectOne() {
        return getEjbFacade().findAll();
    }
    @FacesConverter(forClass = ProfesorAsignatura.class)
    public static class ProfesorAsignaturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProfesorAsignaturaController controller = (ProfesorAsignaturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "profesorAsignaturaController");
            return controller.getProfesorAsignatura(getKey(value));
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
            if (object instanceof ProfesorAsignatura) {
                ProfesorAsignatura o = (ProfesorAsignatura) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProfesorAsignatura.class.getName()});
                return null;
            }
        }

    }
}
