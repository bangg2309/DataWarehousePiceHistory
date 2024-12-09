package com.pricehistory.util;

import org.slf4j.Logger;

import java.util.Properties;

public class PropUtil {
    private static final Properties prop = new Properties();

    static {
        try {
            //  1. Load properties file
            prop.load(PropUtil.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception e) {
            Logger logger = org.slf4j.LoggerFactory.getLogger(PropUtil.class);
            logger.error("Khong load duoc file config", e);
            e.printStackTrace();
        }
    }

    public static String getProp(String key) {
        return prop.get(key).toString();
    }
}