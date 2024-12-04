package com.pricehistory.constant;

public class Queries {
    // Load dữ liệu từ file CSV vào bảng
    public static String loadQuery(String csvFilePath, String tableName) {
        return "LOAD DATA LOCAL INFILE '" + csvFilePath + "' INTO TABLE " + tableName +
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
    // Lấy danh sách tên file trong ngày hôm nay
    public static final String SELECT_FILENAME_FILE_LOGS_TODAY = "SELECT file_name FROM file_logs WHERE DATE(extract_time) = CURDATE()";


    // Thêm một bản ghi mới vào bảng file_logs
    public static final String INSERT_FILE_LOG =
            "INSERT INTO file_logs (file_name, status, extract_time) VALUES (:file_name, :status, NOW())";

    // Lấy thông tin chi tiết về file theo ID
    public static final String SELECT_FILE_LOG_BY_ID =
            "SELECT * FROM file_logs WHERE id = :id";

    // Lấy danh sách file được xử lý trong khoảng thời gian cụ thể
    public static final String SELECT_FILE_LOGS_BY_DATE_RANGE =
            "SELECT * FROM file_logs WHERE extract_time BETWEEN :start_date AND :end_date";

    // Xóa các bản ghi cũ trước một ngày cụ thể
    public static final String DELETE_OLD_FILE_LOGS =
            "DELETE FROM file_logs WHERE DATE(extract_time) < :threshold_date";

    // Kiểm tra file đã được xử lý chưa dựa trên tên file
    public static final String CHECK_FILE_PROCESSED =
            "SELECT COUNT(*) FROM file_logs WHERE file_name = :file_name AND status = 'completed'";

    // Lấy số lượng file theo trạng thái
    public static final String COUNT_FILES_BY_STATUS =
            "SELECT status, COUNT(*) as count FROM file_logs GROUP BY status";

    // Lấy file gần đây nhất đã xử lý
    public static final String SELECT_LATEST_COMPLETED_FILE =
            "SELECT * FROM file_logs WHERE status = 'completed' ORDER BY extract_time DESC LIMIT 1";

    // Kiểm tra số lượng file thất bại trong ngày hôm nay
    public static final String COUNT_FAILED_FILES_TODAY =
            "SELECT COUNT(*) FROM file_logs WHERE status = 'error' AND DATE(extract_time) = CURDATE()";

    // Lấy danh sách file chưa xử lý (pending)
    public static final String SELECT_PENDING_FILES =
            "SELECT * FROM file_logs WHERE status = 'pending' ORDER BY extract_time ASC";

    // Cập nhật trạng thái của một file cụ thể
    public static final String UPDATE_FILE_STATUS_BY_ID =
            "UPDATE file_logs SET status = :new_status, extract_time = NOW() WHERE id = :id";

}
