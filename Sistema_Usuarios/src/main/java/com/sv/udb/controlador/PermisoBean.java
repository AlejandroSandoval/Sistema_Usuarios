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
 *
 * @author aleso
 */
@Named(value = "permisoBean")
@ManagedBean
@ViewScoped
public class PermisoBean implements Serializable {
    @EJB
    private PermisoFacadeLocal FCDEPermiso;    
    private Permiso objePerm;
    private List<Permiso> listPerm;
    private boolean guardar;
    private List<String> valores;
    private LOG4J log;
    
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
     * Creates a new instance of PermisoBean
     */
    public PermisoBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.valores = new ArrayList<>();
        this.limpForm();
        this.consTodo();
        log = new LOG4J();
        log.debug("Se inicializa el modelo de Permiso");
    }
    
    public void limpForm()
    {
        this.objePerm = new Permiso();
        this.guardar = true;  
        this.preSeleChkb(0);
    }
   
    public int getValoSuma()
    {
        int suma = 0;
        for(String val: valores)
        {
           suma += Integer.parseInt(val);
        }
        return suma;
    }
    
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
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objePerm.setValoPerm(getValoSuma());
            FCDEPermiso.create(this.objePerm);
            this.listPerm.add(this.objePerm);
            log.info("Permiso creado: "+this.objePerm.getCodiRole().getNombRole()+" - "+this.objePerm.getValoPerm());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            this.limpForm();
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
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objePerm.setValoPerm(getValoSuma());
            this.listPerm.remove(this.objePerm); //Limpia el objeto viejo
            FCDEPermiso.edit(this.objePerm);
            this.listPerm.add(this.objePerm); //Agrega el objeto modificado
            log.info("Permiso modificado: "+this.objePerm.getCodiPerm());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
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
