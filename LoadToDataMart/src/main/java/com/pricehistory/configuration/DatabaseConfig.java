package com.pricehistory.configuration;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pricehistory.util.PropUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
public class DatabaseConfig {
    private static Jdbi jdbiStaging;
    private static Jdbi jdbiControl;

    private static String host = PropUtil.getProp("db.host");
    private static String port = PropUtil.getProp("db.port");
    private static String username = PropUtil.getProp("db.username");
    private static String password = PropUtil.getProp("db.password");
    private static String databaseNameDataMart = PropUtil.getProp("db.databaseNameDataMart");
    private static String databaseNameControl = PropUtil.getProp("db.databaseNameControl");
    private static String allowLoadLocalInfile = PropUtil.getProp("db.allowLoadLocalInfile");


    private static void makeConnectStaging() {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://" + host + ":" + port + "/"
                    + databaseNameDataMart + "?allowLoadLocalInfile=" + allowLoadLocalInfile);
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);
            // Kết nối thử để kiểm tra cấu hình
            dataSource.getConnection().close();

            // Tạo Jdbi object và cài đặt plugin
            jdbiStaging = Jdbi.create(dataSource);
            jdbiStaging.installPlugin(new SqlObjectPlugin());
        } catch (SQLException e) {
            throw new RuntimeException("Không thể kết nối cơ sở dữ liệu.", e);
        }
    }

    private static void makeConnectControl() {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://" + host + ":" + port + "/"
                    + databaseNameControl + "?allowLoadLocalInfile=" + allowLoadLocalInfile);
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);
            // Kết nối thử để kiểm tra cấu hình
            dataSource.getConnection().close();

            // Tạo Jdbi object và cài đặt plugin
            jdbiControl = Jdbi.create(dataSource);
            jdbiControl.installPlugin(new SqlObjectPlugin());
        } catch (SQLException e) {
            throw new RuntimeException("Không thể kết nối cơ sở dữ liệu.", e);
        }
    }


    public static Jdbi getStaging() {
        if (jdbiStaging == null) makeConnectStaging();
        return jdbiStaging;
    }

    public static Jdbi getControl() {
        if (jdbiControl == null) makeConnectControl();
        return jdbiControl;
    }


}