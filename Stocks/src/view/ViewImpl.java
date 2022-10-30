package view;

import java.util.Scanner;

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
}
