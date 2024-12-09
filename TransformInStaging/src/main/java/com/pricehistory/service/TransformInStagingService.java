package com.pricehistory.service;

import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.MESSAGES;
import com.pricehistory.constant.Queries;
import com.pricehistory.util.PropUtil;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;


public class TransformInStagingService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(TransformInStagingService.class);
    private MailService mailService = MailService.getInstance();


    private String tableName = PropUtil.getProp("stagingTableName");




    public void transformInStaging() {
//        Bước 3 kiểm tra record trong control.file_log
        if (isTodayDataExistByStatus("TR")) {
            //B4. update trong trong control.file_log thành TO
            updateFileLogStatus("TO");
            System.out.println("Bắt đầu transform dữ liệu vào staging...");
             //Bước 5.kết nối thành công db.staging thì thực hiện truncate table staging
            DatabaseConfig.getStaging().useHandle(handle -> handle.execute("TRUNCATE TABLE " + tableName));
            try {
                //Gọi câu lệnh procedure transform_refrigerators_data()
                DatabaseConfig.getStaging().useHandle(handle -> handle.createCall("{call transform_refrigerators_data()}").invoke());
            } catch (Exception e) {
                e.printStackTrace();
                // Ghi log lỗi
                logger.error(MESSAGES.ERROR_TRANSFORM, e);
                // Gửi email thông báo lỗi
                mailService.sendMail(MESSAGES.ERROR_TRANSFORM, "Lỗi: " + e.getMessage());
//                Cập nhật status của record trong control.file_log thành TF
                updateFileLogStatus("TF");
//                 Transform không thành công
                System.out.println("Transform dữ liệu vào staging thất bại.");
                return;
            }
//          Bước  6 Transform thành công

            updateFileLogStatus("LR");
            // Gửi email thông báo thành công
            mailService.sendMail(MESSAGES.SUCCESS_TRANSFORM, MESSAGES.SUCCESS_TRANSFORM);
            // Bước 7
            System.out.println("Transform dữ liệu vào staging thành công.");
        } else {
            System.out.println("Không có dữ liệu để transform vào staging.");
//            System.out.println(MESSAGES.NO_DATA_LOAD_TRANSFORM);
            mailService.sendMail(MESSAGES.NO_DATA_LOAD_TRANSFORM, MESSAGES.NO_DATA_LOAD_TRANSFORM);

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