package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import enums.stockTicker;

public class Stocks {
  stockTicker stockSymbol;
  String date;
  int numberOfShares;
  String valueOfShare;

  /**
   * Public constructor that takes in two parameters to create
   * a new Stocks object.
   *
   * @param ticker         the ticker value of this stock or the symbol of the stock.
   * @param numberOfShares number of shares of this stock.
   */
  public Stocks(stockTicker ticker, int numberOfShares) {
    this.stockSymbol = ticker;
    this.numberOfShares = numberOfShares;
  }

  /**
   * Method that fetches the data of a stock from an API and stores
   * it in its fields.
   */
  void fillStockData(String date) {

    String pathToFile = "src/stocksData_csv/daily_" + this.stockSymbol + ".csv";
    this.setDate(date);
    BufferedReader reader = null;
    String line = "";
    try {
      reader = new BufferedReader(new FileReader(pathToFile));
      while ((line = reader.readLine()) != null) {
        String[] lineArray = line.split(",");
        if (lineArray[0].equals(this.date)) {
          try {
            this.setValueOfShare(lineArray[4]);
          } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  String getTicker() {
    return this.stockSymbol.toString();
  }

  int getNumberOfShares() {
    return this.numberOfShares;
  }

  String getValueOfShare() {
    return this.valueOfShare;
  }

  String getDate() {
    return this.date;
  }

  private void setValueOfShare(String value) {
    this.valueOfShare = value;
  }

  private void setDate(String date) {
    this.date = date;
  }

}
