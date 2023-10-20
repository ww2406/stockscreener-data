package com.ww.stockscreener.data.repos;

import com.ww.stockscreener.core.models.StockBar;
import com.ww.stockscreener.core.repos.StockProviderRepo;

import java.time.LocalDate;
import java.util.List;

public class PolygonStockProviderRepo implements StockProviderRepo {
    @Override
    public List<StockBar> getBarsForEntireMarket(LocalDate startDate, LocalDate endDate) {
        return null;
    }
}
