package com.pricehistory.constant;

public class MESSAGE {

    public static final String START_LOAD_FILE_CONFIG = "Bat dau load file config";
    public static final String ERROR_LOAD_FILE_CONFIG = "Khong load duoc file config";
    public static final String SUCCESS_LOAD_FILE_CONFIG = "Load file config thanh cong";

    //database
    public static final String ERROR_CONNECT_DATABASE_CONTROL = "Ket noi database control that bai";
    public static final String ERROR_CONNECT_DATABASE_DATAWAREHOUSE = "Ket noi database datawarehouse that bai";

    //Data warehouse
    public static final String START_LOAD_DATAWAREHOUSE = "ETL - DATAWAREHOUSE - Bat dau load du lieu vao data warehouse";
    public static final String ERROR_LOAD_DATAWAREHOUSE = "ETL - DATAWAREHOUSE - Load du lieu vao data warehouse that bai";
    public static final String SUCCESS_LOAD_DATAWAREHOUSE = "ETL - DATAWAREHOUSE - Load du lieu vao data warehouse thanh cong";
    public static final String NO_DATA_LOAD_DATAWAREHOUSE = "ETL - DATAWAREHOUSE - Khong co du lieu de load vao data warehouse";

    //Aggregation
    public static final String START_AGGREGATION = "ETL - AGGREGATION - Bat dau tinh toan du lieu";
    public static final String ERROR_AGGREGATION = "ETL - AGGREGATION - Tinh toan du lieu that bai";
    public static final String SUCCESS_AGGREGATION = "ETL - AGGREGATION - Tinh toan du lieu thanh cong";
    public static final String NO_DATA_LOAD_AGGREGATION = "ETL - AGGREGATION - Khong co du lieu de tinh toan";

    //Transfer to data mart
    public static final String START_TRANSFER_TO_DATA_MART = "ETL - TRANSFER TO DATA MART - Bat dau chuyen du lieu vao data mart";
    public static final String ERROR_TRANSFER_TO_DATA_MART = "ETL - TRANSFER TO DATA MART - Chuyen du lieu vao data mart that bai";
    public static final String SUCCESS_TRANSFER_TO_DATA_MART = "ETL - TRANSFER TO DATA MART - Chuyen du lieu vao data mart thanh cong";
}
