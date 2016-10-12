/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

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
public class UsuarioRolFacade extends AbstractFacade<UsuarioRol> implements UsuarioRolFacadeLocal {

    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioRolFacade() {
        super(UsuarioRol.class);
    }
    
    @Override
    public UsuarioRol findByAcceAndCont(Object acce, Object cont) {
        Query q = getEntityManager().createQuery("SELECT u FROM UsuarioRol u WHERE u.acceUsua = :acceUsua AND u.contUsua = :contUsua AND u.estaUsuaRole = :estaUsuaRole", UsuarioRol.class);        
        q.setParameter("acceUsua", acce);
        q.setParameter("contUsua", cont);
        q.setParameter("estaUsuaRole", 1);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : (UsuarioRol)resu.get(0);
    }
    
    @Override
    public List<UsuarioRol> findByAcce(Object acce) {
        Query q = getEntityManager().createQuery("SELECT u FROM UsuarioRol u WHERE u.acceUsua = :acceUsua AND u.estaUsuaRole = :estaUsuaRole", UsuarioRol.class);        
        q.setParameter("acceUsua", acce);
        q.setParameter("estaUsuaRole", 1);
        List resu = q.getResultList();
        System.out.println(""+resu);
        return resu;
    }
    
    @Override
    public List<UsuarioRol> findByAcceOnly() {
        Query q = getEntityManager().createQuery("SELECT u FROM UsuarioRol u WHERE u.estaUsuaRole = :estaUsuaRole GROUP BY(u.acceUsua)", UsuarioRol.class);        
        q.setParameter("estaUsuaRole", 1);
        List resu = q.getResultList();
        return resu;
    }
    
    @Override
    public UsuarioRol findByAcceAndRol(Object acce, Object codiRole) {
        Query q = getEntityManager().createQuery("SELECT u FROM UsuarioRol u WHERE u.acceUsua = :acceUsua AND u.codiRole = :codiRole", UsuarioRol.class);        
        q.setParameter("acceUsua", acce);
        q.setParameter("codiRole", codiRole);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : (UsuarioRol)resu.get(0);
    }
}
