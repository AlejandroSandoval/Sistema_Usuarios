/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.UsuarioRolFacadeLocal;
import com.sv.udb.modelo.UsuarioRol;
import com.sv.udb.utils.Notificacion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author REGISTRO
 */
@Named(value = "usuarioRolBean")
@SessionScoped
public class UsuarioRolBean implements Serializable {

    @EJB
    private UsuarioRolFacadeLocal FCDEUsuaRole;
    
    @Inject
    private GlobalAppBean globalAppBean; //Bean de aplicación
    
    private UsuarioRol objeUsuaRole;
    private List<UsuarioRol> listUsuaRole;
    private List<UsuarioRol> listUsuar;
    private boolean guardar;

    public UsuarioRol getObjeUsuaRole() {
        return objeUsuaRole;
    }

    public void setObjeUsuaRole(UsuarioRol objeUsuaRole) {
        this.objeUsuaRole = objeUsuaRole;
    }

    public List<UsuarioRol> getListUsuaRole() {
        return listUsuaRole;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<UsuarioRol> getListUsuar() {
        return listUsuar;
    }
    
    

    
    public UsuarioRolBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeUsuaRole = new UsuarioRol();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeUsuaRole.setContUsua("prueba");
            FCDEUsuaRole.create(this.objeUsuaRole);
            this.listUsuaRole.add(this.objeUsuaRole);
            this.guardar = false;
            //this.limpForm(); //Omito para mantener los datos en la modal
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
        finally
        {
            
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listUsuaRole.remove(this.objeUsuaRole); //Limpia el objeto viejo
            FCDEUsuaRole.edit(this.objeUsuaRole);
            this.listUsuaRole.add(this.objeUsuaRole); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        finally
        {
            
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeUsuaRole.setEstaUsuaRole(0);
            FCDEUsuaRole.edit(this.objeUsuaRole);
            this.listUsuaRole.remove(this.objeUsuaRole);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listUsuaRole = FCDEUsuaRole.findAll();
            this.listUsuar = FCDEUsuaRole.findByAcceOnly();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiObjePara"));
        try
        {
            this.objeUsuaRole = FCDEUsuaRole.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeUsuaRole.getAcceUsua()) + "')");
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
