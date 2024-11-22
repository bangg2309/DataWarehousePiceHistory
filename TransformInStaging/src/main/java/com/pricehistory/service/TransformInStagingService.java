package com.pricehistory.service;

import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.Queries;
import com.pricehistory.util.PropUtil;

public class TransformInStagingService {

    private String tableName = PropUtil.getProp("stagingTableName");

    public void transformInStaging() {
        if (isTodayDataExistByStatus("TR")) {
            updateFileLogStatus("LO");
            System.out.println("Bắt đầu transform dữ liệu vào staging...");

            DatabaseConfig.getStaging().useHandle(handle -> handle.execute("TRUNCATE TABLE " + tableName));
            DatabaseConfig.getStaging().useHandle(handle -> handle.createCall("{call transform_refrigerators_data()}").invoke());

            updateFileLogStatus("LR");
            System.out.println("Transform dữ liệu vào staging thành công.");
        } else {
            System.out.println("Không có dữ liệu để transform vào staging.");
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
