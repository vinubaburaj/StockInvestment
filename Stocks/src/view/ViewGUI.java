package view;

import controller.FlexiblePortfolioFeatures;

/**
 * Interface that handles the GUI view.
 * Displays the options available and the results to the user.
 */
public interface ViewGUI {

  /**
   * This method adds the action listeners to add features to the buttons.
   */
  void addFeatures(FlexiblePortfolioFeatures features);

  /**
   * This method sets the visibility of the view.
   */
  void isVisible(boolean visible);

  /**
   * This method echos an output to the screen.
   */
  void echoOutput(String output);

  /**
   * This method clears the inputs.
   */
  void clearInputString();

}
