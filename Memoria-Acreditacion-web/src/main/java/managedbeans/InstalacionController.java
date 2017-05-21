/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Instalacion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
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
import sessionbeans.InstalacionFacadeLocal;

/**
 *
 * @author Vasco
 */
@Named(value = "instalacionController")
@SessionScoped
public class InstalacionController implements Serializable {

    /**
     * Creates a new instance of InstalacionController
     */
    public InstalacionController() {
    }
    @EJB
    private InstalacionFacadeLocal ejbFacade;
    private List<Instalacion> items = null;
    private Instalacion selected;
    private List<Instalacion> lista_nombres = null;
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public InstalacionFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(InstalacionFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Instalacion> getItems() {
        if (items == null) {
            items = getEjbFacade().findAll();
        }
        Collections.sort(items, new Comparator<Instalacion>(){
            @Override
            public int compare(Instalacion o1, Instalacion o2){
               return o1.getNombre_instalacion().compareTo(o2.getNombre_instalacion());
            }
        });
        return items;
    }

    public void setItems(List<Instalacion> items) {
        this.items = items;
    }

    public Instalacion getSelected() {
        return selected;
    }

    public void setSelected(Instalacion selected) {
        this.selected = selected;
    }

    public List<Instalacion> getLista_nombres() {
        return lista_nombres;
    }

    public void setLista_nombres(List<Instalacion> lista_nombres) {
        this.lista_nombres = lista_nombres;
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
    public Instalacion prepareCreate() {
        selected = new Instalacion();
        initializeEmbeddableKey();
        return selected;
    }
    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("InstalacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("InstalacionUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("InstalacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null;
            items = null;
        }
    }
    public Instalacion getInstalacion(java.lang.Long id) {
        return getEjbFacade().find(id);
    }

    public List<Instalacion> getItemsAvailableSelectMany() {
        return getEjbFacade().findAll();
    }

    public List<Instalacion> getItemsAvailableSelectOne() {
        return getEjbFacade().findAll();
    }
    @FacesConverter(forClass = Instalacion.class)
    public static class InstalacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InstalacionController controller = (InstalacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "instalacionController");
            return controller.getInstalacion(getKey(value));
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
            if (object instanceof Instalacion) {
                Instalacion o = (Instalacion) object;
                return getStringKey(o.getId_instalacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Instalacion.class.getName()});
                return null;
            }
        }

    }
}
