/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.ejb.PermisoFacadeLocal;
import com.sv.udb.modelo.Permiso;
import com.sv.udb.utils.LOG4J;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 * Esta clase se encarga de manejar lo relacionado al CRUD de Permisos
 * @author: AGAV Team
 * @version: Prototipo 1
 */
@Named(value = "permisoBean")
@ManagedBean
@ViewScoped
public class PermisoBean implements Serializable {
    //Campos de la clase
    @EJB
    private PermisoFacadeLocal FCDEPermiso;    
    private Permiso objePerm;
    private Permiso objeVali;
    private List<Permiso> listPerm;
    private boolean guardar;
    private List<String> valores;
    private LOG4J log;
    
    //Encapsulamiento de los campos de la clas
    
    public Permiso getObjePerm() {
        return objePerm;
    }

    public void setObjePerm(Permiso objePerm) {
        this.objePerm = objePerm;
    }

    public List<Permiso> getListPerm() {
        return listPerm;
    }

    public void setListPerm(List<Permiso> listPerm) {
        this.listPerm = listPerm;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public List<String> getValores() {
        return valores;
    }

    public void setValores(List<String> valores) {
        this.valores = valores;
    }

   /**
     * Constructor de la clase Permiso
     */
    public PermisoBean() {
    }
    
    /**
     * Método que se ejecuta después de la construcción del Bean e inicializa las variables
     */
    @PostConstruct
    public void init()
    {
        this.valores = new ArrayList<>();
        this.limpForm();
        this.consTodo();
        log = new LOG4J();
        log.debug("Se inicializa el modelo de Permiso");
    }
    
    /**
     * Método que limpia el formulario reiniciando las variables
     */
    public void limpForm()
    {
        this.objePerm = new Permiso();
        this.guardar = true;  
        this.preSeleChkb(0);
    }
   
    /**
    * Método que lee el arrayList donde se guardan los valores de los permisos y los hace un solo número
    * @return el valor del permiso que se guardará en la base de datos
    */
    public int getValoSuma()
    {
        int suma = 0;
        for(String val: valores)
        {
           suma += Integer.parseInt(val);
        }
        return suma;
    }
    
    /**
     * Método que define el valor a "checked" de los checkbox al consultar permisos de un rol
     * @param valor número entre 1 y 7 que define el valor de los permisos del rol 
     */
    public void preSeleChkb(int valor)
    {
        if(!this.valores.isEmpty())
        {
            this.valores.clear();
        }
        switch(valor)
        {
            case 1:
                this.valores.add("1");
                break;
            case 2:
                this.valores.add("2");
                break;
            case 3:
                this.valores.add("1");
                this.valores.add("2");
                break;
            case 4:
                this.valores.add("4");
                break;
            case 5:
                this.valores.add("1");
                this.valores.add("4");
                break;
            case 6:
                this.valores.add("2");
                this.valores.add("4");
                break;
            case 7:
                this.valores.add("1");
                this.valores.add("2");
                this.valores.add("4");
                break;
        }
    }
    
    /**
    * Método que valida si ya existe un registro ingresado
    * @return el valor de true = si no hay registros duplicados y false = si hay registros duplicados para posteriormente mostrar un error
    */
    public boolean valiUsuaRole()
    {
        boolean resp = true;
        this.objeVali = FCDEPermiso.findByRolePagi(this.objePerm.getCodiRole(), this.objePerm.getCodiPagi());
        if(this.objeVali != null)
            resp = (this.objeVali.getCodiPerm()== this.objePerm.getCodiPerm()) ? true : false;
        return resp;
    }
    
     /**
     * Método que guarda la información en la base datos
     */
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            if(valiUsuaRole())
            {
                this.objePerm.setValoPerm(getValoSuma());
                FCDEPermiso.create(this.objePerm);
                this.listPerm.add(this.objePerm);
                log.info("Permiso creado: "+this.objePerm.getCodiRole().getNombRole()+" - "+this.objePerm.getValoPerm());
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            }
            else
            {
                ctx.execute("setMessage('MESS_WARN', 'Atención', 'Datos ya registrados')"); 
            }
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            log.error("Error creando Permiso: "+getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }
    
    /**
     * Método que modifica la información en la base datos
     */
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            if(valiUsuaRole())
            {
                this.objePerm.setValoPerm(getValoSuma());
                this.listPerm.remove(this.objePerm); //Limpia el objeto viejo
                FCDEPermiso.edit(this.objePerm);
                this.listPerm.add(this.objePerm); //Agrega el objeto modificado
                log.info("Permiso modificado: "+this.objePerm.getCodiPerm());
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            }
            else
            {
                ctx.execute("setMessage('MESS_WARN', 'Atención', 'Datos ya registrados')"); 
            }
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
            log.error("Error modificando Permiso: "+getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }
    
    /**
     * Método que consulta la información de la base datos
     */
    public void consTodo()
    {
        try
        {
            this.listPerm = FCDEPermiso.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }    
    
    /**
     * Método que consulta la información de un registro específico de la base datos
     */
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiObjePara"));
        try
        {
            this.objePerm = FCDEPermiso.find(codi);
            this.preSeleChkb(this.objePerm.getValoPerm());
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
        finally
        {
            
        }
    }
    
}
