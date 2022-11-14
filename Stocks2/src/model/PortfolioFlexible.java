package model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.stockTicker;
import utility.WorkWithXML;

public class PortfolioFlexible extends AbstractPortfolio {

  private float commission = 10;

  private final String finalPath = "src/allUserPortfolios/flexiblePortfolios/";

  //    private final absolutePath = System.getProperty("user.dir");
  //    private final osSeperator = System.getProperty("file.separator");
  //    private final String finalPat = absolutePath + osSeperator + "allUserPortfolios" + osSeperator
  //            + "user1_portfolios" + osSeperator;


  public void createPortfolio(List<Stocks> stocks, String portfolioName) {
    LocalDate dateObj = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String dateToday = dateObj.format(formatter);

    ArrayList<HashMap<String, String>> stocksList =
            createPortfolioAbs(stocks, dateToday, commission);

    WorkWithXML p = new WorkWithXML(this.finalPath + portfolioName + ".xml", portfolioName);
    p.create(stocksList);
  }

  public HashMap<String, Integer> examinePortfolio(String portfolioName) throws IOException {
    return examinePortfolioAbs(portfolioName, this.finalPath + portfolioName + ".xml");
  }

  public boolean purchaseStocks(List<HashMap<String, String>> stocks, String portfolioName) {
    for (HashMap<String, String> i : stocks) {
      i.put("Commission", String.valueOf(commission));
    }

    WorkWithXML p = new WorkWithXML(this.finalPath + portfolioName + ".xml", portfolioName);

    return p.update(stocks);
  }

  public boolean sellStocks(stockTicker tick, int numOfShares, String date, String portfolioName) {
    HashMap<String, String> stock = new HashMap<>();
    stock.put("Stock-ticker", String.valueOf(tick));
    stock.put("Number-of-shares", String.valueOf(numOfShares * -1));
    stock.put("Date", date);
    stock.put("Commission", String.valueOf(commission));

    List<HashMap<String, String>> stocks = new ArrayList<>();
    stocks.add(stock);

    WorkWithXML p = new WorkWithXML(this.finalPath + portfolioName + ".xml", portfolioName);

    return p.update(stocks);
  }

  //date later than creation date
  public double findCostBasis(String date, String portfolioName) throws IOException {
    WorkWithXML p = new WorkWithXML(this.finalPath + portfolioName + ".xml", portfolioName);
    double totalCommission = 0;
    List<HashMap<String, String>> stocks = p.read();
    LocalDate dateFormatted = LocalDate.parse(date);

    double sumOfAllPurchased = 0;

    for (int i = 0; i < stocks.size(); i++) {
      totalCommission = totalCommission + Double.parseDouble(stocks.get(i).get("Commission"));
      Stocks stock = new Stocks(stockTicker.valueOf(stocks.get(i).get("Stock ticker")),
              Integer.parseInt(stocks.get(i).get("Number of shares")));
      stock.fillStockData(stocks.get(i).get("Date of transaction"), true);

      int isBefore = dateFormatted.compareTo(LocalDate.parse(stock.getDate()));
      if (isBefore == 1 || isBefore == 0) {
        sumOfAllPurchased = sumOfAllPurchased + (stock.getNumberOfShares()
                * Double.parseDouble(stock.getValueOfShare()));
      }
    }
    return totalCommission + sumOfAllPurchased;
  }

  public Double getTotalValue(String portfolioName, String date) throws IOException {
    double totalValue = 0;
    WorkWithXML p = new WorkWithXML(this.finalPath + portfolioName + ".xml", portfolioName);
    List<HashMap<String, String>> allStocksData = p.read();
    HashMap<String, Integer> allTickerQuant = new HashMap<>();

    LocalDate dateFormatted = LocalDate.parse(date);

    for (HashMap<String, String> s : allStocksData) {
      if (dateFormatted.compareTo(LocalDate.parse(s.get("Date of transaction"))) > 0) {
        if (allTickerQuant.containsKey(s.get("Stock ticker"))) {
          allTickerQuant.put(s.get("Stock ticker"), allTickerQuant.get(s.get("Stock ticker"))
                  + Integer.parseInt(s.get("Number of shares")));
        } else {
          allTickerQuant.put(s.get("Stock ticker"), Integer.parseInt(s.get("Number of shares")));
        }
      }
    }

    for (Map.Entry<String, Integer> entry : allTickerQuant.entrySet()) {
      Stocks stock = new Stocks(stockTicker.valueOf(entry.getKey()), entry.getValue());
      stock.fillStockData(date, true);

      totalValue += Double.parseDouble(stock.getValueOfShare()) * stock.getNumberOfShares();
    }

    return totalValue;
  }

  public void changeCommission(float c) {
    this.commission = c;
  }

}
