package com.ww.stockscreener.data.repos;

import com.ww.stockscreener.core.models.StockBar;
import com.ww.stockscreener.core.repos.StockProviderRepo;
import io.polygon.kotlin.sdk.rest.AggregateDTO;
import io.polygon.kotlin.sdk.rest.GroupedDailyParameters;
import io.polygon.kotlin.sdk.rest.PolygonRestClient;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PolygonStockProviderRepo implements StockProviderRepo {
    @Override
    public List<StockBar> getBarsForEntireMarket(LocalDate startDate, LocalDate endDate) {
        String polygonKey = System.getenv("POLYGON");
        PolygonRestClient client = new PolygonRestClient(polygonKey);

        // create an array list to hold the data
        ArrayList<AggregateDTO> barData= new ArrayList<AggregateDTO>();
        while (startDate.compareTo(endDate)<=0) {
            var results = client.getGroupedDailyAggregatesBlocking(new GroupedDailyParameters("us", "stocks", startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), false));
            barData.addAll(results.getResults());
            startDate = startDate.plusDays(1);
        }
        var infos = barData.stream().map(dto -> new StockBar(dto.getTicker(),
                dto.getClose(),
                dto.getHigh(),
                dto.getLow(),
                dto.getOpen(),
                ZonedDateTime.ofInstant(Instant.ofEpochMilli(dto.getTimestampMillis()), ZoneId.of("Z")),
                dto.getVolume()
        )).collect(Collectors.toList());
        return infos;
    }
}
