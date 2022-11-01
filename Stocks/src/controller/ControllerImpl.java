package controller;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

import view.View;
import view.ViewImpl;
import model.Stocks;
import model.PortfolioImpl;
import model.Portfolio;
import enums.stockTicker;


public class ControllerImpl implements Controller{
  final InputStream in;
  final PrintStream out;
  public ControllerImpl(InputStream in, PrintStream out){
    this.in = in;
    this.out = out;
  }

  @Override
  public void start() throws IllegalArgumentException{
    View view = new ViewImpl();
    view.showMenu();
    Scanner scan = new Scanner(this.in);
    int choice = scan.nextInt();
    switch(choice) {
      case 1: {
        view.inputPortfolioName();
//        System.out.println("Enter new portfolio name:");
        String portfolioName = scan.next();
        List<Stocks> stocks = createPortfolioController();
        Portfolio portfolio = new PortfolioImpl();
        portfolio.createPortfolio(stocks, portfolioName);
      }
      break;

      case 2: {
        view.inputPortfolioName();
//        System.out.println("Enter the name of portfolio to fetch:");
        String portfolioName = scan.next();
          if (!checkFileExists(portfolioName)) {
            throw new IllegalArgumentException("Portfolio name: "
                    + portfolioName + " doesn't exist");
          }

          examinePortfolioController(portfolioName);
        break;
      }

      case 3: {
        view.inputPortfolioName();
        String portfolioName = scan.next();
        if (!checkFileExists(portfolioName)) {
          throw new IllegalArgumentException("Portfolio name: "
                  + portfolioName + " doesn't exist");
        }
        view.inputDate();
        String date = scan.next();
        getTotalPortfolioValueController(portfolioName, date);
        break;
      }

      default: {
        throw new IllegalArgumentException("Invalid option chosen.");
      }
    }

//    char choice = scan.next().CharAt(0);
//    this.out.println("Option chosen: " + option);
  }

  private List<Stocks> createPortfolioController(){
    boolean run = true;
    Scanner scan = new Scanner(this.in);
    List<Stocks> stocks = new ArrayList<>();
    while(run){
      View view = new ViewImpl();
      view.showStockOptions();
      int stockChoice = scan.nextInt();
      int numberOfShares = 0;
      if(stockChoice != 6){
        view.showNumberOfSharesMessage();
        numberOfShares = scan.nextInt();
      }
      switch(stockChoice){
        case 1: {
          Stocks s = new Stocks(stockTicker.MSFT, numberOfShares);
          stocks.add(s);
        }
        break;
        case 2: {
          Stocks s = new Stocks(stockTicker.GOOGL, numberOfShares);
          stocks.add(s);
        }
        break;
        case 3: {
          Stocks s = new Stocks(stockTicker.AAPL, numberOfShares);
          stocks.add(s);
        }
        break;
        case 4: {
          Stocks s = new Stocks(stockTicker.IBM, numberOfShares);
          stocks.add(s);
        }
        break;
        case 5: {
          Stocks s = new Stocks(stockTicker.MS, numberOfShares);
          stocks.add(s);
        }
        break;
        case 6: {
          run = false;
        }
      }
    }
      return stocks;
  }

  private boolean checkFileExists(String portfolioName){
    File filePath = new File("src/allUserPortfolios/user1_portfolios/" + portfolioName);
    return filePath.exists();
  }

  private void examinePortfolioController(String portfolioName){
    Portfolio portfolio = new PortfolioImpl();
    List<String[]> stocks = portfolio.examinePortfolio(portfolioName);

    View view = new ViewImpl();
    view.showPortfolio(stocks);

  }

  private void getTotalPortfolioValueController(String portfolioName, String date){
    Portfolio portfolio = new PortfolioImpl();
    List<String[]> stocks = portfolio.examinePortfolio(portfolioName);
    double totalValue = 0;
    for(String[] stock : stocks){

//      stockTicker st = new stockTicker("Microsoft");
//      System.out.println("Here");
//      stockTicker st = stockTicker.valueOf("MSFT");
//      System.out.println("Enum val: " + st.getClass().getSimpleName());
//      System.out.println("Enum printing: " + st);

      Stocks s = new Stocks(stockTicker.valueOf(stock[0]), Integer.parseInt(stock[2]));
      ((PortfolioImpl) portfolio).fillStockData(s, date);
      totalValue += Double.parseDouble(s.getValueOfShare()) * s.getNumberOfShares();
    }
    View view = new ViewImpl();
    view.showTotalValue(portfolioName, date, totalValue);
  }
}
