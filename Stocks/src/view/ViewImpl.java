package view;

import java.util.List;

import enums.stockTicker;


public class ViewImpl implements View {
  @Override
  public void showMenu() {

    System.out.println("\n What are you looking for today? ");
    System.out.println(
             "1. Create a portfolio. \n"
            + "2. View composition of a portfolio. \n"
            + "3. Get total value of a portfolio on a specified date. \n"
            + "\n"
            + "Choose an option number: "
    );
  }

  @Override
  public void viewAllPortfolio() {

  }

  @Override
  public void viewPortfolio() {

  }
  @Override
  public void showStockOptions(){
    int i=1;
    System.out.println("Choose a stock number from the following, after which" +
            " enter the number of shares needed");
      for(stockTicker st : stockTicker.values()){
        System.out.println(i++ + ". " + st.getStockName());
      }
      System.out.println(i + ". " + "Finished adding stocks, create portfolio now.");
  }

  public void showNumberOfSharesMessage(){
    System.out.println("Enter the number of shares you want to buy "
    + "of this stock: ");
    System.out.println("(Note: You can only buy whole number amount "
            +"of shares)");
  }

  @Override
  public void showPortfolio(List<String[]> stocks) {
    System.out.println("Portfolio contains the following stocks and its details: \n");
    for(String[] stock : stocks){
      System.out.println("Stock Name: " + stock[1]  + "\n"
              + "Number of shares purchased: " + stock[2] + "\n"
              + "Value purchased at: " + stock[3] + "\n"
              + "Date purchased on: " + stock[4] + "\n");
    }
  }

  @Override
  public void inputPortfolioName() {
    System.out.println("Enter the name of portfolio: ");
  }

  @Override
  public void inputDate(){
    System.out.println("Enter the date in YYYY-MM-DD format for which "
            + "you want to calculate the total value of the portfolio.");
  }

  @Override
  public void showTotalValue(String portfolioName,String date, Double totalValue) {
    System.out.println("Total value of portfolio " + portfolioName
            + " on " + date + " is: " + totalValue);
  }
}
