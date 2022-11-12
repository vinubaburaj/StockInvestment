package model;

import java.util.List;

/**
 * This is the Portfolio interface which contains the function signatures for
 * all the functionalities that a user can use regarding a portfolio.
 */
public interface Portfolio {

  /**
   * Method that creates an XML document that stores
   * all the data related to the portfolio.
   */
  void createPortfolio(List<Stocks> stocks, String portfolioName);

  /**
   * Method that gets the data of the portfolio from its XML and returns
   * it in a list of strings.
   *
   * @param portfolioName the file name from which it needs
   *                      to retrieve the data.
   */
  List<String[]> examinePortfolio(String portfolioName);

  /**
   * Method that gets the total value of a portfolio on a particular date.
   *
   * @param stocks the list of stocks in the portfolio
   * @param date   the date at which it gets the total
   *               value of the portfolio.
   */
  Double getTotalValue(List<String[]> stocks, String date);
}
