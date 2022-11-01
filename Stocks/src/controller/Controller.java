package controller;

/**
 * Interface that contains the functions of a controller.
 * A controller bridges the gap between a model and view.
 */
public interface Controller {
  /**
   * Method that starts the operation of the Stocks program
   * from the controller.
   */
  void start() throws IllegalArgumentException;
}
