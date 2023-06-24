/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.bean;

import com.miempresa.dao.CargaJpaController;
import com.miempresa.entidades.Carga;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author noone
 */
public class CargaBean {
    private EntityManagerFactory emf;
    private CargaJpaController cargaJpaController;
    
    public CargaBean(){
        emf = Persistence.createEntityManagerFactory("GenDelivery");
        cargaJpaController = new CargaJpaController(emf);
    }

    public List<Carga> obtenerCargas() {
        return cargaJpaController.findCargaEntities();
    }
    
    
}
