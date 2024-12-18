package com.pricehistory.constant;

public class Queries {
    public static final String SELECT_FILE_LOGS_TODAY_BY_STATUS = "SELECT count(id) FROM file_logs WHERE status = :status AND DATE(extract_time) = CURDATE()";

    public static final String UPDATE_FILE_LOG_STATUS = "UPDATE file_logs SET status = :status WHERE DATE(extract_time) = CURDATE()";

    public static final String SELECT_FILENAME_FILE_LOGS_TODAY = "SELECT file_name FROM file_logs WHERE DATE(extract_time) = CURDATE()";


}
