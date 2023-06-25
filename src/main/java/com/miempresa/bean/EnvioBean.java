
package com.miempresa.bean;

import com.miempresa.dao.EnvioJpaController;
import com.miempresa.dao.exceptions.NonexistentEntityException;
import com.miempresa.entidades.Envio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author noone
 */
public class EnvioBean {
    private EntityManagerFactory emf;
    private EnvioJpaController envioJpaController;
    
    public EnvioBean(){
        emf = Persistence.createEntityManagerFactory("GenDelivery");
        envioJpaController = new EnvioJpaController(emf);
    }

    public List<Envio> obtenerEnvios() {
        return envioJpaController.findEnvioEntities();
    }

    public Envio traerEnvio(int parseInt) {
        return envioJpaController.findEnvio(parseInt);
    }

    void eliminarEnvio(int parseInt) {
        try {
            envioJpaController.destroy(parseInt);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EnvioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
