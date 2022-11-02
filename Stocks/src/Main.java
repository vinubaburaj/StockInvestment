import controller.Controller;
import controller.ControllerImpl;

public class Main {
  public static void main(String[] args){
    Controller controller = new ControllerImpl(System.in, System.out);
    try {
      controller.start();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
//      controller.start();
    }
  }
}
