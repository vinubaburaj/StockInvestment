package controller;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import view.View;
import view.ViewImpl;

public class ControllerImpl implements Controller{
  final InputStream in;
  final PrintStream out;
  public ControllerImpl(InputStream in, PrintStream out){
    this.in = in;
    this.out = out;
  }

  @Override
  public void start() {
    View view = new ViewImpl();
    view.showMenu();
    Scanner scan = new Scanner(this.in);
    int option = scan.nextInt();
    this.out.println("Option chosen: " + option);
  }
}
