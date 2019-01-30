package org.pan.service;

import org.pan.bean.Permission;
import org.pan.bean.Role;
import org.pan.bean.User;

import java.util.Set;

public interface SecurityService {
    User findUserByUserName(String username);
    Set<Role> findRoleByUserName(String userName);
    Set<Permission> findPermissionsByRoleName(String roleName);
}
