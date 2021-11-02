package web.repository;

import org.springframework.data.repository.CrudRepository;
import web.model.User;

public interface UserDao extends CrudRepository<User, Long> {
    User findByName(String name);

    User findByEmail(String email);

    void deleteById(Long id);
}

