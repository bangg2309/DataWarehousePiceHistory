package com.pricehistory.service;

import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.MESSAGE;
import com.pricehistory.constant.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadToDataWarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(LoadToDataWarehouseService.class);
    private final MailService mailService = MailService.getInstance();

    public void loadToDataWarehouse() {
        if (isTodayDataExistByStatus("LR")) {
            updateFileLogStatus("LO");
            logger.info(MESSAGE.START_LOAD_DATAWAREHOUSE);

            try {
                DatabaseConfig.getJdbiDatawarehouse().useHandle(handle -> handle.createCall("{call transform_refrigerators_to_dim_product()}").invoke());
            } catch (Exception e) {
                logger.error(MESSAGE.ERROR_LOAD_DATAWAREHOUSE, e);
                mailService.sendMail(MESSAGE.ERROR_LOAD_DATAWAREHOUSE, String.valueOf(e));
                updateFileLogStatus("LF");
                return;
            }

            updateFileLogStatus("AR");
            logger.info(MESSAGE.SUCCESS_LOAD_DATAWAREHOUSE);
            mailService.sendMail(MESSAGE.SUCCESS_LOAD_DATAWAREHOUSE, MESSAGE.SUCCESS_LOAD_DATAWAREHOUSE);

        } else {
            logger.warn(MESSAGE.NO_DATA_LOAD_DATAWAREHOUSE);
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
