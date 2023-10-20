package com.ww.stockscreener.data.pipelinr.commands;

import an.awesome.pipelinr.Command;
import com.ww.stockscreener.core.models.StockBar;

import java.time.LocalDate;
import java.util.List;

public record GetEntireMarketDataBarsCommand (
        LocalDate startDate,
        LocalDate endDate
) implements Command<List<StockBar>> {

}
