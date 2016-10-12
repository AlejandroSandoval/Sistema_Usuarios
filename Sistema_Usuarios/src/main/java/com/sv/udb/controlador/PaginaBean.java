/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.PaginaFacadeLocal;
import com.sv.udb.modelo.Pagina;
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
@Named(value = "paginaBean")
@ViewScoped
@ManagedBean
public class PaginaBean implements Serializable {

    @EJB
    private PaginaFacadeLocal FCDEPaginas;
    
   
    private List<Pagina> listPagi;
    private Pagina objePagi;
    private boolean guardar;

    public PaginaFacadeLocal getFCDEPaginas() {
        return FCDEPaginas;
    }

    public void setFCDEPaginas(PaginaFacadeLocal FCDEPaginas) {
        this.FCDEPaginas = FCDEPaginas;
    }

    public List<Pagina> getListPagi() {
        return listPagi;
    }

    public void setListPagi(List<Pagina> listPagi) {
        this.listPagi = listPagi;
    }

    public Pagina getObjePagi() {
        return objePagi;
    }

    public void setObjePagi(Pagina objePagi) {
        this.objePagi = objePagi;
    }

    public boolean isGuardar() {
        return guardar;
    }
    
    public PaginaBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objePagi = new Pagina();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEPaginas.create(this.objePagi);
            this.listPagi.add(this.objePagi);
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
            this.listPagi.remove(this.objePagi); //Limpia el objeto viejo
            FCDEPaginas.edit(this.objePagi);
            this.listPagi.add(this.objePagi); //Agrega el objeto modificado
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
            this.listPagi = FCDEPaginas.findAll();
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
            this.objePagi = FCDEPaginas.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " +this.objePagi.getNombPagi()+ "')");
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
