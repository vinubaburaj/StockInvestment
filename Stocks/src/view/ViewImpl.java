package view;

import java.time.LocalDate;
import java.util.List;

import enums.stockTicker;


public class ViewImpl implements View {
  @Override
  public String showMenu() {
    String s = "\n  What are you looking for today? \n"
            + "1. Create a portfolio. \n"
            + "2. View composition of a portfolio. \n"
            + "3. Get total value of a portfolio on a specified date. \n"
            + "4. Exit the program \n"
            + "\n"
            + "Choose an option number: ";
    return s;
  }

  @Override
  public String showStockOptions() {
    int i = 1;
    StringBuilder s = new StringBuilder();
    s.append("\nType the stock TICKER, after which enter the number of shares needed\n");
    for (stockTicker st : stockTicker.values()) {
      s.append(st.getStockName()).
              append("( Ticker: ").
              append(st).
              append(") \n");
    }
    s.append("Finished adding stocks, create portfolio now. (Type Finish)\n");
    return s.toString();
  }

  @Override
  public String showNumberOfSharesMessage() {

    String s = "\nEnter the number of shares you want to buy of this stock: \n"
            + " (Note: You can only buy whole number amount of shares) \n";
    return s;
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
  public String inputDate() {
    return "\nEnter the date in YYYY-MM-DD format for which "
            + "you want to calculate the total value of the portfolio.\n";
  }

  @Override
  public String showTotalValue(String portfolioName, String date, Double totalValue) {
    return "\nTotal value of portfolio " + portfolioName
            + " on " + date + " is: " + totalValue + "\n";
  }

  @Override
  public String showInvalidDateMessage(LocalDate dateToday,
                                       LocalDate lastHistoricDate) {
    return "\nPlease enter a valid date in YYYY-MM-DD format between "
            + lastHistoricDate + " and " + dateToday + "\n";
  }

  @Override
  public String createSuccessfulMessage() {
    return "\nSuccessfully created portfolio.\n";
  }

  @Override
  public String createUnsuccessfulMessage() {
    return "\nNo stocks entered.\n";
  }


  /* (Check if this is needed.) */
  @Override
  public String displayErrorMessage(String error) {

    return error;
  }
}
