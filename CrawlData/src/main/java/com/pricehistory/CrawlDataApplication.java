package com.pricehistory;

import com.pricehistory.service.CrawlDataService;

import java.io.IOException;

public class CrawlDataApplication {
    public static void main(String[] args) throws IOException {
        CrawlDataService crawlDataService = new CrawlDataService();
        crawlDataService.extractsourceDienmayxanh();
    }
}
