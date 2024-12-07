package com.pricehistory.service;

import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadToDataWarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(LoadToDataWarehouseService.class);

    public void loadToDataWarehouse() {
        if (isTodayDataExistByStatus("LR")) {
            updateFileLogStatus("LO");
            logger.info("Bắt đầu load dữ liệu vào data warehouse...");

            try {
                DatabaseConfig.getJdbiDatawarehouse().useHandle(handle -> handle.createCall("{call transform_refrigerators_to_dim_product()}").invoke());
            } catch (Exception e) {
                logger.error("Load dữ liệu vào data warehouse thất bại.", e);
                updateFileLogStatus("LF");
                return;
            }

            updateFileLogStatus("AR");
            logger.info("Load dữ liệu vào data warehouse thành công.");
        } else {
            logger.warn("Không có dữ liệu để load vào data warehouse.");
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
