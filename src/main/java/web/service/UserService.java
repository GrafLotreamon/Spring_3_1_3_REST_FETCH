package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

public interface UserService extends UserDetailsService {
    void createUser(User user);

    Iterable<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User getUserByEmail(String email);
}
