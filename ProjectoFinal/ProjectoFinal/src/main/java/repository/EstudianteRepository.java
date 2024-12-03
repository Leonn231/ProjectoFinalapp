package repository;

import model.Estudiante;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class EstudianteRepository {

    public void guardar(Estudiante estudiante) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(estudiante);
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

    public List<Estudiante> obtenerTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Estudiante e", Estudiante.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Estudiante obtenerPorCodigo(String codigoEstudiante) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Estudiante e WHERE e.codigoEstudiante = :codigo", Estudiante.class)
                    .setParameter("codigo", codigoEstudiante)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public void eliminar(Estudiante estudiante) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(estudiante) ? estudiante : em.merge(estudiante));
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

