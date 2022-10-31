package view;

/**
 * Interface that handles the view of the program.
 * Displays the options available and the results to the user.
 */
public interface View {
  /**
   * Method that displays the main menu to the client.
   */
  void showMenu();

  /**
   * Method that displays all the portfolios of a user.
   */
  void viewAllPortfolio();

  /**
   * Method that displays a particular portfolio of a user.
   */
  void viewPortfolio();

  /**
   * Method that shows the options of stocks available in this program.
   */
  void showStockOptions();

  /**
   * Method to intimate user to enter the integer amount
   * of shares to be bought.
   */
  void showNumberOfSharesMessage();
}
