package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.AppUser;
import com.baytree_mentoring.baytree_mentoring.models.Role;

import java.util.List;

public interface AppUserService {
    AppUser saveUser(AppUser user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    AppUser getUser(String username);

    List<AppUser> getUsers();

    boolean checkIfUserExists(String username, String password);
}
