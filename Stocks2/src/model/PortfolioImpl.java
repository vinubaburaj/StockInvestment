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

  public void createPortfolio(List<Stocks> stocks, String portfolioName){
    String dateToday = "2022-11-02";
    ArrayList<HashMap<String, String>> stocksList = createPortfolioAbs(stocks, dateToday, -1);
    //    String absolutePath = System.getProperty("user.dir");
    //    String osSeperator = System.getProperty("file.separator");
    //    String finalPath = absolutePath + osSeperator + "allUserPortfolios" + osSeperator
    //            + "user1_portfolios" + osSeperator
    //            + portfolioName + ".xml";
    String finalPath = "src/allUserPortfolios/inflexiblePortfolios/" + portfolioName + ".xml";

    WorkWithXML p = new WorkWithXML(finalPath, portfolioName);
    p.create(stocksList);
  }

  public Double getTotalValue(List<String[]> stocks, String date) throws IOException {
    // function to get the total value of a portfolio.
    double totalValue = 0;
    for (String[] stock : stocks) {
      Stocks s = new Stocks(stockTicker.valueOf(stock[0]), Integer.parseInt(stock[2]));
      if (s.fillStockData(date, false)) {
        totalValue += Double.parseDouble(s.getValueOfShare()) * s.getNumberOfShares();
      }
    }
    return totalValue;
  }

}
