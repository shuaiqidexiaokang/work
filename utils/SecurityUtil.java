package com.xjhh.framework.utils;

import com.xjhh.framework.base.entity.SysUser;
import org.apache.shiro.SecurityUtils;

public class SecurityUtil {

    public static String getUserId() {
        return getUser().getLogId();
    }

    public static SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }
}
