/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.bean;

import com.miempresa.dao.UsuarioJpaController;
import com.miempresa.entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author noone
 */
public class UsuarioBean {
    private EntityManagerFactory emf;
    private UsuarioJpaController usuarioJpaController;
    
    public UsuarioBean(){
        emf = Persistence.createEntityManagerFactory("GenDelivery");
        usuarioJpaController = new UsuarioJpaController(emf);
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioJpaController.findUsuarioEntities();
    }
    
    
    
    
}
