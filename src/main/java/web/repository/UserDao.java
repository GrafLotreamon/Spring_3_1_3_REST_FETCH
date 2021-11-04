package web.repository;


import web.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void delete(Long id);

    void update(User updatedUser);

    User getUserById(Long id);

    List<User> getAllUsers();


    public User getUserByEmail(String email);

}
