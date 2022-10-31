package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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

  /**
   * Method that fetches the data of a stock from an API and stores
   * it in its fields.
   */
  void fetchStockData(){
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

    InputStream in = null;
    try {
      in = url.openStream();
      Reader reader = new InputStreamReader(in);
      BufferedReader br = new BufferedReader(reader);
      br.readLine();
      String line = br.readLine();
      String[] lines = new String[6];

      while (line != null) {
        lines = line.split(",");
        break;
      }
      this.setPurchaseDate(lines[0]);
      this.setValueOfShare(lines[4]);
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + this.stockSymbol);
    }
  }

  String getTicker(){
    return this.stockSymbol.toString();
  }

  int getNumberOfShares(){
    return this.numberOfShares;
  }

  private void setValueOfShare(String value){
    this.valueOfShare = value;
  }

  private void setPurchaseDate(String date){
    this.purchaseDate = date;
  }

  String getValueOfShare(){
    return this.valueOfShare;
  }
}
