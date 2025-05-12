package com.myproject;
import java.util.*;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // TODO: Implement Singleton pattern
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        // TODO: Implement Singleton logic
        if (instance == null) {
            instance = new StockFeeder();
        }
        return instance;
    }

    public void addStock(Stock stock) {
        // TODO: Implement adding a stock to stockList
        // Kiểm tra null
        if(!stockList.contains(stock)){
            stockList.add(stock);
            viewers.put(stock.getCode(), new ArrayList<>());
        }
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // TODO: Implement registration logic, including checking stock existence

        // Đăng ký một StockViewer để theo dõi cổ phiếu code
        
        // Kiểm tra stock tồn tại trong stockList
        if (viewers.get(code) == null) {
            Logger.errorRegister(code);
            return;
        }
        viewers.get(code).add(stockViewer);
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // TODO: Implement unregister logic, including error logging

        // Nếu code không có trong viewer, log lỗi 
        if(!viewers.containsKey(code)||!viewers.get(code).contains(stockViewer)){
            Logger.errorUnregister(code);
            return;
        }
        viewers.get(code).remove(stockViewer);
    }

    public void notify(StockPrice stockPrice) {
        // TODO: Implement notifying registered viewers about price updates

        // Thông báo cố phiếu thay đổi

        String code=stockPrice.getCode();
        if(viewers.containsKey(code)){
            for(StockViewer viewers: viewers.get(code)){
                viewers.onUpdate(stockPrice);
            }
        }
    }
}
