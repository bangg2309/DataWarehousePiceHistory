package com.pricehistory.service;

import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.Queries;

public class AggregationService {

    public void aggregationInTable() {
        if (isTodayDataExistByStatus("AR")) {
            updateFileLogStatus("AO");
            System.out.println("Bắt đầu thêm dữ liệu vào bảng AggregatedHistoryPrice...");

            DatabaseConfig.getJdbiDatawarehouse().useHandle(handle -> handle.createCall("{call transform_dim_product_to_aggregated_history_price()}").invoke());

            updateFileLogStatus("MR");
            System.out.println("Thêm dữ liệu vào bảng AggregatedHistoryPrice thành công.");
        } else {
            System.out.println("Thêm dữ liệu vào bảng AggregatedHistoryPrice thất bại.");
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
