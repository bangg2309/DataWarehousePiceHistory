package com.pricehistory.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class PropUtil {
    private static final Properties prop = new Properties();

    static {
        try {
            prop.load(PropUtil.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception e) {
            log.error("File config not found");
            e.printStackTrace();
        }
    }

    public static String getProp(String key) {
        return prop.get(key).toString();
    }
}