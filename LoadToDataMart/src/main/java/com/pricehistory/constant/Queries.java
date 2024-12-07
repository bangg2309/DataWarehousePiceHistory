package com.pricehistory.constant;

public class Queries {
    public static String loadQuery(String csvFilePath, String tableName) {
        return "LOAD DATA LOCAL INFILE '" + csvFilePath + "' INTO TABLE " + tableName +
                " FIELDS TERMINATED BY ',' " +
                " ENCLOSED BY '\"' " +
                " LINES TERMINATED BY '\\n'" +
                " IGNORE 1 ROWS" +
                " (SK, name, product_code, image_url, brand, price, effective_date, expiry_date,date_id, is_deleted);";
    }

    public static final String SELECT_FILE_LOGS_TODAY_BY_STATUS = "SELECT count(id) FROM file_logs WHERE status = :status AND DATE(extract_time) = CURDATE()";

    public static final String UPDATE_FILE_LOG_STATUS = "UPDATE file_logs SET status = :status WHERE DATE(extract_time) = CURDATE()";

    public static final String SELECT_FILENAME_FILE_LOGS_TODAY = "SELECT file_name FROM file_logs WHERE DATE(extract_time) = CURDATE()";


}
