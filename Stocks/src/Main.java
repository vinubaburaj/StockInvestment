import java.io.IOException;
import java.io.InputStreamReader;

import controller.Controller;
import controller.ControllerImpl;
import model.PortfolioImpl;

public class Main {
  public static void main(String[] args){
    Controller controller = new ControllerImpl(new InputStreamReader(System.in),
            System.out);
    try {
      controller.start(new PortfolioImpl());
    }
    catch(IOException e){
      System.out.println(e.getMessage());
//      controller.start();
    }
  }
}
