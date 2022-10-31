package enums;

public enum stockTicker {
  MSFT("Microsoft"),
  GOOGL("Google"),
  AAPL("Apple"),
  IBM("IBM"),
  MS("Morgan Stanley");

  private final String stockName;

  public String getStockName(){
    return this.stockName;
  }

  stockTicker(String name){
    this.stockName = name;
  }
}
