/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.RolFacadeLocal;
import com.sv.udb.modelo.Rol;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author aleso
 */
@Named(value = "rolBean")
@ViewScoped
@ManagedBean
public class RolBean implements Serializable {

    @EJB
    private RolFacadeLocal FCDERoles;
   
    private List<Rol> listRole;
    private Rol objeRole;
    private boolean guardar;
    
    public RolFacadeLocal getFCDERoles() {
        return FCDERoles;
    }

    public void setFCDERoles(RolFacadeLocal FCDERoles) {
        this.FCDERoles = FCDERoles;
    }

    public List<Rol> getListRole() {
        return listRole;
    }

    public void setListRole(List<Rol> listRole) {
        this.listRole = listRole;
    }

    public Rol getObjeRole() {
        return objeRole;
    }

    public void setObjeRole(Rol objeRole) {
        this.objeRole = objeRole;
    }

    public boolean isGuardar() {
        return guardar;
    }
    
    public RolBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.listRole = FCDERoles.findAll();
        this.limpForm();
    }
    
    public void limpForm()
    {
        this.objeRole = new Rol();
        this.guardar = true;        
    }
   
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDERoles.create(this.objeRole);
            this.listRole.add(this.objeRole);
            this.limpForm();
            this.guardar = false;
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
            this.listRole.remove(this.objeRole); //Limpia el objeto viejo
            FCDERoles.edit(this.objeRole);
            this.listRole.add(this.objeRole); //Agrega el objeto modificado
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
    
    public void consTodo()
    {
        try
        {
            this.listRole = FCDERoles.findAll();
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
            this.objeRole = FCDERoles.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " +this.objeRole.getNombRole()+ "')");
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
