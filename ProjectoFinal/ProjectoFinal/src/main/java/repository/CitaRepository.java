 package repository;

import model.Cita;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CitaRepository {

    public void guardar(Cita cita) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cita);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Cita> obtenerTodas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cita c", Cita.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Cita obtenerPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Cita.class, id);
        } finally {
            em.close();
        }
    }

    public void modificarCita(Long idCita, String nuevaFechaHora) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cita cita = em.find(Cita.class, idCita);
            cita.setFechaHora(java.time.LocalDateTime.parse(nuevaFechaHora));
            em.merge(cita);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void cancelarCita(Long idCita) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cita cita = em.find(Cita.class, idCita);
            em.remove(cita);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}


