package managedbeans;

import entities.Rol;
import entities.Usuario;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
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
import sessionbeans.RolFacadeLocal;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private RolFacadeLocal rolFacade;  
    @EJB
    private UsuarioFacadeLocal ejbFacade;
    private List<Usuario> items = null;
    private Usuario selected;
    private Usuario antiguovalor;
    private List<Usuario> todos = null;
    private List<Rol> todosroles = null;
    private List<Usuario> solohabilitados = null;
    private String rolNombre;
    private String old_password = "";
    private String new_password = "";

    public UsuarioController() {
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacadeLocal getFacade() {
        return ejbFacade;
    }

    public RolFacadeLocal getRolFacade() {
        return rolFacade;
    }

    public void setRolFacade(RolFacadeLocal rolFacade) {
        this.rolFacade = rolFacade;
    }

    public UsuarioFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UsuarioFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Usuario getAntiguovalor() {
        return antiguovalor;
    }

    public void setAntiguovalor(Usuario antiguovalor) {
        this.antiguovalor = antiguovalor;
    }

    public List<Usuario> getTodos() {
        return todos;
    }

    public void setTodos(List<Usuario> todos) {
        this.todos = todos;
    }

    public List<Rol> getTodosroles() {
        if (todosroles == null) {
            todosroles = rolFacade.findAll();
        }
        return todosroles;
    }

    public void setTodosroles(List<Rol> todosroles) {
        this.todosroles = todosroles;
    }

    public List<Usuario> getSolohabilitados() {
        return solohabilitados;
    }

    public void setSolohabilitados(List<Usuario> solohabilitados) {
        this.solohabilitados = solohabilitados;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
        selected.setRol(rolFacade.find(rolNombre));
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<Usuario> getAllItems() {
        todos = getFacade().findAll();
        if (todos == null) {
            todos = new ArrayList<Usuario>();
        }
        return todos;
    }
    
    public Usuario encontrarUsuarioPorCorreo(String correo) {
        getAllItems();//todos los items
        for (Usuario item : todos) {//para cada item de Preingreso de la bd
            if (correo.equals(item.getNombreUsuario())) {//si el objeto a comparar es igual al rut de entrada
                setSelected(item);
                return item;//se retorna
            }
        }
        return null;//si no se encuentra retorna nulo
    }
    
    public String encontrarUsuarioPorCorreoRol(String correo) {
        getAllItems();//todos los items
        for (Usuario item : todos) {//para cada item de Preingreso de la bd
            if (correo.equals(item.getNombreUsuario())) {//si el objeto a comparar es igual al rut de entrada
                return item.getRol().getNombre();//se retorna
            }
        }
        return null;//si no se encuentra retorna nulo
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
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

    public Usuario getUsuario(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getRutUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

}
