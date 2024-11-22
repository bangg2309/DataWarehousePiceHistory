package com.pricehistory;

import com.pricehistory.service.LoadToStagingService;

public class LoadToStagingApplication {
    public static void main(String[] args) {
        LoadToStagingService loadToStagingService = new LoadToStagingService();
        loadToStagingService.loadToStaging();
    }
}
