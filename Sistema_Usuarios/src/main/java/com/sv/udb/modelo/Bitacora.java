/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "bitacora", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b"),
    @NamedQuery(name = "Bitacora.findByCodiBita", query = "SELECT b FROM Bitacora b WHERE b.codiBita = :codiBita"),
    @NamedQuery(name = "Bitacora.findByCodiUsua", query = "SELECT b FROM Bitacora b WHERE b.codiUsua = :codiUsua"),
    @NamedQuery(name = "Bitacora.findByNombBita", query = "SELECT b FROM Bitacora b WHERE b.nombBita = :nombBita"),
    @NamedQuery(name = "Bitacora.findByAcciBita", query = "SELECT b FROM Bitacora b WHERE b.acciBita = :acciBita"),
    @NamedQuery(name = "Bitacora.findByFechBita", query = "SELECT b FROM Bitacora b WHERE b.fechBita = :fechBita"),
    @NamedQuery(name = "Bitacora.findByRegiBita", query = "SELECT b FROM Bitacora b WHERE b.regiBita = :regiBita")})
public class Bitacora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_bita")
    private Integer codiBita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codi_usua")
    private int codiUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomb_bita")
    private String nombBita;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "acci_bita")
    private String acciBita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_bita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechBita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "regi_bita")
    private int regiBita;

    public Bitacora() {
    }

    public Bitacora(Integer codiBita) {
        this.codiBita = codiBita;
    }

    public Bitacora(Integer codiBita, int codiUsua, String nombBita, String acciBita, Date fechBita, int regiBita) {
        this.codiBita = codiBita;
        this.codiUsua = codiUsua;
        this.nombBita = nombBita;
        this.acciBita = acciBita;
        this.fechBita = fechBita;
        this.regiBita = regiBita;
    }

    public Integer getCodiBita() {
        return codiBita;
    }

    public void setCodiBita(Integer codiBita) {
        this.codiBita = codiBita;
    }

    public int getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(int codiUsua) {
        this.codiUsua = codiUsua;
    }

    public String getNombBita() {
        return nombBita;
    }

    public void setNombBita(String nombBita) {
        this.nombBita = nombBita;
    }

    public String getAcciBita() {
        return acciBita;
    }

    public void setAcciBita(String acciBita) {
        this.acciBita = acciBita;
    }

    public Date getFechBita() {
        return fechBita;
    }

    public void setFechBita(Date fechBita) {
        this.fechBita = fechBita;
    }

    public int getRegiBita() {
        return regiBita;
    }

    public void setRegiBita(int regiBita) {
        this.regiBita = regiBita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiBita != null ? codiBita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        if ((this.codiBita == null && other.codiBita != null) || (this.codiBita != null && !this.codiBita.equals(other.codiBita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Bitacora[ codiBita=" + codiBita + " ]";
    }
    
}
