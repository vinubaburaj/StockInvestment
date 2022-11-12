package view;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface that handles the view of the program.
 * Displays the options available and the results to the user.
 */
public interface View {
  /**
   * Method that displays the main menu to the client.
   */
  String showMenu();

  /**
   * Method that shows the options of stocks available in this program.
   */
  String showStockOptions();

  /**
   * Method to intimate user to enter the integer amount
   * of shares to be bought.
   */
  String showNumberOfSharesMessage();

  /**
   * Displays the contents of a portfolio.
   * Portfolio data is received as a list of string array.
   * Each String array contains the following information on its indices
   * [0] - Name of the stock.
   * [1] - Number of shares purchased.
   * [2] - Value of a share when purchased.
   * [3] - Date when share was purchased.
   *
   * @param stocks list of stocks contained in the portfolio.
   */
  String showPortfolio(List<String[]> stocks);

  /**
   * Method to intimate user to enter portfolio name
   * which is to be scanned for further operations.
   */
  String inputPortfolioName();

  /**
   * Method to display the total value of a portfolio
   * on a particular date to the user.
   */
  String showTotalValue(String portfolioName, String date, Double totalValue);

  /**
   * Method that intimates the user to display a valid date
   * between the two LocalDate values provided.
   *
   * @param dateToday        most recent local date user can provide.
   * @param lastHistoricDate oldest date user can provide.
   */
  String showDateMessage(LocalDate dateToday,
                         LocalDate lastHistoricDate);

  /**
   * Method that intimates the user that a portfolio has been
   * successfully created.
   */
  String createSuccessfulMessage();

  /**
   * Method that intimates the user when a portfolio
   * could not be created.
   */
  String createUnsuccessfulMessage();

  /**
   * Method that takes an error message as input from the controller
   * and displays it in the view component.
   *
   * @param error the error message to be displayed.
   */
  String displayErrorMessage(String error);

}