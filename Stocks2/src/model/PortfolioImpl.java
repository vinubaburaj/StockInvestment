package model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import enums.stockTicker;
import utility.WorkWithXML;

/**
 * This class contains the implementation of a portfolio.
 * Performs create, view and getValue operations of a portfolio.
 */
public class PortfolioImpl extends AbstractPortfolio{

  //    String absolutePath = System.getProperty("user.dir");
  //    String osSeperator = System.getProperty("file.separator");
  //    String path = absolutePath + osSeperator + "allUserPortfolios" + osSeperator
  //            + "user1_portfolios" + osSeperator;

  private final String finalPath = "src/allUserPortfolios/inflexiblePortfolios/";



  public void createPortfolio(List<Stocks> stocks, String portfolioName){
    String dateToday = "2022-11-02";
    ArrayList<HashMap<String, String>> stocksList = createPortfolioAbs(stocks, dateToday, -1);
    WorkWithXML p = new WorkWithXML(this.finalPath + portfolioName + ".xml", portfolioName);
    p.create(stocksList);
  }



  public HashMap<String, Integer> examinePortfolio(String portfolioName) throws IOException {
    return examinePortfolioAbs(portfolioName, this.finalPath + portfolioName + ".xml");
  }



  public Double getTotalValue(String portfolioName, String date) throws IOException {
    double totalValue = 0;
//    for (HashMap<String, String> stock : stocks) {
//      Stocks s = new Stocks(stockTicker.valueOf(stock.get("Stock ticker")),
//              Integer.parseInt(stock.get("Number of shares")));
//      if (s.fillStockData(date, false)) {
//        totalValue += Double.parseDouble(s.getValueOfShare()) * s.getNumberOfShares();
//      }
//    }
    return totalValue;
  }

}
