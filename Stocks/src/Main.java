import controller.Controller;
import controller.ControllerImpl;

public class Main {
  public static void main(String[] args){
    Controller controller = new ControllerImpl(System.in, System.out);
    controller.start();
  }
}
