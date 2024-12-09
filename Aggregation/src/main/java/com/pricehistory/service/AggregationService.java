package com.pricehistory.service;

import ch.qos.logback.classic.Logger;
import com.mysql.cj.log.Log;
import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.MESSAGE;
import com.pricehistory.constant.Queries;
import org.slf4j.LoggerFactory;

public class AggregationService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(AggregationService.class);
    private MailService mailService = MailService.getInstance();

    public void aggregationInTable() {
        if (isTodayDataExistByStatus("AR")) {
            updateFileLogStatus("AO");
            System.out.println(MESSAGE.START_AGGREGATION);
            try {
                DatabaseConfig.getJdbiDatawarehouse().useHandle(handle -> handle.createCall("{call transform_dim_product_to_aggregated_history_price()}").invoke());
            }
            catch (Exception e) {
                e.printStackTrace();
                logger.error(MESSAGE.ERROR_AGGREGATION, e);
                mailService.sendMail(MESSAGE.ERROR_AGGREGATION, String.valueOf(e));
                updateFileLogStatus("AF");
                return;
            }
            logger.info(MESSAGE.SUCCESS_AGGREGATION);
            mailService.sendMail(MESSAGE.SUCCESS_AGGREGATION, MESSAGE.SUCCESS_AGGREGATION);
            updateFileLogStatus("MR");
        } else {
            System.out.println(MESSAGE.NO_DATA_LOAD_AGGREGATION);
        }
    }


    private boolean isTodayDataExistByStatus(String status) {
        return DatabaseConfig.getControl().withHandle(handle -> handle.createQuery(Queries.SELECT_FILE_LOGS_TODAY_BY_STATUS)
                .bind("status", status)
                .mapTo(Integer.class)
                .findFirst()
                .orElse(0)) > 0;
    }

    public void updateFileLogStatus(String status) {
        DatabaseConfig.getControl().useHandle(handle -> handle.createUpdate(Queries.UPDATE_FILE_LOG_STATUS)
                .bind("status", status)
                .execute());
    }
}
