/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.ProfesorContrato;
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
import sessionbeans.ProfesorContratoFacadeLocal;

/**
 *
 * @author Vasco
 */
@Named(value = "profesorContratoController")
@SessionScoped
public class ProfesorContratoController implements Serializable {

    /**
     * Creates a new instance of ProfesorContratoController
     */
    public ProfesorContratoController() {
    }
    @EJB
    private ProfesorContratoFacadeLocal ejbFacade;
    private List<ProfesorContrato> items = null;
    private ProfesorContrato selected;
    protected void setEmbeddableKeys() {
    }
    protected void initializeEmbeddableKey() {
    }

    public ProfesorContratoFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ProfesorContratoFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<ProfesorContrato> getItems() {
        if (items == null) {
            items = getEjbFacade().findAll();
        }
        return items;
    }

    public void setItems(List<ProfesorContrato> items) {
        this.items = items;
    }

    public ProfesorContrato getSelected() {
        return selected;
    }

    public void setSelected(ProfesorContrato selected) {
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
    public ProfesorContrato prepareCreate() {
        selected = new ProfesorContrato();
        initializeEmbeddableKey();
        return selected;
    }
    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorContratoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorContratoUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProfesorContratoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null;
            items = null;
        }
    }
    public ProfesorContrato getProfesorContrato(java.lang.Long id) {
        return getEjbFacade().find(id);
    }

    public List<ProfesorContrato> getItemsAvailableSelectMany() {
        return getEjbFacade().findAll();
    }

    public List<ProfesorContrato> getItemsAvailableSelectOne() {
        return getEjbFacade().findAll();
    }
    @FacesConverter(forClass = ProfesorContrato.class)
    public static class ProfesorContratoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProfesorContratoController controller = (ProfesorContratoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "profesorContratoController");
            return controller.getProfesorContrato(getKey(value));
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
            if (object instanceof ProfesorContrato) {
                ProfesorContrato o = (ProfesorContrato) object;
                return getStringKey(o.getId_profesor_contrato());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProfesorContrato.class.getName()});
                return null;
            }
        }

    }
}
