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
import com.miempresa.entidades.Carga;
import com.miempresa.entidades.Electrodomestico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author noone
 */
public class ElectrodomesticoJpaController implements Serializable {

    public ElectrodomesticoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Electrodomestico electrodomestico) throws PreexistingEntityException, Exception {
        if (electrodomestico.getCargaList() == null) {
            electrodomestico.setCargaList(new ArrayList<Carga>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Carga> attachedCargaList = new ArrayList<Carga>();
            for (Carga cargaListCargaToAttach : electrodomestico.getCargaList()) {
                cargaListCargaToAttach = em.getReference(cargaListCargaToAttach.getClass(), cargaListCargaToAttach.getIdCarga());
                attachedCargaList.add(cargaListCargaToAttach);
            }
            electrodomestico.setCargaList(attachedCargaList);
            em.persist(electrodomestico);
            for (Carga cargaListCarga : electrodomestico.getCargaList()) {
                Electrodomestico oldIdElectrodomesticoOfCargaListCarga = cargaListCarga.getIdElectrodomestico();
                cargaListCarga.setIdElectrodomestico(electrodomestico);
                cargaListCarga = em.merge(cargaListCarga);
                if (oldIdElectrodomesticoOfCargaListCarga != null) {
                    oldIdElectrodomesticoOfCargaListCarga.getCargaList().remove(cargaListCarga);
                    oldIdElectrodomesticoOfCargaListCarga = em.merge(oldIdElectrodomesticoOfCargaListCarga);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findElectrodomestico(electrodomestico.getIdElectrodomestico()) != null) {
                throw new PreexistingEntityException("Electrodomestico " + electrodomestico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Electrodomestico electrodomestico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Electrodomestico persistentElectrodomestico = em.find(Electrodomestico.class, electrodomestico.getIdElectrodomestico());
            List<Carga> cargaListOld = persistentElectrodomestico.getCargaList();
            List<Carga> cargaListNew = electrodomestico.getCargaList();
            List<Carga> attachedCargaListNew = new ArrayList<Carga>();
            for (Carga cargaListNewCargaToAttach : cargaListNew) {
                cargaListNewCargaToAttach = em.getReference(cargaListNewCargaToAttach.getClass(), cargaListNewCargaToAttach.getIdCarga());
                attachedCargaListNew.add(cargaListNewCargaToAttach);
            }
            cargaListNew = attachedCargaListNew;
            electrodomestico.setCargaList(cargaListNew);
            electrodomestico = em.merge(electrodomestico);
            for (Carga cargaListOldCarga : cargaListOld) {
                if (!cargaListNew.contains(cargaListOldCarga)) {
                    cargaListOldCarga.setIdElectrodomestico(null);
                    cargaListOldCarga = em.merge(cargaListOldCarga);
                }
            }
            for (Carga cargaListNewCarga : cargaListNew) {
                if (!cargaListOld.contains(cargaListNewCarga)) {
                    Electrodomestico oldIdElectrodomesticoOfCargaListNewCarga = cargaListNewCarga.getIdElectrodomestico();
                    cargaListNewCarga.setIdElectrodomestico(electrodomestico);
                    cargaListNewCarga = em.merge(cargaListNewCarga);
                    if (oldIdElectrodomesticoOfCargaListNewCarga != null && !oldIdElectrodomesticoOfCargaListNewCarga.equals(electrodomestico)) {
                        oldIdElectrodomesticoOfCargaListNewCarga.getCargaList().remove(cargaListNewCarga);
                        oldIdElectrodomesticoOfCargaListNewCarga = em.merge(oldIdElectrodomesticoOfCargaListNewCarga);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = electrodomestico.getIdElectrodomestico();
                if (findElectrodomestico(id) == null) {
                    throw new NonexistentEntityException("The electrodomestico with id " + id + " no longer exists.");
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
            Electrodomestico electrodomestico;
            try {
                electrodomestico = em.getReference(Electrodomestico.class, id);
                electrodomestico.getIdElectrodomestico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The electrodomestico with id " + id + " no longer exists.", enfe);
            }
            List<Carga> cargaList = electrodomestico.getCargaList();
            for (Carga cargaListCarga : cargaList) {
                cargaListCarga.setIdElectrodomestico(null);
                cargaListCarga = em.merge(cargaListCarga);
            }
            em.remove(electrodomestico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Electrodomestico> findElectrodomesticoEntities() {
        return findElectrodomesticoEntities(true, -1, -1);
    }

    public List<Electrodomestico> findElectrodomesticoEntities(int maxResults, int firstResult) {
        return findElectrodomesticoEntities(false, maxResults, firstResult);
    }

    private List<Electrodomestico> findElectrodomesticoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Electrodomestico.class));
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

    public Electrodomestico findElectrodomestico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Electrodomestico.class, id);
        } finally {
            em.close();
        }
    }

    public int getElectrodomesticoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Electrodomestico> rt = cq.from(Electrodomestico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
