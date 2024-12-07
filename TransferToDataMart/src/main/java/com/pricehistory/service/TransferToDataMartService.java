package com.pricehistory.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.Queries;
import com.pricehistory.util.PropUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class TransferToDataMartService {

    String host = PropUtil.getProp("db.host");
    String user = PropUtil.getProp("db.username");
    String password = PropUtil.getProp("db.password");
    String database = PropUtil.getProp("db.databaseNameDatawarehouse");
    String tableName = PropUtil.getProp("db.tableName");
    String outputPath = PropUtil.getProp("db.outputPath");

    //    Server
    String hostServer = PropUtil.getProp("server.presentation.host");
    String userServer = PropUtil.getProp("server.presentation.username");
    String directoryServer = PropUtil.getProp("server.presentation.directory");
    String passwordServer = PropUtil.getProp("server.presentation.password");


    public void transferToDataMart() throws IOException {
        if (isTodayDataExistByStatus("MR")) {
//            updateFileLogStatus("MO");
//            exportUsingOutfile(host, user, password, database, tableName, outputPath);
            transferFileToDataMart(hostServer, 22, userServer, passwordServer);
            System.out.println("Data has been transferred to Data Mart");
        } else {
            System.out.println("No data to transfer");
        }
    }

    private void exportUsingOutfile(String host, String user, String password, String database, String tableName, String outputPath) {
        String query = String.format(
                "SELECT * FROM %s.%s INTO OUTFILE '%s' " +
                        "FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n';",
                database, tableName, outputPath
        );

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.execute(query);
            System.out.println("Data exported to: " + outputPath);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void transferFileToDataMart(String server, int port, String username, String password) throws IOException {
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;

        try {
            // Thiết lập session
            session = jsch.getSession(username, server, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // Tạo kênh SFTP
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // Upload file
            File file = new File(outputPath);

            String remotePath = "D:\\NLU\\HKI-Nam4\\DW\\remote\\" + file.getName();

            System.out.println("Uploading file to remote path: " + remotePath);
            channelSftp.put(new FileInputStream(file), remotePath);
            System.out.println("File uploaded successfully to: " + remotePath);



        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Failed to transfer file to Data Mart", e);
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }




    private boolean isTodayDataExistByStatus(String status) {
        return DatabaseConfig.getControl().withHandle(handle -> handle.createQuery(Queries.SELECT_FILE_LOGS_TODAY_BY_STATUS)
                .bind("status", status)
                .mapTo(Integer.class)
                .findFirst()
                .orElse(0)) > 0;
    }

    public void updateFileLogStatus(String status) {
        DatabaseConfig.getControl().useHandle(handle -> handle.createUpdate(Queries.UPDATE_FILE_LOG_STATUS)
                .bind("status", status)
                .execute());
    }
}
