package model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import enums.stockTicker;
import utility.WorkWithXML;

abstract class AbstractPortfolio implements Portfolio {

  public ArrayList<HashMap<String, String>> createPortfolioAbs(List<Stocks> stocks, String date, float commission) {
    ArrayList<HashMap<String, String>> stocksList = new ArrayList<>();
    for (Stocks stock : stocks) {
//      stock.fillStockData(date);
      HashMap<String, String> stockMap = new HashMap<>();
      stockMap.put("Date", date);
      stockMap.put("Stock-ticker", stock.getTicker());
      stockMap.put("Number-of-shares", String.valueOf(stock.getNumberOfShares()));

      if(commission != -1){
        stockMap.put("Commission", String.valueOf(commission));
      }
      stocksList.add(stockMap);
    }

    return stocksList;
  }


  public HashMap<String, Integer> examinePortfolioAbs(String portfolioName, String path)
          throws IOException {
    WorkWithXML p = new WorkWithXML(path, portfolioName);
    List<HashMap<String, String>> allStocksData = p.read();
    HashMap<String, Integer> allTickerQuant = new HashMap<>();

    for (HashMap<String, String> s : allStocksData) {
      if (allTickerQuant.containsKey(s.get("Stock ticker"))) {
        allTickerQuant.put(s.get("Stock ticker"), allTickerQuant.get(s.get("Stock ticker"))
                + Integer.parseInt(s.get("Number of shares")));
      } else {
        allTickerQuant.put(s.get("Stock ticker"), Integer.parseInt(s.get("Number of shares")));
      }
    }
    return allTickerQuant;
  }

}
