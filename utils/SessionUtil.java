package com.xjhh.framework.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


/**
 * SessionUtil session 工具类
 * 
 * @author
 */
public class SessionUtil {


    public static Session getSession() {

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return session;
    }

}
