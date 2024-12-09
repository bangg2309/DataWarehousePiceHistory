package com.pricehistory.service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.constant.Queries;
import com.pricehistory.util.PropUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TransferToDataMartService {
    //    Database
    String tableName = PropUtil.getProp("db.tableName");
    String outputPath = PropUtil.getProp("db.outputPath");
    //    Server
    String hostRemote = PropUtil.getProp("remote.host");
    String userRemote = PropUtil.getProp("remote.username");
    String directoryRemote = PropUtil.getProp("remote.directory");
    String passwordRemote = PropUtil.getProp("remote.password");
    String scriptNameRemote = PropUtil.getProp("remote.scriptName");



    public void transferToDataMart() throws IOException {
        if (isTodayDataExistByStatus("MR")) {
            exportUsingOutfile(tableName, outputPath);
            transferFileToDataMart(hostRemote, userRemote, passwordRemote);
            System.out.println("Data has been transferred to Data Mart");
            updateFileLogStatus("SC");
        } else {
            System.out.println("No data to transfer");
        }
    }

    private void exportUsingOutfile(String tableName, String outputPath) {
        String query = Queries.EXPORT_DATA_TO_FILE
                .replace("{table_name}", tableName)
                .replace("{output_path}", outputPath);

        DatabaseConfig.getJdbiDatawarehouse().useHandle(handle -> handle.execute(query));
    }


    private void transferFileToDataMart(String server, String username, String password) throws IOException {
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;
        ChannelExec channelExec = null;

        try {
            // Thiết lập session
            session = jsch.getSession(username, server, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // Tạo kênh SFTP để tải file lên
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            File file = new File(outputPath);
            String remotePath = directoryRemote + "/" + file.getName();

            System.out.println("Uploading file to remote path: " + remotePath);
            channelSftp.put(new FileInputStream(file), remotePath);
            System.out.println("File uploaded successfully to: " + remotePath);

            // Đóng kênh SFTP
            channelSftp.disconnect();

            // Thực thi file .jar trên máy remote
            System.out.println("Executing .jar file on remote server...");
            String command = String.format("cd %s && java -jar %s", directoryRemote, scriptNameRemote);

            channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(command);
            channelExec.setErrStream(System.err);
            channelExec.setOutputStream(System.out);

            channelExec.connect();
            System.out.println("Command executed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Failed to transfer file or execute jar on Data Mart", e);
        } finally {
            if (channelExec != null && channelExec.isConnected()) {
                channelExec.disconnect();
            }
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
