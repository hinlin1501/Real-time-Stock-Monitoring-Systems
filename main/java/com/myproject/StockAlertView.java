package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        // TODO: Implement constructor
        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold; 
        this.lastAlertedPrices = new HashMap<>(); // Luu lich su canh bao 
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement alert logic based on threshold conditions
        String code = stockPrice.getCode();
    double price = stockPrice.getAvgPrice();

    Double lastPrice = lastAlertedPrices.get(code);

    boolean isHigh = price >= alertThresholdHigh;
    boolean isLow = price <= alertThresholdLow;

    if (isHigh || isLow) {
        if (lastPrice == null || Double.compare(lastPrice, price) != 0) {
            if (isHigh) {
                alertAbove(code, price);
            } else {
                alertBelow(code, price);
            }
            lastAlertedPrices.put(code, price);
        }
    } else {
        // Trong vùng an toàn: xóa nếu đã cảnh báo trước đó
        if (lastPrice != null) {
            lastAlertedPrices.remove(code);
        }
    }
    }

    private void alertAbove(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
    }

    private void alertBelow(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
    }
}
