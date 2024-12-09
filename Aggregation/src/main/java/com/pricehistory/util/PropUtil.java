package com.pricehistory.util;

import ch.qos.logback.classic.Logger;
import com.pricehistory.service.MailService;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class PropUtil {
    private static final Properties prop = new Properties();
    private static MailService mailService = MailService.getInstance();
    private static Logger logger = (Logger) LoggerFactory.getLogger(PropUtil.class);

    static {
        try {
            prop.load(PropUtil.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception e) {
            logger.error("Khong load duoc file config", e);
            mailService.sendMail("Khong load duoc file config", String.valueOf(e));
            e.printStackTrace();
        }
    }

    public static String getProp(String key) {
        return prop.get(key).toString();
    }
}