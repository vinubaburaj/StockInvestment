package controller;

import java.util.HashMap;
import java.util.List;

/**
 * This is the interface for the flexible portfolios GUI.
 */
public interface FlexiblePortfolioFeatures {

  /**
   * This function sets the CreatePortfolioView as the current view.
   */
  void createPortfolio();

  /**
   * This function initiates the request for creating a new empty portfolio.
   *
   * @param portfolioName the name of the portfolio.
   * @param creationDate  the portfolio creation date.
   */
  void createPortfolioToModel(String portfolioName, String creationDate);

  /**
   * This function initiates the request for creating a new empty portfolio.
   */
  void examinePortfolio();

  /**
   * This function calls the view for setting the existing portfolio name
   * to let the user work on existing portfolios.
   */
  void existingPortfolios();

  /**
   * This function called the view for user to enter details for purchasing stocks.
   */
  void purchaseStocks();

  /**
   * This function gets the details of all the stocks that the user wants to purchase
   * and delegates control to the model for purchasing stocks and updating portfolio.
   *
   * @param stockList          the list of all stocks to purchase with details.
   * @param stockChoice        the current stock choice.
   * @param numberOfShares     the number of shares to purchase.
   * @param date               the date to purchase on.
   * @param finishedPurchasing boolean to indicate if the user has finished purchasing.
   */
  void getPurchasedStocksFromView(List<HashMap<String, String>> stockList,
                                  String stockChoice, String numberOfShares, String date,
                                  boolean finishedPurchasing);

  /**
   * This function sets the SellStockView as the current view to let the user enter
   * details for stock to be sold.
   */
  void sellShares();

  /**
   * This function makes check on the details entered to sell stocks and delegates control
   * to the model for updating  to files about stocks to sell.
   *
   * @param stockChoice    the current stock choice.
   * @param numberOfShares the number of shares to purchase.
   * @param date           the date to purchase on.
   */
  void performSellShare(String stockChoice, String numberOfShares, String date);

  /**
   * This function takes the new commission from the view and delegates control to the mode
   * to make changes on the commission file.
   *
   * @param comm the new commission.
   */
  void changeCommission(String comm);

  /**
   * This function sets the GetCostBasisView as the current view to let
   * users get details about the cost basis that they want to find.
   */
  void costBasisDate();

  /**
   * This function delegates control to the model to find cost basis.
   *
   * @param date the date to find cost basis on.
   */
  void getCostBasis(String date);

  /**
   * This function sets the new view to GetTotalValueView to get details about the
   * total value that the user wants to calculate.
   */
  void totalValueDate();

  /**
   * This function delegates control to the model to get the total value of a portfolio
   * by date.
   *
   * @param date the date to find total value on.
   */
  void getTotalValue(String date);

  /**
   * This function sets the new view as investmentStrategyView.
   */
  void investmentStrategy();

  /**
   * This function gets the investment strategy details from the view and sets
   * the new view as GetInvestmentStocksView.
   *
   * @param investmentAmount the total amount that the user wants to invest.
   * @param strategyName     the name of the strategy.
   * @param startingDate     the start date
   * @param endingDate       the end date for the strategy.
   * @param recurringDays    the frequency in days.
   */
  void getInvestmentStrategyDetailsFromView(String strategyName, String investmentAmount,
                                            String startingDate, String endingDate,
                                            String recurringDays);

  /**
   * This function delegates control to the model to process investment strategy.
   *
   * @param investmentAmount the total amount that the user wants to invest.
   * @param strategyName     the name of the strategy.
   * @param startDate        the start date
   * @param endDate          the end date for the strategy.
   * @param recurringDays    the frequency in days.
   * @param stockList        the stocks with weightages.
   * @param stockChoice      the current stock choice.
   * @param stockWeightage   the current stock weightage.
   * @param finishedAdding   boolean to show if the user has finished adding.
   */
  void processInvestmentStrategy(HashMap<String, Integer> stockList, String strategyName,
                                 String investmentAmount, String startDate, String endDate,
                                 String recurringDays, String stockChoice, String stockWeightage,
                                 boolean finishedAdding);

  /**
   * This function delegates control to the model to set the portfolio name.
   *
   * @param portFolioName the portfolio name to set.
   */
  void setPortfolioName(String portFolioName);

  /**
   * This function gets the current commission value from model.
   */
  void getCommission();

  /**
   * This function sets the new view to the flexible portfolio operations view.
   */
  void backToPortfolioOperations();

  /**
   * This function sets the new view to the main menu.
   */
  void backToHomeMenu();

  /**
   * This function exits from the program.
   */
  void exitProgram();

}
