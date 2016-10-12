/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.PermisoFacadeLocal;
import com.sv.udb.ejb.UsuarioRolFacadeLocal;
import com.sv.udb.modelo.Pagina;
import com.sv.udb.modelo.Permiso;
import com.sv.udb.modelo.Rol;
import com.sv.udb.modelo.UsuarioRol;
import com.sv.udb.utils.Notificacion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author aleso
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private PermisoFacadeLocal FCDEPermiso;

    private static final long serialVersionUID = 5074501358281220977L;

    @EJB
    private UsuarioRolFacadeLocal FCDEUsuaRole;
    
    
    @Inject
    private GlobalAppBean globalAppBean; //Bean de aplicación
    
    private UsuarioRol objeUsua;
    private Pagina objePagi;
    private Permiso objePerm;
    private boolean loge;
    private String usua;
    private String cont;
    private String imagPerf;
    private List<Notificacion> listNoti;//Lista de Notificaciones
    private List<UsuarioRol> listusua;
    private List<Permiso> listperm;
    private List<Permiso> listpermprue;

    public LoginBean() {
    }
    
    @PostConstruct
    public void init()
    {
        
    }

    public UsuarioRol getObjeUsua() {
        return objeUsua;
    }

    public void setObjeUsua(UsuarioRol objeUsua) {
        this.objeUsua = objeUsua;
    }

    public Pagina getObjePagi() {
        return objePagi;
    }

    public void setObjePagi(Pagina objePagi) {
        this.objePagi = objePagi;
    }
    
    public boolean isLoge() {
        return loge;
    }

    public String getUsua() {
        return usua;
    }

    public void setUsua(String usua) {
        this.usua = usua;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getImagPerf() {
        return imagPerf;
    }

    public List<Notificacion> getListNoti() {
        return listNoti;
    }

    public List<Permiso> getListperm() {
        return listperm;
    }

    public void setListpermprue(List<Permiso> listpermprue) {
        this.listpermprue = listpermprue;
    }

    public Permiso getObjePerm() {
        return objePerm;
    }

    public void setObjePerm(Permiso objePerm) {
        this.objePerm = objePerm;
    }
    
    
    
    public void creaSess()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        try
        {
            this.loge = false;
            this.objeUsua = FCDEUsuaRole.findByAcceAndCont(this.usua, this.cont);
            if(this.objeUsua != null)
            {
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Bienvenido)"); //No se muestra porque redirecciona
                this.loge = true;
                //Cargar una imagen de usuario (Puede ser de una BD)
                this.imagPerf = "images/userDemo.png";
                //Llenar lista de notificaciones.... puede salir de la DB
                this.listNoti = new ArrayList<>();
                this.listNoti.add(new Notificacion("Notificación 1", false));
                this.listNoti.add(new Notificacion("Notificación 2", false));
                this.listNoti.add(new Notificacion("Notificación 3", false));
                this.listNoti.add(new Notificacion("Notificación 4", false));
                this.listNoti.add(new Notificacion("Notificación 5", false));
                this.listNoti.add(new Notificacion("Notificación 6", false));
                this.listNoti.add(new Notificacion("Notificación 7", false));
                this.listNoti.add(new Notificacion("Notificación 8", false));
                this.listusua = FCDEUsuaRole.findByAcce(this.usua);
                this.listperm = new ArrayList<>();
                for(UsuarioRol temp : listusua){
                    this.listpermprue = FCDEPermiso.findByRole(temp.getCodiRole());
                    if(listpermprue != null){
                    for(Permiso temp1 : listpermprue){
                        this.listperm.add(temp1);
                    }
                    }
                }
                System.out.println(""+listperm);
                //Redireccionar
                facsCtxt.getExternalContext().redirect(globalAppBean.getUrl("index.xhtml"));
            }
            else
            {
                ctx.execute("setMessage('MESS_WARN', 'Atención', 'Ingreso incorrecto')");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al logear')");
        }
        finally
        {
            
        }
    }
    
    
    
    public void cerrSess()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        try
        {
            facsCtxt.getExternalContext().invalidateSession();
            facsCtxt.getExternalContext().redirect(globalAppBean.getUrl("login.xhtml")); 
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al finalizar la sesión')");
        }
        finally
        {
            
        }       
    }
}
