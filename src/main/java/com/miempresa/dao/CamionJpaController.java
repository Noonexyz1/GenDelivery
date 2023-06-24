/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.dao;

import com.miempresa.dao.exceptions.NonexistentEntityException;
import com.miempresa.entidades.Camion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.miempresa.entidades.Envio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author noone
 */
public class CamionJpaController implements Serializable {

    public CamionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Camion camion) {
        if (camion.getEnvioList() == null) {
            camion.setEnvioList(new ArrayList<Envio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Envio> attachedEnvioList = new ArrayList<Envio>();
            for (Envio envioListEnvioToAttach : camion.getEnvioList()) {
                envioListEnvioToAttach = em.getReference(envioListEnvioToAttach.getClass(), envioListEnvioToAttach.getIdEnvio());
                attachedEnvioList.add(envioListEnvioToAttach);
            }
            camion.setEnvioList(attachedEnvioList);
            em.persist(camion);
            for (Envio envioListEnvio : camion.getEnvioList()) {
                Camion oldIdCamionOfEnvioListEnvio = envioListEnvio.getIdCamion();
                envioListEnvio.setIdCamion(camion);
                envioListEnvio = em.merge(envioListEnvio);
                if (oldIdCamionOfEnvioListEnvio != null) {
                    oldIdCamionOfEnvioListEnvio.getEnvioList().remove(envioListEnvio);
                    oldIdCamionOfEnvioListEnvio = em.merge(oldIdCamionOfEnvioListEnvio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Camion camion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Camion persistentCamion = em.find(Camion.class, camion.getIdCamion());
            List<Envio> envioListOld = persistentCamion.getEnvioList();
            List<Envio> envioListNew = camion.getEnvioList();
            List<Envio> attachedEnvioListNew = new ArrayList<Envio>();
            for (Envio envioListNewEnvioToAttach : envioListNew) {
                envioListNewEnvioToAttach = em.getReference(envioListNewEnvioToAttach.getClass(), envioListNewEnvioToAttach.getIdEnvio());
                attachedEnvioListNew.add(envioListNewEnvioToAttach);
            }
            envioListNew = attachedEnvioListNew;
            camion.setEnvioList(envioListNew);
            camion = em.merge(camion);
            for (Envio envioListOldEnvio : envioListOld) {
                if (!envioListNew.contains(envioListOldEnvio)) {
                    envioListOldEnvio.setIdCamion(null);
                    envioListOldEnvio = em.merge(envioListOldEnvio);
                }
            }
            for (Envio envioListNewEnvio : envioListNew) {
                if (!envioListOld.contains(envioListNewEnvio)) {
                    Camion oldIdCamionOfEnvioListNewEnvio = envioListNewEnvio.getIdCamion();
                    envioListNewEnvio.setIdCamion(camion);
                    envioListNewEnvio = em.merge(envioListNewEnvio);
                    if (oldIdCamionOfEnvioListNewEnvio != null && !oldIdCamionOfEnvioListNewEnvio.equals(camion)) {
                        oldIdCamionOfEnvioListNewEnvio.getEnvioList().remove(envioListNewEnvio);
                        oldIdCamionOfEnvioListNewEnvio = em.merge(oldIdCamionOfEnvioListNewEnvio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = camion.getIdCamion();
                if (findCamion(id) == null) {
                    throw new NonexistentEntityException("The camion with id " + id + " no longer exists.");
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
            Camion camion;
            try {
                camion = em.getReference(Camion.class, id);
                camion.getIdCamion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The camion with id " + id + " no longer exists.", enfe);
            }
            List<Envio> envioList = camion.getEnvioList();
            for (Envio envioListEnvio : envioList) {
                envioListEnvio.setIdCamion(null);
                envioListEnvio = em.merge(envioListEnvio);
            }
            em.remove(camion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Camion> findCamionEntities() {
        return findCamionEntities(true, -1, -1);
    }

    public List<Camion> findCamionEntities(int maxResults, int firstResult) {
        return findCamionEntities(false, maxResults, firstResult);
    }

    private List<Camion> findCamionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Camion.class));
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

    public Camion findCamion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Camion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCamionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Camion> rt = cq.from(Camion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
