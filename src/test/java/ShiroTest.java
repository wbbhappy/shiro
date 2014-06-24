import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by panmingzhi on 2014/6/25.
 */
public class ShiroTest {

    @BeforeClass
    public static void before(){
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager(new MyRealm());
        SecurityUtils.setSecurityManager(defaultSecurityManager);
    }

    @Test
    public void loginTestSuccess(){
        UsernamePasswordToken upt = new UsernamePasswordToken("pan","1234");
        SecurityUtils.getSubject().login(upt);
    }

    @Test(expected = IncorrectCredentialsException.class)
    public void loginTestFaile(){
        UsernamePasswordToken upt = new UsernamePasswordToken("pan","12345");
        SecurityUtils.getSubject().login(upt);
    }

    @Test
    public void premissionTest(){
        //管理员用户登陆
        UsernamePasswordToken upt = new UsernamePasswordToken("pan","1234");
        SecurityUtils.getSubject().login(upt);

        //断断是否有管理员角色
        boolean admin = SecurityUtils.getSubject().hasRole("admin");
        Assert.assertEquals(true,admin);

        //判断是否有普通管理员角色
        boolean manager = SecurityUtils.getSubject().hasRole("manager");
        Assert.assertEquals(false,manager);

        //premission中写道：carpark.* 代表停车场中所有权限
        //判断是否有停车场修改权限
        boolean permitted = SecurityUtils.getSubject().isPermitted("carpark:edit");
        Assert.assertEquals(true,permitted);

        //判断是否有停车场查看权限
        boolean permitted2 = SecurityUtils.getSubject().isPermitted("carpark:view");
        Assert.assertEquals(true,permitted2);
    }

    @Test
    public void premissionTest2(){
        //管理员用户登陆
        UsernamePasswordToken upt = new UsernamePasswordToken("fang","1234");
        SecurityUtils.getSubject().login(upt);

        //断断是否有管理员角色
        boolean admin = SecurityUtils.getSubject().hasRole("admin");
        Assert.assertEquals(false,admin);

        //判断是否有普通管理员角色
        boolean manager = SecurityUtils.getSubject().hasRole("manager");
        Assert.assertEquals(true,manager);

        //判断是否有停车场修改权限
        boolean permitted = SecurityUtils.getSubject().isPermitted("carpark:edit");
        Assert.assertEquals(false,permitted);

        //判断是否有停车场查看权限
        boolean permitted2 = SecurityUtils.getSubject().isPermitted("carpark:view");
        Assert.assertEquals(true,permitted2);
    }
}
