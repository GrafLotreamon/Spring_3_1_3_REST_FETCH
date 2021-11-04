package web.repository;


import web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    Role getRoleByRole(String role);

    Role getRoleById(Long id);

    List<Role> allRoles();

     void addRole(Role role);


     void addRoles(Set<Role> roles);

    }




