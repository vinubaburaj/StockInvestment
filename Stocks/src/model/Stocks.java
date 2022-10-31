package model;

import java.net.MalformedURLException;
import java.net.URL;
import enums.stockTicker;

public class Stocks {
  String stockName;
  stockTicker stockSymbol;
  String purchaseDate;
  int numberOfShares;
  String valueOfShare;

  public Stocks(String[] lines, String ticker){
    this.purchaseDate = lines[0];
    this.valueOfShare = lines[4];

  }

  public Stocks(stockTicker ticker, int numberOfShares){
    this.stockSymbol = ticker;
    this.numberOfShares = numberOfShares;
  }

  private void getStockData(){
    String apiKey = "W0M1JOKC82EZEQA8";
    URL url = null;

    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=compact"
              + "&symbol"
              + "=" + this.stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

  }
}
