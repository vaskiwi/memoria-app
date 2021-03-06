package managedbeans;

import entities.Rol;
import entities.Usuario;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.UsuarioFacadeLocal;
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
import javax.faces.application.FacesMessage;
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
    private List<Usuario> itemsSortRut = null;
    private List<Usuario> itemsSortActivo = null;
    private List<Usuario> itemsSortRol = null;
    private Usuario selected;
    private Usuario antiguovalor;
    private List<Usuario> todos = null;
    private List<Rol> todosroles = null;
    private List<Usuario> solohabilitados = null;
    private String rolNombre;
    private String old_password = "";
    private String new_password = "";
    private boolean eliminarUsuario;

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
        if(ejbFacade.findByRutUsuario(selected.getRutUsuario())==null){
            if(ejbFacade.findByNombreUsuario(selected.getNombreUsuario())==null){
                persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    items = null;    // Invalidate list of items to trigger re-query.
                }
            }
            else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Existe un usuario con este nombre"));
            } 
        }
        else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Existe un usuario con este rut"));
        }     
    }
    
    public void registrar() {
        selected.setRol(rolFacade.findByNombre("COMITÉ"));
        selected.setActivo(false);
        if(ejbFacade.findByRutUsuario(selected.getRutUsuario())==null){
            if(ejbFacade.findByNombreUsuario(selected.getNombreUsuario())==null){
                persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    items = null;    // Invalidate list of items to trigger re-query.
                }
            }
            else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Existe un usuario con este nombre"));
            } 
        }
        else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Existe un usuario con este rut"));
        }     
    }

    public void update() {
        if (new_password.length() > 0) {
            boolean succes = selected.cambiarPassword(old_password, new_password);

            if (succes) {
                selected.setUser_password(new_password);
                JsfUtil.addSuccessMessage("Contraseña cambiada con éxito");
            } else {
                JsfUtil.addErrorMessage("No se pudo cambiar la contraseña");
            }
        }
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public boolean isEliminarUsuario() {
        List<Rol> roles = rolFacade.findByNombreList("ADMINISTRADOR");
        List<Usuario> administradores = ejbFacade.findByRolActivo(roles.get(0), true);
        if(selected==null){
            eliminarUsuario=true;
            return eliminarUsuario;
        }
        else{
            if(selected.getRol().getNombre().equals("ADMINISTRADOR")){
                if(administradores.size()==1){
                    eliminarUsuario=true;
                    return eliminarUsuario;
                }
            }
        }
        eliminarUsuario=false;
        return eliminarUsuario;
    }

    public void setEliminarUsuario(boolean eliminarUsuario) {
        this.eliminarUsuario = eliminarUsuario;
    }

    public List<Usuario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        Collections.sort(items, new Comparator<Usuario>(){
            @Override
            public int compare(Usuario o1, Usuario o2){
               return o1.getNombreUsuario().compareTo(o2.getNombreUsuario());
            }
         });
        return items;
    }

    public List<Usuario> getItemsSortRut() {
        if (itemsSortRut == null) {
            itemsSortRut = getFacade().findAll();
        }
        Collections.sort(itemsSortRut, new Comparator<Usuario>(){
            @Override
            public int compare(Usuario o1, Usuario o2){
               return o1.getRutUsuario().compareTo(o2.getRutUsuario());
            }
         });
        return itemsSortRut;
    }

    public void setItemsSortRut(List<Usuario> itemsSortRut) {
        this.itemsSortRut = itemsSortRut;
    }

    public List<Usuario> getItemsSortActivo() {
        itemsSortActivo = getFacade().findAll();
        Collections.sort(itemsSortActivo, new Comparator<Usuario>(){
            @Override
            public int compare(Usuario o1, Usuario o2){
                return Boolean.compare(o1.getActivo(),o2.getActivo());
            }
         });
        return itemsSortActivo;
    }

    public void setItemsSortActivo(List<Usuario> itemsSortActivo) {
        this.itemsSortActivo = itemsSortActivo;
    }

    public List<Usuario> getItemsSortRol() {
        if (itemsSortRol == null) {
            itemsSortRol = getFacade().findAll();
        }
        Collections.sort(itemsSortRol, new Comparator<Usuario>(){
            @Override
            public int compare(Usuario o1, Usuario o2){
               return o1.getRol().getNombre().compareTo(o2.getRol().getNombre());
            }
         });
        return itemsSortRol;
    }

    public void setItemsSortRol(List<Usuario> itemsSortRol) {
        this.itemsSortRol = itemsSortRol;
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
