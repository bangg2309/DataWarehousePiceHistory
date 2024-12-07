package com.pricehistory.constant;

public class Queries {

    // Đếm số bản ghi trong file_logs dựa trên status va thời gian extract là thời gian hiện tại
    public static final String SELECT_FILE_LOGS_TODAY_NOT_BY_STATUS = "SELECT count(id) FROM file_logs WHERE status != :status AND DATE(extract_time) = CURDATE()";

    // Lấy ra các bản ghi trong file_config dựa trên source_name
    public static final String SELECT_FILE_CONFIG_BY_SOURCE_NAME = "SELECT * FROM file_config WHERE source_name = :sourceName";

    // Thêm bản ghi vào trong file_logs
    public static final String INSERT_FILE_LOG = "INSERT INTO file_logs (files_config_id, file_name, status, extract_time, total_record) VALUES (:idConfig, :fileName, :status, :extractTime, :totalRecords)";

}
