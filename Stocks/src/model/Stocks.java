package model;

import java.io.BufferedReader;
import java.io.FileReader;
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
  String date;
  int numberOfShares;
  String valueOfShare; // price


//  public Stocks(String[] lines, String ticker){
//    this.date = lines[0];
//    this.valueOfShare = lines[4];
//
//  }

  public Stocks(stockTicker ticker, int numberOfShares){
    this.stockSymbol = ticker;
    this.numberOfShares = numberOfShares;
  }

  /**
   * Method that fetches the data of a stock from an API and stores
   * it in its fields.
   */
  void fillStockData(String date){

      String pathToFile = "src/stocksData_csv/daily_"+this.stockSymbol+".csv";
//      System.out.println(pathToFile);
    this.setDate(date);
      BufferedReader reader = null;
      String line = "";
//      String[] lines = new String[6];
      try {
        reader = new BufferedReader(new FileReader(pathToFile));
        while ((line = reader.readLine()) != null) {
          String[] lineArray = line.split(",");
          if (lineArray[0].equals(this.date)) {
            try {
              this.setValueOfShare(lineArray[4]);
            } catch (NumberFormatException e) {
              System.out.println(e.getMessage());
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
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
  }

  String getTicker(){
    return this.stockSymbol.toString();
  }

  int getNumberOfShares(){
    return this.numberOfShares;
  }
  String getValueOfShare(){
    return this.valueOfShare;
  }

  String getDate() {return this.date;}

  private void setValueOfShare(String value){
    this.valueOfShare = value;
  }

  private void setDate(String date){
    this.date = date;
  }


}
