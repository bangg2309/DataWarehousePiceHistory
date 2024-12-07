package com.pricehistory;


import com.pricehistory.service.LoadToDataMartService;

public class LoadToDataMartApplication {
    public static void main(String[] args) {
        LoadToDataMartService loadToDataMartService = new LoadToDataMartService();
        loadToDataMartService.loadToDataMart();
    }
}
