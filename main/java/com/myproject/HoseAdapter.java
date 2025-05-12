package com.myproject;

import java.util.ArrayList;
import java.util.List;

public class HoseAdapter implements PriceFetcher {
    private HosePriceFetchLib hoseLib;
    private List<String> stockCodes;
    private StockFeeder stockFeeder;
    public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
        // TODO: Implement constructor
        this.hoseLib = hoseLib;
        this.stockCodes = stockCodes; 
        this.stockFeeder= StockFeeder.getInstance();
    }

    @Override
    public List<StockPrice> fetch() {
        // TODO: Fetch stock data and convert it to StockPrice list
        // Gọi API để lấy dữ liệu cổ phiếu từ thư viện Hose
        List<StockPrice> result = new ArrayList<>();
    List<HoseData> rawData = hoseLib.getPrices(stockCodes);

    for (int i = 0; i < rawData.size(); i++) {
        HoseData data = rawData.get(i);
        String code = data.getStockCode();

        for (String targetCode : stockCodes) {
            if (code.equals(targetCode)) {
                StockPrice price = convertToStockPrice(data);
                stockFeeder.notify(price);
                result.add(price);
                break;
            }
        }
    }

    return result;
    }

    private StockPrice convertToStockPrice(HoseData hoseData) {
        // TODO: Convert HoseData to StockPrice
        return new StockPrice(
            hoseData.getStockCode(),
            hoseData.getPrice(),
            hoseData.getVolume(),
            hoseData.getTimestamp()
        );
    }
}
