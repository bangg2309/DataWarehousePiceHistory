package com.pricehistory.service;

import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.Queries;
import com.pricehistory.util.PropUtil;

public class LoadToStagingService {
    private String tableName = PropUtil.getProp("stagingTableName");


    public void loadToStaging() {
        if (isTodayDataExistByStatus("ER")) {

            updateFileLogStatus("EO");

            String fileName = getFileNameToFileLog();
            System.out.println("CSV file path: " + fileName);


            String loadQuery = Queries.loadQuery(fileName, tableName);

            DatabaseConfig.getStaging().useHandle(handle -> handle.execute("TRUNCATE TABLE " + tableName));
            DatabaseConfig.getStaging().useHandle(handle -> handle.execute(loadQuery));

            updateFileLogStatus("TR");
            System.out.println("Load dữ liệu vào staging thành công.");
        } else {
            System.out.println("Không có dữ liệu để load vào staging.");
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
