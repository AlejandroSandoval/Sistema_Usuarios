/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.UsuarioRol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Carlos
 */
@Local
public interface UsuarioRolFacadeLocal {

    void create(UsuarioRol usuarioRol);

    void edit(UsuarioRol usuarioRol);

    void remove(UsuarioRol usuarioRol);

    UsuarioRol find(Object id);
    
    UsuarioRol findByAcceAndCont(Object acce, Object cont);
    
    UsuarioRol findByAcceAndRol(Object acce, Object codiRole);
    
    List<UsuarioRol> findByAcce(Object acce);
    
     List<UsuarioRol> findByAcceOnly();

    List<UsuarioRol> findAll();

    List<UsuarioRol> findRange(int[] range);

    int count();
    
}
