package com.pricehistory.service;

import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.Queries;
import com.pricehistory.util.PropUtil;

public class LoadToDataMartService {
    private String tableName = PropUtil.getProp("dataMartTableName");
    private String dataMartOutputPath = PropUtil.getProp("dataMartOutputPath");

    public void loadToDataMart() {

        String loadQuery = Queries.loadQuery(dataMartOutputPath, tableName);

        DatabaseConfig.getStaging().useHandle(handle -> handle.execute("TRUNCATE TABLE " + tableName));
        DatabaseConfig.getStaging().useHandle(handle -> handle.execute(loadQuery));


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
