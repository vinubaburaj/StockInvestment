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
      stockMap.put("Shares-owned", String.valueOf(stock.getNumberOfShares()));

      if(commission != -1){
        stockMap.put("Commission", String.valueOf(commission));
      }
      stocksList.add(stockMap);
    }

    return stocksList;
  }

  public List<String[]> examinePortfolio(String portfolioName, boolean flexible) throws IOException {
    //    String absolutePath = System.getProperty("user.dir");
    //    String osSeperator = System.getProperty("file.separator");
    //    String path = absolutePath + osSeperator + "allUserPortfolios" + osSeperator
    //            + "user1_portfolios" + osSeperator
    //            + portfolioName + ".xml";
    String path = "src/allUserPortfolios/user1_portfolios/" + portfolioName + ".xml";
    WorkWithXML p = new WorkWithXML(path, portfolioName);

    List<String[]> stocksRaw = p.read(flexible);
    List<String[]> stocks = new ArrayList<>();

    for(String[] s : stocksRaw){
      if(stocksRaw.size() > 0){
        String ticker = s[0];
        stockTicker stockTickerVal = stockTicker.valueOf(ticker);
        String numberOfShares = s[1];
        String date = s[2];
        Stocks stock = new Stocks(stockTickerVal, Integer.parseInt(numberOfShares));
        stock.fillStockData(date, flexible);

        String[] stockDisplay = new String[5];
        stockDisplay[0] = String.valueOf(stockTickerVal);
        stockDisplay[1] = stockTickerVal.getStockName();
        stockDisplay[2] = numberOfShares;
        stockDisplay[3] = stock.getValueOfShare();
        stockDisplay[4] = date;
        stocks.add(stockDisplay);
      }
    }
    return stocks;
  }

}
