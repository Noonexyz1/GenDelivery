
package com.miempresa.bean;

import com.miempresa.dao.EnvioElectrodomesticoJpaController;
import com.miempresa.entidades.EnvioElectrodomestico;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EnvioElectroBean {
    private EntityManagerFactory emf;
    private EnvioElectrodomesticoJpaController envioElectrodomesticoJpaController;

    public EnvioElectroBean() {
        emf = Persistence.createEntityManagerFactory("GenDelivery");
        envioElectrodomesticoJpaController = new EnvioElectrodomesticoJpaController(emf);
    }

    void addEe(EnvioElectrodomestico ee) {
        envioElectrodomesticoJpaController.create(ee);
    }
    
    
    
    
}
