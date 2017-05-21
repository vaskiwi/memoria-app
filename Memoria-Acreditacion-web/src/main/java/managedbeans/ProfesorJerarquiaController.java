/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package managedbeans;

import entities.ProfesorJerarquia;
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
import sessionbeans.ProfesorJerarquiaFacadeLocal;

/**
 *
 * @author Vasco
 */
@Named(value = "profesorJerarquiaController")
@SessionScoped
public class ProfesorJerarquiaController implements Serializable {

    /**
     * Creates a new instance of ProfesorJerarquiaController
     */
    public ProfesorJerarquiaController() {
    }
    @EJB
    private ProfesorJerarquiaFacadeLocal ejbFacade;
    private List<ProfesorJerarquia> items = null;
    private ProfesorJerarquia selected;
    protected void setEmbeddableKeys() {
    }
    protected void initializeEmbeddableKey() {
    }

    public ProfesorJerarquiaFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ProfesorJerarquiaFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<ProfesorJerarquia> getItems() {
        if (items == null) {
            items = getEjbFacade().findAll();
        }
        return items;
    }

    public void setItems(List<ProfesorJerarquia> items) {
        this.items = items;
    }

    public ProfesorJerarquia getSelected() {
        return selected;
    }

    public void setSelected(ProfesorJerarquia selected) {
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
    public ProfesorJerarquia prepareCreate() {
        selected = new ProfesorJerarquia();
        initializeEmbeddableKey();
        return selected;
    }
    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorJerarquiaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorJerarquiaUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProfesorJerarquiaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null;
            items = null;
        }
    }
    public ProfesorJerarquia getProfesorJerarquia(java.lang.Long id) {
        return getEjbFacade().find(id);
    }

    public List<ProfesorJerarquia> getItemsAvailableSelectMany() {
        return getEjbFacade().findAll();
    }

    public List<ProfesorJerarquia> getItemsAvailableSelectOne() {
        return getEjbFacade().findAll();
    }
    @FacesConverter(forClass = ProfesorJerarquia.class)
    public static class ProfesorJerarquiaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProfesorJerarquiaController controller = (ProfesorJerarquiaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "profesorJerarquiaController");
            return controller.getProfesorJerarquia(getKey(value));
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
            if (object instanceof ProfesorJerarquia) {
                ProfesorJerarquia o = (ProfesorJerarquia) object;
                return getStringKey(o.getId_profesor_jerarquia());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProfesorJerarquia.class.getName()});
                return null;
            }
        }

    }
}
