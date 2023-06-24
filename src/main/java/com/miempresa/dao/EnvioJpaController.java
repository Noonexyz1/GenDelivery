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
import com.miempresa.entidades.Camion;
import com.miempresa.entidades.Envio;
import com.miempresa.entidades.EnvioElectrodomestico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author noone
 */
public class EnvioJpaController implements Serializable {

    public EnvioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Envio envio) {
        if (envio.getEnvioElectrodomesticoList() == null) {
            envio.setEnvioElectrodomesticoList(new ArrayList<EnvioElectrodomestico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Camion idCamion = envio.getIdCamion();
            if (idCamion != null) {
                idCamion = em.getReference(idCamion.getClass(), idCamion.getIdCamion());
                envio.setIdCamion(idCamion);
            }
            List<EnvioElectrodomestico> attachedEnvioElectrodomesticoList = new ArrayList<EnvioElectrodomestico>();
            for (EnvioElectrodomestico envioElectrodomesticoListEnvioElectrodomesticoToAttach : envio.getEnvioElectrodomesticoList()) {
                envioElectrodomesticoListEnvioElectrodomesticoToAttach = em.getReference(envioElectrodomesticoListEnvioElectrodomesticoToAttach.getClass(), envioElectrodomesticoListEnvioElectrodomesticoToAttach.getIdEnvioElectrodomestico());
                attachedEnvioElectrodomesticoList.add(envioElectrodomesticoListEnvioElectrodomesticoToAttach);
            }
            envio.setEnvioElectrodomesticoList(attachedEnvioElectrodomesticoList);
            em.persist(envio);
            if (idCamion != null) {
                idCamion.getEnvioList().add(envio);
                idCamion = em.merge(idCamion);
            }
            for (EnvioElectrodomestico envioElectrodomesticoListEnvioElectrodomestico : envio.getEnvioElectrodomesticoList()) {
                Envio oldIdEnvioOfEnvioElectrodomesticoListEnvioElectrodomestico = envioElectrodomesticoListEnvioElectrodomestico.getIdEnvio();
                envioElectrodomesticoListEnvioElectrodomestico.setIdEnvio(envio);
                envioElectrodomesticoListEnvioElectrodomestico = em.merge(envioElectrodomesticoListEnvioElectrodomestico);
                if (oldIdEnvioOfEnvioElectrodomesticoListEnvioElectrodomestico != null) {
                    oldIdEnvioOfEnvioElectrodomesticoListEnvioElectrodomestico.getEnvioElectrodomesticoList().remove(envioElectrodomesticoListEnvioElectrodomestico);
                    oldIdEnvioOfEnvioElectrodomesticoListEnvioElectrodomestico = em.merge(oldIdEnvioOfEnvioElectrodomesticoListEnvioElectrodomestico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Envio envio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Envio persistentEnvio = em.find(Envio.class, envio.getIdEnvio());
            Camion idCamionOld = persistentEnvio.getIdCamion();
            Camion idCamionNew = envio.getIdCamion();
            List<EnvioElectrodomestico> envioElectrodomesticoListOld = persistentEnvio.getEnvioElectrodomesticoList();
            List<EnvioElectrodomestico> envioElectrodomesticoListNew = envio.getEnvioElectrodomesticoList();
            if (idCamionNew != null) {
                idCamionNew = em.getReference(idCamionNew.getClass(), idCamionNew.getIdCamion());
                envio.setIdCamion(idCamionNew);
            }
            List<EnvioElectrodomestico> attachedEnvioElectrodomesticoListNew = new ArrayList<EnvioElectrodomestico>();
            for (EnvioElectrodomestico envioElectrodomesticoListNewEnvioElectrodomesticoToAttach : envioElectrodomesticoListNew) {
                envioElectrodomesticoListNewEnvioElectrodomesticoToAttach = em.getReference(envioElectrodomesticoListNewEnvioElectrodomesticoToAttach.getClass(), envioElectrodomesticoListNewEnvioElectrodomesticoToAttach.getIdEnvioElectrodomestico());
                attachedEnvioElectrodomesticoListNew.add(envioElectrodomesticoListNewEnvioElectrodomesticoToAttach);
            }
            envioElectrodomesticoListNew = attachedEnvioElectrodomesticoListNew;
            envio.setEnvioElectrodomesticoList(envioElectrodomesticoListNew);
            envio = em.merge(envio);
            if (idCamionOld != null && !idCamionOld.equals(idCamionNew)) {
                idCamionOld.getEnvioList().remove(envio);
                idCamionOld = em.merge(idCamionOld);
            }
            if (idCamionNew != null && !idCamionNew.equals(idCamionOld)) {
                idCamionNew.getEnvioList().add(envio);
                idCamionNew = em.merge(idCamionNew);
            }
            for (EnvioElectrodomestico envioElectrodomesticoListOldEnvioElectrodomestico : envioElectrodomesticoListOld) {
                if (!envioElectrodomesticoListNew.contains(envioElectrodomesticoListOldEnvioElectrodomestico)) {
                    envioElectrodomesticoListOldEnvioElectrodomestico.setIdEnvio(null);
                    envioElectrodomesticoListOldEnvioElectrodomestico = em.merge(envioElectrodomesticoListOldEnvioElectrodomestico);
                }
            }
            for (EnvioElectrodomestico envioElectrodomesticoListNewEnvioElectrodomestico : envioElectrodomesticoListNew) {
                if (!envioElectrodomesticoListOld.contains(envioElectrodomesticoListNewEnvioElectrodomestico)) {
                    Envio oldIdEnvioOfEnvioElectrodomesticoListNewEnvioElectrodomestico = envioElectrodomesticoListNewEnvioElectrodomestico.getIdEnvio();
                    envioElectrodomesticoListNewEnvioElectrodomestico.setIdEnvio(envio);
                    envioElectrodomesticoListNewEnvioElectrodomestico = em.merge(envioElectrodomesticoListNewEnvioElectrodomestico);
                    if (oldIdEnvioOfEnvioElectrodomesticoListNewEnvioElectrodomestico != null && !oldIdEnvioOfEnvioElectrodomesticoListNewEnvioElectrodomestico.equals(envio)) {
                        oldIdEnvioOfEnvioElectrodomesticoListNewEnvioElectrodomestico.getEnvioElectrodomesticoList().remove(envioElectrodomesticoListNewEnvioElectrodomestico);
                        oldIdEnvioOfEnvioElectrodomesticoListNewEnvioElectrodomestico = em.merge(oldIdEnvioOfEnvioElectrodomesticoListNewEnvioElectrodomestico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = envio.getIdEnvio();
                if (findEnvio(id) == null) {
                    throw new NonexistentEntityException("The envio with id " + id + " no longer exists.");
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
            Envio envio;
            try {
                envio = em.getReference(Envio.class, id);
                envio.getIdEnvio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The envio with id " + id + " no longer exists.", enfe);
            }
            Camion idCamion = envio.getIdCamion();
            if (idCamion != null) {
                idCamion.getEnvioList().remove(envio);
                idCamion = em.merge(idCamion);
            }
            List<EnvioElectrodomestico> envioElectrodomesticoList = envio.getEnvioElectrodomesticoList();
            for (EnvioElectrodomestico envioElectrodomesticoListEnvioElectrodomestico : envioElectrodomesticoList) {
                envioElectrodomesticoListEnvioElectrodomestico.setIdEnvio(null);
                envioElectrodomesticoListEnvioElectrodomestico = em.merge(envioElectrodomesticoListEnvioElectrodomestico);
            }
            em.remove(envio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Envio> findEnvioEntities() {
        return findEnvioEntities(true, -1, -1);
    }

    public List<Envio> findEnvioEntities(int maxResults, int firstResult) {
        return findEnvioEntities(false, maxResults, firstResult);
    }

    private List<Envio> findEnvioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Envio.class));
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

    public Envio findEnvio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Envio.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnvioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Envio> rt = cq.from(Envio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
