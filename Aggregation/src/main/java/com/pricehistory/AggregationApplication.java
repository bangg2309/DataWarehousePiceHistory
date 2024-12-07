package com.pricehistory;


import com.pricehistory.service.AggregationService;

public class AggregationApplication {
    public static void main(String[] args) {
        AggregationService aggregationService = new AggregationService();
        aggregationService.aggregationInTable();
    }
}
