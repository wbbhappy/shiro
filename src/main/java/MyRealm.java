import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.pan.bean.Permission;
import org.pan.bean.Role;
import org.pan.bean.User;
import org.pan.service.SecurityService;
import org.pan.service.impl.SecurityServiceImpl;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by panmingzhi on 2014/6/24.
 */
public class MyRealm extends AuthorizingRealm {

    private SecurityService securityService = new SecurityServiceImpl();

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
        String userName = (String)principalCollection.fromRealm(getName()).iterator().next();
        //查找所拥有角色
        Set<Role> roleSet = securityService.findRoleByUserName(userName);
        Iterator<Role> iterator = roleSet.iterator();
        while(iterator.hasNext()){
            Role role = iterator.next();
            sai.addRole(role.getRoleName());
            //查找资源操作权限
            Set<Permission> permissionsByRoleName = securityService.findPermissionsByRoleName(role.getRoleName());
            Iterator<Permission> permissionIterator = permissionsByRoleName.iterator();
            while(permissionIterator.hasNext()){
                sai.addStringPermission(permissionIterator.next().getPremissionName());
            }
        }
        return sai;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = securityService.findUserByUserName(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        } else {
            return null;
        }
    }


}
