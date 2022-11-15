package view;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.stockTicker;

/**
 * This view class decides how the user sees outputs produced by the program.
 */
public class ViewImpl implements View {
  @Override
  public String showMenu() {
    String s = "\n  You can choose the following options for an inflexible portfolio: \n"
            + "1. Create an inflexible portfolio. \n"
            + "2. View composition of a portfolio. \n"
            + "3. Get total value of an inflexible portfolio on a specified date. \n"
            + "\n You can choose the following options for a flexible portfolio: \n"
            + "4. Create a flexible portfolio. \n"
            + "5. Buy shares in a flexible portfolio. \n"
            + "6. Sell shares of a flexible portfolio. \n"
            + "7. View composition of a flexible portfolio. \n"
            + "8. Get total value of a flexible portfolio on a specified date. \n"
            + "9. View cost basis of a portfolio. \n"
            + "10. Change commission value. \n"
            + "\n11. Exit the program. \n"
            + "\n"
            + "Choose an option number: \n";
    return s;
  }

  @Override
  public String showStockOptions() {
    int i = 1;
    StringBuilder s = new StringBuilder();
    s.append("\nType the stock TICKER\n");
    for (stockTicker st : stockTicker.values()) {
      s.append(st.getStockName()).
              append("( Ticker: ").
              append(st).
              append(") \n");
    }
    s.append("Finished adding stocks, create portfolio now. (Type Quit)\n");
    return s.toString();
  }


  @Override
  public String showNumberOfSharesMessage() {
    String s = "\nEnter the number of shares you want to buy of this stock: \n"
            + " (Note: You can only buy whole number amount of shares) \n";
    return s;
  }

  @Override
  public String showDateMessage(LocalDate dateToday,
                                LocalDate lastHistoricDate) {
    return ("Please enter a valid date in YYYY-MM-DD format between "
            + lastHistoricDate + " and " + dateToday + " (Must be a business day)\n");
  }

  @Override
  public String showPortfolio(HashMap<String,Integer> stocks) {

    StringBuilder sb = new StringBuilder();
    sb.append("\nPortfolio contains the following stocks: \n");
//    for (String[] stock : stocks) {
//      sb.append("\n").append("Stock Name: ").append(stock[1]).append("\n")
//              .append("Number of shares purchased: ").append(stock[2]).append("\n")
//              .append("Value purchased at: ").append(stock[3]).append("\n").
//              append("Date purchased on: ").append(stock[4]).append("\n");
//    }
    for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
//      Stocks stock = new Stocks(stockTicker.valueOf(entry.getKey()), entry.getValue());
      sb.append(entry.getKey() + " : " + entry.getValue()+ "\n");
    }

    return sb.toString();
  }

  @Override
  public String inputPortfolioName() {
    return "\nEnter the name of portfolio: ";
  }

  @Override
  public String showTotalValue(String portfolioName, String date, Double totalValue) {
    return "\nTotal value of portfolio " + portfolioName
            + " on " + date + " is: " + totalValue + "\n";
  }


  @Override
  public String createSuccessfulMessage() {
    return "\nSuccessfully created portfolio.\n";
  }

  @Override
  public String createUnsuccessfulMessage() {
    return "\nNo stocks entered.\n";
  }


  @Override
  public String displayErrorMessage(String error) {

    return error;
  }

  @Override
  public String showPortfolioOptions(){
    return null;
  }
}
