package com.web.spring_3_1_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.web.spring_3_1_1.model.Role;
import com.web.spring_3_1_1.model.User;
import com.web.spring_3_1_1.service.RoleService;
import com.web.spring_3_1_1.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }




    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authorizedUser", userDetails);
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allRoles", roleService.allRoles());
        return "admin";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User newUser,
                                           @RequestParam(required = true) String[] selectedRole) {
        Set<Role> roles = new HashSet<>();

        if (selectedRole != null) {
            for (String roleId : selectedRole) {
                roles.add(roleService.getRoleByRole(roleId));
            }
        } else {
            roles.add(roleService.getRoleByRole("ROLE_USER"));
        }
         newUser.setRoles(roles);
        System.out.println(selectedRole);
        userService.addUser(newUser);
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User editUser,
                             @RequestParam(required = true) String[] selectedRole) {
        Set<Role> roles = new HashSet<>();

        if (selectedRole != null) {
            for (String roleId : selectedRole) {
                roles.add(roleService.getRoleByRole(roleId));
            }
        } else {
            roles.add(roleService.getRoleByRole("ROLE_USER"));
        }
        editUser.setRoles(roles);
        System.out.println(selectedRole);
        userService.update(editUser);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

