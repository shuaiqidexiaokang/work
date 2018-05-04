package lzk;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    Map<String,String> userMap = new HashMap<>();

    {
        userMap.put("Mark","283538989cef48f3d7d8a1c1bdf2008f");
        super.setName("customRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();

        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionsByUserName(userName);

        SimpleAuthorizationInfo authorizationInfo  = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    /**
     * 模拟用户权限获取
     * @param userName
     * @return
     */
    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> sets = new HashSet<>();
        sets.add("user:delete");
        sets.add("user:update");
        return  sets;
    }

    /**
     *模拟用户角色获取
     * @param userName
     * @return
     */
    private Set<String> getRolesByUserName(String userName) {
        Set<String> sets = new HashSet<>();
        sets.add("admin");
        sets.add("user");
        return  sets;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1从主体传过来的认证信息中，获得用户名
        String userName = (String) authenticationToken.getPrincipal();

        //2.通过用户名到数据库中获得凭证
        String password = getPasswordByUserName(userName);
        if (password == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo  = new SimpleAuthenticationInfo(userName,password,"customRealm");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("Mark"));
        return authenticationInfo;
    }

    /**
     * 模拟数据库查询凭证
     * @param userName
     * @return
     */
    private String getPasswordByUserName(String userName) {
        return userMap.get(userName);
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456","Mark");
        System.out.println(md5Hash);
    }
}
