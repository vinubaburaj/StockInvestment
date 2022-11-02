package controller;

import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

import view.View;
import view.ViewImpl;
import model.Stocks;
import model.PortfolioImpl;
import model.Portfolio;
import enums.stockTicker;


public class ControllerImpl implements Controller {
  final InputStream in;
  final PrintStream out;

  private static final LocalDate dateToday = LocalDate.parse("2022-10-28");
  private static final LocalDate lastHistoricDate = LocalDate.parse("2022-06-08");

  public ControllerImpl(InputStream in, PrintStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void start() throws IllegalArgumentException {
    View view = new ViewImpl();
    boolean run = true;
    while (run) {
      view.showMenu();
      Scanner scan = new Scanner(this.in);
      int choice = 8;
      try {
        String stringChoice = scan.nextLine();
        choice = Integer.parseInt(stringChoice);
      } catch (Exception e) {
        view.displayErrorMessage("Invalid entry. Please choose "
                + "a number to enter your choice.");
        this.start();
        return;
      }
      switch (choice) {
        case 1: {
          view.inputPortfolioName();
          String portfolioName = scan.nextLine();
          if (checkFileExists(portfolioName)) {
            view.displayErrorMessage("Portfolio with name "
                    + "already exists. Please choose another name.");
            this.start();
            return ;
          }
          List<Stocks> stocks = createPortfolioController();
          if (stocks.size() > 0) {
            Portfolio portfolio = new PortfolioImpl();
            portfolio.createPortfolio(stocks, portfolioName);
            view.createSuccessfulMessage();
          } else {
            view.createUnsuccessfulMessage();
          }
        }
        break;

        case 2: {
          view.inputPortfolioName();
          String portfolioName = scan.next();
          if (!checkFileExists(portfolioName)) {
            view.displayErrorMessage("Portfolio with name "
                    + portfolioName + " doesn't exist");
            this.start();
            return ;
          }
          examinePortfolioController(portfolioName);
          break;
        }

        case 3: {
          view.inputPortfolioName();
          String portfolioName = scan.next();
          if (!checkFileExists(portfolioName)) {
            view.displayErrorMessage("Portfolio name: "
                    + portfolioName + " doesn't exist");
            this.start();
            return ;
          }
          view.inputDate();
          String date = scan.next();
          getTotalPortfolioValueController(portfolioName, date);
          break;
        }

        case 4: {
          run = false;
          break;
        }

        default: {
          view.displayErrorMessage("Invalid choice selected. Please choose "
                  + "an option from the list provided");
          this.start();
          return ;
        }
      }
    }

  }

  private List<Stocks> createPortfolioController() throws IllegalArgumentException {
    boolean run = true;
    Scanner scan = new Scanner(this.in);
    List<Stocks> stocks = new ArrayList<>();
    HashMap<stockTicker, Integer> uniqueTickers = new HashMap<>();

    while (run) {
      View view = new ViewImpl();
      view.showStockOptions();
      stockTicker stockChoice;
      String stringStockChoice = scan.nextLine();

      if (!stringStockChoice.equalsIgnoreCase("Quit")) {
        try {
          stockChoice = stockTicker.valueOf(stringStockChoice);
        } catch (Exception e) {
          throw new IllegalArgumentException("Invalid entry for stock choice. "
                  + "Please provide valid stock ticker from the list provided.");
        }
        int numberOfShares = 0;
        view.showNumberOfSharesMessage();
        String stringNumberOfShares = scan.nextLine();
        try {
          numberOfShares = Integer.parseInt(stringNumberOfShares);
        } catch (Exception e) {
          throw new IllegalArgumentException("Invalid entry for number of "
                  + "shares. Please enter a whole number value of number of "
                  + "shares to be bought");
        }
        getUniqTicks(uniqueTickers, stockChoice, numberOfShares);

      } else {
        run = false;

      }
    }

    for (Map.Entry<stockTicker, Integer> entry : uniqueTickers.entrySet()) {
      Stocks stock = new Stocks(entry.getKey(), entry.getValue());
      stocks.add(stock);
    }
    return stocks;
  }

  private void getUniqTicks(HashMap<stockTicker,
          Integer> u, stockTicker ticker, int numOfShares) {
    if (u.containsKey(ticker)) {
      int n = u.get(ticker);
      u.put(ticker, n + numOfShares);
    } else {
      u.put(ticker, numOfShares);
    }
  }

  private boolean checkFileExists(String portfolioName) {
    File filePath = new File("src/allUserPortfolios/user1_portfolios/"
            + portfolioName + ".xml");
    return filePath.exists();
  }

  private void examinePortfolioController(String portfolioName) {
    Portfolio portfolio = new PortfolioImpl();
    List<String[]> stocks = portfolio.examinePortfolio(portfolioName);

    View view = new ViewImpl();
    view.showPortfolio(stocks);

  }

  private void getTotalPortfolioValueController(String portfolioName, String date) {
    boolean isValidDate = checkDateValidity(date);
    View view = new ViewImpl();
    if (isValidDate) {
      String getDateToSearch = getDateToLook(date);
      Portfolio portfolio = new PortfolioImpl();
      List<String[]> stocks = portfolio.examinePortfolio(portfolioName);
      Double totalValue = portfolio.getTotalValue(stocks, getDateToSearch);

      view.showTotalValue(portfolioName, getDateToSearch, totalValue);
    } else {
      view.showInvalidDateMessage(dateToday, lastHistoricDate);
    }
  }


  private static boolean checkDateValidity(String date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    format.setLenient(false);
    try {
      format.parse(date);
    } catch (ParseException e) {
      return false;
    }
    LocalDate dateCur = LocalDate.parse(date);
    int compareDateToToday = dateCur.compareTo(dateToday);
    int compareDateToLast = dateCur.compareTo(lastHistoricDate);
    return compareDateToLast > 0 && compareDateToToday <= 0;
  }

  private static String getDateToLook(String date) {
    LocalDate dateCur = LocalDate.parse(date);

    if ("SUNDAY".equals(dateCur.getDayOfWeek().toString())) {
      return String.valueOf(dateCur.minusDays(2));
    } else if ("SATURDAY".equals(dateCur.getDayOfWeek().toString())) {
      return String.valueOf(dateCur.minusDays(1));
    } else {
      return date;
    }
  }

}
