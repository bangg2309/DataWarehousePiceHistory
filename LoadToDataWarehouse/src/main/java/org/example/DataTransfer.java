package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class DataTransfer {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/data_warehouse";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             CallableStatement callableStatement = connection.prepareCall("{CALL transfer_refrigerator_data()}")) {

            callableStatement.execute();
            System.out.println("Dữ liệu đã được chuyển thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
