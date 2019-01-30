package org.pan.service.impl;

import org.pan.bean.Permission;
import org.pan.bean.Role;
import org.pan.bean.User;
import org.pan.service.SecurityService;
import java.util.HashSet;
import java.util.Set;

public class SecurityServiceImpl implements SecurityService {
    public Set<Permission> findPermissionsByRoleName(String roleName) {
        HashSet<Permission> result = new HashSet<Permission>();
        if(roleName.equals("admin")){
            result.add(new Permission("carpark:*"));
        }
        if(roleName.equals("manager")){
            result.add(new Permission("carpark:view"));
        }
        return result;
    }

    public Set<Role> findRoleByUserName(String userName) {
        if(userName.equals("pan")){
            HashSet<Role> roles = new HashSet<Role>();
            roles.add(new Role("admin"));
            return roles;
        }
        if(userName.equals("fang")){
            HashSet<Role> roles = new HashSet<Role>();
            roles.add(new Role("manager"));
            return roles;
        }
        return new HashSet<Role>();
    }

    public User findUserByUserName(String username) {
        if(username.equals("pan")){
            return new User("pan","1234");
        }
        if(username.equals("fang")){
            return new User("fang","1234");
        }
        return null;
    }
}
