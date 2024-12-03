package repository;

import model.Usuario;
import util.JPAUtil;

import javax.persistence.EntityManager;

public class UsuarioRepository {
    private EntityManager em = JPAUtil.getEntityManager();

    public void guardar(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public Usuario obtenerPorUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // Retorna null si el usuario no existe
        }
    }
}
