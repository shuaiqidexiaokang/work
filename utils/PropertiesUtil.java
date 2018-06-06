package com.xjhh.framework.utils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
 * @author Administrator
 */
public class PropertiesUtil {

	public final static Properties PROPS=new Properties();

	static{
		PropertiesUtil.read("setting.properties");
	}


	public static void read(String path) {
		try {
			Reader in = new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(path),
					"UTF-8");
			PROPS.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getValue(String key) {
		try {
			String returnStr = "";
			returnStr = PROPS.getProperty(key);
			return returnStr;
		} catch (Exception e) {
			return "";
		}

	}


	
}
