/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "permiso", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p"),
    @NamedQuery(name = "Permiso.findByCodiPerm", query = "SELECT p FROM Permiso p WHERE p.codiPerm = :codiPerm"),
    @NamedQuery(name = "Permiso.findByValoPerm", query = "SELECT p FROM Permiso p WHERE p.valoPerm = :valoPerm"),
    @NamedQuery(name = "Permiso.findByEstaPerm", query = "SELECT p FROM Permiso p WHERE p.estaPerm = :estaPerm")})
public class Permiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_perm")
    private Integer codiPerm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valo_perm")
    private int valoPerm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_perm")
    private int estaPerm;
    @JoinColumn(name = "codi_pagi", referencedColumnName = "codi_pagi")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pagina codiPagi;
    @JoinColumn(name = "codi_role", referencedColumnName = "codi_role")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol codiRole;

    public Permiso() {
    }

    public Permiso(Integer codiPerm) {
        this.codiPerm = codiPerm;
    }

    public Permiso(Integer codiPerm, int valoPerm, int estaPerm) {
        this.codiPerm = codiPerm;
        this.valoPerm = valoPerm;
        this.estaPerm = estaPerm;
    }

    public Integer getCodiPerm() {
        return codiPerm;
    }

    public void setCodiPerm(Integer codiPerm) {
        this.codiPerm = codiPerm;
    }

    public int getValoPerm() {
        return valoPerm;
    }

    public void setValoPerm(int valoPerm) {
        this.valoPerm = valoPerm;
    }

    public int getEstaPerm() {
        return estaPerm;
    }

    public void setEstaPerm(int estaPerm) {
        this.estaPerm = estaPerm;
    }

    public Pagina getCodiPagi() {
        return codiPagi;
    }

    public void setCodiPagi(Pagina codiPagi) {
        this.codiPagi = codiPagi;
    }

    public Rol getCodiRole() {
        return codiRole;
    }

    public void setCodiRole(Rol codiRole) {
        this.codiRole = codiRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPerm != null ? codiPerm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.codiPerm == null && other.codiPerm != null) || (this.codiPerm != null && !this.codiPerm.equals(other.codiPerm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Permiso[ codiPerm=" + codiPerm + " ]";
    }
    
}
