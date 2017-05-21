/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Contrato;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.ContratoFacadeLocal;

/**
 *
 * @author Vasco
 */
@Named(value = "contratoController")
@SessionScoped
public class ContratoController implements Serializable {

    /**
     * Creates a new instance of ContratoController
     */
    public ContratoController() {
    }
    
    @EJB
    private ContratoFacadeLocal ejbFacade;
    private List<Contrato> items = null;
    private Contrato selected;
    protected void setEmbeddableKeys() {
    }
    protected void initializeEmbeddableKey() {
    }
    public Contrato prepareCreate() {
        selected = new Contrato();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        FacesContext context = FacesContext.getCurrentInstance();
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ContratoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ContratoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ContratoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Contrato> getItems() {
        if (items == null) {
            items = getEjbFacade().findAll();
        }
        return items;
    }

    public ContratoFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ContratoFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Contrato getSelected() {
        return selected;
    }

    public void setSelected(Contrato selected) {
        this.selected = selected;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
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

    public Contrato getContrato(java.lang.Long id) {
        return getEjbFacade().find(id);
    }

    public List<Contrato> getItemsAvailableSelectMany() {
        return getEjbFacade().findAll();
    }

    public List<Contrato> getItemsAvailableSelectOne() {
        return getEjbFacade().findAll();
    }

    @FacesConverter(forClass = Contrato.class)
    public static class ContratoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContratoController controller = (ContratoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contratoController");
            return controller.getContrato(getKey(value));
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
            if (object instanceof Contrato) {
                Contrato o = (Contrato) object;
                return getStringKey(o.getId_contrato());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Contrato.class.getName()});
                return null;
            }
        }

    }

}
