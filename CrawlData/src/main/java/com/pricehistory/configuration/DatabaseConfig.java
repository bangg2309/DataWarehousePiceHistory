package com.pricehistory.configuration;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pricehistory.util.PropUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.sql.Connection;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
public class DatabaseConfig {
    private static Jdbi jdbi;

    private static String host = PropUtil.getProp("db.host");
    private static String port = PropUtil.getProp("db.port");
    private static String username = PropUtil.getProp("db.username");
    private static String password = PropUtil.getProp("db.password");
    private static String databaseName = PropUtil.getProp("db.databaseName");


    private static void makeConnect() {
        MysqlDataSource dataSource = new MysqlDataSource();
        try {
            // Cấu hình DataSource
            dataSource.setURL("jdbc:mysql://" + host + ":" + port + "/" + databaseName);
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);

            // Kết nối thử để kiểm tra cấu hình
            dataSource.getConnection().close();

            // Tạo Jdbi object và cài đặt plugin
            jdbi = Jdbi.create(dataSource);
            jdbi.installPlugin(new SqlObjectPlugin());
            System.out.println("Kết nối cơ sở dữ liệu thành công.");
        } catch (SQLException e) {
            throw new RuntimeException("Không thể kết nối cơ sở dữ liệu.", e);
        }
    }


    public static Jdbi get() {
        if (jdbi == null) {
            makeConnect();
        }
        return jdbi;
    }
}