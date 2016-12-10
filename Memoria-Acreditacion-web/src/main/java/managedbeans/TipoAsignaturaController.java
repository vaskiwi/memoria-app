/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.TipoAsignatura;
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
import sessionbeans.TipoAsignaturaFacadeLocal;

/**
 *
 * @author Vasco
 */
@Named(value = "tipoAsignaturaController")
@SessionScoped
public class TipoAsignaturaController implements Serializable {

    /**
     * Creates a new instance of TipoAsignaturaController
     */
    @EJB
    private TipoAsignaturaFacadeLocal ejbFacade;
    private List<TipoAsignatura> items = null;
    private TipoAsignatura selected;
    public TipoAsignaturaController() {
    }

    public TipoAsignaturaFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(TipoAsignaturaFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public void setItems(List<TipoAsignatura> items) {
        this.items = items;
    }

    public TipoAsignatura getSelected() {
        return selected;
    }

    public void setSelected(TipoAsignatura selected) {
        this.selected = selected;
    }
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    public TipoAsignatura prepareCreate() {
        selected = new TipoAsignatura();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TipoAsignaturaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TipoAsignaturaUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TipoAsignaturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TipoAsignatura> getItems() {
        if (items == null) {
            items = getEjbFacade().findAll();
        }
        return items;
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

    public TipoAsignatura getTipoAsignatura(java.lang.Long id) {
        return getEjbFacade().find(id);
    }

    public List<TipoAsignatura> getItemsAvailableSelectMany() {
        return getEjbFacade().findAll();
    }

    public List<TipoAsignatura> getItemsAvailableSelectOne() {
        return getEjbFacade().findAll();
    }

    @FacesConverter(forClass = TipoAsignatura.class)
    public static class TipoAsignaturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoAsignaturaController controller = (TipoAsignaturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoAsignaturaController");
            return controller.getTipoAsignatura(getKey(value));
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
            if (object instanceof TipoAsignatura) {
                TipoAsignatura o = (TipoAsignatura) object;
                return getStringKey(o.getId_tipo_asignatura());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoAsignatura.class.getName()});
                return null;
            }
        }

    }
    
}
