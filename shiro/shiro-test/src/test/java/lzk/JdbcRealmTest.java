package lzk;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql:///shiro");
        dataSource.setUsername("root");
        dataSource.setPassword("");
    }

    @Test
    public void testAuthentication(){
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");

        subject.login(token);
        System.out.println("isAuthenticated:" +subject.isAuthenticated());

        subject.checkRole("admin");
        subject.checkPermission("user:select");
    }

    @Test
    public void testAuthentication1(){
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        String authenticationQuery = "select password from test_users where username = ?";
        String userRolesQuery = "select role_name from test_user_roles where username = ?";
        String permissionsQuery = "select permission from test_roles_permissions where role_name = ?";
        jdbcRealm.setAuthenticationQuery(authenticationQuery);
        jdbcRealm.setUserRolesQuery(userRolesQuery);
        jdbcRealm.setPermissionsQuery(permissionsQuery);

        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Tony","654321");

        subject.login(token);
        System.out.println("isAuthenticated:" +subject.isAuthenticated());

        subject.checkRole("admin");
        subject.checkPermission("user:select");
    }
}
