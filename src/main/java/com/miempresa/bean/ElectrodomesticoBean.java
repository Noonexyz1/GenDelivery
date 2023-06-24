
package com.miempresa.bean;

import com.miempresa.dao.ElectrodomesticoJpaController;
import com.miempresa.dao.exceptions.NonexistentEntityException;
import com.miempresa.entidades.Electrodomestico;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ElectrodomesticoBean {
    private EntityManagerFactory emf;
    private ElectrodomesticoJpaController electrodomesticoJpaController;
    
    public ElectrodomesticoBean(){
        emf = Persistence.createEntityManagerFactory("GenDelivery");
        electrodomesticoJpaController = new ElectrodomesticoJpaController(emf);
    }

    public List<Electrodomestico> obtenerElectrodomesticos() {
        return electrodomesticoJpaController.findElectrodomesticoEntities();
        
    }

    public Electrodomestico traerElectrodomestico(int parseInt) {
        return electrodomesticoJpaController.findElectrodomestico(parseInt);
    }

    void eliminarElectrodomestico(int parseInt) {
        try {
            electrodomesticoJpaController.destroy(parseInt);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ElectrodomesticoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
