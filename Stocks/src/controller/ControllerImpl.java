package controller;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
  public void start() {
    View view = new ViewImpl();
    view.showMenu();
    Scanner scan = new Scanner(this.in);
    int choice = scan.nextInt();
    switch(choice){
      case 1: {
        System.out.println("Enter portfolio name:");
        String portfolioName = scan.next();
        List<Stocks> stocks = createPortfolioController();
        Portfolio portfolio = new PortfolioImpl();
        portfolio.createPortfolio(stocks,portfolioName);
      }
        break;
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
      int numberOfShares = scan.nextInt();
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
          run = false;
        }
        break;
      }
    }
      return stocks;
  }
}
