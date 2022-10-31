package view;

import enums.stockTicker;


public class ViewImpl implements View {
  @Override
  public void showMenu() {

    System.out.println("\n What are you looking for today? ");
    System.out.println(
             "1. Create a portfolio. \n"
            + "2. View all portfolios. \n"
            + "3. View a portfolio. \n"
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
}
