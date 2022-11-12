package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import enums.stockTicker;
import utility.WorkWithXML;

abstract class AbstractPortfolio implements Portfolio {

  public void createPortfolio(List<Stocks> stocks, String portfolioName) {
    String lastDate = "2022-11-02";
    ArrayList<HashMap<String, String>> stocksList = new ArrayList<>();
    for (Stocks stock : stocks) {
      stock.fillStockData(lastDate);
      HashMap<String, String> stockMap = new HashMap<>();
      stockMap.put("Date", stock.getDate());
      stockMap.put("Stock-ticker", stock.getTicker());
      stockMap.put("Shares-owned", String.valueOf(stock.getNumberOfShares()));

      stocksList.add(stockMap);
    }

    //    String absolutePath = System.getProperty("user.dir");
    //    String osSeperator = System.getProperty("file.separator");
    //    String finalPath = absolutePath + osSeperator + "allUserPortfolios" + osSeperator
    //            + "user1_portfolios" + osSeperator
    //            + portfolioName + ".xml";

    String finalPath = "src/allUserPortfolios/user1_portfolios/" + portfolioName + ".xml";

    WorkWithXML p = new WorkWithXML(finalPath, portfolioName);
    p.create(stocksList);
  }

  public List<String[]> examinePortfolio(String portfolioName) {
    //    String absolutePath = System.getProperty("user.dir");
    //    String osSeperator = System.getProperty("file.separator");
    //    String path = absolutePath + osSeperator + "allUserPortfolios" + osSeperator
    //            + "user1_portfolios" + osSeperator
    //            + portfolioName + ".xml";
    String path = "src/allUserPortfolios/user1_portfolios/" + portfolioName + ".xml";
    WorkWithXML p = new WorkWithXML(path, portfolioName);

    List<String[]> stocksRaw = p.read();
    List<String[]> stocks = new ArrayList<>();

    for(String[] s : stocksRaw){
      if(stocksRaw.size() > 0){
        String ticker = s[0];
        stockTicker stockTickerVal = stockTicker.valueOf(ticker);
        String numberOfShares = s[1];
        String date = s[2];
        Stocks stock = new Stocks(stockTickerVal, Integer.parseInt(numberOfShares));
        stock.fillStockData(date);

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

  public Double getTotalValue(List<String[]> stocks, String date) {
    // function to get the total value of a portfolio.
    double totalValue = 0;
    for (String[] stock : stocks) {
      Stocks s = new Stocks(stockTicker.valueOf(stock[0]), Integer.parseInt(stock[2]));
      if (s.fillStockData(date)) {
        totalValue += Double.parseDouble(s.getValueOfShare()) * s.getNumberOfShares();
      }
    }
    return totalValue;
  }

}
