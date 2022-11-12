import java.io.IOException;
import java.io.InputStreamReader;

import controller.Controller;
import controller.ControllerImpl;
import model.PortfolioImpl;

/**
 * This is the driver program that delegates control to the controller.
 */
public class Main {
  /**
   * Main function of the program that transfer the control
   * to the controller.
   *
   * @param args default parameter
   */
  public static void main(String[] args) {
    Controller controller = new ControllerImpl(new InputStreamReader(System.in),
            System.out);
    try {
      controller.start(new PortfolioImpl());
    } catch (IOException e) {
      System.out.append(e.getMessage());
    }
  }
}
