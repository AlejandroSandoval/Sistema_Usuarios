/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "pagina", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagina.findAll", query = "SELECT p FROM Pagina p"),
    @NamedQuery(name = "Pagina.findByCodiPagi", query = "SELECT p FROM Pagina p WHERE p.codiPagi = :codiPagi"),
    @NamedQuery(name = "Pagina.findByNombPagi", query = "SELECT p FROM Pagina p WHERE p.nombPagi = :nombPagi"),
    @NamedQuery(name = "Pagina.findByDescPagi", query = "SELECT p FROM Pagina p WHERE p.descPagi = :descPagi"),
    @NamedQuery(name = "Pagina.findByEstaPagi", query = "SELECT p FROM Pagina p WHERE p.estaPagi = :estaPagi")})
public class Pagina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_pagi")
    private Integer codiPagi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nomb_pagi")
    private String nombPagi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "desc_pagi")
    private String descPagi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_pagi")
    private int estaPagi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiPagi", fetch = FetchType.LAZY)
    private List<Permiso> permisoList;

    public Pagina() {
    }

    public Pagina(Integer codiPagi) {
        this.codiPagi = codiPagi;
    }

    public Pagina(Integer codiPagi, String nombPagi, String descPagi, int estaPagi) {
        this.codiPagi = codiPagi;
        this.nombPagi = nombPagi;
        this.descPagi = descPagi;
        this.estaPagi = estaPagi;
    }

    public Integer getCodiPagi() {
        return codiPagi;
    }

    public void setCodiPagi(Integer codiPagi) {
        this.codiPagi = codiPagi;
    }

    public String getNombPagi() {
        return nombPagi;
    }

    public void setNombPagi(String nombPagi) {
        this.nombPagi = nombPagi;
    }

    public String getDescPagi() {
        return descPagi;
    }

    public void setDescPagi(String descPagi) {
        this.descPagi = descPagi;
    }

    public int getEstaPagi() {
        return estaPagi;
    }

    public void setEstaPagi(int estaPagi) {
        this.estaPagi = estaPagi;
    }

    @XmlTransient
    public List<Permiso> getPermisoList() {
        return permisoList;
    }

    public void setPermisoList(List<Permiso> permisoList) {
        this.permisoList = permisoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPagi != null ? codiPagi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagina)) {
            return false;
        }
        Pagina other = (Pagina) object;
        if ((this.codiPagi == null && other.codiPagi != null) || (this.codiPagi != null && !this.codiPagi.equals(other.codiPagi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Pagina[ codiPagi=" + codiPagi + " ]";
    }
    
}
