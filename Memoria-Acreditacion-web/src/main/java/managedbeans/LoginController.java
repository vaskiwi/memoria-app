/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Rol;
import sessionbeans.UsuarioFacadeLocal;
import entities.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import managedbeans.util.JsfUtil;
import org.apache.http.HttpResponse;
import sessionbeans.UsuarioFacadeLocal;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.primefaces.json.JSONObject;

/**
 *

 */
@Named(value = "loginController")
//@RequestScoped
@SessionScoped
public class LoginController implements Serializable{

    private String nombre;
    private String password;
    private boolean error = false;
    private String nombre_usuario;
    private List<Rol> roles;
    private String rol;
    private Usuario usuarioLogeado = null;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @Inject
    private UsuarioController userCtrl;

    public UsuarioFacadeLocal getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacadeLocal usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public UsuarioController getUserCtrl() {
        return userCtrl;
    }

    public void setUserCtrl(UsuarioController userCtrl) {
        this.userCtrl = userCtrl;
    }  

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
    
    public Boolean usuarioLogueado(){
        return usuarioLogeado!= null;
    }

    public LoginController(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session != null){
            session.invalidate();
        }
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
    
    public String login() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

            Usuario usuario;
            usuario = userCtrl.encontrarUsuarioPorCorreo(this.nombre);
            //usuario = rut.FindUsuariobyRut(this.username);
            if (usuario == null) {
                context.addMessage(null, new FacesMessage("El usuario no existe"));

                return "/faces/index1.xhtml";

            } else if (usuario.getActivo() == false) {
                context.addMessage(null, new FacesMessage("Usted se encuentra deshabilitado, contáctese con el administrador"));

                return "/faces/index1.xhtml";
            } else {

                if (request.getRemoteUser() == null) {
                    try {
                        request.login(this.nombre, this.password);
                        usuarioLogeado = usuario;
                        context.addMessage(null, new FacesMessage("Usuario autentificado correctamente"));           
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Memoria-Acreditacion-web/faces/index1.xhtml");
                    } catch (ServletException e) {//si request.login fallo y la password o el usuario no corresponden
                        context.addMessage(null, new FacesMessage("El correo y la contraseña ingresados no coinciden"));
                        return "/faces/index1.xhtml";
                    }
                } else {
                    //usuario ya logueado
                    context.addMessage(null, new FacesMessage("Usuario ya autentificado"));
                    return "/faces/index1.xhtml";
                }
                return "/faces/index1.xhtml";
            }

        } catch (Exception e) {
            return "/faces/index1.xhtml";

        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout(); //comprueba en el servidor si la pass y el usuario corresponden
            //la comprobación se hace por parte de Wildfly(se configuraron Roles y rol)
        } catch (ServletException e) {//si request.login fallo y la password o el usuario no corresponden
            context.addMessage(null, new FacesMessage("El correo y la contraseña ingresados no coinciden"));
            return "/faces/index.xhtml";
        }
        return "/faces/index.xhtml";
    }

    /**
     * Devuelve el id del TipoUsuario del usuario que actualmente está logeado
     *
     * @return El id del TipoUsuario
     */
    public String getRol() {
            try {//prueba
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();//obtengo el contexto en el servidor
                nombre = request.getRemoteUser();
                if (request.getRemoteUser() != null) {
                    rol = userCtrl.encontrarUsuarioPorCorreoRol(nombre);
                }

            } catch (Exception e) {//si hay algun error
            }
        return rol;
    }
}
