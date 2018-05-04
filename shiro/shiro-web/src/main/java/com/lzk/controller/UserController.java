package com.lzk.controller;

import com.lzk.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping(value = "/subLogin",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String subLogin(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            token.setRememberMe(user.isRememberMe());
            subject.login(token);
        }catch (AuthenticationException e){
            return e.getMessage();
        }
        /*String msg;
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            System.out.println(msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            System.out.println(msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            System.out.println(msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            System.out.println(msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            System.out.println(msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
            System.out.println(msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            System.out.println(msg);
        }*/
        if (subject.hasRole("admin")){
            return "有admin权限";
        }
        return "无admin权限";
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/testRole",method = RequestMethod.GET)
    @ResponseBody
    public String testRole(){
        return "testRole success";
    }

    @RequiresRoles("admin1")
    @RequestMapping(value = "/testRole1",method = RequestMethod.GET)
    @ResponseBody
    public String testRole1(){
        return "testRole1 success";
    }

    @RequestMapping(value = "/testRolesOr",method = RequestMethod.GET)
    @ResponseBody
    public String testRolesOr(){
        return "testRolesOr success";
    }

    @RequiresPermissions({"user:update","user:delete"})
    @RequestMapping(value = "/testPermission",method = RequestMethod.GET)
    @ResponseBody
    public String testPermission(){
        return "testPermission success";
    }

    @RequiresPermissions("user:select")
    @RequestMapping(value = "/testPermission1",method = RequestMethod.GET)
    @ResponseBody
    public String testPermission1(){
        return "testPermission1 success";
    }
}
