/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.ejb.PaginaFacadeLocal;
import com.sv.udb.modelo.Pagina;
import com.sv.udb.utils.LOG4J;
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
 * Esta clase se encarga de manejar lo relacionado al CRUD de páginas
 * @author: AGAV Team
 * @version: Prototipo 1
 */

@Named(value = "paginaBean")
@ViewScoped
@ManagedBean
public class PaginaBean implements Serializable {
    //Campos de la clase
    @EJB
    private PaginaFacadeLocal FCDEPaginas;
    
   
    private List<Pagina> listPagi;
    private Pagina objePagi;
    private boolean guardar;
    private LOG4J log;

    //Encapsulamiento de los campos de la clase
    
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
    
    //Constructor de la clase
    public PaginaBean() {
    }
    
    /**
     * Método que se ejecuta después de haber construido la clase e inicializa las variables
     */
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
        log = new LOG4J();
        log.debug("Se inicializa el modelo de Pagina");
    }
    
    /**
     * Método que limpia el formulario reiniciando las variables
     */
    public void limpForm()
    {
        this.objePagi = new Pagina();
        this.guardar = true;        
    }
    
    /**
     * Método que guarda la información en la base datos
     */
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEPaginas.create(this.objePagi);
            this.listPagi.add(this.objePagi);
            this.guardar = false;
            log.info("Pagina creada: "+this.objePagi.getNombPagi());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            log.error("Error creando pagina: "+getRootCause(ex).getMessage());
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
            this.listPagi.remove(this.objePagi); //Limpia el objeto viejo
            FCDEPaginas.edit(this.objePagi);
            this.listPagi.add(this.objePagi); //Agrega el objeto modificado
            log.info("Pagina modificada: "+this.objePagi.getCodiPagi());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
            log.error("Error modificando pagina: "+getRootCause(ex).getMessage());
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
    
    /**
     * Método que guarda la información de un registro específico de la base datos
     */
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
