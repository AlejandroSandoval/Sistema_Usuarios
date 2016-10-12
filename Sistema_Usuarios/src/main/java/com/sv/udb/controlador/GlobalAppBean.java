/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import java.io.FileInputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * Esta clase contiene métodos que son utilizados en todo el sistema
 * @author: AGAV Team
 * @version: Prototipo 1
 */
@Named(value = "globalAppBean")
@ApplicationScoped
public class GlobalAppBean {

    /**
     * Constructor de la clase
     */
    public GlobalAppBean() {
    }
    
    /**
     * Método que devuelve la ruta completa de una página dentro sistema
     * @param page el nombre de la página que se quiere encontrar
     * @return la ruta de la ubicación de la página
     */
    public String getUrl(String page)
    {
        String resp;
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        String prefix = facsCtxt.getExternalContext().getInitParameter("prefix");
        resp = String.format("%s/%s/%s", facsCtxt.getExternalContext().getRequestContextPath(), prefix, page);
        return resp;
    }
    
    /**
     * Método que devuelve la ruta completa de un archivo dentro del servidor
     * @param file el nombre del archivo que se quiere encontrar
     * @return la ruta completa de la ubicación del archivo
     */
    public String getResourcePath(String file)
    {
        String resp;      
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        String resoPath = facsCtxt.getExternalContext().getInitParameter("javax.faces.WEBAPP_RESOURCES_DIRECTORY");
        resp = String.format("%s/%s", resoPath, file);
        resp = facsCtxt.getExternalContext().getRealPath(resp);
        return resp;
    }
    
    public StreamedContent getImage(String imag) throws IOException {
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        if (facsCtxt.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            return new DefaultStreamedContent(new FileInputStream(getResourcePath(imag)));
        }
    }
}
