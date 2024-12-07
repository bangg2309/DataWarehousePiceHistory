package com.pricehistory;


import com.pricehistory.service.TransferToDataMartService;

import java.io.IOException;

public class TransferToDataMartApplication {
    public static void main(String[] args) throws IOException {
        TransferToDataMartService transferToDataMartService = new TransferToDataMartService();
        transferToDataMartService.transferToDataMart();
    }
}
