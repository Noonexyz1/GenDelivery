
package com.miempresa.bean;

import com.miempresa.dao.ElectrodomesticoJpaController;
import com.miempresa.entidades.Electrodomestico;
import java.util.List;
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
    
    
    
    
}
