/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.bean;

import com.miempresa.dao.CamionJpaController;
import com.miempresa.entidades.Camion;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author noone
 */
public class CamionBean {
    private EntityManagerFactory emf;
    private CamionJpaController camionJpaController;
    
    
    public CamionBean(){
        emf = Persistence.createEntityManagerFactory("GenDelivery");
        camionJpaController = new CamionJpaController(emf);
    }

    public List<Camion> obtenerCamiones() {
        
        return camionJpaController.findCamionEntities();
        
    }
 
    
    
}
