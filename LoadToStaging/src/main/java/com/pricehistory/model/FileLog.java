package com.pricehistory.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class FileLog {
    int id;
    int idConfig;
    String fileName;
    String status;
    Date extractTime;
    int totalRecords;
}
