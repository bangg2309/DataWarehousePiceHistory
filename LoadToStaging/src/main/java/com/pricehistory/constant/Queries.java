package com.pricehistory.constant;

public class Queries {
    // Load dữ liệu từ file CSV vào bảng
    public static String loadQuery(String csvFilePath, String tableName) {
        return "LOAD DATA LOCAL INFILE '" + csvFilePath.replace("\\", "\\\\") + "' INTO TABLE " + tableName +
                " FIELDS TERMINATED BY ',' " +
                " ENCLOSED BY '\"' " +
                " LINES TERMINATED BY '\\n'" +
                " IGNORE 1 ROWS" +
                " (name, product_code, image_url, brand, price, price_sale, discount,created_date);";
    }

    // Đếm số lượng bản ghi trong ngày hôm nay theo trạng thái
    public static final String SELECT_FILE_LOGS_TODAY_BY_STATUS = "SELECT count(id) FROM file_logs WHERE status = :status AND DATE(extract_time) = CURDATE()";
    // Cập nhật trạng thái của file trong ngày hôm nay
    public static final String UPDATE_FILE_LOG_STATUS = "UPDATE file_logs SET status = :status WHERE DATE(extract_time) = CURDATE()";
    // Lấy danh sách đường dẫn file csv trong ngày hôm nay
    public static final String SELECT_FILENAME_FILE_LOGS_TODAY = "SELECT file_name FROM file_logs WHERE DATE(extract_time) = CURDATE()";



}
