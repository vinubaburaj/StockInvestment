package view;

import java.time.LocalDate;
import java.util.List;

import enums.stockTicker;


public class ViewImpl implements View {
  @Override
  public String showMenu() {
    String s = "\n \n What are you looking for today? \n"
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
    s.append("Type the stock TICKER, after which enter the number of shares needed\n") ;
//    System.out.println("Type the stock TICKER, after which"
//            + " enter the number of shares needed");
    for (stockTicker st : stockTicker.values()) {
      s.append(st.getStockName() + "( Ticker: " + st + ") \n");
//      System.out.println(i++ + ". " + st.getStockName() + "( Ticker: " + st + ")");
    }
    s.append("Finished adding stocks, create portfolio now. (Type Quit)\n");
//    System.out.println(i + ". " + "Finished adding stocks, create portfolio now. (Type Quit)");
    return s.toString();
  }

  @Override
  public String showNumberOfSharesMessage() {

    String s = "Enter the number of shares you want to buy of this stock: \n"
            +" (Note: You can only buy whole number amount of shares) \n";
//    System.out.println("Enter the number of shares you want to buy "
//            + "of this stock: ");
//    System.out.println("(Note: You can only buy whole number amount "
//            + "of shares)");
    return s;
  }

  @Override
  public String showPortfolio(List<String[]> stocks) {

    StringBuilder sb = new StringBuilder();
    sb.append("Portfolio contains the following stocks and its details: \n");
    for (String[] stock : stocks) {
      sb.append("Stock Name: ").append(stock[1]).append("\n")
              .append("Number of shares purchased: ").append(stock[2]).append("\n")
              .append("Value purchased at: ").append(stock[3]).append("\n").
              append("Date purchased on: ").append(stock[4]).append("\n");
    }
    return sb.toString();
  }

  @Override
  public String inputPortfolioName() {
    return "Enter the name of portfolio: ";
  }

  @Override
  public String inputDate() {
    return "Enter the date in YYYY-MM-DD format for which "
            + "you want to calculate the total value of the portfolio.";
  }

  @Override
  public String showTotalValue(String portfolioName, String date, Double totalValue) {
    return "Total value of portfolio " + portfolioName
            + " on " + date + " is: " + totalValue;
  }

  @Override
  public String showInvalidDateMessage(LocalDate dateToday,
                                     LocalDate lastHistoricDate) {
    return "Please enter a valid date in YYYY-MM-DD format between "
            + lastHistoricDate + " and " + dateToday;
  }

  @Override
  public String createSuccessfulMessage() {
    return "Successfully created portfolio.";
  }

  @Override
  public String createUnsuccessfulMessage() {
    return "No stocks entered.";
  }


  /* (Check if this is needed.) */
  @Override
  public String displayErrorMessage(String error){

    return error;
  }
}
