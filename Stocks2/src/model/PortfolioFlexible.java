package model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import enums.stockTicker;
import utility.WorkWithXML;

public class PortfolioFlexible extends AbstractPortfolio{

  private float commission = 10;

  public void createPortfolio(List<Stocks> stocks, String portfolioName){

    LocalDate dateObj = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String dateToday = dateObj.format(formatter);

    ArrayList<HashMap<String, String>> stocksList =
            createPortfolioAbs(stocks, dateToday, commission);
    //    String absolutePath = System.getProperty("user.dir");
    //    String osSeperator = System.getProperty("file.separator");
    //    String finalPath = absolutePath + osSeperator + "allUserPortfolios" + osSeperator
    //            + "user1_portfolios" + osSeperator
    //            + portfolioName + ".xml";

    String finalPath = "src/allUserPortfolios/flexiblePortfolios/" + portfolioName + ".xml";

    WorkWithXML p = new WorkWithXML(finalPath, portfolioName);
    p.create(stocksList);
  }

  public boolean purchaseStocks(List<HashMap<String, String>> stocks, String portfolioName){
    for(HashMap<String, String> i: stocks){
      i.put("Commission", String.valueOf(commission));
    }
    //    String absolutePath = System.getProperty("user.dir");
    //    String osSeperator = System.getProperty("file.separator");
    //    String finalPath = absolutePath + osSeperator + "allUserPortfolios" + osSeperator
    //            + "flexiblePortfolios" + osSeperator
    //            + portfolioName + ".xml";

    String finalPath = "src/allUserPortfolios/flexiblePortfolios/" + portfolioName + ".xml";
    WorkWithXML p = new WorkWithXML(finalPath, portfolioName);

    return p.update(stocks);
  }

  public boolean sellStocks(stockTicker tick, int numOfShares, String date, String portfolioName){
    HashMap<String, String> stock = new HashMap<>();
    stock.put("Stock-ticker", String.valueOf(tick));
    stock.put("Number-of-shares", String.valueOf(numOfShares * -1));
    stock.put("Date", date);
    stock.put("Commission", String.valueOf(commission));

    List<HashMap<String, String>> stocks= new ArrayList<>();
    stocks.add(stock);

    String finalPath = "src/allUserPortfolios/flexiblePortfolios/" + portfolioName + ".xml";
    WorkWithXML p = new WorkWithXML(finalPath, portfolioName);

    return p.update(stocks);
  }

  //date later than creation date
  public double findCostBasis(String date, String portfolioName) throws IOException {
    String finalPath = "src/allUserPortfolios/flexiblePortfolios/" + portfolioName + ".xml";
    WorkWithXML p = new WorkWithXML(finalPath, portfolioName);
    double totalCommission = 0;
    List<String[]> stocks = p.read(true);
    LocalDate dateFormatted = LocalDate.parse(date);

    double sumOfAllPurchased = 0;

    for(int i=0;i<stocks.size();i++){
      totalCommission = totalCommission + Double.parseDouble(stocks.get(i)[3]);
      Stocks stock = new Stocks(stockTicker.valueOf(stocks.get(i)[0]),
              Integer.parseInt(stocks.get(i)[1]));
      stock.fillStockData(stocks.get(i)[2], true);

      int isBefore = dateFormatted.compareTo(LocalDate.parse(stock.getDate()));
      if(isBefore == 1 || isBefore == 0){
        sumOfAllPurchased = sumOfAllPurchased + (stock.getNumberOfShares()
                * Double.parseDouble(stock.getValueOfShare()));
      }
    }
    return totalCommission + sumOfAllPurchased;
  }

  public Double getTotalValue(List<String[]> stocks, String date) {
    // function to get the total value of a portfolio.
    double totalValue = 0;
    return totalValue;
  }

  public void changeCommission(float c){
    this.commission = c;
  }

}
