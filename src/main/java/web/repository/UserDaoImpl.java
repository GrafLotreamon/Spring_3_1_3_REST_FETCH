package web.repository;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }



    @Override
    public void addUser(User user) {
        getEntityManager().persist(user);
    }

    public void delete(Long id) {
        entityManager.remove(getUserById(id));
    }

    public void update(User updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Override
    public User getUserById(Long id) {
        return getEntityManager()
                .createQuery("select u from User u where u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return getEntityManager()
                .createQuery("select u from User u", User.class)
                .getResultList();
    }

//    @Override
//    public User getUserByUsername(String username) {
//        return getEntityManager()
//                .createQuery("select u from User u where u.username = :username", User.class)
//                .setParameter("username", username)
//                .getSingleResult();
//    }

    @Override
    public User getUserByEmail(String email) {
        return getEntityManager()
                .createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

}

