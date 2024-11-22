package com.pricehistory.constant;

public class Queries {
    public static final String SELECT_FILE_LOGS_TODAY_NOT_BY_STATUS = "SELECT count(id) FROM file_logs WHERE status != :status AND DATE(extract_time) = CURDATE()";
    public static final String SELECT_FILE_CONFIG_BY_SOURCE_NAME = "SELECT * FROM file_config WHERE source_name = :sourceName";

    public static final String INSERT_FILE_LOG = "INSERT INTO file_logs (id_config, file_name, status, extract_time, total_records) VALUES (:idConfig, :fileName, :status, :extractTime, :totalRecords)";

}
