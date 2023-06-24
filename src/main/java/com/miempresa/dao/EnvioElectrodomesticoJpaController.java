/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.dao;

import com.miempresa.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.miempresa.entidades.Envio;
import com.miempresa.entidades.Electrodomestico;
import com.miempresa.entidades.EnvioElectrodomestico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author noone
 */
public class EnvioElectrodomesticoJpaController implements Serializable {

    public EnvioElectrodomesticoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnvioElectrodomestico envioElectrodomestico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Envio idEnvio = envioElectrodomestico.getIdEnvio();
            if (idEnvio != null) {
                idEnvio = em.getReference(idEnvio.getClass(), idEnvio.getIdEnvio());
                envioElectrodomestico.setIdEnvio(idEnvio);
            }
            Electrodomestico idElectrodomestico = envioElectrodomestico.getIdElectrodomestico();
            if (idElectrodomestico != null) {
                idElectrodomestico = em.getReference(idElectrodomestico.getClass(), idElectrodomestico.getIdElectrodomestico());
                envioElectrodomestico.setIdElectrodomestico(idElectrodomestico);
            }
            em.persist(envioElectrodomestico);
            if (idEnvio != null) {
                idEnvio.getEnvioElectrodomesticoList().add(envioElectrodomestico);
                idEnvio = em.merge(idEnvio);
            }
            if (idElectrodomestico != null) {
                idElectrodomestico.getEnvioElectrodomesticoList().add(envioElectrodomestico);
                idElectrodomestico = em.merge(idElectrodomestico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnvioElectrodomestico envioElectrodomestico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnvioElectrodomestico persistentEnvioElectrodomestico = em.find(EnvioElectrodomestico.class, envioElectrodomestico.getIdEnvioElectrodomestico());
            Envio idEnvioOld = persistentEnvioElectrodomestico.getIdEnvio();
            Envio idEnvioNew = envioElectrodomestico.getIdEnvio();
            Electrodomestico idElectrodomesticoOld = persistentEnvioElectrodomestico.getIdElectrodomestico();
            Electrodomestico idElectrodomesticoNew = envioElectrodomestico.getIdElectrodomestico();
            if (idEnvioNew != null) {
                idEnvioNew = em.getReference(idEnvioNew.getClass(), idEnvioNew.getIdEnvio());
                envioElectrodomestico.setIdEnvio(idEnvioNew);
            }
            if (idElectrodomesticoNew != null) {
                idElectrodomesticoNew = em.getReference(idElectrodomesticoNew.getClass(), idElectrodomesticoNew.getIdElectrodomestico());
                envioElectrodomestico.setIdElectrodomestico(idElectrodomesticoNew);
            }
            envioElectrodomestico = em.merge(envioElectrodomestico);
            if (idEnvioOld != null && !idEnvioOld.equals(idEnvioNew)) {
                idEnvioOld.getEnvioElectrodomesticoList().remove(envioElectrodomestico);
                idEnvioOld = em.merge(idEnvioOld);
            }
            if (idEnvioNew != null && !idEnvioNew.equals(idEnvioOld)) {
                idEnvioNew.getEnvioElectrodomesticoList().add(envioElectrodomestico);
                idEnvioNew = em.merge(idEnvioNew);
            }
            if (idElectrodomesticoOld != null && !idElectrodomesticoOld.equals(idElectrodomesticoNew)) {
                idElectrodomesticoOld.getEnvioElectrodomesticoList().remove(envioElectrodomestico);
                idElectrodomesticoOld = em.merge(idElectrodomesticoOld);
            }
            if (idElectrodomesticoNew != null && !idElectrodomesticoNew.equals(idElectrodomesticoOld)) {
                idElectrodomesticoNew.getEnvioElectrodomesticoList().add(envioElectrodomestico);
                idElectrodomesticoNew = em.merge(idElectrodomesticoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = envioElectrodomestico.getIdEnvioElectrodomestico();
                if (findEnvioElectrodomestico(id) == null) {
                    throw new NonexistentEntityException("The envioElectrodomestico with id " + id + " no longer exists.");
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
            EnvioElectrodomestico envioElectrodomestico;
            try {
                envioElectrodomestico = em.getReference(EnvioElectrodomestico.class, id);
                envioElectrodomestico.getIdEnvioElectrodomestico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The envioElectrodomestico with id " + id + " no longer exists.", enfe);
            }
            Envio idEnvio = envioElectrodomestico.getIdEnvio();
            if (idEnvio != null) {
                idEnvio.getEnvioElectrodomesticoList().remove(envioElectrodomestico);
                idEnvio = em.merge(idEnvio);
            }
            Electrodomestico idElectrodomestico = envioElectrodomestico.getIdElectrodomestico();
            if (idElectrodomestico != null) {
                idElectrodomestico.getEnvioElectrodomesticoList().remove(envioElectrodomestico);
                idElectrodomestico = em.merge(idElectrodomestico);
            }
            em.remove(envioElectrodomestico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnvioElectrodomestico> findEnvioElectrodomesticoEntities() {
        return findEnvioElectrodomesticoEntities(true, -1, -1);
    }

    public List<EnvioElectrodomestico> findEnvioElectrodomesticoEntities(int maxResults, int firstResult) {
        return findEnvioElectrodomesticoEntities(false, maxResults, firstResult);
    }

    private List<EnvioElectrodomestico> findEnvioElectrodomesticoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnvioElectrodomestico.class));
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

    public EnvioElectrodomestico findEnvioElectrodomestico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnvioElectrodomestico.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnvioElectrodomesticoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnvioElectrodomestico> rt = cq.from(EnvioElectrodomestico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
