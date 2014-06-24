package org.pan.service;

import org.pan.bean.Permission;
import org.pan.bean.Role;
import org.pan.bean.User;

import java.util.Set;

/**
 * Created by panmingzhi on 2014/6/25.
 */
public interface SecurityService {

    Set<Permission> findPermissionsByRoleName(String roleName);

    Set<Role> findRoleByUserName(String userName);

    User findUserByUserName(String username);
}
