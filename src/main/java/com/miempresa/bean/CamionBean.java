
package com.miempresa.bean;

import com.miempresa.dao.CamionJpaController;
import com.miempresa.dao.exceptions.NonexistentEntityException;
import com.miempresa.entidades.Camion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
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

    void addCamion(Camion camion) {
        camionJpaController.create(camion);
    }
    
    void editarCamion(Camion camion) throws Exception{
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Camion camionEdit = em.find(Camion.class, camion.getIdCamion());
        camionEdit.setModelo(camion.getModelo());
        camionEdit.setCapacidadKg(camion.getCapacidadKg());

        em.merge(camionEdit);
        em.getTransaction().commit();
        em.close();

        
    }
 
   
    
}
