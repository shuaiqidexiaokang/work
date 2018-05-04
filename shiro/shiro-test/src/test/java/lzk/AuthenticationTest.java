package lzk;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * shiro认证
 */
public class AuthenticationTest {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("admin","admin");
    }

    @Test
    public void testAuthentication(){
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin","admin");
        //org.apache.shiro.authc.UnknownAccountException: Realm [org.apache.shiro.realm.SimpleAccountRealm@6f79caec] was unable to find account data for the submitted
        //UsernamePasswordToken token = new UsernamePasswordToken("admin1","admin");
        //org.apache.shiro.authc.IncorrectCredentialsException: Submitted credentials for token [org.apache.shiro.authc.UsernamePasswordToken - admin, rememberMe=false] did not match
        //UsernamePasswordToken token = new UsernamePasswordToken("admin","admin1");
        subject.login(token);
        System.out.println("isAuthenticated:" +subject.isAuthenticated());
        subject.logout();
        System.out.println("isAuthenticated:" +subject.isAuthenticated());
    }
}
