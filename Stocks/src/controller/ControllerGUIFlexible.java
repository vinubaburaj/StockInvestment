package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import enums.stockTicker;
import model.PortfolioFlexibleInterface;
import model.Stocks;
import utility.UtilityClass;
import view.JFrameSwingView;
import view.JFramesList;
import view.ViewGUI;
import view.flexibleview.CommissionView;
import view.flexibleview.CreatePortfolioView;
import view.flexibleview.DisplayPortfolioComposition;
import view.flexibleview.EnterPortfolioNameView;
import view.flexibleview.GetCostBasisView;
import view.flexibleview.GetInvestmentStocksView;
import view.flexibleview.GetTotalValueView;
import view.flexibleview.InvestStrategyView;
import view.flexibleview.PurchaseStocksView;
import view.flexibleview.SellStockView;

/**
 * This class implements the Controller for flexible portfolios GUI.
 */
public class ControllerGUIFlexible implements FlexiblePortfolioFeatures {

  private final PortfolioFlexibleInterface model;

  private ViewGUI view;

  /**
   * This is the constructor of the ControllerGUIFlexible class that initializes
   * the model and loads the Jframes.
   */
  public ControllerGUIFlexible(PortfolioFlexibleInterface m) {
    model = m;
    JFramesList.loadJFrames();
  }

  /**
   * This is the view function that initialises the view.
   */
  public void setView(ViewGUI v) {
    view = v;
    view.addFeatures(this);
  }

  @Override
  public void createPortfolio() {
    this.view.isVisible(false);
    setView(new CreatePortfolioView("Create new portfolio"));
    this.view.isVisible(true);
  }

  @Override
  public void createPortfolioToModel(String portfolioName, String creationDate) {
    if (!UtilityClass.checkDateFormat(creationDate)
            || UtilityClass.checkFileExists(portfolioName, true)
            || UtilityClass.checkDateAfterToday(portfolioName, creationDate)) {
      if (!UtilityClass.checkDateFormat(creationDate)) {
        CreatePortfolioView.infoBox("Please enter date in YYYY-MM-DD format",
                "error");
        this.view.clearInputString();
      } else if (UtilityClass.checkDateAfterToday(portfolioName, creationDate)) {
        CreatePortfolioView.infoBox("Date format should be YYYY-MM-DD. " +
                "Future dates are considered invalid. " +
                "Please enter again.", "error");
        this.view.clearInputString();
      } else {
        CreatePortfolioView.infoBox("File already exists, please enter " +
                "the name of an existing portfolio. Also note that " +
                "future dates are considered invalid.", "error");
        this.view.clearInputString();
      }
    } else {
      model.setPortfolioName(portfolioName);
      List<Stocks> stocks = new ArrayList<>();
      try {
        model.createPortfolio(stocks, creationDate);
        CreatePortfolioView.infoBox("Portfolio created.", "info");
      } catch (Exception e) {
        CreatePortfolioView.infoBox("Oops, something went wrong, please try again!"
                , "error");
        this.view.clearInputString();
      }
      backToHomeMenu();
    }
  }

  @Override
  public void examinePortfolio() {
    this.view.isVisible(false);
    HashMap<String, Double> stocks = new HashMap<>();
    try {
      stocks = model.examinePortfolio();
    } catch (IOException e) {
      DisplayPortfolioComposition.infoBox("Unable to fetch portfolio composition",
              "error");
    }

    this.setView(new DisplayPortfolioComposition("Portfolio Composition", stocks));
    if (stocks.isEmpty()) {
      this.view.echoOutput("  Empty portfolio  ");
    }
    this.view.isVisible(true);
  }

  @Override
  public void purchaseStocks() {
    this.view.isVisible(false);
    List<HashMap<String, String>> stocksAppended = new ArrayList<>();
    this.setView(new PurchaseStocksView("Purchase stock", stocksAppended));
    this.view.isVisible(true);
  }

  private boolean checkStocksDetails(String date, String stockChoice, String numberOfShares) {
    boolean dateValid = true;
    boolean stockValid = false;
    boolean numValid = false;
    if (!UtilityClass.checkDateFormat(date)
            || UtilityClass.checkDateBeforeCreatedDate(model.getPortfolioName(), date)
            || UtilityClass.checkDateAfterToday(model.getPortfolioName(), date)) {
      dateValid = false;
    }
    List<String> stockChoices = new ArrayList<>();

    for (stockTicker st : stockTicker.values()) {
      stockChoices.add(st.toString());
    }
    if (stockChoices.contains(stockChoice)) {
      stockValid = true;
    }
    if (UtilityClass.checkIfPositiveInteger(numberOfShares, "Quit") == 1) {
      numValid = true;
    }
    return dateValid && stockValid && numValid;
  }

  @Override
  public void getPurchasedStocksFromView(List<HashMap<String, String>> stockList,
                                         String stockChoice, String numberOfShares, String date,
                                         boolean finishedPurchasing) {
    this.view.isVisible(false);
    if (finishedPurchasing) {
      boolean purchaseSuccessful = model.purchaseStocks(stockList);
      if (purchaseSuccessful) {
        PurchaseStocksView.infoBox("Purchased stocks", "info");
        backToPortfolioOperations();
      } else {
        PurchaseStocksView.infoBox("Something went wrong, " +
                "please try again.", "error");
      }
    } else {
      boolean valid = checkStocksDetails(date, stockChoice, numberOfShares);
      if (valid) {
        HashMap<String, String> stockDetail = new HashMap<>();
        stockDetail.put("Date", date);
        stockDetail.put("Stock-ticker", stockChoice);
        stockDetail.put("Number-of-shares", numberOfShares);
        stockList.add(stockDetail);
        this.setView(new PurchaseStocksView("Purchase stock", stockList));
        this.view.isVisible(true);
      } else {
        PurchaseStocksView.infoBox("Please ensure that date is a past date " +
                "lying between portfolio creation date and current date, and the number " +
                "is a positive integer value.", "error");
        this.view.clearInputString();
        this.view.isVisible(true);
      }
    }
  }

  @Override
  public void sellShares() {
    this.view.isVisible(false);
    this.setView(new SellStockView("Sell stock"));
    this.view.isVisible(true);
  }

  private String sellOk(String stockChoice, String numberOfShares,
                        String date) throws IOException {
    boolean initialCheck = checkStocksDetails(date, stockChoice, numberOfShares);
    if (initialCheck) {
      if (UtilityClass.checkDateChronology(stockChoice, date,
              model.getPortfolioName())) {
        if (!UtilityClass.checkSellIsValid(model.getPortfolioName(),
                numberOfShares, stockChoice)) {
          return ("Not enough shares present to sell.");
        } else {
          return "OK";
        }
      } else {
        return "A share can be sold only for a date after the most recent " +
                "purchase/sale of stock.";
      }
    } else {
      return "Please enter a positive number of stocks to sell. Also note that " +
              "a share can be sold only for a date after the most recent" +
              "purchase/sale of stock. (Date format should be in YYYY-MM-DD)";
    }
  }

  @Override
  public void performSellShare(String stockChoice, String numberOfShares, String date) {
    try {
      String sellOkay = sellOk(stockChoice, numberOfShares, date);
      if (sellOkay.equalsIgnoreCase("OK")) {
        boolean sellSuccessful = model.sellStocks(stockChoice,
                Integer.parseInt(numberOfShares), date);
        if (sellSuccessful) {
          SellStockView.infoBox("Sold successfully.", "info");
          this.view.clearInputString();
        } else {
          SellStockView.infoBox("Sell failed", "error");
        }
      } else {
        SellStockView.infoBox(sellOkay, "error");
      }
    } catch (Exception e) {
      SellStockView.infoBox("Something went wrong, please try again.",
              "error");
    }
  }

  @Override
  public void changeCommission(String comm) {
    if (!UtilityClass.checkIfPositiveFloat(comm)) {
      CommissionView.infoBox("Please enter a valid decimal value.", "error");
      this.view.clearInputString();
    } else {
      float commission;
      try {
        commission = Float.parseFloat(comm);
        model.changeCommission(commission);
        view.echoOutput("Commission changed to: " + comm);
        CommissionView.infoBox("Commission changed.", "info");
        backToPortfolioOperations();
      } catch (Exception e) {
        CommissionView.infoBox("Unable to change commission.", "error");
      }
    }
  }

  @Override
  public void costBasisDate() {
    view.isVisible(false);
    setView(new GetCostBasisView("Cost basis of portfolio"));
    view.isVisible(true);
  }

  @Override
  public void getCostBasis(String date) {
    if (!UtilityClass.checkDateFormat(date)
            || UtilityClass.checkDateAfterToday(model.getPortfolioName(), date)
            || UtilityClass.checkDateBeforeCreatedDate(model.getPortfolioName(), date)) {
      GetCostBasisView.infoBox("Please provide date in correct YYYY-MM-DD format. " +
              "Also note that we accept past dates between portfolio " +
              "creation date and present date only.", "error");
      this.view.clearInputString();
    } else {
      double cost = 0.0;
      try {
        cost = model.findCostBasis(date);
      } catch (IOException e) {
        GetCostBasisView.infoBox("Something went wrong, " +
                "please try again.", "error");
      }
      view.echoOutput("$" + cost);
    }
  }

  @Override
  public void totalValueDate() {
    view.isVisible(false);
    setView(new GetTotalValueView("Total value of a portfolio"));
    view.isVisible(true);
  }

  @Override
  public void getTotalValue(String date) {
    if (!UtilityClass.checkDateFormat(date)
            || UtilityClass.checkDateAfterToday(model.getPortfolioName(), date)) {
      GetTotalValueView.infoBox("Please provide date in correct YYYY-MM-DD format. " +
              "Also note that we accept past dates only", "error");
      this.view.clearInputString();
    } else {
      Double value = 0.0;
      try {
        value = model.getTotalValue(date);
      } catch (IOException e) {
        GetTotalValueView.infoBox("Could not find total value for this date.",
                "error");
      }
      view.echoOutput("$" + value);
    }
  }

  @Override
  public void investmentStrategy() {
    this.view.isVisible(false);
    setView(new InvestStrategyView("Investment Strategy"));
    this.view.isVisible(true);
  }

  @Override
  public void getInvestmentStrategyDetailsFromView(String strategyName, String investmentAmount,
                                                   String startingDate, String endingDate,
                                                   String recurringDays) {
    if (!UtilityClass.checkValidInvestment(strategyName, investmentAmount,
            startingDate, endingDate,
            recurringDays, model.getPortfolioName()).equalsIgnoreCase("ok")) {
      this.view.clearInputString();
      InvestStrategyView.infoBox(UtilityClass.checkValidInvestment(strategyName, investmentAmount,
              startingDate, endingDate,
              recurringDays, model.getPortfolioName()), "error");
    } else {
      if (endingDate.equalsIgnoreCase("none")) {
        endingDate = "2300-01-01";
      }
      this.view.isVisible(false);
      HashMap<String, Integer> stocks = new HashMap<>();
      setView(new GetInvestmentStocksView("Get stock to invest", stocks, strategyName,
              investmentAmount, startingDate, endingDate, recurringDays));
      this.view.isVisible(true);
    }
  }

  @Override
  public void processInvestmentStrategy(HashMap<String, Integer> stockList, String strategyName,
                                        String investmentAmount, String startDate, String endDate,
                                        String recurringDays, String stockChoice,
                                        String stockWeightage,
                                        boolean finishedAdding) {
    if (finishedAdding) {
      boolean checkValidTotalPercent = UtilityClass.checkValidTotalPercent(stockList);
      if (!checkValidTotalPercent) {
        stockList.clear();
        GetInvestmentStocksView.infoBox("Total weightage should add " +
                "up to exactly 100. " +
                "Please enter again.", "error");
        this.view.clearInputString();
      } else {
        try {
          model.createStrategy(Integer.parseInt(investmentAmount), stockList, startDate, endDate,
                  Integer.parseInt(recurringDays), strategyName);
          model.updateStrategy();
          this.view.isVisible(false);
          backToPortfolioOperations();
        } catch (Exception e) {
          GetInvestmentStocksView.infoBox("Sorry, something went wrong, " +
                  "please try again.", "error");
        }
      }
    } else {
      List<String> stockChoices = new ArrayList<>();

      for (stockTicker st : stockTicker.values()) {
        stockChoices.add(st.toString());
      }
      boolean stockValid = stockChoices.contains(stockChoice);
      if (!stockValid ||
              !UtilityClass.checkValidNumberOption(stockWeightage, 1,
                      100)) {
        GetInvestmentStocksView.infoBox("Please choose a valid stock " +
                "and a weight less than 100 and greater than 1", "error");
        this.view.clearInputString();

      } else {
        this.view.isVisible(false);
        stockList.put(stockChoice, Integer.parseInt(stockWeightage));
        setView(new GetInvestmentStocksView("Get stock to invest",
                stockList, strategyName,
                investmentAmount, startDate, endDate, recurringDays));
        this.view.isVisible(true);
      }
    }

  }

  @Override
  public void existingPortfolios() {
    this.view.isVisible(false);
    this.setView(JFramesList.ENTER_PORTFOLIO_NAME_VIEW);
    this.view.isVisible(true);
  }

  @Override
  public void setPortfolioName(String portfolioName) {
    if (!UtilityClass.checkFileExists(portfolioName, true)) {
      EnterPortfolioNameView.infoBox("File does not exist, please enter " +
              "the name of an existing portfolio.", "error");
      this.view.clearInputString();
    } else {
      this.view.isVisible(false);
      try {
        model.setPortfolioName(portfolioName);
        model.updateStrategy();
      } catch (Exception e) {
        EnterPortfolioNameView.infoBox("Something went wrong in loading portfolio," +
                "Please try again!", "error");
      }
      this.setView(JFramesList.FLEXIBLE_PORTFOLIO_OPTION_VIEW);
      this.view.isVisible(true);
    }
  }

  @Override
  public void backToPortfolioOperations() {
    this.view.isVisible(false);
    this.setView(JFramesList.FLEXIBLE_PORTFOLIO_OPTION_VIEW);
    this.view.isVisible(true);
  }

  @Override
  public void backToHomeMenu() {
    this.view.isVisible(false);
    this.setView(new JFrameSwingView("JFrameView"));
    this.view.isVisible(true);
  }

  @Override
  public void getCommission() {
    this.view.isVisible(false);
    float comm = model.getCommissionFromFile();
    setView(new CommissionView("Commission", comm));
    this.view.isVisible(true);
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }
}
