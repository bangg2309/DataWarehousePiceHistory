package com.pricehistory.service;

import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.Queries;
import com.pricehistory.util.PropUtil;

public class TransformInStagingService {


    private String tableName = PropUtil.getProp("stagingTableName");




    public void transformInStaging() {
//        Bước 3 kiểm tra record trong control.file_log
        if (isTodayDataExistByStatus("TR")) {
            //B4. update trong trong control.file_log thành TO
            updateFileLogStatus("TO");
            System.out.println("Bắt đầu transform dữ liệu vào staging...");
             //kết nối thành công db.staging thì thực hiện truncate table staging
            DatabaseConfig.getStaging().useHandle(handle -> handle.execute("TRUNCATE TABLE " + tableName));
            try {
                //Gọi câu lệnh procedure transform_refrigerators_data()
                DatabaseConfig.getStaging().useHandle(handle -> handle.createCall("{call transform_refrigerators_data()}").invoke());
            } catch (Exception e) {
                e.printStackTrace();
//                5.2.a Cập nhật status của record trong control.file_log thành TF
                updateFileLogStatus("TF");
//                5.2 Transform không thành công
                System.out.println("Transform dữ liệu vào staging thất bại.");
                return;
            }
//          Bước  6 Transform thành công

            updateFileLogStatus("LR");
            // Bước 7
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