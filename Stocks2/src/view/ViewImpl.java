package view;

import java.time.LocalDate;
import java.util.List;

import enums.stockTicker;

/**
 * This view class decides how the user sees outputs produced by the program.
 */
public class ViewImpl implements View {
  @Override
  public String showMenu() {
    String s = "\n  What are you looking for today? \n"
            + "1. Create an inflexible portfolio. \n"
            + "2. View composition of a portfolio. \n"
            + "3. Get total value of a portfolio on a specified date. \n"
            + "4. Create a flexible portfolio. \n"
            + "5. Buy shares in a flexible portfolio. \n"
            + "6. Sell shares of a flexible portfolio. \n"
            + "7. Change commission value. \n"
            + "8. Exit the program. \n"
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
  public String showPortfolio(List<String[]> stocks) {

    StringBuilder sb = new StringBuilder();
    sb.append("\nPortfolio contains the following stocks and its details: \n");
    for (String[] stock : stocks) {
      sb.append("\n").append("Stock Name: ").append(stock[1]).append("\n")
              .append("Number of shares purchased: ").append(stock[2]).append("\n")
              .append("Value purchased at: ").append(stock[3]).append("\n").
              append("Date purchased on: ").append(stock[4]).append("\n");
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
    
  }
}
