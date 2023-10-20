package com.ww.stockscreener.data.pipelinr.handlers;

import an.awesome.pipelinr.Command;
import com.ww.stockscreener.core.models.StockBar;
import com.ww.stockscreener.core.repos.StockProviderRepo;
import com.ww.stockscreener.data.pipelinr.commands.GetEntireMarketDataBarsCommand;
import com.ww.stockscreener.data.repos.PolygonStockProviderRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetEntireMarketDataBarsCommandHandler implements Command.Handler<GetEntireMarketDataBarsCommand, List<StockBar>> {
    private final StockProviderRepo polygonStockProviderRepo;

    public GetEntireMarketDataBarsCommandHandler(StockProviderRepo polygonStockProviderRepo) {
        this.polygonStockProviderRepo = polygonStockProviderRepo;
    }

    @Override
    public List<StockBar> handle(GetEntireMarketDataBarsCommand barsCommand) {
        return polygonStockProviderRepo.getBarsForEntireMarket(barsCommand.startDate(), barsCommand.endDate());
    }
}
