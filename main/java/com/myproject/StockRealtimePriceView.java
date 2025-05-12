package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
    
        String code = stockPrice.getCode();
    double price = stockPrice.getAvgPrice();

    Double previous = lastPrices.get(code);

    if (previous == null) {
        lastPrices.put(code, price);
    } else if (previous != price) {
        lastPrices.replace(code, price);
        Logger.logRealtime(code, price);
    }
}
}
