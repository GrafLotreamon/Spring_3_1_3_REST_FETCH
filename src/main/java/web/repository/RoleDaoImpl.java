package web.repository;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }


    @Override
    public Role getRoleByRole(String role) {
        return getEntityManager()
                .createQuery("select r from Role r where r.role = :role", Role.class)
                .setParameter("role", role)
                .getSingleResult();
    }

    @Override
    public Role getRoleById(Long id) {
        return getEntityManager().find(Role.class, id);
    }

    @Override
    public List<Role> allRoles() {
        return getEntityManager()
                .createQuery("select r from Role r", Role.class)
                .getResultList();
    }

    @Override
    public void addRole(Role role) {
        getEntityManager().persist(role);
    }

    public void addRoles(Set<Role> roles) {
        for (Role role : roles) {
            getEntityManager().persist(role);
        }
    }
}
