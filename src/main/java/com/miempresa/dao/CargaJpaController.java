/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.dao;

import com.miempresa.dao.exceptions.NonexistentEntityException;
import com.miempresa.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.miempresa.entidades.Camion;
import com.miempresa.entidades.Carga;
import com.miempresa.entidades.Electrodomestico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author noone
 */
public class CargaJpaController implements Serializable {

    public CargaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carga carga) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Camion idCamion = carga.getIdCamion();
            if (idCamion != null) {
                idCamion = em.getReference(idCamion.getClass(), idCamion.getIdCamion());
                carga.setIdCamion(idCamion);
            }
            Electrodomestico idElectrodomestico = carga.getIdElectrodomestico();
            if (idElectrodomestico != null) {
                idElectrodomestico = em.getReference(idElectrodomestico.getClass(), idElectrodomestico.getIdElectrodomestico());
                carga.setIdElectrodomestico(idElectrodomestico);
            }
            em.persist(carga);
            if (idCamion != null) {
                idCamion.getCargaList().add(carga);
                idCamion = em.merge(idCamion);
            }
            if (idElectrodomestico != null) {
                idElectrodomestico.getCargaList().add(carga);
                idElectrodomestico = em.merge(idElectrodomestico);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCarga(carga.getIdCarga()) != null) {
                throw new PreexistingEntityException("Carga " + carga + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carga carga) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carga persistentCarga = em.find(Carga.class, carga.getIdCarga());
            Camion idCamionOld = persistentCarga.getIdCamion();
            Camion idCamionNew = carga.getIdCamion();
            Electrodomestico idElectrodomesticoOld = persistentCarga.getIdElectrodomestico();
            Electrodomestico idElectrodomesticoNew = carga.getIdElectrodomestico();
            if (idCamionNew != null) {
                idCamionNew = em.getReference(idCamionNew.getClass(), idCamionNew.getIdCamion());
                carga.setIdCamion(idCamionNew);
            }
            if (idElectrodomesticoNew != null) {
                idElectrodomesticoNew = em.getReference(idElectrodomesticoNew.getClass(), idElectrodomesticoNew.getIdElectrodomestico());
                carga.setIdElectrodomestico(idElectrodomesticoNew);
            }
            carga = em.merge(carga);
            if (idCamionOld != null && !idCamionOld.equals(idCamionNew)) {
                idCamionOld.getCargaList().remove(carga);
                idCamionOld = em.merge(idCamionOld);
            }
            if (idCamionNew != null && !idCamionNew.equals(idCamionOld)) {
                idCamionNew.getCargaList().add(carga);
                idCamionNew = em.merge(idCamionNew);
            }
            if (idElectrodomesticoOld != null && !idElectrodomesticoOld.equals(idElectrodomesticoNew)) {
                idElectrodomesticoOld.getCargaList().remove(carga);
                idElectrodomesticoOld = em.merge(idElectrodomesticoOld);
            }
            if (idElectrodomesticoNew != null && !idElectrodomesticoNew.equals(idElectrodomesticoOld)) {
                idElectrodomesticoNew.getCargaList().add(carga);
                idElectrodomesticoNew = em.merge(idElectrodomesticoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = carga.getIdCarga();
                if (findCarga(id) == null) {
                    throw new NonexistentEntityException("The carga with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carga carga;
            try {
                carga = em.getReference(Carga.class, id);
                carga.getIdCarga();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carga with id " + id + " no longer exists.", enfe);
            }
            Camion idCamion = carga.getIdCamion();
            if (idCamion != null) {
                idCamion.getCargaList().remove(carga);
                idCamion = em.merge(idCamion);
            }
            Electrodomestico idElectrodomestico = carga.getIdElectrodomestico();
            if (idElectrodomestico != null) {
                idElectrodomestico.getCargaList().remove(carga);
                idElectrodomestico = em.merge(idElectrodomestico);
            }
            em.remove(carga);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carga> findCargaEntities() {
        return findCargaEntities(true, -1, -1);
    }

    public List<Carga> findCargaEntities(int maxResults, int firstResult) {
        return findCargaEntities(false, maxResults, firstResult);
    }

    private List<Carga> findCargaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carga.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Carga findCarga(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carga.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carga> rt = cq.from(Carga.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
