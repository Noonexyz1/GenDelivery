
package com.miempresa.bean;

import com.miempresa.dao.CamionJpaController;
import com.miempresa.dao.exceptions.NonexistentEntityException;
import com.miempresa.entidades.Camion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    public Camion traerCamion(int parseInt) {
        return camionJpaController.findCamion(parseInt);
    }

    public void eliminarCamion(int parseInt) {
        try {
            camionJpaController.destroy(parseInt);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CamionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Camion obtenerCamion(int parseInt) {
        return camionJpaController.findCamion(parseInt);
    }
 
   
    
}
