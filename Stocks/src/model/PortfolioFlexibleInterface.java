package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * This is the Portfolio interface for flexible portfolios which contains the function signatures
 * for all the functionalities that a user can use for a flexible portfolio.
 */
public interface PortfolioFlexibleInterface {

  /**
   * Method that handles the creation of a flexible portfolio in the inflexiblePortfolios folder.
   *
   * @param stocks all data of stocks that the portfolio is created with.
   */
  void createPortfolio(List<Stocks> stocks, String creationDate) throws Exception;

  /**
   * Method that retrieves portfolio data from files and returns
   * it in a hashmap.
   */
  HashMap<String, Double> examinePortfolio() throws IOException;

  /**
   * Method that handles the purchasing of a particular stock of a portfolio.
   *
   * @param stocks the data of all the stocks to purchase
   */
  boolean purchaseStocks(List<HashMap<String, String>> stocks);

  /**
   * Method that handles the selling of particular stock of a portfolio.
   *
   * @param tick        the ticker value of the stock to sell
   * @param numOfShares the number of shares to sell
   * @param date        the date on which the selling should occur
   */
  boolean sellStocks(String tick, int numOfShares, String date);

  /**
   * Method that gets the cost basis of a portfolio on a particular date. The cost
   * basis gives the total amount spent on the portfolio till that day.
   *
   * @param date the date at which it gets the cost basis of the portfolio.
   */
  double findCostBasis(String date) throws IOException;

  /**
   * Method that gets the total value of a portfolio on a particular date.
   *
   * @param date the date at which it gets the total
   *             value of the portfolio.
   */
  Double getTotalValue(String date) throws IOException;

  /**
   * Method that changes commission value in the commission file.
   *
   * @param c the new commission value
   */
  boolean changeCommission(float c);

  /**
   * Method that gets the current commission value.
   */
  float getCommissionFromFile();

  /**
   * Method that receives the control from controller in the model when the user wants
   * to know the performance of a portfolio.
   *
   * @param startDate the date from which the user wants the data
   * @param endDate   the date till which the user wants the data
   */
  StringBuilder getPerformanceChart(String startDate, String endDate)
          throws IOException;

  /**
   * This is the getter for portfolio name.
   */
  String getPortfolioName();

  /**
   * This is the setter for portfolio name.
   */
  void setPortfolioName(String s);

  /**
   * This function delegates the control to Portfolio DCA impl function for creating strategy.
   *
   * @param total       the total amount that the user wants to invest.
   * @param proportions the proportions for each stock.
   * @param start       the start date
   * @param end         the end date for the strategy.
   * @param days        the frequency in days.
   */
  void createStrategy(int total, HashMap<String, Integer> proportions, String start,
                      String end, int days, String name) throws Exception;

  /**
   * This function delegates the control to Portfolio DCA impl function for updating strategy.
   */
  void updateStrategy() throws Exception;


}
