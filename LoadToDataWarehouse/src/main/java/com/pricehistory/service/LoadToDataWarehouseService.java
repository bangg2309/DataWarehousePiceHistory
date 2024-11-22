package com.pricehistory.service;

import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.Queries;

public class LoadToDataWarehouseService {

    public void loadToDataWarehouse() {
        if (isTodayDataExistByStatus("LR")) {
            updateFileLogStatus("LO");
            System.out.println("Bắt đầu load dữ liệu vào data warehouse...");

            DatabaseConfig.getJdbiDatawarehouse().useHandle(handle -> handle.createCall("{call transform_refrigerators_to_dim_product()}").invoke());

            updateFileLogStatus("AR");
            System.out.println("Load dữ liệu vào data warehouse thành công.");
        } else {
            System.out.println("Không có dữ liệu để load vào data warehouse.");
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
