
package com.miempresa.bean;

import com.miempresa.dao.ElectrodomesticoJpaController;
import com.miempresa.dao.exceptions.NonexistentEntityException;
import com.miempresa.entidades.Electrodomestico;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
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

    void addElectrodomestico(Electrodomestico electro) {
        electrodomesticoJpaController.create(electro);
    }

    void editarElectrodomestico(Electrodomestico electro) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Electrodomestico electroEdit = em.find(Electrodomestico.class, electro.getIdElectrodomestico());
        electroEdit.setNombre(electro.getNombre());
        electroEdit.setPesoKg(electro.getPesoKg());
        electroEdit.setBeneficio(electro.getBeneficio());
        
        em.merge(electroEdit);
        em.getTransaction().commit();
        em.close();
        
    }
    
    
    
    
}
