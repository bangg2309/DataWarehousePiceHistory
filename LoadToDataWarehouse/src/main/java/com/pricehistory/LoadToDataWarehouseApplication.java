package com.pricehistory;


import com.pricehistory.service.LoadToDataWarehouseService;

public class LoadToDataWarehouseApplication {
    public static void main(String[] args) {
        LoadToDataWarehouseService loadToDataWarehouseService = new LoadToDataWarehouseService();
        loadToDataWarehouseService.loadToDataWarehouse();
    }
}
