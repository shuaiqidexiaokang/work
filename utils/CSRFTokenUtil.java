package com.xjhh.framework.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class CSRFTokenUtil {

    public static String generate(HttpServletRequest request) {

        return UUID.randomUUID().toString();
    }

}
