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
}
