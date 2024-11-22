package com.pricehistory.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class FileConfig {
    int id;
    String sourceName;
    String sourceUrl;
    String filePath;
    String dateFormat;
}
