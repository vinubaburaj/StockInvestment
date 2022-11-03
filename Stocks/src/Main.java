import java.io.IOException;
import java.io.InputStreamReader;

import controller.Controller;
import controller.ControllerImpl;

public class Main {
  public static void main(String[] args){
    Controller controller = new ControllerImpl(new InputStreamReader(System.in),
            System.out);
    try {
      controller.start();
    }
    catch(IOException e){
      System.out.println(e.getMessage());
//      controller.start();
    }
  }
}
