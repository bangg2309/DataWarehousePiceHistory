package com.pricehistory.util;

import java.util.Properties;

public class PropUtil {
    private static final Properties prop = new Properties();

    static {
        try {
            prop.load(PropUtil.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception e) {
            //Bước 1.1
            System.out.println("File config not found");
            e.printStackTrace();
        }
    }

    public static String getProp(String key) {
        return prop.get(key).toString();
    }
}