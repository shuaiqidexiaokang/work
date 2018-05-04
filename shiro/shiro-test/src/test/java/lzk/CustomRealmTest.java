package lzk;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 自定义Realm
 */
public class CustomRealmTest {

    CustomRealm customRealm = new CustomRealm();

    @Test
    public void doGetAuthorizationInfo() {
    }

    @Test
    public void doGetAuthenticationInfo() {
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        //加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");

        subject.login(token);
        System.out.println("isAuthenticated:" +subject.isAuthenticated());

        subject.checkRole("admin");
        subject.checkPermissions("user:delete","user:update");
    }
}