/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.dao;

import com.miempresa.dao.exceptions.NonexistentEntityException;
import com.miempresa.dao.exceptions.PreexistingEntityException;
import com.miempresa.entidades.Camion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.miempresa.entidades.Carga;
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

    public void create(Camion camion) throws PreexistingEntityException, Exception {
        if (camion.getCargaList() == null) {
            camion.setCargaList(new ArrayList<Carga>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Carga> attachedCargaList = new ArrayList<Carga>();
            for (Carga cargaListCargaToAttach : camion.getCargaList()) {
                cargaListCargaToAttach = em.getReference(cargaListCargaToAttach.getClass(), cargaListCargaToAttach.getIdCarga());
                attachedCargaList.add(cargaListCargaToAttach);
            }
            camion.setCargaList(attachedCargaList);
            em.persist(camion);
            for (Carga cargaListCarga : camion.getCargaList()) {
                Camion oldIdCamionOfCargaListCarga = cargaListCarga.getIdCamion();
                cargaListCarga.setIdCamion(camion);
                cargaListCarga = em.merge(cargaListCarga);
                if (oldIdCamionOfCargaListCarga != null) {
                    oldIdCamionOfCargaListCarga.getCargaList().remove(cargaListCarga);
                    oldIdCamionOfCargaListCarga = em.merge(oldIdCamionOfCargaListCarga);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCamion(camion.getIdCamion()) != null) {
                throw new PreexistingEntityException("Camion " + camion + " already exists.", ex);
            }
            throw ex;
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
            List<Carga> cargaListOld = persistentCamion.getCargaList();
            List<Carga> cargaListNew = camion.getCargaList();
            List<Carga> attachedCargaListNew = new ArrayList<Carga>();
            for (Carga cargaListNewCargaToAttach : cargaListNew) {
                cargaListNewCargaToAttach = em.getReference(cargaListNewCargaToAttach.getClass(), cargaListNewCargaToAttach.getIdCarga());
                attachedCargaListNew.add(cargaListNewCargaToAttach);
            }
            cargaListNew = attachedCargaListNew;
            camion.setCargaList(cargaListNew);
            camion = em.merge(camion);
            for (Carga cargaListOldCarga : cargaListOld) {
                if (!cargaListNew.contains(cargaListOldCarga)) {
                    cargaListOldCarga.setIdCamion(null);
                    cargaListOldCarga = em.merge(cargaListOldCarga);
                }
            }
            for (Carga cargaListNewCarga : cargaListNew) {
                if (!cargaListOld.contains(cargaListNewCarga)) {
                    Camion oldIdCamionOfCargaListNewCarga = cargaListNewCarga.getIdCamion();
                    cargaListNewCarga.setIdCamion(camion);
                    cargaListNewCarga = em.merge(cargaListNewCarga);
                    if (oldIdCamionOfCargaListNewCarga != null && !oldIdCamionOfCargaListNewCarga.equals(camion)) {
                        oldIdCamionOfCargaListNewCarga.getCargaList().remove(cargaListNewCarga);
                        oldIdCamionOfCargaListNewCarga = em.merge(oldIdCamionOfCargaListNewCarga);
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
            List<Carga> cargaList = camion.getCargaList();
            for (Carga cargaListCarga : cargaList) {
                cargaListCarga.setIdCamion(null);
                cargaListCarga = em.merge(cargaListCarga);
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
