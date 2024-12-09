package com.pricehistory.service;

import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.Queries;
import com.pricehistory.util.PropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadToStagingService {
    private static final Logger logger = LoggerFactory.getLogger(LoadToStagingService.class);
    private String tableName = PropUtil.getProp("stagingTableName");

    public void loadToStaging() {
        try {
            if (isTodayDataExistByStatus("ER")) {
                updateFileLogStatus("EO");

                String fileName = getFileNameToFileLog();
                logger.info("CSV file path: {}", fileName);

                String loadQuery = Queries.loadQuery(fileName, tableName);

                // Truncate table
                int truncateResult = DatabaseConfig.getStaging()
                        .withHandle(handle -> handle.execute("TRUNCATE TABLE " + tableName));

                if (truncateResult == 0) {
                    logger.info("Truncate table '{}' thành công.", tableName);
                }

                // Load data
                int loadResult = DatabaseConfig.getStaging()
                        .withHandle(handle -> handle.execute(loadQuery));

                if (loadResult > 0) {
                    updateFileLogStatus("TR");
                    logger.info("Load dữ liệu vào staging thành công. Số bản ghi: {}", loadResult);
                } else {
                    logger.error("Lỗi không load được dữ liệu");
                    updateFileLogStatus("FL");
                }

            } else {
                logger.warn("Không có dữ liệu để load vào staging.");
            }
        } catch (Exception e) {
            logger.error("Lỗi khi load dữ liệu vào staging.", e);
            updateFileLogStatus("FL");
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

    private String getFileNameToFileLog() {
        return DatabaseConfig.getControl().withHandle(handle -> handle.createQuery(Queries.SELECT_FILENAME_FILE_LOGS_TODAY)
                .mapTo(String.class)
                .findFirst()
                .orElse(""));
    }
}
