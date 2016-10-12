/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Permiso;
import com.sv.udb.modelo.UsuarioRol;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
@Stateless
public class PermisoFacade extends AbstractFacade<Permiso> implements PermisoFacadeLocal {

    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisoFacade() {
        super(Permiso.class);
    }
    
    @Override
    public List<Permiso> findByRole(Object role) {
        Query q = getEntityManager().createQuery("SELECT p FROM Permiso p WHERE p.codiRole = :codiRole AND p.estaPerm = :estaPerm", Permiso.class);        
        q.setParameter("codiRole", role);
        q.setParameter("estaPerm", 1);
        List resu = q.getResultList();
        return resu;
    }
    
    @Override
    public Permiso findByRolePagi(Object role, Object pagi) {
        Query q = getEntityManager().createQuery("SELECT p FROM Permiso p WHERE p.codiRole = :codiRole AND p.codiPagi = :codiPagi AND p.estaPerm = :estaPerm", Permiso.class);        
        q.setParameter("codiRole", role);
        q.setParameter("codiPagi", pagi);
        q.setParameter("estaPerm", 1);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : (Permiso)resu.get(0);
    }
}
