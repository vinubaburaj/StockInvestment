package view;

import view.flexibleview.EnterPortfolioNameView;
import view.flexibleview.FlexiblePortfolioOptionsView;

/**
 * Class that contains the view functionalities to be loaded at the start of the program.
 * These views are then reused at multiple points of the application instead of creating new
 * view frames.
 */
public class JFramesList {
  public static ViewGUI ENTER_PORTFOLIO_NAME_VIEW;
  public static ViewGUI FLEXIBLE_PORTFOLIO_OPTION_VIEW;

  /**
   * Function to load the views into the controller at the start of the program.
   */
  public static void loadJFrames() {
    ENTER_PORTFOLIO_NAME_VIEW = new EnterPortfolioNameView("Enter name of the portfolio");
    FLEXIBLE_PORTFOLIO_OPTION_VIEW = new FlexiblePortfolioOptionsView("Choose " +
            "an option you" +
            " wish to perform on a flexible portfolio:");
  }
}
