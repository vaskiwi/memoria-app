package managedbeans;

import entities.Asignatura;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.AsignaturaFacadeLocal;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import javax.faces.application.FacesMessage;
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

@Named("asignaturaController")
@SessionScoped
public class AsignaturaController implements Serializable {

    @EJB
    private AsignaturaFacadeLocal ejbFacade;
    private List<Asignatura> items = null;
    private List<Asignatura> itemsSortNivel = null;
    private List<Asignatura> itemsSortCodigo = null;
    private List<Asignatura> itemsSortCont = null;
    private List<Asignatura> itemsSortArea = null;
    private List<Asignatura> itemsSortHorasPresenciales = null;
    private List<Asignatura> itemsSortHorasNoPresenciales = null;
    private List<Asignatura> itemsSortCreditos = null;
    private List<Asignatura> itemsSortCarrera = null;
    private List<Asignatura> itemsSortJornada = null;
    private List<Asignatura> itemsSortInfo = null;
    private Asignatura selected;
    private List<Asignatura> lista_nombres = null;
    

    public AsignaturaController() {
    }

    public Asignatura getSelected() {
        return selected;
    }

    public void setSelected(Asignatura selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AsignaturaFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Asignatura prepareCreate() {
        selected = new Asignatura();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        FacesContext context = FacesContext.getCurrentInstance();
        lista_nombres = ejbFacade.findByCodigo(selected.getCodigo());
        if(lista_nombres.isEmpty()){
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AsignaturaCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }
        else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Asignatura ya existe", null));
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AsignaturaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AsignaturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Asignatura> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        Collections.sort(items, new Comparator<Asignatura>(){
            @Override
            public int compare(Asignatura o1, Asignatura o2){
               return o1.getNombre_asignatura().compareTo(o2.getNombre_asignatura());
            }
         });
        return items;
    }

    public List<Asignatura> getItemsSortNivel() {
        itemsSortNivel = getFacade().findAll();
        Collections.sort(itemsSortNivel, new Comparator<Asignatura>(){
            @Override
            public int compare(Asignatura o1, Asignatura o2){
               return o1.getSemestre() - o2.getSemestre();
            }
         });
        return itemsSortNivel;
    }

    public void setItemsSortNivel(List<Asignatura> itemsSortNivel) {
        this.itemsSortNivel = itemsSortNivel;
    }

    public List<Asignatura> getItemsSortCodigo() {
        itemsSortCodigo = getFacade().findAll();
        Collections.sort(itemsSortCodigo, new Comparator<Asignatura>(){
            @Override
            public int compare(Asignatura o1, Asignatura o2){
               return o1.getCodigo() - o2.getCodigo();
            }
         });
        return itemsSortCodigo;
    }

    public void setItemsSortCodigo(List<Asignatura> itemsSortCodigo) {
        this.itemsSortCodigo = itemsSortCodigo;
    }

    public List<Asignatura> getItemsSortCont() {
        itemsSortCont = getFacade().findAll();
        Collections.sort(itemsSortCont, new Comparator<Asignatura>(){
            @Override
            public int compare(Asignatura o1, Asignatura o2){
               return o1.getContr_perfil_egreso() - o2.getContr_perfil_egreso();
            }
         });
        return itemsSortCont;
    }

    public void setItemsSortCont(List<Asignatura> itemsSortCont) {
        this.itemsSortCont = itemsSortCont;
    }

    public List<Asignatura> getItemsSortArea() {
        if (itemsSortArea == null) {
            itemsSortArea = getFacade().findAll();
        }
        Collections.sort(itemsSortArea, new Comparator<Asignatura>(){
            @Override
            public int compare(Asignatura o1, Asignatura o2){
               return o1.getTipo_asignatura().getNombre_tipo_asignatura().compareTo(o2.getTipo_asignatura().getNombre_tipo_asignatura());
            }
         });
        return itemsSortArea;
    }

    public void setItemsSortArea(List<Asignatura> itemsSortArea) {
        this.itemsSortArea = itemsSortArea;
    }

    public List<Asignatura> getItemsSortCarrera() {
        if (itemsSortCarrera == null) {
            itemsSortCarrera = getFacade().findAll();
        }
        Collections.sort(itemsSortCarrera, new Comparator<Asignatura>(){
            @Override
            public int compare(Asignatura o1, Asignatura o2){
               return o1.getCarrera().getNombre_carrera().compareTo(o2.getCarrera().getNombre_carrera());
            }
         });
        return itemsSortCarrera;
    }

    public void setItemsSortCarrera(List<Asignatura> itemsSortCarrera) {
        this.itemsSortCarrera = itemsSortCarrera;
    }
    
    

    public List<Asignatura> getItemsSortHorasPresenciales() {
        itemsSortHorasPresenciales = getFacade().findAll();
        Collections.sort(itemsSortHorasPresenciales, new Comparator<Asignatura>(){
            @Override
            public int compare(Asignatura o1, Asignatura o2){
               return Float.compare(o1.getCant_horas_presenciales(),o2.getCant_horas_presenciales());
            }
         });
        return itemsSortHorasPresenciales;
    }

    public void setItemsSortHorasPresenciales(List<Asignatura> itemsSortHorasPresenciales) {
        this.itemsSortHorasPresenciales = itemsSortHorasPresenciales;
    }

    public List<Asignatura> getItemsSortHorasNoPresenciales() {
        itemsSortHorasNoPresenciales = getFacade().findAll();
        Collections.sort(itemsSortHorasNoPresenciales, new Comparator<Asignatura>(){
            @Override
            public int compare(Asignatura o1, Asignatura o2){
               return Float.compare(o1.getCant_horas_no_presenciales(),o2.getCant_horas_no_presenciales());
            }
         });
        return itemsSortHorasNoPresenciales;
    }

    public void setItemsSortHorasNoPresenciales(List<Asignatura> itemsSortHorasNoPresenciales) {
        this.itemsSortHorasNoPresenciales = itemsSortHorasNoPresenciales;
    }

    public List<Asignatura> getItemsSortCreditos() {
        itemsSortCreditos = getFacade().findAll();
        Collections.sort(itemsSortCreditos, new Comparator<Asignatura>(){
            @Override
            public int compare(Asignatura o1, Asignatura o2){
               return o1.getCreditos_asignatura() - o2.getCreditos_asignatura();
            }
         });
        return itemsSortCreditos;
    }

    public void setItemsSortCreditos(List<Asignatura> itemsSortCreditos) {
        this.itemsSortCreditos = itemsSortCreditos;
    }

    public List<Asignatura> getItemsSortJornada() {
        if (itemsSortJornada == null) {
            itemsSortJornada = getFacade().findAll();
        }
        Collections.sort(itemsSortJornada, new Comparator<Asignatura>(){
            @Override
            public int compare(Asignatura o1, Asignatura o2){
               return o1.getJornada_asignatura().compareTo(o2.getJornada_asignatura());
            }
         });
        return itemsSortJornada;
    }

    public void setItemsSortJornada(List<Asignatura> itemsSortJornada) {
        this.itemsSortJornada = itemsSortJornada;
    }

    public List<Asignatura> getItemsSortInfo() {
        itemsSortInfo = getFacade().findAll();
        Collections.sort(itemsSortInfo, new Comparator<Asignatura>(){
            @Override
            public int compare(Asignatura o1, Asignatura o2){
                return Boolean.compare(o1.isInformacion_completa_asignatura(),o2.isInformacion_completa_asignatura());
            }
         });
        return itemsSortInfo;
    }

    public void setItemsSortInfo(List<Asignatura> itemsSortInfo) {
        this.itemsSortInfo = itemsSortInfo;
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

    public Asignatura getAsignatura(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Asignatura> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Asignatura> getItemsAvailableSelectOne() {
        List<Asignatura> itemsAvailable=getFacade().findByDisponible(true);
        Collections.sort(itemsAvailable, new Comparator<Asignatura>(){
            @Override
            public int compare(Asignatura o1, Asignatura o2){
               return o1.getNombre_asignatura().compareTo(o2.getNombre_asignatura());
            }
         });
        return itemsAvailable;
    }

    @FacesConverter(forClass = Asignatura.class)
    public static class AsignaturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AsignaturaController controller = (AsignaturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "asignaturaController");
            return controller.getAsignatura(getKey(value));
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
            if (object instanceof Asignatura) {
                Asignatura o = (Asignatura) object;
                return getStringKey(o.getId_asignatura());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Asignatura.class.getName()});
                return null;
            }
        }

    }

}
