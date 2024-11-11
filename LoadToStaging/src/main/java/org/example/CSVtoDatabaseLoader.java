package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CSVtoDatabaseLoader {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/staging?allowLoadLocalInfile=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    public static void main(String[] args) {
        String createdDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        String csvFilePath = "D:/NLU/HKI-Nam4/DW/data/refrigerators_" + createdDate + ".csv";
        String tableName = "refrigerators";
        System.out.println(csvFilePath);

        // Câu lệnh LOAD DATA INFILE
        String loadQuery = "LOAD DATA LOCAL INFILE '" + csvFilePath + "' INTO TABLE " + tableName +
                " FIELDS TERMINATED BY ',' " +
                " ENCLOSED BY '\"' " +
                " LINES TERMINATED BY '\\n'" +
                " IGNORE 1 ROWS" +
                " (name, product_code, image_url, brand, price, price_sale, discount,created_date);";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.execute(loadQuery);
            System.out.println("Dữ liệu đã được tải thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
