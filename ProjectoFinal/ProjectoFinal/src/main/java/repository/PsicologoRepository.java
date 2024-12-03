package repository;

import model.Psicologo;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class PsicologoRepository {

    public void guardar(Psicologo psicologo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (psicologo.getId() == null) {
                em.persist(psicologo); // Persist para nuevas entidades
            } else {
                em.merge(psicologo); // Merge para entidades existentes
            }
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


    public List<Psicologo> obtenerTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Psicologo p", Psicologo.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Psicologo obtenerPorNombre(String nombre) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Psicologo p WHERE p.nombre = :nombre", Psicologo.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public void actualizarDisponibilidad(Psicologo psicologo, String nuevosHorarios) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            psicologo.setHorariosDisponibles(nuevosHorarios);
            em.merge(psicologo);
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

    public boolean verificarDisponibilidad(String nombre, String horario) {
        Psicologo psicologo = obtenerPorNombre(nombre);
        return psicologo.getHorariosDisponibles().contains(horario);
    }
}
