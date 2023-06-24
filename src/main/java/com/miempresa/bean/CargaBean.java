/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.bean;

import com.miempresa.dao.CargaJpaController;
import com.miempresa.dao.exceptions.NonexistentEntityException;
import com.miempresa.entidades.Carga;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public Carga traerCarga(int parseInt) {
        return cargaJpaController.findCarga(parseInt);
    }

    void eliminarCarga(int parseInt) {
        try {
            cargaJpaController.destroy(parseInt);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CargaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
