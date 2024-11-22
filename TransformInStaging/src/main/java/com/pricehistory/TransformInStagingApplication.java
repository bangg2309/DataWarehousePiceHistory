package com.pricehistory;


import com.pricehistory.service.TransformInStagingService;

public class TransformInStagingApplication {
    public static void main(String[] args) {
        TransformInStagingService transformInStagingService = new TransformInStagingService();
        transformInStagingService.transformInStaging();
    }
}
